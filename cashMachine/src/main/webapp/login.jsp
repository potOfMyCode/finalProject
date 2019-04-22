<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 11.04.2019
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <fmt:setBundle var="link" basename="message" scope="session"/>


    <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/sign-in/">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signin.css"/>

    <jsp:include page="WEB-INF/views/header_welcome.jsp"/>
    <title><fmt:message key="login.title" bundle="${link}"/></title>

    <style>
        .layer {
            width: 25%; /* Ширина элемента в пикселах */
            height: 30%;
            padding: 5%; /* Поля вокруг текста */
            margin: auto; /* Выравниваем по центру */
        }
    </style>

<!-- Bootstrap core CSS -->

</head>
<body class="text-center">
    <div class="layer">
    <div class="top-cover center-block">
        <div class="row centered vertical-align">
                <form  action="${pageContext.request.contextPath}/app/login" method="post" class="form-signin">
                    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="guest.login" bundle="${link}"/></h1>
                    <label for="login" class="sr-only">Login</label>
                    <input type="text" name="login" id="login" class="form-control" placeholder="<fmt:message key="placeholder.login" bundle="${link}"/>" required autofocus>
                    <label for="password" class="sr-only">Password</label>
                    <input type="password" name="password" id="password" class="form-control" placeholder="<fmt:message key="placeholder.password" bundle="${link}"/>" required>
                    <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="guest.button.signin" bundle="${link}"/></button>
                    <p class="mt-5 mb-3 text-muted">&copy; 2019-2020</p>
                </form>
        </div>
    </div>
</div>
</body>
</html>