<%--
  Created by IntelliJ IDEA.
  User: LKPDe
  Date: 27-11-2020
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form method="post">
    <h1>Welcome, please sign in:</h1>
    <label for="emailName">Email address</label>
    <input type="email" class="form-control" id="emailName" aria-describedby="emailNameHelp" name="email">
    <small id="emailNameHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    <label for="passName">Password</label>
    <input type="password" class="form-control" id="passName" name="pass">
    <button type="submit" class="btn btn-primary">Login</button>

</form>
<!--
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
</div>-->
