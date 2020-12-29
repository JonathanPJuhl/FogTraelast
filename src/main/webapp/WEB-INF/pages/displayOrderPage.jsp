<%@ page contentType="text/html;charset=UTF-8"%>
<div class="scrollbar" id="styleFog">
    <div class="content">
        <h1>Velkommen!</h1>
        <h1>${requestScope.list.toString()}</h1>

        <form id="login_button" action="${pageContext.request.contextPath}/CreateOrder">
            <input type="submit" value="Opret endnu en ordre!" />
        </form>
    </div>
</div>