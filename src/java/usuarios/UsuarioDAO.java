/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 1233243
 */
public class UsuarioDAO {
    // TODO: control d'errors
    
    private static String dbURL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11227091";
    private static String dbUser = "sql11227091";
    private static String dbUserPassword = "Q1etReKkYK";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    
    private static void createConnection()
    {
        try
        {
            //Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            //Get a connection
            conn = DriverManager.getConnection(dbURL, dbUser, dbUserPassword); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
        
    public static void register(Usuario usuario){ 
        try
        {
            if (conn == null || conn.isClosed()){
               createConnection(); 
            }
            
            stmt = conn.createStatement();
            stmt.execute("insert into Usuario(usuario, password, nombre, apellidos, correo) values ('"  + usuario.getUser() + "','" + usuario.getPassword() + "','" + usuario.getNombre()+ "','" + usuario.getApellidos() + "','" + usuario.getCorreo()+"')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
     public static boolean login(Usuario usuario){ 
        boolean loginCorrecte = false;
        try
        {
            if (conn == null || conn.isClosed()){
               createConnection(); 
            }
            
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select count(*) as numRecords from Usuario where usuario = '" + usuario.getUser() + "' and password = '" + usuario.getPassword() + "'");
            if(results.next()){
                if(results.getObject("NUMRECORDS", Integer.class).equals(new Integer(1))){
                    loginCorrecte = true;
                }
            }

            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }

        return loginCorrecte;
    }   

    public static void disconnect(){
        try
        {
            if (!conn.isClosed()){
                   conn.close();
                }
            }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    
    
}
