<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="css/form-style.css">
    
    <title>Forgot</title>
</head>
<body>

<fmt:setLocale value="en"/>
<fmt:setBundle basename="resource"/>
<div class="page">
    <form class="form" action="ForgotPass" method="post" novalidate>
        <div class="form_group m-2 nav justify-content-center">
            <p class="display-6"><fmt:message key="forgotPass.set.password"/></p>
        </div>
        <div class="form_group">
            <input type="email" class="input email-input field form-control" name="email" placeholder="<fmt:message key="email"/>">
        </div>
        <div class="form_group">
            <input type="password" class="input password-input field form-control" name="password" placeholder="<fmt:message key="password"/>" >
        </div>
        <div class="form_group">
            <input type="password" class="input passwordReap-input field form-control" name="passwordReap" placeholder="<fmt:message key="passwordRepeat"/>" >
        </div>
        <button class="btn  btn btn-primary " type="submit">
            <fmt:message key="forgot.password.btn"/>
        </button>
        <small>
            <div class="m-1" >
                <c:if test="${sessionScope.errorForgotPass != null}">
                    <p class="er"><fmt:message key="${sessionScope.errorForgotPass}" /></p>
                </c:if>
                <a href="logIn.jsp" class="card-link text-decoration-none"><fmt:message key="forgot.password.do.no.have.acc"/></a>
            </div>
        </small>
    </form>
</div>
<script src='js/validation/validForgotPass.js'></script>
</body>
</html>