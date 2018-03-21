<%-- 
    Document   : login
    Created on : 28-feb-2018, 17:00:48
    Author     : 1233243
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <%
            if ((request.getSession(false) != null) && (request.getSession(false).getAttribute("userName")) != null) {
        %>
              <meta http-equiv="refresh" content="0; url=servletRegistroVid/listado">  
        <%       
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
<div class="container">
	<div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Welcome !!!</h3>
                </div>
                <div class="panel-body">
                    <form role="form" action="servletUsuarios/login" method="post">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="Usuario" name="usuario" type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Password" name="password" type="password" value="">
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <button type="submit" class="btn btn-success btn-block">Login</button>
                            <p>No te has registrado aún? <a href="registroUsu.jsp">Registrarse</a></p>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</html>
