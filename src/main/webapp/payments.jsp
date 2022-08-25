<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Your payments</title>
    <%@ include file="/tags/bootstrap.jspf" %>
    <%@ include file="/tags/nav.jspf" %>
</head>
<body>
<c:if test="${!sessionScope.paymentList.isEmpty()}">
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">Sender</th>
        <th scope="col">Recipient</th>
        <th scope="col">Amount</th>
        <th scope="col">Date</th>
        <th scope="col">Status</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="list" items="${sessionScope.paymentList}">
    <tr>
        <td><c:out value="${list.getEmailSender()}"/></td>
        <td><c:out value="${list.getEmailRecipient()}"/></td>
        <td><c:out value="${list.getAmount()}"/> ₴</td>
        <td><c:out value="${list.getDate()}"/></td>
        <td><c:out value="${list.getPaymentStatus()}"/></td>


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
                   href="Pagination?records=${sessionScope.recordsPerPage}&page=${sessionScope.currentPage-1}&sorting=${sessionScope.sorting}">Previous</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.currentPage == 1}">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
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
                     href="Pagination?records=${sessionScope.recordsPerPage}&page=${sessionScope.currentPage+1}&sorting=${sessionScope.sorting}">Next</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.currentPage ge sessionScope.noOfPages}">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
<form action="Pagination" class="d-flex m-3">
    <input type="hidden" name="page" value="1">
    <div class="form-group ">
        <label for="records">Number of records</label>
        <select class="form-control" id="records" name="records">
            <option value="5">5 items</option>
            <option value="10" >10 items</option>
            <option value="15">15 items</option>
        </select>
        <label for="records1">Sorting</label>
        <select class="form-control" id="records1" name="sorting">
            <option value="1" >number ></option>
            <option value="2" >number <</option>
            <option value="3" >date ></option>
            <option value="4" >date <</option>
        </select>
        <button type="submit" class="btn btn-primary " >Submit</button>
    </div>
</form>

</body>
</html>
