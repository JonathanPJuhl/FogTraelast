<%--
  Created by IntelliJ IDEA.
  User: Bruger
  Date: 13-12-2020
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="scrollbar" id="styleFog" align="center">
    <h2>Således kommer din carport til at se ud!</h2>
    <h6>vælg enten at acceptere eller prøve igen nedenfor </h6>
</svg>
    <div class="mb-3 ml-3 mr-3">
        <svg viewBox="0 0 ${requestScope.svgScaleHeight} ${requestScope.svgScaleWidth}" preserveAspectRatio="xMidYMid slice" >
            <svg x=100 y=100 class="banner" width="$${requestScope.construction1.roof.width}" height="${requestScope.construction1.roof.length}">
                ${requestScope.svgCarport}
            </svg>
        </svg>
    </div>
    <div class="row">
        <div class="col-md-6 mb-3">
    <form method="get" id="pay" action="${pageContext.request.contextPath}/BOM">
    <input type="hidden" value=true name="payCheck"/>
    <input align="center" class="btn btn-primary center" type="submit" value="Accepter og betal carport" />
    </form>
        </div>
        <div class="col-md-6 mb-3">
            <form method="get" action="${pageContext.request.contextPath}/Orders/new">
            <input type="submit" class="btn btn-primary center"  value="Afslå og prøv igen" >
            </form>
        </div>
        </div>
    </div>
</div>