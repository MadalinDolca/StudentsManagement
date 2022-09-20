package interfata;

import autentificare.AutentificareFrame;
import bursieri.BursieriFrame;
import catalog.CatalogFrame;
import clase_elevi.ClaseEleviFrame;
import date_elevi.DateEleviFrame;
import global.Setari;
import global.Splash;
import javax.swing.ImageIcon;
import muzica.Muzica;

/**
 * Clasa principală din pachetul <code>interfata</code> care creaza si conține 
 * toate elementele grafice ale ferestrei principale cu meniuri, cât și 
 * metodele necesare pentru accesarea acestor optiuni.
 * 
 * @author Madalin
 */
public class InterfataFrame extends javax.swing.JFrame {

    /**
     * Variabila de manipulare fereastra date elevi.
     */
    public static DateEleviFrame dateEleviFrame;
    
    /**
     * Variabila de manipulare fereastra catalog.
     */
    public static CatalogFrame catalogFrame;
    
    /**
     * Variabila de manipulare fereastra clase elevi.
     */
    public static ClaseEleviFrame claseEleviFrame;
    
    /**
     * Variabila de manipulare fereastra bursieri.
     */
    public static BursieriFrame bursieriFrame;
    
    /**
     * Variabila de manipulare muzica.
     */
    Muzica mu;

