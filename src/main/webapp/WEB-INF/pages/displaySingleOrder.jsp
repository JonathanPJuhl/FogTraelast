<%--
  Created by IntelliJ IDEA.
  User: Jonathan
  Date: 07-12-2020
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="scrollbar" id="styleFog">

        <div class="row" align="center">
<c:forEach var="orders" items="${requestScope.orderList}">
            <div class="col-md-12 order-md-1" align="center">
                <div class="col-md-6 mb-3" align="center">
                    <h2>Ordre tilhørende:</h2>
                    <h6>${orders.customerEmail}</h6>
                </div>



                    <div class="row">
                        <div class="col-md-6 mb-3" align="right">
                            <b><p> OdreID:</p></b>
                        </div>
                        <div class="col-md-6 mb-3" align="left">
                            <a href="<c:url value="/Orders/${orders.orderID}"/>">#<c:out value="${orders.orderID}"/></a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3" align="right">
                            <b><p id="element1">Ordrens status:</p></b>
                        </div>
                        <div class="col-md-6 mb-3" align="left">

                                <c:if test="${orders.orderStatus=='New'}">
                                    Nyoprettet
                                </c:if>
                                <c:if test="${orders.orderStatus=='Processing'}">
                                    Under Bearbejdning
                                </c:if>
                                <c:if test="${orders.orderStatus=='Done'}">
                                    Færdiggjort
                                </c:if>


                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3" align="right">
                            <b><p>Længde:</p></b>
                        </div>
                        <div class="col-md-6 mb-3" align="left">
                            <c:out value="${orders.length}"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3" align="right">
                            <b><p>Bredde:</p></b>
                        </div>
                        <div class="col-md-6 mb-3" align="left">
                            <c:out value="${orders.width}"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3" align="right">
                            <b><p>Ønsket tagtype:</p></b>
                        </div>
                        <div class="col-md-6 mb-3" align="left">
                            <c:out value="${orders.roofType}"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3" align="right">
                            <b><p>Ønskes skur:</p></b>
                        </div>
                        <div class="col-md-6 mb-3" align="left">
                            <c:if test="${orders.shedOrNo==0}">
                                Nej
                            </c:if>
                            <c:if test="${orders.shedOrNo==1}">
                                Ja
                            </c:if>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3" align="right">
                            <b><p>Ønskes beklædning:</p></b>
                        </div>
                        <div class="col-md-6 mb-3" align="left">
                           <c:if test="${orders.wallsOrNo==0}">
                               Nej
                           </c:if>
                            <c:if test="${orders.wallsOrNo==1}">
                                Ja
                            </c:if>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3" align="right">
                            <b><p>Tlf:</p></b>
                        </div>
                        <div class="col-md-6 mb-3" align="left">
                            <c:out value="${orders.customerPhone}"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3" align="right">
                            <b><p>Mail:</p></b>
                        </div>
                        <div class="col-md-6 mb-3" align="left">
                            <c:out value="${orders.customerEmail}"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3" align="right">
                            <b><p>Pris:</p></b>
                        </div>
                        <div class="col-md-6 mb-3" align="left">
                                ${requestScope.priceWithCoverage} kr.
                        </div>
                    </div>


                </c:forEach>

                <div class="row">
                    <div class="col-md-12 mb-3" align="center">
                <h2 align="center">Stykliste:</h2>
                </div>
                </div>
                <table border="1" cellpadding="5" align="center">
                    <tr>
                        <th>OrdreID</th>
                        <th>MaterialeID</th>
                        <th>Længde</th>
                        <th>Bredde</th>
                        <th>Materialets beskrivelse</th>
                        <th>antal</th>
                    </tr>
                    <c:forEach var="bom" items="${requestScope.bomList}">
                        <tr>
                            <td><c:out value="${bom.orderID}"/> </td>
                            <td><c:out value="${bom.materialID_By_Category}" /></td>
                            <td><c:out value="${bom.length}" /></td>
                            <td><c:out value="${bom.width}" /></td>
                            <td><c:out value="${bom.description}" /></td>
                            <td><c:out value="${bom.quantity}" /></td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="row">
                    <div class="col-md-12 mb-3" align="center">
                        <h2 align="center">Plantegning:</h2>
                    </div>
                </div>
                <%--<svg class="banner" width="${sessionScope.construction1.roof.width}" height="${sessionScope.construction1.roof.length}">
                    ${sessionScope.svgCarport}

                </svg>--%>
                    </div>

            </div>

        </div>
</div>



