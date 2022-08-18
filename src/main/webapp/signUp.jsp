<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/form-style.css">
    <link type="text/css" rel="stylesheet" href="css/fonts.css">
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
            <p><a href="logIn.jsp">Do not have an account ?</a></p>
            <p><a href="">Forgot password ?</a></p>
        </div>
    </form>
</div>
<script src='js/validation/validSign.js'></script>
</body>
</html>
