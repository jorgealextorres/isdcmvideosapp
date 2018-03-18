<%-- 
    Document   : registroVid
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
        <h1>Registrar videos</h1>
        <form action="servletRegistroVid/register" method="post">
            <p>Título: <input type="text" name="titulo" /></p>
            <p>Autor: <input type="text" name="autor" /></p>
            <p>Fecha de creación: <input type="date" name="fechaCreacion" /></p>
            <p>Duración: <input type="time" name="duracion" /></p>
            <p>Reproducciones: <input type="text" name="reproducciones" /></p>
            <p>Descripción: <input type="text" name="descripcion" /></p>
            <p>Formato: <input type="text" name="formato" /></p>
            <p><input type="submit" value="Registrar video" /></p>
        </form>
    </body>
</html>
