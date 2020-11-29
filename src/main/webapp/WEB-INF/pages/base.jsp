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

    <!-- SKAL FIXES -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
          crossorigin="anonymous">
    <!-- SKAL FIXES -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css"/>
    <title>${requestScope.title}</title>

</head>
<!-- SKAL FIXES -->
<body class="d-flex flex-column h-100">
<jsp:include page="/WEB-INF/contains/navbar.jsp" flush="true"/>
<!--<div style="background-image: url('$');"> -->
<main role="main" class="container">
    <jsp:include page="${requestScope.content}" flush="true"/>
    </div>

</main>
<!-- SKAL FIXES -->
<!--<jsp:include page="/WEB-INF/contains/footer.jsp" flush="true"/>-->

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</body>
<!---
<body>

TODO
$<jsp:include page="${requestScope.content}"></jsp:include>

<%@ include file = "/WEB-INF/customMeasures.jsp" %>

</body>-->
</html>