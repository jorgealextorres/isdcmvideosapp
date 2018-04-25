package videos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import isdcm.video.soap.SoapService_Service;
import isdcm.video.soap.Video;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jorgetorres
 */
//@WebServlet(urlPatterns = {"/busquedaVideoServlet"})
public class busquedaVideoServlet extends HttpServlet {

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
        List<Video> videos = null;
        String parametrosBusqueda = "";
        String searchText = request.getParameter("searchText");
        
        
        if(!searchText.isEmpty()){
            SoapService_Service service = new SoapService_Service();   
            
            parametrosBusqueda = "Búsqueda realizada para el tipo de busqueda ";
        
            switch(request.getParameter("tipoBusqueda")){
                case "title": 
                    videos = service.getSoapServicePort().searchByTitle(searchText);
                    parametrosBusqueda = parametrosBusqueda + " 'titulo' ";
                    break;
                case "autor": 
                    videos = service.getSoapServicePort().searchByAutor(searchText);
                    parametrosBusqueda = parametrosBusqueda + " 'autor' ";
                    break;
                case "year": 
                    Integer year = 0;
                    try {
                        year = Integer.parseInt(searchText);
                        videos = service.getSoapServicePort().searchByYear(year);
                        parametrosBusqueda = parametrosBusqueda + " 'año de creación' ";
                    }
                    catch(Exception ex) {
                        parametrosBusqueda = "El formato del año no es válido. Búsqueda realizada para el tipo de busqueda 'año de creacion' ";
                    }

                    break;
            }
            
            parametrosBusqueda = parametrosBusqueda + " y con el texto de búsqueda '" + searchText +"'";
        } else{
            parametrosBusqueda = "No se ha introducido un texto de búsqueda.";
        }
        request.setAttribute("listaVideos", videos);
        request.setAttribute("parametrosBusqueda", parametrosBusqueda);
        
        response.setStatus(200);
        
        request.getRequestDispatcher("../busquedaVideo.jsp").forward(request, response);
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
        
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userName") != null)
        {
            processRequest(request, response);
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
        
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userName") != null)
        {
                response.sendRedirect(String.format("%s%s", request.getContextPath(),"/busquedaVideo.jsp"));
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

}
