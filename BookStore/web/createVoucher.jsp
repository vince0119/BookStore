<%-- 
    Document   : dashboard
    Created on : May 15, 2021, 12:31:22 PM
    Author     : phuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/createVoucherStyles.css" />
    </head>
    <body>

        <c:set var="visitPage" value="${sessionScope.VISIT_UPDATE_DELETE_TIME}"/>
        <c:if test="${ empty visitPage}" >
            <c:set var="visitPage" value="1" />
        </c:if>
        <c:set var="isLoginned" value="${sessionScope.IS_LOGINNED}"/>
        <c:set var="user" value="${sessionScope.USER_DTO}" />
        <c:set var="searchValue" value="${param.txtSearchProduct}" />
        <div class="header">
            <p>Dash Board</p>
        </div>
        <div class="main" >
            <div class="container" >
                <div class="slide-bar">
                    <div class="slide-bar-one" >
                        <div class="slide-bar-text">
                            <p style="color: #0D5BC6" >Welcome</p>
                        </div>
                        <div class="slide-bar-content-welcome">
                            <small>Account : </small>
                            <p>${user.fullname}</p>
                        </div>
                        <form action="DispatchServlet" method="POST">
                            <input type="submit" value="Logout" name="btnAction" />
                        </form>
                    </div>
                    <div class="slide-bar-two">
                        <div class="slide-bar-text">
                            <p style="color: #0D5BC6" >Actions</p>
                        </div>
                        <div class="slide-bar-content">
                            <div class="link-block-one" >
                                <a href="createBook.jsp">Create Book</a>
                            </div>
                            <div class="link-block-two" >
                                <a href="updateDelete.jsp" >Update / Delete Books</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="content" >
                    <form class="create-voucher-form" action="DispatchServlet" method="POST" >
                        <div class="line-one">
                            <div class="block-content">
                                <p>Title </p>
                                <input type="text" name="txtDiscountTitle" value="" />
                            </div>
                            <div class="block-content">
                                <p>Discount Percent</p>
                                <input type="text" name="txtDiscountPercent" value="" />
                            </div>
                            <div class="block-content">
                                <p>Valid Date</p>
                                <div class="time" >
                                    <select name="txtDay">
                                        <option selected="selected">Day</option>
                                        <c:forEach begin="1" end="31" varStatus="counter">
                                            <option>${counter.count}</option>
                                        </c:forEach>
                                    </select>
                                    <select name="txtMonth">
                                        <option selected="selected">Month</option>
                                        <c:forEach begin="1" end="12" varStatus="counter">
                                            <option>${counter.count}</option>
                                        </c:forEach>
                                    </select>
                                    <select name="txtYear">
                                        <option selected="selected">Year</option>
                                        <c:forEach begin="1" end="3" varStatus="counter">
                                            <option>${counter.count + 2020}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <input class="line-two" type="submit" value="Create Voucher" name="btnAction" />
                    </form>
                    <c:set var="msg" value="${sessionScope.CREATE_VOUCHER_MSG}"/>
                    <div class="message" >
                        <p>${msg}</p>
                    </div>
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
