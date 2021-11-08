package bsreiseveranstalter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author sbopp
 */
public class XLStoCSVForm extends javax.swing.JFrame {

    public XLStoCSVForm() {
        initComponents();
        XLSDatei();

    }

    public void XLSDatei() {

        JFileChooser fileopen = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("XLS file", "txt", "xls");
        fileopen.addChoosableFileFilter(filter);

        int ret = fileopen.showDialog(null, "Choose file");

        if (ret == JFileChooser.APPROVE_OPTION) {

            // Read Text file
            File file = fileopen.getSelectedFile();

            InputStream inp = null;
            try {
                inp = new FileInputStream(file);
                Workbook wb = WorkbookFactory.create(inp);

                for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                    //System.out.println(wb.getSheetAt(i).getSheetName());
                    echoAsCSV(wb.getSheetAt(i));
                }
            } catch (IOException ex) {
                Logger.getLogger(ExcelReading.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    inp.close();
                } catch (IOException ex) {
                    Logger.getLogger(ExcelReading.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        

        }
    }

    public void echoAsCSV(Sheet sheet) throws IOException {

        String source = "C:\\BSProjekt\\CSVDatei";
        File file = new File(source + "");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Datei ist existieren");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        Row row = null;
        DataFormatter formatter = new DataFormatter();


        File fout = new File( source + " ");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 2; i < sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            String sfout = "";
            for (int j = 0; j < row.getLastCellNum(); j++) {
                String zelle = formatter.formatCellValue(row.getCell(j));
                if (j == row.getLastCellNum() - 1) {
                    System.out.print(zelle);
                    sfout += zelle;
                    bw.write(sfout.trim());
                    bw.newLine();
                } else {
                    System.out.print(zelle + ";");
                    sfout += zelle + ";";
                }
            }
            System.out.println();
        }
        bw.close();

    }

    private class ExcelReading {

        public ExcelReading() {
        }
    }

    public void startProgress() {

        Runnable runnable = new Runnable() {

            public void run() {

                for (int i = 0; i <= 100; i++) {
                    try {

                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    jProgressBar.setValue(i);
                }
                bStart.setEnabled(true);
            }
        };
        new Thread(runnable).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jProgressBar = new javax.swing.JProgressBar();
        bStart = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        bCSV = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Datei XLS zu CSV umwendeln");

        jProgressBar.setForeground(new java.awt.Color(204, 255, 255));
        jProgressBar.setStringPainted(true);

        bStart.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bStart.setText("Start");
        bStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStartActionPerformed(evt);
            }
        });

        jToggleButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jToggleButton1.setText("XLS Datei auswählen...");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        bCSV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bCSV.setText("CSV Datei in Ordner gespeichen");
        bCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCSVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
            .addGroup(layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bStart)
                    .addComponent(jToggleButton1)
                    .addComponent(bCSV)
                    .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jToggleButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bCSV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bStart)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStartActionPerformed
//        XLSDatei();
        startProgress();
    }//GEN-LAST:event_bStartActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        XLSDatei();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void bCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCSVActionPerformed
//        void echoAsCSV(Sheet sheet);
        
    }//GEN-LAST:event_bCSVActionPerformed

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
            java.util.logging.Logger.getLogger(XLStoCSVForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XLStoCSVForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XLStoCSVForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XLStoCSVForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XLStoCSVForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton bCSV;
    private javax.swing.JButton bStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
