<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">

    <%@ include file="/tags/bootstrap.jspf" %>

    <title>Payments</title>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light ">
    <div class="container-fluid ms-4">
        <a class="navbar-brand m-3" href="mainPage.jsp" >Payments <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" width="24" height="24" fill="currentColor" >
            <path d="M512 32C547.3 32 576 60.65 576 96V128H0V96C0 60.65 28.65 32 64 32H512zM576 416C576 451.3 547.3 480 512 480H64C28.65 480 0 451.3 0 416V224H576V416zM112 352C103.2 352 96 359.2 96 368C96 376.8 103.2 384 112 384H176C184.8 384 192 376.8 192 368C192 359.2 184.8 352 176 352H112zM240 384H368C376.8 384 384 376.8 384 368C384 359.2 376.8 352 368 352H240C231.2 352 224 359.2 224 368C224 376.8 231.2 384 240 384z"></path>
        </svg></a>
        <ul class="navbar-nav me-auto mb-3 mb-lg-0 p-md-3 ms-4 ">
            <li class="nav-item ">
                <a class="nav-link active btn-lg disabled"  aria-current="page" aria-disabled="true" tabindex="-1"  >Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="aboutUs.jsp">About Us</a>
            </li>
        </ul>
        <ul class="nav nav-pills p-md-4">
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="logIn.jsp">Sign Up</a>
            </li>
            <li>
                <button class="btn" type="submit" onclick="window.location.href = 'signUp.jsp';"> Log In</button>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>
