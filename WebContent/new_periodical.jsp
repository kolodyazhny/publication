<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta charset="UTF-8">

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var = "loc"/>
    <fmt:message bundle="${loc}" key="new.per.title" var="Title"/>
    <fmt:message bundle="${loc}" key="new.per.ISSN" var="ISSN"/>
    <fmt:message bundle="${loc}" key="new.per.enterISSN" var="enterISSN"/>
    <fmt:message bundle="${loc}" key="new.per.perTitle" var="perTitle"/>
    <fmt:message bundle="${loc}" key="new.per.enter.perTitle" var="enterTitle"/>
    <fmt:message bundle="${loc}" key="new.per.publisher" var="publisher"/>
    <fmt:message bundle="${loc}" key="new.per.enter.publisher" var="enterPublisher"/>
    <fmt:message bundle="${loc}" key="new.per.desc" var="perDesc"/>
    <fmt:message bundle="${loc}" key="new.per.save" var="perSave"/>
    <fmt:message bundle="${loc}" key="new.per.price" var="perPrice"/>
    <fmt:message bundle="${loc}" key="new.per.enter.price" var="enterPrice"/>


    <fmt:message bundle="${loc}" key="nav.logout" var="navLogout"/>
    <fmt:message bundle="${loc}" key="nav.title" var="navTitle"/>
    <fmt:message bundle="${loc}" key="nav.main" var="navMain"/>

    <title>${Title}</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
</head>
<body>

<%@include file="navbar.jsp"%>

<div class="container">
    <form action="AddPeriodical" method="post" class="form-horizontal" role="form">
        <div class="form-group">
            <label class="control-label col-sm-2" for="issn">${ISSN}:</label>
            <div class="col-sm-10">
                <input type="text" name="issn" class="form-control" id="issn" placeholder="${enterISSN}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="title">${perTitle}:</label>
            <div class="col-sm-10">
                <input type="text" name="title" class="form-control" id="title" placeholder="${enterTitle}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="pub">${publisher}:</label>
            <div class="col-sm-10">
                <input type="text" name="publisher" class="form-control" id="pub" placeholder="${enterPublisher}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="price">${perPrice}:</label>
            <div class="col-sm-10">
                <input type="text" name="price" class="form-control" id="price" placeholder="${enterPrice}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="desc">${perDesc}:</label>
            <div class="col-sm-10">
                <textarea class="form-control" rows="8" name="desc" id="desc"></textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-success">${perSave}</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>