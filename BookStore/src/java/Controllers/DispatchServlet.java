/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DispatchServlet extends HttpServlet {

    private final String START_APP_CONTROLLER = "StartAppServlet";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";
    private final String DELETE_FROM_CART_CONTROLLER = "DeleteFromCartServlet";
    private final String UPDATE_PRODUCT_QUANTITY_IN_CART_CONTROLLER
            = "UpdateProductQuantityInCartServlet";
    private final String SEARCH_CONTROLLER = "SearchServlet";
    private final String CREATE_NEW_BOOK_CONTROLLER = "CreateNewBookServlet";
    private final String SELECT_PRODUCT_CONTROLLER = "SelectProductServlet";
    private final String UPDATE_CONTROLLER = "UpdateServlet";
    private final String DELETE_CONTROLLER = "DeleteServlet";
    private final String CREATE_VOUCHER_CONTROLLER = "CreateVoucherServlet";
    private final String APPLY_VOUCHER_CONTROLLER = "ApplyVoucherSevlet";
    private final String PAY_CONTROLLER = "PayServlet";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    private final String HISTORY_CONTROLLER = "HistoryServlet";
    private final String HISTORY_IN_DETAIL_CONTROLLER = "HistoryInDetailServlet";

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
        String url = START_APP_CONTROLLER;
        try {
            if (button == null) {

            } else if (button.equals("Add To Cart")) {
                url = ADD_TO_CART_CONTROLLER;
            } else if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (button.equals("DeleteFromCart")) {
                url = DELETE_FROM_CART_CONTROLLER;
            } else if (button.equals("Update Quantity")) {
                System.out.println("Here");
                url = UPDATE_PRODUCT_QUANTITY_IN_CART_CONTROLLER;
            } else if (button.equals("From 0 to 25 dollars")
                    || button.equals("From 26 to 50 dollars")
                    || button.equals("More than 50 dollars")
                    || button.equals("Java")
                    || button.equals("CSharp")
                    || button.equals("Javascript")
                    || button.equals("Java Web")
                    || button.equals("CPlus")
                    || button.equals("Python")
                    || button.equals("Search")) {
                url = SEARCH_CONTROLLER;
            } else if (button.equals("Create New Book")) {
                url = CREATE_NEW_BOOK_CONTROLLER;
            } else if (button.equals("Select")) {
                url = SELECT_PRODUCT_CONTROLLER;
            } else if (button.equals("Update")) {
                System.out.println("Update Dispatch !");
                url = UPDATE_CONTROLLER;
            } else if (button.equals("Delete")) {
                url = DELETE_CONTROLLER;
            } else if (button.equals("Create Voucher")) {
                System.out.println("Create Voucher Dispatch");
                url = CREATE_VOUCHER_CONTROLLER;
            } else if (button.equals("Apply Voucher")) {
                url = APPLY_VOUCHER_CONTROLLER;
            } else if (button.equals("Pay")) {
                url = PAY_CONTROLLER;
            } else if (button.equals("Logout")) {
                url = LOGOUT_CONTROLLER;
            } else if (button.equals("History")) {
                url = HISTORY_CONTROLLER;
            } else if (button.equals("HistoryInDetail")) {
                url = HISTORY_IN_DETAIL_CONTROLLER;
            }
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
