<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 20.04.2019
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" isErrorPage="true"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <fmt:setBundle var="link" basename="message" scope="session"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="exception.error.productNotExist" bundle="${link}"/></title>
</head>
<body class="d-flex flex-column h-100">

<jsp:include page="../views/header_welcome.jsp"/>

<p class="py-2 ml-3"><h3><fmt:message key="exception.error.productNotExist.label" bundle="${link}"/></h3></p>
<!--
<br/>
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.exception}
<br/>
Exception: ${pageContext.errorData.throwable}
<br/>
Message from exception: ${pageContext.exception.message}
-->
<jsp:include page="../views/footer.jsp"/>


</body>
</html>
