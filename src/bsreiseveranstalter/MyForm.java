package bsreiseveranstalter;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class MyForm extends JFrame {

    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                MyForm form = new MyForm();
                form.setVisible(true);
            }
        });
    }

    public MyForm() {

        // Create Form Frame
        super("Bs Reiseveranstallter Fly2");
        setSize(668, 345);
        setLocation(500, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        // Label Result
        final JLabel lblResult = new JLabel("Result", JLabel.CENTER);
        lblResult.setBounds(150, 22, 370, 14);
        getContentPane().add(lblResult);

        // Table
        table = new JTable();
        getContentPane().add(table);

        // Table Model
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addColumn("fg_id");
        model.addColumn("fg_name");
        model.addColumn("");
        model.addColumn("line");
        model.addColumn("fh_id");
        model.addColumn("land");
        model.addColumn("stadt");
        model.addColumn("fh_id");
        model.addColumn("land");
        model.addColumn("dauer");
        model.addColumn("datum");
        model.addColumn("preis");
        model.addColumn("typ");
        model.addColumn("hersteller");
        model.addColumn("sitze_bel");
        model.addColumn("sitze_ges");
        model.addColumn("b_id");
        model.addColumn("b_dat");
        model.addColumn("p_id");
        model.addColumn("anr");
        model.addColumn("p_name");
        model.addColumn("plz");
        model.addColumn("ort");
        model.addColumn("strasse");
        model.addColumn("land");

        // ScrollPane
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(84, 98, 506, 79);
        getContentPane().add(scroll);

        // Create Button Open JFileChooser
        JButton btnButton = new JButton("Open File Chooser");
        btnButton.setBounds(268, 47, 135, 23);
        btnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileopen = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter(
                        "Text/CSV file", "txt", "csv");
                fileopen.addChoosableFileFilter(filter);

                int ret = fileopen.showDialog(null, "Choose file");

                if (ret == JFileChooser.APPROVE_OPTION) {

                    // Read Text file
                    File file = fileopen.getSelectedFile();

                    try {
                        BufferedReader br = new BufferedReader(new FileReader(
                                file));
                        String line;
                        int row = 0;
                        while ((line = br.readLine()) != null) {
                    String[] arr = line.split(";");
                    model.addRow(new Object[0]);
                    model.setValueAt(arr[0], row, 0);
                    model.setValueAt(arr[1], row, 1);
                    model.setValueAt(arr[2], row, 2);
                    model.setValueAt(arr[3], row, 3);
                    model.setValueAt(arr[4], row, 4);
                    model.setValueAt(arr[5], row, 5);
                    model.setValueAt(arr[6], row, 6);
                    model.setValueAt(arr[7], row, 7);
                    model.setValueAt(arr[8], row, 8);
                    model.setValueAt(arr[9], row, 9);
                    model.setValueAt(arr[10], row, 10);
                    model.setValueAt(arr[11], row, 11);
                    model.setValueAt(arr[12], row, 12);
                    model.setValueAt(arr[13], row, 13);
                    model.setValueAt(arr[14], row, 14);
                    model.setValueAt(arr[15], row, 15);
                    model.setValueAt(arr[16], row, 16);
                    model.setValueAt(arr[17], row, 17);
                    model.setValueAt(arr[18], row, 18);
                    model.setValueAt(arr[19], row, 19);
                    model.setValueAt(arr[20], row, 20);
                    model.setValueAt(arr[21], row, 21);
                    model.setValueAt(arr[22], row, 22);
                    model.setValueAt(arr[23], row, 23);
                    model.setValueAt(arr[24], row, 24);
                    model.setValueAt(arr[25], row, 25);
                    row++;
                }
                        br.close();
                    } catch (IOException ex) {
                        // TODO Auto-generated catch block
                        ex.printStackTrace();
                    }

                    lblResult.setText(fileopen.getSelectedFile().toString());
                }

            }
        });
        getContentPane().add(btnButton);

        // Button Save
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                SaveData(); // save Data
            }
        });
        btnSave.setBounds(292, 228, 89, 23);
        getContentPane().add(btnSave);

    }

//    private void SaveData() {
//        Connection connect = null;
//        Statement s = null;
//
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Connection con = DriverManager.getConnection("jdbc:sqlserver://10.140.130.16:1433;"
//                    + " databaseName=BS;user=dba5;password=dba5;");
//
//            s = connect.createStatement();
//
//            for (int i = 0; i < table.getRowCount(); i++) {
//                String CustomerID = table.getValueAt(i, 0).toString();
//                String Name = table.getValueAt(i, 1).toString();
//                String Email = table.getValueAt(i, 2).toString();
//                String CountryCode = table.getValueAt(i, 3).toString();
//                String Budget = table.getValueAt(i, 4).toString();
//                String Used = table.getValueAt(i, 5).toString();
//
//                // SQL Insert
//                String sql = "INSERT INTO customer "
//                        + "(CustomerID,Name,Email,CountryCode,Budget,Used) "
//                        + "VALUES ('" + CustomerID + "','"
//                        + Name + "','"
//                        + Email + "'" + ",'"
//                        + CountryCode + "','"
//                        + Budget + "','"
//                        + Used + "') ";
//                s.execute(sql);
//            }
//
//            JOptionPane.showMessageDialog(null,
//                    "Import Data Successfully");
//
//        } catch (Exception ex) {
//            // TODO Auto-generated catch block
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//            ex.printStackTrace();
//        }
//
//        try {
//            if (s != null) {
//                s.close();
//                connect.close();
//            }
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }

}
