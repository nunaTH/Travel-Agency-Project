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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Datenbank Verbundung :</h1>
        
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
		
		String sql = "SELECT * FROM  Passagiere ORDER BY p_id ASC";
		
		ResultSet rec = s.executeQuery(sql);
		%>
                
                <table width="600" border="1">
		  <tr>
		    <th width="100"> <div >Passagiere Nummer </div></th>
		    <th width="98"> <div >Anrede </div></th>
		    <th width="198"> <div >Name </div></th>
		    <th width="97"> <div >PLZ </div></th>
		    <th width="59"> <div>Ort </div></th>
		    <th width="100"> <div >Strasse </div></th>
                     <th width="71"> <div >Land </div></th>
		  </tr>
                  
                  	<%while((rec!=null) && (rec.next())) { %>
				  <tr>
				    <td><div ><%=rec.getInt("p_id")%></div></td>
				    <td><%=rec.getString("anr")%></td>
                                    <td><%=rec.getString("name")%></td>
				    <td><%=rec.getString("plz")%></td>
				    <td><div ><%=rec.getString("ort")%></div></td>
				    <td ><%=rec.getString("strasse")%></td>
				    <td ><%=rec.getString("land")%></td>
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
			if(s!=null){
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
