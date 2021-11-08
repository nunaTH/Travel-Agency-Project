package bsreiseveranstalter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Scanner;
public class DateilesenTest{
    public static void main(String[] args) throws IOException {

        Scanner ein = new Scanner(System.in);
        
        System.out.print("Bitte Dateinamen eingeben: ");
        String name = ein.next();
        
        FileInputStream in = null;
        byte [] b = new byte[10];

        try {
            in = new FileInputStream(name);
            int c;

            while ((c = in.read(b)) != -1) {
               for (int i=0; i < b.length; i++)
               {
                   System.out.format("%02x ",b[i]);
               }
               System.out.print("   ");
               for (int i=0; i < b.length; i++)
               {
                   if (b[i] < 32)
                      System.out.print(".");
                   else
                      System.out.format("%c",b[i]);
               }
               System.out.println();
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
