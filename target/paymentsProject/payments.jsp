<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Your payments</title>
    <%@ include file="/tags/bootstrap.jspf" %>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light ">
    <div class="container-fluid">
        <a class="navbar-brand m-3"  >Payments <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" width="24" height="24" fill="currentColor" >
            <path d="M512 32C547.3 32 576 60.65 576 96V128H0V96C0 60.65 28.65 32 64 32H512zM576 416C576 451.3 547.3 480 512 480H64C28.65 480 0 451.3 0 416V224H576V416zM112 352C103.2 352 96 359.2 96 368C96 376.8 103.2 384 112 384H176C184.8 384 192 376.8 192 368C192 359.2 184.8 352 176 352H112zM240 384H368C376.8 384 384 376.8 384 368C384 359.2 376.8 352 368 352H240C231.2 352 224 359.2 224 368C224 376.8 231.2 384 240 384z"></path>
        </svg></a>

        <ul class="navbar-nav me-auto mb-3 mb-lg-0 p-md-3">
            <li class="nav-item ">
                <a class="nav-link active"  aria-current="page" href="personalCustomerAccount.jsp"  >Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="aboutUs.jsp">About Us</a>
            </li>
        </ul>
        <ul class="nav justify-content-end ">
            <li class="nav-item dropdown ">
                <a class="nav-link dropdown-toggle " data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Account</a>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="accountInfo.jsp">Account info</a></li>
                    <li><a class="dropdown-item" href="cards.jsp">Cards</a></li>
                    <li><a class="dropdown-item" href="Payments" methods="doPost">Payments</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="LogOut" methods="doPost">Log out</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>



<table class="table table-hover m-3">
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
        <td><c:out value="${list.getAmount()}"/> $</td>
        <td><c:out value="${list.getDate()}"/></td>
        <td><c:out value="${list.getPaymentStatus()}"/></td>
    </tr>
    </c:forEach>
    </tbody>
</table>

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
<form action="Pagination" class="nav justify-content-end m-3">
    <input type="hidden" name="page" value="1">
    <div class="form-group col-md-1 ">
        <label for="records">Number of records</label>
        <select class="form-control" id="records" name="records">
            <option value="5">5 items</option>
            <option value="10" >10 items</option>
            <option value="15">15 items</option>
        </select>
        <label for="records1">Sorting</label>
        <select class="form-control" id="records1" name="sorting">
            <option value="1" >by number</option>
            <option value="2" >by date</option>
        </select>
        <button type="submit" class="btn btn-primary">Submit</button>
    </div>
</form>

</body>
</html>
