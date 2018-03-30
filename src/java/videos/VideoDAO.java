/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videos;

import common.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoDAO {
    private static Connection connection;
    private static PreparedStatement stmt = null;
    
    private static void init() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        connection = DatabaseConnection.getConnection();
    }
    
    private static void disconnect() throws SQLException
    {
        DatabaseConnection.disconnect();
    }
        
    public static void register(Video video) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{ 
        try{
            init();
            String sentence = "insert into videos(titulo, autor, fechaCreacion, duracion, reproducciones, descripcion, formato) " +
                        "values (?,?,?,?,?,?,?)";
            stmt = connection.prepareStatement(sentence);
            stmt.setString(1, video.getTitulo());
            stmt.setString(2, video.getAutor());
            stmt.setDate(3, new java.sql.Date(video.getFechaCreacion().getTime()));
            stmt.setTime(4, video.getDuracion());
            stmt.setInt(5, video.getReproducciones());
            stmt.setString(6, video.getDescripcion());
            stmt.setString(7, video.getFormato());
            stmt.executeUpdate();
            stmt.close();
            connection.commit();
        }
        finally{
            disconnect();
        }
    }  
    
    public static List<Video> retrieve(Integer offset, Integer numRecords) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        // TODO : falta per aplicar l'offset i el numRecords 
        List<Video> videos = new ArrayList<Video>();
        
        try{
            Video video = null;
            String statement = "select titulo, autor, fechaCreacion, duracion, reproducciones, descripcion, formato from videos";
            init();
            stmt = connection.prepareStatement(statement);
            ResultSet results = stmt.executeQuery();

            while(results.next()){
                video = new Video(results.getObject("TITULO", String.class),
                                results.getObject("AUTOR", String.class),
                                results.getDate("FECHACREACION"),
                                results.getTime("DURACION"),
                                results.getObject("REPRODUCCIONES", Integer.class),
                                results.getObject("DESCRIPCION", String.class),
                                results.getObject("FORMATO", String.class));

                videos.add(video);
            }

            results.close();
            stmt.close();
        }
        finally{
            disconnect();
        }
        
        if(videos.isEmpty()){
            videos = null;
        }
        
        return videos;
    }
    
    public static boolean videoExists(String titulo) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{

        String statement = "select titulo from videos where titulo = '" + titulo + "'";
        boolean exists = false;
        
        try{
            init();
            stmt = connection.prepareStatement(statement);
            ResultSet results = stmt.executeQuery();

            if(results.next()){
                exists = true;
            }

            results.close();
            stmt.close();
        }
        finally{
            disconnect();
        }
        
        return exists;
    }
}
