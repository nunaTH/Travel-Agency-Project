<%-- 
    Document   : SQLServerConnect
    Created on : 03.09.2019, 14:46:50
    Author     : sbopp
--%>

<%@page import= "java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="file/bootstrap.min.css">
        <script type="text/javascript" src="file/bootstrap.min.js"></script>
        <link rel="stylesheet" href="resources/css/main.css" />
        <link rel="stylesheet" href="resources/css/search.css" />
        <link rel="stylesheet" href="resources/css/table.css" />

        <title>Bs Veranstaltung FLY2</title>
    </head>
    <body>
        <%
            String keyword = "";
            String passagiere = "";
            if (request.getParameter("txtKeyword") != null) {
                keyword = request.getParameter("txtKeyword");
            }
            if (request.getParameter("txtPassagiere") != null) {
                passagiere = request.getParameter("txtPassagiere");
            }

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

                // String sql1 = "SELECT * FROM  Buchungen ORDER BY b_id ASC";
                // ResultSet rec = s.executeQuery(sql1);
                String sql2 = "SELECT TOP 7 * FROM  Buchungen WHERE b_id like '%" + keyword + "%' "
                        + " ORDER BY b_id ASC";
                System.out.println(sql2);
                ResultSet rec = s.executeQuery(sql2);
                String sql3 = "SELECT TOP 7 * FROM  Buchungen WHERE p_id like '%" + passagiere + "%' "
                        + " ORDER BY b_id ASC";
                System.out.println(sql3);
                rec = s.executeQuery(sql3);
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
                            <li style="padding-right: 10px;"><a href="services.jsp">Buchungen</a></li>
                            <li style="padding-right: 10px;"><a href="contact.jsp">Kontakt</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>

        <div class="container-fluid text-center bg-grey" >
            <div class="jumbotron text-left">
                <h2>Buchungen suchen :</h2>
                <div class="col-sm-4">
                    <form name="frmSearch" method="get" action="buchungen.jsp">
                        <%--
                        <h2>Buchungen suchen :</h2>
                        <b>Passagiere Nummer :</b><input name="txtPassagiere" type="text" id="txtKeyword" value="<%=passagiere%>"> <input type="submit" value="Suchen">
                        <p></p>
                        <b>Buchung Nummer :</b><input name="txtKeyword" type="text" id="txtKeyword" value="<%=keyword%>"><input type="submit" value="Suchen">
                        --%>  
                        <table width="50%" class="table table-hover text-center">
                            <tr>
                                <td width="15">Passagiere Nummer :</td>
                                <td  width="20">  <input name="txtPassagiere" type="text" id="txtKeyword" value="<%=passagiere%>"></td>
                                <td  width="10">  <input type="submit" value="Suchen"></td>
                            </tr>
                            <tr >
                                <td width="15">Buchung Nummer :</td>
                                <td width="20">   <input name="txtKeyword" type="text" id="txtKeyword" value="<%=keyword%>"></td>
                                <td width="10">  <input type="submit" value="Suchen"></td>
                            </tr>
                        </table>

                    </form>  
                </div>
                <div class="col-sm-4">
                    <table width="800px" border="1">
                        <tr >
                            <th width="10"> <div >Buchungn Nummer </div></th>
                            <th width="60"> <div >Buchung Datum </div></th>
                            <th width="50"> <div >Preis </div></th>
                            <th width="50"> <div >Fluglinie </div></th>
                            <th width="60"> <div>Datum fliegen </div></th>
                            <th width="40"> <div >Fluggesell -schaften </div></th>
                            <th width="71"> <div >Passagier Nummer </div></th>
                        </tr>

                        <%while ((rec != null) && (rec.next())) {%>
                        <tr>
                            <td><div ><%=rec.getInt("b_id")%></div></td>
                            <td><%=rec.getString("b_dat")%></td>
                            <td><%=rec.getString("preis")%></td>
                            <td><%=rec.getString("fl_id")%></td>
                            <td><div ><%=rec.getString("f_dat")%></div></td>
                            <td ><%=rec.getString("fg_id")%></td>
                            <td ><%=rec.getString("p_id")%></td>
                        </tr>
                        <%}%>
                    </table>   
                </div>
            </div>
        </div>


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
                out.println(e.getMessage());
                e.printStackTrace();
            }
        %>
        <div class="container-fluid" >
            <div class="jumbotron text-left">
                <div class="footer">
                    <footer class="container-fluid text-center">
                        <a href="#index.jsp" title="To Top">
                            <span class="glyphicon glyphicon-chevron-up"></span>
                        </a>
                        <p>&copy; Copyright S.Bopp und A.Soeke - <a href="https://www.Bsreiseveranstalter.de" title="Visit us">www.Bsreiseveranstalter.de</a></p> 
                    </footer>
                </div>
            </div>
        </div>

    </body>
</html>
