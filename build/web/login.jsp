<%-- 
    Document   : login
    Created on : 28-feb-2018, 17:00:48
    Author     : 1233243
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
        <h1>Login</h1>
        <form action="servletUsuarios/login" method="post">
            <p>Nombre usuario: <input type="text" name="usuario" /></p>
            <p>Contraseña: <input type="text" name="password" /></p>
            <p><input type="submit" value="login" /></p>
        </form>
        <p>No te has registrado aún? <a href="registroUsu.jsp">Registrarse</a></p>
    </body>
</html>
