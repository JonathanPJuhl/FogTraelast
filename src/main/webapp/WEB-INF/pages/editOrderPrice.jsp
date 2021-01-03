<%--
  Created by IntelliJ IDEA.
  User: Bruger
  Date: 03-01-2021
  Time: 04:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="scrollbar" id="styleFog">
        <div align="center">
            <h1>Prisredigering - du ændre ordrenr.<div style="color: blue"> ${sessionScope.editID}</div></h1>
            <form alig="center" method="post" action="${pageContext.request.contextPath}/Orders/edit">
                <br>
                <label>Pris for alle materialer:</label>
                <h3>${requestScope.priceBOM} kr.</h3>

                <label>Pris beregnet med 25% dækningsgrad:</label>
                <h3>${requestScope.priceWithCoverage} kr.</h3>
                <br>
                <h6>Hvad skal prisen ændres til?</h6>
                <input type="number" class="form-control" id="priceAdministrated"
                       aria-describedby="priceAdminstrated" name="price">

                <input type="submit" class="btn btn-primary" value="Rediger pris!">
            </form>
        </div>
    </div>
</div>
