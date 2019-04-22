<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 11.04.2019
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">

</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
  <h5 class="my-0 mr-md-auto font-weight-normal"><fmt:message key="header.name" bundle="${link}"/></h5>
  <a class="my-2 my-md-0 mr-md-3" href="${pageContext.request.contextPath}/?lang=en"><fmt:message key="header.language.eng" bundle="${link}"/></a>
  <a class="my-2 my-md-0 mr-md-3" href="${pageContext.request.contextPath}/?lang=ua"><fmt:message key="header.language.ukr" bundle="${link}"/></a>
  <nav class="my-2 my-md-0 mr-md-3">
    <a class="nav-link" href="${pageContext.request.contextPath}/app/home"><fmt:message key="header.home" bundle="${link}"/></a>
  </nav>
  <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/app/login"><fmt:message key="header.login" bundle="${link}"/></a>
</div>
</body>
</html>
