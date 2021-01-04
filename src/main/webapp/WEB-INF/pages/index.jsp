<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${requestScope.alreadyCustomer == false}">
<div class="scrollbar" id="styleFog" align="center"><h1 style="color:blue;" ><p class="pb-4"><strong>Mangler du en splinter ny carport?</strong></h1>
    <h3 style="color: cornflowerblue" ><strong>Så er du det helt rigtige sted!</strong></h3>
<img src="<c:url value="/images/carport.PNG"></c:url>">
    </p>
</div>
</c:if>

<c:if test="${requestScope.alreadyCustomer == true}">

    <div class="scrollbar" id="styleFog" align="center"><h1 style="color:blue;" >
        <p class="pb-4"><strong>Velkommen tilbage!</strong></p></h1>
        <form method="post" action="/BOM" name="orderNumber">
        <h3 style="color: cornflowerblue" >
            <strong>Indtast dit ordrenummer for at se din ordre:</strong></h3>
        <input class="form-control" type="number" name="orderNumber" id="orderNumber">
        <button name="ordreNumber" class="submit form-control">Se din ordre!</button>
        </form>

        <h3 style="color: cornflowerblue" ><strong>Håber det går vi kan huske dig!</strong></h3>
        <form method="post" action="${pageContext.request.contextPath}/">
            <button class="submit form-control">Ellers klik her</button>
        </form> <!--- TODO -->
    </div>
    </div>
</c:if>