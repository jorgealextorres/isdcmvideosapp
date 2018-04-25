<%-- 
    Document   : listadpVid
    Created on : 28-feb-2018, 16:59:38
    Author     : 1233243
--%>

<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="common.DatabaseConnection"%>
<%@page import="videos.Video"%>
<%@page import="java.util.List"%>
<%@page import="videos.VideoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <style>
        #videos {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #videos td, #videos th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #videos tr:nth-child(even){background-color: #f2f2f2;}

        #videos tr:hover {background-color: #ddd;}

        #videos th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
        }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (request.getSession(false) != null && request.getSession(false).getAttribute("userName") != null) {
                String userName = request.getSession(false).getAttribute("userName").toString();
        %>
                <form action="servletUsuarios/logout" method="post">
                    <div>
                        <p align="right">
                            Welcome, ${userName} ! 
                            <button type="submit" class="btn btn-success">Log out</button>
                        </p>
                    </div>
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
        

        <div class="content">
        <h1>Listado videos</h1>
        <table id="videos">
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
                    <tr><td>No hay registros</td></tr>
                    <%
                } else{
                    for(Video element:videos){
                        SimpleDateFormat creationFormat = new SimpleDateFormat("MM/dd/yyyy");
                        SimpleDateFormat durationFormat = new SimpleDateFormat("HH:mm:ss");
                    %>
                        <tr>
                        <td><%=element.getTitulo()%></td>
                        <td><%=element.getAutor()%></td>
                        <td><%=creationFormat.format((element.getFechaCreacion()).getTime())%></td>
                        <td><%=durationFormat.format(new Timestamp(element.getDuracion()))%></td>
                        <td><%=element.getReproducciones()%></td>
                        <td><%=element.getFormato()%></td>
                        </tr>
                        <%
                    }
                }
            } catch(Exception e){%>
                <tr><td>Error: <%=e.getMessage()%></td></tr>
                <%
            }
            DatabaseConnection.disconnect();
        %>
        </table>  
        <div>
            <form action="servletRegistroVid/showForm" method="post">
                <button type="submit" class="btn btn-success">Registrar video</button>
            </form>
            <form action="busquedaVideoServlet/showForm" method="post">
                <button type="submit" class="btn btn-success">Buscar video</button>
            </form>
        </div>
        </div>
    </body>
</html>
