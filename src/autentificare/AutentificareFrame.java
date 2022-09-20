package autentificare;

import global.Database;
import global.Mesaje;
import global.Setari;
import global.Splash;
import interfata.InterfataFrame;
import java.sql.SQLException;
import javax.swing.ImageIcon;

/**
 * Clasa principală din pachetul <code>autentificare</code> care creaza si 
 * conține toate elementele grafice ale ferestrei de autentificare, cât și 
 * metodele necesare pentru acordarea accesului la baza de date.
 * 
 * @author Madalin
 */
public class AutentificareFrame extends javax.swing.JFrame {

    /**
     * Variabila de manipulare a ferestrei interfeței cu meniuri.
     *
     * @see interfata.InterfataFrame
     */
    public static InterfataFrame interfataFrame;

    /**
     * Variabila care memorează numele de utilizator din baza de date.
     */
    public static String username;

    /**
     * Variabila care memorează parola contului din baza de date.
     */
    public static String password;

    /**
     * Variabila care memorează tipul de utilizator al contului din baza de
     * date.
     */
    public static String usertype;

    /**
     * Variabila care memorează numele de utilizator introdus în fereastra de
     * autentificare.
     */
    String nume;

    /**
     * Variabila care memorează parola introdusă în fereastra de autentificare.
     */
    String parola;

    /**
     * Variabila de memorare a utilizatorului restricționat.
     */
    static String bannedUser;

