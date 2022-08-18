<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Your payments</title>
</head>
<body>
    <c:forEach var="list" items="${sessionScope.paymentList}">
        <p class="fw-bold fs-4">Date: <c:out value="${list.getDate()}"/> </p>
        <p class="fw-bold fs-4">Status: <c:out value="${list.getPaymentStatus()}"/> </p>


    </c:forEach>


</body>
</html>
