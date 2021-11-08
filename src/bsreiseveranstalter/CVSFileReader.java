package bsreiseveranstalter;

/**
 *
 * @author Surattana
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CVSFileReader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
//        demoFileReader();
//        demoBufferReader();
//        demoBufferReader();
        demoBufferReader2();
//        demoBufferReader3();

//        getCSVData();
        long endTime = System.currentTimeMillis();
        System.out.printf("eingelesene Zeit (buffer): = %d\n", endTime - startTime);

    }

    public static void demoFileReader() {
        try {
//            FileReader r = new FileReader("C:/BS/DateienFallstudieFIAE/AA.xls");
            FileReader r = new FileReader("C:/BS/CSV/AA.csv");
            int data;
            while ((data = r.read()) != -1) {
                System.out.printf("%c", data);
            }
            r.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read all File in Bytes
    public static void demoReadAll() {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("C:/BS/CSV/AA.csv"));
            String s = new String(bytes);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Read all File in line

    public static void demoReadLines() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("C:/BS/CSV/AA.csv"));
            for (String line : lines) {
                System.out.println(line.toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * **************************************************************
     */
    public static void demoBufferReader() {

        try {
            BufferedReader r = new BufferedReader(new FileReader("C:/BS/CSV/AA.csv"));
//            int data;
//            while ((data = r.read()) != -1) {
//                System.out.printf("%c", data);
//            }
            String s;
            while ((s = r.readLine()) != null) {
                System.out.println(s);
            }
            r.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void demoBufferReader2() {
        try (BufferedReader r = new BufferedReader(new FileReader("C:/BSProjekt/CSV/AZ.csv"))) {
//            int data;
//            while ((data = r.read()) != -1) {
//                System.out.printf("%c", data);
//            }
            String s;
            while ((s = r.readLine()) != null) {
                System.out.println(s);
            }
//            r.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void demoBufferReader3() {
        try (BufferedReader r = Files.newBufferedReader(Paths.get("C:/BS/CSV/AA.csv"))) {
//            int data;
//            while ((data = r.read()) != -1) {
//                System.out.printf("%c", data);
//            }
            String s;
            while ((s = r.readLine()) != null) {
                System.out.println(s);
            }
//            r.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * **************************************************************
     */
    private static void getCSVData() {

    }

    public List<String[]> getCSVData(String filePath) {

        try {

            File f = new File(filePath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String get = "";
            String[] arr = new String[26];
            List<String[]> list = new ArrayList<>();
            int i = 0;

            while ((get = br.readLine()) != null) {

                arr = get.split(";");

                if (i > 1 && arr[0].length() < 3) {
                    list.add(arr);
                }
                i++;
            }

            return list;

        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e1) {
            return null;
        }
    }

}
