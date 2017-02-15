<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta charset="UTF-8">

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var = "loc"/>
    <fmt:message bundle="${loc}" key="claim.title" var="claimTitle"/>
    <fmt:message bundle="${loc}" key="claim.header" var="claimHeader"/>
    <fmt:message bundle="${loc}" key="claim.header.title" var="perTitle"/>
    <fmt:message bundle="${loc}" key="claim.header.price" var="perPrice"/>
    <fmt:message bundle="${loc}" key="claim.card.header" var="cardHeader"/>
    <fmt:message bundle="${loc}" key="claim.card.number" var="cardNumber"/>
    <fmt:message bundle="${loc}" key="claim.card.cvc" var="cardCVC"/>
    <fmt:message bundle="${loc}" key="claim.card.name" var="cardName"/>
    <fmt:message bundle="${loc}" key="claim.get" var="claimGet"/>

    <fmt:message bundle="${loc}" key="nav.logout" var="navLogout"/>
    <fmt:message bundle="${loc}" key="nav.title" var="navTitle"/>
    <fmt:message bundle="${loc}" key="nav.main" var="navMain"/>

    <title>${claimTitle}</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
</head>
<body>

<%@include file="navbar.jsp"%>

<div class="container">
    <div class="row">
        <h2>${claimHeader}:</h2>
        <h4>${perTitle}: ${requestScope.title}</h4>
        <h4>${perPrice}: ${requestScope.price}</h4>
    </div>

    <div class="row">
        <h3>${cardHeader}.</h3>
        <br>
        <form class="form-horizontal" role="form" action="Subscribe" method="post">

            <input type="hidden" name="perID" value="${requestScope.perID}">

            <div class="form-group">
                <label class="control-label col-sm-2" for="card">${cardNumber}:</label>
                <div class="col-sm-10">
                    <input type="text" name="number" class="form-control" id="card">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="cvc">${cardCVC}:</label>
                <div class="col-sm-10">
                    <input type="text" name="cvc" class="form-control" id="cvc">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">${cardName}:</label>
                <div class="col-sm-10">
                    <input type="text" name="name" class="form-control" id="name">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-info">${claimGet}</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>