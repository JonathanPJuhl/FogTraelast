<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="scrollbar" id="styleFog">
<c:if test="${requestScope.alreadyCustomer == false}">
    <H1>Her din bestilling!</H1>
    <br>
    <H3 align="left">&nbsp;&nbsp;&nbsp;&nbsp;Din carports egenskaber:</H3>
    <H5 align="left"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-
        Bredde: ${(sessionScope.tempConstruction.width)/1000} m -
        Længde: ${(sessionScope.tempConstruction.length)/1000} m
        - Tagtype: ${(sessionScope.tempConstruction.roofChoice)}
    </H5>
    <H5 align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <c:if test="${sessionScope.tempConstruction.shedOrNo==1}">
            - Beklædnings materiale: ${requestScope.usersChoices.shedAndCarportCladding.nametype},
            ${requestScope.usersChoices.shedAndCarportCladding.color}
        </c:if>
    </H5>
    <H5 align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

        <c:if test="${sessionScope.tempConstruction.isCladdingChoice()==1}">
            - Tag materiale: ${requestScope.usersChoices.roofCladding.nametype}, ${requestScope.usersChoices.roofCladding.color}

        </c:if>
    </H5>
    <table border="1" cellpadding="5">
        <h2>Stykliste</h2>
        <tr>
            <th>Materialets beskrivelse</th>
            <th>Længde</th>
            <th>Antal</th>
            <th>Enhed</th>
            <th>Beskrivelse</th>
        </tr>
        <c:forEach var="BOMItem" items="${sessionScope.bom}">
            <tr>
                <td>${BOMItem.width}X${BOMItem.material.height}mm. ${BOMItem.material.color}
                    - ${BOMItem.material.nametype} </td>
                <td>${BOMItem.length}</td>
                <td>${BOMItem.quantity}</td>
                <td>stk</td>
                <td>${BOMItem.description}</td>
            </tr>
        </c:forEach>
    </table>
    </div>
    <c:if test="${requestScope.svg}">
        <svg class="banner" width="${sessionScope.construction1.roof.width}"
             height="${sessionScope.construction1.roof.length}">
                ${sessionScope.svgCarport}

        </svg>
    </c:if>
</c:if>
<c:if test="${requestScope.alreadyCustomer}">
    <H1> Du er allerede kunde! Er du sikker på du vil bestille igen?</H1>
    <form method="post" action="/BOM">
        <a href="/">
            <button class="form-control">Nej</button>
        </a> <!-- TODO-->
        <button type="submit" class="btn btn-primary">Ja!</button>
    </form>
</c:if>