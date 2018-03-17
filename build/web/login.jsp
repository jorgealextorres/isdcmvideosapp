<%-- 
    Document   : login
    Created on : 28-feb-2018, 17:00:48
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
        <h1>Login</h1>
        <form action="servletUsuarios/login" method="post">
            <p>Nombre usuario: <input type="text" name="usuario" /></p>
            <p>Contraseña: <input type="text" name="password" /></p>
            <p><input type="submit" value="login" /></p>
        </form>
    </body>
</html>
