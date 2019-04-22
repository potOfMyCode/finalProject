<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 20.04.2019
  Time: 22:35
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
    <title><fmt:message key="highcashier.X-Report.title" bundle="${link}"/></title>

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
<div class="layer" >
<table class="table my-2">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="highcashier.X-Report.label.name" bundle="${link}"/></th>
        <th scope="col"><fmt:message key="highcashier.X-Report.label.totalSum" bundle="${link}"/></th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="worker" items="${zWorkers}">

        <tr>
            <td>${worker.key}</td>
            <td><fmt:formatNumber pattern="0.00" value="${worker.value/course}"></fmt:formatNumber> ${currency}</td>
        </tr>

    </c:forEach>
    </tbody>
</table>
</div>
<jsp:include page="../views/footer.jsp"/>
</body>
</html>
