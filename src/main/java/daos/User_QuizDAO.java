package daos;

import dtos.Question;
import utils.DBUtils;

import java.sql.*;

public class User_QuizDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public User_QuizDAO() {
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
    public boolean CreateUser_Quiz(String userId, String quizId, int grade) throws SQLException, Exception {
        String sql = "INSERT INTO User_Quiz ([userId], [quizId], [grade]) VALUES (?, ?, ?)";

        try {
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            if (conn != null) {
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, userId);
                preStm.setString(2, quizId);
                preStm.setInt(3, grade);
                preStm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
