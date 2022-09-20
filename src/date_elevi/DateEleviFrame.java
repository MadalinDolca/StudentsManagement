package date_elevi;

import autentificare.AutentificareFrame;
import static autentificare.AutentificareFrame.interfataFrame;
import global.Database;
import global.Mesaje;
import global.Setari;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * Clasa principală din pachetul <code>date_elevi</code> care creaza si conține toate
 * elementele grafice ale ferestrei date elevi, cât și metodele necesare
 * pentru gestionarea datelor personale ale elevilor.
 * 
 * @author Madalin
 */
public class DateEleviFrame extends javax.swing.JFrame {

    /**
     * Inițializează componentele ferestrei si iconița ferestrei. Dezactiveaza
     * butonul <code>adaugaElevButton</code>, <code>modificaElevButton</code>,
     * <code>stergeElevButton</code> si <code>exportaDateButton</code> pentru
     * accesul de profesor si arata un mesaj inforational pentru acest grad. 
     * Adauga toti elevii din baza de date in <code>dateEleviTable</code>.

     * Adauga absentele elevului selectat, de la materia selectata, din baza de 
     * date in <code>absenteTable</code>.
     */
    public DateEleviFrame() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(Setari.icon)).getImage()); // iconita ferestrei

        // dezactivare butoane pentru acces de profesor
        if (AutentificareFrame.usertype.equals("profesor")) {
            adaugaElevButton.setEnabled(false);
            modificaElevButton.setEnabled(false);
            stergeElevButton.setEnabled(false);
            exportaDateButton.setEnabled(false);
            infoAccesLabel.setText("<html>Nu ai gradul suficient<br/>de mare pentru a<br/>face modificări!</html>");
        } else {
            infoAccesLabel.setText("");
            infoAccesLabel.setBorder(null);
        }

        dateEleviTable.setModel(new DateEleviModelTabel()); // setare model tabel date
        dateEleviTable.setAutoCreateRowSorter(true); // auto sortare randuri
        dateEleviTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18)); // font table header
        dateEleviTable.getTableHeader().setBackground(new Color(240, 240, 240)); // fundal table header
        dateEleviTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // permite doar un singur rand selectat

        istoricModificariTable.setModel(new IstoricModificariModelTabel()); // setare model tabel istoric
        istoricModificariTable.setAutoCreateRowSorter(true); // auto sortare randuri
        istoricModificariTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18)); // font table header
        istoricModificariTable.getTableHeader().setBackground(new Color(240, 240, 240)); // fundal table header
        istoricModificariTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // permite doar un singur rand selectat

        // culoare alternativa rand
        /*dateEleviTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                Component c = super.getTableCellRendererComponent(table,
                        value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? null : new Color(240, 240, 240));

                return c;
            };};*/
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
     * Seteaza continutul din <code>detaliiLabel</code> cu numarul total al 
     * elevilor din unitate.
     * 
     * <p>Afiseaza <code>mesajFrameReconectare</code> daca preluarea numarului
     * total de elevi din unitate a esuat.</p>
     * 
     * @see global.Mesaje#mesajFrameReconectare(javax.swing.JFrame, java.lang.String, java.lang.String) 
     */
    public void detaliiEleviDinUnitate() {
        int nrElevi = 0;

        try {
            // selectare numar total de elevi din unitatea de invatamant
            Database.stmt = Database.connection.createStatement();
            Database.rs = Database.stmt.executeQuery("select count(Elevi.id) from Elevi;");

            while (Database.rs.next()) {
                nrElevi = Database.rs.getInt("count(Elevi.id)");
            }
        } catch (SQLException ex) {
            Mesaje.mesajFrameReconectare(this, null,
                    "<html><b>Eroare preluare număr elevi din unitate!</b><br/>"
                    + "Probabil s-a întrerupt conexiunea.</html>"); // afisare mesaj reconectare in caz de eroare
        }

        detaliiLabel.setText(
                "<html>Numărul de elevi din unitatea de învățământ: "
                + "<b> " + nrElevi + "</b><html>");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adaugaElevButton = new javax.swing.JButton();
        stergeElevButton = new javax.swing.JButton();
        dateEleviTabbedPane = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        dateEleviTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        istoricModificariTable = new javax.swing.JTable();
        modificaElevButton = new javax.swing.JButton();
        exportaDateButton = new javax.swing.JButton();
        fixeazaFereastraCheckBox = new javax.swing.JCheckBox();
        infoAccesLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        detaliiLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Date Elevi");
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

        adaugaElevButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        adaugaElevButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/plus.png"))); // NOI18N
        adaugaElevButton.setText("Adaugă Elev");
        adaugaElevButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        adaugaElevButton.setIconTextGap(10);
        adaugaElevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adaugaElevButtonActionPerformed(evt);
            }
        });

        stergeElevButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        stergeElevButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/remove.png"))); // NOI18N
        stergeElevButton.setText("Șterge Elev");
        stergeElevButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        stergeElevButton.setIconTextGap(10);
        stergeElevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stergeElevButtonActionPerformed(evt);
            }
        });

        dateEleviTabbedPane.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        dateEleviTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                dateEleviTabbedPaneStateChanged(evt);
            }
        });

        dateEleviTable.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        dateEleviTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9"
            }
        ));
        dateEleviTable.setRowHeight(40);
        jScrollPane2.setViewportView(dateEleviTable);

        dateEleviTabbedPane.addTab("Date Elevi", jScrollPane2);

        istoricModificariTable.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        istoricModificariTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        istoricModificariTable.setRowHeight(40);
        jScrollPane3.setViewportView(istoricModificariTable);

        dateEleviTabbedPane.addTab("Istoric Modificări", jScrollPane3);

        modificaElevButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        modificaElevButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/pencil.png"))); // NOI18N
        modificaElevButton.setText("Modifică Elev");
        modificaElevButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        modificaElevButton.setIconTextGap(10);
        modificaElevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificaElevButtonActionPerformed(evt);
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

        infoAccesLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        infoAccesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoAccesLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        infoAccesLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INFO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(37, 56, 71));
        jPanel1.setLayout(null);

        detaliiLabel.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        detaliiLabel.setForeground(new java.awt.Color(255, 255, 255));
        detaliiLabel.setText("Numărul de elevi din unitatea de învățământ");
        jPanel1.add(detaliiLabel);
        detaliiLabel.setBounds(20, 10, 1440, 32);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(dateEleviTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1202, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(adaugaElevButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stergeElevButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modificaElevButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportaDateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fixeazaFereastraCheckBox)
                    .addComponent(infoAccesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(adaugaElevButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modificaElevButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stergeElevButton)
                        .addGap(18, 18, 18)
                        .addComponent(infoAccesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exportaDateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fixeazaFereastraCheckBox))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(dateEleviTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Reactiveaza butonul <code>dateEleviButton</code> la inchiderea ferestrei 
     * curente.
     * 
     * @param evt indică faptul că a avut loc acțiunea de inchidere a ferestrei
     * 
     * @see interfata.InterfataFrame#dateEleviButtonActivare()
     */
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        interfataFrame.dateEleviButtonActivare(); // reactivare buton dupa inchidere fereastra
    }//GEN-LAST:event_formWindowClosed

    /**
     * Deschide modalul de adaugare a unui elev si a datelor sale la apasarea
     * butonului <code>adaugaElevButton</code>.
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * de adaugare a unui elev
     */
    private void adaugaElevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaElevButtonActionPerformed
        new AdaugaDialog(this, true).setVisible(true); // deschide fereastra dialog de adaugare
    }//GEN-LAST:event_adaugaElevButtonActionPerformed

    /**
     * Metoda de reimprospatare a tabelelor dupa eventuale prelucrari.
     * 
     * @param evt indică faptul că a avut loc acțiunea de obtinere a focus-ului
     * ferestrei curente
     */
    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        dateEleviTable.setModel(new DateEleviModelTabel());
        resizeColumnWidth(dateEleviTable);

        istoricModificariTable.setModel(new IstoricModificariModelTabel());
        resizeColumnWidth(istoricModificariTable);

        // dateEleviJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        detaliiEleviDinUnitate();
    }//GEN-LAST:event_formWindowGainedFocus

    /**
     * Deschide modalul de modificare <code>ModificaDialog</code> a elevului
     * selectat la apasarea butonului <code>modificaButton</code>.
     * 
     * <p>Afiseaza un mesaj de atentionare daca nu s-a selectat nimic.</p>
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * de modificare a datelor elevului
     * 
     * @see date_elevi.ModificaDialog
     */
    private void modificaElevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificaElevButtonActionPerformed
        if (dateEleviTable.getSelectedRow() == -1) { // daca nu avem nici o linie selectata
            Mesaje.mesajAtentionare(this, null, "Nu ați selectat nici un elev!", "warning");
        } else {
            new ModificaDialog(this, true, dateEleviTable).setVisible(true); // deschide fereastra dialog de actualizare
        }
    }//GEN-LAST:event_modificaElevButtonActionPerformed

    /**
     * Afiseaza un mesaj de tip <code>mesajInterogareOptiune</code> cu doua
     * butoane pentru stergerea din baza de date a elevului selectat la 
     * apasarea <code>stergeElevButton</code>.
     * 
     * <p>Afiseaza un mesaj de atentionare daca nu s-a selectat nici un elev.</p>
     * <p>Afiseaza un mesaj de confirmare daca s-a efectuat stergerea cu succes.</p>
     * <p>Afiseaza <code>mesajFrameReconectare()</code> daca stergerea a esuat.</p>
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * de stergere a unui elev
     * 
     * @see global.Mesaje#mesajInterogareOptiune(java.lang.Object, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     * @see global.Mesaje#mesajFrameReconectare(javax.swing.JFrame, java.lang.String, java.lang.String)
     */
    // metoda de stergere a unui elev selectat din baza de date la apasarea butonului
    private void stergeElevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stergeElevButtonActionPerformed
        if (dateEleviTable.getSelectedRow() == -1) {
            Mesaje.mesajAtentionare(this, null, "Nu ați selectat nici un elev!", "warning");
        } else {
            int confirmareStergere = Mesaje.mesajInterogareOptiune(this,
                    "Ștergere Elev", "Ștergeți elevul selectat?",
                    "Da", "Nu");

            if (confirmareStergere != 0) { // daca am apasat "NO" iesim din metoda
                return;
            } else { // daca am apasat "YES"
                try {
                    // obtinem id-ul randului selectat
                    int idRandSelectat = Integer.parseInt(dateEleviTable.getValueAt(dateEleviTable.getSelectedRow(), 0).toString());

                    Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
                    Database.stmt.executeUpdate(
                            "delete from Elevi where id = " + idRandSelectat + ";"
                    );

                    if (true) {
                        Mesaje.mesajSucces(this, "Elev Șters",
                                "<html><b>Succes!</b><br/>Un elev a fost șters.</html>");
                    }

                } catch (SQLException ex) {
                    Mesaje.mesajFrameReconectare(this, "Eroare Ștergere!",
                            "<html><b>Ștergere eșuată!</b><br/>"
                            + "Probabil s-a întrerupt conexiunea.</html>");
                }
            }
        }
    }//GEN-LAST:event_stergeElevButtonActionPerformed

    /**
     * Exporta toate datele existente in tabel, alaturi de niste informatii
     * suplimentare in functie de tab-ul selectat in 
     * <code>dateEleviTabbedPane</code>, utilizand o fereastra de explorare a 
     * fisierelor. 
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

        fc.setSelectedFile(new File("Date Elevi " + dataCurenta + ".txt"));

        int userSelection = fc.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fisierDeExportat = fc.getSelectedFile();

            try {
                FileOutputStream fileWriter = new FileOutputStream(fisierDeExportat);
                PrintStream ps = new PrintStream(fileWriter, true, "UTF-8");

                // scriem coloanele tabelului
                ps.print(dateEleviTable.getColumnName(0) + ", "
                        + dateEleviTable.getColumnName(1) + ", "
                        + dateEleviTable.getColumnName(2) + ", "
                        + dateEleviTable.getColumnName(3) + ", "
                        + dateEleviTable.getColumnName(4) + ", "
                        + dateEleviTable.getColumnName(5) + ", "
                        + dateEleviTable.getColumnName(6) + ", "
                        + dateEleviTable.getColumnName(7) + ", "
                        + dateEleviTable.getColumnName(8) + "\n"
                );

                // scriem liniile tabelului
                for (int i = 0; i < dateEleviTable.getRowCount(); i++) {
                    ps.print(dateEleviTable.getValueAt(i, 0).toString() + ", "
                            + dateEleviTable.getValueAt(i, 1) + ", "
                            + dateEleviTable.getValueAt(i, 2) + ", "
                            + dateEleviTable.getValueAt(i, 3) + ", "
                            + dateEleviTable.getValueAt(i, 4) + ", "
                            + dateEleviTable.getValueAt(i, 5) + ", "
                            + dateEleviTable.getValueAt(i, 6) + ", "
                            + dateEleviTable.getValueAt(i, 7) + ", "
                            + dateEleviTable.getValueAt(i, 8).toString() + "\n"
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
     * Dezactiveaza toate butoanele atunci cand tab-ul "Istoric Modificari"
     * din <code>dateEleviTabbedPane</code> este activat, indiferent de grad
     * si reactiveaza butoanele atunci cand tab-ul "Date Elevi" este activat.
     * 
     * @param evt indica faptul ca a avut loc actiunea de modificare a starii
     * <code>dateEleviTabbedPane</code>
     */
    private void dateEleviTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dateEleviTabbedPaneStateChanged
        // activare butoane daca primul tab e selectat
        if (dateEleviTabbedPane.getSelectedIndex() == 0) {
            adaugaElevButton.setEnabled(true);
            modificaElevButton.setEnabled(true);
            stergeElevButton.setEnabled(true);
            exportaDateButton.setEnabled(true);
            infoAccesLabel.setText("");
            infoAccesLabel.setBorder(null);
        } // dezactivare butoane daca al doilea tab e selectat
        else if (dateEleviTabbedPane.getSelectedIndex() == 1) {
            adaugaElevButton.setEnabled(false);
            modificaElevButton.setEnabled(false);
            stergeElevButton.setEnabled(false);
            exportaDateButton.setEnabled(false);
            infoAccesLabel.setText("<html>Istoricul nu se poate<br/>modifica sau exporta!</html>");
            infoAccesLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(
                    null, "INFO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                    javax.swing.border.TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("Dialog", 1, 18)));
        }
    }//GEN-LAST:event_dateEleviTabbedPaneStateChanged

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
            java.util.logging.Logger.getLogger(DateEleviFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DateEleviFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DateEleviFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DateEleviFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adaugaElevButton;
    private javax.swing.JTabbedPane dateEleviTabbedPane;
    private javax.swing.JTable dateEleviTable;
    private javax.swing.JLabel detaliiLabel;
    private javax.swing.JButton exportaDateButton;
    private javax.swing.JCheckBox fixeazaFereastraCheckBox;
    private javax.swing.JLabel infoAccesLabel;
    private javax.swing.JTable istoricModificariTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton modificaElevButton;
    private javax.swing.JButton stergeElevButton;
    // End of variables declaration//GEN-END:variables
}
