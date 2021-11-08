<%-- 
    Document   : profile
    Created on : 06.09.2019, 21:32:45
    Author     : Surattana
--%>

<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

<html>
    <head>
        <title>ThaiCreate.Com JSP Tutorial</title>
    </head>
    <body>

        <%
            Object strUserID = session.getAttribute("sUserID");
            if (strUserID == null) // Check Login
            {
                response.sendRedirect("index.jsp");
            }

            Connection connect = null;
            Statement s = null;

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                //connect = DriverManager.getConnection("jdbc:sqlserver://10.140.130.16:1433;"
                //        + " databaseName=BS;user=dba5;password=dba5;");
                connect = DriverManager.getConnection("jdbc:sqlserver://localhost\\CDT;"
                        + "databaseName=BS;user=sa;password=nuna13");
                s = connect.createStatement();

                String sql = "SELECT * FROM  BsUser WHERE vorname = '" + strUserID.toString() + "' ";

                ResultSet rec = s.executeQuery(sql);
                if (rec.next()) {
        %>

        Profile<br>
        <table border="1" style="width: 300px">
            <tbody>
                <tr>
                    <td> &nbsp;UserID</td>
                    <td>
                        <%=rec.getString("UserID")%>
                    </td>
                </tr>
                <tr>
                    <td> &nbsp;Username</td>
                    <td>
                        <%=rec.getString("Username")%>
                    </td>
                </tr>
                <tr>
                    <td> &nbsp;Password</td>
                    <td>
                        <%=rec.getString("Password")%>
                    </td>
                </tr>
                <tr>
                    <td> &nbsp;Email</td>
                    <td>
                        <%=rec.getString("Email")%>
                    </td>
                </tr>		
                <tr>
                    <td> &nbsp;Name</td>
                    <td>
                        <%=rec.getString("Name")%>
                    </td>
                </tr>	   
            </tbody>
        </table>
        <br>	
        <%} %>
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
    </body>
</html>