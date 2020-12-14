<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jonathan
  Date: 14-12-2020
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="scrollbar" id="styleFog">
    <div class="text-white" align="center">
        <h1>Vælg dine tag og beklædnings egenskaber</h1>
        <form method="post" action="${pageContext.request.contextPath}/Orders/viewSVG}">
            <select name="materialOption">
            <c:forEach var="materialOption" items="${requestScope.claddingOptionsRoof}">
            <option id="roofMaterial" value="${materialOption.nametype}">${materialOption.nametype} - ${materialOption.color}</option>
        </c:forEach>
            </select>
    <br>

            <c:if test = "${requestScope.userChoice.roofChoice=='Pitched'}">
            <label for="degreeOption">Tagbeklædning </label>
            <seclect name = "degreeOption">
                <c:forEach var="i" begin="5" end="45" step="5">
                    <option id="degreeOption" value=${i}>${i}</option>
                </c:forEach>
                </seclect>
            </c:if>



                <c:if test = "${requestScope.userChoice.shedOrNo==1}">
                <label for="shedLength">skurlængde </label>
                <input type="number" name="shedLength" id="shedLength">
                <label for="shedWidth">skurbredde </label>
                <input type="number" name="shedWidth" id="shedWidth">
                </c:if>


    </div>
    </form>
</div>
