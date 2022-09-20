package catalog;

import com.toedter.calendar.JTextFieldDateEditor;
import global.Database;
import global.Mesaje;
import global.Setari;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

/**
 * Creaza si contine toate elementele grafice ale ferestrei modal de adaugare a 
 * notei, cat si metodele necesare pentru efectuarea acestei operatiuni.
 * 
 * @author Madalin
 */
public class AdaugaNotaDialog extends javax.swing.JDialog {

    /**
     * Variabila de memorare a ID-ului materiei curente.
     */
    String idMaterieCurenta;

    /**
     * Inițializează componentele nemodificabile ale ferestrei, iconița 
     * ferestrei si pozitia modalului in concordanta cu cea a ferestrei pentru 
     * catalog. Autocompleteaza datele dialogului cu datele selectate in 
     * fereastra parinte.
     *
     * @param parent proprietarul din care este afișat dialogul
     * @param modal specifică dacă dialogul blochează introducerea utilizatorului 
     * în alte ferestre de nivel superior atunci când este afișat
     */
    public AdaugaNotaDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(Setari.icon)).getImage()); // iconita ferestrei
        this.setLocationRelativeTo(parent); // pozitia modalului corespunde cu cea a ferestrei

        // completare campuri
        clasaTextField.setText(CatalogFrame.numeClasaSelectata);
        elevTextField.setText(CatalogFrame.numeElevSelectat);
        materiaTextField.setText(CatalogFrame.numeMaterieSelectata);

        // dezactivez editarea de la tastatura a textField-ului de la data nasterii
        JTextFieldDateEditor dataTextField = (JTextFieldDateEditor) dataDateChooser.getDateEditor();
        dataTextField.setEditable(false);

        // setari buton calendar
        dataDateChooser.getCalendarButton().setPreferredSize(new Dimension(200, this.getHeight()));
        dataDateChooser.getCalendarButton().setText("Alege Data");
        dataDateChooser.getCalendarButton().setFont(new Font("Dialog", Font.PLAIN, 24));

        // setari dimensine month
        dataDateChooser.getJCalendar().setPreferredSize(new Dimension(424, 300));
        dataDateChooser.getJCalendar().getComponent(0).setPreferredSize(new Dimension(80, 40));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adaugaDialogButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        clasaTextField = new javax.swing.JTextField();
        elevTextField = new javax.swing.JTextField();
        materiaTextField = new javax.swing.JTextField();
        dataDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        notaTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tipNotaComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adaugă Notă");
        setResizable(false);

        adaugaDialogButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        adaugaDialogButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/plus.png"))); // NOI18N
        adaugaDialogButton.setText("Adaugă Nota");
        adaugaDialogButton.setIconTextGap(10);
        adaugaDialogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adaugaDialogButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Clasa");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Nota");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Elev");

        clasaTextField.setEditable(false);
        clasaTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        clasaTextField.setText("Clasa");

        elevTextField.setEditable(false);
        elevTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        elevTextField.setText("Elev");

        materiaTextField.setEditable(false);
        materiaTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        materiaTextField.setText("Materia");

        dataDateChooser.setToolTipText("");
        dataDateChooser.setDateFormatString("yyyy-MM-dd");
        dataDateChooser.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setText("Materia");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setText("Data");

        notaTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setText("Tip Notă");

        tipNotaComboBox.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        tipNotaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normală", "Teză" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tipNotaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(notaTextField)
                            .addComponent(materiaTextField)
                            .addComponent(elevTextField)
                            .addComponent(clasaTextField)))
                    .addComponent(adaugaDialogButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clasaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(elevTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(materiaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(notaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipNotaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dataDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(adaugaDialogButton)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Adauga nota la materia elevului selectat in baza de date.
     * 
     * <p>Verifica daca nota este un numar, daca respecta formatul si daca este
     * un numar pozitiv.</p>
     * <p>Verifica daca elevul are deja o nota de teza la materia respectiva.</p>
     * <p>Arata un mesaj de atentionare daca nu s-a ales nota sau data.</p>
     * <p>Arata <code>mesajDialogReconectare()</code> daca preluarea ID-ului
     * materiei a esuat sau daca adaugarea a esuat.<p>
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * de adaugare
     * 
     * @see global.Mesaje#mesajDialogReconectare(javax.swing.JDialog, java.lang.String, java.lang.String) 
     */
    private void adaugaDialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaDialogButtonActionPerformed
        boolean eroareNota = false;
        boolean potAdauga = true;

        // verific completarea si corectitudinea campurilor
        if (notaTextField.getText().equals("")
                || ((JTextField) dataDateChooser.getDateEditor().getUiComponent()).getText().equals("")) {
            Mesaje.mesajAtentionare(this, "Atenție!", "Ai lăsat un câmp gol!", "warning");
            potAdauga = false;

        } // daca campurile sunt completate, verificam corectitudinea lor
        else {
            try {
                Float.parseFloat(notaTextField.getText()); // verific daca nota este un numar
            } catch (NumberFormatException ex) {
                eroareNota = true;
            }

            // daca nota este un numar, verific daca este pozitiv
            if (eroareNota == true) {
                Mesaje.mesajAtentionare(this, "Atenție!",
                        "<html><b>Nota</b> trebuie să fie un număr pozitiv!<br/>"
                        + "Exemplu format: <b>9.90</b><br/></html>", "warning");
                potAdauga = false;

            } // daca nota este numar pozitiv, verific dimensiunea acesteia
            else if (!notaTextField.getText().equals("")
                    && Float.parseFloat(notaTextField.getText()) < 1.0
                    || !notaTextField.getText().equals("")
                    && Float.parseFloat(notaTextField.getText()) > 10.0) {
                
                Mesaje.mesajAtentionare(this, "Atenție!",
                        "<html>Dimensiunea <b>Notei</b> este incorectă!</html>", "warning");
                potAdauga = false;
            }

            // preluare id materie curenta
            try {
                Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
                Database.rs = Database.stmt.executeQuery(
                        "select id from Materii where nume = '"
                        + CatalogFrame.numeMaterieSelectata + "';");

                while (Database.rs.next()) { // parcurgem fiecare linie din tabelul bazei de date
                    // adaugare continut celula din tabelul bazei de date in string
                    idMaterieCurenta = Database.rs.getString("id");
                }

            } catch (SQLException ex) {
                Mesaje.mesajDialogReconectare(this, "Eroare!",
                        "<html><b>Eroare preluare ID materii!</b><br/>"
                        + "Probabil s-a întrerupt conexiunea!</html>");
            }

            // verificare daca este o teza deja atribuit elevului la materia respectiva
            try {
                Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
                Database.rs = Database.stmt.executeQuery(
                        "select Note.tipNota from Note "
                        + "inner join Materii on Note.idMaterie = Materii.id "
                        + "inner join Elevi on Note.idElev = Elevi.id "
                        + "where Elevi.id = '" + CatalogFrame.idElevSelectat + "' "
                        + "and Materii.id = '" + idMaterieCurenta + "' "
                        + "and Note.tipNota = 'Teză';");
                Database.rs.next();

                String existentaTeza = Database.rs.getString("tipNota");

                if (tipNotaComboBox.getSelectedItem().toString().equals(existentaTeza)) {
                    Mesaje.mesajAtentionare(this, "Atenție!",
                            "<html>Acest elev are deja o notă de teză<br/>la această materie!</html>", "warning");
                    potAdauga = false;
                }

            } catch (SQLException ex) {
                System.out.println("Eroare SQL");
                // raporteaza faptul ca nu putem opera o selectie nula
                // Logger.getLogger(AdaugaDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // adaugarea in baza de date
        if (potAdauga == true) {
            try {
                Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
                Database.stmt.executeUpdate(
                        "insert into Note(idElev, idMaterie, nota, data, tipNota) values ("
                        + "'" + CatalogFrame.idElevSelectat + "', "
                        + "'" + idMaterieCurenta + "', "
                        + Float.parseFloat(notaTextField.getText()) + ", "
                        + "'" + ((JTextField) dataDateChooser.getDateEditor().getUiComponent()).getText() + "', "
                        + "'" + tipNotaComboBox.getSelectedItem().toString() + "');"
                );

                this.dispose(); // inlaturare fereastra dupa adaugarea datelor

            } catch (SQLException ex) {
                Mesaje.mesajAtentionare(this, "Atenție!",
                        "<html><b>Adăugare eșuată!</b><br/>"
                        + "Probabil s-a întrerupt conexiunea.</html>", "warning");
            }
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
            java.util.logging.Logger.getLogger(AdaugaNotaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdaugaNotaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdaugaNotaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdaugaNotaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adaugaDialogButton;
    private javax.swing.JTextField clasaTextField;
    private com.toedter.calendar.JDateChooser dataDateChooser;
    private javax.swing.JTextField elevTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField materiaTextField;
    private javax.swing.JTextField notaTextField;
    private javax.swing.JComboBox<String> tipNotaComboBox;
    // End of variables declaration//GEN-END:variables
}
