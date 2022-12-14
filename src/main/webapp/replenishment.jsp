
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/form-style.css">
    <%@ include file="/tags/bootstrap.jspf" %>
    <%@ include file="/tags/nav.jspf" %>
    <title>Replenishment</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>
<div class="d-grid gap-2 col-2 mx-auto m-4">
    <div class="container-fluid m-3 row gy-2">
        <p class="fst-normal fs-3 "><fmt:message key="card.balance"/>: <c:out value="${sessionScope.cards.get(sessionScope.cardNumber-1).getBalance()}"/> ₴</p>
        <p class="fst-normal fs-5 "><c:out value="${sessionScope.cards.get(sessionScope.cardNumber-1).getNumberOfCard()}"/></p>

    </div>
    <div class="form" >
        <form action="Replenishment" method="post" novalidate>
            <div class="mb-3">
                <label class="form-label"><fmt:message key="amount"/> </label>
                <input type="number" class="input form-control amount-input field" name="amount" id="exampleInput" min="1" step="0.5">
            </div>
            <button class="btn btn-primary d-grid gap-2 mt-3" type="submit"><fmt:message key="submit"/></button>
        </form>
    </div>
</div>


<script src='js/validation/validReplenishment.js'></script>
</body>
</html>
