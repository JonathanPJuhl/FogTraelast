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


    <form method="post" action="${pageContext.request.contextPath}/Orders/editOrder">

        <div class="row" align="center">

            <div class="col-md-12 order-md-1" align="center">
                <div class="col-md-6 mb-3" align="center">
                    <h2>Rediger ordre</h2>
                </div>

                <c:forEach var="orders" items="${requestScope.orderList}">

                    <div class="row">
                        <div class="col-md-6 mb-3" align="right">
                            <p> OdreID:</p>
                        </div>
                        <div class="col-md-6 mb-3" align="left">
                            <a href="<c:url value="/Orders/${orders.orderID}"/>">#<c:out value="${orders.orderID}"/></a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3" align="right">
                            <p id="element1">Ordrens status:</p>
                        </div>
                        <div class="col-md-6 mb-3" align="left">
                            <select id="element2" name="orderStatus">
                                <c:if test="${orders.orderStatus=='New'}">
                                    <option value="New" selected>Nyoprettet</option>
                                    <option value="Processing">Under Bearbejdning</option>
                                    <option value="Done">Færdiggjort</option>
                                </c:if>
                                <c:if test="${orders.orderStatus=='Processing'}">
                                    <option value="Processing" selected>Under Bearbejdning</option>
                                    <option value="New">Nyoprettet</option>
                                    <option value="Done">Færdiggjort</option>
                                </c:if>
                                <c:if test="${orders.orderStatus=='Done'}">
                                    <option value="Done" selected>Færdiggjort</option>
                                    <option value="New">Nyoprettet</option>
                                    <option value="Processing">Under Bearbejdning</option>
                                </c:if>

                            </select>
                        </div>
                    </div>
                <div class="row">
                    <div class="col-md-6 mb-3" align="right">
                        <p>Længde:</p>
                    </div>
                    <div class="col-md-6 mb-3" align="left">
                     <c:out value="${orders.length}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3" align="right">
                        <p>Bredde:</p>
                    </div>
                    <div class="col-md-6 mb-3" align="left">
                    <c:out value="${orders.width}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3" align="right">
                        <p>Ønsket tagtype:</p>
                    </div>
                    <div class="col-md-6 mb-3" align="left">
                        <select name="roofType">
                            <option value="Flat">Fladt tag</option>
                            <option value="Pitched">Tag med rejsning</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3" align="right">
                        <p>Ønskes skur:</p>
                    </div>
                    <div class="col-md-6 mb-3" align="left">
                        <select name="shedOrNo">
                            <option value=0>Ikke ønsket</option>
                            <option value="1">Ønsket</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3" align="right">
                        <p>Ønskes beklædning:</p>
                    </div>
                    <div class="col-md-6 mb-3" align="left">
                        <select name="cladding">
                            <option value=0>Ikke ønsket</option>
                            <option value="1">Ønsket</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3" align="right">
                        <p>Kundens tlf:</p>
                    </div>
                    <div class="col-md-6 mb-3" align="left">
                    <c:out value="${orders.customerPhone}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3" align="right">
                        <p>Kundens email:</p>
                    </div>
                    <div class="col-md-6 mb-3" align="left">
                    <c:out value="${orders.customerEmail}"/>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3" align="right">
                            <p>Pris:</p>
                        </div>
                        <div class="col-md-6 mb-3" align="left">
                    <input type="number" name="price" value="${orders.price}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3" align="right">
                            <p>tilknyttet sælger:</p>
                        </div>
                        <div class="col-md-6 mb-3" align="left">
                   <select name="salesmanID">
                        <c:forEach var="salesman" items="${requestScope.salesmen}">
                        <option value="${salesman.ID}">${salesman.name}</option>
                        </c:forEach>
                        </select>
                        </div>
                    </div>

                    <input type="submit" value="Ret til">

                </c:forEach>

            </div>

    </form>
</div>


</div>
