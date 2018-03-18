/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jorgetorres
 */
public class DatabaseConnection {
    private static String dbURL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11227091";
    private static String dbUser = "sql11227091";
    private static String dbUserPassword = "Q1etReKkYK";
    // jdbc Connection
    private static Connection conn = null;
    
    private static void createConnection() throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException
    {
        //Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        Class.forName("com.mysql.jdbc.Driver").newInstance(); 
        //Get a connection
        conn = DriverManager.getConnection(dbURL, dbUser, dbUserPassword); 
    }
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        if (conn == null || conn.isClosed()){
               createConnection(); 
        }
        
        return conn;
    }
    
    public static void disconnect() throws SQLException{
        if(conn != null && !conn.isClosed()){
            conn.close();
        }
    }
}
