/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Beans_Assignment;
import dao.AssignmentDAo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import utils.ConnectionClass;

@MultipartConfig
/**
 *
 * @author 1389026
 */
public class Upload extends HttpServlet {

    private final String DIR = "Uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String servletPath = request.getServletContext().getRealPath("/");

        Part part = request.getPart("file");
        HttpSession session = request.getSession();
        
        String filename = getFileName(part);
        String title = request.getParameter("title");
        String desc = request.getParameter("description");
        String date = request.getParameter("deadline");
        String ass_id = request.getParameter("action");
        String curr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        if (!filename.equals("")) {
            File directory = new File(servletPath + "../../" + DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            
            try {
                File file = new File(servletPath + "../../" + DIR + File.separator + filename);
                OutputStream os = new FileOutputStream(file);
                InputStream is = part.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[2048];

                while ((read = is.read(bytes)) != -1) {
                    os.write(bytes, 0, read);
                }
                
                filename = file.getAbsolutePath();

            } catch (Exception ex) {
                System.out.println(ex);
                out.write(ex.getMessage());
            }
        }
        
        Beans_Assignment bean = new Beans_Assignment();
        
        bean.setId(Integer.parseInt((String)session.getAttribute("userId")));
        bean.setTitle(title);
        bean.setDescription(desc);
        bean.setDeadline(date);
        bean.setFilepath(filename);
        bean.setCreated(curr);
        if(!ass_id.equals("create"))
            bean.setAss_id(Integer.parseInt(ass_id));
        
        boolean status = false;
        try {
            if(bean.getAss_id() > 0)
                status = new AssignmentDAo().update(new ConnectionClass().getConnetion(), bean);
            else
                status = new AssignmentDAo().save(new ConnectionClass().getConnetion(), bean);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(status)
        {
            response.sendRedirect("admin.jsp");
        }
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
