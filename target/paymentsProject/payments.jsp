<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="tags" prefix="tag" %>
<html>
<head>
    <title>Your payments</title>
    <%@ include file="/tags/bootstrap.jspf" %>
    <%@ include file="/tags/nav.jspf" %>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>
<c:if test="${!sessionScope.paymentList.isEmpty()}">
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="sender"/></th>
        <th scope="col"><fmt:message key="recip"/></th>
        <th scope="col"><fmt:message key="amount"/></th>
        <th scope="col"><fmt:message key="date"/></th>
        <th scope="col"><fmt:message key="status"/></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="list" items="${sessionScope.paymentList}">
    <tr>
        <td><c:out value="${list.getCardNumberSender()}"/></td>
        <td><c:out value="${list.getCardNumberRecipient()}"/></td>
        <td><c:out value="${list.getAmount()}"/> ₴</td>
        <td><tag:date date="${list.getDate()}" locale="${sessionScope.locale}"/></td>
        <td><fmt:message key="${list.getPaymentStatus()}"/></td>


    </tr>
    </c:forEach>
    </tbody>
</table>
</c:if>
<c:if test="${sessionScope.paymentList.isEmpty()}">
    <h4>Empty</h4>
</c:if>
    <nav aria-label="Navigation" class="nav justify-content-end">
        <ul class="pagination">
            <c:if test="${sessionScope.currentPage != 1}">
                <li class="page-item"><a class="page-link"
                   href="Pagination?records=${sessionScope.recordsPerPage}&page=${sessionScope.currentPage-1}&sorting=${sessionScope.sorting}"><fmt:message key="Previous"/></a>
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
                            href="Pagination?records=${sessionScope.recordsPerPage}&page=${i}&sorting=${sessionScope.sorting}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${sessionScope.currentPage lt sessionScope.noOfPages}">
                <li class="page-item"><a class="page-link"
                     href="Pagination?records=${sessionScope.recordsPerPage}&page=${sessionScope.currentPage+1}&sorting=${sessionScope.sorting}"><fmt:message key="Next"/></a>
                </li>
            </c:if>
            <c:if test="${sessionScope.currentPage ge sessionScope.noOfPages}">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true"><fmt:message key="Next"/></a>
                </li>
            </c:if>
        </ul>
    </nav>
<form action="Pagination" class="d-flex m-3">
    <input type="hidden" name="page" value="1">
    <div class="form-group ">
        <label for="records"><fmt:message key="number.of.records"/></label>
        <select class="form-control" id="records" name="records">
            <option value="5">5 <fmt:message key="items"/></option>
            <option value="10" >10 <fmt:message key="items"/></option>
            <option value="15">15 <fmt:message key="items"/></option>
        </select>
        <label for="records1"><fmt:message key="sorting"/></label>
        <select class="form-control" id="records1" name="sorting">
            <option value="1" ><fmt:message key="number"/> ></option>
            <option value="2" ><fmt:message key="number"/> <</option>
            <option value="3" ><fmt:message key="date1"/> ></option>
            <option value="4" ><fmt:message key="date1"/> <</option>
        </select>
        <button type="submit" class="btn btn-primary " ><fmt:message key="submit"/></button>
    </div>
</form>

</body>
</html>
