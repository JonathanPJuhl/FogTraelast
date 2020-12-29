<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jonathan
  Date: 30-11-2020
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="scrollbar" id="styleFog">
<div align="center" id="pay" >
    <table border="1" cellpadding="5">
        <tr>
            <th>orderID</th>
            <th>orderStatus</th>
            <th>length</th>
            <th>width</th>
            <th>roofType</th>
            <th>shedOrNO</th>
            <th>cladding</th>
            <th>customerPhone</th>
            <th>customerEmail</th>
            <th>price</th>
            <th>salesmanID</th>

        </tr>
        <c:forEach var="orders" items="${requestScope.list}">
            <tr>
                <td><a href="<c:url value="/Orders/${orders.orderID}"/>">#<c:out value="${orders.orderID}" /></a> </td>
                <td><c:out value="${orders.orderStatus}" /></td>
                <td><c:out value="${orders.length}" /></td>
                <td><c:out value="${orders.width}" /></td>
                <td><c:out value="${orders.roofType}" /></td>
                <c:if test="${orders.shedOrNo == 0}">
                    <td>Ikke ønsket</td>
                </c:if>
                <c:if test="${orders.shedOrNo == 1}">
                    <td>Ønsket</td>
                </c:if>
                <c:if test="${orders.cladding == 0}">
                    <td>Ikke ønsket</td>
                </c:if>
                <c:if test="${orders.cladding == 1}">
                    <td>Ønsket</td>
                </c:if>
                <td><c:out value="${orders.customerPhone}" /></td>
                <td><c:out value="${orders.customerEmail}" /></td>
                <td><c:out value="${orders.price}" /></td>
                <td><c:out value="${orders.salesmanID}" /></td>
                <form method="post" action="${pageContext.request.contextPath}/Orders/edit">
                    <td>
                        <button type="submit"  value="${orders.orderID}" name="orderID" >Rediger</button>
                    </td>
                </form>
            </tr>
        </c:forEach>
    </table>
</div>
</div>