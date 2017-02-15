<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/mytaglib.tld" prefix="mytag"%>
<%@taglib uri="/WEB-INF/tld/mytaglib.tld" prefix="mytag2"%>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var = "loc"/>
    <fmt:message bundle="${loc}" key="catalog.title" var="catalogTitle"/>
    <fmt:message bundle="${loc}" key="catalog.header" var="tableHeader"/>
    <fmt:message bundle="${loc}" key="new.score" var="newScore"/>

    <fmt:message bundle="${loc}" key="nav.logout" var="navLogout"/>
    <fmt:message bundle="${loc}" key="nav.title" var="navTitle"/>
    <fmt:message bundle="${loc}" key="nav.main" var="navMain"/>

    <title>${catalogTitle}</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
</head>
<body>

<%@include file="navbar.jsp"%>
<div class="container">
    <form action="add_score.jsp" style="width: 50px; margin: auto;">
        <button type="submit" class="btn btn-default">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            ${newScore}
        </button>
    </form>
    <br>
</div>

<div class="container">
	<h2>${publication}:</h2>
</div>
<c:out value = "${requestScope.scoreList}"/>
    <table class="table table-hover">

        <tbody>
        <c:forEach items="${requestScope.scoreList}" var="pub">

            <form  class="form-group"  method="post">
                <tr>
                    <td>
                        <input class="form-control" name="name" type="text" value="${pub.name}" />
                    </td>
                    <td>
                        <input class="form-control" name="number" type="text" value="${pub.cardNumber}" />
                    </td>
                    <td>
                        <input class="form-control" name="cvc" type="text" value="${pub.cvc}" />
                    </td>
                </tr>
            </form>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="container">
    <h2>${tableHeader}</h2>
    <mytag:tabletag indexPage="false" publication="${requestScope.publicationList}"></mytag:tabletag>
</div>


</body>
</html>