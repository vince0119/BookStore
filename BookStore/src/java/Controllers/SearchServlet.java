/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import book.BookDAO;
import book.BookDTO;
import Category.CategoryDAO;
import Category.CategoryDTO;


public class SearchServlet extends HttpServlet {

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
        String button = request.getParameter("btnAction");
        String checkPage = request.getParameter("txtCheckPage");
        String searchValue = request.getParameter("txtSearchProduct");
        String url = "search.jsp";
        HttpSession session = request.getSession(false);

        try {
            int countForVistUpdateDeletePage = 2;
            CategoryDAO categoryDAO = new CategoryDAO();
            List<CategoryDTO> categoryList = categoryDAO.getCategories();
            BookDAO bookDAO = new BookDAO();
            List<BookDTO> bookList = null;
            if (button.equals("From 0 to 25 dollars")) {
                bookList = new ArrayList<>();
                bookList = bookDAO.getListBySearchAndFilterbyPrice(searchValue, 0, 25);
            } else if (button.equals("From 26 to 50 dollars")) {
                bookList = new ArrayList<>();
                bookList = bookDAO.getListBySearchAndFilterbyPrice(searchValue, 25, 50);
            } else if (button.equals("More than 50 dollars")) {
                bookList = new ArrayList<>();
                bookList = bookDAO.getListBySearchAndFilterbyPrice(searchValue, 50,
                        bookDAO.getMaxPrice());
            } else if (button.equals("Search")) {
                bookList = new ArrayList<>();
                if (!searchValue.isEmpty()) {
                    bookList = bookDAO.getListBySearchAndFilterbyPrice(searchValue,
                            0,
                            bookDAO.getMaxPrice());
                }
                if (checkPage.equals("fromIndex") || checkPage.equals("fromSearch")) {
                    url = "search.jsp?txtSearchProduct=" + searchValue;
                } else if (checkPage.equals("fromUpdateAndDelete")) {
                    url = "updateDelete.jsp?txtSearchProduct=" + searchValue;
                }
            } else {
                String categoryID = categoryDAO.getIDByName(button.toString());
                System.out.println("Category ID : " + categoryID);
                bookList = bookDAO.getBooksByCategoryID(categoryID);
            }
            session.setAttribute("SEARCH_LIST", bookList);
            session.setAttribute("VISIT_UPDATE_DELETE_TIME", countForVistUpdateDeletePage + "");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
