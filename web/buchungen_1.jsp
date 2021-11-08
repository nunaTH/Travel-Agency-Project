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

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        --%>
        <link rel="stylesheet" href="file/bootstrap.min.css">
        <script type="text/javascript" src="file/bootstrap.min.js"></script>
        <link rel="stylesheet" href="resources/css/main.css" />
        <link rel="stylesheet" href="resources/css/search.css" />
        <link rel="stylesheet" href="resources/css/table.css" />

        <title>Bs Veranstaltung FLY2</title>
    </head>
    <body>

        <div class="container-fluid" style="margin-top: 20px">
            <div class="jumbotron">
                <h1 style="text-align: center">BS Reiseveranstalter FLY2</h1>

                <div  class="btn-group" style="text-align: center">
                    <button class="btn btn-success"><h3>Login</h3></button>
                    <button class="btn btn-success"><h3>Buchungen</h3></button>
                    <button class="btn btn-success"><h3>Über FLY2</h3></button>
                    <button class="btn btn-success"><h3>Kontakt</h3></button>
                    <button class="btn btn-success"><h3>Impress</h3></button>     
                </div>  
            </div>
        </div>




        <%
            String keyword = "";
            String passagiere = "";
            if (request.getParameter("txtKeyword") != null) {
                keyword = request.getParameter("txtKeyword");
            }
            if (request.getParameter("txtPassagiere") != null) {
                passagiere = request.getParameter("txtPassagiere");
            }
        %>

        <div class="container" >
            <div class="jumbotron"></div>
            <div  class="btn-group" style="text-align: left">
                <h3><span class=label label-success>Fluggesellschalften</span></h3>
                <h3><span class=label label-success>Flughafen</span></h3>
                <h3><span class=label label-success>Fluege</span></h3>
                <h3><span class=label label-success>Fluezeuge</span></h3>
                <h3><span class=label label-success>Passagiere</span></h3>
                <h3><span class=label label-success>Fluglinie</span></h3>
                <h3><span class=label label-success>Buchungen</span></h3>

            </div>
            <h2><span class="label label-primary">Datenbankverwaltung:</span></h2>
            <ul class="nav nav-stacked">
                <li>Fluggesellschalften</li>
                <li>Flughafen</li>
                <li>Fluege</li>
                <li>Fluezeuge</li>
                <li>Passagiere</li>
                <li>Fluglinie</li>
                <li>Buchungen</li>

            </ul>



            <form name="frmSearch" method="get" action="buchungen.jsp">
                <table class="table table-hover " style="text-align: left">
                    <tr >
                        <th>Buchung Nummer :
                            <input name="txtKeyword" type="text" id="txtKeyword" value="<%=keyword%>">
                            <input type="submit" value="Suchen"></th>
                    </tr>
                    <tr>
                        <th>Passagiere Nummer :
                            <input name="txtPassagiere" type="text" id="txtKeyword" value="<%=passagiere%>">
                            <input type="submit" value="Suchen"></th>
                    </tr>
                </table>
            </form>  
        </div>
    </div>
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

            // String sql1 = "SELECT * FROM  Buchungen ORDER BY b_id ASC";
            // ResultSet rec = s.executeQuery(sql1);
            String sql2 = "SELECT * FROM  Buchungen WHERE b_id like '%" + keyword + "%' "
                    + " ORDER BY b_id ASC";
            System.out.println(sql2);
            ResultSet rec = s.executeQuery(sql2);
            String sql3 = "SELECT * FROM  Buchungen WHERE p_id like '%" + passagiere + "%' "
                    + " ORDER BY b_id ASC";
            System.out.println(sql3);
            rec = s.executeQuery(sql3);
    %>

    <table width="600" border="1">
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
</body>
</html>
