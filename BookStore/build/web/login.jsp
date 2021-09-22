<%-- 
    Document   : login
    Created on : May 13, 2021, 8:38:33 AM
    Author     : phuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/loginStyles.css" >
        <title>JSP Page</title>
    </head>
    <body>
        <form class="login-box" action="DispatchServlet" method="POST">
            <h1>Login</h1>
            <div class="text-box">
                <input type="text" name="txtUsername" value="" placeholder="Username" /><br>
            </div>
            <div class="text-box" >
                <input type="password" name="txtPassword" value="" placeholder="Password" /><br>
            </div>
            <input class="btn-Login" type="submit" value="Login" name="btnAction" />
            <c:set var="error" value="${sessionScope.LOGIN_ERROR}"/>
            <c:if test="${not empty error}" >
                <p style="color: red">${error}</p>
            </c:if>
        </form>
    </body>
</html>
