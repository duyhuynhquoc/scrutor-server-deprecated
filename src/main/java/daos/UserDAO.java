/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.User;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author LCCuong
 */
public class UserDAO {

    private static Connection conn = null;
    private static PreparedStatement preStm = null;
    private static ResultSet rs = null;

    public UserDAO() {
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

    public static User getTeacher(String userId, String password) throws Exception {
        User teacher = null;
        try {
            String sql = "SELECT userId, FullName, password, email, role FROM scrutor_User"
                    + "WHERE userId=? AND password=? AND Role=teacher";
            conn = DBUtils.makeConnection();

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userId);
            preStm.setString(2, password);
            rs = preStm.executeQuery();

            if (rs.next()) {
                teacher = new User(rs.getString("userId"), rs.getString("FullName"),
                        rs.getString("password"), rs.getString("email"), rs.getString("role"));
            }
        } finally {
            closeConnection();
        }
        return teacher;
    }

    public static User getStudent(String userId, String password) throws Exception {
        User student = null;
        try {
            String sql = "SELECT userId, FullName, password, email, role FROM scrutor_User"
                    + "WHERE userId=? AND password=? AND Role=student";
            conn = DBUtils.makeConnection();

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userId);
            preStm.setString(2, password);
            rs = preStm.executeQuery();

            if (rs.next()) {
                student = new User(rs.getString("userId"), rs.getString("FullName"),
                        rs.getString("password"), rs.getString("email"), rs.getString("role"));
            }
        } finally {
            closeConnection();
        }
        return student;
    }

    public static User createAccount(String userId, String fullname, String password, String email, String role) throws Exception {
        User user = null;
        try {
            String sql = "Insert Into scrutor_User"
                    + " values(?, ?, ?, ?, ?) ";
            conn = DBUtils.makeConnection();

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userId);
            preStm.setString(2, fullname);
            preStm.setString(3, password);
            preStm.setString(4, email);
            preStm.setString(5, role);
            rs = preStm.executeQuery();

            if (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("FullName"),
                        rs.getString("password"), rs.getString("email"), rs.getString("role"));
            }
        } finally {
            closeConnection();
        }
        return user;
    }

}
