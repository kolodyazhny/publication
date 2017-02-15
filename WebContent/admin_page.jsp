<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta charset="UTF-8">

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var = "loc"/>
    <fmt:message bundle="${loc}" key="admin.title" var="adminTitle"/>
    <fmt:message bundle="${loc}" key="admin.new.periodical" var="newPeriodical"/>
    <fmt:message bundle="${loc}" key="admin.publication" var="publication"/>
    <fmt:message bundle="${loc}" key="admin.delete" var="delete"/>
    <fmt:message bundle="${loc}" key="admin.save" var="save"/>
    <fmt:message bundle="${loc}" key="admin.desc" var="desc"/>
    <fmt:message bundle="${loc}" key="admin.ISSN" var="ISSN"/>
    <fmt:message bundle="${loc}" key="admin.price" var="price"/>
    <fmt:message bundle="${loc}" key="admin.publisher" var="publisher"/>
    <fmt:message bundle="${loc}" key="admin.table.title" var="tableTitle"/>

    <fmt:message bundle="${loc}" key="admin.new.user" var="newUser"/>
    <fmt:message bundle="${loc}" key="admin.user" var="user"/>
    <fmt:message bundle="${loc}" key="admin.name" var="name"/>
    <fmt:message bundle="${loc}" key="admin.email" var="email"/>


    <fmt:message bundle="${loc}" key="nav.logout" var="navLogout"/>
    <fmt:message bundle="${loc}" key="nav.title" var="navTitle"/>
    <fmt:message bundle="${loc}" key="nav.main" var="navMain"/>

    <title>${adminTitle}</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
</head>
<body>

<%@include file="navbar.jsp"%>

<div class="container">

    <form action="new_periodical.jsp" style="width: 50px; margin: auto;">
        <button type="submit" class="btn btn-default">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            ${newPeriodical}
        </button>
    </form>

    <br>
<div class="container">
	<h2>${publication}:</h2>
</div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>${ISSN}</th>
            <th>${tableTitle}</th>
            <th>${publisher}</th>
            <th>${desc}</th>
            <th>${price}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.publicationList}" var="pub">
            <form  class="form-group" action="ChangePublication" method="post">
                <tr>
                    <td>
                        <input class="form-control" name="issn" type="text" value="${pub.ISSN}" />
                    </td>
                    <td>
                        <input class="form-control" name="title" type="text" value="${pub.title}" />
                    </td>
                    <td>
                        <input class="form-control" name="publisher" type="text" value="${pub.publisher}" />
                    </td>
                    <td>
                        <textarea class="form-control" rows="8" name="desc"><c:out value="${pub.description}"></c:out></textarea>
                    </td>
                    <td>
                        <input class="form-control" name="price" type="text" value="${pub.price}" />
                    </td>
                    <td>
                        <button class="btn btn-success btn-md" type="submit" name="save">${save}</button>
                        <input type="hidden" name="id" value="${pub.id}"/>
                    </td>
                    <td>
                        <button class="btn btn-danger btn-md" type="submit" name="delete">${delete}</button>
                    </td>
                </tr>
            </form>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>