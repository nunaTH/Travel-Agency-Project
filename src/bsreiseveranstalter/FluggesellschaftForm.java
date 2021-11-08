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
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.proteanit.sql.DbUtils;

public class FluggesellschaftForm extends javax.swing.JFrame {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private Statement stmt = null;

    public FluggesellschaftForm() {
        initComponents();
        con = Connect.ConnectDB();
        showDataToForm();
//        showData();
    }

    public void showDataToForm() {

        String sql = "select * from Fluggesellschaften ORDER BY fg_id ASC";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            Fluggesellschaften.setModel(DbUtils.resultSetToTableModel(rs));
            while (rs.next()) {
                System.out.println(rs.getString("Fluggesellschaften"));
            }
        } catch (Exception e) {
//            Logger.getLogger(FluglinieForm.class
//                    .getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void showData() {
        String sql = "SELECT * from Fluggesellschaften";

        try {
            Connection con = Connect.ConnectDB();
            Statement stm = con.createStatement();
            pst = con.prepareStatement(sql);
            rs = stm.executeQuery(sql);
            Fluggesellschaften.setModel(DbUtils.resultSetToTableModel(rs));
            rs.first();// zeigen erste Attibut
            dataToText(rs);

            while (rs.next()) {
                System.out.println(rs.getString("fg_id" + rs.getString(1)));
//                System.out.println(rs.getString("Stadt" + rs.getString(2)));
//                System.out.println(rs.getString("land" + rs.getString(3)));
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
                        if (arr.length < 4) {
                            break; // Abbruchkriterium datensatz 20!
                        }
//                    double preis = Double.parseDouble(arr[12].replace(",", ".")); //Datentyp möchte als Trenner den PUNKT! das zweite komma ist trenner
                        String sql = "IF NOT EXISTS (SELECT * FROM Fluggesellschaften WHERE fg_id ='" + arr[0] + "')"
                                + "BEGIN " // Quasi geschweifte Klammer bei if else anw.
                                + "INSERT INTO Fluggesellschaften" + "(fg_id, fg_name, linie)"
                                + "VALUES ('" + arr[0] + "','" + arr[1] + "','" + arr[3] + "') " + "END";
                     
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

    

    public void dataToText(ResultSet rs) {
        try {
            fg_id.setText(rs.getString(1));
            fg_name.setText(rs.getString(2));
            line.setText(rs.getString(3));
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
        fg_id = new javax.swing.JTextField();
        line = new javax.swing.JTextField();
        fg_name = new javax.swing.JTextField();
        bInsert = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Fluggesellschaften = new javax.swing.JTable();
        bdelete = new javax.swing.JButton();
        txt_suchen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        hautpmenue = new javax.swing.JButton();
        IMFg = new javax.swing.JButton();
        bAktl = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        IMFlug = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BS Reiseveranstalter FLY2");
        setName("FluggesellschaftForm"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Fluggesellschaften hinzufügen");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("FlugGE ID :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Linie :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Name :");

        fg_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fg_idActionPerformed(evt);
            }
        });
        fg_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fg_idKeyPressed(evt);
            }
        });

