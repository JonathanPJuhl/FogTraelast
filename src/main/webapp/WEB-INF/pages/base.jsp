<%--
  Created by IntelliJ IDEA.
  User: Lars og Cathrine
  Date: 27-11-2020
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">

    <!-- for smartphone -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <!-- Style css -->

    <link rel="stylesheet" href="<c:url value="/style.css"></c:url>"/>

    <!--Icon in tab -->
    <link rel="icon" href="<c:url value="/images/FogLogo.png"></c:url>"/>

    <title>${requestScope.title}</title>

</head>


<body>
<div id="page-container">
    <div id="content-wrap">
<!--navbar-->
<jsp:include page="../contains/navbar.jsp" flush="true"/>

<main class="container">

    <jsp:include page="${requestScope.content}" flush="true"/>

</main>

<!--footer-->
<jsp:include page="../contains/footer.jsp" flush="true"/>
</div>
</div>
</body>

</html>