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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.ListIterator;

public class CSV_to_DB2
{
    public static void main(String[] args) throws SQLException
    {
        String path = "C:\\BSProjekt\\CSV\\AA.csv";
        File file = new File(path);

        Connection connect = null;
        Statement s = null;
        ResultSet rs=null;

        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            try 
            {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connect = DriverManager.getConnection("jdbc:sqlserver://10.140.130.16:1433;databaseName=BS;user=dba5;password=dba5;");

                s = connect.createStatement();
            } 
            catch (Exception e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            

            ArrayList<String> liste=new ArrayList<>();
        
            ListIterator<String> li = liste.listIterator();
            
            LinkedHashSet<String> hs = new LinkedHashSet<>();
            
            
//            String sqlQuery = "SELECT * FROM Flughafen";
//            rs = s.executeQuery(sqlQuery);
            
            while ((line = br.readLine()) != null) // && li.hasNext()) 
            {
//
//                if(!line.equals(li.next()))
//                {
                    //try 
                    //{
                    //Fluggesellschaft
                    String[] arr = line.split(";");
                    String sql = "INSERT INTO dbo.Flugzeuge (typ,hersteller,sitze_ges) VALUES ('" + arr[13] + "','" + arr[14] + "'," + arr[16] + ");";
                    
                    hs.add(sql);
                    
                          //s.execute(sql);
                     
                    //} 
                    //catch (SQLException e)
                    //{
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                    //}
//liste hinzufügen
                System.out.println("Import success!");
//                }
            }
            
            Iterator it = hs.iterator();
            while (it.hasNext())
            {
                try
                {
                s.execute((String) it.next());
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }

            br.close();
        } 
        catch (IOException e) 
        {

            e.printStackTrace();
        }

    }

}
