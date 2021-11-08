package bsreiseveranstalter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.DataFormatter;

public class XLStoCSV 
{
    public static void echoAsCSV(Sheet sheet) throws IOException 
    {
        Row row = null;
        DataFormatter formatter = new DataFormatter();

        File fout = new File("C:/BSProjekt/XlsToCsv/AA.csv");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 2; i < sheet.getLastRowNum(); i++)
        {
            row = sheet.getRow(i);
            String sfout = "";
            for (int j = 0; j < row.getLastCellNum(); j++)
            {
                String zelle = formatter.formatCellValue(row.getCell(j));
                if (j == row.getLastCellNum() - 1)
                {
                    System.out.print(zelle);
                    sfout += zelle;
                    bw.write(sfout.trim());
                    bw.newLine();
                }
                else 
                {
                    System.out.print(zelle + ";");
                    sfout += zelle + ";";
                }
            }
            System.out.println();
        }
        bw.close();
    }

    public static void main(String[] args) 
    {
        InputStream inp = null;
        try 
        {
            inp = new FileInputStream("C:/BSProjekt/DateienFallstudieFIAE/AA.xls");
            Workbook wb = WorkbookFactory.create(inp);

            for (int i = 0; i < wb.getNumberOfSheets(); i++) 
            {
                //System.out.println(wb.getSheetAt(i).getSheetName());
                echoAsCSV(wb.getSheetAt(i));
            }
        }  
        catch (IOException ex) 
        {
            Logger.getLogger(ExcelReading.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally 
        {
            try 
            {
                inp.close();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(ExcelReading.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static class ExcelReading {

        public ExcelReading() {
        }
    }
}
