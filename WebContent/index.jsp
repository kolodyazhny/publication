<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/mytaglib.tld" prefix="mytag" %>
<html>
<head>
    <meta charset="UTF-8">

    <fmt:setLocale value="${sessionScope.local}" scope="session"/>
    <fmt:setBundle basename="local" var = "loc"/>
    <fmt:message bundle="${loc}" key="index.title" var="indexTitle"/>
    <fmt:message bundle="${loc}" key="index.header" var="indexHeader"/>

    <fmt:message bundle="${loc}" key="nav.login" var="navLogin"/>
    <fmt:message bundle="${loc}" key="nav.title" var="navTitle"/>
    <fmt:message bundle="${loc}" key="nav.main" var="navMain"/>

    <title>${indexTitle}</title>
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
                <li class="active"><a href="#">${navMain}<span class="sr-only">(current)</span></a></li>
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
                <li>
                    <form action="login.jsp" method="post">
                        <input class="btn btn-primary navbar-btn" type="submit" value="${navLogin}">
                    </form>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div class="container">
    <h2>${indexHeader}:</h2>
    <mytag:tabletag indexPage="true" publication="${requestScope.publicationList}"></mytag:tabletag>
</div>

</body>
</html>