package dao;
import beans.UserBeans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
/**
 *
 * @author 1389026
 */
public class UserDetails {

    public boolean save(Connection con, UserBeans ub) {
        try {
            String query = "insert into user_master(name,email,password,role) values(?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, ub.getUsername());
            stmt.setString(2, ub.getEmail());
            stmt.setString(3, ub.getPassword());
            stmt.setString(4, "1");
            int n = stmt.executeUpdate();
            return n != 0;

        } catch (SQLException ex) {
            Logger.getLogger(UserDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public JSONObject fetch(Connection con, UserBeans ub) {
        try {
            ResultSet rs = null;
            String query = "select * from user_master where name=? and password=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, ub.getUsername());
            stmt.setString(2, ub.getPassword());
            rs = stmt.executeQuery();
            if (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("name", rs.getString("name"));
                obj.put("role", rs.getString("role"));
                obj.put("user_id", rs.getString("user_id1"));
//                System.out.println(rs.getString("name")+rs.getString("role"));
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new JSONObject();
    }
}
