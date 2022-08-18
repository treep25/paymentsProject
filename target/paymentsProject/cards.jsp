<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <title>Cards</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light ">
    <div class="container-fluid">
        <a class="navbar-brand m-3"  >Payments <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" width="24" height="24" fill="currentColor" >
            <path d="M512 32C547.3 32 576 60.65 576 96V128H0V96C0 60.65 28.65 32 64 32H512zM576 416C576 451.3 547.3 480 512 480H64C28.65 480 0 451.3 0 416V224H576V416zM112 352C103.2 352 96 359.2 96 368C96 376.8 103.2 384 112 384H176C184.8 384 192 376.8 192 368C192 359.2 184.8 352 176 352H112zM240 384H368C376.8 384 384 376.8 384 368C384 359.2 376.8 352 368 352H240C231.2 352 224 359.2 224 368C224 376.8 231.2 384 240 384z"></path>
        </svg></a>

        <ul class="navbar-nav me-auto mb-3 mb-lg-0 p-md-3">
            <li class="nav-item ">
                <a class="nav-link active"  aria-current="page" href="personalCustomerAccount.jsp"  >Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="aboutUs.jsp">About Us</a>
            </li>
        </ul>
        <ul class="nav nav-tabs gx-md-5">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Account</a>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="accountInfo.jsp">Account info</a></li>
                    <li><a class="dropdown-item" href="cards.jsp">Cards</a></li>
                    <li><a class="dropdown-item" href="#">Payments</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="LogOut" methods="doPost">Log out</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>

<div class="container-fluid m-3 row gy-2">
    <p class="fst-normal fs-3   ">Card balance</p>
    <p class="fst-normal fs-4"><c:out value="${sessionScope.card.getBalance()}"/> $</p>
</div>
<div class="d-grid gap-2 col-2 mx-auto">
    <a class="btn btn-primary" href="send.jsp" role="button">Send</a>
    <a class="btn btn-primary" href="replenishment.jsp" role="button">Reestablish</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>
</html>