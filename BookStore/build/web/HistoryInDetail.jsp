<%@page import="java.util.Map"%>
<%@page import="Cart.Cart"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/indexStyles.css" >
    </head>
    <body>
        <c:set var="isLoginned" value="${sessionScope.IS_LOGINNED}"/>
        <c:set var="orderDetailList" value="${sessionScope.ORDER_DETAIL_LIST}"/>
        <c:set var="selectedOrder" value="${sessionScope.SELECTED_ORDER}"/>
        <div class="header" >
            <a href="index.jsp" ><img class="header_logo" src="images/logo.png"/></a>
            <form action="DispatchServlet" method="POST" class="header_search">
                <input class="search_box" 
                       type="text" name="txtSearchProduct"
                       value="" 
                       placeholder="Search Product Here ... " />
                <ul class="menu" >
                    <li>
                        Filter By &#9660
                        <ul class="sub-menu">
                            <li class="block-sub-menu">
                                Price &#9658 
                                <div class="sub-menu-2" >
                                    <input type="submit" 
                                           value="From 0 to 25 dollars" 
                                           name="btnAction"/>
                                    <input type="submit" 
                                           value="From 26 to 50 dollars" 
                                           name="btnAction"/>
                                    <input type="submit" 
                                           value="More than 50 dollars" 
                                           name="btnAction"/>
                                </div>
                            </li>
                            <li class="block-sub-menu" >
                                Category &#9658 
                                <div class="sub-menu-2">
                                    <c:set var="categories" value="${sessionScope.CATEGORY_LIST}"/>
                                    <c:forEach var="category" items="${categories}">
                                        <input type="submit" 
                                               value="${category.categoryName}" 
                                               name="btnAction"
                                               />
                                    </c:forEach>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
                <input type="hidden" name="txtCheckPage" value="fromIndex" />
                <input class="search_button" type="submit" value="Search" name="btnAction" />
            </form>
            <c:if test="${isLoginned eq false}" >
                <div class="header-login">
                    <a class="login-link" href="login.jsp">Login</a>
                </div>
                <div class="shopping-cart">
                    <a class="cart-link" href="login.jsp">Cart : 0</a>
                </div>
            </c:if>
            <c:if test="${isLoginned eq true}">
                <div class="header-user">
                    <small style="font-size: 10px" >Account</small>
                    <ul class="block-account">
                        <li>
                            <small style="font-size: 12px">${sessionScope.USER_DTO.fullname}&#9660</small>
                            <ul class="sub-menu-account">
                                <li><p>Coin : ${sessionScope.USER_DTO.coin}</p></li>
                                <li><a href="DispatchServlet?btnAction=History" >History</a></li>
                                <li><a href="DispatchServlet?btnAction=Logout">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div class="shopping-cart" >
                    <c:set var="totalQuantity" value="${sessionScope.TOTAL_QUANTITY}" />
                    <c:set var="cart" value="${sessionScope.CART}"/>
                    <c:if test="${ empty totalQuantity || empty cart}" >
                        <a class="cart-link" href="viewCart.jsp">Cart :  0</a>    
                    </c:if>
                    <c:if test="${not empty totalQuantity && not empty cart}" >
                        <c:url var="viewCartLink" value="viewCart.jsp">
                            <c:param name="txtUsername" value="${sessionScope.USER_DTO.username}"/>
                            <c:param name="txtFullname" value="${sessionScope.USER_DTO.fullname}"/>
                            <c:param name="txtAddress" value="${sessionScope.USER_DTO.address}" />
                            <c:param name="txtEmail" value="${sessionScope.USER_DTO.email}" />
                            <c:param name="txtTotalQuantity" value="${totalQuantity}"/>
                        </c:url>
                        <a class="cart-link" href="${viewCartLink}">Cart :  ${totalQuantity}</a>
                    </c:if>
                </div>
            </c:if>
        </div>
        <div class="content" >
            <div class="container" >
                <!-- Slider : Start -->
                <div class="breadscrum" >
                    <!--Start of slider-->
                    <div class="slider">
                        <!--Start of slides-->
                        <div class="slides">
                            <!--Radio button Start-->
                            <input type="radio" name="radio-btn" id="radio1" value="" />
                            <input type="radio" name="radio-btn" id="radio2" value="" />
                            <input type="radio" name="radio-btn" id="radio3" value="" />
                            <input type="radio" name="radio-btn" id="radio4" value="" />
                            <!--Radio button End-->
                            <!--Slide image : Start-->
                            <div class="slide first">
                                <img src="images/Slide04.jpg"/>
                            </div>
                            <div class="slide" >
                                <img src="images/Slide03.jpg"/>
                            </div>
                            <div class="slide" >
                                <img src="images/Slide02.jpg"/>
                            </div>
                            <div class="slide" >
                                <img src="images/Slide01.jpg"/>
                            </div>
                            <!--Slide image : End-->
                            <!--Automatic Slide: Start-->
                            <div class="slide-auto">
                                <div class="auto-btn1"></div>
                                <div class="auto-btn2"></div>
                                <div class="auto-btn3"></div>
                                <div class="auto-btn4"></div>
                            </div>
                            <!--Automatic Slide: End-->
                        </div>
                        <!--End of slides-->
                        <!--Slide Manual : Start-->
                        <div class="slide-manual">
                            <label for="radio1" class="manual-btn" ></label>
                            <label for="radio2" class="manual-btn" ></label>
                            <label for="radio3" class="manual-btn" ></label>
                            <label for="radio4" class="manual-btn" ></label>
                        </div>
                        <!--Slide Manual : End-->
                    </div>
                    <!--End of slider-->
                    <script type="text/javascript">
                        var counter = 1;
                        setInterval(function () {
                            document.getElementById('radio' + counter).checked
                                    = true;
                            counter++;
                            if (counter > 4) {
                                counter = 1;
                            }
                        }, 4000);
                    </script> 
                    <div class="promotion">
                        <img class="promotion-banner" src="images/featured_banner_1.png" />
                    </div>
                </div>
                <div class="history-detail" >
                    <c:if test="${ not empty orderDetailList}">
                        <div class="history-detail-text">
                            <p>Bill on : ${selectedOrder.time}</p>
                        </div>
                        <table class="history-detail-content" border="1">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Book Name</th>
                                    <th>Quantity</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    Cart cart = (Cart) session.getAttribute("CART_2");
                                    if (cart != null) {
                                        Map<String, Integer> items = cart.getItems();
                                        if (items != null) {
                                            int count = 0;
                                            for (Map.Entry<String, Integer> item : items.entrySet()) {
                                %>
                                <tr>
                                    <td><%= ++count%></td>
                                    <td><%= item.getKey()%></td>
                                    <td><%= item.getValue()%></td>
                                </tr>
                                <%
                                    }
                                %>
                                <tr>
                                    <td colspan="2" >Total</td>
                                    <td>${selectedOrder.total}</td>
                                </tr>
                                <%
                                        }
                                    }
                                %>

                            </tbody>
                        </table>

                    </c:if>
                </div>
            </div>
        </div>
        <div class="footer">
            <div class="footer-container" >
                <div class="about-us" >
                    <p class="footer-title">About Me</p>
                    <div class="block-text" >
                        <p>Fullname : Pham Hoang Phuc</p>
                        <p>Student Code : SE130193</p>
                        <p>Class : SE10D</p>
                    </div>
                </div>
                <div class="payment-methods">
                    <p class="footer-title">Payment Methods</p>   
                    <div class="block-text" >
                        <div class="line-img">
                            <img src="https://frontend.tikicdn.com/_desktop-next/static/img/footer/visa.svg"/>
                            <img src="https://frontend.tikicdn.com/_desktop-next/static/img/footer/mastercard.svg"/>
                            <img src="https://frontend.tikicdn.com/_desktop-next/static/img/footer/jcb.svg"/>
                        </div>
                        <div class="line-img">
                            <img src="https://frontend.tikicdn.com/_desktop-next/static/img/footer/cash.svg"/>
                            <img src="https://frontend.tikicdn.com/_desktop-next/static/img/footer/internet-banking.svg"/>
                            <img src="https://frontend.tikicdn.com/_desktop-next/static/img/footer/installment.svg"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>