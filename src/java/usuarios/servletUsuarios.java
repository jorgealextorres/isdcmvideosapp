package usuarios;

import common.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
            throws ServletException, IOException
    {
        request.setAttribute("title", "Error");
        request.setAttribute("redirect", "");
        request.setAttribute("message", "404 Not found");
        response.setStatus(404);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
    
    protected void processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {     
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("title", "Login");
        
        try
        {
            HttpSession session = request.getSession(false);
            
            if (session != null && session.getAttribute("userName") != null)
            {
                throw new Exception("El usuario ya ha hecho login anteriormente");
            }
            
            String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");
            
            if (isNullEmptyOrWhiteSpace(usuario)) throw new Exception("Usuario no definido");
            if (isNullEmptyOrWhiteSpace(password)) throw new Exception("Password no definido");
            
            boolean correctLogin = UsuarioDAO.isLoginCorrect(request.getParameter("usuario"), request.getParameter("password"));
            
            if (correctLogin) 
            {
                session.setAttribute("userName", request.getParameter("usuario"));
                request.setAttribute("message", "El usuario ha hecho login correctamente.");               
                request.setAttribute("redirect", "../servletRegistroVid/listado");
                response.setStatus(200);
            }
            else
            {
                throw new Exception("No existe ningun usuario con este login");
            }
        }
        catch (Exception e)
        {
            request.setAttribute("message", "El usuario no se ha podido logar. Error: " + e.getMessage() + ".");
            request.setAttribute("redirect", "../login.jsp");
            response.setStatus(400);
        }
        finally
        {
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }
    
    
    protected void processLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {     
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("title", "Log out");
        
        try
        {
            request.getSession().invalidate();

            request.setAttribute("message", "El usuario ha sido desconectado correctamente.");               
            request.setAttribute("redirect", "../login.jsp");
            
            response.setStatus(200);

        }
        catch (Exception e)
        {
            request.setAttribute("message", "El usuario no se ha podido deconectar. Error: " + e.getMessage() + ".");
            request.setAttribute("redirect", "../servletRegistroVid/listado");
            response.setStatus(500);
        }
        finally
        {
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
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
            throws ServletException, IOException
    {
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
        }
        else if(request.getRequestURL().toString().endsWith("/login")) {
            processLogin(request, response);
        }
        else if(request.getRequestURL().toString().endsWith("/logout")) {
            processLogout(request, response);
        }
        else {
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
        
        request.setAttribute("title", "Registro de usuarios");
        
        Usuario usuario;
        
        try {
            
            usuario = new Usuario(request.getParameter("usuario"),
                                    request.getParameter("password"),
                                    request.getParameter("nombre"),
                                    request.getParameter("apellidos"),
                                    request.getParameter("correo"));
            
            if (isNullEmptyOrWhiteSpace(usuario.getUser())) throw new Exception("Usuario no definido");
            if (isNullEmptyOrWhiteSpace(usuario.getPassword())) throw new Exception("Password no definido");
            if (!usuario.getPassword().equals(request.getParameter("password2"))) throw new Exception("Los passwords no coinciden");
            if (isNullEmptyOrWhiteSpace(usuario.getCorreo())) throw new Exception("Correo no definido");
                   
            UsuarioDAO.register(usuario);
                      
            request.setAttribute("message", "El usuario " + request.getParameter("usuario") + " ha sido registrado");     
            request.setAttribute("redirect", "../login.jsp");
            response.setStatus(200);       
        }
        catch(Exception e) {
            request.setAttribute("message", "El usuario no se ha podido registrar. Puede que el nombre de usuario o correo ya existan en la base de datos o haya algun problema de conectividad.");
            request.setAttribute("redirect", "../registroUsu.jsp");
            response.setStatus(400);
        }
        finally {
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }
    
    private boolean isNullEmptyOrWhiteSpace(String str)
    {
        return (str == null || str.trim().isEmpty());
    }
    
    
}
