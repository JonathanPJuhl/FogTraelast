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
    <div align="center">

        <h1>Vælg dine egenskaber</h1>

        <form method="post">

            <label for="roofMaterial">Tagbeklædning </label>
            <select name="roofMaterialOption" class="form-control">
                <c:forEach var="materialOption" items="${requestScope.claddingOptionsRoof}">
                    <option id="roofMaterial" value="${materialOption.id}">${materialOption.nametype}
                        - ${materialOption.color}</option>
                </c:forEach>
            </select>

            <br>
            <c:if test="${requestScope.userChoice.isCladdingChoice()==1 || requestScope.userChoice.shedOrNo() ==1}">
                <label for="caportCladding">Beklædning </label>
                <select name="carportCladding" class="form-control">
                    <c:forEach var="materialOption" items="${requestScope.claddingOptionsShedCarport}">
                        <option id="caportCladding" value="${materialOption.id}">${materialOption.nametype}
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
                <c:if test="${requestScope.tooBigShed}">
                    <label for="shedLength" style="color: crimson">Vælg en kortere længde og/eller
                        bredde</label>
                </c:if>
                <br>
                <label for="shedLength">Skurlængde (cm) </label>
                <input type="number" name="shedLength" id="shedLength" class="form-control">
                <small class="form-text text-muted">NB! Huske at denne skal være mindre, end længde af selve carporten - ikke taget!</small>

                <label for="shedWidth">Skurbredde (cm)</label>
                <input type="number" name="shedWidth" id="shedWidth" class="form-control">
                <small class="form-text text-muted">NB! Huske at denne skal være mindre, end længde af selve carporten - ikke taget!</small>

            </c:if>
            <br>
            <button class="btn btn-primary" type="submit">Se tegning!</button>
        </form>
    </div>
</div>
