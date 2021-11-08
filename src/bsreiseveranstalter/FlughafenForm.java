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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class FlughafenForm extends javax.swing.JFrame {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private Statement stmt = null;
    private JTable table = null;

    public FlughafenForm() {
        initComponents();
        con = Connect.ConnectDB();
        showDataToForm();
//        showData();
    }

    public void showDataToForm() {

        String sql = "select * from Flughafen ORDER BY fh_id ASC";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            Flughafen.setModel(DbUtils.resultSetToTableModel(rs));
            while (rs.next()) {
                System.out.println(rs.getString("Flughafen"));
            }
        } catch (Exception e) {
//            Logger.getLogger(FluglinieForm.class
//                    .getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void showData() {
        String sql = "SELECT * from Flughafen";

        try {
            Connection con = Connect.ConnectDB();
            Statement stm = con.createStatement();
            pst = con.prepareStatement(sql);
            rs = stm.executeQuery(sql);
            Flughafen.setModel(DbUtils.resultSetToTableModel(rs));
            rs.first();// zeigen erste Attibut
            dataToText(rs);

            while (rs.next()) {
                System.out.println(rs.getString("fh_id" + rs.getString(1)));
//                System.out.println(rs.getString("Stadt" + rs.getString(2)));
//                System.out.println(rs.getString("land" + rs.getString(3)));
            }
        } catch (Exception e) {
//            Logger.getLogger(FluglinieForm.class
//                    .getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }

    private void CsvImport() {

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
                        if (arr.length < 4) {
                            break; // Abbruchkriterium datensatz 20!
                        }
//                    double preis = Double.parseDouble(arr[12].replace(",", ".")); //Datentyp möchte als Trenner den PUNKT! das zweite komma ist trenner
                        String sql1 = "IF NOT EXISTS (SELECT * FROM Flughafen WHERE fh_id ='" + arr[4] + "')"
                                + "BEGIN " // Quasi geschweifte Klammer bei if else anw.
                                + "INSERT INTO Flughafen" + "(fh_id, land, stadt)"
                                + "VALUES ('" + arr[4] + "','" + arr[5] + "','" + arr[6] + "') " + "END";
                        String sql2 = "IF NOT EXISTS (SELECT * FROM Flughafen WHERE fh_id ='" + arr[7] + "')"
                                + "BEGIN " // Quasi geschweifte Klammer bei if else anw.
                                + "INSERT INTO Flughafen" + "(fh_id, land, stadt)"
                                + "VALUES ('" + arr[7] + "','" + arr[8] + "','" + arr[9] + "') " + "END";
                        s.execute(sql1);
                        s.execute(sql2);

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

    public void dataToText(ResultSet rs) {
        try {
            fh_id.setText(rs.getString(1));
            stadt.setText(rs.getString(2));
            land.setText(rs.getString(3));
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
        fh_id = new javax.swing.JTextField();
        land = new javax.swing.JTextField();
        stadt = new javax.swing.JTextField();
        bInsert = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Flughafen = new javax.swing.JTable();
        bdelete = new javax.swing.JButton();
        txt_suchen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        IMFlug = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        bAktl = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BS Reiseveranstalter FLY2");
        setName("FlughafenForm"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Flughafen hinzufügen");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Flughafen ID :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Land :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Stadt :");

        fh_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fh_idActionPerformed(evt);
            }
        });
        fh_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fh_idKeyPressed(evt);
            }
        });

        land.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                landActionPerformed(evt);
            }
        });

        stadt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stadtActionPerformed(evt);
            }
        });

        bInsert.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bInsert.setText("Insert");
        bInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInsertActionPerformed(evt);
            }
        });

        Flughafen.setModel(new javax.swing.table.DefaultTableModel(
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
        Flughafen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FlughafenMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Flughafen);

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

        jLabel5.setText("Suchen ID (3 Buchstaben eingeben)");

        IMFlug.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        IMFlug.setText("CSV Datei auswählen und in Datenbank importieren");
        IMFlug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IMFlugActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Hauptmenue");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        bAktl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bAktl.setText("Aktualisieren");
        bAktl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAktlActionPerformed(evt);
            }
        });

        bDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bDelete.setText("Delete");
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Flughafen :");

        jLabel7.setText("3 Grossbuchstaben eingeben");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(stadt)
                                .addComponent(land)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(bInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(32, 32, 32)
                                            .addComponent(bdelete))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(fh_id, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel7)))
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(21, 21, 21)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(bDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(34, 34, 34)
                                    .addComponent(bAktl))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(IMFlug)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1))))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fh_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(stadt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(land, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bInsert)
                    .addComponent(bdelete))
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAktl)
                    .addComponent(bDelete)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IMFlug)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void fh_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fh_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fh_idActionPerformed

    private void landActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_landActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_landActionPerformed

    private void stadtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stadtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stadtActionPerformed

    private void bInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInsertActionPerformed
        try {
            String sql = "INSERT INTO Flughafen(fh_id,land,stadt)values(?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, fh_id.getText());
            pst.setString(2, land.getText());
            pst.setString(3, stadt.getText());

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
                String sql = "SELECT * FROM Flughafen WHERE fh_id=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, data);
                rs = pst.executeQuery();
                Flughafen.setModel(DbUtils.resultSetToTableModel(rs));

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
                String sql = "SELECT * FROM Flughafen WHERE fh_id=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, data);
                rs = pst.executeQuery();
                Flughafen.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_txt_suchenActionPerformed

    private void FlughafenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FlughafenMouseClicked
        try {

            int row = Flughafen.getSelectedRow();
            String selectID = Flughafen.getValueAt(row, 0).toString();
            String sql = "SELECT * from Flughafen where fh_id='" + selectID + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
//            Flughafen.setModel(DbUtils.resultSetToTableModel(rs));

            if (rs.next()) {
                String add1 = rs.getString("fh_id");
                String add2 = rs.getString("stadt");
                String add3 = rs.getString("land");
                fh_id.setText(add1);
                stadt.setText(add2);
                land.setText(add3);

            }
//            pst.execute();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_FlughafenMouseClicked

    private void IMFlugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IMFlugActionPerformed

        CsvImport();

    }//GEN-LAST:event_IMFlugActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Hauptmenue hm = new Hauptmenue();
        hm.setVisible(true);

        // Hide Current Form
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bAktlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAktlActionPerformed
        showDataToForm();
    }//GEN-LAST:event_bAktlActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed

        try {
            String sql = "Delete from Flughafen where fh_id='" + fh_id.getText() + "'";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            stmt = con.createStatement();
            //            Flughafen.setModel(DbUtils.resultSetToTableModel(rs));
            int confirm = JOptionPane.showConfirmDialog(this, "Möchten Sie Daten löschen?", fh_id.getText() + "Ja oder Nein", JOptionPane.YES_NO_CANCEL_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, " Data wurden gelöschen");
                Flughafen.setModel(DbUtils.resultSetToTableModel(rs));
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

    private void fh_idKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fh_idKeyPressed
        try {
            String sql = "select * from Flughafen";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                String id = rs.getString("fh_id");
                if (id.equalsIgnoreCase(fh_id.getText())) {
                    JOptionPane.showMessageDialog(this, "PK schon gegeben", "Wahnung!", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_fh_idKeyPressed

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
            java.util.logging.Logger.getLogger(FlughafenForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FlughafenForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FlughafenForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FlughafenForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FlughafenForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Flughafen;
    private javax.swing.JButton IMFlug;
    private javax.swing.JButton bAktl;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bInsert;
    private javax.swing.JButton bdelete;
    private javax.swing.JTextField fh_id;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField land;
    private javax.swing.JTextField stadt;
    private javax.swing.JTextField txt_suchen;
    // End of variables declaration//GEN-END:variables

    private void clearData() {
        fh_id.setText("");
        stadt.setText("");
        land.setText("");

    }
}
