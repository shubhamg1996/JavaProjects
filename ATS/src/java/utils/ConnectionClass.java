/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1389026
 */
public class ConnectionClass {
    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/ats";
    public Connection getConnetion() throws ClassNotFoundException, SQLException {
        Connection con;

        Class.forName(driver);
        con = DriverManager.getConnection(url,"root","root");
        
        return con;
    }

}
