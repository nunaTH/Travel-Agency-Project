<%-- 
    Document   : anmeldungErfolg
    Created on : 05.09.2019, 23:41:34
    Author     : Surattana
--%>
<%@page import= "java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="resources/css/main.css" />
        <link rel="stylesheet" href="resources/css/search.css" />

        <title>Register</title>
    </head>
    <body>
        <%

            Connection connect = null;
            Statement s = null;
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                //connect = DriverManager.getConnection("jdbc:sqlserver://10.140.130.16:1433;"
                //        + " databaseName=BS;user=dba5;password=dba5;");
                connect = DriverManager.getConnection("jdbc:sqlserver://localhost\\CDT;"
                        + "databaseName=BS;user=sa;password=nuna13");
                if (connect != null) {
                    out.println("Datenbank verbinden");
                } else {
                    out.println("Datenbank fehlen");
                }
                String username = request.getParameter("Username");
                String password = request.getParameter("Password");

                s = connect.createStatement();

                // String sql = "SELECT * FROM  BsUser ORDER BY vorname ASC";
                String sql = "SELECT * FROM  BsUser WHERE "
                        + " Username = '" + username + "' AND "
                        + " Password = '" + password + "' ";
                ResultSet rec = s.executeQuery(sql);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                out.println(e.getMessage());
                e.printStackTrace();
            }

            try {
                if (s != null) {
                    s.close();
                    connect.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                out.println(e.getMessage());
                e.printStackTrace();
            }

        %>
        <div>
            <nav class="navbar navbar-default navbar-fixed-top">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span> 
                        </button>
                        <a class="navbar-brand" href="index.jsp">BS Reiseveranstalter FLY2</a>
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
            <div class="jumbotron text-center">
                <h2>Willkommen </h2>
                <h2>die Anmeldung ist erfolg!</h2>
                <center><a href="buchungen.jsp"><h4>klick hier zu BS Reiseveranstalter FLY2...</h4></a></center>
            </div>
            <br><br>
            <div class="footer">
                <footer class="container-fluid text-center">
                    <a href="index.jsp" title="To Top">
                        <span class="glyphicon glyphicon-chevron-up"></span>
                    </a>
                    <p>&copy; Copyright Surattana Bopp und Alihan Soeke - <a href="https://www.fly2.de" title="Visit us">www.fly2.de</a></p> 
                </footer>
            </div>
    </body>
</html>