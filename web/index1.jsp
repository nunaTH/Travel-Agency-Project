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
        <link rel="stylesheet" href="resources/css/main.css" />
        <title>Home</title>
    </head>
    <body>
        <div>
            <nav class="navbar navbar-default navbar-fixed-top">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span> 
                        </button>
                        <a class="navbar-brand" href="home.jsp">BS Reiseveranstalter FLY2</a>
                    </div>
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav navbar-right">
                            <li style="padding-right: 5px;"><a href="login.jsp">LOG-IN</a></li>
                            <li><a href="about.jsp">Über FLY</a></li>
                            <li><a href="services.jsp">Buchungen</a></li>
                            <li><a href="contact.jsp">Kontakt</a></li>
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
                                <img src="images/Paris.jpg" alt="Frankeich">
                                <p><strong>Paris</strong></p>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="thumbnail">
                                <img src="images/Nuernberg.jpg" alt="Deutschland">
                                <p><strong>Nürnberg</strong></p>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="thumbnail">
                                <img src="images/New York.jpg" alt="USA">
                                <p><strong>New York</strong></p>
                            </div>
                        </div>
                    </div> 
                    <div class="row text-center">
                          <div class="row text-center">
                            <div class="col-sm-4">
                                <div class="thumbnail">
                                    <img src="images/frankfurt.jpg" alt="Deutschland">
                                    <p><strong>Frankfurt</strong></p>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="thumbnail">
                                    <img src="images/Nuernberg.jpg" alt="Italien">
                                    <p><strong>Rom</strong></p>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="thumbnail">
                                    <img src="images/New York.jpg" alt="Japan">
                                    <p><strong>Tokyo</strong></p>
                                </div>
                            </div>
                        </div> 
                    </div>
                    <div class="row text-center">
                          <div class="row text-center">
                            <div class="col-sm-4">
                                <div class="thumbnail">
                                    <img src="images/frankfurt.jpg" alt="Thailand">
                                    <p><strong>Bangkok</strong></p>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="thumbnail">
                                    <img src="images/Nuernberg.jpg" alt="Singapur">
                                    <p><strong>Singapur</strong></p>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="thumbnail">
                                    <img src="images/New York.jpg" alt="USA">
                                    <p><strong>San Franciso</strong></p>
                                </div>
                            </div>
                        </div> 
                    </div>
                      <h4>Und mehr...<a href="Flughafen.jsp">Klick hier</a></h4>
                </div>
            </div>
        </div>
        <footer class="container-fluid text-center">
            <a href="home.jsp" title="To Top">
                <span class="glyphicon glyphicon-chevron-up"></span>
            </a>
            <p>&copy; Copyright Surattana Bopp und Alihan Soeke - <a href="https://www.fly2.de" title="Visit us">www.fly2.de</a></p> 
        </footer>
    </head>

</html>
