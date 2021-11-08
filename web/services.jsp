<%-- 
    Document   : services
    Created on : 11.07.2019, 13:05:52
    Author     : edicu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="resources/css/main.css" />
        <link rel="stylesheet" href="resources/css/login.css" />
        <title>Services</title>
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span> 
                    </button>
                    <a class="navbar-brand" href="home.jsp">DBP Flight Shop</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="about.jsp">ABOUT</a></li>
                        <li><a href="services.jsp">SERVICES</a></li>
                        <li><a href="#portfolio">AJAX</a></li>
                        <li><a href="contact.jsp">CONTACT</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <br><br>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-8">
                    <h2>Our services</h2>
                    <h4>DBP Flight Shop has great services4u</h4> 
                    <p>- We offer you the best flightservice of the world.<br>
                       - If you have lost your flight back, we will let you another week there so you can enjoy your extended stay<br>
                       - No matter if your baggage get lost, you can buy all your lost things in our DBP-LostAndFound-Shop (coming soon)<br>
                        <br>That's just a small selection of our services. There are several more!</p>
                    <button class="btn btn-default btn-lg">Click here for spending money</button>
                </div>
                <div class="col-sm-4">
                    <span class="glyphicon glyphicon-wrench logo"></span>
                </div>
            </div>
        </div>

        <div class="container-fluid bg-grey">
            <div class="row">
                <div class="col-sm-4">
                    <span class="glyphicon glyphicon-globe logo"></span> 
                </div>
                <div class="col-sm-8">
                    <h2>Our Values</h2>
                    <h4><strong>MONEY:</strong> We love it, and we want more. We also love "Freibier" ;-) </h4> 
                    <p><strong>CUSTOMERS:</strong> We hate them, just pay up and shut up. </p>
                </div>
            </div>
        </div>
        
        <div class="footer">
            <footer class="container-fluid text-center">
                <a href="#home.jsp" title="To Top">
                    <span class="glyphicon glyphicon-chevron-up"></span>
                </a>
                 
            </footer>
        </div>
    </body>
</html>
