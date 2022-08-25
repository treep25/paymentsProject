<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <%@ include file="/tags/bootstrap.jspf" %>
    <link type="text/css" rel="stylesheet" href="css/form-style.css">

    <title>Log in</title>
</head>
<body>
<div class="page">
    <form class="form" action="SignUp" method="post" novalidate>
        <div class="form_group m-2 nav justify-content-center">
            <p class="display-6">Log In</p>
        </div>
        <div class="form_group">
            <input type="email" class="input email-input field form-control" name="email" placeholder="Email">
        </div>
        <div class="form_group">
            <input type="password" class="input password-input field form-control" name="password" placeholder="Password" >
        </div>
        <button class="btn  btn btn-primary " type="submit">
            Log in
        </button>
        <small>
            <div class="m-1" >
                <p class="er">${sessionScope.error1}</p>
                <a href="logIn.jsp" class="card-link text-decoration-none">Do not have an account ?</a>
                <p><a href="forgotPass.jsp" class="card-link text-decoration-none size">Forgot password ?</a></p>
            </div>
        </small>

    </form>
</div>
<script src='js/validation/validSign.js'></script>
</body>
</html>