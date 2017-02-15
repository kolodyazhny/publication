<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta charset="UTF-8">

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var = "loc"/>
    <fmt:message bundle="${loc}" key="new.subsc.title" var="Title"/>
    <fmt:message bundle="${loc}" key="new.subsc.hi" var="Hi"/>
    <fmt:message bundle="${loc}" key="new.subsc.message" var="message"/>

    <fmt:message bundle="${loc}" key="nav.logout" var="navLogout"/>
    <fmt:message bundle="${loc}" key="nav.title" var="navTitle"/>
    <fmt:message bundle="${loc}" key="nav.main" var="navMain"/>

    <title>${Title}</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
</head>
<body>
<%@include file="navbar.jsp"%>

<div class="container">
    <h1>${Hi}, ${sessionScope.currentUser}! ${message} ${sessionScope.currentLogin}</h1>
</div>
</body>

</html>