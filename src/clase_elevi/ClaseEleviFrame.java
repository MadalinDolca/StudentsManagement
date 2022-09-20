package clase_elevi;

import autentificare.AutentificareFrame;
import static autentificare.AutentificareFrame.interfataFrame;
import global.Database;
import global.Mesaje;
import global.Setari;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * Clasa principală din pachetul <code>clase_elevi</code> care creaza si conține toate
 * elementele grafice ale ferestrei de bursieri, cât și metodele necesare
 * pentru claselor din care acestia fac parte.
 * 
 * @author Madalin
 */
public class ClaseEleviFrame extends javax.swing.JFrame {

    /**
     * Variabila de preluare a continutului selectiei din <code>clasaComboBox</code>
     */
    public static String numeClasaSelectata;

    /**
     * Inițializează componentele ferestrei si iconița ferestrei. Dezactiveaza
     * butoanele <code>adaugaButton</code>, <code>modificaButton</code> si 
     * <code>stergeButton</code> pentru accesul de profesor si arata un mesaj
     * inforational pentru acest grad. Adauga numele claselor din baza de date 
     * in <code>clasaComboBox</code> si adauga elevii ce apartin clasei
     * selectate in <code>claseEleviTable</code>.
     * 
     * <p>Afiseaza <code>mesajFrameReconectare</code> daca preluarea claselor
     * din baza de date a esuat.</p>
     * 
     * @see global.Mesaje#mesajFrameReconectare(javax.swing.JFrame, java.lang.String, java.lang.String)
     */    
    public ClaseEleviFrame() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(Setari.icon)).getImage()); // iconita ferestrei

        // dezactivare butoane pentru acces de profesor
        if (AutentificareFrame.usertype.equals("profesor")) {
            adaugaButton.setEnabled(false);
            modificaButton.setEnabled(false);
            stergeButton.setEnabled(false);
            infoAccesLabel.setText("<html>Nu ai gradul suficient de mare<br/>pentru a face modificări!</html>");
        } else {
            infoAccesLabel.setText("");
            infoAccesLabel.setBorder(null);
        }

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
            Mesaje.mesajFrameReconectare(this, null,
                    "<html><b>Eroare preluare clase!</b><br/>"
                    + "Probabil s-a întrerupt conexiunea.</html>"); // afisare mesaj reconectare in caz de eroare
        }

        numeClasaSelectata = clasaComboBox.getSelectedItem().toString(); // preluare continut selectat

        claseEleviTable.setModel(new ClaseEleviModelTabel()); // setare model tabel date
        claseEleviTable.setAutoCreateRowSorter(true); // auto sortare randuri
        claseEleviTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18)); // font table header
        claseEleviTable.getTableHeader().setBackground(new Color(240, 240, 240)); // fundal table header
        claseEleviTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // permite doar un singur rand selectat
    }
    
    /**
     * Metoda pentru redimensionarea latimii coloanelor in functie de 
     * dimensiunea continutului din celule.
     * 
     * @param table tabelul caruia i se va redimensiona latimea coloanelor
     */
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();

        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width

            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }

            if (width > 300) {
                width = 300;
            }

            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    /**
     * Seteaza <code>detaliiLabel</code> cu numarul total de elevi care apartin
     * clasei selectate in <code>claseComboBox</code>.
     * 
     * <p>Afiseaza <code>mesajFrameReconectare</code> daca preluarea numarului 
     * total de elevi din clasa din baza de date a esuat.</p>
     * 
     * @see global.Mesaje#mesajFrameReconectare(javax.swing.JFrame, java.lang.String, java.lang.String) 
     */    
    public void detaliiEleviDinClasa() {
        int nrElevi = 0;

        try {
            // selectare numar total de elevi din clasa selectata
            Database.stmt = Database.connection.createStatement();
            Database.rs = Database.stmt.executeQuery(
                    "select count(Elevi.id) from Elevi "
                    + "inner join Clase on Elevi.idClasa = Clase.id where Clase.nume = '"
                    + numeClasaSelectata + "';"
            );

            while (Database.rs.next()) {
                nrElevi = Database.rs.getInt("count(Elevi.id)");
            }
        } catch (SQLException ex) {
            Mesaje.mesajFrameReconectare(this, null,
                    "<html><b>Eroare preluare număr total elevi!</b><br/>"
                    + "Probabil s-a întrerupt conexiunea.</html>");
        }

        detaliiLabel.setText(
                "<html>Numărul de elevi din <b>Clasa " + numeClasaSelectata + "</b> este: "
                + "<b> " + nrElevi + "</b><html>");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exportaDateButton = new javax.swing.JButton();
        fixeazaFereastraCheckBox = new javax.swing.JCheckBox();
        adaugaButton = new javax.swing.JButton();
        stergeButton = new javax.swing.JButton();
        modificaButton = new javax.swing.JButton();
        clasaComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        claseEleviTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        detaliiLabel = new javax.swing.JLabel();
        infoAccesLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Repartizarea Elevilor pe Clase");
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        exportaDateButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        exportaDateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/share.png"))); // NOI18N
        exportaDateButton.setText("Exportă Date");
        exportaDateButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        exportaDateButton.setIconTextGap(10);
        exportaDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportaDateButtonActionPerformed(evt);
            }
        });

        fixeazaFereastraCheckBox.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        fixeazaFereastraCheckBox.setSelected(true);
        fixeazaFereastraCheckBox.setText("Fixează Fereastra");
        fixeazaFereastraCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fixeazaFereastraCheckBoxActionPerformed(evt);
            }
        });

        adaugaButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        adaugaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/plus.png"))); // NOI18N
        adaugaButton.setText("Adaugă în Clasă");
        adaugaButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        adaugaButton.setIconTextGap(10);
        adaugaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adaugaButtonActionPerformed(evt);
            }
        });

        stergeButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        stergeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/remove.png"))); // NOI18N
        stergeButton.setText("Șterge din Clasă");
        stergeButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        stergeButton.setIconTextGap(10);
        stergeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stergeButtonActionPerformed(evt);
            }
        });

        modificaButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        modificaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/pencil.png"))); // NOI18N
        modificaButton.setText("Modifică Elev");
        modificaButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        modificaButton.setIconTextGap(10);
        modificaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificaButtonActionPerformed(evt);
            }
        });

        clasaComboBox.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        clasaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        clasaComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                clasaComboBoxItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Selectează Clasa");

        jScrollPane1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        claseEleviTable.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        claseEleviTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        claseEleviTable.setRowHeight(40);
        jScrollPane1.setViewportView(claseEleviTable);

        jPanel1.setBackground(new java.awt.Color(37, 56, 71));
        jPanel1.setLayout(null);

        detaliiLabel.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        detaliiLabel.setForeground(new java.awt.Color(255, 255, 255));
        detaliiLabel.setText("Numărul de elevi din clasă");
        jPanel1.add(detaliiLabel);
        detaliiLabel.setBounds(20, 10, 1020, 32);

        infoAccesLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        infoAccesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoAccesLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        infoAccesLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INFO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(clasaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adaugaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportaDateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modificaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fixeazaFereastraCheckBox)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(stergeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(infoAccesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(clasaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(adaugaButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modificaButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stergeButton)
                        .addGap(18, 18, 18)
                        .addComponent(infoAccesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exportaDateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fixeazaFereastraCheckBox)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Reactiveaza butonul <code>claseEleviButton</code> la inchiderea ferestrei
     * curente.
     * 
     * @param evt indică faptul că a avut loc acțiunea de inchidere a ferestrei
     * 
     * @see interfata.InterfataFrame#claseEleviButtonActivare()
     */
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        interfataFrame.claseEleviButtonActivare(); // reactivare buton dupa inchidere fereastra
    }//GEN-LAST:event_formWindowClosed

    /**
     * Exporta toate datele existente in tabel, alaturi de niste informatii
     * suplimentare, utilizand o fereastra de explorare a fisierelor. 
     * 
     * <p>Afiseaza <code>mesajExportareEsuata</code> daca exportarea a esuat.</p>
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * de exportare a datelor din tabel
     * 
     * @see global.Mesaje#mesajExportareEsuata(java.lang.Object)
     */
    private void exportaDateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportaDateButtonActionPerformed
        // preluare data curenta
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String dataCurenta = sdf.format(new Date());

        // stabilire setari salvare
        JFileChooser fc = new JFileChooser(new File("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop"));
        fc.setPreferredSize(new Dimension(800, 600));
        fc.setDialogTitle("Exportă Datele");

        fc.setFileFilter(new FileNameExtensionFilter("Fișier Text (.txt)", "txt"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Fișier Comma-Separated Values (.csv)", "csv"));

        fc.setSelectedFile(new File("Repartizare Elevi pe Clase " + dataCurenta + ".txt"));

        int userSelection = fc.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fisierDeExportat = fc.getSelectedFile();

            try {
                FileOutputStream fileWriter = new FileOutputStream(fisierDeExportat);
                PrintStream ps = new PrintStream(fileWriter, true, "UTF-8");

                // scriere nume clasa
                ps.print("Clasa: " + numeClasaSelectata + "\n");

                // scriem coloanele tabelului
                ps.print(claseEleviTable.getColumnName(0) + ", "
                        + claseEleviTable.getColumnName(1) + ", "
                        + claseEleviTable.getColumnName(2) + ", "
                        + claseEleviTable.getColumnName(3) + "\n"
                );

                // scriem liniile tabelului
                for (int i = 0; i < claseEleviTable.getRowCount(); i++) {
                    ps.print(claseEleviTable.getValueAt(i, 0).toString() + ", "
                            + claseEleviTable.getValueAt(i, 1) + ", "
                            + claseEleviTable.getValueAt(i, 2) + ", "
                            + claseEleviTable.getValueAt(i, 3) + "\n"
                    );
                }

                ps.close(); // inchidere fisier

            } catch (IOException ex) {
                Mesaje.mesajExportareEsuata(this);
            }
        }
    }//GEN-LAST:event_exportaDateButtonActionPerformed

    /**
     * Fixeaza fereastra curenta daca <code>fixeazaFereastraCheckBox</code> este
     * selectat sau o defixeaza daca se deselecteaza check box-ul.
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a casetei
     * <code>fixeazaFereastraCheckBox</code>
     */
    private void fixeazaFereastraCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fixeazaFereastraCheckBoxActionPerformed
        if (fixeazaFereastraCheckBox.isSelected()) { // checkbox selectat
            this.setAlwaysOnTop(true);
        } else { // checkbox deselectat
            this.setAlwaysOnTop(false);
        }
    }//GEN-LAST:event_fixeazaFereastraCheckBoxActionPerformed

    /**
     * Deschide modalul de adaugare <code>AdaugaDialog</code> a unui elev in
     * clasa la apasarea butonului <code>adaugaButton</code>.
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * de adaugare a unui bursier
     * 
     * @see clase_elevi.AdaugaDialog
     */
    private void adaugaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaButtonActionPerformed
        new AdaugaDialog(this, true).setVisible(true); // deschide fereastra dialog de adaugare
    }//GEN-LAST:event_adaugaButtonActionPerformed

    /**
     * Afiseaza un mesaj de tip <code>mesajInterogareOptiune</code> cu doua 
     * butoane pentru stergerea din baza de date a elevului selectat din clasa 
     * curenta la apasarea <code>stergeButton</code>.
     * 
     * <p>Afiseaza un mesaj de atentionare daca nu s-a selectat nici un elev.</p>
     * <p>Afiseaza un mesaj de confirmare daca s-a efectuat stergerea cu succes.</p>
     * <p>Afiseaza <code>mesajFrameReconectare()</code> daca stergerea a esuat.</p>
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * de stergere a unui elev din clasa
     * 
     * @see global.Mesaje#mesajInterogareOptiune(java.lang.Object, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
     * @see global.Mesaje#mesajFrameReconectare(javax.swing.JFrame, java.lang.String, java.lang.String)
     */
    private void stergeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stergeButtonActionPerformed
        if (claseEleviTable.getSelectedRow() == -1) {
            Mesaje.mesajAtentionare(this, null, "Nu ați selectat nimic!", "warning");
        } else {
            int confirmareStergere = Mesaje.mesajInterogareOptiune(this,
                    "Ștergere Elev", "Ștergeți elevul selectat din clasă?",
                    "Da", "Nu");

            if (confirmareStergere != 0) { // daca am apasat "NO" iesim din metoda
                return;
            } else { // daca am apasat "YES"
                try {
                    // obtinem id-ul randului selectat
                    int idRandSelectat = Integer.parseInt(claseEleviTable.getValueAt(claseEleviTable.getSelectedRow(), 0).toString());

                    Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
                    Database.stmt.executeUpdate(
                            "update Elevi set idClasa = 100 where id = " + idRandSelectat + ";"
                    );

                    if (true) {
                        Mesaje.mesajSucces(this, "Elev Șters din Clasă",
                                "<html><b>Succes!</b><br/>Un elev a fost șters din clasă.</html>");
                    }

                } catch (SQLException ex) {
                    Mesaje.mesajFrameReconectare(this, "Eroare Ștergere!",
                            "<html><b>Ștergere eșuată!</b><br/>"
                            + "Probabil s-a întrerupt conexiunea.</html>");
                }
            }
        }
    }//GEN-LAST:event_stergeButtonActionPerformed

    /**
     * Deschide modalul de modificare <code>ModificaDialog</code> a clasei
     * elevului selectat la apasarea butonului <code>modificaButton</code>.
     * 
     * <p>Afiseaza un mesaj de atentionare daca nu s-a selectat nici un elev.</p>
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * de adaugare a unui bursier
     * 
     * @see clase_elevi.ModificaDialog
     */
    private void modificaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificaButtonActionPerformed
        if (claseEleviTable.getSelectedRow() == -1) { // daca nu avem nici o linie selectata
            Mesaje.mesajAtentionare(this, null, "Nu ați selectat nimic!", "warning");
        } else {
            new ModificaDialog(this, true, claseEleviTable).setVisible(true); // deschide fereastra dialog de actualizare
        }
    }//GEN-LAST:event_modificaButtonActionPerformed

    /**
     * Reactualizeaza <code>claseEleviTable</code> la reselectarea 
     * <code>clasaComboBox</code> cu elevii care apartin clasei selectate.
     * 
     * @param evt indică faptul că a avut loc acțiunea de modificare a starii
     * <code>clasaComboBox</code>
     */
    private void clasaComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_clasaComboBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            numeClasaSelectata = clasaComboBox.getSelectedItem().toString(); // preluare continut reselectat
            claseEleviTable.setModel(new ClaseEleviModelTabel()); // resetare model tabel dupa reselectare
            detaliiEleviDinClasa();
        }
    }//GEN-LAST:event_clasaComboBoxItemStateChanged

    /**
     * Metoda de reimprospatare a tabelului dupa eventuale prelucrari.
     * 
     * @param evt indică faptul că a avut loc acțiunea de obtinere a focus-ului
     * ferestrei curente
     */  
    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        claseEleviTable.setModel(new ClaseEleviModelTabel());
        resizeColumnWidth(claseEleviTable);
        // dateEleviJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        detaliiEleviDinClasa();
    }//GEN-LAST:event_formWindowGainedFocus

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
            java.util.logging.Logger.getLogger(ClaseEleviFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClaseEleviFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClaseEleviFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClaseEleviFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adaugaButton;
    private javax.swing.JComboBox<String> clasaComboBox;
    private javax.swing.JTable claseEleviTable;
    private javax.swing.JLabel detaliiLabel;
    private javax.swing.JButton exportaDateButton;
    private javax.swing.JCheckBox fixeazaFereastraCheckBox;
    private javax.swing.JLabel infoAccesLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modificaButton;
    private javax.swing.JButton stergeButton;
    // End of variables declaration//GEN-END:variables
}
