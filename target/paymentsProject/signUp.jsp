<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <%@ include file="/tags/bootstrap.jspf" %>
    <link type="text/css" rel="stylesheet" href="css/form-style.css">

    <title>Log in</title>
</head>
<body>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="resource"/>
<div class="page">
    <form class="form" action="SignUp" method="post" novalidate>
        <div class="form_group m-2 nav justify-content-center">
            <p class="display-6"><fmt:message key="log.in"/></p>
        </div>
        <div class="form_group">
            <input type="email" class="input email-input field form-control" name="email" placeholder="<fmt:message key="email"/>">
        </div>
        <div class="form_group">
            <input type="password" class="input password-input field form-control" name="password" placeholder="<fmt:message key="password"/>" >
        </div>
        <button class="btn  btn btn-primary " type="submit">
            <fmt:message key="log.in"/>
        </button>
        <small>
            <div class="m-1" >
                <c:if test="${sessionScope.error1 != null}">
                    <p class="er"><fmt:message key="${sessionScope.error1}"/></p>
                </c:if>
                <a href="logIn.jsp" class="card-link text-decoration-none"><fmt:message key="forgot.password.do.no.have.acc"/></a>
                <p><a href="forgotPass.jsp" class="card-link text-decoration-none size"><fmt:message key="forgot.password"/></a></p>
            </div>
        </small>

    </form>
</div>
<script src='js/validation/validSign.js'></script>
</body>
</html>