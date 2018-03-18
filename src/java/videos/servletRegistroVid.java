package videos;

import common.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class servletRegistroVid extends HttpServlet {

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
        /*
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
            
            DatabaseConnection.disconnect();
        }
        catch(Exception e) {
            out.println("<h1>El usuario no se ha podido logar. Error: " + e.getMessage()+ "</h1>");
        }
        
        out.println("</body>");
        out.println("</html>");
        */
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
        out.println("<title>Video registrado</title>");            
        out.println("</head>");
        out.println("<body>");
        
        Video video;
        
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
            LocalTime duracionLocal = LocalTime.parse(request.getParameter("duracion"), dateTimeFormatter);
            
            video = new Video(request.getParameter("titulo"),
                                request.getParameter("autor"),
                                new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("fechaCreacion")),
                                new Time(duracionLocal.getHour(), duracionLocal.getMinute(), duracionLocal.getSecond()),
                                new Integer(request.getParameter("reproducciones")),
                                request.getParameter("descripcion"),
                                request.getParameter("formato"));
            
            VideoDAO.register(video);
            out.println("<h1>El video ha sido registrado</h1>");
            out.println("<p>video: " + request.getParameter("titulo") + "</p>");
            
            DatabaseConnection.disconnect();
        }
        catch(Exception e) {
            out.println("<h1>El video no se ha podido registrar. Error: " + e.getMessage()+ "</h1>");
        }
        
        out.println("</body>");
        out.println("</html>");
    }
}
