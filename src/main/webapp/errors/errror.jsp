<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = pageContext.getException().getMessage();
    String exception = pageContext.getException().getClass().toString();
%>
<html>
<head>
    <title>Exception</title>
</head>
<body>
<h2>Exception occurred while processing the request</h2>
<p>Type: <%= exception%></p>
</body>
</html>
