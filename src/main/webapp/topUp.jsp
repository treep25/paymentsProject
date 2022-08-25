<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/form-style.css">
    <%@ include file="/tags/bootstrap.jspf" %>
    <%@ include file="/tags/nav.jspf" %>
    <title>Send</title>
</head>
<body>

<div class="d-grid gap-2 col-2 mx-auto m-4">
    <div class="form" >
        <form action="ReplenishmentToCustomer" method="post" novalidate>
            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Customer id</label>
                <input type="number" class="input form-control id-input field" id="exampleInputEmail1" name="recipient" aria-describedby="emailHelp">
            </div>
            <div class="mb-3">
                <label class="form-label">Amount </label>
                <input type="number" class="input form-control amount-input field" name="amount" id="exampleInput" min="1" step="0.5">
            </div>
            <p class="er"><c:out value="${sessionScope.error}"/></p>
            <button class="btn btn-primary d-grid gap-2 mt-3" type="submit">Submit</button>
        </form>
    </div>
</div>

</body>
<script src='js/validation/validReplenishment.js'></script>
</html>
