<%-- 
    Document   : contact
    Created on : 12.07.2019, 10:21:33
    Author     : DPorter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="resources/css/main.css" />
        <title>JSP Page</title>
    </head>
    <body>
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
        <div class="container text-center">
            <h1>Kontact</h1> 
            <p></p>

        </div>
        <div class="container-fluid bg-grey">
            <h2 class="text-center"></h2>
            <div class="row">
                <div class="col-sm-5">
                    <h2>Kontakt :</h2>
                    <p><span class="glyphicon glyphicon-map-marker"></span>BS Reiseveranstalter FLY2</p>
                    <p><span class="glyphicon glyphicon-phone"></span> +49 1515151515</p>
                    <p><span class="glyphicon glyphicon-envelope"></span> contact@bstofly2.de</p> 
                </div>
                <div class="col-sm-5">
                    <h2>Impress :</h2>
                    <p><span class="glyphicon glyphicon-map-marker"></span>BS Reiseveranstalter FLY2</p>
                    <p><span class="glyphicon glyphicon-envelope"></span> Ihhaber Nuna Asli</p> 
                    <p><span class="glyphicon glyphicon-envelope"></span> steuerNummer</p> 
                   
                </div>
                <div class="col-sm-7">
                    <h3> Formular:</h3>
                    <div class="row">
                        <div class="col-sm-6 form-group">
                            <input class="form-control" id="name" name="name" placeholder="Name" type="text" required>
                        </div>
                        <div class="col-sm-6 form-group">
                            <input class="form-control" id="email" name="email" placeholder="Email" type="email" required>
                        </div>
                    </div>
                    <textarea class="form-control" id="comments" name="comments" placeholder="Nachricht" rows="3"></textarea><br>
                    <div class="row">
                        <div class="col-sm-12 form-group">
                            <button class="btn btn-default pull-left" type="submit">Senden</button>
                        </div>
                    </div> 
                </div>
            </div>
        </div>


        <div class="footer">
            <footer class="container-fluid text-center">
                <a href=index.jsp" title="To Top">
                    <span class="glyphicon glyphicon-chevron-up"></span>
                </a>
                <p>&copy; Copyright S.Bopp und A.Soeke - <a href="https://www.Bsreiseveranstalter.de" title="Visit us">www.Bsreiseveranstalter.de</a></p> 
            </footer>
        </div>
    </body>
</html>
