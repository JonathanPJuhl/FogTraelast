<%--
  Created by IntelliJ IDEA.
  User: Jonathan
  Date: 07-12-2020
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="scrollbar" id="styleFog">
    <div align="center" >
        <table class="text-white" border="1" cellpadding="5">
            <h2 class="text-white">Ordre før ændring</h2>
            <tr>
                <th>orderID</th>
                <th>orderStatus</th>
                <th>length</th>
                <th>width</th>
                <th>customerPhone</th>
                <th>customerEmail</th>
                <th>price</th>
                <th>salesmanID</th>
            </tr>
            <c:forEach var="orders" items="${requestScope.orderList}">
                <tr>
                    <td><a href="<c:url value="/Orders/${orders.orderID}"/>">#<c:out value="${orders.orderID}" /></a> </td>
                    <td><c:out value="${orders.orderStatus}" /></td>
                    <td><c:out value="${orders.length}" /></td>
                    <td><c:out value="${orders.width}" /></td>
                    <td><c:out value="${orders.customerPhone}" /></td>
                    <td><c:out value="${orders.customerEmail}" /></td>
                    <td><c:out value="${orders.price}" /></td>
                    <td><c:out value="${orders.salesmanID}" /></td>
                </tr>
            </c:forEach>
        </table>

        <form method="post"  action="${pageContext.request.contextPath}/Orders/editOrder">
        <table class="text-white" border="1" cellpadding="5">
            <h2 class="text-white">Rediger ordre</h2>

            <tr>
                <th>orderID</th>
                <th>orderStatus</th>
                <th>length</th>
                <th>width</th>
                <th>customerPhone</th>
                <th>customerEmail</th>
                <th>price</th>
                <th>salesmanID</th>
            </tr>
            <c:forEach var="orders" items="${requestScope.orderList}">
                <tr>
                    <td><a href="<c:url value="/Orders/${orders.orderID}"/>">#<c:out value="${orders.orderID}"/></a> </td>
                    <td><select name="orderStatus">
                        <option value="New">Nyoprettet</option>
                        <option value="Processing">Under Bearbejdning</option>
                        <option value="Done">Færdiggjort</option>
                    </select></td>
                    <td><c:out value="${orders.length}" /></td>
                    <td><c:out value="${orders.width}" /></td>
                    <td><c:out value="${orders.customerPhone}" /></td>
                    <td><c:out value="${orders.customerEmail}" /></td>
                    <td><input type="number" name="price"></td>
                    <td><select name="salesmanID">
                        <c:forEach var="salesman" items="${requestScope.salesmen}" >
                            <option value="${salesman.ID}">${salesman.name}</option>
                        </c:forEach> </td>
                    <td><input type="submit" value="Ret til"></td>
                </tr>
            </c:forEach>
        </table>
        </form>
    </div>
</div>