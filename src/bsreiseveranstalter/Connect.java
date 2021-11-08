package bsreiseveranstalter;

import java.sql.*;

/**
 *
 * @author Surattana
 */

public class Connect {

    public static Connection ConnectDB() {
        try {
//           Class.forName("com.mysql.jdbc.Driver");
//           String url="jdbc:mysql://localhost/sakila";
//           Connection con=DriverManager.getConnection(url,"root","nuna13");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://10.140.130.16:1433;"
                    + " databaseName=BS;user=dba5;password=dba5;");
           
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            String url = "jdbc:sqlserver://10.140.130.16:1433;databaseName=BS";
            return con;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
