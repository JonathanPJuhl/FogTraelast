<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jonathan
  Date: 30-11-2020
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<a class="btn-primary btn" href="<c:url value="/Orders"/>">Vis alle ordre</a>

    <form method="get" id="NewButton" action="${pageContext.request.contextPath}/Orders/new">
        <input type="submit" value="Opret ny ordre"/>
    </form>

