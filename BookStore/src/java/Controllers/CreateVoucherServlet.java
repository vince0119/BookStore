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
import Discount.DiscountDAO;
import Discount.DiscountDTO;
import Validate.Validate;


public class CreateVoucherServlet extends HttpServlet {

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
        DiscountDAO discountDAO = new DiscountDAO();
        HttpSession session = request.getSession(false);
        Date date = new Date();
        String discountTitle = request.getParameter("txtDiscountTitle");
        String discountPercentTmp = request.getParameter("txtDiscountPercent");
        String validDayTmp = request.getParameter("txtDay");
        String validMonthTmp = request.getParameter("txtMonth");
        String validYearTmp = request.getParameter("txtYear");
        Validate validate = new Validate();
        try {
            if (session != null) {
                String error = validate.getCreateVoucherFormValidated(discountTitle,
                        discountPercentTmp, validDayTmp, validMonthTmp, validYearTmp);
                if (error.isEmpty()) {
                    int currYear = date.getYear() + 1900;
                    int currMonth = date.getMonth() + 1;
                    int currDay = date.getDate();
                    int currHour = date.getHours();
                    int currMin = date.getMinutes();
                    int currSecond = date.getSeconds();
                    int validDay = Integer.parseInt(validDayTmp);
                    int validMonth = Integer.parseInt(validMonthTmp);
                    int validYear = Integer.parseInt(validYearTmp);
                    // Check if validDate > currDate
                    error = validate.isValidDateRight(currYear, currMonth, currDay, validYear, validMonth, validDay);
                    if (error.isEmpty()) {
                        String discountID = "Y" + currYear + "-" + "M" + currMonth + "-"
                                + "D" + currDay + "-" + "H" + currHour + "-"
                                + "M" + currMin + "-" + "S" + currSecond;
                        int discountPercent = Integer.parseInt(discountPercentTmp.toString());
                        String createDate = currDay + "-" + currMonth + "-" + currYear;
                        String validDate = validDay + "-" + validMonth + "-" + validYear;
                        DiscountDTO discountDTO = new DiscountDTO(discountID, discountTitle, discountPercent, createDate, validDate, false);
                        /*
                Y/Nam-M/Thang-D/Ngay-H/Gio-M/Phut-S/Giat
                         */
                        if (discountDAO.createNewVoucher(discountDTO)) {
                            session.setAttribute("NEW_CREATED_VOUCHER", date);
                            session.setAttribute("CREATE_VOUCHER_MSG", "Create Voucher Successfully !");
                        }

                    } else {
                        session.setAttribute("CREATE_VOUCHER_MSG", error);
                    }
                } else {
                    session.setAttribute("CREATE_VOUCHER_MSG", error);
                }
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect("createVoucher.jsp");
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
