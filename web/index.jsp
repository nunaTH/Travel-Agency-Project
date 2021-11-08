<%-- 
    Document   : index
    Created on : 03.09.2019, 15:13:43
    Author     : sbopp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
   
        <link rel="stylesheet" href="file/bootstrap.min.css">
        <script type="text/javascript" src="file/bootstrap.min.js"></script>
        <link rel="stylesheet" href="resources/css/main.css" />
        <link rel="stylesheet" href="resources/css/search.css" />
        <link rel="stylesheet" href="resources/css/table.css" />
        <link rel="stylesheet" href="resources/css/login.css" />
        <title>Home</title>
    </head>
    <body>
        <div>
            <nav class="navbar navbar-default navbar-fixed-top">
                <div class="container" style="margin-top: 20px">
                    <div class="navbar-header">
                        <h1 style="text-align: center">BS Reiseveranstalter FLY2</h1>

                    </div>
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav navbar-right">
                            <li style="padding-right: 10px;"><a href="index.jsp">Home</a></li>
                            <li style="padding-right: 10px;"><a href="login.jsp">LOG-IN</a></li>
                            <li style="padding-right: 10px;"><a href="about.jsp">Über FLY</a></li>
                            <li style="padding-right: 10px;"><a href="buchungen.jsp">Buchungen</a></li>
                            <li style="padding-right: 10px;"><a href="contact.jsp">Kontakt</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
        <div class="container-fluid text-center bg-grey">
            <div class="jumbotron text-center">
                <div class="row"> 
                    <h2>Unseren Angeboten?</h2>
                    <h4>Bitte wählen Sie Ihre liebling Stadtreise </h4>
                    <div class="row text-center">
                        <div class="col-sm-4">
                            <div class="thumbnail">
                                <img class="img-circle"src="resources/images/nuernberg.jpg"width="200px" height="150px" alt="Deutschland">
                                <p><strong>Nürnberg : Deutschland</strong></p>
                            </div>
                        </div> 
                        <div class="col-sm-4">
                            <div class="thumbnail">
                                <img class="img-circle"src="resources/images/Singapore.jpg" width="200px" height="150px" alt="Singapur">
                                <p><strong>Singapur : Singapur</strong></p>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="thumbnail">
                                <img class="img-circle"src="resources/images/newyork2.jpg" width="200px" height="150px" alt="USA">
                                <p><strong>New York : USA</strong></p>
                            </div>
                        </div>

                    </div> 
                    <h4>Und mehr...<a href="login.jsp">Klick hier zum Login </a></h4>
                </div>
            </div>
        </div>
        <footer class="container-fluid text-center">
            <a href="index.jsp" title="To Top">
                <span class="glyphicon glyphicon-chevron-up"></span>
            </a>
            <p>&copy; Copyright Surattana Bopp und Alihan Soeke - <a href="https://www.fly2.de" title="Visit us">www.fly2.de</a></p> 
        </footer>
    </body> 
</html>
