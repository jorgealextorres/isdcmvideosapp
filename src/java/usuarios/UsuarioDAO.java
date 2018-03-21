package usuarios;

import common.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {
    private static Connection connection;
    private static Statement stmt = null;
    
    private static void init() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        connection = DatabaseConnection.getConnection();
    }
    
    private static void disconnect() throws SQLException
    {
        DatabaseConnection.disconnect();
    }
       
    public static void register(Usuario usuario) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{ 
        init();
        stmt = connection.createStatement();
        stmt.execute("insert into Usuario(usuario, password, nombre, apellidos, correo) values ('"  + usuario.getUser() + "','" + usuario.getPassword() + "','" + usuario.getNombre()+ "','" + usuario.getApellidos() + "','" + usuario.getCorreo()+"')");
        stmt.close();
        disconnect();
    }
    
    public static boolean isLoginCorrect(String usuario, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{ 
        boolean correct = false;
        init();
        stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery("select count(*) as numRecords from Usuario where usuario = '" + usuario + "' and password = '" + password + "'");
        
        if(results.next()){
            if(results.getObject("NUMRECORDS", Integer.class).equals(new Integer(1))){
                correct = true;
            }
        }

        results.close();
        stmt.close();
        disconnect();
        return correct;
    }
    
    public static Usuario getUsuario(String userName) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {     
        init();
        stmt = connection.createStatement();
        
        ResultSet results = stmt.executeQuery("select * from Usuario where usuario = '" + userName + "';");
        
        Usuario usuario = null;
        
        if (results.next())
        {
            usuario = new Usuario
            (
                results.getString("usuario"),
                results.getString("password"),
                results.getString("nombre"),
                results.getString("apellidos"),
                results.getString("correo")
            );
        }
        
        results.close();
        stmt.close();
        disconnect();
                
        return usuario;
    } 
}