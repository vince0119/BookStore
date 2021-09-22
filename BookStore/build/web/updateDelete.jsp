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
        <link rel="stylesheet" href="css/updateDelete.css" />
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
                                <a href="createVoucher.jsp">Create Voucher</a>
                            </div>
                            <div class="link-block-two" >
                                <a href="createBook.jsp" >Create New Book</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="content" >
                    <form class="header-search" action="DispatchServlet" method="POST" >
                        <c:if test="${not empty searchValue}" >
                            <input class="search_box" type="text" 
                                   name="txtSearchProduct" value="${searchValue}" />
                        </c:if>
                        <c:if test="${empty searchValue}" >
                            <input class="search_box" type="text" 
                                   name="txtSearchProduct" value="" 
                                   placeholder="Search Product Here ... "/>
                        </c:if>
                        <input class="search_button"  type="submit" 
                               value="Search" name="btnAction" />
                        <input type="hidden" name="txtCheckPage" value="fromUpdateAndDelete" />
                    </form>
                    <c:if test="${visitPage != 1}" >
                        <div class="products-search" >
                            <div class="products-searchList" >
                                <c:set var="searchList" value="${sessionScope.SEARCH_LIST}"/>
                                <c:if test="${not empty searchList}" >
                                    <c:forEach var="book" items="${searchList}">
                                        <form class="product-card"  
                                              action="DispatchServlet" 
                                              method="POST">
                                            <img src="${book.image}"/>
                                            <h2>
                                                ${book.title}
                                                <input type="hidden" name="txtTitle" 
                                                       value="${book.title}" />
                                            </h2>
                                            <p class="product-price">Price : ${book.price}$</p>
                                            <p>
                                                <input class="select-product-btn" 
                                                       type="submit" value="Select"
                                                       name="btnAction" />
                                            </p>
                                        </form>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty searchList}">
                                    <div class="searchList-not-found" >
                                        <p>Sorry ! We can not find products suitable for your request !</p>
                                        <p>Suggestion for you :</p>
                                        <ul>
                                            <li>Please make sure all words are spelled correctly</li>
                                            <li>Try other keywords</li>
                                            <li>Try more general keywords</li>
                                        </ul>
                                    </div>
                                </c:if>
                            </div>
                        </div>
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
