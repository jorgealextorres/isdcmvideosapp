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
        <script>
            $(document).ready(function() {
                $("#errores").hide();
            });
            
            function checkFields() {
                var newLine = '\n';
                var errorMessage = '';
                var titulo=$('#titulo').val();
                var autor=$('#autor').val();
                var fechaCreacion=$('#fechaCreacion').val();
                var duracion=$('#duracion').val();
                var reproducciones=$('#reproducciones').val();
                var descripcion=$('#descripcion').val();
                var formato=$('#formato').val();
                var re = new RegExp("[0-9]$");

                if(titulo.length < 1){
                    errorMessage += 'El campo titulo está vacío.';
                }
                if(errorMessage.length > 0){
                    errorMessage += newLine;  //TODO: Does not work
                }
                if(autor.length < 1){
                    errorMessage += 'El campo autor está vacío.';
                }
                if(errorMessage.length > 0){
                    errorMessage += newLine;  //TODO: Does not work
                }
                if(fechaCreacion.length < 1){
                    errorMessage += 'El fecha de creación está vacío.';
                }
                if(errorMessage.length > 0){
                    errorMessage += newLine;  //TODO: Does not work
                }
                if(duracion.length < 1){
                    errorMessage += 'El duración está vacío.';
                }
                if(errorMessage.length > 0){
                    errorMessage += newLine;  //TODO: Does not work
                }
                if(reproducciones.length < 1){
                    errorMessage += 'El reproducciones está vacío.';
                }
                if(errorMessage.length > 0){
                    errorMessage += newLine;  //TODO: Does not work
                }
                if(descripcion.length < 1){
                    errorMessage += 'El descripción está vacío.';
                }
                if(errorMessage.length > 0){
                    errorMessage += newLine;  //TODO: Does not work
                }
                if(formato.length < 1){
                    errorMessage += 'El formato está vacío.';
                }
                if(errorMessage.length > 0){
                    errorMessage += newLine;  //TODO: Does not work
                }               
                if (!re.test(reproducciones)) {
                    errorMessage += 'El campo reproducciones no tiene el formato correcto.';
                }
                
                if(errorMessage.length < 1){
                    document.getElementById("formRegistraVideo").submit();
                } else{
                    $('#errorText').val(errorMessage);
                    $("#errores").show();
                }
            }
        </script> 

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
                    <form role="form" action="servletRegistroVid/register" method="post" id="formRegistraVideo">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="Título" id="titulo" name="titulo" type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Autor" id="autor" name="autor" type="text" value="">
                            </div>
       
                            <div class="form-group">
                                <input class="form-control" placeholder="Fecha de creación" id="fechaCreacion" name="fechaCreacion" type="date" value="">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Duración" id="duracion" name="duracion" type="time" value="">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Reproducciones" id="reproducciones" name="reproducciones" type="text" value="" pattern="[0-9]" title="Only numbers">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Descripción" id="descripcion" name="descripcion" type="text" value="">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Formato" id="formato" name="formato" type="text" value="">
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <button type="button" class="btn btn-success btn-block" onclick="checkFields()">Registrar video</button>
                            
                            <p><a href="listadoVid.jsp">Cancelar</a></p>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
        
        <div id="errores">
            <input id="errorText" type="text" value="menudo error" style='color:red;display:block;width:100%;border:0px;'>
        </div>
    </body>
</html>
