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
        <script>
            $(document).ready(function() {
                $("#errores").hide();
            });
            
            function checkFields() {
                var errorMessage = '';
                var user=$('#usuario').val();
                var password=$('#password').val();
                var password2=$('#password2').val();
                var correo=$('#correo').val();
                var re = new RegExp("[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$");

                if(user.length < 1){
                    errorMessage += 'El campo usuario está vacío.<br>';
                }
                if(password.length < 1){
                    errorMessage += 'El campo contraseña está vacío.<br>';
                }
                if(correo.length < 1){
                    errorMessage += 'El correo electrónico está vacío.<br>';
                }
                if(password != password2){
                    errorMessage += 'El password y su confirmación no son iguales.<br>';
                }            
                if (!re.test(correo)) {
                    errorMessage += 'El correo electrónico no tiene el formato correcto.<br>';
                }
                
                if(errorMessage.length < 1){
                    document.getElementById("formRegistroUsuario").submit();
                } else{
                    $('#errorText').html(errorMessage);
                    $("#errores").show();
                }
            }
        </script> 

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
                    <form role="form" action="servletUsuarios/register" method="post" id="formRegistroUsuario">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="Nombre" name="nombre" id ="nombre" type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Apellidos" name="apellidos" id="apellidos" type="text" value="">
                            </div>
       
                            <div class="form-group">
                                <input class="form-control" placeholder="Correo electrónico" name="correo" id="correo" type="text" value="" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Usuario" name="usuario" id="usuario" type="text" value="">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Contraseña" name="password" id="password" type="password" value="" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Repite contraseña" name="password2" id="password2" type="password" value="" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters">
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <button type="button" class="btn btn-success btn-block" onclick="checkFields()">Registrar usuario</button>
                            <p><a href="login.jsp">Volver</a></p>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
        
        <div id="errores">
            <p id="errorText" type="text" value="menudo error" style='color:red;display:block;width:100%;border:0px;text-align: center'>
        </div>
    </body>
</html>
