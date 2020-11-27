<%--
  Created by IntelliJ IDEA.
  User: LKPDe
  Date: 27-11-2020
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="container">
    <div class="row card hoverable">
        <div class="card-content ">
            <h4 class="center blue-text">Login Form</h4>
                <input type="hidden" name="target" value="login" >

                <div class="col s12">
                    <div class="input-field">
                        <input type="text" name="email" placeholder="Username*">
                    </div>
                </div>
                <div class="col s12">
                    <div class="input-field">
                        <input type="password" name="password" placeholder="Password*">
                    </div>
                </div>
                <div class="col s12">
                    <p><label><input type="checkbox"></label></p>
                </div>
                <div class="col s12 center">
                    <button type="submit" value="Submit" class="btn btn-large waves-effect waves-light blue">Login<i class="material-icons right">send</i></button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
