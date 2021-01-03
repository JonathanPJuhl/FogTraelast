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
        <form method="post" action="${pageContext.request.contextPath}/Orders/displaySingleOrder" name="orderNumber">
            <h3 style="color: cornflowerblue" >
                <strong>Indtast dit ordrenummer for at se din ordre:</strong></h3>
            <input class="form-control" type="number" name="orderNumber">
            <input type="submit" value="Se din ordre!">
        </form>
    </div>
    </div>


