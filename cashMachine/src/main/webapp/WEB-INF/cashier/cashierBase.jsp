<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 14.04.2019
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <fmt:setBundle var="link" basename="message" scope="session"/>
    <title><fmt:message key="cashierBase.title" bundle="${link}"/></title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/sign-in/">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">

</head>

<body class="d-flex flex-column h-100">

<jsp:include page="../views/header.jsp"/>

<h3><a href="${pageContext.request.contextPath}/app/cashier/createCheck"><fmt:message key="cashierBase.createNewCheck" bundle="${link}"/></a></h3>
<br>

<jsp:include page="../views/footer.jsp"/>

</body>
</html>
