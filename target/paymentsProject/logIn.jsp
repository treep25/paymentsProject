<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/form-style.css">
    <%@ include file="/tags/bootstrap.jspf" %>
    <link rel="shortcut icon" href="#">
    <title>Registration</title>
</head>
<body>
<div class="page">
    <form class="form" name="formReg" action="AddNewCustomer" method="post" novalidate>
        <div class="form_group m-2 nav justify-content-center">
            <p class="display-6">Sign Up</p>
        </div>
        <div class="form_group">
            <input type="text" class="input name-input form-control field" name="name" placeholder="Name">
        </div>
        <div class="form_group">
            <input type="text" class="input secName-input form-control field" name="secName" placeholder="Second name">
        </div>
        <div class="form_group">
            <input type="text" class="input email-input form-control field" name="email" placeholder="Email">
        </div>
        <div class="form_group">
            <input type="text" class="input phone-input form-control field" name="phone" placeholder="Phone">
        </div>
        <div class="form_group">
            <input type="password" class="input password-input form-control field" name="password" placeholder="Password">
        </div>
        <div class="form_group">
            <input type="password" class="input passwordRepeat-input field   form-control" name="passwordRepeat"
                   placeholder="Password">
        </div>
        <div class="form_group">
            <div class="form-check">
                <small>
                    <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                    <label class="form-check-label" for="flexCheckDefault">
                        Accept privacy police
                    </label>
                </small>
            </div>
        </div>
        <button class="btn btn btn-primary " type="submit">
            Sign up
        </button>
        <small>
            <div class="m-1" >
                <p class="er">${sessionScope.error}</p>
                <p class="er">${sessionScope.validationError}</p>
                <a href="signUp.jsp" class="card-link text-decoration-none size">Already have an account?</a>
            </div>
        </small>
    </form>
</div>
<script src='js/validation/validReg.js'></script>
</body>
</html>