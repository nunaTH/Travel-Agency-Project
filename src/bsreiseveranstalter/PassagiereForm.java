package bsreiseveranstalter;

import java.awt.event.MouseEvent;
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

public class PassagiereForm extends javax.swing.JFrame {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private Statement stmt = null;

    public PassagiereForm() {
        initComponents();
        con = Connect.ConnectDB();
        showDataToForm();
        showDataCombo();
//        showFluglinie();
//        showDay();
//        showMonth();
//        showYear();
    }

    // BS_DB zugriff
    public void showDataToForm() {
//        String sql="select*from country";
        String sql = "select * from Passagiere";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tbPassagiere.setModel(DbUtils.resultSetToTableModel(rs));
            while (rs.next()) {
                System.out.println(rs.getString("Passagiere"));
            }
        } catch (Exception e) {
            Logger.getLogger(PassagiereForm.class
                    .getName()).log(Level.SEVERE, null, e);
            //  e.printStackTrace();
        }
    }

    public void showDataCombo() {
        try {
            String sql = "select * from Anrede";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
//            tbPassagiere.setModel(DbUtils.resultSetToTableModel(rs));
            while (rs.next()) {
                String name = rs.getString("a_id");
                cbAnrede.addItem(name);
            }
        } catch (Exception e) {
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
                        if (arr.length < 8) {
                            break; // Abbruchkriterium datensatz 20!
                        }
//                    double preis = Double.parseDouble(arr[12].replace(",", ".")); //Datentyp möchte als Trenner den PUNKT! das zweite komma ist trenner
                        String sql = "IF NOT EXISTS (SELECT * FROM Passagiere WHERE p_id =" + arr[19] + ")"
                                + "BEGIN "
                                + "INSERT INTO Passagiere" + "(p_id, anr, name, plz, ort, strasse, land)"
                                + "VALUES (" + arr[19] + ",'" + arr[20] + "','" + arr[21] + "','" + arr[22] + "','" + arr[23] + "','" + arr[24] + "','" + arr[25] + "') "
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
        tbPassagiere = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        strasse = new javax.swing.JTextField();
        plz = new javax.swing.JTextField();
        passagier = new javax.swing.JTextField();
        Anrede = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        land = new javax.swing.JTextField();
        ort = new javax.swing.JTextField();
        txt_suchen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        bInsert = new javax.swing.JButton();
        bdelete1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cbAnrede = new javax.swing.JComboBox<>();
        bAktl = new javax.swing.JButton();
        bDelete1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        anredeCVS = new javax.swing.JButton();
        IMFlug1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BS Reiseveranstalter FLY2");
        setName("PassagiereForm"); // NOI18N

        tbPassagiere.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tbPassagiere.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPassagiere.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPassagiereMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbPassagiere);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Passagiere Hinzufügen");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Strasse :");
        jLabel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("PLZ  :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Passagier ID :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Name :");

        strasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                strasseActionPerformed(evt);
            }
        });

        plz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plzActionPerformed(evt);
            }
        });

        passagier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passagierActionPerformed(evt);
            }
        });
        passagier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passagierKeyPressed(evt);
            }
        });

        Anrede.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Anrede.setText("Anrede :");

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Hauptmenue");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Ort  :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Land  :");

        land.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                landActionPerformed(evt);
            }
        });

        ort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ortActionPerformed(evt);
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

        jLabel5.setText("Suchen ID ");

        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });
        txt_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nameKeyReleased(evt);
            }
        });

        jLabel9.setText("Suchen Name ");

        bInsert.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bInsert.setText("Insert");
        bInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInsertActionPerformed(evt);
            }
        });

        bdelete1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bdelete1.setText("Cancel");
        bdelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdelete1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Hilfsobjekte:");

        bAktl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bAktl.setText("Aktualisieren");
        bAktl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAktlActionPerformed(evt);
            }
        });

        bDelete1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bDelete1.setText("Delete");
        bDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDelete1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Datensatz :");

        anredeCVS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        anredeCVS.setText("Anrede Spalten von CSV Datei hinzufügen");
        anredeCVS.setActionCommand("");
        anredeCVS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anredeCVSActionPerformed(evt);
            }
        });

        IMFlug1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        IMFlug1.setText("CSV Datei auswählen und in Datenbank importieren");
        IMFlug1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IMFlug1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(Anrede)
                                    .addComponent(jLabel6))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(passagier, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cbAnrede, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(56, 56, 56)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel7)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(name)
                                                .addGap(23, 23, 23)
                                                .addComponent(jLabel8)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(strasse, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                            .addComponent(plz)
                                            .addComponent(ort)
                                            .addComponent(land)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(bInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bdelete1))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(IMFlug1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(bDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(bAktl))
                                            .addComponent(anredeCVS))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(17, 17, 17))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passagier, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(strasse, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Anrede)
                    .addComponent(plz, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbAnrede, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ort, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(land, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bInsert)
                    .addComponent(bdelete1))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bDelete1)
                    .addComponent(bAktl)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(anredeCVS)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jButton1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IMFlug1))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void strasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_strasseActionPerformed
        try {
            if (strasse == null) {
                showDataToForm();
            } else {
                String data = strasse.getText();
                String sql = "SELECT * FROM Passagiere WHERE strasse=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, data);
                rs = pst.executeQuery();
                tbPassagiere.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_strasseActionPerformed

    private void plzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plzActionPerformed
        try {
            if (plz == null) {
                showDataToForm();
            } else {
                String fl = plz.getText();
                String sql = "SELECT * FROM Passagiere WHERE plz=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, fl);
                rs = pst.executeQuery();
                tbPassagiere.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_plzActionPerformed

    private void passagierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passagierActionPerformed
        try {
            if (passagier == null) {
                showDataToForm();
            } else {
                String fl = passagier.getText();
                String sql = "SELECT * FROM Passagiere WHERE p_id=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, fl);
                rs = pst.executeQuery();
                tbPassagiere.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_passagierActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void tbPassagiereMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPassagiereMouseClicked
        try {

            int row = tbPassagiere.getSelectedRow();
            String selectID = tbPassagiere.getValueAt(row, 0).toString();
            String sql = "SELECT * from Passagiere where p_id='" + selectID + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
//            Flughafen.setModel(DbUtils.resultSetToTableModel(rs));

            if (rs.next()) {
                String add1 = rs.getString("p_id");
                String add2 = rs.getString("anr");
                String add3 = rs.getString("name");
                String add4 = rs.getString("strasse");
                String add5 = rs.getString("plz");
                String add6 = rs.getString("ort");
                String add7 = rs.getString("land");

                passagier.setText(add1);
                cbAnrede.setSelectedItem(add2);
                name.setText(add3);
                strasse.setText(add4);
                plz.setText(add5);
                ort.setText(add6);
                land.setText(add7);

            }
//            pst.execute();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_tbPassagiereMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Hauptmenue hm = new Hauptmenue();
        hm.setVisible(true);

        // Hide Current Form
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void landActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_landActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_landActionPerformed

    private void ortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ortActionPerformed

    private void txt_suchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_suchenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_suchenActionPerformed

    private void txt_suchenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_suchenKeyReleased
        try {
            if (txt_suchen == null) {
                showDataToForm();
            } else {
                String data = txt_suchen.getText();
                String sql = "SELECT * FROM Passagiere WHERE p_id=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, data);
                rs = pst.executeQuery();
                tbPassagiere.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_txt_suchenKeyReleased

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        try {
            if (txt_name == null) {
                showDataToForm();
            } else {
                String data = txt_name.getText();
                String sql = "SELECT * FROM Passagiere WHERE name=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, data);
                rs = pst.executeQuery();
                tbPassagiere.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_txt_nameActionPerformed

    private void txt_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nameKeyReleased
        try {
            if (txt_name == null) {
                showDataToForm();
            } else {
                String data = txt_name.getText();
                String sql = "SELECT * FROM Passagiere WHERE name=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, data);
                rs = pst.executeQuery();
                tbPassagiere.setModel(DbUtils.resultSetToTableModel(rs));

                pst.execute();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_txt_nameKeyReleased

    private void bInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInsertActionPerformed
        try {
            String sql = "INSERT INTO Passagiere(p_id,anr,name,plz,ort,strasse,land)values(?,?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, passagier.getText());
//            pst.setString(2, anrede.getText());
            pst.setString(2, cbAnrede.getSelectedItem().toString());
            pst.setString(3, name.getText());
            pst.setString(4, strasse.getText());
            pst.setString(5, plz.getText());
            pst.setString(6, ort.getText());
            pst.setString(7, land.getText());

            pst.execute();

            JOptionPane.showMessageDialog(this, "Möchten Sie speichern", "Daten wurden in Tabellen gespeichert", JOptionPane.INFORMATION_MESSAGE);
            showDataToForm();
            clearData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_bInsertActionPerformed

    private void bdelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdelete1ActionPerformed
        clearData();
    }//GEN-LAST:event_bdelete1ActionPerformed

    private void passagierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passagierKeyPressed
        try {
            String sql = "select * from Passagiere";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
//            tbPassagiere.setModel(DbUtils.resultSetToTableModel(rs));
            while (rs.next()) {
                String id = rs.getString("p_id");
                if (id.equalsIgnoreCase(passagier.getText())) {
                    JOptionPane.showMessageDialog(this, "PK schon gegeben", "Wahnung!", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_passagierKeyPressed

    private void bAktlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAktlActionPerformed
        showDataToForm();
    }//GEN-LAST:event_bAktlActionPerformed

    private void bDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDelete1ActionPerformed

        try {
            String sql = "Delete from Passagiere where p_id='" + passagier.getText() + "'";
//            int row = tbPassagiere.getSelectedRow();
//            String selectID = tbPassagiere.getValueAt(row, 0).toString();

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            stmt = con.createStatement();
//            Flughafen.setModel(DbUtils.resultSetToTableModel(rs));
            int confirm = JOptionPane.showConfirmDialog(this, "Möchten Sie Daten löschen?", passagier.getText() + "Ja oder Nein", JOptionPane.YES_NO_CANCEL_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, " Data wurden gelöschen");
                tbPassagiere.setModel(DbUtils.resultSetToTableModel(rs));
//                rs.first();
//                tbPassagiereMouseClicked((MouseEvent) rs);
//                clearData();
//                showDataToForm();
//                dataTotext(rs);
            } else if (confirm == JOptionPane.NO_OPTION) {
//                JOptionPane.showMessageDialog(this," Data wurden nicht gelöschen");
                return;
            }
//                String add1 = rs.getString("p_id");
//                String add2 = rs.getString("anr");
//                String add3 = rs.getString("name");
//                String add4 = rs.getString("strasse");
//                String add5 = rs.getString("plz");
//                String add6 = rs.getString("ort");
//                String add7 = rs.getString("land");
//
////                passagier.setText(add1);
////                cbAnrede.setSelectedItem(add2);
////                name.setText(add3);
////                strasse.setText(add4);
////                plz.setText(add5);
////                ort.setText(add6);
////                land.setText(add7);
//
//            }
//            pst.execute();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_bDelete1ActionPerformed

    private void anredeCVSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anredeCVSActionPerformed
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
                        if (arr.length < 1) {
                            break;
                        }
//                    double preis = Double.parseDouble(arr[12].replace(",", ".")); //Datentyp möchte als Trenner den PUNKT! das zweite komma ist trenner
////                    
                        
                         String sql = "IF NOT EXISTS (SELECT * FROM Anrede WHERE a_id ='" + arr[0] + "')"
                                + "BEGIN " // Quasi geschweifte Klammer bei if else anw.
                                + "INSERT INTO Anrede" + "(a_id)"
                                + "VALUES ('" + arr[0] + "') " + "END";
                  
                        s.execute(sql);

//                        System.out.printf("Import success Nr. %s!\n", arr[17]);
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
    }//GEN-LAST:event_anredeCVSActionPerformed

    private void IMFlug1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IMFlug1ActionPerformed
         CsvImport();
    }//GEN-LAST:event_IMFlug1ActionPerformed

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
            java.util.logging.Logger.getLogger(PassagiereForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PassagiereForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PassagiereForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PassagiereForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PassagiereForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Anrede;
    private javax.swing.JButton IMFlug1;
    private javax.swing.JButton anredeCVS;
    private javax.swing.JButton bAktl;
    private javax.swing.JButton bDelete1;
    private javax.swing.JButton bInsert;
    private javax.swing.JButton bdelete1;
    private javax.swing.JComboBox<String> cbAnrede;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField land;
    private javax.swing.JTextField name;
    private javax.swing.JTextField ort;
    private javax.swing.JTextField passagier;
    private javax.swing.JTextField plz;
    private javax.swing.JTextField strasse;
    private javax.swing.JTable tbPassagiere;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_suchen;
    // End of variables declaration//GEN-END:variables

    private void clearData() {
        passagier.setText("");
        cbAnrede.setSelectedIndex(0);
        name.setText("");
        strasse.setText("");
        plz.setText("");
        ort.setText("");
        land.setText("");

    }
}
