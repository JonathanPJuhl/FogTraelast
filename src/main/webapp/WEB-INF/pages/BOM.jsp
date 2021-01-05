<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="refresh" content="10; url=http://localhost:8080/Fog_Traelast/Orders/displayOrder?orderNumber=${requestScope.orderID}&tlf=${sessionScope.tempConstruction.customerPhone}">
<div class="scrollbar" id="styleFog">

    <H1 align="center">Her din bestilling med ID: ${requestScope.orderID}</H1>
    <br>
    <H3 align="center">Din carports egenskaber:</H3>

    <H5 align="center">
        Bredde: ${(sessionScope.tempConstruction.width)/1000} m
    </H5>


        <H5 align="center">
        Længde: ${(sessionScope.tempConstruction.length)/1000} m
        </H5>


    <H5 align="center">
            Tagtype: ${(sessionScope.tempConstruction.roofChoice)}
    </H5>

    <H5 align="center">
        <c:if test="${sessionScope.tempConstruction.shedOrNo==1}">
            Beklædnings materiale: ${requestScope.usersChoices.shedAndCarportCladding.nametype},
            ${requestScope.usersChoices.shedAndCarportCladding.color}
        </c:if>
    </H5>
    <H5 align="center">

        <c:if test="${sessionScope.tempConstruction.isCladdingChoice()==1}">
            Tag materiale: ${requestScope.usersChoices.roofCladding.nametype}, ${requestScope.usersChoices.roofCladding.color}

        </c:if>
    </H5>
    <div align="center">
        <img src="<c:url value="/images/Spinner-1s-200px.gif"></c:url>" style="width:5vw"
             class="d-inline-block">
    </div>
    <H5 align="center">Vent venligst, mens din ordre bliver bearbejdet. Du vil blive videresendt om et øjeblik.</H5>
   <%-- <table class="mb-3" border="1" cellpadding="5" align="center">
        <h2 class="mt-5" align="center">Stykliste</h2>
        <h6 align="center">Alle mål herunder er i mm.</h6>
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
    </div>--%>

<input type="hidden" value=false name="payCheck"/>
