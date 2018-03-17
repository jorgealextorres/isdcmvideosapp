<%-- 
    Document   : registroUsu
    Created on : 28-feb-2018, 16:59:38
    Author     : 1233243
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registrar usuarios</h1>
        <form action="servletUsuarios/register" method="post">
            <p>Nombre: <input type="text" name="nombre" /></p>
            <p>Apellidos: <input type="text" name="apellidos" /></p>
            <p>Correo: <input type="text" name="correo" /></p>
            <p>Nombre usuario: <input type="text" name="usuario" /></p>
            <p>Contraseña: <input type="text" name="password" /></p>
            <p>Repite contraseña: <input type="text" name="password2" /></p>
            <p><input type="submit" value="Registrar usuario" /></p>
        </form>
    </body>
</html>
