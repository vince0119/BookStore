/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Users.UserDAO;
import Users.UserDTO;
import Validate.Validate;


public class LoginServlet extends HttpServlet {

    private String LOGIN_PAGE = "login.jsp";
    private String INDEX_PAGE = "index.jsp";
    private String ADMIN_DASHBOARD_PAGE = "createBook.jsp";

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession(false);
        try {
            Validate validate = new Validate();
            UserDAO userDAO = new UserDAO();
            String error = validate.getLoginFormValidated(username, password);
            if (error.isEmpty()) {
                if (userDAO.checkLogin(username, password)) {
                    UserDTO userDTO = userDAO.getUserByUsername(username);
                    boolean isAdmin = userDAO.checkRoleByUsername(username);
                    if (isAdmin) {
                        url = ADMIN_DASHBOARD_PAGE;
                    } else {
                        url = INDEX_PAGE;
                        session.setAttribute("IS_LOGINNED", true);
                    }
                    session.setAttribute("USER_DTO", userDTO);
                } else {
                    session.setAttribute("LOGIN_ERROR", "User is not existed !");
                }
            } else {
                session.setAttribute("LOGIN_ERROR", error);
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect(url);
            out.close();
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
        processRequest(request, response);
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
