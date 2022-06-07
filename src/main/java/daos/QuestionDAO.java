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
    public boolean CreateNewQuestion(Question q) throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement preStm = null;
        String sql = "INSERT INTO QUESTION (userId, content, type, difficulty) VALUES (?, ?, ?, ?, ?)";

        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, q.getUserId());
                preStm.setString(2, q.getQuestionContent());
                preStm.setString(3, q.getType());
                preStm.setInt(4, q.getDifficulty());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean editQuestion(Question question) throws SQLException, Exception {
        String sql = "UPDATE scrutor_question SET userId=?, "
                + "content=?, type=?, dificulty=? WHERE questionId=?";
        try {
            conn = DBUtils.makeConnection();
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
        }finally{
            closeConnection();
        }
        return false;
    }

    public ArrayList<QuestionBank> getAllQuestions() throws SQLException, Exception {
        try {
            conn = DBUtils.makeConnection();
            String sql = "SELECT questionId, content, type, dificulty, userId," +
                    "questionOptionId, content, isCorrect"
                + "from Question JOIN  ";

            preStm = conn.prepareStatement(sql);

            rs = preStm.executeQuery();

            ArrayList<QuestionBank> list = new ArrayList<QuestionBank>();

            while (rs.next()) {
                String questionId = rs.getString("questionId");
                String contentq = rs.getString("content");
                String type = rs.getString("type");
                int difficulty = rs.getInt("difficulty");
                String userId = rs.getString("userId");

//                String questionId = rs.getString("questionId");
                String questionOptionId = rs.getString("questionOptionId");
                String contenta = rs.getString("content");
                Boolean isCorrect = rs.getBoolean("isCorrect");
                Question q = new Question(questionId, contentq, type, difficulty, userId );
                QuestionOption qo = new QuestionOption(questionOptionId, questionId, contenta, isCorrect);

                list.add(new QuestionBank(q, qo));
            }
            return list;
        } finally {
           this.closeConnection();
        }
    }
}
