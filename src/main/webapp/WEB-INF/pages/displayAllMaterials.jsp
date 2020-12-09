<%--
  Created by IntelliJ IDEA.
  User: Cathrine
  Date: 09-12-2020
  Time: 09:43

--%>
<h1 class="text-white">Stykliste</h1>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="scrollbar" id="styleFog">
    <div align="center" >
        <table class="text-white" border="1" cellpadding="5">
            <h2 class="text-white">List of orders</h2>
            <tr>
                <th>Materialets beskrivelse</th>
                <th>Længde</th>
                <th>Antal</th>
                <th>Enhed</th>
                <th>Beskrivelse</th>
            </tr>
            <c:forEach var="orders" items="${requestScope.list}">
                <tr>
                    <td><c:url value="${}"/></td>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>