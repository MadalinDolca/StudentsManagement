package clase_elevi;

import global.Database;
import global.Mesaje;
import global.Setari;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;

/**
 * Creaza si contine toate elementele grafice ale ferestrei modal de modificare
 * a elevului in clasa, cat si metodele necesare pentru efectuarea acestei
 * operatiuni.
 *
 * @author Madalin
 */
public class ModificaDialog extends javax.swing.JDialog {

    /**
     * Pointer spre tabelul "claseEleviTable" pentru preluare si modificare date.
     */
    JTable modificaClaseEleviTable;
    
    /**
     * Variabila de memorare a ID-ului clasei selectate din comboBox.
     */
    String idClasaSelectata;
    
    /**
     * Variabila de memorare a ID-ului clasei curente.
     */
    String idClasaCurenta;
    
    
    /**
     * Inițializează componentele nemodificabile ale ferestrei, iconița 
     * ferestrei si pozitia modalului in concordanta cu cea a ferestrei pentru 
     * clase. Autocompleteaza datele dialogului cu datele selectate in 
     * fereastra parinte.
     * 
     * <p>Afiseaza <code>mesajDialogReconectare</code> daca preluarea claselor
     * din baza de date a esuat.</p>
     * 
     * @param parent proprietarul din care este afișat dialogul
     * @param modal specifică dacă dialogul blochează introducerea utilizatorului 
     * în alte ferestre de nivel superior atunci când este afișat
     * @param claseEleviTable tabelul din <code>ClaseEleviTable</code>
     * 
     * @see clase_elevi.ClaseEleviFrame#claseEleviTable
     * @see global.Mesaje#mesajDialogReconectare(javax.swing.JDialog, java.lang.String, java.lang.String)
     */
    public ModificaDialog(java.awt.Frame parent, boolean modal, JTable claseEleviTable) {
        super(parent, modal);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(Setari.icon)).getImage()); // iconita ferestrei
        this.setLocationRelativeTo(parent); // pozitia modalului corespunde cu cea a ferestrei

        modificaClaseEleviTable = claseEleviTable; // instantiere

        // adugare nume elev selectat in textField
        numeElevTextField.setText(
                (modificaClaseEleviTable.getValueAt(modificaClaseEleviTable.getSelectedRow(), 1)).toString() + " "
                + (modificaClaseEleviTable.getValueAt(modificaClaseEleviTable.getSelectedRow(), 2)).toString() + " "
                + (modificaClaseEleviTable.getValueAt(modificaClaseEleviTable.getSelectedRow(), 3)).toString()
        );

        // adugare clasa curenta elev selectat in textField
        clasaCurentăTextField.setText(ClaseEleviFrame.numeClasaSelectata);

        // adaugarea claselor din DB in combobox la deschiderea ferestrei
        try {
            ArrayList<String> claseArray = new ArrayList<String>();
            Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
            Database.rs = Database.stmt.executeQuery("select nume from Clase;");

            while (Database.rs.next()) { // parcurgem fiecare linie din tabelul bazei de date
                // adaugam continutul celulelor din tabelul bazei de date in array
                claseArray.add(Database.rs.getString("nume"));
            }

            clasaComboBox.removeAllItems();
            clasaComboBox.setModel(new DefaultComboBoxModel(claseArray.toArray()));

        } catch (SQLException ex) {
            Mesaje.mesajDialogReconectare(this, "Eroare!",
                    "<html><b>Eroare preluare clase!</b><br/>"
                    + "Probabil s-a întrerupt conexiunea!</html>");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        actualizareButton = new javax.swing.JButton();
        numeElevTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        clasaComboBox = new javax.swing.JComboBox<>();
        clasaCurentăTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modifică Clasa Elevului");
        setResizable(false);

        actualizareButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        actualizareButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/floppy-disk.png"))); // NOI18N
        actualizareButton.setText("Actualizare");
        actualizareButton.setIconTextGap(10);
        actualizareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizareButtonActionPerformed(evt);
            }
        });

        numeElevTextField.setEditable(false);
        numeElevTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        numeElevTextField.setText("Nume Elev");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Elev");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Clasa Nouă");

        clasaComboBox.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        clasaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        clasaCurentăTextField.setEditable(false);
        clasaCurentăTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        clasaCurentăTextField.setText("Clasa Curentă");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Clasa Curentă");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(actualizareButton, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(111, 111, 111)
                                    .addComponent(jLabel1))))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(numeElevTextField)
                            .addComponent(clasaCurentăTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                            .addComponent(clasaComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeElevTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clasaCurentăTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clasaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(actualizareButton)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Actualizeaza clasa elevului cu noua clasa selectata din combo box.
     * 
     * <p>Arata <code>mesajDialogReconectare()</code> daca preluarea ID-ului
     * clasei selectate si ID-ul clasei curente a esuat.<p>
     * <p>Arata un mesaj de atentionare daca clasa selectata este identica cu
     * clasa curenta.<p>
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * de adaugare
     * 
     * @see global.Mesaje#mesajDialogReconectare(javax.swing.JDialog, java.lang.String, java.lang.String) 
     */
    private void actualizareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizareButtonActionPerformed
        int idRandSelectat = Integer.parseInt(modificaClaseEleviTable.getValueAt(modificaClaseEleviTable.getSelectedRow(), 0).toString());

        // preluare id clasa selectata din comboBox si id clasa curenta
        try {
            // preluare id clasa selectata din comboBox
            Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
            Database.rs = Database.stmt.executeQuery(
                    "select id from Clase where nume = '"
                    + clasaComboBox.getSelectedItem().toString() + "';");

            while (Database.rs.next()) { // parcurgem fiecare linie din tabelul bazei de date
                // adaugare continut celula din tabelul bazei de date in string
                idClasaSelectata = Database.rs.getString("id");
            }

            // preluare id clasa curenta
            Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
            Database.rs = Database.stmt.executeQuery(
                    "select id from Clase where nume = '"
                    + ClaseEleviFrame.numeClasaSelectata + "';");

            while (Database.rs.next()) { // parcurgem fiecare linie din tabelul bazei de date
                // adaugare continut celula din tabelul bazei de date in string
                idClasaCurenta = Database.rs.getString("id");
            }
        } catch (SQLException ex) {
            Mesaje.mesajDialogReconectare(this, "Eroare!",
                    "<html><b>Eroare preluare ID clasă selectată și curentă!</b><br/>"
                    + "Probabil s-a întrerupt conexiunea!</html>");
        }

        // verificare daca clasa curenta si clasa selectata sunt identice
        if (idClasaSelectata.equals(idClasaCurenta)) {
            Mesaje.mesajAtentionare(this, "Atenție!",
                    "<html><b>Clasa curentă</b> este identică cu<br/>"
                    + "<b>Clasa nou selectată</b>.</html>", "warning");

        } // adaugarea in baza de date a noilor date ale elevului
        else {
            try {
                Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
                Database.stmt.executeUpdate(
                        "update Elevi set "
                        + "idClasa = '" + idClasaSelectata + "' "
                        + "where id = " + idRandSelectat);

                this.dispose(); // inlaturare fereastra dupa actualizarea datelor

            } catch (SQLException ex) {
                Mesaje.mesajDialogReconectare(this, "Eroare!",
                        "<html><b>Actualizare eșuată!</b><br/>"
                        + "Probabil s-a întrerupt conexiunea!</html>");
            }
        }
    }//GEN-LAST:event_actualizareButtonActionPerformed

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
    private javax.swing.JButton actualizareButton;
    private javax.swing.JComboBox<String> clasaComboBox;
    private javax.swing.JTextField clasaCurentăTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField numeElevTextField;
    // End of variables declaration//GEN-END:variables
}
