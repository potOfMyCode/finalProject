<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 21.04.2019
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <fmt:setBundle var="link" basename="message" scope="session"/>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/sign-in/">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">

    <link href="../../bootstrap/bootstrap.min.css" rel="stylesheet"/>
    <title><fmt:message key="highcashier.Z-Report.title" bundle="${link}"/></title>

    <style>
        html, body{
            padding: 0px;
            margin: 0px;
        }

        .layer {
            display:flex;
            max-width: 100%;
            height: auto
        }
        .layer1 {
            display:inline-block;

            width: 30%; /* Ширина элемента в пикселах */
            height: 30%;
            padding: 1%; /* Поля вокруг текста */
            padding-left: 5%;
        }
        .layer2 {
             display:inline-block;
            width: 15%; /* Ширина элемента в пикселах */
            height: 30%;
            padding: 1%; /* Поля вокруг текста */
            margin-left: 1%;
         }
        .layer11 {
            width: 30%; /* Ширина элемента в пикселах */
            height: 30%;
            padding: 1%; /* Поля вокруг текста */
            margin: auto; /* Выравниваем по центру */
        }
        .layer22 {
            width: 15%; /* Ширина элемента в пикселах */
            height: 30%;
            padding: 1%; /* Поля вокруг текста */
            margin: auto; /* Выравниваем по центру */

        }

    </style>
</head>
<body>

<jsp:include page="../views/header.jsp"/>


<div class="layer">
        <div class="layer1">

            <table class="table">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="highcashier.Z-Report.label.idProduct" bundle="${link}"/></th>
                    <th scope="col"><fmt:message key="highcashier.Z-Report.label.attribute_value" bundle="${link}"/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="product" items="${nameProducts}">

                    <tr>
                        <td>${product.key}</td>
                        <td>${product.value}</td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
        </div>



    <div class="layer2">
        <table class="table">

            <thead>
            <tr>
                <th scope="col"><fmt:message key="highcashier.Z-Report.label.total" bundle="${link}"/></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="total" items="${totalPriceForProduct}">
                <tr>
                    <td><fmt:formatNumber pattern="0.00" value="${total.value/course}"></fmt:formatNumber> ${currency}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div style="margin-left: 30%">
    <h4><fmt:message key="highcashier.Z-Report.label.totalSum" bundle="${link}"/><fmt:formatNumber pattern="0.00" value="${totalSum/course}"></fmt:formatNumber> ${currency}</h4>
</div>


<jsp:include page="../views/footer.jsp"/>
</body>
</html>
