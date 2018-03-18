<%-- 
    Document   : listadpVid
    Created on : 28-feb-2018, 16:59:38
    Author     : 1233243
--%>

<%@page import="common.DatabaseConnection"%>
<%@page import="videos.Video"%>
<%@page import="java.util.List"%>
<%@page import="videos.VideoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado videos</h1>
        <table border=1 cellpadding=5>
          <tr>
            <th>Título</th>
            <th>Autor</th>
            <th>Fecha Creacion</th>
            <th>Duración</th>
            <th>Reproducciones</th>
            <th>Formato</th>
          </tr>
          
        <%
            try{
                List<Video> videos = VideoDAO.retrieve(0, 0);
                
                if(videos == null || videos.isEmpty()){%>
                    <tr><th>No hay registros</th></tr>
                    <%
                } else{
                    for(Video element:videos){%>
                        <tr>
                        <th><%=element.getTitulo()%></th>
                        <th><%=element.getAutor()%></th>
                        <th><%=element.getFechaCreacion()%></th>
                        <th><%=element.getDuracion()%></th>
                        <th><%=element.getReproducciones()%></th>
                        <th><%=element.getFormato()%></th>
                        </tr>
                        <%
                    }
                }
            } catch(Exception e){%>
                <tr><th>Error: <%=e.getMessage()%></th></tr>
                <%
            }
            DatabaseConnection.disconnect();
        %>
        </table>  
    </body>
</html>
