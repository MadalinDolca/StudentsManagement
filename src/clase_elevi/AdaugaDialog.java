package clase_elevi;

import global.Database;
import global.Mesaje;
import global.Setari;
import global.Splash;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

/**
 * Creaza si contine toate elementele grafice ale ferestrei modal de adaugare a
 * elevului in clasa, cat si metodele necesare pentru efectuarea acestei
 * operatiuni.
 *
 * @author Madalin
 */
public class AdaugaDialog extends javax.swing.JDialog {

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
     * <p>Afiseaza <code>mesajDialogReconectare</code> daca preluarea ID-ului
     * clasei din baza de date a esuat.</p>
     * <p>Afiseaza <code>mesajFrameReconectare</code> daca preluarea claselor
     * din baza de date a esuat.</p>
     * 
     * @param parent proprietarul din care este afișat dialogul
     * @param modal specifică dacă dialogul blochează introducerea utilizatorului 
     * în alte ferestre de nivel superior atunci când este afișat
     * 
     * @see global.Mesaje#mesajDialogReconectare(javax.swing.JDialog, java.lang.String, java.lang.String) 
     * @see global.Mesaje#mesajFrameReconectare(javax.swing.JFrame, java.lang.String, java.lang.String) 
     */
    public AdaugaDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(Setari.icon)).getImage()); // iconita ferestrei
        this.setLocationRelativeTo(parent); // pozitia modalului corespunde cu cea a ferestrei

        // adaugare clasa selectata in texfield
        clasaSelectataTextField.setText(ClaseEleviFrame.numeClasaSelectata);

        // preluare id clasa curenta
        try {
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
                    "<html><b>Eroare preluare ID clase!</b><br/>"
                    + "Probabil s-a întrerupt conexiunea!</html>");
        }

        // adaugarea claselor din DB, diferite de clasa curenta, in combobox la deschiderea ferestrei
        try {
            ArrayList<String> eleviArray = new ArrayList<String>();
            Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
            Database.rs = Database.stmt.executeQuery(
                    "select id, nume, initialaTata, prenume from Elevi where idClasa != "
                    + idClasaCurenta + " order by id;");

            while (Database.rs.next()) { // parcurgem fiecare linie din tabelul bazei de date
                // adaugam continutul celulelor din tabelul bazei de date in array
                eleviArray.add(Database.rs.getString("id") + " | "
                        + Database.rs.getString("nume") + " "
                        + Database.rs.getString("initialaTata") + " "
                        + Database.rs.getString("prenume"));
            }

            alegeElevComboBox.removeAllItems();
            alegeElevComboBox.setModel(new DefaultComboBoxModel(eleviArray.toArray()));

        } catch (SQLException ex) {
            Mesaje.mesajFrameReconectare(
                    Splash.autentificareFrame.interfataFrame.claseEleviFrame, "Eroare!",
                    "<html><b>Eroare preluare clase elevi!</b><br/>"
                    + "Probabil s-a întrerupt conexiunea!</html>");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adaugaDialogButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        alegeElevComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        clasaSelectataTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adaugă Elev în Clasa Curentă");
        setResizable(false);

        adaugaDialogButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        adaugaDialogButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/plus.png"))); // NOI18N
        adaugaDialogButton.setText("Adaugă în Clasă");
        adaugaDialogButton.setIconTextGap(10);
        adaugaDialogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adaugaDialogButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Alege Elev");

        alegeElevComboBox.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        alegeElevComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Clasa Selectată");

        clasaSelectataTextField.setEditable(false);
        clasaSelectataTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        clasaSelectataTextField.setText("Clasa Selectată");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adaugaDialogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(clasaSelectataTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                            .addComponent(alegeElevComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alegeElevComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(clasaSelectataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(adaugaDialogButton)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Adauga elevul in clasa selectata in combo box.
     * 
     * <p>Arata <code>mesajDialogReconectare()</code> daca adaugarea elevului
     * in clasa a esuat.<p>
     * <p>Arata un mesaj de atentionare daca nu exista nici un elev fara clasa.<p>
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * de adaugare
     * 
     * @see global.Mesaje#mesajDialogReconectare(javax.swing.JDialog, java.lang.String, java.lang.String) 
     */
    private void adaugaDialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaDialogButtonActionPerformed
        String idElevAles;

        try {
            // preluare id elev ales (copiere pana la intalnirea primului spatiu)
            idElevAles = alegeElevComboBox.getSelectedItem().toString();
            if (idElevAles.contains(" ")) {
                idElevAles = idElevAles.substring(0, idElevAles.indexOf(" "));
            }

            // setare clasa curenta elevului selectat
            try {
                Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
                Database.stmt.executeUpdate("update Elevi set idClasa = " + idClasaCurenta + " where id = " + idElevAles);

                this.dispose(); // inlaturare fereastra dupa adaugare

            } catch (SQLException ex) {
                Mesaje.mesajDialogReconectare(this, "Eroare!",
                        "<html><b>Adăugare eșuată!</b><br/>"
                        + "Probabil s-a întrerupt conexiunea!</html>");
            }

        } catch (Exception e) {
            Mesaje.mesajAtentionare(this, "Atenție!", "Nu există nici un elev de adăugat!", "warning");
        }
    }//GEN-LAST:event_adaugaDialogButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AdaugaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdaugaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdaugaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdaugaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adaugaDialogButton;
    private javax.swing.JComboBox<String> alegeElevComboBox;
    private javax.swing.JTextField clasaSelectataTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
