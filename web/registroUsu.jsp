<%-- 
    Document   : registroUsu
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
        <title>Registro de usuarios</title>
    </head>
    <body>
        
        <div class="container">
	<div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Registro de usuario</h3>
                </div>
                <div class="panel-body">
                    <form role="form" action="servletUsuarios/register" method="post">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="Nombre" name="nombre" type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Apellidos" name="apellidos" type="text" value="">
                            </div>
       
                            <div class="form-group">
                                <input class="form-control" placeholder="Correo electrónico" name="correo" type="text" value="">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Usuario" name="usuario" type="text" value="">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Contraseña" name="password" type="text" value="">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Repite contraseña" name="password2" type="text" value="">
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <button type="submit" class="btn btn-success btn-block">Registrar usuario</button>
                            <p><a href="login.jsp">Volver</a></p>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
    </body>
</html>
