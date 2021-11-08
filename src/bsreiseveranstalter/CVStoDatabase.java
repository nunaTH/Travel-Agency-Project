package bsreiseveranstalter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CVStoDatabase {

    public static void main(String[] args) {

        String path = "C:\\BSProjekt\\CSV\\AA.csv";
//        String path = "C:\\BSProjekt\\CSV\\AA.csv", "ISO-8859-15");
        File file = new File(path);

        Connection connect = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connect = DriverManager.getConnection("jdbc:sqlserver://10.140.130.16:1433;"
                        + " databaseName=BS;user=dba5;password=dba5;");

                s = connect.createStatement();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            while ((line = br.readLine()) != null) {

                try {
                    // Flughafen
//                    String[] arr = line.split(";");
//                    String sql = "INSERT INTO dbo.Fluggesellschaften" + "(fg_id,fg_name,line)"
//                            + "VALUES ('" + arr[0] + "','" + arr[1] + "','" + arr[3] + "')";
                    //passagiere
                    String[] arr = line.split(";");
                    String sql = "INSERT INTO Passagiere" + "(p_id,anr,name,plz,ort,strasse,land) "
                            + "Values ('"+arr[19]+"','"+arr[20]+"','"+arr[21]+"','"+arr[22]+"','"+arr[23]+"',"
                            + "'"+arr[24]+"','"+arr[25]+"')"; 
                            
//                        String id = rs.getString("fg_id");
//                      
//                        if (id.equalsIgnoreCase("fg_id"));
//                        {
//                            System.out.println("Dopple Primary Key!");                     
//                        }
                        s.execute(sql);

                    }catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                    System.out.println("Import success!");
                }
                br.close();
            }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        }

    }
