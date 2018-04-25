/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videos;

import common.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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

            LocalDateTime duracionLocal = new Timestamp(video.getDuracion()).toLocalDateTime();
            stmt.setTime(4, new Time(duracionLocal.getHour(), duracionLocal.getMinute(), duracionLocal.getSecond()));
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
    
    private static List<Video> listFromResults(ResultSet resultados) throws SQLException{
        List<Video> videos = new ArrayList<Video>();
        Date today = new Date();
        today.setTime(0);
        Video video = null;
        
        while(resultados.next()){
            video = new Video(resultados.getObject("TITULO", String.class),
                            resultados.getObject("AUTOR", String.class),
                            resultados.getDate("FECHACREACION"),
                            today.getTime() + resultados.getTime("DURACION").getTime(),
                            resultados.getObject("REPRODUCCIONES", Integer.class),
                            resultados.getObject("DESCRIPCION", String.class),
                            resultados.getObject("FORMATO", String.class));

            videos.add(video);
        }    
        
        if(videos.isEmpty()){
            videos = null;
        }
        
        return videos;
    }

    public static List<Video> retrieve(Integer offset, Integer numRecords) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        // TODO : falta per aplicar l'offset i el numRecords 
        List<Video> videos = new ArrayList<Video>();
        
        try{
            String statement = "select titulo, autor, fechaCreacion, duracion, reproducciones, descripcion, formato from videos";
            init();
            stmt = connection.prepareStatement(statement);
            ResultSet results = stmt.executeQuery();

            videos = listFromResults(results);
            
            results.close();
            stmt.close();
        }
        finally{
            disconnect();
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
    
    public static List<Video> searchByTitle(String searchText) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        List<Video> videos = new ArrayList<Video>();
        
        try{
            String statement = "select titulo, autor, fechaCreacion, duracion, reproducciones, descripcion, formato from videos where titulo like '%" + searchText +"%'";
            init();
            stmt = connection.prepareStatement(statement);
            ResultSet results = stmt.executeQuery();

            videos = listFromResults(results);
            
            results.close();
            stmt.close();
        }
        finally{
            disconnect();
        }

        return videos;
    }
    public static List<Video> searchByAutor(String searchText) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        List<Video> videos = new ArrayList<Video>();
        
        try{
            String statement = "select titulo, autor, fechaCreacion, duracion, reproducciones, descripcion, formato from videos where autor like '%" + searchText +"%'";
            init();
            stmt = connection.prepareStatement(statement);
            ResultSet results = stmt.executeQuery();

            videos = listFromResults(results);
            
            results.close();
            stmt.close();
        }
        finally{
            disconnect();
        }
        
        return videos;
    }
    public static List<Video> searchByYear(int searchYear) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, Exception{
        List<Video> videos = new ArrayList<Video>();
        
        try{
            if(searchYear < 0) throw new Exception("EL número es negativo");
            
            String statement = "select titulo, autor, fechaCreacion, duracion, reproducciones, descripcion, formato from videos where year(fechaCreacion) = " + searchYear;
            init();
            stmt = connection.prepareStatement(statement);
            ResultSet results = stmt.executeQuery();

            videos = listFromResults(results);
            
            results.close();
            stmt.close();
        }
        finally{
            disconnect();
        }
        
        return videos;
    }
}
