<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="/tags/bootstrap.jspf" %>
    <%@ include file="/tags/nav.jspf" %>
    <title>Account information</title>
</head>
<body>

<div class="container-fluid m-3 row gy-2">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="resource"/>
    <p class="fst-normal fs-3   "><fmt:message key="personal.customer.information"/></p>

    <div class="fs-2 row-cols-lg-3 ">
        <ol class="list-group   ">
            <li class="list-group-item fs-4">1. <fmt:message key="name"/>: <c:out value="${sessionScope.customer.getFirstName()}"/> </li>
            <li class="list-group-item fs-4">2. <fmt:message key="second.name"/>: <c:out value="${sessionScope.customer.getSecondName()}"/></li>
            <li class="list-group-item fs-4">3. <fmt:message key="login"/>: <c:out value="${sessionScope.customer.getLogin()}"/></li>
            <li class="list-group-item fs-4">4. <fmt:message key="phone"/>: <c:out value="${sessionScope.customer.getPhone()}"/></li>
            <li class="list-group-item fs-4">5. <fmt:message key="role"/>: <fmt:message key="${sessionScope.role}"/></li>
            <li class="list-group-item fs-4">6. <fmt:message key="cards"/>:</li>
            <c:forEach var="card" items="${sessionScope.cards}" >
                <c:if test="${card.getStatus() ne 'Active'}">
                    <li class="list-group-item fs-4">  <c:out value="${card.getNumberOfCard()}"/> <fmt:message key="Blocked"/> </li>
                </c:if>
                <c:if test="${card.getStatus() eq 'Active'}">
                    <li class="list-group-item fs-4">  <c:out value="${card.getNumberOfCard()}"/> <fmt:message key="Active"/></a></li>
                </c:if>
                <c:if test="${card.getStatus() eq 'Prepare'}">
                    <li class="list-group-item fs-4">  <c:out value="${card.getNumberOfCard()}"/> <fmt:message key="Prepare"/> </a></li>
                </c:if>
            </c:forEach>
        </ol>
    </div>
</div>

</body>
</html>
