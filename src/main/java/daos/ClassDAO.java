/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.ClassDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBUtils;

/**
 *
 * @author LCCuong
 */
public class ClassDAO {
    //9/6/2022 Get All Class
    public ArrayList<ClassDetail> getAllClass() throws SQLException, Exception {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            String sql = "SELECT classId, className "
                    + "from Class";

            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery();
            ArrayList<ClassDetail> list = new ArrayList<ClassDetail>();

            while (rs.next()) {
                String classId = rs.getString("classId");
                String className = rs.getString("className");
                //mọi người đặt tên lại class1 giúp em
                ClassDetail classInfo = new ClassDetail(classId, className);
                list.add(classInfo);
            }
            return list;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    //9/6/2022 Get ClassName by UserID
    public String getClassNameByID(String userID) throws SQLException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String className = null;
        try {
            con = DBUtils.makeConnection();
            String sql = "SELECT className "
                    + "from Class "
                    + "Where tagId like ?";

            preStm = con.prepareStatement(sql);
            preStm.setString(1, userID);
            rs = preStm
                    .executeQuery();
            if (rs.next()) {
                className = rs.getString("className");
            }
        } finally {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (con != null) {
            con.close();
        }
        return className;
    }

    }
}
