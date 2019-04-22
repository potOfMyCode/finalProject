<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 20.04.2019
  Time: 14:24
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

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/body.css" type="text/css">

    <title><fmt:message key="productmaker.updateProduct.title" bundle="${link}"/></title>
    <style>
        .footer-down {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            min-height: 100vh;
        }

        .form-block {
            width: 25%;
            margin: 0 auto;
            text-align: center;
        }
    </style>
</head>
<body class="footer-down">

<jsp:include page="../views/header.jsp"/>

<div class="form-block" >
    <form action="${pageContext.request.contextPath}/app/productmaker/updateProduct" method="post">

        <select class="custom-select" name="idProduct" id="idProduct" required autofocus>
            <option id="product" value=""><fmt:message key="productmaker.updateProduct.label.choose" bundle="${link}"/></option>
            <div class="invalid-feedback"><fmt:message key="addProduct.label.attribute.invalid" bundle="${link}"/></div>

            <c:forEach var="product" items="${products}">
                <option value="${product.idProduct}" >${product.idProduct}: ${product.name}, price: <fmt:formatNumber pattern="0.00" value="${product.price/course}"></fmt:formatNumber> ${currency} - ${product.attribute_value}</option>
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
        <p><fmt:message key="productmaker.updateProduct.label.enterNewValue" bundle="${link}"/></p>
        <label for="attribute_value" class="sr-only"><fmt:message key="productmaker.updateProduct.label.qOrW" bundle="${link}"/></label>
        <input type="text" name="attribute_value" id="attribute_value" class="form-control" placeholder="" required autofocus>
        <p><fmt:message key="addProduct.label.text.newPrice" bundle="${link}"/></p>
        <label for="price" class="sr-only"><fmt:message key="productmaker.updateProduct.label.price" bundle="${link}"/></label>
        <input type="text" name="price" id="price" class="form-control" placeholder="" required autofocus>


        <button type="submit" class="btn btn-primary"><fmt:message key="productmaker.updateProduct.button.update" bundle="${link}"/></button>
    </form>
</div>

<jsp:include page="../views/footer.jsp"/>

</body>
</html>
