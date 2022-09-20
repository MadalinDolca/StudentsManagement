package date_elevi;

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
 * unui elev, cat si metodele necesare pentru efectuarea acestei operatiuni.
 * 
 * @author Madalin
 */
public class AdaugaDialog extends javax.swing.JDialog {

    /**
     * Inițializează componentele ferestrei, iconița ferestrei si pozitia 
     * modalului in concordanta cu cea a ferestrei pentru date elevi.
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

        // dezactivez editarea de la tastatura a textField-ului de la data nasterii
        JTextFieldDateEditor dataNasteriiTextField = (JTextFieldDateEditor) dataNasteriiDateChooser.getDateEditor();
        dataNasteriiTextField.setEditable(false);

        // setari buton calendar
        dataNasteriiDateChooser.getCalendarButton().setPreferredSize(new Dimension(200, this.getHeight()));
        dataNasteriiDateChooser.getCalendarButton().setText("Alege Data");
        dataNasteriiDateChooser.getCalendarButton().setFont(new Font("Dialog", Font.PLAIN, 24));

        // setari dimensine month
        dataNasteriiDateChooser.getJCalendar().setPreferredSize(new Dimension(424, 300));
        dataNasteriiDateChooser.getJCalendar().getComponent(0).setPreferredSize(new Dimension(80, 40));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adaugaDialogButton = new javax.swing.JButton();
        cnpTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        numeTextField = new javax.swing.JTextField();
        initialaTataTextField = new javax.swing.JTextField();
        Ini = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        prenumeTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        adresaTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        numarTelefonTextField = new javax.swing.JTextField();
        sexComboBox = new javax.swing.JComboBox<>();
        dataNasteriiDateChooser = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adaugare Elev");
        setPreferredSize(new java.awt.Dimension(670, 495));
        setResizable(false);

        adaugaDialogButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        adaugaDialogButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/plus.png"))); // NOI18N
        adaugaDialogButton.setText("Adaugă");
        adaugaDialogButton.setIconTextGap(10);
        adaugaDialogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adaugaDialogButtonActionPerformed(evt);
            }
        });

        cnpTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("CNP");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Nume");

        numeTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        initialaTataTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        Ini.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        Ini.setText("Inițială Tată");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setText("Prenume");

        prenumeTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setText("Sex");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setText("Data Nașterii");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel7.setText("Adresă");

        adresaTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel8.setText("Număr Telefon");

        numarTelefonTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        sexComboBox.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        sexComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculin", "Feminin" }));

        dataNasteriiDateChooser.setToolTipText("");
        dataNasteriiDateChooser.setDateFormatString("yyyy-MM-dd");
        dataNasteriiDateChooser.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(adaugaDialogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Ini)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(adresaTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prenumeTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(initialaTataTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numeTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cnpTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numarTelefonTextField)
                            .addComponent(sexComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dataNasteriiDateChooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cnpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(initialaTataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ini))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prenumeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sexComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(dataNasteriiDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adresaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numarTelefonTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(adaugaDialogButton)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Adauga noul elev alaturi de datele sale in baza de date.
     * 
     * <p>Arata <code>mesajAtentionare</code> daca s-a lasat un camp gol.</p>
     * <p>Verifica daca CNP-ul este un numar pozitiv, daca dimensiunea acestuia
     * este corecta si daca acesta apatine altui elev.</p>
     * <p>Verifica daca numarul de telefon este format din cifre.</p>
     * <p>Arata <code>mesajDialogReconectare()</code> daca adaugarea a esuat.<p>
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * de adaugare
     * 
     * @see global.Mesaje#mesajAtentionare(java.lang.Object, java.lang.String, java.lang.String, java.lang.String) 
     * @see global.Mesaje#mesajDialogReconectare(javax.swing.JDialog, java.lang.String, java.lang.String) 
     */
    private void adaugaDialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaDialogButtonActionPerformed
        boolean eroareCNP = false, eroareNumarTelefon = false;
        boolean potAdauga = true;

