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
<div align="center">
<h1>Vælg dine tag og beklædnings egenskaber</h1>
    <form method="post" action="${pageContext.request.contextPath}/Orders/viewSVG}">
    <label for="roofMaterial">Tagbeklædning </label>
        <select name="claddingRoof">
            <c:forEach var="materialOption" items="${sessionScope.claddingOptionsRoof}">
                <option id="roofMaterial" value="${materialOption}">${materialOption.name}</option>
            </c:forEach>
        </select>
        <seclect name = "degreeOption">
                <c:forEach var="i" begin="45" end="4" step="-5">
                    <option value="${i}">${i}</option>
                </c:forEach>
        </seclect>

    </form>
    </div>
    </div>

