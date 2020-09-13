/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.SubmissionBeans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnectionClass;

/**
 *
 * @author 1389026
 */
public class SubmissionDao {

    private Connection con = null;

    public SubmissionDao() {
        try {
            con = new ConnectionClass().getConnetion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubmissionDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SubmissionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    public boolean submit(SubmissionBeans sb){
        try {
            String query = "insert into submission( userID1, path, submitted_on, assignment_id) values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, sb.getUserID1());
            ps.setString(2, sb.getPath());
            ps.setString(3, sb.getSubmitted_on());
            ps.setInt(4, sb.getAssignment_id());
            int n = ps.executeUpdate();
            return n!=0;
        } catch (SQLException ex) {
            Logger.getLogger(SubmissionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
