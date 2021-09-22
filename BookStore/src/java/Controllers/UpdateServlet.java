/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import book.BookDAO;
import book.BookDTO;
import Category.CategoryDAO;
import Validate.Validate;


public class UpdateServlet extends HttpServlet {

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
        String id = request.getParameter("txtBookID");
        String title = request.getParameter("txtTitle");
        String image = request.getParameter("txtImage");
        String description = request.getParameter("txtDescription");
        String priceString = request.getParameter("txtPrice");
        String author = request.getParameter("txtAuthor");
        String quantityString = request.getParameter("txtQuantity");
        String categoryName = request.getParameter("txtCategoryName");
        BookDAO bookDAO = new BookDAO();
        Validate validate = new Validate();
        Date date = new Date();
        CategoryDAO categoryDAO = new CategoryDAO();
        HttpSession session = request.getSession();
        try {
            if (session != null) {
                String message = validate.getCreateNewBookFormValidated(title, image, description, priceString, author, quantityString, categoryName);
                if (message.isEmpty()) {
                    float price = Float.parseFloat(priceString);
                    int quantity = Integer.parseInt(quantityString);
                    String categoryID = categoryDAO.getIDByName(categoryName);
                    int currentDay = date.getDate();
                    int currentMonth = date.getMonth() + 1;
                    int currentYear = date.getYear() + 1900;
                    String lastUpdateDate = currentDay + "-" + currentMonth + "-" + currentYear;
                    BookDTO bookDTO = new BookDTO(id, title, image, description, price, author, lastUpdateDate, quantity, categoryID);
                    if (bookDAO.update(bookDTO)) {
                        bookDTO = bookDAO.getBookByTitle(title);
                        session.setAttribute("BOOK_SELECTED", bookDTO);
                        message = "Update Successfully !";
                        session.setAttribute("CRUD_MESSAGE", message);
                    }
                } else {
                    session.setAttribute("CRUD_MESSAGE", message);
                }
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect("updateDeleteAction.jsp");
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
