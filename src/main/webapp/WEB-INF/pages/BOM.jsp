<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Bruger
  Date: 15-12-2020
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="scrollbar" id="styleFog">
    <div align="center">
        <H1>Tillykke med din nye carport!</H1>
        <br>
        <H3 class="text-white" align="left">&nbsp;&nbsp;&nbsp;&nbsp;Din carports egenskaber:</H3>
        <H5 class="text-white" align="left"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-
            Bredde: ${(sessionScope.tempConstruction.width)/1000} m -
            Længde: ${(sessionScope.tempConstruction.length)/1000} m
            - Tagtype: ${(sessionScope.tempConstruction.roofChoice)}
            - Skur? ${(sessionScope.tempConstruction.shedOrNo)}
            - Beklædning? ${(sessionScope.tempConstruction.claddingChoice)}</H5>
        <H5 class="text-white" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <c:if test="${sessionScope.tempConstruction.shedOrNo==1}">
                - Beklædnings materiale ${(sessionScope.tempConstruction.shedAndCarportCladding.nametype)} - ${(sessionScope.tempConstruction.shedAndCarportCladding.color)}
            </c:if>
            <c:if test="${sessionScope.tempConstruction.isCladdingChoice()==1}">
            - Tag materiale ${(sessionScope.tempConstruction.roofCladding.nametype)}
            - ${sessionScope.tempConstruction.roofCladding.color} -</H5>
        </c:if>
        <table class="text-white" border="1" cellpadding="5">
            <h2 class="text-white">Stykliste</h2>
            <tr>
                <th>Materialets beskrivelse</th>
                <th>Længde (i mm)</th>
                <th>Bredde (i mm)</th>
                <th>Antal</th>
                <th>Enhed</th>
                <th>Beskrivelse</th>
            </tr>
            <c:forEach var="BOMItem" items="${sessionScope.bom}">
                <tr>
                    <td>${BOMItem.material.nametype}</td>
                    <td>${BOMItem.length}</td>
                    <td>${BOMItem.width}</td>
                    <td>${BOMItem.quantity}</td>
                    <!--TODO SKAL FIXES -->
                    <td>${BOMItem.material.color}</td>
                    <td>${BOMItem.description}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
