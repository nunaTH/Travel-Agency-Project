package bsreiseveranstalter;

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

public class FluglinieForm extends javax.swing.JFrame {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private Statement stmt = null;

    public FluglinieForm() {
        initComponents();
        con = Connect.ConnectDB();
        showDataToForm();
        showFluglinie();
//        showDay();
//        showMonth();
//        showYear();
    }

    // BS_DB zugriff
    public void showDataToForm() {
//        String sql="select*from country";
        String sql = "select * from Fluglinie";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tbFluglinie.setModel(DbUtils.resultSetToTableModel(rs));
            while (rs.next()) {
                System.out.println(rs.getString("Fluglinie"));
            }
        } catch (Exception e) {
            Logger.getLogger(FluglinieForm.class
                    .getName()).log(Level.SEVERE, null, e);
            //  e.printStackTrace();
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
                        if (arr.length < 7) {
                            break; // Abbruchkriterium wegen der letzten zeile von 3 spalten (datensatz blaa)
                        }
                        double preis = Double.parseDouble(arr[12].replace(",", ".")); //Datentyp möchte als Trenner den PUNKT! das zweite komma ist trenner
                        String sql = "IF NOT EXISTS (SELECT * FROM Fluglinie WHERE fl_id =" + arr[3] + ")"
                                + "BEGIN " // Quasi geschweifte Klammer bei if else anw.
                                + "INSERT INTO Fluglinie" + "(fl_id, start_fh, ziel_fh, fg_id, dauer, datum, preis)"
                                + "VALUES ('" + arr[3] + "','" + arr[4] + "','" + arr[7] + "','" + arr[0] + "','" + arr[10] + "','" + arr[11] + "'," + preis + ") "
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
        // Datenbank in combobox
        //    public void VonFlughafen() {
        //        try {
        //            String sql = "select start_fh from Fluglinie";
        //            pst = con.prepareStatement(sql);
        //            rs = pst.executeQuery();
        //            while (rs.next()) {
        //                String name = rs.getString("start_fh");
        //                ComboVonCountry.addItem(name);
        //
        //            }
        //
        //        } catch (Exception e) {
        //            Logger.getLogger(FluglinieForm.class.getName()).log(Level.SEVERE, null, e);
        //            //  e.printStackTrace();
        //        }
        //    }
        //    public void NachFlughafen() {
        //        try {
        //            String sql = "select ziel_fh from Fluglinie";
        //            pst = con.prepareStatement(sql);
        //            rs = pst.executeQuery();
        //            while (rs.next()) {
        //                String name = rs.getString("ziel_fh");
        //                ComboNachFlughafen.addItem(name);
        //            }
        //
        //        } catch (Exception e) {
        //            Logger.getLogger(FluglinieForm.class.getName()).log(Level.SEVERE, null, e);
        //            //  e.printStackTrace();
        //        }
        //    }
        //    public void comboDatum() {
        //        try {
        //            String sql = "select datum from Fluglinie";
        //            pst = con.prepareStatement(sql);
        //            rs = pst.executeQuery();
        //            while (rs.next()) {
        //                String name = rs.getString("datum");
        //                comboDatum.addItem(name);
        //            }
        //
        //        } catch (Exception e) {
        //            Logger.getLogger(FluglinieForm.class.getName()).log(Level.SEVERE, null, e);
        //            //  e.printStackTrace();
        //        }
        //    }
        // join DB in Tabellen
    public void showFluglinie() {
        String sql = "select * from Fluglinie";
//        String sql = "SELECT country_id, country, actor_id, actor.first_name FORM country INNER JOIN actor"
//                + "ON country.country_id=actor.actor_id";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tbFluglinie.setModel(DbUtils.resultSetToTableModel(rs));
            while (rs.next()) {
                System.out.println(rs.getString("fluglinie"));
            }
        } catch (Exception e) {
            Logger.getLogger(FluglinieForm.class
                    .getName()).log(Level.SEVERE, null, e);
            //  e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tbFluglinie = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        start_fg_suchen = new javax.swing.JTextField();
        dauer = new javax.swing.JTextField();
        datum_suchen = new javax.swing.JTextField();
        fg_id_suchen = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        fl_id_suchen = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        bAktl = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        ziel_id_suchen = new javax.swing.JTextField();
        preis = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        IMFlug = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BS Reiseveranstalter FLY2");
        setName("FluglinieForm"); // NOI18N

        tbFluglinie.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tbFluglinie.setModel(new javax.swing.table.DefaultTableModel(
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
        tbFluglinie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFluglinieMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbFluglinie);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Fluglinie Suchen");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Von :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nach :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Datum :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Flug Linie :");

        start_fg_suchen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start_fg_suchenActionPerformed(evt);
            }
        });

        dauer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dauerActionPerformed(evt);
            }
        });

        datum_suchen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datum_suchenActionPerformed(evt);
            }
        });

        fg_id_suchen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fg_id_suchenActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Fluggesellschaft :");

        fl_id_suchen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fl_id_suchenActionPerformed(evt);
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

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Preis :");

        ziel_id_suchen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ziel_id_suchenActionPerformed(evt);
            }
        });

        preis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preisActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Dauer :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel10.setText("yyyy-mm-dd");

        jLabel11.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel11.setText("Euro (€)");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Fluglinie Datenbank :");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel13.setText("2 Buchstaben");

        jLabel14.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel14.setText("3 Buchstaben");

        jLabel15.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel15.setText("Nummer ");

        jLabel16.setText("3 Buchstaben");

        IMFlug.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        IMFlug.setText("CSV Datei auswählen und in Datenbank importieren");
        IMFlug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IMFlugActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel17.setText("3 Buchstaben");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel9))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(fg_id_suchen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(datum_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(start_fg_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel13))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel6)
                                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(ziel_id_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(preis, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jLabel11))
                                                            .addComponent(jLabel15)
                                                            .addComponent(fl_id_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel16)
                                                            .addComponent(jLabel17)))
                                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(dauer, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(IMFlug)
                                    .addComponent(bAktl))))
                        .addGap(0, 160, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datum_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fg_id_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(start_fg_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(ziel_id_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel16)
                                .addGap(40, 40, 40)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dauer, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(preis, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fl_id_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAktl)
                    .addComponent(jLabel12))
                .addGap(18, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(IMFlug))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void start_fg_suchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_fg_suchenActionPerformed
        try {
            if (start_fg_suchen == null) {
                showDataToForm();
            } else {
                String data = start_fg_suchen.getText();
                String sql = "SELECT * FROM Fluglinie WHERE start_fh=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, data);
                rs = pst.executeQuery();
                tbFluglinie.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_start_fg_suchenActionPerformed

    private void dauerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dauerActionPerformed
        try {
            if (dauer == null) {
                showDataToForm();
            } else {
                String fl = dauer.getText();
                String sql = "SELECT * FROM Fluglinie WHERE dauer=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, fl);
                rs = pst.executeQuery();
                tbFluglinie.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_dauerActionPerformed

    private void datum_suchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datum_suchenActionPerformed
        try {
            if (datum_suchen == null) {
                showDataToForm();
            } else {
                String fl = datum_suchen.getText();
                String sql = "SELECT * FROM Fluglinie WHERE datum=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, fl);
                rs = pst.executeQuery();
                tbFluglinie.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_datum_suchenActionPerformed

    private void fg_id_suchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fg_id_suchenActionPerformed
        try {
            if (fg_id_suchen == null) {
                showDataToForm();
            } else {
                String fg = fg_id_suchen.getText();
                String sql = "SELECT * FROM Fluglinie WHERE fg_id=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, fg);
                rs = pst.executeQuery();
                tbFluglinie.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_fg_id_suchenActionPerformed

    private void fl_id_suchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fl_id_suchenActionPerformed
        try {
            if (fl_id_suchen == null) {
                showDataToForm();
            } else {
                String fl = fl_id_suchen.getText();
                String sql = "SELECT * FROM Fluglinie WHERE fl_id=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, fl);
                rs = pst.executeQuery();
                tbFluglinie.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_fl_id_suchenActionPerformed

    private void tbFluglinieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFluglinieMouseClicked
        try {

            int row = tbFluglinie.getSelectedRow();
            String selectID = tbFluglinie.getValueAt(row, 0).toString();
            String sql = "SELECT * from Fluglinie where fl_id='" + selectID + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
//            Flughafen.setModel(DbUtils.resultSetToTableModel(rs));

            if (rs.next()) {
                String add1 = rs.getString("datum");
                String add2 = rs.getString("fg_id");
                String add3 = rs.getString("fl_id");
                String add4 = rs.getString("start_fh");
                String add5 = rs.getString("ziel_fh");
                String add6 = rs.getString("dauer");
                String add7 = rs.getString("preis");

                datum_suchen.setText(add1);
                fg_id_suchen.setText(add2);
                fl_id_suchen.setText(add3);
                start_fg_suchen.setText(add4);
                ziel_id_suchen.setText(add5);
                dauer.setText(add6);
                preis.setText(add7);
            }
//            pst.execute();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_tbFluglinieMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Hauptmenue hm = new Hauptmenue();
        hm.setVisible(true);

        // Hide Current Form
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bAktlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAktlActionPerformed
        showDataToForm();
    }//GEN-LAST:event_bAktlActionPerformed

    private void ziel_id_suchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ziel_id_suchenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ziel_id_suchenActionPerformed

    private void preisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_preisActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cleardata();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void IMFlugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IMFlugActionPerformed

        CsvImport();
    }//GEN-LAST:event_IMFlugActionPerformed

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
            java.util.logging.Logger.getLogger(FluglinieForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FluglinieForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FluglinieForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FluglinieForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FluglinieForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton IMFlug;
    private javax.swing.JButton bAktl;
    private javax.swing.JTextField datum_suchen;
    private javax.swing.JTextField dauer;
    private javax.swing.JTextField fg_id_suchen;
    private javax.swing.JTextField fl_id_suchen;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField preis;
    private javax.swing.JTextField start_fg_suchen;
    private javax.swing.JTable tbFluglinie;
    private javax.swing.JTextField ziel_id_suchen;
    // End of variables declaration//GEN-END:variables

    private void cleardata() {

        datum_suchen.setText("");
        fg_id_suchen.setText("");
        fl_id_suchen.setText("");
        start_fg_suchen.setText("");
        ziel_id_suchen.setText("");
        dauer.setText("");
        preis.setText("");

    }
}
