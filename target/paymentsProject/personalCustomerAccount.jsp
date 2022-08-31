<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>Personal customer account</title>
    <%@ include file="/tags/bootstrap.jspf" %>
    <%@ include file="/tags/navbarHomePage.jspf" %>

</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>
<footer class="py-5 bg-dark">
    <div class="container"><p class="m-0 text-center text-white"> <fmt:message key="welcome"/> </p></div>
</footer>
</body>
</html>
