<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/form-style.css">
    <%@ include file="/tags/bootstrap.jspf" %>
    <%@ include file="/tags/nav.jspf" %>
    <title>Remove Customer</title>
</head>
<body>
<div class="d-grid gap-2 col-2 mx-auto m-4">
    <div class="container-fluid m-3 row gy-2">
        <p class="fst-normal fs-3 ">Block user`s card</p>
    </div>
    <div class="form" >
        <form action="DeleteCustomer" method="post" novalidate>
            <div class="mb-3">
                <label class="form-label">Customer id </label>
                <input type="number" class="input form-control amount-input field" name="id" id="exampleInput" min="1" step="0.5">
            </div>
            <button class="btn btn-danger d-grid gap-2 mt-3"  type="submit">Submit</button>
        </form>
    </div>
</div>

<script src='js/validation/validReplenishment.js'></script>
</body>
</html>
