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
                <li>
                    <form action="LogoutServlet"  method="post">
                        <input class="btn btn-primary navbar-btn" type="submit" value="${sessionScope.currentUser} ${navLogout}">
                    </form>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
