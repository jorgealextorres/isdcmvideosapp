package usuarios;

import common.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {
    private static Connection connection;
    private static Statement stmt = null;
    
    private static void init() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        connection = DatabaseConnection.getConnection();
    }
       
    public static void register(Usuario usuario) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{ 
        init();
        stmt = connection.createStatement();
        stmt.execute("insert into Usuario(usuario, password, nombre, apellidos, correo) values ('"  + usuario.getUser() + "','" + usuario.getPassword() + "','" + usuario.getNombre()+ "','" + usuario.getApellidos() + "','" + usuario.getCorreo()+"')");
        stmt.close();
    }
    
     public static boolean login(Usuario usuario) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{ 
        boolean loginCorrecte = false;
        init();
        stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery("select count(*) as numRecords from Usuario where usuario = '" + usuario.getUser() + "' and password = '" + usuario.getPassword() + "'");
        
        if(results.next()){
            if(results.getObject("NUMRECORDS", Integer.class).equals(new Integer(1))){
                loginCorrecte = true;
            }
        }

        results.close();
        stmt.close();
        return loginCorrecte;
    }    
}