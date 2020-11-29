<%--
  Created by IntelliJ IDEA.
  User: Lars og Cathrine
  Date: 27-11-2020
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


    <title>${requestScope.title}</title>

</head>
<body>
<h1> HEJ </h1>
<main role="main" class="container">
    <jsp:include page="${requestScope.content}" flush="true"/>


</main>
</body>

</html>