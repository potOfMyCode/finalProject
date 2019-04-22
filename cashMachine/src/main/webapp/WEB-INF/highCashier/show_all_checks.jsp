<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 20.04.2019
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <fmt:setBundle var="link" basename="message" scope="session"/>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/sign-in/">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">

    <link href="../../bootstrap/bootstrap.min.css" rel="stylesheet"/>
    <title><fmt:message key="highcashier.show_all_checks.title" bundle="${link}"/></title>

    <style>
        .layer {
            width: 30%; /* Ширина элемента в пикселах */
            height: 30%;
            padding: 5%; /* Поля вокруг текста */
            margin: auto; /* Выравниваем по центру */

        }

    </style>
</head>
<body>

<jsp:include page="../views/header.jsp"/>

<nav aria-label="Page navigation example" >
    <ul class="pagination justify-content-center" style="font-size: 36px">
        <li class="page-item" >
            <c:if test="${currentPage != 1}">
                <td><a href="show_all_checks?page=${currentPage - 1}">&laquo;   </a></td>
            </c:if>
        </li>

        <li class="page-item active">
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="show_all_checks?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </li>
        <li class="page-item" >
            <c:if test="${currentPage lt noOfPages}">
                <td><a href="show_all_checks?page=${currentPage + 1}">   &raquo;</a></td>
            </c:if>
        </li>
    </ul>
</nav>


<c:forEach var="check" items="${checkList}">
    <div class="">
        <div class="layer ">

            <table class="table my-2">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="highcashier.show_all_checks.label.idCheck" bundle="${link}"/></th>
                    <th scope="col"><fmt:message key="highcashier.show_all_checks.label.idWorker" bundle="${link}"/></th>
                    <th scope="col"><fmt:message key="highcashier.show_all_checks.label.price" bundle="${link}"/></th>
                </tr>
                </thead>

                <tbody>

                <tr>
                    <td>${check.idCheck}</td>
                    <td>${check.idWorker}</td>
                    <td><fmt:formatNumber pattern="0.00" value="${check.price/course}"></fmt:formatNumber> ${currency}</td>
                </tr>
                </tbody>
            </table>
            <table class="table my-2 align-content-center">

                <thead>
                <tr>
                    <th scope="col"><fmt:message key="highcashier.show_all_checks.label.idProduct" bundle="${link}"/></th>
                    <th scope="col"><fmt:message key="highcashier.show_all_checks.label.attribute_value" bundle="${link}"/></th>

                </tr>
                </thead>

                <tbody>
                <c:forEach var="product" items="${check.products}">
                    <tr>
                        <td>${product.key}</td>
                        <td>${product.value}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <form action="${pageContext.request.contextPath}/app/highcashier/cancelProductFromCheck" method="post">
                <div class="mb-3">
                    <label for="idProduct"><fmt:message key="highcashier.show_all_checks.label.removeProduct" bundle="${link}"/></label>
                    <input type="text" class="form-control" name="idProduct" id="idProduct" placeholder="<fmt:message key="highcashier.show_all_checks.placeholder1" bundle="${link}"/>" required>
                    <div class="invalid-feedback">
                        <h6><fmt:message key="highcashier.show_all_checks.label.uncorrectId" bundle="${link}"/></h6>>
                    </div>
                    <div class="mb-3">
                        <label for="productAttribute"><fmt:message key="highcashier.show_all_checks.label.howMuch" bundle="${link}"/></label>
                        <input type="text" class="form-control" name="productAttribute" id="productAttribute" placeholder="<fmt:message key="highcashier.show_all_checks.placeholder2" bundle="${link}"/>" required>
                        <div class="invalid-feedback">
                            <h6><fmt:message key="highcashier.show_all_checks.label.uncorrectData" bundle="${link}"/></h6>>
                        </div>
                    </div>
                    <button name="currentCheck" id ="currentCheck" value="${check.idCheck}"type="submit" class="btn btn-primary"><fmt:message key="highcashier.show_all_checks.button.cancelProduct" bundle="${link}"/></button>
                </div>
            </form>
            <br>
            <form action="${pageContext.request.contextPath}/app/highcashier/cancelCheck" method="post">
                <button name="idCheck" id ="idCheck" value="${check.idCheck}" type="submit" class="btn btn-primary"><fmt:message key="highcashier.show_all_checks.button.cancelCheck" bundle="${link}"/></button>
            </form>

        </div>
    </div>
</c:forEach>

<jsp:include page="../views/footer.jsp"/>
</body>
</html>
