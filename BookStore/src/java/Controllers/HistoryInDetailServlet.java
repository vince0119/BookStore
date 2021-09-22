/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import book.BookDAO;
import Cart.Cart;
import Order.OrderDAO;
import Order.OrderDTO;
import Orderdetail.OrderDetailDAO;
import Orderdetail.OrderDetailDTO;


public class HistoryInDetailServlet extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                String txtOrderID = request.getParameter("txtOrderID");
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                OrderDAO orderDAO = new OrderDAO();
                OrderDTO orderDTO = orderDAO.getOrderDTOByOrderID(txtOrderID);
                BookDAO bookDAO = new BookDAO();
                List<OrderDetailDTO> orderDetailDTOList = orderDetailDAO.getOrderDetailDTOByOrderID(txtOrderID);
                session.setAttribute("ORDER_DETAIL_LIST", orderDetailDTOList);
                Cart cart = new Cart();
                for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
                    cart.addToCart(bookDAO.getBookDTOByBookID(orderDetailDTO.getBookID()).getTitle());
                }
                session.setAttribute("CART_2", cart);
                session.setAttribute("SELECTED_ORDER", orderDTO);
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect("HistoryInDetail.jsp");
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
