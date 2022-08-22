<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/form-style.css">
    <link type="text/css" rel="stylesheet" href="css/fonts.css">
    <title>Registration</title>
</head>
<body>

<div class="page">
    <form class="form" name="formReg" action="AddNewCustomer" method="post" novalidate>
        <h1 class="w">Sign Up</h1>
        <div class="form_group">
            <input type="text" class="input name-input field" name="name" placeholder="Name">
        </div>
        <div class="form_group">
            <input type="text" class="input secName-input field" name="secName" placeholder="Second name">
        </div>
        <div class="form_group">
            <input type="email" class="input email-input field" name="email" placeholder="Email">
        </div>
        <div class="form_group">
            <input type="tel" class="input phone-input field" name="phone" placeholder="Phone">
        </div>
        <div class="form_group">
            <input type="password" class="input password-input field" name="password" placeholder="Password">
        </div>
        <div class="form_group">
            <input type="password" class="input passwordRepeat-input field" name="passwordRepeat"
                   placeholder="Password">
        </div>
        <div class="form_group">
            <input type="checkbox" class="checkbox" id="privacy_check">
            <label class="checkbox_label" for="privacy_check">Accept privacy policy</label>
        </div>
        <button class="btn" type="submit">
            Submit
        </button>
        <p class="er">${sessionScope.error}</p>
        <p class="er">${sessionScope.validationError}</p>
        <div class="container signin">
            <p><a href="signUp.jsp">Already have an account?</a></p>
        </div>
    </form>
</div>
<script src='js/validation/validReg.js'></script>
</body>
</html>
