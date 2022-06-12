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

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public QuestionDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public boolean CreateNewQuestion(String userId, String questionContent, String type, int difficulty) throws SQLException, Exception {
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

    public boolean CreateQuestionOption(String questionId, String questionOptionContent, Boolean isCorrect) throws Exception {
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

    public boolean editQuestion(Question question) throws SQLException, Exception {
        String sql = "UPDATE question SET userId=?, "
                + "content=?, type=?, dificulty=? WHERE questionId=?";
        try {
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            if (conn != null) {
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, question.getUserId());
                preStm.setString(2, question.getQuestionContent());
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

    public ArrayList<QuestionBank> getAllQuestions() throws SQLException, Exception {
        try {
            conn = DBUtils.makeConnection();
            String sql = "SELECT questionId, userId, questionContent, type, dificulty"
                    + "questionOptionId, questionOptionContent, isCorrect"
                    + "from Question LEFT JOIN QuestionOption On Question.questionId = QuestionOption.questionId";

            preStm = conn.prepareStatement(sql);

            rs = preStm.executeQuery();

            ArrayList<QuestionBank> list = new ArrayList<QuestionBank>();

            while (rs.next()) {
                String questionId = rs.getString("questionId");
                String questionContent = rs.getString("content");
                String type = rs.getString("type");
                int difficulty = rs.getInt("difficulty");
                String userId = rs.getString("userId");

                String questionOptionId = rs.getString("questionOptionId");
                String questionOptionContent = rs.getString("content");
                Boolean isCorrect = rs.getBoolean("isCorrect");
                Question q = new Question(questionId, userId, questionContent, type, difficulty);
                QuestionOption qo = new QuestionOption(questionOptionId, questionId, questionOptionContent, isCorrect);

                list.add(new QuestionBank(q, qo));
            }
            return list;
        } finally {
            this.closeConnection();
        }
    }

    public Question getQuestionById(String Id) throws SQLException, Exception {
        try {
            conn = DBUtils.makeConnection();
            String sql = "SELECT * FROM QUESTION WHERE questionId=?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, Id);
            rs = preStm.executeQuery();
            while (rs.next()) {
                String questionId = rs.getString("questionId");
                String questionContent = rs.getString("content");
                String type = rs.getString("type");
                int difficulty = rs.getInt("difficulty");
                String userId = rs.getString("userId");
                Question question = new Question(questionId, userId, questionContent, type, difficulty);
                return question;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            this.closeConnection();
        }
        return null;
    }

    ///////////////////////////////////////////// KHOA
    public ArrayList<QuestionBank> getQuestionByTagName(String searchTagName) throws SQLException, Exception {
        try {
            conn = DBUtils.makeConnection();
            // 2 bảng John ( 1 cái để lấy Tag Name và 1 để lấy question Option)
            String sql = "SELECT questionId, questionContent, type, dificulty, userId, "
                    + "questionOptionId, questionOptionContent, isCorrect, "
                    + "from Question  question RIGHT JOIN  QuestionOption questionOption on question.questionId = questionOption.questionId "
                    + "where question.questionId =  (select questionTag.questionId "
                    + //trả về 1 list questionID mà người dùng tìm
                    "from   Question_Tag questionTag join Tag tag on questionTag.tagId = tag.tagId "
                    + "where tag.tagId like ?) ";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + searchTagName + "%");
            rs = preStm.executeQuery();

            ArrayList<QuestionBank> list = new ArrayList<>();
            // Nếu a không muốn hiện questionOption thì xóa nó đi nha
            while (rs.next()) {
                String questionId = rs.getString("questionId");
                String questionContent = rs.getString("questionContent");
                String type = rs.getString("type");
                int difficulty = rs.getInt("dificulty");
                String userId = rs.getString("userId");
                String questionOptionId = rs.getString("questionOptionId");
                String questionOptionContent = rs.getString("questionOptionContent");
                Boolean isCorrect = rs.getBoolean("isCorrect");

                Question q = new Question(questionId, userId, questionContent, type, difficulty);
                QuestionOption qo = new QuestionOption(questionOptionId, questionId, questionOptionContent, isCorrect);

                list.add(new QuestionBank(q, qo));
            }
            return list;
        } finally {
            this.closeConnection();
        }
    }

    //GetQuestionOptionByQuestionId
    public ArrayList<QuestionBank> getQuestionOptionByQuestionID(String searchQuestionId) throws SQLException, Exception {
        try {
            conn = DBUtils.makeConnection();

            String sql = "SELECT question.questionId, question.questionContent, question.type, question.dificulty, question.userId, "
                    + "questionOption.questionOptionId, questionOption.questionOptionContent, questionOption.isCorrect "
                    + "from Question  question FULL OUTER JOIN QuestionOption questionOption on question.questionId = questionOption.questionId "
                    + "where  question.questionId  like ?";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + searchQuestionId + "%");
            rs = preStm.executeQuery();

            ArrayList<QuestionBank> list = new ArrayList<>();
            // Sửa lại kiểu dữ liệu để thông với DB với e với
            while (rs.next()) {
                String questionId = rs.getString("questionId");
                String questionContent = rs.getString("questionContent");
                String type = rs.getString("type");
                Integer difficulty = rs.getInt("dificulty");
                String userId = rs.getString("userId");
                String questionOptionId = rs.getString("questionOptionId");
                String questionOptionContent = rs.getString("questionOptionContent");
                Boolean isCorrect = rs.getBoolean("isCorrect");

                Question q = new Question(questionId, userId, questionContent, type, difficulty);
                QuestionOption qo = new QuestionOption(questionOptionId, questionId, questionOptionContent, isCorrect);

                list.add(new QuestionBank(q, qo));
            }
            return list;
        } finally {
            this.closeConnection();
        }
    }

    ///////////////////////////////// THUAN
    public void deleteQuestion(String id) throws Exception {
        try {
            conn = DBUtils.makeConnection();
            String query = "DELETE from Question where questionId=" + id;
            preStm = conn.prepareCall(query);
            preStm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void deleteQuestionChoices(String questionId) throws Exception {
        try {
            conn = DBUtils.makeConnection();
            String query = "DELETE from QuestionOption where questionId=" + questionId;
            preStm = conn.prepareCall(query);
            preStm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}
