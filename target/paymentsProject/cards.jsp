<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/cards.css">
    <%@ include file="/tags/bootstrap.jspf" %>
    <%@ include file="/tags/nav.jspf" %>

    <title>Cards</title>
</head>
<body>
<% int balance = 0;
    for (Card card:cardSet){
        if(!card.getStatus().equals("Blocked")){
            balance +=card.getBalance();
        }
}%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>
<div class="d-grid gap-2 col-2 mx-auto m-4">
    <p class="fst-normal fs-3 "><fmt:message key="card.balance"/> : <%=balance%> ₴
        <c:if test="${sessionScope.cards.size()!=3}">
        <a type="button" href="AddCard" class="btn btn-success"><fmt:message key="add"/></a>
        </c:if>
    </p>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm ui-corner-left">
            <div class="container-fluid m-3 row gy-2">
                <c:if test="${sessionScope.cards.get(0).getStatus() eq 'Active'}">
                    <p class="fst-normal fs-5 "><fmt:message key="card"/> 1: ${sessionScope.cards.get(0).getBalance()} ₴</p>
                    <p class="fst-normal fs-5 ">${sessionScope.cards.get(0).getNumberOfCard()}</p>
                </c:if>
                <c:if test="${sessionScope.cards.get(0).getStatus() eq 'Blocked' || sessionScope.cards.get(0).getStatus() eq 'Prepare' }">
                    <p class="fst-normal fs-5 "><fmt:message key="Blocked"/></p>
                    <p class="fst-normal fs-5 ">${sessionScope.cards.get(0).getNumberOfCard()}</p>
                </c:if>
            </div>
            <div class="form">
                <form >
                    <c:if test="${sessionScope.cards.get(0).getStatus() eq 'Active'}">
                        <a class="btn btn-primary d-grid gap-2 mt-3" href="SendTransfer?card=1" hidden =""  role="button"><fmt:message key="send"/></a>
                        <a class="btn btn-primary d-grid gap-2 mt-3" href="Replenishment?card=1" role="button"><fmt:message key="reestablish"/></a>
                    </c:if>
                    <c:if test="${sessionScope.cards.get(0).getStatus() eq 'Blocked' || sessionScope.cards.get(0).getStatus() eq 'Prepare' }">
                        <a class="btn btn-warning d-grid gap-2 mt-3 " data-bs-toggle="modal" data-bs-target="#staticBackdrop"><fmt:message key="request"/></a>
                        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
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
                                        <a class="btn btn-primary" href="MakeRequest?card=1"> <fmt:message key="request"/></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </form>
            </div>
        </div>
        <c:if test="${sessionScope.cards.size() ge 2}">
            <div class="col-sm ">
                <div class="container-fluid m-3 row gy-2">
                    <c:if test="${sessionScope.cards.get(1).getStatus() eq 'Active'}">
                        <p class="fst-normal fs-5 "><fmt:message key="card"/> 2 : ${sessionScope.cards.get(1).getBalance()} ₴</p>
                        <p class="fst-normal fs-5 ">${sessionScope.cards.get(1).getNumberOfCard()}</p>
                    </c:if>
                    <c:if test="${sessionScope.cards.get(1).getStatus() eq 'Blocked' || sessionScope.cards.get(1).getStatus() eq 'Prepare'}">
                        <p class="fst-normal fs-5 "><fmt:message key="Blocked"/></p>
                        <p class="fst-normal fs-5 ">${sessionScope.cards.get(1).getNumberOfCard()}</p>
                    </c:if>
                </div>
                <div class="form" >
                    <form >
                        <c:if test="${sessionScope.cards.get(1).getStatus() eq 'Active'}">
                            <a class="btn btn-primary d-grid gap-2 mt-3" href="SendTransfer?card=2" hidden =""  role="button"><fmt:message key="send"/></a>
                            <a class="btn btn-primary d-grid gap-2 mt-3" href="Replenishment?card=2" role="button"><fmt:message key="reestablish"/></a>
                        </c:if>
                        <c:if test="${sessionScope.cards.get(1).getStatus() eq 'Blocked' || sessionScope.cards.get(1).getStatus() eq 'Prepare'}">
                            <a class="btn btn-warning d-grid gap-2 mt-3 " data-bs-toggle="modal" data-bs-target="#staticBackdrop1"><fmt:message key="request"/></a>
                            <div class="modal fade" id="staticBackdrop1" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel1" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="staticBackdropLabel1"><fmt:message key="making.request"/></h5>
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
                                            <a class="btn btn-primary" href="MakeRequest?card=2"> <fmt:message key="request"/></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
            <c:if test="${sessionScope.cards.size() eq 3}">
                <div class="col-sm ui-corner-right">
                    <div class="container-fluid m-3 row gy-2">
                        <c:if test="${sessionScope.cards.get(2).getStatus() eq 'Active'}">
                            <p class="fst-normal fs-5 "><fmt:message key="card"/> 3 : ${sessionScope.cards.get(2).getBalance()} ₴</p>
                            <p class="fst-normal fs-5 ">${sessionScope.cards.get(2).getNumberOfCard()}</p>
                        </c:if>
                        <c:if test="${sessionScope.cards.get(2).getStatus() eq 'Blocked' || sessionScope.cards.get(2).getStatus() eq 'Prepare'}">
                            <p class="fst-normal fs-5 "><fmt:message key="Blocked"/></p>
                            <p class="fst-normal fs-5 ">${sessionScope.cards.get(2).getNumberOfCard()}</p>
                        </c:if>

                    </div>
                    <div class="form">
                        <form >
                            <c:if test="${sessionScope.cards.get(2).getStatus() eq 'Active'}">
                                <a class="btn btn-primary d-grid gap-2 mt-3" href="SendTransfer?card=3" hidden =""  role="button"><fmt:message key="send"/></a>
                                <a class="btn btn-primary d-grid gap-2 mt-3" href="Replenishment?card=3" role="button"><fmt:message key="reestablish"/></a>
                            </c:if>
                            <c:if test="${sessionScope.cards.get(2).getStatus() eq 'Blocked' || sessionScope.cards.get(2).getStatus() eq 'Prepare'}">
                                <a class="btn btn-warning d-grid gap-2 mt-3 " data-bs-toggle="modal" data-bs-target="#staticBackdrop2"><fmt:message key="request"/></a>
                                <div class="modal fade" id="staticBackdrop2" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel2" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="staticBackdropLabel2"><fmt:message key="making.request"/></h5>
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
                                                <a class="btn btn-primary" href="MakeRequest?card=3"> <fmt:message key="request"/></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </form>
                    </div>
                </div>
            </c:if>
        </c:if>

    </div>


</div>


</body>
</html>