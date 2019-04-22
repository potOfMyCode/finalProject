<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 18.04.2019
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <fmt:setBundle var="link" basename="message" scope="session"/>
    <title><fmt:message key="cashier.createCheck.title" bundle="${link}"/></title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/sign-in/">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">

    <style>
        .layer1 {
            background-color: #fc0; /* Цвет фона слоя */
            padding: 5px; /* Поля вокруг текста */
            float: left; /* Обтекание по правому краю */
            margin-left: 15%;
            position: fixed;
        }
        .layer2 {
            background-color: #c0c0c0; /* Цвет фона слоя */
            /* Поля вокруг текста */
            float: left; /* Обтекание по правому краю */
            margin-left: 25%;
            margin-right: 15%;
            padding: 5px 5px 5px 30%;
            width: 100%;
            max-width: 100%;
            position: fixed;
        }

    </style>

</head>

<body style="display: flex; flex-direction: column;">
<jsp:include page="../views/header.jsp"/>
<div style="flex: 1 1 available">
<div class="text-center col-lg-3 my-5  ml-5 layer1" >
    <form id="check" action="${pageContext.request.contextPath}/app/cashier/addProductToCheck" method="post">

        <select class="custom-select" name="idProduct" id="idProduct" required autofocus>
        <option id="product" value=""><fmt:message key="addProduct.chooseOption" bundle="${link}"/></option>
        <div class="invalid-feedback"><fmt:message key="addProduct.label.attribute.invalid" bundle="${link}"/></div>

        <c:forEach var="product" items="${products}">
            <option value="${product.idProduct}" >${product.idProduct}: ${product.name}, price: <fmt:formatNumber pattern="0.00" value="${product.price/course}"></fmt:formatNumber> ${currency}</option>
        </c:forEach>
        </select>
        <br>
        <div class="mb-3">
            <select class="custom-select" name="attribute_name" id="attribute_name">
                <option value=""><fmt:message key="addProduct.label.choose.attribute" bundle="${link}"/></option>
                <option value="weight"><fmt:message key="addProduct.label.attribute.weight" bundle="${link}"/></option>
                <option value="quantity"><fmt:message key="addProduct.label.attribute.quantity" bundle="${link}"/></option>
            </select>
            <div class="invalid-feedback"><fmt:message key="addProduct.label.attribute.invalid" bundle="${link}"/></div>
        </div>
        <p><fmt:message key="addProduct.enter.text" bundle="${link}"/></p>
        <label for="attribute_value" class="sr-only"><fmt:message key="addProduct.enter.attribute" bundle="${link}"/></label>
        <input type="text" name="attribute_value" id="attribute_value" class="form-control" placeholder="" required autofocus>
        <h6><fmt:message key="addProduct.text.price" bundle="${link}"/> </h6>

            <button type="submit" class="btn btn-primary"><fmt:message key="addProduct.button.addToCheck" bundle="${link}"/></button>
    </form>
    <br>
    <form id="closeCheck" action="${pageContext.request.contextPath}/app/cashier/closeCheck" method="post">
        <button type="submit" class="btn btn-primary"><fmt:message key="addProduct.button.closeCheck" bundle="${link}"/></button>
    </form>
</div>
<div class="text-center col-lg-3 my-5  ml-5 layer2">
    <table class="table my-2">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="addProduct.label.worker" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="addProduct.label.price2" bundle="${link}"/></th>
        </tr>
        </thead>

        <tbody>

            <tr>
                <td>${check.idWorker}</td>
                <td><fmt:formatNumber pattern="0.00" value="${check.price/course}"></fmt:formatNumber> ${currency}</td>
            </tr>
        </tbody>
    </table>
    <table class="table my-2">

        <thead>
        <tr>
            <th scope="col"><fmt:message key="addProduct.label.idProduct" bundle="${link}"/></th>
            <th scope="col"><fmt:message key="addProduct.label.attributeValue" bundle="${link}"/></th>

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
</div>
</div>
<jsp:include page="../views/footer.jsp"/>
</body>
</html>