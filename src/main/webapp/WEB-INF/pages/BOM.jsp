<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="refresh" content="3; url=http://localhost:8080/Fog_Traelast/Orders/displayOrder?orderNumber=${requestScope.orderID}&tlf=${sessionScope.tempConstruction.customerPhone}">
<div class="scrollbar" id="styleFog">

    <H1 align="center">Her din bestilling med ID: ${requestScope.orderID}</H1>

    <H5 align="center">Vent venligst, mens din ordre bliver bearbejdet. Du vil blive videresendt om et øjeblik.</H5>

    <div align="center">
        <img src="<c:url value="/images/Spinner-1s-200px.gif"></c:url>" style="width:5vw"
             class="d-inline-block">
    </div>

<input type="hidden" value=false name="payCheck"/>

</div>