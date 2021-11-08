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

        <title>Registieren</title>
    </head>
    <body>
        <%
            Connection connect = null;
            Statement s = null;

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                //connect = DriverManager.getConnection("jdbc:sqlserver://10.140.130.16:1433;"
                //       + " databaseName=BS;user=dba5;password=dba5;");
                connect = DriverManager.getConnection("jdbc:sqlserver://localhost\\CDT;"
                        + "databaseName=BS;user=sa;password=nuna13");
                if (connect != null) {
                    out.println("Datenbank verbinden");
                } else {
                    out.println("Datenbank fehlen");
                }
                s = connect.createStatement();

                String user = request.getParameter("username");
                String pwd = request.getParameter("passwort");

                String sql = "SELECT * FROM  BsUser WHERE "
                        + " username = '" + user + "' AND "
                        + " passwort = '" + pwd + "' ";

                if (user.equals("") && pwd.equals("")) {
                    response.sendRedirect("loginErfolg.jsp");
                } else {
                    response.sendRedirect("anmeldung.jsp");
                }

                sql = "SELECT Top 1 * FROM  BsUser where username='" + user.toString() + "' ";
                ResultSet rec = s.executeQuery(sql);

        %>

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
            <div class="container-fluid text-center bg-grey centered">
                <div class="jumbotron text-center">
                    <h2>Willkommen </h2>
                    <h2>Ihr Log-In ist erfolg!</h2>
                    <div> <table width="600" border="0" align="center">
                            <tr class="text-center">
                                <th width="40"> <div >Vorname</div></th>
                                <th width="40"> <div >Nachname</div></th>
                                <th width="20"> <div >Username </div></th>
                                    <%-- <th width="20"> <div >Passwort </div></th> --%>

                            </tr>

                            <%while (rec.next()) {%>
                            <tr class="text-left">
                                <td><div ><%=rec.getString("vorname")%></div></td>
                                <td><%=rec.getString("nachname")%></td>
                                <td><%=rec.getString("username")%></td>
                                <%-- <td><%=rec.getString("passwort")%></td> --%>
                            </tr>
                            <%}%>
                        </table>
                    </div>
                    <center><a href="buchungen.jsp"><h4>klick hier zu BS Reiseveranstalter FLY2...</h4></a></center>
                </div>

            </div>
        </div>
    </div>
    <br><br>

    <%
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

    <div class="footer">
        <footer class="container-fluid text-center">
           <%-- <a href="index.jsp" title="To Top">
                <span class="glyphicon glyphicon-chevron-up"></span>
            </a> --%>
            <p>&copy; Copyright Surattana Bopp und Alihan Soeke - <a href="https://www.fly2.de" title="Visit us">www.fly2.de</a></p> 
        </footer>
    </div>
</body>
</html>