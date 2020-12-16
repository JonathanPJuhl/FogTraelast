<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Bruger
  Date: 15-12-2020
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1 class="text-white">Stykliste</h1>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="scrollbar" id="styleFog">
    <div align="center" >
        <table class="text-white" border="1" cellpadding="5">
            <h2 class="text-white">Stykliste</h2>
            <tr>
                <th>Materialets beskrivelse</th>
                <th>Længde</th>
                <th>Antal</th>
                <th>Enhed</th>
                <th>Beskrivelse</th>
            </tr>
            <c:forEach var="BOMItem" items="${sessionScope.bom}">
                <tr>
                    <td>${BOMItem.material.nametype}</td>
                    <td>${BOMItem.length}</td>
                    <td>${BOMItem.quantity}</td>
                    <!--SKAL FIXES -->
                    <td>${BOMItem.material.color}</td>
                    <td>${BOMItem.description}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
