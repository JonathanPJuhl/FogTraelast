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

    <!-- for smartphone -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Style css -->
    <link rel="stylesheet" href="style.css">

    <title>${requestScope.title}</title>

    </head>
    <body>


    <!--navbar-->
    <div class="container navbar-dark bg-light">
        <header class="blog-header py-3">
            <div class="row flex-nowrap justify-content-between align-items-center">
                <div class="col-4 pt-1">
                    <p class="text-white">Velkommen til <!--TODO ${user.name} --></p>
                </div>
                <div class="col-4 text-center">
                    <a class="navbar-brand" href="${Index}">
                        <img src="images/FogLogo.png" style="width:5vw" class="d-inline-block">
                        Byggemarked
                    </a>
                </div>
                <div class="col-4 d-flex justify-content-end align-items-center">
                    <a class="btn btn-sm btn-outline-secondary" href="#">Login</a>
                </div>
            </div>
        </header>

        <div class="py-1 mb-2">
            <nav class="nav d-flex justify-content-center nav-menu">
                <a class="p-2 px-4 nav-link text-white " href="${CustomMeasures}">Start Byg!</a>
                <a class="p-2 px-4 nav-link text-white" href="#">Ordre</a>
                <a class="p-2 px-4 nav-link text-white" href="#">Status</a>

            </nav>
        </div>
    </div>


    <jsp:include page="${requestScope.content}" flush="true"/>


</main>
</body>

</html>