package daos;

import dtos.ClassDetail;
import dtos.Question;
import dtos.User_Quiz;
import utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;

public class User_QuizDAO {

    private static Connection conn;
    private static PreparedStatement preStm;
    private static ResultSet rs;

    public User_QuizDAO() {
    }

    private static void closeConnection() throws Exception {
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

    public static boolean CreateUser_Quiz(String userId, String quizId, int grade) throws SQLException, Exception {
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

    //9/6/2022 Hàm dùng ID của 1 quiz để show tất cả User
//    public ArrayList<User_Quiz> GetAllStudentdoQuiz(String QuizID) throws SQLException {
//        String sql = "select * from User_Quiz uq left join User u "
//                + "on uq.userId = u.userId where quizId = ?";
//        try {
//            conn = DBUtils.makeConnection();
//            preStm = conn.prepareStatement(sql);
//            rs = preStm.executeQuery();
//            ArrayList<User_Quiz> list = new ArrayList<User_Quiz>();
//
//            while (rs.next()) {
//                String classId = rs.getString("classId");
//                String className = rs.getString("className");
//                //mọi người đặt tên lại class1 giúp em
//                ClassDetail classInfo = new ClassDetail(classId, className);
//                list.add(classInfo);
//            }
//            return list;
//
//        } finally {
//            this.closeConnection();
//        }
//    }

}
