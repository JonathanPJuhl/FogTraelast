<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jonathan og Cathrine
  Date: 29-11-2020
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="container navbar-dark bg-light">
    <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">
            <div class="col-4 pt-1">
                <p class="text-white">Velkommen til</p>
                <p class="reset-anchor text-primary">${sessionScope.user.name}</p>
            </div>
            <div class="col-4 text-center">
                <a class="navbar-brand" href="${navBar.findUrl("Index")}">
                    <img src="<c:url value="/images/FogLogo.png"></c:url>" style="width:5vw"
                         class="d-inline-block">
                    Trælast
                </a>
            </div>
            <div class="col-4 d-flex justify-content-end align-items-center">
<c:if test="${sessionScope.userID == null}">
<a class="btn btn-sm btn-outline-secondary" href="${navBar.findUrl("SalesmanLogin")}">Login</a>
</c:if>
                <c:if test="${sessionScope.userID != null}">
                    <a class="btn btn-sm btn-outline-secondary" href="${navBar.findUrl("SalesmanLogin/Logout")}">Logout</a>
                </c:if>
            </div>
        </div>
    </header>
</nav>
<nav class="container navbar-dark bg-light">
    <div class="py-1 mb-2">
        <ul class="nav d-flex justify-content-center nav-menu">
            <a class="p-2 px-4 nav-link text-white " href="${navBar.findUrl("Orders/new")}">Start Byg!</a>
            <a class="p-2 px-4 nav-link text-white " href="${navBar.findUrl("Orders/findOrder")}">Find din ordre!</a>

            <c:if test="${sessionScope.user.roleID == \"2\"}">
                <a class="p-2 px-4 nav-link text-white" href="${navBar.findUrl("Orders/SortByNew")}">Nyoprettede ordre</a>
                <a class="p-2 px-4 nav-link text-white" href="${navBar.findUrl("Orders/SortByProcessing")}">Igangværende ordre</a>
                <a class="p-2 px-4 nav-link text-white" href="${navBar.findUrl("Orders/SortByDone")}">Færdiggjorte ordre</a>
                <a class="p-2 px-4 nav-link text-white" href="${navBar.findUrl("Orders/SortBySalesman")}">Mine ordre</a>
            </c:if>

        </ul>
    </div>
</nav>