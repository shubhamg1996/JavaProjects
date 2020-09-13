/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Beans_Assignment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AssignmentDAo {

    public boolean save(Connection con, Beans_Assignment ub) {
        try {
            String query = "insert into assignment_master(assignment,created_on, deadline, description, user_id1, filename) values(?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, ub.getTitle());
            stmt.setString(2, ub.getCreated());
            stmt.setString(3, ub.getDeadline());
            stmt.setString(4, ub.getDescription());
            stmt.setInt(5, ub.getId());
            stmt.setString(6, ub.getFilepath());

            int n = stmt.executeUpdate();
            return n != 0;

        } catch (SQLException ex) {
            Logger.getLogger(UserDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public JSONArray fetch(Connection con, Beans_Assignment ub) {
        JSONArray ja = null;
        try {
            String query = "select * from assignment_master";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            JSONObject js = new JSONObject();
            ja = new JSONArray();
            System.out.println(ps);
            JSONObject obj;
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("title", rs.getString("assignment"));
                obj.put("description", rs.getString("description"));
                obj.put("created_on", rs.getString("created_on"));
                obj.put("deadline", rs.getString("deadline"));
                obj.put("filename", rs.getString("filename"));
                obj.put("userID", rs.getString("user_id1"));
                obj.put("deadline", rs.getString("deadline"));
                obj.put("assignment_id", rs.getString("ass_id"));
                ja.add(obj);
            }
//            System.out.println(ja);
//            js.put("data", ja);
            return ja;

        } catch (SQLException ex) {
            Logger.getLogger(UserDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ja;
    }

    public boolean update(Connection con, Beans_Assignment ub) {
        try {
            String file = ub.getFilepath();
            String query = null;
            if (file == null || file.equals("")) {
                query = "update assignment_master set assignment=?, created_on=?, deadline=?, description=? where ass_id=? ";
            } else {
                query = "update assignment_master set assignment=?, created_on=?, deadline=?, description=?, filename=? where ass_id=? ";
            }

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, ub.getTitle());
            stmt.setString(2, ub.getCreated());
            stmt.setString(3, ub.getDeadline());
            stmt.setString(4, ub.getDescription());
            if (file == null || file.equals("")) {
//                stmt.setString(5, ub.getFilepath());
                stmt.setInt(5, ub.getAss_id());
            } else {
                stmt.setString(5, ub.getFilepath());
                stmt.setInt(6, ub.getAss_id());
            }
            return stmt.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDAo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public JSONArray getDefaulters(Connection con, int taskId) {
        try {
            String query = "SELECT name, email from user_master where role!=2 and user_id1 not in "
                    + "(select userID1 from submission where assignment_id=?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, taskId);

            ResultSet rs = ps.executeQuery();;
            JSONArray ja = new JSONArray();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("name", rs.getString("name"));
                obj.put("email", rs.getString("email"));
                ja.add(obj);
            }

            return ja;

        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDAo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public JSONArray getSubmissions(Connection con, int userid) {
        try {
            String query = "select assignment_id,path from submission where userID1=?;";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userid);

            ResultSet rs = ps.executeQuery();;
            JSONArray ja = new JSONArray();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("id", rs.getString("assignment_id"));
                obj.put("file", rs.getString("path"));
                obj.put("action", "<button id='down'><span class=\"glyphicon glyphicon-arrow-down\"/></button>");
                ja.add(obj);
            }

            return ja;

        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDAo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
