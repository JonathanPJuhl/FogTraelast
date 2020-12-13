<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Cathrine
  Date: 09-12-2020
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="scrollbar" id="styleFog">
    <div class="text-white" align="center">
        <h1>Vælg dine tag og beklædnings egenskaber</h1>
        <form method="post" action="${pageContext.request.contextPath}/Orders/viewSVG}">
            <label for="roofMaterial">Tag beklædning </label>
            <c:forEach var="materialOption" items="${sessionScope.claddingOptionsRoof}">
                <select name="claddingRoof">
                    <option id="roofMaterial" value="${materialOption}">${materialOption.name}</option>
                </select>
            </c:forEach>
            <c:if test="${sessionScope.construction.roof.flat == false}">
            <c:forEach var="materialOption" items="${requestScope.claddingOptions}">
                <select name="cladding">
                    <option value="${materialOption}">${materialOption.name}</option>
                </select>
            </c:forEach>
            </c:if>
        </form>
    </div>
</div>

