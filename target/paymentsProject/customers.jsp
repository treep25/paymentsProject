<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Customers</title>
    <%@ include file="/tags/bootstrap.jspf" %>
    <%@ include file="/tags/nav.jspf" %>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>
<table class="table table-hover  table table-sm">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="Id"/></th>
        <th scope="col"><fmt:message key="name"/></th>
        <th scope="col"><fmt:message key="second.name"/></th>
        <th scope="col"><fmt:message key="login"/></th>
        <th scope="col"><fmt:message key="phone"/></th>
        <th scope="col"><fmt:message key="card.balance"/></th>
        <th scope="col"><fmt:message key="role"/></th>
        <th scope="col"><fmt:message key="status.of.card"/></th>
        <th scope="col"><fmt:message key="Edit"/></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="list" items="${sessionScope.listOfCustomers}">
        <tr>
            <td><c:out value="${list.getUserID()}"/></td>
            <td><c:out value="${list.getFirstName()}"/></td>
            <td><c:out value="${list.getSecondName()}"/></td>
            <td><c:out value="${list.getLogin()}"/></td>
            <td><c:out value="${list.getPhone()}"/></td>
            <td><c:out value="${list.getBalance()}"/> ₴</td>
            <td><fmt:message key="${list.getRole()}"/></td>
            <td><fmt:message key="${list.getStatusOfCard()}"/></td>

            <c:if test="${list.getStatusOfCard() eq 'Blocked'}">
                <td><a class="btn btn-danger" href="StatusOfCard?status=Active&id=${list.getUserID()}"><fmt:message key="Blocked"/></a></td>
            </c:if>
            <c:if test="${list.getStatusOfCard() != 'Blocked'}">
            <td><a class="btn btn-success " href="StatusOfCard?status=Blocked&id=${list.getUserID()}"><fmt:message key="Active"/></a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>

<nav aria-label="Navigation" class="nav justify-content-end">
    <ul class="pagination">
        <c:if test="${sessionScope.currentPage != 1}">
            <li class="page-item"><a class="page-link"
               href="PaginationAllCustomers?records=${sessionScope.recordsPerPage}&page=${sessionScope.currentPage-1}&sorting=${sessionScope.sorting}"><fmt:message key="Previous"/></a>
            </li>
        </c:if>

        <c:if test="${sessionScope.currentPage == 1}">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1" aria-disabled="true"><fmt:message key="Previous"/></a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${sessionScope.noOfPages}" var="i">
            <c:choose>
                <c:when test="${sessionScope.currentPage eq i}">
                    <li class="page-item active"><a class="page-link">
                            ${i}<span class="sr-only"></span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link"
                         href="PaginationAllCustomers?records=${sessionScope.recordsPerPage}&page=${i}&sorting=${sessionScope.sorting}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${sessionScope.currentPage lt sessionScope.noOfPages}">
            <li class="page-item"><a class="page-link"
                href="PaginationAllCustomers?records=${sessionScope.recordsPerPage}&page=${sessionScope.currentPage+1}&sorting=${sessionScope.sorting}"><fmt:message key="Next"/></a>
            </li>
        </c:if>
        <c:if test="${sessionScope.currentPage ge sessionScope.noOfPages}">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1" aria-disabled="true"><fmt:message key="Next"/></a>
            </li>
        </c:if>
    </ul>
</nav>

<form action="PaginationAllCustomers" class="d-flex">
    <input type="hidden" name="page" value="1">
    <div class="form-group ">
        <label for="records"><fmt:message key="number.of.records"/></label>
        <select class="form-control" id="records" name="records">
            <option value="5">5 <fmt:message key="items"/></option>
            <option value="10" >10 <fmt:message key="items"/></option>
        </select>
        <label for="records1"><fmt:message key="Sorting"/></label>
        <select class="form-control" id="records1" name="sorting">
            <option value="1" ><fmt:message key="number"/> ></option>
            <option value="2" ><fmt:message key="number"/> <</option>
            <option value="3" ><fmt:message key="name"/> ></option>
            <option value="4" ><fmt:message key="name"/> <</option>
            <option value="5" ><fmt:message key="card.balance"/> ></option>
            <option value="6" ><fmt:message key="card.balance"/> <</option>
        </select>
        <button type="submit" class="btn btn-primary"><fmt:message key="submit"/></button>
    </div>
</form>

</body>
</html>
