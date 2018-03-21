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
import javax.servlet.http.HttpSession;

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
        response.sendRedirect(String.format("%s%s", request.getContextPath(),"/registroVid.jsp"));
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
        //processRequest(request, response);
        response.sendRedirect(String.format("%s%s", request.getContextPath(),"/listadoVid.jsp"));
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
            throws ServletException, IOException
    {       
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("userName") != null)
        {
            if (request.getRequestURL().toString().endsWith("/register")) {
                processRegister(request, response);
            } else{
                processRequest(request, response);
            }  
        }
        else
        {
            request.setAttribute("title", "Error");
            request.setAttribute("redirect", "../login.jsp");
            request.setAttribute("message", "403 Forbidden");
            response.setStatus(403);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
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
        
        Video video;
        
        try {
            String titulo = request.getParameter("titulo");
            String reproducciones = request.getParameter("reproducciones");
            
            if (isNullEmptyOrWhiteSpace(titulo)) throw new Exception("Titulo no definido");
            if (isNullEmptyOrWhiteSpace(request.getParameter("fechaCreacion"))) throw new Exception("Fecha no definida");

            if (isNullEmptyOrWhiteSpace(reproducciones)) reproducciones = "0";
            
            if (VideoDAO.videoExists(titulo)) throw new Exception("El video ya existe");
            
            try
            {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
                LocalTime duracionLocal = LocalTime.parse(request.getParameter("duracion"), dateTimeFormatter);
                video = new Video(titulo,
                                    request.getParameter("autor"),
                                    new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fechaCreacion")),
                                    new Time(duracionLocal.getHour(), duracionLocal.getMinute(), duracionLocal.getSecond()),
                                    new Integer(request.getParameter("reproducciones")),
                                    request.getParameter("descripcion"),
                                    request.getParameter("formato"));
            }
            catch (Exception e) {
                throw new Exception("Algunos datos del video no tienen el formato adecuado");
            }
            if (video.getReproducciones() < 0) throw new Exception("El numero de reproducciones no puede ser negativo");
            
            VideoDAO.register(video);
            
            request.setAttribute("message", "El video se ha registrado correctamente.");
            response.setStatus(200);
        }
        catch(Exception e) {
            request.setAttribute("message", "El video no se ha podido registrar. Error: " + e.getMessage() + ".");
            response.setStatus(400);
        }
        finally
        {
            response.setContentType("text/html;charset=UTF-8");
            request.setAttribute("title", "Registrar video");
            request.setAttribute("redirect", "../servletRegistroVid/listado");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }
    
    private boolean isNullEmptyOrWhiteSpace(String str)
    {
        return (str == null || str.trim().isEmpty());
    }
}