    /**
     * Inițializează componentele ferestrei si iconița ferestrei. Afiseaza
     * numele utilizatorului si gradul acestuia. Dezactiveaza butonul
     * <code>dateEleviButton</code> pentru accesul de profesor.
     */
    public InterfataFrame() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(Setari.icon)).getImage()); // iconita ferestrei

        versiuneLabel.setText(global.Setari.numarVersiune);
        numeUtilizatorLabel.setText(AutentificareFrame.username);
        gradUtilizatorLabel.setText(AutentificareFrame.usertype);

        // dezactivare butoane pentru acces de profesor
        if (AutentificareFrame.usertype.equals("profesor")) {
            dateEleviButton.setEnabled(false);
        }

        // pornire muzica
        mu = new muzica.Muzica();
        mu.playMusic();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateEleviButton = new javax.swing.JButton();
        deconectareButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        catalogButton = new javax.swing.JButton();
        claseEleviButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        gradUtilizatorLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        numeUtilizatorLabel = new javax.swing.JLabel();
        versiuneLabel = new javax.swing.JLabel();
        bursieriButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestionare Elevi");
        setResizable(false);

        dateEleviButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        dateEleviButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/student.png"))); // NOI18N
        dateEleviButton.setText("Date Elevi");
        dateEleviButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dateEleviButton.setIconTextGap(15);
        dateEleviButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateEleviButtonActionPerformed(evt);
            }
        });

        deconectareButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        deconectareButton.setText("Deconectare");
        deconectareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deconectareButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setText("Gestionare Elevi");

        catalogButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        catalogButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/book.png"))); // NOI18N
        catalogButton.setText("Catalog");
        catalogButton.setIconTextGap(15);
        catalogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catalogButtonActionPerformed(evt);
            }
        });

        claseEleviButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        claseEleviButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/books.png"))); // NOI18N
        claseEleviButton.setText("Clase Elevi");
        claseEleviButton.setIconTextGap(15);
        claseEleviButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claseEleviButtonActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(37, 56, 71));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Grad:");

        gradUtilizatorLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        gradUtilizatorLabel.setForeground(new java.awt.Color(255, 255, 255));
        gradUtilizatorLabel.setText("N \\ N");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Conectat ca:");

        numeUtilizatorLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        numeUtilizatorLabel.setForeground(new java.awt.Color(255, 255, 255));
        numeUtilizatorLabel.setText("N \\ N");

        versiuneLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        versiuneLabel.setForeground(new java.awt.Color(255, 255, 255));
        versiuneLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        versiuneLabel.setText("Versiune");
        versiuneLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                versiuneLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numeUtilizatorLabel)
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gradUtilizatorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(versiuneLabel)
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(numeUtilizatorLabel)
                    .addComponent(jLabel3)
                    .addComponent(gradUtilizatorLabel)
                    .addComponent(versiuneLabel))
                .addGap(14, 14, 14))
        );

        bursieriButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        bursieriButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/trophy.png"))); // NOI18N
        bursieriButton.setText("Bursieri");
        bursieriButton.setIconTextGap(15);
        bursieriButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bursieriButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(claseEleviButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateEleviButton, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(catalogButton, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addComponent(bursieriButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(63, 63, 63))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(deconectareButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateEleviButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(catalogButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(claseEleviButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bursieriButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(deconectareButton)
                .addGap(32, 32, 32)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Deconecteaza utilizatorul, inchide fereastra curenta si afiseasza
     * fereastra de autentificare.
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * de deconectare
     */
    private void deconectareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deconectareButtonActionPerformed
        // Database.deconectare(); // deconectare de la baza de date
        mu.stopMusic(); // oprire muzica

        Splash.autentificareFrame.setVisible(true); // afisare fereastra de autentificare
        AutentificareFrame.interfataFrame.dispose(); // inlaturare fereastra interfeta principala
        AutentificareFrame.interfataFrame = null; // dezinstantiere interfeta principala
    }//GEN-LAST:event_deconectareButtonActionPerformed

    /**
     * Afiseaza fereastra <code>DateEleviFrame</code> si dezactiveaza si 
     * redenumeste butonul <code>dateEleviButton</code>.
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * <code>dateEleviButton</code>
     * 
     * @see date_elevi.DateEleviFrame
     */
    private void dateEleviButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateEleviButtonActionPerformed
        dateEleviFrame = new DateEleviFrame();
        dateEleviFrame.setVisible(true); // afisare fereastra date elevi

        dateEleviButton.setEnabled(false); // dezactivare buton dupa apasare
        dateEleviButton.setText("Date Elevi ✔"); // redenumire buton dupa apasare (bifa)
    }//GEN-LAST:event_dateEleviButtonActionPerformed

    /**
     * Reactiveaza si redenumeste butonul <code>dateEleviButton</code>.
     */
    public void dateEleviButtonActivare() {
        dateEleviButton.setEnabled(true); // reactivare buton dupa inchidere ferestra date elevi
        dateEleviButton.setText("Date Elevi"); // redenumire buton dupa apasare
    }

    /**
     * Afiseaza fereastra <code>CatalogFrame</code> si dezactiveaza si 
     * redenumeste butonul <code>catalogButton</code>.
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * <code>catalogButton</code>
     * 
     * @see catalog.CatalogFrame
     */
    private void catalogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catalogButtonActionPerformed
        catalogFrame = new CatalogFrame();
        catalogFrame.setVisible(true); // afisare fereastra catalog

        catalogButton.setEnabled(false); // dezactivare buton dupa apasare
        catalogButton.setText("Catalog ✔"); // redenumire buton dupa apasare (bifa)
    }//GEN-LAST:event_catalogButtonActionPerformed

    /**
     * Reactiveaza si redenumeste butonul <code>catalogButton</code>.
     */
    public void catalogButtonActivare() {
        catalogButton.setEnabled(true); // reactivare buton dupa inchidere ferestra catalog
        catalogButton.setText("Catalog"); // redenumire buton dupa apasare
    }
    
    /**
     * Opreste muzica la apasarea numarului versiunii.
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a numarului
     * versiunii
     */
    private void versiuneLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_versiuneLabelMouseClicked
        mu.stopMusic(); // oprire muzica la click
    }//GEN-LAST:event_versiuneLabelMouseClicked

    /**
     * Afiseaza fereastra <code>ClaseEleviFrame</code> si dezactiveaza si 
     * redenumeste butonul <code>claseEleviButton</code>.
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * <code>claseEleviButton</code>
     * 
     * @see clase_elevi.ClaseEleviFrame
     */    
    private void claseEleviButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claseEleviButtonActionPerformed
        claseEleviFrame = new ClaseEleviFrame();
        claseEleviFrame.setVisible(true); // afisare fereastra catalog

        claseEleviButton.setEnabled(false); // dezactivare buton dupa apasare
        claseEleviButton.setText("Clase Elevi ✔"); // redenumire buton dupa apasare (bifa)
    }//GEN-LAST:event_claseEleviButtonActionPerformed
    
    /**
     * Reactiveaza si redenumeste butonul <code>claseEleviButton</code>.
     */
    public void claseEleviButtonActivare() {
        claseEleviButton.setEnabled(true); // reactivare buton dupa inchidere ferestra catalog
        claseEleviButton.setText("Clase Elevi"); // redenumire buton dupa apasare
    }
    
    /**
     * Afiseaza fereastra <code>BursieriFrame</code> si dezactiveaza si 
     * redenumeste butonul <code>bursieriButton</code>.
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * <code>bursieriButton</code>
     * 
     * @see bursieri.BursieriFrame
     */    
    private void bursieriButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bursieriButtonActionPerformed
        bursieriFrame = new BursieriFrame();
        bursieriFrame.setVisible(true); // afisare fereastra bursieri

        bursieriButton.setEnabled(false); // dezactivare buton dupa apasare
        bursieriButton.setText("Bursieri ✔"); // redenumire buton dupa apasare (bifa)
    }//GEN-LAST:event_bursieriButtonActionPerformed

    /**
     * Reactiveaza si redenumeste butonul <code>bursieriButton</code>.
     */
    public void bursieriButtonActivare() {
        bursieriButton.setEnabled(true); // reactivare buton dupa inchidere ferestra catalog
        bursieriButton.setText("Bursieri"); // redenumire buton dupa apasare
    }
    
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
            java.util.logging.Logger.getLogger(InterfataFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfataFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfataFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfataFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bursieriButton;
    private javax.swing.JButton catalogButton;
    private javax.swing.JButton claseEleviButton;
    private javax.swing.JButton dateEleviButton;
    private javax.swing.JButton deconectareButton;
    private javax.swing.JLabel gradUtilizatorLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel numeUtilizatorLabel;
    private javax.swing.JLabel versiuneLabel;
    // End of variables declaration//GEN-END:variables
}
