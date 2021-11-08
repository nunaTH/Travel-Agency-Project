package bsreiseveranstalter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class ConnectCDT {

    public static Connection ConnectDB() {
        String connectionUrl = "jdbc:sqlserver://10.140.130.16:1433;"
                + " databaseName=BS;user=dba5;password=dba5;";
        // Deklaration der JDBC Objekte.
        Connection con = null;
        Statement stmt = null;
//        ResultSet rs = null;

        try {
            // Verbindung zum Treiber herstellen
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Das Objekt con erhält die Infos
            con = DriverManager.getConnection(connectionUrl);
            stmt = con.createStatement();

            stmt.executeUpdate("create table testingOne (id integer, name varchar(30) not null);");

        } catch (Exception exi) {
            exi.printStackTrace();
        } finally {
        }
        return null;
    }
}
