/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnectionClass;

/**
 *
 * @author 1389026
 */
public class GetSubmissionPaths {
   private Connection con = null; ;
    public GetSubmissionPaths(){
       try {
           con = new ConnectionClass().getConnetion();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(GetSubmissionPaths.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(GetSubmissionPaths.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    public String[] getPaths(int assid){
       try {
           String query = "select path from submission where assignment_id=?";
           PreparedStatement ps = con.prepareStatement(query);
           ps.setInt(1, assid);
           ResultSet rs = ps.executeQuery();
           rs.last();
           String [] sa = new String[rs.getRow()];
           rs.beforeFirst();
           int i=0;
           while(rs.next()){
               sa[i++] = rs.getString("path");
           }
           return sa;
       } catch (SQLException ex) {
           Logger.getLogger(GetSubmissionPaths.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
    }
}
