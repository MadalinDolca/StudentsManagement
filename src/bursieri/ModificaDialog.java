package bursieri;

import global.Database;
import global.Mesaje;
import global.Setari;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;

/**
 * Creaza si contine toate elementele grafice ale ferestrei modal de modificare
 * a unui bursier, cat si metodele necesare pentru efectuarea acestei
 * operatiuni.
 *
 * @author Madalin
 */
public class ModificaDialog extends javax.swing.JDialog {

    /**
     * Pointer spre tabelul <code>bursieriTable</code> pentru preluare si
     * modificare date.
     *
     * @see bursieri.BursieriFrame#bursieriTable
     */
    JTable modificaBursieriTable;

    /**
     * Initializeaza componentele ferestrei, iconita ferestrei si pozitia
     * modalului in concordanta cu cea a ferestrei pentru bursieri.
     * Autocompleteaza <code>numeElevTextField</code> si
     * <code>tipBursaComboBox</code> cu datele selectate din
     * <code>bursieriTable</code>.
     *
     * @param parent proprietarul din care este afișat dialogul
     * @param modal specifică dacă dialogul blochează introducerea
     * utilizatorului în alte ferestre de nivel superior atunci când este afișat
     * @param bursieriTable tabelul din <code>BursieriFrame</code>
     *
     * @see bursieri.BursieriFrame#bursieriTable
     */
    public ModificaDialog(java.awt.Frame parent, boolean modal, JTable bursieriTable) {
        super(parent, modal);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(Setari.icon)).getImage()); // iconita ferestrei
        this.setLocationRelativeTo(parent); // pozitia modalului corespunde cu cea a ferestrei

        modificaBursieriTable = bursieriTable; // instantiere

        // completare camp nume elev
        numeElevTextField.setText((modificaBursieriTable.getValueAt(modificaBursieriTable.getSelectedRow(), 1)).toString());

        // adaugare in comboBox datele din array-ul din BursieriFrame
        tipBursaComboBox.setModel(new DefaultComboBoxModel(BursieriFrame.tipBurseArray.toArray()));
        tipBursaComboBox.setSelectedItem(BursieriFrame.tipBursaSelectata);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modificaButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tipBursaComboBox = new javax.swing.JComboBox<>();
        numeElevTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modifică Bursa");
        setResizable(false);

        modificaButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        modificaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/floppy-disk.png"))); // NOI18N
        modificaButton.setText("Modifică Bursa");
        modificaButton.setIconTextGap(10);
        modificaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificaButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Nume Elev");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Tipul de Bursă");

        tipBursaComboBox.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        tipBursaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        numeElevTextField.setEditable(false);
        numeElevTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        numeElevTextField.setText("Nume Elev");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(modificaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tipBursaComboBox, 0, 442, Short.MAX_VALUE)
                            .addComponent(numeElevTextField))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(numeElevTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipBursaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(modificaButton)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Modifica bursa elevului selectat in tabel cu noua bursa din dialog.
     * Autoselecteaza bursa din <code>tipBursaComboBox</code> cu noua bursa din
     * acest dialog.
     * 
     * <p>Afiseaza <code>mesajDialogReconectare()</code> daca preluarea ID-ului
     * bursei a esuat, daca actualizarea a esuat sau daca nu exista nici un
     * elev de modificat.</p>
     *
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului de
     * modificare
     * 
     * @see bursieri.BursieriFrame#tipBursaComboBox
     * @see global.Mesaje#mesajDialogReconectare(javax.swing.JDialog, java.lang.String, java.lang.String)
     */
    private void modificaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificaButtonActionPerformed
        String idElevAles; // variabila de memorare a id-ului elevului ales
        idElevAles = modificaBursieriTable.getValueAt(modificaBursieriTable.getSelectedRow(), 0).toString();

        int idBursaSelectata = 100;  // variabila de memorare a id-ului bursei alese

        try {
            // preluare id tip bursa selectata
            try {
                Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
                Database.rs = Database.stmt.executeQuery(
                        "select id from Burse where tipBursa = '"
                        + tipBursaComboBox.getSelectedItem().toString() + "';");

                while (Database.rs.next()) { // parcurgem fiecare linie din tabelul bazei de date
                    // adaugare continut celula din tabelul bazei de date in string
                    idBursaSelectata = Database.rs.getInt("id");
                }

            } catch (SQLException ex) {
                Mesaje.mesajDialogReconectare(this, "Eroare!",
                        "<html><b>Eroare preluare ID bursă!</b><br/>"
                        + "Probabil s-a întrerupt conexiunea!</html>");
            }

            // setare bursa selectata, elevului selectat
            try {
                Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
                Database.stmt.executeUpdate("update Elevi set idBursa = " + idBursaSelectata + " where id = " + idElevAles);

                this.dispose(); // inlaturare fereastra dupa adaugare

            } catch (SQLException ex) {
                Mesaje.mesajDialogReconectare(this, "Eroare!",
                        "<html><b>Actualizare eșuată!</b><br/>"
                        + "Probabil s-a întrerupt conexiunea!</html>");
            }

        } catch (Exception e) {
            Mesaje.mesajAtentionare(this, "Atenție!",
                    "<html>Nu există nici un elev de modificat!</html>", "warning");
        }

        // auto-selectare tip bursa din BursieriFrame la tipul de bursa ales la modificare
        BursieriFrame.pointerTipBursaComboBox.setSelectedItem(tipBursaComboBox.getSelectedItem().toString());
    }//GEN-LAST:event_modificaButtonActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (global.Setari.temaLookAndFeel.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModificaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton modificaButton;
    private javax.swing.JTextField numeElevTextField;
    private javax.swing.JComboBox<String> tipBursaComboBox;
    // End of variables declaration//GEN-END:variables
}
