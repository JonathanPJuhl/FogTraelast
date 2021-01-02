<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jonathan
  Date: 30-11-2020
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="scrollbar2" id="styleFog">
    <div align="center">
        <h1>Velkommen!</h1>
        <h1>${sessionScope.user.name}</h1>
        <p>Oversigt over alle ordrer</p>
    </div>
            <div class="row">
        <div class="col-md-12 scroll">
    <jsp:include page="../pages/displayAllOrders.jsp" flush="true"/>
          </div>
        </div>
    </div>

