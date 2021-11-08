/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsreiseveranstalter;

/**
 *
 * @author sbopp
 */
public class Hauptmenue extends javax.swing.JFrame {

    /**
     * Creates new form Hauptmenue
     */
    public Hauptmenue() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bBuchungen = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bPassagiere = new javax.swing.JButton();
        bFlughafen = new javax.swing.JButton();
        bFluggesellschaft = new javax.swing.JButton();
        bFlugzeuge = new javax.swing.JButton();
        bFluege = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        bFluglinie = new javax.swing.JButton();
        bXlsToCsv = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BS Reiseveranstalter FLY2");
        setName("MenueForm"); // NOI18N

        bBuchungen.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bBuchungen.setText("Buchungen");
        bBuchungen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBuchungenActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("BS Reiseveranstalter Fly2");

        jLabel2.setText("Bitte wählen Sie ");

        bPassagiere.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bPassagiere.setText("Passagiere");
        bPassagiere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPassagiereActionPerformed(evt);
            }
        });

        bFlughafen.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bFlughafen.setText("Flughafen");
        bFlughafen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFlughafenActionPerformed(evt);
            }
        });

        bFluggesellschaft.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bFluggesellschaft.setText("Fluggesellschaften");
        bFluggesellschaft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFluggesellschaftActionPerformed(evt);
            }
        });

        bFlugzeuge.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bFlugzeuge.setText("Flugzeuge");
        bFlugzeuge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFlugzeugeActionPerformed(evt);
            }
        });

        bFluege.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bFluege.setText("Fluege");
        bFluege.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFluegeActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Datenbank Verwaltungen");

        bFluglinie.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bFluglinie.setText("FLuglinie ");
        bFluglinie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFluglinieActionPerformed(evt);
            }
        });

        bXlsToCsv.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bXlsToCsv.setText("XLS Datei zu CSV Datei umwandeln");
        bXlsToCsv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bXlsToCsvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(210, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(207, 207, 207))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(308, 308, 308))))
            .addGroup(layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bXlsToCsv)
                    .addComponent(bFluglinie)
                    .addComponent(bFlughafen)
                    .addComponent(jLabel3)
                    .addComponent(bFluege)
                    .addComponent(bFlugzeuge)
                    .addComponent(bPassagiere)
                    .addComponent(bBuchungen)
                    .addComponent(bFluggesellschaft))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(bXlsToCsv, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bFluggesellschaft, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bFlughafen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bFlugzeuge, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bFluege, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bPassagiere, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bFluglinie, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bBuchungen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bFlughafenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFlughafenActionPerformed
        // New Form
        FlughafenForm fh = new FlughafenForm();
        fh.setVisible(true);

        // Hide Current Form
        setVisible(false);
    }//GEN-LAST:event_bFlughafenActionPerformed

    private void bBuchungenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBuchungenActionPerformed
        // New Form
        BuchungenForm buchung = new BuchungenForm();
        buchung.setVisible(true);

        // Hide Current Form
        setVisible(false);
    }//GEN-LAST:event_bBuchungenActionPerformed

    private void bFluggesellschaftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFluggesellschaftActionPerformed
        FluggesellschaftForm fg = new FluggesellschaftForm();
        fg.setVisible(true);

        // Hide Current Form
        setVisible(false);

    }//GEN-LAST:event_bFluggesellschaftActionPerformed

    private void bFlugzeugeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFlugzeugeActionPerformed
        FlugzeugeForm fz = new FlugzeugeForm();
        fz.setVisible(true);

        // Hide Current Form
        setVisible(false);
    }//GEN-LAST:event_bFlugzeugeActionPerformed

    private void bFluegeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFluegeActionPerformed
        FluegeForm f = new FluegeForm();
        f.setVisible(true);

        // Hide Current Form
        setVisible(false);
    }//GEN-LAST:event_bFluegeActionPerformed

    private void bFluglinieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFluglinieActionPerformed
        FluglinieForm fl = new FluglinieForm();
        fl.setVisible(true);

        // Hide Current Form
        setVisible(false);
    }//GEN-LAST:event_bFluglinieActionPerformed

    private void bPassagiereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPassagiereActionPerformed
        PassagiereForm ps = new PassagiereForm();
        ps.setVisible(true);

        // Hide Current Form
        setVisible(false);
    }//GEN-LAST:event_bPassagiereActionPerformed

    private void bXlsToCsvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bXlsToCsvActionPerformed
        XLStoCSVForm xc = new XLStoCSVForm();
        xc.setVisible(true);

        // Hide Current Form
        setVisible(false);

    }//GEN-LAST:event_bXlsToCsvActionPerformed

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
            java.util.logging.Logger.getLogger(Hauptmenue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Hauptmenue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Hauptmenue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Hauptmenue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Hauptmenue().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBuchungen;
    private javax.swing.JButton bFluege;
    private javax.swing.JButton bFluggesellschaft;
    private javax.swing.JButton bFlughafen;
    private javax.swing.JButton bFluglinie;
    private javax.swing.JButton bFlugzeuge;
    private javax.swing.JButton bPassagiere;
    private javax.swing.JButton bXlsToCsv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
