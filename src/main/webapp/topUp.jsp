<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/form-style.css">
    <%@ include file="/tags/bootstrap.jspf" %>
    <%@ include file="/tags/nav.jspf" %>
    <title>Send</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>
<div class="d-grid gap-2 col-2 mx-auto m-4">
    <div class="form" >
        <form action="ReplenishmentToCustomer" method="post" novalidate>
            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label"><fmt:message key="recipient.card"/></label>
                <input type="text" class="input form-control id-input field" id="exampleInputEmail1" name="recipient" aria-describedby="emailHelp">
            </div>
            <div class="mb-3">
                <label class="form-label"><fmt:message key="amount"/> </label>
                <input type="number" class="input form-control amount-input field" name="amount" id="exampleInput" min="1" step="0.5">
            </div>
            <c:if test="${sessionScope.error != null}">
                <p class="er"><fmt:message key="${sessionScope.error}"/></p>
            </c:if>
            <button class="btn btn-primary d-grid gap-2 mt-3" type="submit"><fmt:message key="submit"/></button>
        </form>
    </div>
</div>

</body>
<script src='js/validation/validReplenishment.js'></script>
</html>
