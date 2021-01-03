<%--
  Created by IntelliJ IDEA.
  User: Bruger
  Date: 13-12-2020
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="scrollbar2" id="styleFog">

    <div class="item">
    <svg viewBox="0 0 ${sessionScope.construction1.roof.width} ${sessionScope.construction1.roof.length}" preserveAspectRatio="xMidYMid slice" class="banner" >
    <svg width="${sessionScope.svgLength}" height="${sessionScope.svgWidth}">
        ${sessionScope.svgCarport}
    </svg>
</svg>
</div>
    <form id="pay" action="${pageContext.request.contextPath}/BOM">
    <input type="hidden" value=true name="payCheck">
    <input class="btn btn-primary center" type="submit" value="Accepter og betal carport" />
</form>
</div>


