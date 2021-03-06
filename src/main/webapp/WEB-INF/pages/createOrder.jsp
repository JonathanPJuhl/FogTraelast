<%--
  Created by IntelliJ IDEA.
  User: Jonathan og Cathrine
  Date: 29-11-2020
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
<div class="scrollbar" id="styleFog">
    <div align="center">
        <h1>Vælg dine mål</h1>
        <form method="post" action="${pageContext.request.contextPath}/Orders/create">

            <label for="lengthName">Længde (cm) </label>
            <input type="number" class="form-control" id="lengthName" aria-describedby="lengthNameHelp" name="length">
            <small class="form-text text-muted">NB! Dette er med de 18 cm's udhæng af tag</small>

            <label for="widthName">Bredde (cm)</label>
            <input type="number" class="form-control" id="widthName" name="width">
            <small class="form-text text-muted">NB! Dette er med de 18 cm's udhæng af tag</small>

            <label for="roofType">Tag type</label>
            <select class="form-control" name="roofType" id="roofType">
                <option value="Flat">Fladt tag</option>
                <option value="Pitched">Tag med rejsning</option>
            </select>

            <label for="shedOrNo">Vil du have et skur? </label>
            <select class="form-control" name="shedOrNo" id="shedOrNo">
                <option value=1>Ja</option>
                <option value=0>Nej</option>
            </select>

            <label for="cladding">Beklædning</label>
            <select class="form-control" name="cladding" id="cladding">
                <option value=1>Med vægge(3 stk)</option>
                <option value=0>Uden vægge</option>
            </select>

            <label for="emailName">Email </label>
            <input type="email" class="form-control" id="emailName" aria-describedby="emailNameHelp" name="email">
            <small id="emailNameHelp" class="form-text text-muted">Vi giver aldrig din email ud til andre!</small>

            <label for="phoneName">Tlf</label>
            <input type="text" class="form-control" id="phoneName" name="phone">
            <small id="phoneNameHelp" class="form-text text-muted">Vi giver aldrig dit nummer ud til andre!</small>
            <br>
            <button type="submit" class="btn btn-primary">Bestil videre på carport!</button>
        </form>
    </div>
</div>
</div>