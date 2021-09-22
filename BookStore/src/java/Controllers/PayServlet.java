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
import java.util.HashMap;
import java.util.Map;
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
import Cart.Cart;
import Order.OrderDAO;
import Orderdetail.OrderDetailDAO;
import Users.UserDAO;
import Users.UserDTO;


public class PayServlet extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        Date date = new Date();
        UserDAO userDAO = new UserDAO();
        BookDAO bookDAO = new BookDAO();
        OrderDAO orderDAO = new OrderDAO();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        String message = "";
        String urlRewriting = "invalid.html";
        try {
            System.out.println("Here");
            if (session != null) {
                System.out.println("Session != null");
                UserDTO userDTO = (UserDTO) session.getAttribute("USER_DTO");
                if (userDTO != null) {
                    System.out.println("user != null");
                    urlRewriting = "DispatchServlet?btnAction=Login"
                            + "&txtUsername=" + userDTO.getUsername()
                            + "&txtPassword=" + userDTO.getPassword();
                    int second = date.getSeconds();
                    int minute = date.getMinutes();
                    int hour = date.getHours();
                    int day = date.getDate();
                    int month = date.getMonth() + 1;
                    int year = date.getYear() + 1900;
                    String time = year + "-" + month + "-" + day
                            + "-" + hour + "-" + minute + "-" + second;
                    String lastestID = orderDAO.getLastestOrderID();
                    String orderID = "";
                    if (lastestID == null || lastestID.isEmpty()) {
                        orderID = "Order-1";
                    } else {
                        orderID = "Order-".concat((Integer.parseInt(lastestID.substring(6)) + 1) + "");
                    }
                    String totalTmp = (String) session.getAttribute("TOTAL_WITH_VOUCHER");
                    float total = Float.parseFloat(totalTmp);
                    if (orderDAO.createNewOrder(orderID, userDTO.getUsername(), time, total)) {
                        /*
                            orderDetailID
                            bookID
                            quantity
                            orderID
                         */

                        Cart cart = (Cart) session.getAttribute("CART");
                        if (cart != null) {
                            Map<String, Integer> items = cart.getItems();
                            for (Map.Entry item : items.entrySet()) {
                                //Create automatic increment orderDetailID
                                String lastestOrderDetailID = orderDetailDAO.getLastestOrderDetailID();
                                String orderDetailID = "";
                                if (lastestOrderDetailID == null || lastestOrderDetailID.isEmpty()) {
                                    orderDetailID = 1 + "";
                                } else {
                                    orderDetailID = (Integer.parseInt(lastestOrderDetailID) + 1) + "";
                                }
                                System.out.println("OrderID Detail " + orderDetailID);
                                // get BookID
                                BookDTO bookDTO = bookDAO.getBookByTitle((String) item.getKey());
                                String bookID = bookDTO.getBookID();
                                //get Quantity
                                int quantity = (int) item.getValue();
                                //get OrderID
                                String orderIDTmp = orderDAO.getLastestOrderID();
                                if (orderDetailDAO.createOrderDetail(orderDetailID, bookID, quantity, orderID)) {
                                    if (bookDAO.updateQuantity(bookID, bookDTO.getQuantity() - quantity)) {
                                        String addedCoin = request.getParameter("txtAddedCoin");
                                        if (addedCoin != null) {
                                            int updateCoin = userDTO.getCoin() + Integer.parseInt(addedCoin);
                                            if (userDAO.updateCoin(updateCoin, userDTO.getUsername())) {
                                                session.setAttribute("CART", null);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect(urlRewriting);
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
