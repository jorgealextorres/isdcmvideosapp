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
        <%
            if (request.getSession(false) != null && request.getSession(false).getAttribute("userName") != null) {
                String userName = request.getSession(false).getAttribute("userName").toString();
        %>
                <form action="servletUsuarios/logout" method="post">
                    <p>Welcome, ${userName}! <input type="submit" value="Log out" /></p>
                </form>
        <%       
            }
            else 
            {
                request.setAttribute("title", "Error");
                request.setAttribute("redirect", "login.jsp");
                request.setAttribute("message", "403 Forbidden");
                response.setStatus(403);
                request.getRequestDispatcher("/message.jsp").forward(request, response);
            }
        %>
        

        
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
        
        <form action="servletRegistroVid/showForm" method="post">
            <p><input type="submit" value="Registrar video" /></p>
        </form>
    </body>
</html>
