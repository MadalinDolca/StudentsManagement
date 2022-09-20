package bursieri;

import global.Database;
import global.Mesaje;
import global.Setari;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

/**
 * Creaza si contine toate elementele grafice ale ferestrei modal de adaugare a 
 * unui bursier, cat si metodele necesare pentru efectuarea acestei operatiuni.
 *
 * @author Madalin
 */
public class AdaugaDialog extends javax.swing.JDialog {

    /**
     * Inițializează componentele ferestrei, iconița ferestrei si pozitia
     * modalului in concordanta cu cea a ferestrei pentru bursieri. Adauga in
     * <code>alegeElevComboBox</code> elevii fara bursa din baza de date la
     * deschiderea ferestrei. Adauga in <code>tipBursaComboBox</code> datele din
     * array-ul <code>tipBurseArray</code>.
     *
     * <p>Se afiseaza un mesaj de eroare in cazul in care preluarea listei cu
     * elevi din baza de date a esuat.</p>
     *
     * @param parent proprietarul din care este afișat dialogul
     * @param modal specifică dacă dialogul blochează introducerea utilizatorului 
     * în alte ferestre de nivel superior atunci când este afișat
     */
    public AdaugaDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(Setari.icon)).getImage()); // iconita ferestrei
        this.setLocationRelativeTo(parent); // pozitia modalului corespunde cu cea a ferestrei

        // adaugare elevi fara bursa din DB in combobox la deschiderea ferestrei
        try {
            ArrayList<String> eleviArray = new ArrayList<String>();
            Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
            Database.rs = Database.stmt.executeQuery(
                    "select Elevi.id, Elevi.nume, Elevi.initialaTata, Elevi.prenume from Elevi "
                    + "inner join Burse on Elevi.idBursa = Burse.id "
                    + "where Elevi.idBursa = 100;");

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
            Mesaje.mesajDialogReconectare(this, "Eroare!",
                    "<html><b>Preluare listă elevi eșuată!</b><br/>"
                    + "Probabil s-a întrerupt conexiunea.</html>"); // afisare mesaj reconectare in caz de eroare

        }

        // adaugare in comboBox datele din array-ul din BursieriFrame
        tipBursaComboBox.setModel(new DefaultComboBoxModel(BursieriFrame.tipBurseArray.toArray()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        alegeElevComboBox = new javax.swing.JComboBox<>();
        tipBursaComboBox = new javax.swing.JComboBox<>();
        adaugaButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adaugă Bursier");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Alege Elev");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Tipul de Bursă");

        alegeElevComboBox.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        alegeElevComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tipBursaComboBox.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        tipBursaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        adaugaButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        adaugaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/plus.png"))); // NOI18N
        adaugaButton.setText("Adaugă Bursier");
        adaugaButton.setIconTextGap(10);
        adaugaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adaugaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(adaugaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(alegeElevComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tipBursaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(alegeElevComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipBursaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(adaugaButton)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Va prelua ID-ul elevului ales, ID-ul bursei selectate si va actualiza in
     * baza de date bursa elevului cu cea selectata.
     * 
     * <p>Afiseaza un mesaj de eroare daca preluarea ID-ului bursei a esuat.</p>
     * <p>Afiseaza un mesaj de eroare daca adaugarea bursei a esuat.</p>
     * <p>Afiseaza un mesaj daca nu există nici un elev de adăugat.</p>
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * de adaugare
     */
    private void adaugaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaButtonActionPerformed
        String idElevAles; // variabila de memorare a id-ului elevului ales
        int idBursaSelectata = 100;  // variabila de memorare a id-ului bursei alese

        try {
            // preluare id elev ales (copiere pana la intalnirea primului spatiu)
            idElevAles = alegeElevComboBox.getSelectedItem().toString();
            if (idElevAles.contains(" ")) {
                idElevAles = idElevAles.substring(0, idElevAles.indexOf(" "));
            }

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
                        "<html><b>Preluare ID Bursă eșuată!</b><br/>"
                        + "Probabil s-a întrerupt conexiunea.</html>");
            }

            // setare bursa selectata, elevului selectat
            try {
                Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
                Database.stmt.executeUpdate("update Elevi set idBursa = " + idBursaSelectata + " where id = " + idElevAles);

                this.dispose(); // inlaturare fereastra dupa adaugare

            } catch (SQLException ex) {
                Mesaje.mesajDialogReconectare(this, "Eroare!",
                        "<html><b>Adăugare eșuată!</b><br/>"
                        + "Probabil s-a întrerupt conexiunea.</html>");
            }

        } catch (Exception e) {
            Mesaje.mesajAtentionare(this, null, "Nu există nici un elev de adăugat!", "warning");
        }

        // auto-selectare tip bursa din BursieriFrame la tipul de bursa ales la adaugare
        BursieriFrame.pointerTipBursaComboBox.setSelectedItem(tipBursaComboBox.getSelectedItem().toString());
    }//GEN-LAST:event_adaugaButtonActionPerformed

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
    private javax.swing.JButton adaugaButton;
    private javax.swing.JComboBox<String> alegeElevComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox<String> tipBursaComboBox;
    // End of variables declaration//GEN-END:variables
}
