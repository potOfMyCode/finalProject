<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 14.04.2019
  Time: 15:13
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

    <title><fmt:message key="registration.title" bundle="${link}"/></title>
</head>
<body class="d-flex flex-column h-100">


<jsp:include page="../views/header.jsp"/>


<div class="container">

    <div class="row ">

        <div class="col-md-12 mb-2 mt-4">
            <h4 class="mb-3"><fmt:message key="registration.name" bundle="${link}"/></h4>

            <form action="${pageContext.request.contextPath}/app/admin/registration" method="post" class="needs-validation">

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="name"><fmt:message key="registration.label.name" bundle="${link}"/></label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="" value="" required>
                        <div class="invalid-feedback">
                            <fmt:message key="registration.label.invalid.name" bundle="${link}"/>
                        </div>
                    </div>
                </div>

                <div class="mb-3">
                    <select class="custom-select" name="role" id="role" required>
                        <option value=""><fmt:message key="registration.label.choose.role" bundle="${link}"/></option>
                        <option value="CASHIER"><fmt:message key="registration.label.role.cashier" bundle="${link}"/></option>
                        <option value="HIGHCASHIER"><fmt:message key="registration.label.role.highCashier" bundle="${link}"/></option>
                        <option value="PRODUCTMAKER"><fmt:message key="registration.label.role.productMaker" bundle="${link}"/></option>
                        <option value="ADMIN"><fmt:message key="registration.label.role.admin" bundle="${link}"/></option>
                    </select>
                    <div class="invalid-feedback"><fmt:message key="registration.label.role.invalid" bundle="${link}"/></div>
                </div>

                <div class="mb-3">
                    <label for="login"><fmt:message key="registration.label.login" bundle="${link}"/></label>
                    <input type="text" class="form-control" name="login" id="login" placeholder="vasya21" required>
                    <div class="invalid-feedback">
                        <fmt:message key="registration.label.login.ivalid" bundle="${link}"/>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="password"><fmt:message key="registration.label.password" bundle="${link}"/></label>
                    <input type="password" class="form-control" name="password" id="password" required>
                    <div class="invalid-feedback">
                        <fmt:message key="registration.label.password.invalid" bundle="${link}"/>
                    </div>
                </div>

                <hr class="mb-4">
                <input type="submit" class="form-control btn-submit" name="submitRegistration" value="<fmt:message key="registration.button" bundle="${link}"/>">
            </form>
        </div>
    </div>

</div>
<jsp:include page="../views/footer.jsp"/>

</body>
</html>

