<%-- 
    Document   : registroVid
    Created on : 28-feb-2018, 16:59:38
    Author     : 1233243
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de videos</title>
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
        
        <div class="container content">
	<div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Registro de video</h3>
                </div>
                <div class="panel-body">
                    <form role="form" action="servletRegistroVid/register" method="post">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="Título" name="titulo" type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Autor" name="autor" type="text" value="">
                            </div>
       
                            <div class="form-group">
                                <input class="form-control" placeholder="Fecha de creación" name="fechaCreacion" type="date" value="">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Duración" name="duracion" type="time" value="">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Reproducciones" name="reproducciones" type="text" value="">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Descripción" name="descripcion" type="text" value="">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Formato" name="formato" type="text" value="">
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <button type="submit" class="btn btn-success btn-block">Registrar video</button>
                            
                            <p><a href="listadoVid.jsp">Cancelar</a></p>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
    </body>
</html>
