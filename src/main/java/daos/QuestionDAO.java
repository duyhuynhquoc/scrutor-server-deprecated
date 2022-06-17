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

    public QuestionDAO() {
    }

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


//    public static boolean editQuestion(Question question) throws SQLException, Exception {
//        String sql = "UPDATE question SET userId=?, "
//                + "content=?, type=?, dificulty=? WHERE questionId=?";
//        try {
//            conn = DBUtils.makeConnection();
//            preStm = conn.prepareStatement(sql);
//            if (conn != null) {
//                preStm = conn.prepareStatement(sql);
//                preStm.setString(1, question.getUserId());
//                preStm.setString(2, question.getContent());
//                preStm.setString(3, question.getType());
//                preStm.setInt(4, question.getDifficulty());
//                preStm.setString(5, question.getQuestionId());
//                preStm.executeUpdate();
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            closeConnection();
//        }
//        return true;
//    }

    public static ArrayList<Question> getQuestions(String teacherId) {
        conn = null;
        preStm = null;
        rs = null;
        ArrayList<Question> list = new ArrayList<>();

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {

                String sql = "SELECT tbl_Question.questionId, teacherId, tbl_Question.content, type, difficulty, optionId, tbl_Option.content, isCorrect FROM tbl_Question LEFT JOIN tbl_Option On tbl_Question.questionId = tbl_Option.questionId WHERE teacherId = ?;";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, teacherId);
                rs = preStm.executeQuery();

                if (rs != null) {
                    String preQuestionId = "";
                    while (rs.next()) {
                        String questionId = rs.getString("tbl_Question.questionId");
                        String questionContent = rs.getString("tbl_Question.content");
                        String type = rs.getString("type");
                        int difficulty = rs.getInt("difficulty");

                        String questionOptionId = rs.getString("optionId");
                        String questionOptionContent = rs.getString("tbl_Option.content");
                        Boolean isCorrect = rs.getBoolean("isCorrect");

                        Option qo = new Option(questionOptionId, questionId, questionOptionContent, isCorrect);


                        if (!questionId.equals(preQuestionId)) {
                            Question q = new Question(questionId, teacherId, questionContent, type, difficulty);
                            list.add(q);
                        }

                        list.get(list.size() - 1).addOption(qo);
                        preQuestionId = questionId;
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

                // Insert question
                String sql = "INSERT INTO tbl_Question (questionId, teacherId, content, type, difficulty) VALUES(?, ?, ?, ?, ?);";
                preStm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

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
                return null;
            }
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }

        return q;
    }

//    public static Question getQuestionById(String Id) throws SQLException, Exception {
//        try {
//            conn = DBUtils.makeConnection();
//            String sql = "SELECT * FROM QUESTION WHERE questionId=?";
//            preStm = conn.prepareStatement(sql);
//            preStm.setString(1, Id);
//            rs = preStm.executeQuery();
//            while (rs.next()) {
//                String questionId = rs.getString("questionId");
//                String questionContent = rs.getString("content");
//                String type = rs.getString("type");
//                int difficulty = rs.getInt("difficulty");
//                String userId = rs.getString("userId");
//                Question question = new Question(questionId, userId, questionContent, type, difficulty);
//                return question;
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            closeConnection();
//        }
//        return null;
//    }
//
//    ///////////////////////////////////////////// KHOA
//    public static ArrayList<QuestionBank> getQuestionByTagName(String searchTagName) throws SQLException, Exception {
//        try {
//            conn = DBUtils.makeConnection();
//            // 2 bảng John ( 1 cái để lấy Tag Name và 1 để lấy question Option)
//            String sql = "SELECT questionId, questionContent, type, dificulty, userId, "
//                    + "questionOptionId, questionOptionContent, isCorrect, "
//                    + "from Question  question RIGHT JOIN  QuestionOption questionOption on question.questionId = questionOption.questionId "
//                    + "where question.questionId =  (select questionTag.questionId "
//                    + //trả về 1 list questionID mà người dùng tìm
//                    "from   Question_Tag questionTag join Tag tag on questionTag.tagId = tag.tagId "
//                    + "where tag.tagId like ?) ";
//
//            preStm = conn.prepareStatement(sql);
//            preStm.setString(1, "%" + searchTagName + "%");
//            rs = preStm.executeQuery();
//
//            ArrayList<QuestionBank> list = new ArrayList<>();
//            // Nếu a không muốn hiện questionOption thì xóa nó đi nha
//            while (rs.next()) {
//                String questionId = rs.getString("questionId");
//                String questionContent = rs.getString("questionContent");
//                String type = rs.getString("type");
//                int difficulty = rs.getInt("dificulty");
//                String userId = rs.getString("userId");
//                String questionOptionId = rs.getString("questionOptionId");
//                String questionOptionContent = rs.getString("questionOptionContent");
//                Boolean isCorrect = rs.getBoolean("isCorrect");
//
//                Question q = new Question(questionId, userId, questionContent, type, difficulty);
//                QuestionOption qo = new QuestionOption(questionOptionId, questionId, questionOptionContent, isCorrect);
//
//                list.add(new QuestionBank(q, qo));
//            }
//            return list;
//        } finally {
//            closeConnection();
//        }
//    }
//
//    //GetQuestionOptionByQuestionId
//    public static ArrayList<QuestionBank> getQuestionOptionByQuestionID(String searchQuestionId) throws SQLException, Exception {
//        try {
//            conn = DBUtils.makeConnection();
//
//            String sql = "SELECT question.questionId, question.questionContent, question.type, question.dificulty, question.userId, "
//                    + "questionOption.questionOptionId, questionOption.questionOptionContent, questionOption.isCorrect "
//                    + "from Question  question FULL OUTER JOIN QuestionOption questionOption on question.questionId = questionOption.questionId "
//                    + "where  question.questionId  like ?";
//
//            preStm = conn.prepareStatement(sql);
//            preStm.setString(1, "%" + searchQuestionId + "%");
//            rs = preStm.executeQuery();
//
//            ArrayList<QuestionBank> list = new ArrayList<>();
//            // Sửa lại kiểu dữ liệu để thông với DB với e với
//            while (rs.next()) {
//                String questionId = rs.getString("questionId");
//                String questionContent = rs.getString("questionContent");
//                String type = rs.getString("type");
//                Integer difficulty = rs.getInt("dificulty");
//                String userId = rs.getString("userId");
//                String questionOptionId = rs.getString("questionOptionId");
//                String questionOptionContent = rs.getString("questionOptionContent");
//                Boolean isCorrect = rs.getBoolean("isCorrect");
//
//                Question q = new Question(questionId, userId, questionContent, type, difficulty);
//                QuestionOption qo = new QuestionOption(questionOptionId, questionId, questionOptionContent, isCorrect);
//
//                list.add(new QuestionBank(q, qo));
//            }
//            return list;
//        } finally {
//            closeConnection();
//        }
//    }
//
//    ///////////////////////////////// THUAN
//    public static void deleteQuestion(String id) throws Exception {
//        try {
//            conn = DBUtils.makeConnection();
//            String query = "DELETE from Question where questionId=" + id;
//            preStm = conn.prepareCall(query);
//            preStm.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            closeConnection();
//        }
//    }
//
//    public static void deleteQuestionChoices(String questionId) throws Exception {
//        try {
//            conn = DBUtils.makeConnection();
//            String query = "DELETE from QuestionOption where questionId=" + questionId;
//            preStm = conn.prepareCall(query);
//            preStm.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            closeConnection();
//        }
//    }
}
