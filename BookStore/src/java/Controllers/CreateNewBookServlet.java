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
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import book.BookDAO;
import Category.CategoryDAO;
import Validate.Validate;


public class CreateNewBookServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("type/html;charset=UTF-8");
        PrintWriter s = response.getWriter();
        HttpSession session = request.getSession(false);
        Validate date = new Validate();
        BookDAO bookDao = new BookDAO();
        CategoryDAO categoryDao = new CategoryDAO();
        Date day = new Date();
        try {
            String lastBookID = bookDao.getLastBookID();
            String bookID = "Book".concat((Integer.parseInt(lastBookID.substring(4)) + 1) + "");
            String title = request.getParameter("txtTitle");
            String image = request.getParameter("txTimage");
            String description = request.getParameter("txtDescription");
            String price = request.getParameter("txtPrice");
            String author = request.getParameter("txtAuthor");
            String quantity = request.getParameter("txtQuantity");
            String CategoryName = request.getParameter("txtCategoryName");
            String Error = date.getCreateNewBookFormValidated(title, image, description, price, author, quantity, CategoryName);
            if (Error.isEmpty()) {
                float priceS = Float.parseFloat(price);
                int quantityS = Integer.parseInt(quantity);
                String CategoryID = categoryDao.getIDByName(CategoryName);
                int currentDay = day.getDate();
                int currentMonth = day.getMonth();
                int currentyear = day.getYear();
                String importDay = currentDay + "/" + currentMonth + "/" + currentyear;
                if (bookDao.createBook(bookID, title, image, description, priceS, author, importDay,"",quantityS, false, CategoryID)) {
                    session.setAttribute("CURD_MESSAGE", "Create Successfully.");
                }
            } else {
                session.setAttribute("CURD_MESSAGE", "error.");
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally{
            response.sendRedirect("createBook.jsp");
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
