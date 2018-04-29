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

import org.h2.jdbcx.JdbcDataSource;

/**
 *
 * @author jorgetorres
 */
public class DatabaseConnection {
    private static JdbcDataSource ds = null;
            
    private static final String dbDriver = "org.h2.Driver";
    private static final String dbURL = "jdbc:h2:~/videosDb";
    private static final String dbUser = "userdb";
    private static final String dbUserPassword = "123456";
    // jdbc Connection
    private static Connection conn = null;
    
    private static void createConnection() throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException
    {                
        Class.forName(dbDriver);
        conn = DriverManager.getConnection(dbURL, dbUser,dbUserPassword);
        
        conn.setAutoCommit(false);

        initDatabase();
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
    
    public static void initDatabase() throws SQLException{
                
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS Usuario (\n" +
                        "  id bigint auto_increment primary key,\n" +
                        "  usuario varchar(35) NOT NULL,\n" +
                        "  password varchar(35) NOT NULL,\n" +
                        "  nombre varchar(35) NOT NULL,\n" +
                        "  apellidos varchar(70) NOT NULL,\n" +
                        "  correo varchar(150) NOT NULL\n" +
                        ")");
        
        stmt.execute("alter table if exists Usuario ADD CONSTRAINT IF NOT EXISTS usuario_UNIQUE UNIQUE(usuario)");
        stmt.execute("alter table if exists Usuario ADD CONSTRAINT IF NOT EXISTS correo_UNIQUE UNIQUE(correo)");
        
        
        stmt.execute("CREATE TABLE IF NOT EXISTS videos (\n" +
                        "  id bigint auto_increment primary key,\n" +
                        "  titulo varchar(100) NOT NULL,\n" +
                        "  autor varchar(100) NOT NULL,\n" +
                        "  fechaCreacion datetime NOT NULL,\n" +
                        "  duracion time NOT NULL,\n" +
                        "  reproducciones int(11) NOT NULL,\n" +
                        "  descripcion varchar(255) NOT NULL,\n" +
                        "  formato varchar(5) NOT NULL,\n" +
                        "  url varchar(2083) NOT NULL\n" +
                        ");");
        //stmt.execute("alter table if exists videos ADD CONSTRAINT IF NOT EXISTS titulo_UNIQUE UNIQUE(titulo)");
        
        stmt.close();
        conn.commit();
    }
}
