<%--
  Created by IntelliJ IDEA.
  User: Jonathan
  Date: 29-11-2020
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
Vælg dine mål!
<form method="post">

    <label for="lengthName">Længde </label>
    <input type="number" class="form-control" id="lengthName" aria-describedby="lengthNameHelp" name="length">

    <label for="widthName">Bredde</label>
    <input type="number" class="form-control" id="widthName" name="width">

    <label for="emailName">Email </label>
    <input type="email" class="form-control" id="emailName" aria-describedby="emailNameHelp" name="email">
    <small id="emailNameHelp" class="form-text text-muted">Vi giver aldrig din email ud til andre!</small>

    <label for="phoneName">Tlf</label>
    <input type="text" class="form-control" id="phoneName" name="phone">
    <small id="phoneNameHelp" class="form-text text-muted">Vi giver aldrig dit nummer ud til andre!</small>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

</body>
</html>
