<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">

    <%@ include file="/tags/bootstrap.jspf" %>

    <title>Payments page</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand " href="mainPage.jsp" >Payments <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" width="24" height="24" fill="currentColor" >
            <path d="M512 32C547.3 32 576 60.65 576 96V128H0V96C0 60.65 28.65 32 64 32H512zM576 416C576 451.3 547.3 480 512 480H64C28.65 480 0 451.3 0 416V224H576V416zM112 352C103.2 352 96 359.2 96 368C96 376.8 103.2 384 112 384H176C184.8 384 192 376.8 192 368C192 359.2 184.8 352 176 352H112zM240 384H368C376.8 384 384 376.8 384 368C384 359.2 376.8 352 368 352H240C231.2 352 224 359.2 224 368C224 376.8 231.2 384 240 384z"></path>
        </svg></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item disabled" tabindex="-1" aria-disabled="true"><a class="nav-link active" aria-current="page">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="aboutUs.jsp">About</a></li>
            </ul>
            <a class="btn btn-primary m-2" href="logIn.jsp">Sign Up</a>
            <a class="btn" href="signUp.jsp">Log In</a>
        </div>
    </div>
</nav>
<header class="bg-dark py-5">
    <div class="container px-4 px-lg-5 my-5">
        <div class="text-center text-white">
            <h1 class="display-4 fw-bolder">Payments</h1>
            <p class="lead fw-normal text-white-50 mb-0">Create your personal account</p>
        </div>

    </div>
    <div class="d-grid gap-3 d-sm-flex justify-content-sm-center">
        <a class="btn btn-primary btn-lg px-4 me-sm-3" href="logIn.jsp">Get Started</a>
    </div>
</header>
<section class="py-5">
    <div class="container px-4 px-lg-5 my-5">
        <div class="row gx-4 gx-lg-5 align-items-center">
            <div class="col-md-15"><img class="card-img-top mb-5 mb-md-0" src="font/online-payment-for-small-business-1000x563.jpg" alt="..." /></div>
        </div>
    </div>
</section>

<footer class="py-5 bg-dark">
    <div class="container"><p class="m-0 text-center text-white"> Welcome </p></div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>
</html>
