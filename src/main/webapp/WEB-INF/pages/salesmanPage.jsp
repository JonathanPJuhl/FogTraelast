<%--
  Created by IntelliJ IDEA.
  User: Jonathan
  Date: 30-11-2020
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Velkommen til: ${requestScope.list.toString()}!</h1>
Hvad har du lyst til at gøre nu?

<form method="post" id="display_all_orders_button" action="${pageContext.request.contextPath}/DisplayAllOrders">
    <input type="submit" value="Vis alle ordre" />
</form>
    <form method="get" id="NewButton" action="${pageContext.request.contextPath}/Orders/new">
        <input type="submit" value="Opret ny ordre"/>
    </form>
        <form method="post" id="vis specifik ordre">
            <input type="number" placeholder="Indtast ordreID" name="orderID"/>
            <input type="submit" value="Vis ordre" />
        </form>
</body>
</html>