        line.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lineActionPerformed(evt);
            }
        });

        fg_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fg_nameActionPerformed(evt);
            }
        });

        bInsert.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bInsert.setText("Insert");
        bInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInsertActionPerformed(evt);
            }
        });

        Fluggesellschaften.setModel(new javax.swing.table.DefaultTableModel(
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
        Fluggesellschaften.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FluggesellschaftenMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Fluggesellschaften);

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

        jLabel5.setText("Suchen  ID (2 Buchstaben eingeben)");

        hautpmenue.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        hautpmenue.setText("Hauptmenue");
        hautpmenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hautpmenueActionPerformed(evt);
            }
        });

        IMFg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        IMFg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IMFgActionPerformed(evt);
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
        jLabel6.setText("Fluggesellschaften :");

        IMFlug.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        IMFlug.setText("CSV Datei auswählen und in Datenbank importieren");
        IMFlug.setToolTipText("");
        IMFlug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IMFlugActionPerformed(evt);
            }
        });

        jLabel7.setText("2 Grossbuchstaben eingeben");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(IMFg)
                        .addGap(180, 180, 180)
                        .addComponent(hautpmenue))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel4))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(fg_name)
                                                .addComponent(line)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(bInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(bdelete))
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(fg_id, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(jLabel7)))
                                                    .addGap(0, 0, Short.MAX_VALUE)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txt_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(IMFlug)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(bDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(bAktl)))))
                        .addGap(0, 123, Short.MAX_VALUE)))
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
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fg_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fg_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(line, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bdelete))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(bDelete)
                    .addComponent(bAktl))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(IMFlug)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IMFg)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(hautpmenue)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void fg_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fg_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fg_idActionPerformed

    private void lineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lineActionPerformed

    private void fg_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fg_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fg_nameActionPerformed

    private void bInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInsertActionPerformed
        try {
            String sql = "INSERT INTO Fluggesellschaften(fg_id,fg_name,linie)values(?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, fg_id.getText());
            pst.setString(2, fg_name.getText());
            pst.setString(3, line.getText());

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
                String sql = "SELECT * FROM Fluggesellschaften WHERE fg_id=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, data);
                rs = pst.executeQuery();
                Fluggesellschaften.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }//GEN-LAST:event_txt_suchenKeyReleased

    private void txt_suchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_suchenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_suchenActionPerformed

    private void FluggesellschaftenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FluggesellschaftenMouseClicked
        try {
            int row = Fluggesellschaften.getSelectedRow();
            String selectID = Fluggesellschaften.getValueAt(row, 0).toString();
            String sql = "SELECT * from Fluggesellschaften where fg_id='" + selectID + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
//            Flughafen.setModel(DbUtils.resultSetToTableModel(rs));

            if (rs.next()) {
                String add1 = rs.getString("fg_id");
                String add2 = rs.getString("fg_name");
                String add3 = rs.getString("linie");
                fg_id.setText(add1);
                fg_name.setText(add2);
                line.setText(add3);

            }
//            pst.execute();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }//GEN-LAST:event_FluggesellschaftenMouseClicked

    private void hautpmenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hautpmenueActionPerformed
        Hauptmenue hm = new Hauptmenue();
        hm.setVisible(true);

        // Hide Current Form
        setVisible(false);
    }//GEN-LAST:event_hautpmenueActionPerformed

    private void IMFgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IMFgActionPerformed
        CsvImport();
    }//GEN-LAST:event_IMFgActionPerformed

    private void bAktlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAktlActionPerformed
        showDataToForm();
    }//GEN-LAST:event_bAktlActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed

        try {
            String sql = "Delete from Fluggesellschaften where fg_id='" + fg_id.getText() + "'";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            stmt = con.createStatement();
            //            Flughafen.setModel(DbUtils.resultSetToTableModel(rs));
            int confirm = JOptionPane.showConfirmDialog(this, "Möchten Sie Daten löschen?", fg_id.getText() + "Ja oder Nein", JOptionPane.YES_NO_CANCEL_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, " Data wurden gelöschen");
                Fluggesellschaften.setModel(DbUtils.resultSetToTableModel(rs));
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

    private void IMFlugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IMFlugActionPerformed

        CsvImport();

    }//GEN-LAST:event_IMFlugActionPerformed

    private void fg_idKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fg_idKeyPressed
        try {
            String sql = "select * from Fluggesellschaften";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                String id = rs.getString("fg_id");
                if (id.equalsIgnoreCase(fg_id.getText())) {
                    JOptionPane.showMessageDialog(this, "PK schon gegeben", "Wahnung!", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_fg_idKeyPressed

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
            java.util.logging.Logger.getLogger(FluggesellschaftForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FluggesellschaftForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FluggesellschaftForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FluggesellschaftForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FluggesellschaftForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Fluggesellschaften;
    private javax.swing.JButton IMFg;
    private javax.swing.JButton IMFlug;
    private javax.swing.JButton bAktl;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bInsert;
    private javax.swing.JButton bdelete;
    private javax.swing.JTextField fg_id;
    private javax.swing.JTextField fg_name;
    private javax.swing.JButton hautpmenue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField line;
    private javax.swing.JTextField txt_suchen;
    // End of variables declaration//GEN-END:variables

    private void clearData() {
        fg_id.setText("");
        fg_name.setText("");
        line.setText("");

    }
}
