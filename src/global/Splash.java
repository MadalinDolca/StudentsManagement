package global;

import autentificare.AutentificareFrame;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * Clasa principală din pachetul <code>global</code> care creaza si conține
 * toate elementele grafice ale ferestrei de incarcare, cât și instructiunile
 * necesare pentru conectarea la bazele de date, stabilirea restrictiilor si
 * declansarea setarilor.
 * 
 * @author Madalin
 */
public class Splash extends javax.swing.JFrame {

    /**
     * Variabila de manipulare fereastra splash.
     */
    public static Splash splashFrame;
    
    /**
     * Variabila de manipulare fereastra autentificare.
     */
    public static AutentificareFrame autentificareFrame;
    
    /**
     * Variabila de memorare identificator unic universal.
     */
    public static String uuid;
    
    /**
     * Variabila de memorare identificator unic universal interzis.
     */
    String bannedUUID;

    /**
     * Inițializează componentele ferestrei de incarcare, stabileste conexiunea
     * cu bazele de date, declanseaza setarile globale si aplica restrictiile
     * de utilizare.
     */
    public Splash() {
        setUndecorated(true); // eliminare bara 
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(Setari.icon)).getImage()); // iconita ferestrei
        setBackground(new Color(0, 0, 0, 0)); // fundal transparent

        this.setVisible(true); // afisare splash screen

        Database.conectare(this); // conectare la baza de date
        Setari.setariGlobale(); // declansare setari globale

        // obtinere identificator unic universal
        try {
            String OS = System.getProperty("os.name").toLowerCase();

            // daca e Windows OS
            if (OS.indexOf("win") >= 0) {
                String command = "wmic csproduct get UUID";
                StringBuffer output = new StringBuffer();
                Process SerNumProcess = Runtime.getRuntime().exec(command);
                BufferedReader sNumReader = new BufferedReader(new InputStreamReader(SerNumProcess.getInputStream()));
                String line = "";

                while ((line = sNumReader.readLine()) != null) {
                    output.append(line + "\n");
                }

                uuid = output.toString().substring(output.indexOf("\n"), output.length()).trim();

            } // daca e macOS 
            else if (OS.indexOf("mac") >= 0) {
                String command = "system_profiler SPHardwareDataType | awk '/UUID/ { print $3; }'";
                StringBuffer output = new StringBuffer();
                Process SerNumProcess = Runtime.getRuntime().exec(command);
                BufferedReader sNumReader = new BufferedReader(new InputStreamReader(SerNumProcess.getInputStream()));
                String line = "";

                while ((line = sNumReader.readLine()) != null) {
                    output.append(line + "\n");
                }

                uuid = output.toString().substring(output.indexOf("UUID: "), output.length()).replace("UUID: ", "");
                SerNumProcess.waitFor();
                sNumReader.close();

            } // daca e UNIX
            else if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0) {
                StringBuffer output = new StringBuffer();
                Process process;
                String[] cmd = {"/bin/sh", "-c", "echo <password for superuser> | sudo -S cat /sys/class/dmi/id/product_uuid"};

                process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = "";

                while ((line = reader.readLine()) != null) {
                    output.append(line + "\n");
                }

                uuid = output.toString();
            } else if (OS.indexOf("sunos") >= 0) {
                // Solaris OS
            }

        } catch (Exception ex) {
            System.out.println("Eșuare obtinere ID");
        }

        // verificare daca UUID-ul utilizatorului este interzis
        try {
            Database.adminStmt = Database.adminConnection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
            Database.adminRs = Database.adminStmt.executeQuery(
                    "select uuid from BannedSystems where uuid = '" + uuid + "';");

            while (Database.adminRs.next()) {
                bannedUUID = Database.adminRs.getString("uuid");
            }

            if (uuid.equals(bannedUUID)) {
                // mesajul dialogului                
                JLabel continutDialog = new JLabel("<html>" + Setari.mesajRestrictionare + "</html>");
                continutDialog.setFont(new Font("Arial", Font.PLAIN, 24));
                String[] optiuni = {"Am înțeles"}; // optiunile dialogului
                UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 18))); // dimensiune font butoane

                int optiuneAleasa = JOptionPane.showOptionDialog(this, continutDialog, "Acces Respins!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        new ImageIcon(Splash.class.getResource("/imagini/block.png")),
                        optiuni, optiuni[0]);

                // inchidere program daca s-a apasat "Am inteles"
                if (optiuneAleasa == 0) {
                    System.exit(0);
                } // inchidere program daca s-a apasat "Inchidere Fereastra" 
                else if (optiuneAleasa == JOptionPane.CLOSED_OPTION) {
                    System.exit(0);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Eșuare căutare ID");
        }

        // verificare daca versiunea programului este egala cu cea mai noua versiune
        if (!Setari.numarVersiune.equals(Setari.numarVersiuneCurenta)) {
            JLabel continutDialog = new JLabel("<html><b>Nu folosești versiunea curentă a aplicației!</b><br/>"
                    + "Te rog să faci update la cea mai nouă versiune<br/>"
                    + "pentru a putea folosi aplicația în continuare.</html>");
            continutDialog.setFont(new Font("Arial", Font.PLAIN, 24));
            String[] optiuni = {"Am înțeles"}; // optiunile dialogului
            UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 18))); // dimensiune font butoane

            int optiuneAleasa = JOptionPane.showOptionDialog(this, continutDialog, "Acces Respins!",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon(Splash.class.getResource("/imagini/update.png")),
                    optiuni, optiuni[0]);

            // inchidere program daca s-a apasat "Am inteles"
            if (optiuneAleasa == 0) {
                System.exit(0);
            } // inchidere program daca s-a apasat "Inchidere Fereastra" 
            else if (optiuneAleasa == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            }
        }

        this.dispose();  // inlaturare splash screen
        autentificareFrame = new autentificare.AutentificareFrame();
        autentificareFrame.setVisible(true); // afisare fereastra de autentificare
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splashLogoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        splashLogoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/splash screen icon.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splashLogoLabel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splashLogoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        // Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                splashFrame = new Splash(); // .setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel splashLogoLabel;
    // End of variables declaration//GEN-END:variables
}
