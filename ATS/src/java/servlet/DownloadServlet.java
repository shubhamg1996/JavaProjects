/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
public class DownloadServlet extends HttpServlet {

    

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filepath = request.getParameter("filepath");
        
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/octate-stream");
        response.setHeader("Content-Disposition", "attachment; filename="+filepath.substring(filepath.lastIndexOf('\\')));
        File f = new File(filepath);
        InputStream is = new FileInputStream(f);
        byte[] b = new byte[1024*2];
        int n=0;
        while((n=is.read(b, 0, b.length)) > 0){
            out.write(b);
        }
    }
}
