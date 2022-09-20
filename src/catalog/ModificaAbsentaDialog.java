package catalog;

import com.toedter.calendar.JTextFieldDateEditor;
import global.Database;
import global.Mesaje;
import global.Setari;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Creaza si contine toate elementele grafice ale ferestrei modal de modificare
 * a unei absente, cat si metodele necesare pentru efectuarea acestei
 * operatiuni.
 *
 * @author Madalin
 */
public class ModificaAbsentaDialog extends javax.swing.JDialog {

    /**
     * Pointer spre tabelul <code>absenteTable</code> pentru preluare si
     * modificare date.
     *
     * @see catalog.CatalogFrame#absenteTable
     */
    JTable modificaAbsenteTable;

    /**
     * Inițializează componentele nemodificabile ale ferestrei, iconița 
     * ferestrei si pozitia modalului in concordanta cu cea a ferestrei pentru 
     * catalog. Autocompleteaza datele dialogului cu datele selectate in 
     * fereastra parinte.
     * 
     * @param parent proprietarul din care este afișat dialogul
     * @param modal specifică dacă dialogul blochează introducerea
     * utilizatorului în alte ferestre de nivel superior atunci când este afișat
     * @param absenteTable tabelul din <code>CatalogFrame</code>
     * 
     * @see catalog.CatalogFrame#absenteTable
     */
    public ModificaAbsentaDialog(java.awt.Frame parent, boolean modal, JTable absenteTable) {
        super(parent, modal);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(Setari.icon)).getImage()); // iconita ferestrei
        this.setLocationRelativeTo(parent); // pozitia modalului corespunde cu cea a ferestrei

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

        modificaAbsenteTable = absenteTable; // instantiere

        // completare campuri
        clasaTextField.setText(CatalogFrame.numeClasaSelectata);
        elevTextField.setText(CatalogFrame.numeElevSelectat);
        materiaTextField.setText(CatalogFrame.numeMaterieSelectata);

        // preluare date din tabelul clasei CatalogFrame in campurile din ModificaAbsentaDialog
        ((JTextField) dataDateChooser.getDateEditor().getUiComponent()).setText((modificaAbsenteTable.getValueAt(modificaAbsenteTable.getSelectedRow(), 1)).toString());
        tipAbsentaComboBox.setSelectedItem((modificaAbsenteTable.getValueAt(modificaAbsenteTable.getSelectedRow(), 2)).toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipAbsentaComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        clasaTextField = new javax.swing.JTextField();
        dataDateChooser = new com.toedter.calendar.JDateChooser();
        elevTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        materiaTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        modificaDialogButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modifică Absență");
        setResizable(false);

        tipAbsentaComboBox.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        tipAbsentaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nemotivată", "Motivată" }));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Elev");

        clasaTextField.setEditable(false);
        clasaTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        clasaTextField.setText("Clasa");

        dataDateChooser.setToolTipText("");
        dataDateChooser.setDateFormatString("yyyy-MM-dd");
        dataDateChooser.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        elevTextField.setEditable(false);
        elevTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        elevTextField.setText("Elev");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setText("Materia");

        materiaTextField.setEditable(false);
        materiaTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        materiaTextField.setText("Materia");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setText("Data");

        modificaDialogButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        modificaDialogButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/floppy-disk.png"))); // NOI18N
        modificaDialogButton.setText("Modifică Absența");
        modificaDialogButton.setIconTextGap(10);
        modificaDialogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificaDialogButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setText("Tip Absență");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Clasa");

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
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tipAbsentaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(materiaTextField)
                            .addComponent(elevTextField)
                            .addComponent(clasaTextField)))
                    .addComponent(modificaDialogButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(tipAbsentaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dataDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(modificaDialogButton)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Modifica absenta selectata din tabel cu datele din dialog.
     * 
     * <p>Afiseaza un mesaj de atentionare daca nu s-a ales data.</p>
     * <p>Afiseaza <code>mesajDialogReconectare()</code> daca modificarea a
     * esuat.</p>
     *
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului de
     * modificare
     * 
     * @see global.Mesaje#mesajDialogReconectare(javax.swing.JDialog, java.lang.String, java.lang.String)
     */
    private void modificaDialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificaDialogButtonActionPerformed
        int idRandSelectat = Integer.parseInt(modificaAbsenteTable.getValueAt(modificaAbsenteTable.getSelectedRow(), 0).toString());

        // verific completarea si corectitudinea campurilor
        if (((JTextField) dataDateChooser.getDateEditor().getUiComponent()).getText().equals("")) {
            Mesaje.mesajAtentionare(this, "Atenție!", "Nu ai ales data!", "warning");
        } else {
            try {
                Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
                Database.stmt.executeUpdate(
                        "update Absente set "
                        + "data = '" + ((JTextField) dataDateChooser.getDateEditor().getUiComponent()).getText() + "', "
                        + "tipAbsenta = '" + tipAbsentaComboBox.getSelectedItem().toString() + "', "
                        + "actualizat = now() "
                        + "where id = " + idRandSelectat + ";"
                );

                this.dispose(); // inlaturare fereastra dupa adaugarea datelor

            } catch (SQLException ex) {
                Mesaje.mesajDialogReconectare(this, "Eroare!",
                        "<html><b>Modificare eșuată!</b><br/>"
                        + "Probabil s-a întrerupt conexiunea.</html>");
            }
        }
    }//GEN-LAST:event_modificaDialogButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ModificaAbsentaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificaAbsentaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificaAbsentaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificaAbsentaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField clasaTextField;
    private com.toedter.calendar.JDateChooser dataDateChooser;
    private javax.swing.JTextField elevTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField materiaTextField;
    private javax.swing.JButton modificaDialogButton;
    private javax.swing.JComboBox<String> tipAbsentaComboBox;
    // End of variables declaration//GEN-END:variables
}
