/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 1233243
 */
public class servletUsuarios extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login</title>");            
        out.println("</head>");
        out.println("<body>");
        
        Usuario usuario;
        
        try {
            usuario = new Usuario(request.getParameter("usuario"),
                                    request.getParameter("password"),
                                    null,
                                    null,
                                    null);
            
            if(UsuarioDAO.login(usuario)){
                out.println("<h1>El usuario ha sido logado correctamente</h1>");
                out.println("<p>Usuario: " + request.getParameter("usuario") + "</p>");
            } else{
                out.println("<h1>El usuario no se ha podido logar</h1>");
            }
        }
        catch(Exception e) {
            out.println("<h1>El usuario no se ha podido logar. Error: " + e.getMessage()+ "</h1>");
        }
        finally{
           UsuarioDAO.disconnect();
        }
        
        out.println("</body>");
        out.println("</html>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String test;
        test = request.getRequestURL().toString();
        if (request.getRequestURL().toString().endsWith("/register")) {
            processRegister(request, response);
        } else{
            processRequest(request, response);
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    protected void processRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Usuario registrado</title>");            
        out.println("</head>");
        out.println("<body>");
        
        Usuario usuario;
        
        try {
            usuario = new Usuario(request.getParameter("usuario"),
                                    request.getParameter("password"),
                                    request.getParameter("nombre"),
                                    request.getParameter("apellidos"),
                                    request.getParameter("correo"));
            
            UsuarioDAO.register(usuario);
            out.println("<h1>El usuario ha sido registrado</h1>");
            out.println("<p>Usuario: " + request.getParameter("usuario") + "</p>");
        }
        catch(Exception e) {
            out.println("<h1>El usuario no se ha podido registrar</h1>");
        }
        finally{
           UsuarioDAO.disconnect();
        }
        
        out.println("</body>");
        out.println("</html>");
    }
}
