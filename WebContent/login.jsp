<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta charset="UTF-8">

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var = "loc"/>
    <fmt:message bundle="${loc}" key="login.title" var="loginTitle"/>
    <fmt:message bundle="${loc}" key="login.header" var="loginHeader"/>
    <fmt:message bundle="${loc}" key="login.login" var="loginLogin"/>
    <fmt:message bundle="${loc}" key="login.password" var="loginPassword"/>
    <fmt:message bundle="${loc}" key="login.enter.password" var="enterPassword"/>
    <fmt:message bundle="${loc}" key="login.email" var="enterEmail"/>
    <fmt:message bundle="${loc}" key="login.submit" var="loginSubmit"/>
    <fmt:message bundle="${loc}" key="login.registration" var="loginReg"/>

    <fmt:message bundle="${loc}" key="nav.title" var="navTitle"/>
    <fmt:message bundle="${loc}" key="nav.main" var="navMain"/>

    <title>${loginTitle}</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand">${navTitle}</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="index.jsp">${navMain}<span class="sr-only">(current)</span></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form action="">
                        <input type="hidden" name="locale" value="ru">
                        <button type="submit" class="btn btn-default navbar-btn"><img src="css/bootstrap/icons/russia.png"></button>
                    </form>
                </li>
                <li>
                    <form action="">
                        <input type="hidden" name="locale" value="en">
                        <button type="submit" class="btn btn-default navbar-btn"><img src="css/bootstrap/icons/united_kingdom.png"></button>
                    </form>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div class="container">
    <h2>${loginHeader}.</h2>
    <br>
    <form class="form-horizontal" role="form" action="LoginServlet" method="post">
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">${loginLogin}:</label>
            <div class="col-sm-10">
                <input name="login" type="email" class="form-control" id="email" placeholder="${enterEmail}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="pwd">${loginPassword}:</label>
            <div class="col-sm-10">
                <input name="password" type="password" class="form-control" id="pwd" placeholder="${enterPassword}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-success">${loginSubmit}</button>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="registration.jsp" class="btn btn-info" role="button">${loginReg}</a>
            </div>
        </div>
    </form>
</div>

</body>
</html>