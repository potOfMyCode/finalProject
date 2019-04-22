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
    <meta charset="utf-8"/>
    <fmt:setBundle var="link" basename="message" scope="session"/>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/sign-in/">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">


    <title><fmt:message key="productmaker.base.title" bundle="${link}"/></title>

</head>

<body class="d-flex flex-column h-100">

<jsp:include page="../views/header.jsp"/>

<h3><a href="${pageContext.request.contextPath}/app/productmaker/show_all"><fmt:message key="productmaker.base.showAllProducts" bundle="${link}"/></a></h3>
<br>
<h3><a href="${pageContext.request.contextPath}/app/productmaker/addProduct"><fmt:message key="productmaker.base.addNewProduct" bundle="${link}"/></a></h3>
<br>
<h3><a href="${pageContext.request.contextPath}/app/productmaker/updateProduct"><fmt:message key="productmaker.base.updateExistProduct" bundle="${link}"/></a></h3>
<br>


<jsp:include page="../views/footer.jsp"/>

</body>
</html>
