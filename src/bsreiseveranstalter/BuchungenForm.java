package bsreiseveranstalter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.proteanit.sql.DbUtils;

public class BuchungenForm extends javax.swing.JFrame {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private Statement stmt = null;

    public BuchungenForm() {
        initComponents();
        con = Connect.ConnectDB();
        showDataToForm();

//        showDay();
//        showMonth();
//        showYear();
    }

    // BS_DB zugriff
    public void showDataToForm() {
//        String sql="select*from country";
        String sql = "select * from Buchungen";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tbBuchungen.setModel(DbUtils.resultSetToTableModel(rs));
            while (rs.next()) {
                System.out.println(rs.getString("Buchungen"));
            }
        } catch (Exception e) {
            Logger.getLogger(BuchungenForm.class
                    .getName()).log(Level.SEVERE, null, e);
            //  e.printStackTrace();
        }
    }

    public void CsvImport() throws ParseException {
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
                        double preis = Double.parseDouble(arr[12].replace(",", ".")); //Datentyp möchte als Trenner den PUNKT! das zweite komma ist trenner
//                        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy ");
//                        Date date = (Date) formatter.parse("12.03.2008");
//                        int datum = Integer.parseInt(arr[18].replace(".", "-"));
//                        double preis = Double.parseDouble(arr[12].replace(",","."));
                        String sql = "IF NOT EXISTS (SELECT * FROM Buchungen WHERE b_id ='" + arr[17] + "')"
                                + "BEGIN "
                                + "INSERT INTO Buchungen" + "(b_id, b_dat, preis, fl_id, f_dat, fg_id, p_id)"
                                + "VALUES (" + arr[17] + ",'" + arr[18] + "'," + preis + "," + arr[3] + ",'" + arr[11] + "','" + arr[0] + "'," + arr[19] + ") "
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tbBuchungen = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        p_id_suchen = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        b_id = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        b_datum_suchen = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        preis = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        fl_id_suchen = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        fh_id_suchen = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        f_Datum_suchen = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        hauptmenue = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        bDelete = new javax.swing.JButton();
        bAktl = new javax.swing.JButton();
        IMFlug1 = new javax.swing.JButton();
        bdelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BS Reiseveranstalter");
        setName("BuchungenForm"); // NOI18N

        tbBuchungen.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tbBuchungen.setModel(new javax.swing.table.DefaultTableModel(
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
        tbBuchungen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBuchungenMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbBuchungen);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Buchungen Information");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Buchung Nummer :");

        p_id_suchen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_id_suchenActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Fluglinie :");

        b_id.setToolTipText("");
        b_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_idActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Passagier Nummer :");

        b_datum_suchen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_datum_suchenActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Buchung Datum :");

        preis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preisActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Preis :");

        fl_id_suchen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fl_id_suchenActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Fluggesellschaft :");

        fh_id_suchen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fh_id_suchenActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Flug Datum :");

        f_Datum_suchen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_Datum_suchenActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Ticket ausdrucken");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel2.setText("(Suchen nach Passagiere Name)");

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel3.setText("(Suchen nach Buchungen ID)");

        hauptmenue.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        hauptmenue.setText("Hauptmenue");
        hauptmenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hauptmenueActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Buchung :");

        bDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bDelete.setText("Delete");
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });

        bAktl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bAktl.setText("Aktualisieren");
        bAktl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAktlActionPerformed(evt);
            }
        });

        IMFlug1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        IMFlug1.setText("CSV Datei auswählen und in Datenbank importieren");
        IMFlug1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IMFlug1ActionPerformed(evt);
            }
        });

        bdelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bdelete.setText("Cancel");
        bdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(IMFlug1)
                        .addGap(18, 18, 18)
                        .addComponent(hauptmenue)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(b_datum_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(b_id)
                                        .addComponent(p_id_suchen, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                                    .addComponent(fh_id_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(preis, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(f_Datum_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fl_id_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(bdelete))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(bDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bAktl)))
                        .addGap(0, 6, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_id, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p_id_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_datum_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fh_id_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fl_id_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(f_Datum_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(preis, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bdelete)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bDelete)
                        .addComponent(bAktl)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hauptmenue)
                    .addComponent(IMFlug1))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void p_id_suchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_id_suchenActionPerformed
        try {
            if (p_id_suchen == null) {
                showDataToForm();
            } else {
                String data = p_id_suchen.getText();
                String sql = "SELECT * FROM Buchungen WHERE p_id=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, data);
                rs = pst.executeQuery();
                tbBuchungen.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_p_id_suchenActionPerformed

    private void b_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_idActionPerformed
        try {
            if (b_id == null) {
                showDataToForm();
            } else {
                String data = b_id.getText();
                String sql = "SELECT * FROM Buchungen WHERE b_id=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, data);
                rs = pst.executeQuery();
                tbBuchungen.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_b_idActionPerformed

    private void b_datum_suchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_datum_suchenActionPerformed
        try {
            if (p_id_suchen == null) {
                showDataToForm();
            } else {
                String data = p_id_suchen.getText();
                String sql = "SELECT * FROM Buchungen WHERE b_dat=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, data);
                rs = pst.executeQuery();
                tbBuchungen.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_b_datum_suchenActionPerformed

    private void preisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_preisActionPerformed

    private void fl_id_suchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fl_id_suchenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fl_id_suchenActionPerformed

    private void fh_id_suchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fh_id_suchenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fh_id_suchenActionPerformed

    private void f_Datum_suchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_Datum_suchenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_f_Datum_suchenActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MessageFormat header = new MessageFormat("Buchungen Information");
        MessageFormat footer = new MessageFormat("Page(1, number)");

        try {
            tbBuchungen.print(JTable.PrintMode.NORMAL, header, footer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbBuchungenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBuchungenMouseClicked
        try {
            int row = tbBuchungen.getSelectedRow();
            String selectID = tbBuchungen.getValueAt(row, 0).toString();
            String sql = "SELECT * from Buchungen where b_id='" + selectID + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                String add1 = rs.getString("b_id");
                String add2 = rs.getString("p_id");
                String add3 = rs.getString("b_dat");
                String add4 = rs.getString("fl_id");
                String add5 = rs.getString("fg_id");
                String add6 = rs.getString("f_dat");
                String add7 = rs.getString("preis");
                b_id.setText(add1);
                p_id_suchen.setText(add2);
                b_datum_suchen.setText(add3);
                fl_id_suchen.setText(add4);
                fh_id_suchen.setText(add5);
                f_Datum_suchen.setText(add6);
                preis.setText(add7);

            }
//            pst.execute();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_tbBuchungenMouseClicked

    private void hauptmenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hauptmenueActionPerformed
        Hauptmenue hm = new Hauptmenue();
        hm.setVisible(true);

        // Hide Current Form
        setVisible(false);
    }//GEN-LAST:event_hauptmenueActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed

        try {
            String sql = "Delete from Buchungen where b_id='" + b_id.getText() + "'";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            stmt = con.createStatement();
            //            Flughafen.setModel(DbUtils.resultSetToTableModel(rs));
            int confirm = JOptionPane.showConfirmDialog(this, "Möchten Sie Daten löschen?", b_id.getText() + "Ja oder Nein", JOptionPane.YES_NO_CANCEL_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, " Data wurden gelöschen");
                tbBuchungen.setModel(DbUtils.resultSetToTableModel(rs));
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

    private void IMFlug1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IMFlug1ActionPerformed
        try {
            CsvImport();
        } catch (ParseException ex) {
            Logger.getLogger(BuchungenForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_IMFlug1ActionPerformed

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
            java.util.logging.Logger.getLogger(BuchungenForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuchungenForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuchungenForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuchungenForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuchungenForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton IMFlug1;
    private javax.swing.JButton bAktl;
    private javax.swing.JButton bDelete;
    private javax.swing.JTextField b_datum_suchen;
    private javax.swing.JTextField b_id;
    private javax.swing.JButton bdelete;
    private javax.swing.JTextField f_Datum_suchen;
    private javax.swing.JTextField fh_id_suchen;
    private javax.swing.JTextField fl_id_suchen;
    private javax.swing.JButton hauptmenue;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField p_id_suchen;
    private javax.swing.JTextField preis;
    private javax.swing.JTable tbBuchungen;
    // End of variables declaration//GEN-END:variables

    private void clearData() {
        b_id.setText("");
        p_id_suchen.setText("");
        b_datum_suchen.setText("");
        fh_id_suchen.setText("");
        fl_id_suchen.setText("");
        f_Datum_suchen.setText("");
        preis.setText("");
    }
}
