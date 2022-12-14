<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Requests</title>
    <%@ include file="/tags/bootstrap.jspf" %>
    <%@ include file="/tags/nav.jspf" %>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>
<table class="table table-hover m-3 table table-sm">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="Id"/></th>
        <th scope="col"><fmt:message key="name"/></th>
        <th scope="col"><fmt:message key="second.name"/></th>
        <th scope="col"><fmt:message key="role"/></th>
        <th scope="col"><fmt:message key="login"/></th>
        <th scope="col"><fmt:message key="phone"/></th>
        <th scope="col"><fmt:message key="card.number"/></th>
        <th scope="col"><fmt:message key="card.balance"/></th>
        <th scope="col"><fmt:message key="status.of.card"/></th>
        <th scope="col"><fmt:message key="request.to.unblock.card"/></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="list" items="${sessionScope.requestList}">
        <tr>
            <td><c:out value="${list.getUserID()}"/></td>
            <td><c:out value="${list.getFirstName()}"/></td>
            <td><c:out value="${list.getSecondName()}"/></td>
            <td><fmt:message key="${list.getRole()}"/></td>
            <td><c:out value="${list.getLogin()}"/></td>
            <td><c:out value="${list.getPhone()}"/></td>
            <td><c:out value="${list.getCardNum()}"/></td>
            <td><c:out value="${list.getBalance()}"/> ₴</td>
            <td><fmt:message key="${list.getStatusOfCard()}"/></td>
            <c:if test="${list.getStatusOfCard() eq 'Prepare'}">
                <td><a class="btn btn-warning" href="StatusOfCard?status=Active&id=${list.getCardNum()}"><fmt:message key="Prepare"/></a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
