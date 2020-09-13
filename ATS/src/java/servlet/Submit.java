/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.SubmissionBeans;
import dao.SubmissionDao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
@MultipartConfig
/**
 *
 * @author 1389026
 */
public class Submit extends HttpServlet {

    private final String DIR = "Submissions";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String servletPath = request.getServletContext().getRealPath("/");
        HttpSession session = request.getSession();

        Part part = request.getPart("file");
        String filename = getFileName(part);

        int user_id = Integer.parseInt((String) session.getAttribute("userId"));
        int ass_id = Integer.parseInt(request.getParameter("assid"));
        String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

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
            
            filename = file.getName();
            
            SubmissionBeans bean = new SubmissionBeans();
            bean.setAssignment_id(ass_id);
            bean.setUserID1(user_id);
            bean.setSubmitted_on(now);
            bean.setPath(filename);
            
            if(new SubmissionDao().submit(bean))
                response.sendRedirect("users.jsp");
        } catch (Exception ex) {
            System.out.println(ex);
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
