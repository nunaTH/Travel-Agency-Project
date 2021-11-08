package bsreiseveranstalter;

/**
 *
 * @author Surattana
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class FluegeForm extends javax.swing.JFrame {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private Statement stmt = null;
    private JTable table = null;

    public FluegeForm() {
        initComponents();
        con = Connect.ConnectDB();
        showDataToForm();
//        showData();
    }

    private void SaveData() {
        Connection connect = null;
        Statement s = null;

        try {
            Connection con = Connect.ConnectDB();
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//
//            connect = DriverManager.getConnection("jdbc:sqlserver://10.140.130.16:1433;"
//                    + " databaseName=BS;user=dba5;password=dba5;");

            s = connect.createStatement();

            for (int i = 0; i < table.getRowCount(); i++) {
                String typ = table.getValueAt(i, 4).toString();
                String hersteller = table.getValueAt(i, 5).toString();
                String sitze_ges = table.getValueAt(i, 6).toString();

                // SQL Insert
                String sql = "INSERT INTO Fluege "
                        + "(typ,hersteller,sitze_ges) "
                        + "VALUES ('" + typ + "','"
                        + hersteller + "','" + sitze_ges + "') ";

                s.execute(sql);
            }

            JOptionPane.showMessageDialog(null, "Import Data Successfully");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }

        try {
            if (s != null) {
                s.close();
                connect.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void showDataToForm() {

        String sql = "select * from Fluege ORDER BY f_dat ASC";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            Fluege.setModel(DbUtils.resultSetToTableModel(rs));
            while (rs.next()) {
                System.out.println(rs.getString("Fluege"));
            }
        } catch (Exception e) {
//            Logger.getLogger(FluglinieForm.class
//                    .getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void CsvImport() {
        long startTime = System.currentTimeMillis();

        JFileChooser fileopen = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Text/CSV file", "txt", "csv");
        fileopen.addChoosableFileFilter(filter);

        int ret = fileopen.showDialog(null, "Choose file");

        if (ret == JFileChooser.APPROVE_OPTION) {

            // Read Text file
            File file = fileopen.getSelectedFile();

//        File file = new File(filter);
//        String path = "C:/BSProjekt/CSV/AA.csv";
//        File file = new File(path);
            String connectionUrl = "jdbc:sqlserver://10.140.130.16:1433;"
                    + " databaseName=BS;user=dba5;password=dba5;";
//        String connectionUrl = "jdbc:sqlserver://localhost:1433;"
//                + "databaseName=BS;integratedSecurity=true";
//        String connectionUrl = "jdbc:sqlserver://localhost\\CDT;"
//                + "databaseName=BS;user=sa;password=nuna13";
//        Connection connect = null;
            Statement s = null;

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection connect = DriverManager.getConnection(connectionUrl);

                    s = connect.createStatement();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                while ((line = br.readLine()) != null) {

                    try {
                        //Fluggesellschaften
                        String[] arr = line.split(";");
                        if (arr.length < 5) {
                            break; // Abbruchkriterium datensatz 20!
                        }
//                        double preis = Double.parseDouble(arr[12].replace(",", ".")); //Datentyp möchte als Trenner den PUNKT! das zweite komma ist trenner
                        String sql = "IF NOT EXISTS (SELECT * FROM Fluege WHERE f_dat ='" + arr[11] + "')"
                                + "BEGIN "
                                + "INSERT INTO Fluege" + "(f_dat, sitze_bel, fl_id, typ)"
                                + "VALUES ('" + arr[11] + "'," + arr[15] + "," + arr[3] + ",'" + arr[13] + "') "
                                + "END";

                        s.execute(sql);

                        System.out.printf("Import success Nr. %s!\n", arr[17]);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                br.close();
                System.out.printf("Habe fertig!\n");
                long endTime = System.currentTimeMillis();
                System.out.printf("eingelesene Zeit (buffer): = %d\n", endTime - startTime);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void showData() {
        String sql = "SELECT * from Fluege";

        try {
            Connection con = Connect.ConnectDB();
            Statement stm = con.createStatement();
            pst = con.prepareStatement(sql);
            rs = stm.executeQuery(sql);
            Fluege.setModel(DbUtils.resultSetToTableModel(rs));
            rs.first();// zeigen erste Attibut
            dataToText(rs);

            while (rs.next()) {
                System.out.println(rs.getString("f_dat" + rs.getString(1)));
//                System.out.println(rs.getString("Stadt" + rs.getString(2)));
//                System.out.println(rs.getString("land" + rs.getString(3)));
            }
        } catch (Exception e) {
//            Logger.getLogger(FluglinieForm.class
//                    .getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }

    public void dataToText(ResultSet rs) {
        try {
            f_dat.setText(rs.getString(1));
            sitzeBe.setText(rs.getString(2));
            fl_id.setText(rs.getString(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        f_dat = new javax.swing.JTextField();
        fl_id = new javax.swing.JTextField();
        sitzeBe = new javax.swing.JTextField();
        bInsert = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Fluege = new javax.swing.JTable();
        bdelete = new javax.swing.JButton();
        txt_suchen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        typ = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        hauptmenue = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        bAktl = new javax.swing.JButton();
        IMFlug = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BS Reiseveranstalter FLY2");
        setName("FluegeForm"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Fluege hinzufügen");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Flug Datum :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Flug linie ID :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Sitze  Belegt :");

        f_dat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_datActionPerformed(evt);
            }
        });
        f_dat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                f_datKeyPressed(evt);
            }
        });

        fl_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fl_idActionPerformed(evt);
            }
        });

        sitzeBe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sitzeBeActionPerformed(evt);
            }
        });

        bInsert.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bInsert.setText("Insert");
        bInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInsertActionPerformed(evt);
            }
        });

        Fluege.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Fluege.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FluegeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Fluege);

        bdelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bdelete.setText("Cancel");
        bdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdeleteActionPerformed(evt);
            }
        });

        txt_suchen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_suchenActionPerformed(evt);
            }
        });
        txt_suchen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_suchenKeyReleased(evt);
            }
        });

        jLabel5.setText("YYYY-MM-DD suchen");

        typ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Typ  :");

        hauptmenue.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        hauptmenue.setText("Hauptmenue");
        hauptmenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hauptmenueActionPerformed(evt);
            }
        });

        bDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bDelete.setText("Delete");
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Fluege :");

        bAktl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bAktl.setText("Aktualisieren");
        bAktl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAktlActionPerformed(evt);
            }
        });

        IMFlug.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        IMFlug.setText("CSV Datei auswählen und in Datenbank importieren");
        IMFlug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IMFlugActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(103, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sitzeBe)
                                    .addComponent(fl_id)
                                    .addComponent(typ)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(f_dat, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(bInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(bdelete)))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(IMFlug)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(hauptmenue, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bAktl)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(f_dat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(sitzeBe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fl_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bInsert)
                    .addComponent(bdelete))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bDelete)
                    .addComponent(jLabel7)
                    .addComponent(bAktl))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IMFlug)
                    .addComponent(hauptmenue))
                .addGap(22, 22, 22))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void f_datActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_datActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_f_datActionPerformed

    private void fl_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fl_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fl_idActionPerformed

    private void sitzeBeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sitzeBeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sitzeBeActionPerformed

    private void bInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInsertActionPerformed
        try {
            String sql = "INSERT INTO Fluege(f_dat,sitze_bel,fl_id,typ)values(?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, f_dat.getText());
            pst.setString(2, sitzeBe.getText());
            pst.setString(3, fl_id.getText());
            pst.setString(4, typ.getText());

            pst.execute();

            JOptionPane.showMessageDialog(this, "Möchten Sie speichern", "Daten wurden in Tabellen gespeichert", JOptionPane.INFORMATION_MESSAGE);
            showDataToForm();
            clearData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_bInsertActionPerformed

    private void bdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdeleteActionPerformed
        clearData();
//        String sql = "DELEETE from Flughafen where fh_id = '" + fh_id.getText() + "'";
//        try {
//
//            pst = con.prepareStatement(sql);
//            Statement stm = con.createStatement();
//            stm.executeUpdate(sql);
////            pst.setString(1, fh_id.getText());
////            pst.setString(2, land.getText());
////            pst.setString(3, stadt.getText());
//
//            rs = pst.executeQuery();
//            Flughafen.setModel(DbUtils.resultSetToTableModel(rs));
//
//            pst.execute();
//
//            JOptionPane.showMessageDialog(this, "Möchten Sie löschen", "Daten wurden in Tabellen gelöscht", JOptionPane.INFORMATION_MESSAGE);
//            showDataToForm();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_bdeleteActionPerformed

    /**/
    private void txt_suchenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_suchenKeyReleased
        try {
            if (txt_suchen == null) {
                showDataToForm();
            } else {
                String data = txt_suchen.getText();
                String sql = "SELECT * FROM Fluege WHERE f_dat=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, data);
                rs = pst.executeQuery();
                Fluege.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_txt_suchenKeyReleased

    private void txt_suchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_suchenActionPerformed
         try {
            if (txt_suchen == null) {
                showDataToForm();
            } else {
                String data = txt_suchen.getText();
                String sql = "SELECT * FROM Fluege WHERE f_dat=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, data);
                rs = pst.executeQuery();
                Fluege.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_txt_suchenActionPerformed

    private void FluegeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FluegeMouseClicked
        try {
            int row = Fluege.getSelectedRow();
            String selectID = Fluege.getValueAt(row, 0).toString();
            String sql = "SELECT * from Fluege where f_dat='" + selectID + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
//            Flughafen.setModel(DbUtils.resultSetToTableModel(rs));

            if (rs.next()) {
                String add1 = rs.getString("f_dat");
                String add2 = rs.getString("sitze_bel");
                String add3 = rs.getString("fl_id");
                String add4 = rs.getString("typ");
                f_dat.setText(add1);
                sitzeBe.setText(add2);
                fl_id.setText(add3);
                typ.setText(add4);

            }
//            pst.execute();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }//GEN-LAST:event_FluegeMouseClicked

    private void typActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typActionPerformed

    private void hauptmenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hauptmenueActionPerformed
        Hauptmenue hm = new Hauptmenue();
        hm.setVisible(true);

        // Hide Current Form
        setVisible(false);
    }//GEN-LAST:event_hauptmenueActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed

        try {
            String sql = "Delete from Fluege where f_dat='" + f_dat.getText() + "'";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            stmt = con.createStatement();
            //            Flughafen.setModel(DbUtils.resultSetToTableModel(rs));
            int confirm = JOptionPane.showConfirmDialog(this, "Möchten Sie Daten löschen?", f_dat.getText() + "Ja oder Nein", JOptionPane.YES_NO_CANCEL_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, " Data wurden gelöschen");
                Fluege.setModel(DbUtils.resultSetToTableModel(rs));
                //                rs.first();
                //                tbPassagiereMouseClicked((MouseEvent) rs);
                //                clearData();
                //                showDataToForm();
                //                dataTotext(rs);
            } else if (confirm == JOptionPane.NO_OPTION) {
                //                JOptionPane.showMessageDialog(this," Data wurden nicht gelöschen");
                return;
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_bDeleteActionPerformed

    private void bAktlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAktlActionPerformed
        showDataToForm();
    }//GEN-LAST:event_bAktlActionPerformed

    private void IMFlugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IMFlugActionPerformed

        CsvImport();
    }//GEN-LAST:event_IMFlugActionPerformed

    private void f_datKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_datKeyPressed
        try {
            String sql = "select * from Fluege";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                String id = rs.getString("f_dat");
                if (id.equalsIgnoreCase(f_dat.getText())) {
                    JOptionPane.showMessageDialog(this, "PK schon gegeben", "Wahnung!", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_f_datKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FluegeForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FluegeForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FluegeForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FluegeForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FluegeForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Fluege;
    private javax.swing.JButton IMFlug;
    private javax.swing.JButton bAktl;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bInsert;
    private javax.swing.JButton bdelete;
    private javax.swing.JTextField f_dat;
    private javax.swing.JTextField fl_id;
    private javax.swing.JButton hauptmenue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField sitzeBe;
    private javax.swing.JTextField txt_suchen;
    private javax.swing.JTextField typ;
    // End of variables declaration//GEN-END:variables

    private void clearData() {
        f_dat.setText("");
        sitzeBe.setText("");
        fl_id.setText("");
        typ.setText("");

    }
}
