<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jonathan
  Date: 30-11-2020
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<h1>Velkommen til: ${requestScope.list.toString()}!</h1>
Hvad har du lyst til at gøre nu?

<form method="post" id="display_all_orders_button" action="${pageContext.request.contextPath}/DisplayAllOrders">
    <%--<input type="number" placeholder="Vælg en specifik ordre" name="id"/>--%>
    <input type="submit" value="Vis alle ordre" />
</form>
