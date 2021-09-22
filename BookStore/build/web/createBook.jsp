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
        <link rel="stylesheet" href="css/dashboardStyles.css" />
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER_DTO}" />
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
                                <a href="updateDelete.jsp" >Update / Delete Books</a>
                            </div>
                        </div>
                    </div>
                </div>
                <form class="content" action="DispatchServlet" method="POST">
                    <div class="content-text" >Create New Book</div>
                    <div class="content-form" >
                        <div class="content-form-left">
                            <div class="block-content">
                                <p>Title</p>
                                <input type="text" name="txtTitle" value="" />
                            </div>
                            <div class="block-content">
                                <p>Image</p>
                                <input type="text" name="txtImage" value="" />
                            </div>
                            <div class="block-content">
                                <p>Description</p>
                                <input type="text" name="txtDescription" value="" />
                            </div>
                            <div class="block-content">
                                <p>Price</p>
                                <input type="text" name="txtPrice" value="" />
                            </div>
                        </div>
                        <div class="content-form-right" >
                            <div class="block-content">
                                <p>Author</p>
                                <input type="text" name="txtAuthor" value="" />
                            </div>
                            <div class="block-content">
                                <p>Quantity</p>
                                <input type="text" name="txtQuantity" value="" />
                            </div>
                            <div class="block-content">
                                <p>Category Name</p>
                                <div class="block-content-category">
                                    <select name="txtCategoryName">
                                        <c:set var="categories" value="${sessionScope.CATEGORY_LIST}" />
                                        <c:forEach var="category" items="${categories}">
                                            <option>${category.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <input class="btn-create-new-book" type="submit" value="Create New Book" name="btnAction" />
                    <c:set var="createMessage" value="${sessionScope.CRUD_MESSAGE}" />
                    <c:if test="${not empty createMessage}" >
                        <p class="error-msg">${createMessage}</p>
                    </c:if>
                </form>
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
