<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="css/form-style.css">

    <title>Forgot</title>
</head>
<body>
<div class="page">
    <form class="form" action="ForgotPass" method="post" novalidate>
        <div class="form_group m-2 nav justify-content-center">
            <p class="display-6">Set Password</p>
        </div>
        <div class="form_group">
            <input type="email" class="input email-input field form-control" name="email" placeholder="Email">
        </div>
        <div class="form_group">
            <input type="password" class="input password-input field form-control" name="password" placeholder="Password" >
        </div>
        <div class="form_group">
            <input type="password" class="input passwordReap-input field form-control" name="passwordReap" placeholder="Password" >
        </div>
        <button class="btn  btn btn-primary " type="submit">
            Set
        </button>
        <small>
            <div class="m-1" >
                <p class="er">${sessionScope.error}</p>
                <a href="logIn.jsp" class="card-link text-decoration-none">Do not have an account ?</a>
            </div>
        </small>

    </form>
</div>
<script src='js/validation/validForgotPass.js'></script>
</body>
</html>