<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/tags/bootstrap.jspf" %>
    <%@ include file="/tags/nav.jspf" %>
    <title>Title</title>
</head>
<body>
<header class="bg-dark py-5">
    <div class="container px-4 px-lg-5 my-5">
        <div class="text-center text-white">
            <h1 class="display-4 fw-bolder"><fmt:message key="payments"/></h1>
            <p class="lead fw-normal text-white-50 mb-0"><fmt:message key="create.your.personal.account"/></p>
        </div>

    </div>
</header>
<section id="about">
    <div class="container px-4">
        <div class="row gx-4 justify-content-center">
            <div class="col-lg-8">
                <h2><fmt:message key="about.us"/></h2>
                <p class="lead"><fmt:message key="Project.Payments"/></p>
                <ul>
                    <li><fmt:message key="E-mail"/></li>
                    <li><fmt:message key="Country"/></li>
                    <li><fmt:message key="Phone"/></li>
                    <li><fmt:message key="Address"/></li>
                </ul>
            </div>
        </div>
    </div>
</section>
<footer class="py-5 bg-dark">
    <div class="container"><p class="m-0 text-center text-white"> <fmt:message key="welcome"/> </p></div>
</footer>
</body>
</html>
