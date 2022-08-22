<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/form-style.css">
    <link type="text/css" rel="stylesheet" href="css/fonts.css">
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">--%>

    <title>Log in</title>
</head>
<body>
<div class="page">
    <form class="form" action="SignUp" method="post" novalidate>
        <div class="form_group">
            <input type="email" class="input email-input field" name="email" placeholder="Email">
        </div>
        <div class="form_group">
            <input type="password" class="input password-input field" name="password" placeholder="Password" >
        </div>
        <button class="btn" type="submit">
            Sign in
        </button>
        <div class="container reg">
            <p class="er">${sessionScope.error1}</p>
            <p><a href="logIn.jsp">Do not have an account ?</a></p>
            <p><a href="">Forgot password ?</a></p>
        </div>
    </form>
</div>
<script src='js/validation/validSign.js'></script>
</body>
</html>
