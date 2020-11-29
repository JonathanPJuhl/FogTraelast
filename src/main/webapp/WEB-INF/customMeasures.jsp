<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lars og Cathrine
  Date: 27-11-2020
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="CustomMeasures">
    <label for="length">Vælg fulde længde carport:</label>
    <select id="length" name="chosenLength">
        <c:forEach var = "i" begin = "300" end = "700" step="100">
            <option value="${i*10}">${i}</option>
        </c:forEach>
    </select>

    <label for="width">Vælg fulde bredde carport:</label>
    <select id="width" name="chosenWidth">
        <c:forEach var = "i" begin = "200" end = "600" step="50">
            <option value="${i*10}">${i}</option>
        </c:forEach>
    </select>
</form>
</body>
</html>
