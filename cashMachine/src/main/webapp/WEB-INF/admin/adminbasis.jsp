<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 11.04.2019
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <fmt:setBundle var="link" basename="message" scope="session"/>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/sign-in/">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">

    <title><fmt:message key="adminbasis.title" bundle="${link}"/></title>
</head>
<body>
<jsp:include page="../views/header.jsp"/>
<a href="${pageContext.request.contextPath}/app/admin/show_all"><h3><fmt:message key="adminbasis.showAllWorker" bundle="${link}"/></h3></a>
<br>
<a href="${pageContext.request.contextPath}/app/admin/registration"><h3><fmt:message key="adminbasis.addNewWorker" bundle="${link}"/></h3></a>
<br>

<jsp:include page="../views/footer.jsp"/>
</body>
</html>