    /**
     * Inițializează componentele ferestrei, iconița ferestrei, label-ul credit
     * și label-ul care conține numărul versiunii.
     */
    public AutentificareFrame() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(Setari.icon)).getImage()); // iconita ferestrei
        creditLabel.setText(Setari.credit); // labelul cu credit
        versiuneLabel.setText(Setari.numarVersiune); // labelul cu numarul versiunii
    }

    /**
     * Caută în baza de date contul care deține numele și parola specificate în
     * text field-uri. 
     * 
     * <p>Dacă acest cont există, se verifică dacă este cumva restricționat si
     * va afisa un <code>mesajAtentionare</code>, altfel se deschide fereastra
     * interfeței principale <code>InterfataFrame</code>.</p>
     * <p>Daca acest cont nu exista, se afiseaza un mesaj de atentionare.</p>
     * <p>Afiseaza <code>mesajAutentificareReconectare</code> daca apar 
     * probleme de conectare la baza de date.</p>
     *
     * @param nume memorează numele introdus în text field
     * @param parola memorează parola introdusă în text field
     * 
     * @see global.Mesaje#mesajAtentionare(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
     * @see global.Mesaje#mesajAutentificareReconectare(javax.swing.JFrame, java.lang.String, java.lang.String)
     */
    public static void autentificare(String nume, String parola) {
        try {
            Database.adminStmt = Database.adminConnection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
            Database.adminRs = Database.adminStmt.executeQuery(
                    "select username, password, usertype from Conturi where username = '" + nume
                    + "' and password = '" + parola + "';");

            while (Database.adminRs.next()) {
                username = Database.adminRs.getString("username");
                password = Database.adminRs.getString("password");
                usertype = Database.adminRs.getString("usertype");
            }

            // daca datele introduse corespund cu cele din baza de date
            if (nume.equals(username) && parola.equals(password)) {
                // verificare daca utilizatorul este interzis
                Database.adminStmt = Database.adminConnection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
                Database.adminRs = Database.adminStmt.executeQuery(
                        "select numeCont from BannedAccounts where numeCont = '" + nume + "';");

                while (Database.adminRs.next()) {
                    bannedUser = Database.adminRs.getString("numeCont");
                }

                // daca utilizatorul este restrictionat
                if (nume.equals(bannedUser)) {
                    Mesaje.mesajAtentionare(Splash.autentificareFrame, "Acces Respins!",
                            "Acest utilizator este restricționat!", "block");

                } // daca utilizatorul are acces
                else {
                    interfataFrame = new InterfataFrame();
                    interfataFrame.setVisible(true); // afisare fereastra interfata principala
                    Splash.autentificareFrame.dispose(); // inlaturara fereastra de autentificare

                    Database.adminStmt = Database.adminConnection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
                    Database.adminStmt.executeUpdate(
                            "insert into Autentificari(numeCont, numePC, uuid) values ('"
                            + username + "', '" + System.getProperty("user.name") + "', '"
                            + Splash.uuid + "');");
                }

            } // daca datele introduse sunt incorecte
            else {
                Mesaje.mesajAtentionare(Splash.autentificareFrame, "Atenție!",
                        "<html><b>Nume de utilizator</b> sau<br/><b>Parolă</b> incorectă!</html>",
                        "warning");
            }

        } catch (SQLException ex) { // in cazul in care se intampina erori, afisam un mesaj de eroare si reconectare
            Mesaje.mesajAutentificareReconectare(Splash.autentificareFrame, null, "Eroare Bază de Date!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        numeAutentificareTextField = new javax.swing.JTextField();
        parolaAutentificarePasswordField = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        autentificareButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        creditLabel = new javax.swing.JLabel();
        versiuneLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Autentificare");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/hacker.png"))); // NOI18N
        jLabel1.setText("Nume");
        jLabel1.setIconTextGap(10);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/password.png"))); // NOI18N
        jLabel2.setText("Parolă");
        jLabel2.setIconTextGap(10);

        numeAutentificareTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        parolaAutentificarePasswordField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel3.setText("AUTENTIFICARE");

        autentificareButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        autentificareButton.setText("Autentificare");
        autentificareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autentificareButtonActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(37, 56, 71));
        jPanel1.setLayout(null);

        creditLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        creditLabel.setForeground(new java.awt.Color(255, 255, 255));
        creditLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        creditLabel.setText("Credit");
        creditLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(creditLabel);
        creditLabel.setBounds(0, 10, 430, 24);

        versiuneLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        versiuneLabel.setForeground(new java.awt.Color(255, 255, 255));
        versiuneLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        versiuneLabel.setText("Versiune");
        jPanel1.add(versiuneLabel);
        versiuneLabel.setBounds(0, 40, 430, 19);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(numeAutentificareTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(parolaAutentificarePasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(autentificareButton, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numeAutentificareTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(parolaAutentificarePasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(autentificareButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Va prelua datele introduse în text field-urile <code>numeAutentificareTextField</code>
     * si <code>parolaAutentificarePasswordField</code> la apăsarea butonului de
     * autentificare si le va memora in variabilele <code>nume</code> si 
     * <code>parola</code>, dupa care va apela metoda 
     * <code>autentificare(nume, parola)</code>.
     * 
     * <p>Se afiseaza un mesaj de atentionare în cazul in care campurile nu
     * sunt completate.</p>
     *
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * de autentificare
     */
    private void autentificareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autentificareButtonActionPerformed
        nume = numeAutentificareTextField.getText(); // preluam datele din text field-uri
        parola = parolaAutentificarePasswordField.getText();

        // verificare daca s-au completat campurile
        if (numeAutentificareTextField.getText().equals("")
                || parolaAutentificarePasswordField.getText().equals("")) {
            Mesaje.mesajAtentionare(this, "Atenție!", "Ai lăsat un câmp gol!", "warning");

        } else { // autentificare la baza de date
            autentificare(nume.toLowerCase(), parola);
        }
    }//GEN-LAST:event_autentificareButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AutentificareFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AutentificareFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AutentificareFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AutentificareFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton autentificareButton;
    private javax.swing.JLabel creditLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField numeAutentificareTextField;
    private javax.swing.JPasswordField parolaAutentificarePasswordField;
    private javax.swing.JLabel versiuneLabel;
    // End of variables declaration//GEN-END:variables
}
