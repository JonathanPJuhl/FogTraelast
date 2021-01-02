<%--
  Created by IntelliJ IDEA.
  User: Jonathan
  Date: 13-11-2020
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="scrollbar" id="styleFog">
<form method="post">
    <div align="center">
        <h1>Velkommen - bare log ind</h1>

    </div>
    <div align="center">
        <c:if test="${requestScope.Error=='Forkert email eller password, prøv igen'}">
            <p style="color: red">${requestScope.Error}</p>
        </c:if>
    </div>
            <div align="center">
        <label for="emailName">Email address</label>
    <input type="email" class="form-control" id="emailName" aria-describedby="emailNameHelp" name="email">
    <small id="emailNameHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    <label for="passName">Password</label>
    <input type="password" class="form-control" id="passName" name="password">
    <button type="submit" class="btn btn-primary">Login</button>
    </div>
</form>
</div>