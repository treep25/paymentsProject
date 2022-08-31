<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/form-style.css">
    <%@ include file="/tags/bootstrap.jspf" %>
    <link rel="shortcut icon" href="#">
    <title>Registration</title>
</head>
<body>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="resource"/>
<div class="page">
    <form class="form" name="formReg" action="AddNewCustomer" method="post" novalidate>
        <div class="form_group m-2 nav justify-content-center">
            <p class="display-6"><fmt:message key="sign.up"/></p>
        </div>
        <div class="form_group">
            <input type="text" class="input name-input form-control field" name="name" placeholder="<fmt:message key="name"/>">
        </div>
        <div class="form_group">
            <input type="text" class="input secName-input form-control field" name="secName" placeholder="<fmt:message key="second.name"/>">
        </div>
        <div class="form_group">
            <input type="text" class="input email-input form-control field" name="email" placeholder="<fmt:message key="email"/>">
        </div>
        <div class="form_group">
            <input type="text" class="input phone-input form-control field" name="phone" placeholder="<fmt:message key="phone"/>">
        </div>
        <div class="form_group">
            <input type="password" class="input password-input form-control field" name="password" placeholder="<fmt:message key="password"/>">
        </div>
        <div class="form_group">
            <input type="password" class="input passwordRepeat-input field   form-control" name="passwordRepeat"
                   placeholder="<fmt:message key="passwordRepeat"/>">
        </div>
        <div class="form_group">
            <div class="form-check">
                <small>
                    <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                    <label class="form-check-label" for="flexCheckDefault">
                        <fmt:message key="Accept.privacy.police"/>
                    </label>
                </small>
            </div>
        </div>
        <button class="btn btn btn-primary " type="submit">
            <fmt:message key="sign.up"/>
        </button>
        <small>
            <div class="m-1" >
                <c:if test="${sessionScope.error != null}">
                    <p class="er"><fmt:message key="error"/></p>
                </c:if>
                <c:if test="${sessionScope.validationError != null}">
                    <p class="er"><fmt:message key="validationError"/></p>
                </c:if>
                <a href="signUp.jsp" class="card-link text-decoration-none size"><fmt:message key="already.have.an.account"/></a>
            </div>
        </small>
    </form>
</div>
<script src='js/validation/validReg.js'></script>
</body>
</html>