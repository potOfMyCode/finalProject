<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 17.04.2019
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <fmt:setBundle var="link" basename="message" scope="session"/>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/sign-in/">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">

    <title><fmt:message key="show_all.title" bundle="${link}"/></title>
</head>
<body>

<jsp:include page="../views/header.jsp"/>


<br><br>

<div class="container-fluid">

    <h2><fmt:message key="show_all.table.title" bundle="${link}"/></h2>

    <table class="table my-3">

        <thead>
        <tr>
            <th scope="col"><fmt:message key="show_all.label.name" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="show_all.label.role" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="show_all.label.login" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="show_all.label.password" bundle="${link}"/></th>
        </tr>
        </thead>

        <tbody>

        <c:forEach var="product" items="${workers}">
            <tr>
                <td>${product.name}</td>
                <td>${product.role}</td>
                <td>${product.login}</td>
                <td>${product.password}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>

<jsp:include page="../views/footer.jsp"/>
</body>
</html>
