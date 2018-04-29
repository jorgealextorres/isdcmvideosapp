<%-- 
    Document   : listadpVid
    Created on : 28-feb-2018, 16:59:38
    Author     : 1233243
--%>


<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="common.DatabaseConnection"%>
<%@page import="videos.Video"%>
<%@page import="java.util.List"%>
<%@page import="videos.VideoDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="css/jplayer.blue.monday.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/main.css" type="text/css" media="all" />
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/jquery.jplayer.min.js" type="text/javascript"></script>
     
        
        <%

                String titulo = request.getParameter("titulo");
                Video video = null;
                String url = "";
                try 
                {               
                    video = VideoDAO.retrieveVideoWithUrl(titulo);
                    url = video.getUrl();
                }
                catch(Exception e) {
                    //do nothing
                }
                finally {
                    DatabaseConnection.disconnect();
                }
        %>
        
        
        <script type="text/javascript">

        $(document).ready(function(){

           $("#jquery_jplayer").jPlayer({
               ready: function () {
                   $(this).jPlayer("setMedia", {
                       m4v: "<%=url %>",
                       ogv: "<%=url %>"
                   });
               },
               ended: function (event) {
                   $("#jquery_jplayer").jPlayer("play", 0);
               },
               swfPath: "swf",
               supplied: "m4v, ogv",
               cssSelectorAncestor: "#jp_interface"
           })
           .bind($.jPlayer.event.play, function() { // pause other instances of player when current one play
                   $(this).jPlayer("pauseOthers");
           });

       });

      </script>
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
        

        <div class="content" style="text-align:center;">
        <h1> Reproducir video</h1>
        <br>    
        <%
            if(request.getParameter("titulo") == null || video == null || video.getUrl().isEmpty())
            {
                %>
                <h2>Video no encontrado</h2>
                <%
            }
            else
            {
                SimpleDateFormat creationFormat = new SimpleDateFormat("MM/dd/yyyy");
                SimpleDateFormat durationFormat = new SimpleDateFormat("HH:mm:ss");
                %>
                <p>
                    <span>Titulo: <%=video.getTitulo()%></span><br>
                    <span>Autor: <%=video.getAutor()%></td><br>
                    <span>Fecha creación: <%=creationFormat.format((video.getFechaCreacion()).getTime())%></span><br>
                    <span>Número de reproducciones: <%=video.getReproducciones()%></span><br>
                </p>
                     <div class="players">
            <h2>Video player</h2>

            <div class="jp-video jp-video-270p" style="display:inline-block">
                <div class="jp-type-single">
                    <div id="jquery_jplayer" class="jp-jplayer"></div>
                    <div id="jp_interface" class="jp-interface">
                        <div class="jp-video-play"></div>
                        <ul class="jp-controls">
                            <li><a href="#" class="jp-play" tabindex="1">play</a></li>
                            <li><a href="#" class="jp-pause" tabindex="1">pause</a></li>
                            <li><a href="#" class="jp-stop" tabindex="1">stop</a></li>
                            <li><a href="#" class="jp-mute" tabindex="1">mute</a></li>
                            <li><a href="#" class="jp-unmute" tabindex="1">unmute</a></li>
                        </ul>
                        <div class="jp-progress">
                            <div class="jp-seek-bar">
                                <div class="jp-play-bar"></div>
                            </div>
                        </div>
                        <div class="jp-volume-bar">
                            <div class="jp-volume-bar-value"></div>
                        </div>
                        <div class="jp-current-time"style="text-align: left"></div>
                        <div class="jp-duration"></div>
                    </div>
                    <div id="jp_playlist" class="jp-playlist">
                        <ul>
                            <li><%=video.getTitulo()%></li>
                        </ul>
                    </div>
                </div>
            </div>

        </div>

                </p>
                <%
            }
        %>
        <br>
        <div>
            <a href="/isdcmvideosapp/listadoVid.jsp" class="btn btn-success" role="button">Volver al listado de videos</a>
        </div>
    </body>
</html>