        // verific completarea si corectitudinea campurilor
        if (cnpTextField.getText().equals("")
                || numeTextField.getText().equals("")
                || initialaTataTextField.getText().equals("")
                || prenumeTextField.getText().equals("")
                || ((JTextField) dataNasteriiDateChooser.getDateEditor().getUiComponent()).getText().equals("")
                || adresaTextField.getText().equals("")
                || numarTelefonTextField.getText().equals("")) {
            Mesaje.mesajAtentionare(this, "Atenție!", "Ai lăsat un câmp gol!", "warning");
            potAdauga = false;

        } // daca campurile sunt completate, verificam corectitudinea lor
        else {
            try {
                Long.parseLong(cnpTextField.getText()); // verific daca cnp-ul este un numar
            } catch (NumberFormatException ex) {
                eroareCNP = true;
            }

            // daca cnp-ul este un numar, verific daca este pozitiv
            if (eroareCNP == true) {
                Mesaje.mesajAtentionare(this, "Atenție!",
                        "<html><b>CNP</b>-ul trebuie să fie un număr pozitiv!</html>", "warning");
                potAdauga = false;

            } // daca cnp-ul este numar pozitiv, verific dimensiunea acestuia
            else if (!cnpTextField.getText().equals("") && cnpTextField.getText().length() != 13) {
                Mesaje.mesajAtentionare(this, "Atenție!",
                        "<html>Dimensiunea <b>CNP</b>-ului este incorectă!</html>", "warning");
                potAdauga = false;

            } // verificare daca cnp-ul pozitiv de 13 caractere este deja atribuit altui elev 
            else {
                try {
                    Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
                    Database.rs = Database.stmt.executeQuery(
                            "select cnp from Elevi where cnp = '"
                            + cnpTextField.getText() + "';");
                    Database.rs.next();

                    String existentaCNP = Database.rs.getString("cnp");

                    if (cnpTextField.getText().equals(existentaCNP)) {
                        Mesaje.mesajAtentionare(this, "Atenție!",
                                "<html><b>CNP</b>-ul este deja atribuit unui elev!</html>", "warning");
                        potAdauga = false;
                    }

                } catch (SQLException ex) {
                    // raporteaza faptul ca nu putem opera o selectie nula
                    // Logger.getLogger(AdaugaDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                Long.parseLong(numarTelefonTextField.getText()); // verific daca e numar
            } catch (NumberFormatException ex) {
                eroareNumarTelefon = true;
            }

            if (eroareNumarTelefon == true) {
                Mesaje.mesajAtentionare(this, "Atenție!",
                        "<html><b>Numărul de telefon</b> trebuie să conțină cifre!</html>", "warning");
                potAdauga = false;
            }
        }

        // adaugarea in baza de date
        if (potAdauga == true) {
            try {
                Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
                Database.stmt.executeUpdate(
                        "insert into Elevi(cnp, nume, initialaTata, prenume, sex, dataNasterii, adresa, nrTelefon) VALUES ("
                        + "'" + cnpTextField.getText() + "', "
                        + "'" + numeTextField.getText() + "', "
                        + "'" + initialaTataTextField.getText() + "', "
                        + "'" + prenumeTextField.getText() + "', "
                        + "'" + sexComboBox.getSelectedItem().toString() + "', "
                        + "'" + ((JTextField) dataNasteriiDateChooser.getDateEditor().getUiComponent()).getText() + "', "
                        + "'" + adresaTextField.getText() + "', "
                        + "'" + numarTelefonTextField.getText() + "');"
                );

                this.dispose(); // inlaturare fereastra dupa adaugarea datelor

            } catch (SQLException ex) {
                Mesaje.mesajDialogReconectare(this, "Eroare Adăugare!",
                        "<html><b>Adăugare eșuată!</b><br/>"
                        + "Probabil s-a întrerupt conexiunea.</html>");
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
    private javax.swing.JLabel Ini;
    private javax.swing.JButton adaugaDialogButton;
    private javax.swing.JTextField adresaTextField;
    private javax.swing.JTextField cnpTextField;
    private com.toedter.calendar.JDateChooser dataNasteriiDateChooser;
    private javax.swing.JTextField initialaTataTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField numarTelefonTextField;
    private javax.swing.JTextField numeTextField;
    private javax.swing.JTextField prenumeTextField;
    private javax.swing.JComboBox<String> sexComboBox;
    // End of variables declaration//GEN-END:variables
}
