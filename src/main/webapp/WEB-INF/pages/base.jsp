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
    <!-- TODO In case det ikke virker på andres computere
    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sticky-footer-navbar/">
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet"> -->

    <!-- Style css -->
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css"/>

    <!--Icon in tab -->
    <link rel="icon" href="${pageContext.request.contextPath}/images/FogLogo.png"/>


    <title>${requestScope.title}</title>

</head>
<body>
<main>


    <!--navbar-->

    <nav class="container navbar-dark bg-light">
        <header class="blog-header py-3">
            <div class="row flex-nowrap justify-content-between align-items-center">
                <div class="col-4 pt-1">
                    <p class="text-white">Velkommen til <h6 class="reset-anchor text-primary"> ${requestScope.list.toString()}</h6> </p>
                </div>
                <div class="col-4 text-center">
                    <a class="navbar-brand" href="${Index}">
                        <img src="${pageContext.request.contextPath}/images/FogLogo.png" style="width:5vw" class="d-inline-block">
                        Byggemarked
                    </a>
                </div>
                <div class="col-4 d-flex justify-content-end align-items-center">
                    <a class="btn btn-sm btn-outline-secondary" href="#">Login</a>
                </div>
            </div>
        </header>
    </nav>
    <nav class="container navbar-dark bg-light">
        <div class="py-1 mb-2">
            <ul class="nav d-flex justify-content-center nav-menu">
                <a class="p-2 px-4 nav-link text-white " href="${CustomMeasures}">Start Byg!</a>
                <a class="p-2 px-4 nav-link text-white" href="#">Ordre</a>
                <a class="p-2 px-4 nav-link text-white" href="#">Status</a>

            </ul>
        </div>
    </nav>
</main>
<jsp:include page="${requestScope.content}" flush="true"/>
<!--footer-->
<footer class="fixed-footer">
    <div class="container bg-light pl-10">
        <div class="row text-white">
            <div class="col pt-2 ">
                <h6 class="mb-1"><p class="reset-anchor text-primary mb-0">Kontakt kundeservice: </p></h6>
                <p class="mb-0">+45 00 00 00 00 </p>
                <p>detteerikkeenmail@snyd.dk</p></div>
            <div class="col pt-2"><h6>Adresse:</h6>
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d71799.63231630497!2d12.
                    43860577738041!3d55.7804132433618!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46524e0cd2198e
                    09%3A0xca6a70136052238a!2s2800%20Kongens%20Lyngby!5e0!3m2!1sda!2sdk!4v1606917855476!5m2!1sda!2sdk"
                        width="200vw" height="75vh" frameborder="0" style="border:0;" allowfullscreen="true"
                        aria-hidden=
                                "false" tabindex="0"></iframe>
            </div>
            <div class="col pt-2">
                <ul class="list-unstyled">
                    <li>
                        <h6 class="mb-1"><a class="reset-anchor text-primary">Mandag til
                            fredag</a></h6>
                        <p class=" text-small text-fancy mb-0">Kl. 08:00 - 17.00</p>
                    </li>
                    <li class="mb-1">
                        <h6 class="mb-1"><a class="reset-anchor text-primary">Lørdage</a></h6>
                        <p class=" text-small text-fancy mb-0">Kl. 10:00 - 14.00</p>
                    </li>
                </ul>
            </div>

        </div>
    </div>

</footer>

</body>

</html>