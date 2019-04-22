<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 11.04.2019
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Footer Bottom -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/foot.css"/>
<hr/>
  <footer id="footer" class="container" style="flex: 0 0 auto">
    <p class="float-right">
      <a href="#">Back to top</a>
    </p>
    <p>&copy; Company 2019-2020</p>
    <p><fmt:message key="footer.copyright" bundle="${link}"/>
     <fmt:message key="footer.rights" bundle="${link}"/>

    </p>
  </footer>

</footer>
