<%--
  Created by IntelliJ IDEA.
  User: Jonathan
  Date: 03-01-2021
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <div class="scrollbar" id="styleFog" align="center"><h1 style="color:blue;" >
        <p class="pb-4"><strong>Velkommen tilbage!</strong></p></h1>
        <form method="get" action="${pageContext.request.contextPath}/Orders/displayOrder">
            <h3 style="color: cornflowerblue" >
                <strong>Indtast dit ordrenummer samt det tlf nr du bestilte med for at se din ordre:</strong></h3>
            <input class="form-control" type="number" name="orderNumber" placeholder="Ordrenr">
            <input class="form-control" type="text" name="tlf" placeholder="tlf">
            <input type="submit" value="Se din ordre!">
        </form>
    </div>
    </div>


