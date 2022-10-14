/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.jsu.mcis.lab6;

import edu.jsu.mcis.lab6.dao.DAOFactory;
import edu.jsu.mcis.lab6.dao.TrainingSessionsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andrewpruitt
 */
public class TrainingSessions extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TrainingSessions</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TrainingSessions at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        
        DAOFactory daoFactory = null;
        
    String p_id = request.getParameter("id");            
    if (p_id == null || "".equals(p_id)) { 
    /* no id provided; insert corresponding code here */
    //
    }
    else{
        try ( PrintWriter out = response.getWriter()) {
            
            int sessionid = Integer.parseInt(request.getParameter("sessionid"));
            int attendeeid = Integer.parseInt(request.getParameter("attendeeid"));
            
            TrainingSessionsDAO dao = daoFactory.getTrainingSessionsDAO();
            
            out.println(dao.find(sessionid, attendeeid));
            
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
