/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author zilidazn
 */
public class JDBCConnection {

    public static Connection getConnection() {
        String jdbcURL = "jdbc:mysql://localhost:3306/CRUD?useSSL=false";
        String jdbcUsername = "zilidazn";
        String jdbcPassword = "290899";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
        }
        return connection;
    }
}
