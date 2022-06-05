package com.example.sructorserver;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

        import java.io.IOException;
        import java.io.PrintWriter;
        import java.sql.SQLException;
        import java.util.logging.Level;
        import java.util.logging.Logger;
        import javax.servlet.RequestDispatcher;
        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import sample.scrutor.Scrutor_studentDAO;
        import sample.scrutor.Scrutor_teacherDAO;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {
    private final String LOGIN_PAGE = "Login.jsp";
    private final String SEARCH_PAGE = "search.jsp";
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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        String button = request.getParameter("btAction");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        try {
            if(button.equals("Login")) {
                //Check Account whether a Student OR not
                Scrutor_studentDAO  daoStudent = new Scrutor_studentDAO();
                boolean result = daoStudent.checkLoginStudent(username, password);
                HttpSession session = request.getSession();
                session.setAttribute("RESULT", result);
                if (result) {
                    String fullname = daoStudent.getUsername(username);
                    session.setAttribute("FULLNAME", fullname);
                    url = SEARCH_PAGE;
                }
                //Check Account whether a Teacher OR not
                Scrutor_teacherDAO  daoTeacher = new Scrutor_teacherDAO();
                boolean result1 = daoTeacher.checkLoginTeacher(username, password);
                session.setAttribute("RESULT", result1);
                if (result1) {
                    String fullname = daoTeacher.getUsername(username);
                    session.setAttribute("FULLNAME", fullname);
                    url = SEARCH_PAGE;
                }

            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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