<%--
  Created by IntelliJ IDEA.
  User: Bruger
  Date: 13-12-2020
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="scrollbar" id="styleFog">
    <form id="pay" action="${pageContext.request.contextPath}/BOM">
        <input type="hidden" value=true name="payCheck">
        <input class="btn btn-primary center" type="submit" value="Accepter og betal carport" />
    </form>
    <svg class="banner" width="${sessionScope.construction1.roof.width}" height="${sessionScope.construction1.roof.length}">
    ${sessionScope.svgCarport}

</svg>

</div>