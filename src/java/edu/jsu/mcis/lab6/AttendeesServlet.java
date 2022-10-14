/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.jsu.mcis.lab6;

import edu.jsu.mcis.lab6.dao.DAOFactory;
import edu.jsu.mcis.lab6.dao.RegistrationDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andrewpruitt
 */
public class AttendeesServlet extends HttpServlet {

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
            out.println("<title>Servlet AttendeesServle</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AttendeesServle at " + request.getContextPath() + "</h1>");
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

        ServletContext context = request.getServletContext();

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        }
        else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }
        
        response.setContentType("application/json; charset=UTF-8");
        
        try ( PrintWriter out = response.getWriter()) {
            
            int sessionid = Integer.parseInt(request.getParameter("sessionid"));
            int attendeeid = Integer.parseInt(request.getParameter("attendeeid"));
            
            RegistrationDAO dao = daoFactory.getRegistrationDAO();
            
            out.println(dao.find(sessionid, attendeeid));
            
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        
        doGet(request, response);  
        
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        
    BufferedReader br = null;
    
    response.setContentType("application/json;charset=UTF-8");
    
    try (PrintWriter out = response.getWriter()) {
        
        br = new BufferedReader(new InputStreamReader(request.getInputStream())); 
        String p = URLDecoder.decode(br.readLine().trim(), Charset.defaultCharset());
          HashMap<String, String> parameters = new HashMap<>();
          String[] pairs = p.trim().split("&");
          for (int i = 0; i < pairs.length; ++i) {
              String[] pair = pairs[i].split("=");
              parameters.put(pair[0], pair[1]);
}
          String name = parameters.get("name");
          int id = Integer.parseInt(parameters.get("id"));
          // rest of servlet code goes here
      }
      catch (Exception e) { e.printStackTrace(); }
      
    finally {
        if (br != null) {
            try { br.close(); } catch (Exception e) { e.printStackTrace(); }

        } 
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
