<%--
  Created by IntelliJ IDEA.
  User: LKPDe
  Date: 27-11-2020
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form method="post">
    <h1>Welcome, please sign in:</h1>
    <label for="emailName">Email address</label>
    <input type="email" class="form-control" id="emailName" aria-describedby="emailNameHelp" name="email">
    <small id="emailNameHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    <label for="passName">Password</label>
    <input type="password" class="form-control" id="passName" name="pass">
    <button type="submit" class="btn btn-primary">Login</button>

</form>
