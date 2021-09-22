<%-- 
    Document   : viewCart
    Created on : May 15, 2021, 7:09:19 PM
    Author     : phuc
--%>

<%@page import="Discount.DiscountDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Discount.DiscountDTO"%>
<%@page import="book.BookDTO"%>
<%@page import="book.BookDAO"%>
<%@page import="Users.UserDTO"%>
<%@page import="java.util.Map"%>
<%@page import="Cart.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/viewCartStyles.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (session != null) {
                List<DiscountDTO> list = null;
                DiscountDAO discountDAO = new DiscountDAO();
                UserDTO userDTO = (UserDTO) session.getAttribute("USER_DTO");
                Cart cart = (Cart) session.getAttribute("CART");
                float totalPrice = 0;
                int addedCoin = 0;
        %>
        <div class="container" >
            <div class="title" >
                <h1>Payment</h1>
            </div>
            <%
                if (cart != null) {
                    totalPrice = cart.getTotalPrice();
                    String total = String.format("%.02f", cart.getTotalPrice());
                    String totalWithVoucher = (String) session.getAttribute("APPLY_VOUCHER_TOTAL_PRICE");
                    if (totalWithVoucher != null) {
                        total = String.format("%.02f", Float.parseFloat(totalWithVoucher));
                    }
                    session.setAttribute("TOTAL_WITH_VOUCHER", total);
                    Map<String, Integer> items = cart.getItems();
            %>
            <div class="main" >
                <%
                    if (session != null) {
                        if (totalPrice >= 50.0 && totalPrice < 100.0) {
                            addedCoin = 50;
                        } else if (totalPrice >= 100 && totalPrice < 200) {
                            addedCoin = 100;
                        } else if (totalPrice >= 200) {
                            addedCoin = 200;
                        }
                %>
                <div class="user-information">
                    <h1 class="user-information-title">User's Information</h1>
                    <div class="block" >
                        <p class="infor-title"> Fullname</p> 
                        <p class="infor-content-text"><%= userDTO.getFullname()%></p>
                    </div>
                    <div class="block" >
                        <p class="infor-title" >Email</p>
                        <p class="infor-content-text"><%= userDTO.getEmail()%></p>
                    </div>
                    <div class="block" >
                        <p class="infor-title">Phone Number</p>
                        <p class="infor-content-text"><%= userDTO.getPhoneNumber()%></p>
                    </div>
                    <div class="block" >
                        <p class="infor-title">Address</p>
                        <p class="infor-content-text"><%= userDTO.getAddress()%></p>
                    </div>
                    <div class="block">
                        <p class="infor-title">Coin will be added </p>
                        <p class="infor-content-text"><%= addedCoin%></p>
                    </div>
                    <div class="block" >
                        <a href="index.jsp" style="text-decoration: none">
                            <<< Back To Shopping Here ! </a>
                    </div>
                </div>
                <%
                    }
                %>
                <div class="line"></div>
                <div class="cart-information">
                    <h1 class="cart-information-title">Your Cart</h1>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Title</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Update</th>
                                <th>Remove</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int count = 0;
                                BookDAO bookDAO = new BookDAO();
                                for (Map.Entry<String, Integer> item : items.entrySet()) {
                                    BookDTO bookDTO = bookDAO.getBookByTitle(item.getKey());
                                    String urlRewritingUpdate = "DispatchServlet"
                                            + "?btnAction=UpdateQuantity"
                                            + "&pk=" + item.getKey()
                                            + "&txtQuantityInCart=" + request.getParameter("txtQuantityInCart");
                                    String urlRewritingDelete = "DispatchServlet"
                                            + "?btnAction=DeleteFromCart"
                                            + "&pk=" + item.getKey();
                            %>
                        <form action="DispatchServlet" method="POST" >
                            <tr>
                                <td><%= ++count%></td>
                                <td>
                                    <%= item.getKey()%>
                                    <input type="hidden" name="txtTitle" 
                                           value="<%= item.getKey()%>" />
                                </td>
                                <td>
                                    <input id="quantity" 
                                           class="quantity-text" 
                                           type="text" 
                                           name="txtQuantityInCart" 
                                           value="<%= item.getValue()%>" />
                                </td>
                                <td><%= bookDTO.getPrice()%>$</td>
                                <td>
                                    <input type="submit" value="Update Quantity" name="btnAction" />
                                </td>
                                <td>
                                    <a href="<%= urlRewritingDelete%>">
                                        <img class="delete-icon" src="https://d1nhio0ox7pgb.cloudfront.net/_img/v_collection_png/512x512/shadow/delete.png" />
                                    </a>
                                </td>
                            </tr>
                        </form>
                        <%
                            }
                        %>
                        <tr>
                        <form action="DispatchServlet" method="POST">
                            <td colspan="3" style="text-align: center">
                                <select name="txtSelectDiscount">
                                    <%
                                        list = new ArrayList<DiscountDTO>();
                                        if (userDTO.getCoin() >= 1000) {
                                            int percent = 20;
                                            try {
                                                list.add(discountDAO.getDiscountDTOPercent(0, percent));
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            if (userDTO.getCoin() >= 2000) {
                                                percent = 40;
                                                try {
                                                    list.add(discountDAO.getDiscountDTOPercent(20, percent));
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                                if (userDTO.getCoin() >= 3000) {
                                                    percent = 50;
                                                    try {
                                                        list.add(discountDAO.getDiscountDTOPercent(40, percent));
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                    if (userDTO.getCoin() >= 4000) {
                                                        percent = 60;
                                                        try {
                                                            list.add(discountDAO.getDiscountDTOPercent(50, percent));
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        for (DiscountDTO discountDTO : list) {
                                    %>
                                    <option>
                                        <%= discountDTO.getDiscountID() + "( -" + discountDTO.getDiscountPercent() + "%)"%> 
                                    </option>
                                    <%
                                        }
                                    %>
                                </select>
                            </td>
                            <td colspan="3" >
                                <input type="hidden" name="txtTotalPrice" value="<%= total%>" />
                                <%
                                    if (Float.parseFloat(total) >= 20) {
                                %>
                                <input class="apply-voucher-btn" type="submit" value="Apply Voucher" name="btnAction" />
                                <%
                                } else {
                                %>
                                <p style="color: red" >Total must be more than 20 to use Voucher</p>
                                <%
                                    }
                                %>
                            </td>
                        </form>
                        </tr>
                        <tr>
                            <td colspan="3" style="text-align: center" >Total</td>
                            <td colspan="3" style="text-align: center"><%= total%>$</td>
                        </tr>
                        </tbody>
                    </table>
                    <%
                        String errorMessage = (String) session.getAttribute("UPDATE_QUANTITY_IN_IN_CART_MESSAGE");
                        if (errorMessage != null && !errorMessage.isEmpty()) {
                    %>
                    <p class="message"><%= errorMessage%></p>
                    <%
                        }
                    %>
                </div>
            </div>
            <% } else {
                String urlRewriting = "DispatchServlet"
                        + "?btnAction=Login"
                        + "&txtUsername=" + userDTO.getUsername()
                        + "&txtPassword=" + userDTO.getPassword();
            %>
            <div class="cart-empty" >             
                <h2 class="cart-empty-title" >Your Cart is empty</h2>
                <a class="cart-empty-link" href="<%= urlRewriting%>" >Shopping Now !</a>
            </div>
            <%
                }
            %>
            <%
                if (cart != null) {
            %>

            <form action="DispatchServlet" method="POST">
                <input type="hidden" name="txtAddedCoin" value="<%= addedCoin%>" />
                <input class="btn-Pay"  type="submit" value="Pay" name="btnAction" />
            </form>
            <%
                }
            %>
        </div>
        <%
            }
        %>
    </body>

</html>
