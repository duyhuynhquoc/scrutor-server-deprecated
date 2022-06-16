package daos;

import dtos.Question;
import dtos.QuestionBank;
import dtos.QuestionOption;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public static boolean CreateNewQuestion(String userId, String questionContent, String type, int difficulty) throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement preStm = null;
        String sql = "INSERT INTO QUESTION ([userId], [questionContent], [type], [difficulty]) VALUES (?, ?, ?, ?)";

        try {
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            if (conn != null) {
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, userId);
                preStm.setString(2, questionContent);
                preStm.setString(3, type);
                preStm.setInt(4, difficulty);
                preStm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
        return true;
    }

    public static boolean CreateQuestionOption(String questionId, String questionOptionContent, Boolean isCorrect) throws Exception {
        Connection conn = null;
        PreparedStatement preStm = null;
        String sql = "INSERT INTO QuestionOption ([questionId],[questionOptionContent],[isCorrect]) values (?,?,?,?)";
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                preStm = conn.prepareStatement(sql);
                preStm.setString(2, questionId);
                preStm.setString(3, questionOptionContent);
                preStm.setBoolean(4, isCorrect);
                preStm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
        return true;
    }

    public static boolean editQuestion(Question question) throws SQLException, Exception {
        String sql = "UPDATE question SET userId=?, "
                + "content=?, type=?, dificulty=? WHERE questionId=?";
        try {
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            if (conn != null) {
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, question.getUserId());
                preStm.setString(2, question.getContent());
                preStm.setString(3, question.getType());
                preStm.setInt(4, question.getDifficulty());
                preStm.setString(5, question.getQuestionId());
                preStm.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
        return true;
    }

    public static ArrayList<Question> getQuestions() {
        conn = null;
        preStm = null;
        rs = null;
        ArrayList<Question> list = new ArrayList<>();

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {

                String sql = "SELECT Question.questionId, userId, Question.content, type, difficulty, questionOptionId, QuestionOption.content, isCorrect FROM Question LEFT JOIN QuestionOption On Question.questionId = QuestionOption.questionId;";

                preStm = conn.prepareStatement(sql);

                rs = preStm.executeQuery();


                if (rs != null) {
                    String preQuestionId = "";
                    while (rs.next()) {
                        String questionId = rs.getString("Question.questionId");
                        String questionContent = rs.getString("Question.content");
                        String type = rs.getString("type");
                        int difficulty = rs.getInt("difficulty");
                        String userId = rs.getString("userId");

                        String questionOptionId = rs.getString("questionOptionId");
                        String questionOptionContent = rs.getString("QuestionOption.content");
                        Boolean isCorrect = rs.getBoolean("isCorrect");

                        QuestionOption qo = new QuestionOption(questionOptionId, questionId, questionOptionContent, isCorrect);


                        if (!questionId.equals(preQuestionId)) {
                            Question q = new Question(questionId, userId, questionContent, type, difficulty);
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
