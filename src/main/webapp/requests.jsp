<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Requests</title>
    <%@ include file="/tags/bootstrap.jspf" %>
    <%@ include file="/tags/nav.jspf" %>
</head>
<body>
<table class="table table-hover m-3 table table-sm">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">First name</th>
        <th scope="col">Second name</th>
        <th scope="col">Login</th>
        <th scope="col">Phone</th>
        <th scope="col">Balance</th>
        <th scope="col">Role</th>
        <th scope="col">Status of card</th>
        <th scope="col">Request to unblock card</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="list" items="${sessionScope.requestList}">
        <tr>
            <td><c:out value="${list.getUserID()}"/></td>
            <td><c:out value="${list.getFirstName()}"/></td>
            <td><c:out value="${list.getSecondName()}"/></td>
            <td><c:out value="${list.getLogin()}"/></td>
            <td><c:out value="${list.getPhone()}"/></td>
            <td><c:out value="${list.getBalance()}"/> ₴</td>
            <td><c:out value="${list.getRole()}"/></td>
            <td><c:out value="${list.getStatusOfCard()}"/></td>
            <c:if test="${list.getStatusOfCard() eq 'Prepare'}">
                <td><a class="btn btn-warning" href="StatusOfCard?status=Active&id=${list.getUserID()}">Prepare</a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
