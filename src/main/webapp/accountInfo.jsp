<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="/tags/bootstrap.jspf" %>
    <%@ include file="/tags/nav.jspf" %>
    <title>Account information</title>
</head>
<body>

<div class="container-fluid m-3 row gy-2">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="resource"/>
    <p class="fst-normal fs-3   "><fmt:message key="personal.customer.information"/></p>

    <div class="fs-2 row-cols-lg-3 ">
        <ol class="list-group   ">
            <li class="list-group-item fs-4">1. <fmt:message key="name"/>: <c:out value="${sessionScope.customer.getFirstName()}"/> </li>
            <li class="list-group-item fs-4">2. <fmt:message key="second.name"/>: <c:out value="${sessionScope.customer.getSecondName()}"/></li>
            <li class="list-group-item fs-4">3. <fmt:message key="login"/>: <c:out value="${sessionScope.customer.getLogin()}"/></li>
            <li class="list-group-item fs-4">4. <fmt:message key="phone"/>: <c:out value="${sessionScope.customer.getPhone()}"/></li>
            <li class="list-group-item fs-4">5. <fmt:message key="role"/>: <fmt:message key="${sessionScope.role}"/></li>
            <li class="list-group-item fs-4">6. <fmt:message key="cards"/>:</li>
            <c:if test="${sessionScope.role eq 'Admin'}">
                <c:forEach var="card" items="${sessionScope.cards}">
                    <li class="list-group-item fs-4">  <c:out value="${card.getNumberOfCard()}"/> <fmt:message key="Active"/></a></li>
                </c:forEach>
            </c:if>
            <c:if test="${sessionScope.role ne 'Admin'}">
            <c:forEach var="card" items="${sessionScope.cards}" >
                <c:if test="${card.getStatus() eq 'Active'}">
                    <li class="list-group-item fs-4">  <c:out value="${card.getNumberOfCard()}"/><a class="btn btn-success" href="StatusOfCard?status=Blocked&id=${card.getNumberOfCard()}"> <fmt:message key="Active"/>  </a></a></li>
                </c:if>
                <c:if test="${card.getStatus() eq 'Blocked' || sessionScope.cards.get(0).getStatus() eq 'Prepare' }">
                    <li class="list-group-item fs-4">  <c:out value="${card.getNumberOfCard()}"/>
                        <div class=" fs-5">
                            <fmt:message key="Blocked"/>
                        </div>
                        <a class="btn btn-warning " data-bs-toggle="modal" data-bs-target="#staticBackdrop${(sessionScope.cards.indexOf(card))+1}"><fmt:message key="request"/></a>
                        <div class="modal fade" id="staticBackdrop${(sessionScope.cards.indexOf(card))+1}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="staticBackdropLabel"><fmt:message key="making.request"/></h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <fmt:message key="you.can.make.one"/>
                                        <c:if test="${sessionScope.requestError != null} ">
                                        <p class="er"><fmt:message key="${sessionScope.requestError}"/><p>
                                        </c:if>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message key="close"/></button>
                                        <a class="btn btn-primary" href="MakeRequest?card=${(sessionScope.cards.indexOf(card))+1}"> <fmt:message key="request"/></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                </c:if>
            </c:forEach>
            </c:if>
        </ol>
    </div>
</div>
</body>
</html>
