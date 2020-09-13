/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.UserBeans;
import dao.UserDetails;
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
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import utils.ConnectionClass;

/**
 *
 * @author 1389026
 */
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.setHeader("Cache-control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);

        } else {
            try {

                String username = request.getParameter("username");
                String password = request.getParameter("password");
                PrintWriter out = response.getWriter();
                UserBeans ub = new UserBeans();
                ub.setPassword(password);
                ub.setUsername(username);
                Connection con = new ConnectionClass().getConnetion();
                UserDetails ud = new UserDetails();
                JSONObject res = ud.fetch(con, ub);
                String str1 = (String) res.get("name");
                String str2 = (String) res.get("role");
                HttpSession session = request.getSession();
                session.setAttribute("name", username);
                session.setAttribute("role", str2);
                session.setAttribute("userId", res.get("user_id"));

                if (str2.equalsIgnoreCase("1")) {
                    out.print("user");
                } else if (str2.equals("2")) {
                    out.print("admin");
                } else {
                    out.print("none");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.setHeader("Cache-control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
        }
    }
}
