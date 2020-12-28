<%--
  Created by IntelliJ IDEA.
  User: Bruger
  Date: 13-12-2020
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<svg class="banner" width="${sessionScope.construction1.roof.width}" height="${sessionScope.construction1.roof.length}">
    ${sessionScope.svgCarport}

</svg>

<%--
VIRKER IKKE YET
<svg>
    ${sessionScope.svgCarportFront}
</svg>

<svg>
    ${sessionScope.svgCarportSide}
</svg>--%>

<form id="login_button" action="${pageContext.request.contextPath}/BOM">
    <input type="hidden" value=true name="payCheck">
    <input type="submit" value="Accepter og betal carport" />
</form>
