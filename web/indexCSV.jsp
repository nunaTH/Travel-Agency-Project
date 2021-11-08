<%-- 
    Document   : index
    Created on : 03.09.2019, 13:54:49
    Author     : sbopp
--%>


<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>B</title>
    </head>
    <body>
        <%
            String path = application.getRealPath("/") + "AA.csv";
            File file = new File(path);

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] arr = line.split(";");
                        out.print(arr[0]);
                        out.print(" - ");
                        out.print(arr[1]);
                        out.print(" - ");
                        out.print(arr[2]);
                        out.print(" - ");
                        out.print(arr[3]);
                        out.println("<br>");
                }
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        %>	
    </body>
</html>