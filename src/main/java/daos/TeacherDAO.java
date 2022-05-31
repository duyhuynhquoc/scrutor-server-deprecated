/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Question;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author LCCuong
 */
public class TeacherDAO {
    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;

    public TeacherDAO() {
    }
    
    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (pstm != null) {
            pstm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    public boolean editQuestion(Question question) throws Exception{
        String sql = "UPDATE scrutor_question SET teacherId=?, "
                + "content=?, type=?, dificulty=? WHERE questionId=?";
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, question.getTeacherId());
                pstm.setString(2, question.getContent());
                pstm.setString(3, question.getType());
                pstm.setInt(4, question.getDifficulty());
                pstm.setInt(5, question.getQuestionId());
                pstm.executeUpdate();
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
}
