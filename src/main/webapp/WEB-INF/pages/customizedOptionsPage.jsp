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
            <c:forEach var="materialOption" items="${sessionScope.claddingOptionsRoof}">
                <option id="roofMaterial" value="${materialOption}">${materialOption.name}</option>
            </c:forEach>
            <c:if test = "${requestScope.userChoice.roofChoice=='Pitched'}">
            <label for="roofMaterial">Tagbeklædning </label>
            <seclect name = "degreeOption">
                <c:forEach var="i" begin="45" end="4" step="-5">
                    <option value="${i}">${i}</option>
                </c:forEach>
            </seclect>
            </c:if>

            <c:if test = "${requestScope.userChoice.roofChoice=='Flat'}">
                <label for="roofMaterial">Tagbeklædning </label>
                <select name="claddingRoof">

                </select>
                <seclect name = "degreeOption">
                    <c:forEach var="i" begin="45" end="4" step="-5">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </seclect>
            </c:if>

        </form>
    </div>
</div>
