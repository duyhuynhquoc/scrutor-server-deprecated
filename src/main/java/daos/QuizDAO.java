package daos;

import dtos.Question;
import dtos.Quiz;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class QuizDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public QuizDAO() {
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
    public boolean CreateNewQuiz(String description, Date startAt, Date endAt,  int time) throws SQLException, Exception {
        String sql = "INSERT INTO Quiz ([description], [startAt], [endAt], [time]) VALUES (?, ?, ?, ?)";

        try {
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            if (conn != null) {
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, description);
                preStm.setDate(2, startAt);
                preStm.setDate(3, endAt);
                preStm.setInt(4, time);
                preStm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public Quiz getQuizById(String Id)throws SQLException, Exception{
        try {
            conn = DBUtils.makeConnection();
            String sql = "SELECT * FROM Quiz WHERE quizId=?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, Id);
            rs = preStm.executeQuery();
            while(rs.next()) {
                String quizId = rs.getString("quizId");
                String quizName = rs.getString("quizName");
                String description = rs.getString("description");
                Date startAt = rs.getDate("startAt");
                Date endAt = rs.getDate("endAt");
                int time = rs.getInt("time");
                String userId = rs.getString("userId");
                Quiz quiz = new Quiz(quizId, quizName, description,startAt, endAt, time, userId);
                return quiz;
            }
        }catch (Exception e) {
            System.out.println(e);
        } finally {
            this.closeConnection();
        }
        return null;
    }
}
