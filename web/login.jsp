
<%@page import= "java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="file/bootstrap.min.css">
        <script type="text/javascript" src="file/bootstrap.min.js"></script>
        <link rel="stylesheet" href="resources/css/main.css" />
        <link rel="stylesheet" href="resources/css/search.css" />
        <link rel="stylesheet" href="resources/css/table.css" />
        <link rel="stylesheet" href="resources/css/login.css" />
        <title>Login</title>
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

                String nname = request.getParameter("vorname");
                String vname = request.getParameter("nachname");
                String user = request.getParameter("username");
                String pwd = request.getParameter("passwort");

                String sql = "INSERT INTO BsUser (vorname,nachname,username,passwort)"
                        + "VALUES('" + nname + "','" + vname
                        + "','" + user + "','" + pwd + "')";

                s.execute(sql);
                /*
                String sql = "INSERT INTO BsUser (vorname,nachname,username,passwort)"
                        + "VALUES('"+request.getParameter("vorname")+"','"+request.getParameter("nachname")+
                        "','"+request.getParameter("username")+"','"+request.getParameter("passwort")+"')";
               
            
                sql = "SELECT * FROM  BsUser ORDER by vorname ASC";
                ResultSet rec = s.executeQuery(sql);
                 */

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
                out.println(e.getMessage());
                e.printStackTrace();
            }
        %>

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
            <div class="signup-form login " >
                <form action="loginErfolg.jsp" method="post">
                    <div class="container">

                        <h2>Login</h2>               
                        <hr width="60%" size="6">
                        <div class="row">  
                            <div class="col-sm-3">
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <input type="text" class="form-control" name="username" placeholder="Username" required="required">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <!-- 2 empty sections in the right -->
                            </div>
                        </div>
                    </div>    


                    <div class="container">
                        <div class="row">  
                            <div class="col-sm-3">
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <input type="password" class="form-control" name="passwort" placeholder="Passwort" required="required">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <!-- 2 empty sections in the right -->
                            </div>
                        </div>
                    </div> 
                    <div class="container">
                        <div class="row">  
                            <div class="col-sm-3">
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <div class="row">
                                        <button type="submit" class="btn btn-large register-button-background">Anmelden</button>
                                        <button type="submit" class="btn btn-large register-button-background"><a href="anmeldung.jsp">Registieren</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>


        <div class="footer">
            <footer class="container-fluid text-center">
                <a href=loginErfolg.jsp" title="To Top">
                    <span class="glyphicon glyphicon-chevron-up"></span>
                </a>
                <p>&copy; Copyright S.Bopp und A.Soeke - <a href="https://www.Bsreiseveranstalter.de" title="Visit us">www.Bsreiseveranstalter.de</a></p> 
            </footer>
        </div>
    </body>
</html>
