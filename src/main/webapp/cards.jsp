<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/form-style.css">
    <%@ include file="/tags/bootstrap.jspf" %>
    <%@ include file="/tags/nav.jspf" %>
    <title>Cards</title>
</head>
<body>


    <div class="d-grid gap-2 col-2 mx-auto m-4">
        <div class="container-fluid m-3 row gy-2">
            <p class="fst-normal fs-3 ">Card balance: <c:out value="${sessionScope.card.getBalance()}"/> ₴</p>
        </div>
        <div class="form" >
            <form>
                <a class="btn btn-primary d-grid gap-2 mt-3" href="send.jsp" role="button">Send</a>
                <a class="btn btn-primary d-grid gap-2 mt-3" href="replenishment.jsp" role="button">Reestablish</a>
            </form>
        </div>
    </div>


</body>
</html>