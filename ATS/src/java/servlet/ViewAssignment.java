/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Beans_Assignment;
import dao.AssignmentDAo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import utils.ConnectionClass;


public class ViewAssignment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String taskid = request.getParameter("taskid");
        String userid = request.getParameter("userid");
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            AssignmentDAo ud = new AssignmentDAo();
            Connection con = new ConnectionClass().getConnetion();
            Beans_Assignment ub = new Beans_Assignment();
            JSONArray ob = null;
            if(taskid == null && userid==null)
                ob = ud.fetch(con, ub);
            else if(userid == null)
                ob = ud.getDefaulters(con, Integer.parseInt(taskid));
            else
                ob = ud.getSubmissions(con, Integer.parseInt(userid));
            out.print(ob);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewAssignment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ViewAssignment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
