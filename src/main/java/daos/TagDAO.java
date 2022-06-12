/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Tag;
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
public class TagDAO {
    
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public TagDAO() {
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
    //9/6/2022
    public ArrayList<Tag> GetAllTag() throws SQLException, Exception {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            String sql = "SELECT tagId, tagName "
                    + "from Tag";

            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery();

            ArrayList<Tag> list = new ArrayList<Tag>();

            while (rs.next()) {
                String tagId = rs.getString("tagId");
                String tagName = rs.getString("tagName");

                Tag tag = new Tag(tagId,tagName);
                list.add(tag);
            }
            return list;
        } finally {
            this.closeConnection();
        }
    }
}
