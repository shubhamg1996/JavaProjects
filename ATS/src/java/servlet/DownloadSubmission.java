/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 1389026
 */
public class DownloadSubmission extends HttpServlet {

    private final String DIR = "Submissions";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletContext().getRealPath("/");
        String file = servletPath + "../../" + DIR ;
        String filepath = request.getParameter("filepath");
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/octate-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + filepath);
//        System.out.println(file+ File.separator+filepath);
        File f = new File(file+ File.separator+filepath);
        InputStream is = new FileInputStream(f);
        byte[] b = new byte[1024 * 2];
        int n = 0;
        while ((n = is.read(b, 0, b.length)) > 0) {
            out.write(b);
        }
    }
}
