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

        <form method="post">

            <label for="roofMaterial">Tagbeklædning </label>
            <select name="roofMaterialOption" class="form-control">
                <c:forEach var="materialOption" items="${requestScope.claddingOptionsRoof}">
                    <option id="roofMaterial" value="${materialOption}">${materialOption.nametype}
                        - ${materialOption.color}</option>
                </c:forEach>
            </select>

            <br>
            <c:if test="${requestScope.userChoice.isCladdingChoice()==1}">
                <label for="caportCladding">Beklædning </label>
                <select name="carportCladding" class="form-control">
                    <c:forEach var="materialOption" items="${requestScope.claddingOptionsRoof}">
                        <option id="caportCladding" value="${materialOption}">${materialOption.nametype}
                            - ${materialOption.color}</option>
                    </c:forEach>
                </select>
            </c:if>
            <br>
            <c:if test="${requestScope.userChoice.roofChoice.equals('Pitched')}">
                <label for="roofMaterial">Taghældning </label>
                <select name="degreeOption" class="form-control">
                    <c:forEach var="i" begin="15" end="45" step="5">
                        <option id="degreeOption" value="${i}">${i}</option>
                    </c:forEach>
                </select>
            </c:if>

            <br>
            <c:if test="${requestScope.userChoice.shedOrNo==1}">
                <label for="shedLength">Skurlængde </label>
                <input type="number" name="shedLength" id="shedLength" class="form-control">

                <label for="shedWidth">Skurbredde </label>
                <input type="number" name="shedWidth" id="shedWidth" class="form-control">

                <label for="shedCladding">Skurbeklædning </label>
                <select name="shedCladding" class="form-control">
                    <c:forEach var="materialOption" items="${requestScope.claddingOptionsRoof}">
                        <option id="shedCladding" value="${materialOption}">${materialOption.nametype}
                            - ${materialOption.color}</option>
                    </c:forEach>
                </select>
            </c:if>

<button class="btn btn-primary" type="submit">Se Stykliste (pt)</button>
        </form>
    </div>
</div>
