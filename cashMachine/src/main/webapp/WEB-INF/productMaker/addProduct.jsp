<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 17.04.2019
  Time: 22:28
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

    <title><fmt:message key="productmaker.addProduct.title" bundle="${link}"/></title>
</head>
<body>
<jsp:include page="../views/header.jsp"/>
<div class="container">

    <div class="row ">

        <div class="col-md-12 mb-2 mt-4">
            <h4 class="mb-3"><fmt:message key="addProduct.name" bundle="${link}"/></h4>

            <form action="${pageContext.request.contextPath}/app/productmaker/addProduct" method="post" class="needs-validation">

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="name"><fmt:message key="addProduct.label.name" bundle="${link}"/></label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="" value="" required>
                        <div class="invalid-feedback">
                            <fmt:message key="addProduct.label.invalid.name" bundle="${link}"/>
                        </div>
                    </div>
                </div>

                <div class="mb-3">
                    <select class="custom-select" name="attribute_name" id="attribute_name" required>
                        <option value=""><fmt:message key="addProduct.label.choose.attribute" bundle="${link}"/></option>
                        <option value="weight"><fmt:message key="addProduct.label.attribute.weight" bundle="${link}"/></option>
                        <option value="quantity"><fmt:message key="addProduct.label.attribute.quantity" bundle="${link}"/></option>
                    </select>
                    <div class="invalid-feedback"><fmt:message key="addProduct.label.attribute.invalid" bundle="${link}"/></div>
                </div>

                <div class="mb-3">
                    <label for="attribute_value"><fmt:message key="addProduct.label.attribute_value" bundle="${link}"/></label>
                    <input type="text" class="form-control" name="attribute_value" id="attribute_value" placeholder="<fmt:message key="addProduct.label.attribute_value.placeholder" bundle="${link}"/>" required>
                    <div class="invalid-feedback">
                        <fmt:message key="addProduct.label.attribute_value.ivalid" bundle="${link}"/>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="price"><fmt:message key="addProduct.label.price" bundle="${link}"/></label>
                    <input type="text" class="form-control" name="price" id="price" required>
                    <div class="invalid-feedback">
                        <fmt:message key="addProduct.label.price.invalid" bundle="${link}"/>
                    </div>
                </div>

                <hr class="mb-4">
                <input type="submit" class="form-control btn-submit" name="submitAddProduct" value="<fmt:message key="addProduct.button" bundle="${link}"/>">
            </form>
        </div>
    </div>

</div>


<jsp:include page="../views/footer.jsp"/>

</body>
</html>
