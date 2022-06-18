package daos;

import dtos.Question;
import dtos.Option;
import dtos.Tag;
import utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class QuestionDAO {

    private static Connection conn;
    private static PreparedStatement preStm;
    private static ResultSet rs;

    private static void closeConnection() {
        try {

            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Question> getQuestions(String teacherId) {
        conn = null;
        preStm = null;
        rs = null;
        ArrayList<Question> list = new ArrayList<>();

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {

                String sql = "SELECT q.questionId, q.content, q.type, q.difficulty, t.tagId, t.name, o.optionId, o.content, o.isCorrect\n" +
                        "FROM tbl_Question as q\n" +
                        "INNER JOIN tbl_Question_Tag as qt\n" +
                        "ON q.questionId = qt.questionId\n" +
                        "INNER JOIN tbl_Tag as t\n" +
                        "ON t.tagId = qt.tagId\n" +
                        "INNER JOIN tbl_Option as o\n" +
                        "ON q.questionId = o.questionId\n" +
                        "WHERE teacherId = ?\n" +
                        "ORDER BY q.content, t.name;";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, teacherId);
                rs = preStm.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        String questionId = rs.getString("q.questionId");
                        String questionContent = rs.getString("q.content");
                        String type = rs.getString("q.type");
                        int difficulty = rs.getInt("q.difficulty");

                        String tagId = rs.getString("t.tagId");
                        String name = rs.getString("t.name");

                        String optionId = rs.getString("o.optionId");
                        String optionContent = rs.getString("o.content");
                        boolean isCorrect = rs.getBoolean("o.isCorrect");

                        // Not the same last question
                        if (list.size() == 0 || !questionId.equals(list.get(list.size() - 1).getQuestionId())) {
                            Question q = new Question(questionId, teacherId, questionContent, type, difficulty);

                            list.add(q);
                        }

                        // Not the same last tag
                        if ((list.size() > 0 && list.get(list.size() - 1).getTags().size() > 0) || !tagId.equals(list.get(list.size() - 1).getTags().get(list.get(list.size() - 1).getTags().size() - 1).getTagId())) {
                            Tag t = new Tag(tagId, name);
                            list.get(list.size() - 1).addTag(t);
                        }

                        Option o = new Option(optionId, questionId, optionContent, isCorrect);
                        list.get(list.size() - 1).addOption(o);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    public static Question createQuestion(Question q, String teacherId) {
        conn = null;
        preStm = null;
        rs = null;

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {
                conn.setAutoCommit(false);

                q.setTeacherId(teacherId);
                q.setQuestionId(UUID.randomUUID().toString());

                // Insert question
                String sql = "INSERT INTO tbl_Question (questionId, teacherId, content, type, difficulty) VALUES(?, ?, ?, ?, ?);";
                preStm = conn.prepareStatement(sql);

                preStm.setString(1, q.getQuestionId());
                preStm.setString(2, q.getTeacherId());
                preStm.setString(3, q.getContent());
                preStm.setString(4, q.getType());
                preStm.setInt(5, q.getDifficulty());
                preStm.executeUpdate();

                // Insert options
                for (Option o : q.getOptions()) {
                    try {
                        o.setQuestionId(q.getQuestionId());
                        o.setOptionId(UUID.randomUUID().toString());

                        preStm = null;
                        sql = "INSERT INTO tbl_Option (optionId, questionId, content, isCorrect) VALUES (?, ?, ?, ?);";
                        preStm = conn.prepareStatement(sql);
                        preStm.setString(1, o.getOptionId());
                        preStm.setString(2, o.getQuestionId());
                        preStm.setString(3, o.getContent());
                        preStm.setBoolean(4, o.getIsCorrect());
                        preStm.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                // Insert tags
                for (Tag t : q.getTags()) {
                    try {
                        sql = "SELECT tagId FROM tbl_Tag WHERE name = ? LIMIT 1";
                        preStm = conn.prepareStatement(sql);
                        preStm.setString(1, t.getName());
                        rs = preStm.executeQuery();


                        // Check whether tag is exist in tbl_Tag
                        if (rs != null && rs.next()) {
                            String tagId = rs.getString("tagId");
                            t.setTagId(tagId);
                        } else {
                            try {
                                t.setTagId(UUID.randomUUID().toString());

                                preStm = null;
                                sql = "INSERT INTO tbl_Tag (tagId, name) VALUES (?, ?);";
                                preStm = conn.prepareStatement(sql);
                                preStm.setString(1, t.getTagId());
                                preStm.setString(2, t.getName());
                                preStm.executeUpdate();
                            } catch (Exception tagException) {
                                tagException.printStackTrace();
                            }
                        }

                        try {
                            preStm = null;
                            sql = "INSERT INTO tbl_Question_Tag (questionId, tagId) VALUES (?, ?);";
                            preStm = conn.prepareStatement(sql);
                            preStm.setString(1, q.getQuestionId());
                            preStm.setString(2, t.getTagId());
                            preStm.executeUpdate();
                        } catch (Exception tagException) {
                            tagException.printStackTrace();
                            System.out.println("Duplicated tag in question");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }

            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                return null;
            }
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }

        return q;
    }

    public static boolean updateQuestion(Question q, String teacherId) {

        conn = null;
        preStm = null;
        rs = null;

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {
                conn.setAutoCommit(false);

                // Update question info
                String sql = "UPDATE tbl_Question SET content = ?, type = ?, difficulty = ? WHERE questionId = ? AND teacherId = ?;";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, q.getContent());
                preStm.setString(2, q.getType());
                preStm.setInt(3, q.getDifficulty());
                preStm.setString(4, q.getQuestionId());
                preStm.setString(5, teacherId);
                preStm.executeUpdate();

                // Delete old options of the question
                preStm = null;
                sql = "DELETE FROM tbl_Option WHERE questionId = ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, q.getQuestionId());
                preStm.executeUpdate();

                // Delete old tags of the question
                preStm = null;
                sql = "DELETE FROM tbl_Question_Tag WHERE questionId = ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, q.getQuestionId());
                preStm.executeUpdate();

                // Insert new options
                for (Option o : q.getOptions()) {
                    try {
                        o.setQuestionId(q.getQuestionId());
                        preStm = null;
                        sql = "INSERT INTO tbl_Option (optionId, questionId, content, isCorrect) VALUES (?, ?, ?, ?);";
                        preStm = conn.prepareStatement(sql);
                        preStm.setString(1, o.getOptionId());
                        preStm.setString(2, o.getQuestionId());
                        preStm.setString(3, o.getContent());
                        preStm.setBoolean(4, o.getIsCorrect());
                        preStm.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                // Insert new tags
                for (Tag t : q.getTags()) {
                    try {
                        sql = "SELECT tagId, name FROM tbl_Tag WHERE name = ?";
                        preStm = conn.prepareStatement(sql);
                        preStm.setString(1, t.getName());
                        rs = preStm.executeQuery();

                        if (rs != null) {
                            while (rs.next()) {
                                String tagId = rs.getString("tagId");
                                String name = rs.getString("name");
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }

                    try {
                        preStm = null;
                        sql = "INSERT INTO tbl_Tag (tagId, name) VALUES (?, ?);";
                        preStm = conn.prepareStatement(sql);
                        preStm.setString(1, t.getTagId());
                        preStm.setString(2, t.getName());
                        preStm.executeUpdate();
                    } catch (Exception tagException) {
                        tagException.printStackTrace();
                        System.out.println("Duplicated tag");
                    }

                    try {
                        preStm = null;
                        sql = "INSERT INTO tbl_Question_Tag (questionId, tagId) VALUES (?, ?);";
                        preStm = conn.prepareStatement(sql);
                        preStm.setString(1, q.getQuestionId());
                        preStm.setString(2, t.getTagId());
                        preStm.executeUpdate();
                    } catch (Exception tagException) {
                        tagException.printStackTrace();
                        System.out.println("Duplicated tag in question");
                    }
                }
            }

            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                return false;
            }
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }

        return true;
    }
}
