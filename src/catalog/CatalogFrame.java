package catalog;

import autentificare.AutentificareFrame;
import static autentificare.AutentificareFrame.interfataFrame;
import global.Database;
import global.Mesaje;
import global.Setari;
import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clasa principală din pachetul <code>catalog</code> care creaza si conține toate
 * elementele grafice ale ferestrei catalog, cât și metodele necesare
 * pentru gestionarea notelor si al absentelor elevilor.
 * 
 * @author Madalin
 */
public class CatalogFrame extends javax.swing.JFrame {

    /**
     * Va memora continutul selectiei din <code>clasaComboBox</code>
     */
    public static String numeClasaSelectata;

    /**
     * Va memora ID-ul elevului din continutul selectiei din
     * <code>elevComboBox</code> utilizand <code>getIdElevSelectat()</code>
     */
    public static String idElevSelectat;

    /**
     * Va memora numele elevului din continutul selectiei din
     * <code>elevComboBox</code> utilizand <code>getNumeElevSelectat()</code>
     */
    public static String numeElevSelectat;

    /**
     * Va memora numele materiei din continutul selectiei din
     * <code>materieComboBox</code>
     */
    public static String numeMaterieSelectata;

    /**
     * Inițializează componentele ferestrei si iconița ferestrei. Dezactiveaza
     * butonul <code>stergeButton</code> pentru accesul de profesor si arata un 
     * mesaj inforational pentru acest grad. 
     * Adauga numele claselor din baza de date in <code>clasaComboBox</code> 
     * utilizand <code>adaugareClaseInComboBox()</code>. 
     * Adauga numele elevilor din clasa selectata in <code>elevComboBox</code>
     * utilizand <code>adaugareEleviInComboBox()</code>.
     * Adauga numele materiilor din baza de date in <code>materieComboBox</code>
     * Adauga notele elevului selectat, de la materia selectata, din baza de
     * date in <code>noteTable</code>.
     * Adauga absentele elevului selectat, de la materia selectata, din baza de 
     * date in <code>absenteTable</code>.
     */
    public CatalogFrame() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(Setari.icon)).getImage()); // iconita ferestrei

        // dezactivare butoane pentru acces de profesor
        if (AutentificareFrame.usertype.equals("profesor")) {
            stergeButton.setEnabled(false);
            infoAccesLabel.setText("<html>Nu ai gradul suficient de mare<br/>pentru a șterge date!</html>");
        } else {
            infoAccesLabel.setText("");
            infoAccesLabel.setBorder(null);
        }

        // adaugarea claselor din DB in comboBox la deschiderea ferestrei
        adaugareClaseInComboBox();
        numeClasaSelectata = clasaComboBox.getSelectedItem().toString();

        // adaugarea elevilor din clasa selectata din DB in comboBox la deschiderea ferestrei
        adaugareEleviInComboBox();
        getIdElevSelectat();

        // adaugarea materiilor din DB in comboBox la deschiderea ferestrei
        adaugareMateriiInComboBox();
        numeMaterieSelectata = materieComboBox.getSelectedItem().toString();

        noteTable.setModel(new NoteModelTabel()); // setare model tabel date
        noteTable.setAutoCreateRowSorter(true); // auto sortare randuri
        noteTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18)); // font table header
        noteTable.getTableHeader().setBackground(new Color(240, 240, 240)); // fundal table header
        noteTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // permite doar un singur rand selectat

        absenteTable.setModel(new AbsenteModelTabel()); // setare model tabel date
        absenteTable.setAutoCreateRowSorter(true); // auto sortare randuri
        absenteTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18)); // font table header
        absenteTable.getTableHeader().setBackground(new Color(240, 240, 240)); // fundal table header
        absenteTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // permite doar un singur rand selectat
    }
    
    /**
     * Obtine ID-ului elevului selectat (daca exista elev) din
     * <code>elevComboBox</code> si il memoreaza in <code>idElevSelectat</code>.
     */
    public void getIdElevSelectat() {
        if (elevComboBox.getSelectedItem() != null) {
            idElevSelectat = elevComboBox.getSelectedItem().toString();

            if (idElevSelectat.contains(" ")) { // extragere ID din String
                idElevSelectat = idElevSelectat.substring(0, idElevSelectat.indexOf(" "));
            }
        } else {
            idElevSelectat = "0"; // nu exista nici un elev
        }
    }

    /**
     * Obtine numele elevului selectat (daca exista elev) fara ID din
     * <code>elevComboBox</code> si il memoreaza in <code>numeElev</code>.
     * 
     * @param numeElevCuID va prelua numele elevului selectat in combo box
     * 
     * @return numele elevului fara ID de tip <code>String</code>
     */
    public String getNumeElevSelectat(String numeElevCuID) {
        String numeElev = numeElevCuID;

        if (numeElev.contains("|")) { // extragere ID din String
            numeElev = numeElev.substring(numeElev.lastIndexOf("| ") + 2);
        }

        return numeElev;
    }

    /**
     * Adauga numele claselor in baza de date in <code>clasaComboBox</code>.
     * 
     * <p>Afiseaza <code>mesajFrameReconectare()</code> daca preluarea numelor
     * claselor din baza de date a esuat.</p>
     * 
     * @see global.Mesaje#mesajFrameReconectare(javax.swing.JFrame, java.lang.String, java.lang.String) 
     */
    public void adaugareClaseInComboBox() {
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
                    "<html><b>Preluare nume clase eșuată!</b><br/>"
                    + "Probabil s-a întrerupt conexiunea.</html>"); // afisare mesaj reconectare in caz de eroare
        }
    }

    /**
     * Adauga elevii din baza de date in <code>elevComboBox</code> in functie
     * de clasa aleasa in <code>clasaComboBox</code>.
     * 
     * <p>Afiseaza <code>mesajFrameReconectare()</code> daca preluarea elevilor
     * din baza de date a esuat.</p>
     * 
     * @see global.Mesaje#mesajFrameReconectare(javax.swing.JFrame, java.lang.String, java.lang.String) 
     */
    public void adaugareEleviInComboBox() {
        numeClasaSelectata = clasaComboBox.getSelectedItem().toString();

        try {
            ArrayList<String> eleviArray = new ArrayList<String>();
            Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
            Database.rs = Database.stmt.executeQuery(
                    "select Elevi.id, Elevi.nume, Elevi.initialaTata, Elevi.prenume from Elevi "
                    + "inner join Clase on Elevi.idClasa = Clase.id where Clase.nume = '"
                    + numeClasaSelectata + "';");

            while (Database.rs.next()) { // parcurgem fiecare linie din tabelul bazei de date
                // adaugam continutul celulelor din tabelul bazei de date in array
                eleviArray.add(Database.rs.getString("id") + " | "
                        + Database.rs.getString("nume") + " "
                        + Database.rs.getString("initialaTata") + " "
                        + Database.rs.getString("prenume"));
            }

            elevComboBox.removeAllItems();
            elevComboBox.setModel(new DefaultComboBoxModel(eleviArray.toArray()));

        } catch (SQLException ex) {
            Mesaje.mesajFrameReconectare(this, null,
                    "<html><b>Preluare elevi eșuată!</b><br/>"
                    + "Probabil s-a întrerupt conexiunea.</html>"); // afisare mesaj reconectare in caz de eroare
        }
    }

    /**
     * Adauga numele materiilor din baza de date in <code>materieComboBox</code>.
     * 
     * <p>Afiseaza <code>mesajFrameReconectare</code> daca preluarea materiilor
     * din baza de date a esuat.</p>
     * 
     * @see global.Mesaje#mesajFrameReconectare(javax.swing.JFrame, java.lang.String, java.lang.String) 
     */
    public void adaugareMateriiInComboBox() {
        try {
            ArrayList<String> materiiArray = new ArrayList<String>();
            Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
            Database.rs = Database.stmt.executeQuery("select nume from Materii;");

            while (Database.rs.next()) { // parcurgem fiecare linie din tabelul bazei de date
                // adaugam continutul celulelor din tabelul bazei de date in array
                materiiArray.add(Database.rs.getString("nume"));
            }

            materieComboBox.removeAllItems();
            materieComboBox.setModel(new DefaultComboBoxModel(materiiArray.toArray()));

        } catch (SQLException ex) {
            Mesaje.mesajFrameReconectare(this, null,
                    "<html><b>Preluare materii eșuată!</b><br/>"
                    + "Probabil s-a întrerupt conexiunea.</html>"); // afisare mesaj reconectare in caz de eroare
        }
    }

    /**
     * Seteaza continutul din <code>detaliiLabel</code> in functie de tab-ul 
     * <code>catalogTabbedPane</code> selectat.
     * 
     * <p>Daca tabul <b>Note</b> este selectat, va afisa media elevului la
     * materia selectata in combo box, continutul modificandu-se daca exista o
     * nota de teza.</p>
     * <p>Daca tabul <b>Absente</b> este selectat, va afisa numorul total de
     * absente nemotivate ale elevului la materia selectata in combo box.</p>
     * 
     * <p>Afiseaza <code>mesajFrameReconectare</code> daca preluarea mediei sau
     * a numarului de absente nemotivate din baza de date a esuat.</p>
     * 
     * @see global.Mesaje#mesajFrameReconectare(javax.swing.JFrame, java.lang.String, java.lang.String) 
     */
    public void detaliiNoteAbsente() {
        float media = 0;
        float notaTeza = 0;
        int nrAbsenteNemotivate = 0;

        // afisare medie daca tabul Note este selectat
        if (catalogTabbedPane.getSelectedIndex() == 0) {
            try {
                // selectare medie note normale
                Database.stmt = Database.connection.createStatement();
                Database.rs = Database.stmt.executeQuery(
                        "select avg(Note.nota) from Note "
                        + "inner join Elevi on Note.idElev = Elevi.id "
                        + "inner join Materii on Note.idMaterie = Materii.id where "
                        + "Elevi.id = '" + idElevSelectat + "' and "
                        + "Materii.nume = '" + numeMaterieSelectata + "' and "
                        + "Note.tipNota = 'Normală';"
                );

                while (Database.rs.next()) {
                    media = Database.rs.getFloat("avg(Note.nota)");
                }

                // selectare medie note teze
                Database.stmt = Database.connection.createStatement();
                Database.rs = Database.stmt.executeQuery(
                        "select avg(Note.nota) from Note "
                        + "inner join Elevi on Note.idElev = Elevi.id "
                        + "inner join Materii on Note.idMaterie = Materii.id where "
                        + "Elevi.id = '" + idElevSelectat + "' and "
                        + "Materii.nume = '" + numeMaterieSelectata + "' and "
                        + "Note.tipNota = 'Teză';"
                );

                while (Database.rs.next()) {
                    notaTeza = Database.rs.getFloat("avg(Note.nota)");
                }
            } catch (SQLException ex) {
                Mesaje.mesajFrameReconectare(this, null,
                        "<html><b>Preluare medie eșuată!</b><br/>"
                        + "Probabil s-a întrerupt conexiunea.</html>"); // afisare mesaj reconectare in caz de eroare
            }

            // setare continut label daca nu exista teza
            if (notaTeza == 0.0) {
                detaliiLabel.setText(
                        "<html>Media de la <b>" + materieComboBox.getSelectedItem()
                        + "</b> este: <b>" + media + "</b><html>");
            } // setare continut label daca exista teza 
            else if (notaTeza != 0.0) {
                media = (3 * media) + notaTeza; // calcul medie cu teza
                media = media / 4;

                detaliiLabel.setText(
                        "<html>Media de la <b>" + materieComboBox.getSelectedItem()
                        + "</b> cu <b>Teză</b> este: <b>" + media + "</b><html>");
            }

        } // afisare numar absente nemotivate daca tabul Absente este selectat 
        else if (catalogTabbedPane.getSelectedIndex() == 1) {
            try {
                // selectare numar total de absente nemotivate
                Database.stmt = Database.connection.createStatement();
                Database.rs = Database.stmt.executeQuery(
                        "select count(Absente.tipAbsenta) from Absente "
                        + "inner join Elevi on Absente.idElev = Elevi.id "
                        + "inner join Materii on Absente.idMaterie = Materii.id where "
                        + "Elevi.id = '" + CatalogFrame.idElevSelectat + "' and "
                        + "Materii.nume = '" + CatalogFrame.numeMaterieSelectata + "' and "
                        + "Absente.tipAbsenta = 'Nemotivată';"
                );

                while (Database.rs.next()) {
                    nrAbsenteNemotivate = Database.rs.getInt("count(Absente.tipAbsenta)");
                }
            } catch (SQLException ex) {
                Mesaje.mesajFrameReconectare(this, null,
                        "<html><b>Preluare număr de absențe eșuată!</b><br/>"
                        + "Probabil s-a întrerupt conexiunea.</html>"); // afisare mesaj reconectare in caz de eroare
            }

            detaliiLabel.setText(
                    "<html>Numărul de <b>absențe nemotivate</b> de la <b>" + materieComboBox.getSelectedItem()
                    + "</b> este: <b>" + nrAbsenteNemotivate + "</b><html>");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clasaComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        elevComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        materieComboBox = new javax.swing.JComboBox<>();
        exportaDateButton = new javax.swing.JButton();
        fixeazaFereastraCheckBox = new javax.swing.JCheckBox();
        adaugaButton = new javax.swing.JButton();
        stergeButton = new javax.swing.JButton();
        modificaButton = new javax.swing.JButton();
        catalogTabbedPane = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        noteTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        absenteTable = new javax.swing.JTable();
        panel1 = new java.awt.Panel();
        detaliiLabel = new javax.swing.JLabel();
        infoAccesLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Catalog");
        setAlwaysOnTop(true);
        setPreferredSize(new java.awt.Dimension(1050, 735));
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

        clasaComboBox.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        clasaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        clasaComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                clasaComboBoxItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Clasa");

        elevComboBox.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        elevComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        elevComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                elevComboBoxItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Elev");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Materie");

        materieComboBox.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        materieComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        materieComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                materieComboBoxItemStateChanged(evt);
            }
        });

        exportaDateButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        exportaDateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/share.png"))); // NOI18N
        exportaDateButton.setText("Exportă Date");
        exportaDateButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        exportaDateButton.setIconTextGap(10);
        exportaDateButton.setPreferredSize(new java.awt.Dimension(275, 48));
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
        adaugaButton.setText("Adaugă Notă");
        adaugaButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        adaugaButton.setIconTextGap(10);
        adaugaButton.setPreferredSize(new java.awt.Dimension(275, 48));
        adaugaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adaugaButtonActionPerformed(evt);
            }
        });

        stergeButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        stergeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/remove.png"))); // NOI18N
        stergeButton.setText("Șterge Notă");
        stergeButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        stergeButton.setIconTextGap(10);
        stergeButton.setPreferredSize(new java.awt.Dimension(275, 48));
        stergeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stergeButtonActionPerformed(evt);
            }
        });

        modificaButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        modificaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagini/pencil.png"))); // NOI18N
        modificaButton.setText("Modifică Absență");
        modificaButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        modificaButton.setIconTextGap(10);
        modificaButton.setPreferredSize(new java.awt.Dimension(275, 48));
        modificaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificaButtonActionPerformed(evt);
            }
        });

        catalogTabbedPane.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        catalogTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                catalogTabbedPaneStateChanged(evt);
            }
        });

        jScrollPane1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        noteTable.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        noteTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        noteTable.setRowHeight(40);
        jScrollPane1.setViewportView(noteTable);

        catalogTabbedPane.addTab("Note", jScrollPane1);

        absenteTable.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        absenteTable.setModel(new javax.swing.table.DefaultTableModel(
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
        absenteTable.setRowHeight(40);
        jScrollPane2.setViewportView(absenteTable);

        catalogTabbedPane.addTab("Absențe", jScrollPane2);

        panel1.setBackground(new java.awt.Color(37, 56, 71));
        panel1.setLayout(null);

        detaliiLabel.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        detaliiLabel.setForeground(new java.awt.Color(255, 255, 255));
        detaliiLabel.setText("Detalii Note / Absențe");
        panel1.add(detaliiLabel);
        detaliiLabel.setBounds(20, 10, 1000, 32);

        infoAccesLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        infoAccesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoAccesLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        infoAccesLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INFO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(clasaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(elevComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addComponent(catalogTabbedPane))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(fixeazaFereastraCheckBox)
                        .addComponent(jLabel3)
                        .addComponent(adaugaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exportaDateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stergeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(modificaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(materieComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(infoAccesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(materieComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(elevComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clasaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(adaugaButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modificaButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stergeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(infoAccesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exportaDateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fixeazaFereastraCheckBox))
                    .addComponent(catalogTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Reactiveaza butonul <code>catalogButton</code> la inchiderea ferestrei 
     * curente.
     * 
     * @param evt indică faptul că a avut loc acțiunea de inchidere a ferestrei
     * 
     * @see interfata.InterfataFrame#catalogButtonActivare()
     */
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        interfataFrame.catalogButtonActivare(); // reactivare buton dupa inchidere fereastra
    }//GEN-LAST:event_formWindowClosed

    /**
     * Exporta toate datele existente in tabel, alaturi de niste informatii
     * suplimentare in functie de tab-ul selectat in 
     * <code>catalogTabbedPane</code>, utilizand o fereastra de explorare a 
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

        // daca tabul Note este selectat
        if (catalogTabbedPane.getSelectedIndex() == 0) {
            fc.setSelectedFile(new File("Notele elevului " + getNumeElevSelectat(elevComboBox.getSelectedItem().toString())
                    + " la " + materieComboBox.getSelectedItem().toString()
                    + " " + dataCurenta + ".txt"));

            int userSelection = fc.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fisierDeExportat = fc.getSelectedFile();

                try {
                    FileOutputStream fileWriter = new FileOutputStream(fisierDeExportat);
                    PrintStream ps = new PrintStream(fileWriter, true, "UTF-8");

                    ps.print("Clasa: " + clasaComboBox.getSelectedItem().toString()
                            + "\nElev: " + getNumeElevSelectat(elevComboBox.getSelectedItem().toString())
                            + "\nMateria: " + materieComboBox.getSelectedItem().toString()
                            + "\n\n");

                    // scriem coloanele tabelului
                    ps.print(noteTable.getColumnName(0) + ", "
                            + noteTable.getColumnName(1) + ", "
                            + noteTable.getColumnName(2) + ", "
                            + noteTable.getColumnName(3) + "\n"
                    );

                    // scriem liniile tabelului
                    for (int i = 0; i < noteTable.getRowCount(); i++) {
                        ps.print(noteTable.getValueAt(i, 0).toString() + ", "
                                + noteTable.getValueAt(i, 1) + ", "
                                + noteTable.getValueAt(i, 2) + ", "
                                + noteTable.getValueAt(i, 3) + "\n"
                        );
                    }

                    ps.close(); // inchidere fisier

                } catch (IOException ex) {
                    Mesaje.mesajExportareEsuata(this);
                }
            }
        } // daca tabul Absente este selectat 
        else if (catalogTabbedPane.getSelectedIndex() == 1) {
            fc.setSelectedFile(new File("Absențele elevului " + getNumeElevSelectat(elevComboBox.getSelectedItem().toString())
                    + " la " + materieComboBox.getSelectedItem().toString()
                    + " " + dataCurenta + ".txt"));

            int userSelection = fc.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fisierDeExportat = fc.getSelectedFile();

                try {
                    FileOutputStream fileWriter = new FileOutputStream(fisierDeExportat);
                    PrintStream ps = new PrintStream(fileWriter, true, "UTF-8");

                    ps.print("Clasa: " + clasaComboBox.getSelectedItem().toString()
                            + "\nElev: " + getNumeElevSelectat(elevComboBox.getSelectedItem().toString())
                            + "\nMateria: " + materieComboBox.getSelectedItem().toString()
                            + "\n\n");

                    // scriem coloanele tabelului
                    ps.print(absenteTable.getColumnName(0) + ", "
                            + absenteTable.getColumnName(1) + ", "
                            + absenteTable.getColumnName(2) + "\n"
                    );

                    // scriem liniile tabelului
                    for (int i = 0; i < absenteTable.getRowCount(); i++) {
                        ps.print(absenteTable.getValueAt(i, 0).toString() + ", "
                                + absenteTable.getValueAt(i, 1) + ", "
                                + absenteTable.getValueAt(i, 2) + "\n"
                        );
                    }

                    ps.close(); // inchidere fisier

                } catch (IOException ex) {
                    Mesaje.mesajExportareEsuata(this);
                }
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
     * Deschide modalul de adaugare a unei note sau a unei absente in functie
     * de tab-ul <code>catalogTabbedPane</code> selectat la apasarea butonului
     * <code>adaugaButton</code>.
     * 
     * <p>Daca tab-ul <b>Note</b> este selectat, deschide 
     * <code>AdaugaNotaDialog</code></p>
     * <p>Daca tab-ul <b>Absente</b> este selectat, deschide 
     * <code>AdaugaAbsentaDialog</code></p>
     * <p>Afiseaza un mesaj de atentionare daca nu exista nici un elev in clasa
     * selectata.</p>
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * de adaugare a unei note sau a unei absente
     * 
     * @see catalog.AdaugaNotaDialog
     * @see catalog.AdaugaAbsentaDialog
     */
    private void adaugaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaButtonActionPerformed
        // daca tabul Note este selectat
        if (catalogTabbedPane.getSelectedIndex() == 0) {
            if (elevComboBox.getSelectedItem() == null) {
                Mesaje.mesajAtentionare(this, "Atenție",
                        "Nu există nici un elev în clasa selectată!", "warning");
            } else {
                numeElevSelectat = getNumeElevSelectat(elevComboBox.getSelectedItem().toString());
                new AdaugaNotaDialog(this, true).setVisible(true); // deschide fereastra dialog de adaugare
            }
        } // daca tabul Absente este selectat 
        else if (catalogTabbedPane.getSelectedIndex() == 1) {
            if (elevComboBox.getSelectedItem() == null) {
                Mesaje.mesajAtentionare(this, "Atenție",
                        "Nu există nici un elev în clasa selectată!", "warning");
            } else {
                numeElevSelectat = getNumeElevSelectat(elevComboBox.getSelectedItem().toString());
                new AdaugaAbsentaDialog(this, true).setVisible(true); // deschide fereastra dialog de adaugare
            }
        }
    }//GEN-LAST:event_adaugaButtonActionPerformed

    /**
     * Afiseaza un mesaj de tip <code>mesajInterogareOptiune</code> cu doua 
     * butoane pentru stergerea din baza de date a notei sau a absentei 
     * selectate la apasarea <code>stergeButton</code> in functie de tab-ul 
     * <code>catalogTabbedPane</code> selectat.
     * 
     * <p>Daca tab-ul <b>Note</b> este selectat, va sterge din baza de date 
     * nota selectata.</p>
     * <p>Daca tab-ul <b>Absente</b> este selectat, va sterge din baza de date 
     * absenta selectata.</p>
     * <p>Afiseaza un mesaj de atentionare daca nu s-a selectat nici o nota sau
     * nici o absenta.</p>
     * <p>Afiseaza un mesaj de confirmare daca s-a efectuat stergerea cu succes.</p>
     * <p>Afiseaza <code>mesajFrameReconectare()</code> daca stergerea a esuat.</p>
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * de stergere a notei sau a absentei
     * 
     * @see global.Mesaje#mesajAtentionare(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
     * @see global.Mesaje#mesajInterogareOptiune(java.lang.Object, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
     * @see global.Mesaje#mesajFrameReconectare(javax.swing.JFrame, java.lang.String, java.lang.String)
     */
    private void stergeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stergeButtonActionPerformed
        // daca tabul Note este selectat
        if (catalogTabbedPane.getSelectedIndex() == 0) {
            if (noteTable.getSelectedRow() == -1) {
                Mesaje.mesajAtentionare(this, "Atenție",
                        "Nu ați selectat nici o notă!", "warning");

            } else {
                JLabel continutDialog = new JLabel("Ștergeți nota selectată?");
                continutDialog.setFont(new Font("Arial", Font.PLAIN, 24));

                int confirmareStergere = Mesaje.mesajInterogareOptiune(this,
                        "Ștergere Notă", "Ștergeți nota selectată?",
                        "Da", "Nu");

                if (confirmareStergere != 0) { // daca am apasat "NO" iesim din metoda
                    return;
                } else { // daca am apasat "YES"
                    try {
                        // obtinem id-ul randului selectat
                        int idRandSelectat = Integer.parseInt(
                                noteTable.getValueAt(noteTable.getSelectedRow(), 0).toString());

                        Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
                        Database.stmt.executeUpdate(
                                "delete from Note where id = " + idRandSelectat + ";"
                        );

                        if (true) {
                            Mesaje.mesajSucces(this, "Notă Ștearsă", "<html><b>Succes!</b><br/>Nota a fost ștearsă.</html>");
                        }

                    } catch (SQLException ex) {
                        Mesaje.mesajFrameReconectare(this, "Eroare Ștergere!",
                                "<html><b>Ștergere eșuată!</b><br/>"
                                + "Probabil s-a întrerupt conexiunea.</html>");

                    }
                }
            }
        } // daca tabul Absente este selectat 
        else if (catalogTabbedPane.getSelectedIndex() == 1) {
            if (absenteTable.getSelectedRow() == -1) {
                Mesaje.mesajAtentionare(this, "Atenție",
                        "Nu ați selectat nici o absență!", "warning");

            } else {
                int confirmareStergere = Mesaje.mesajInterogareOptiune(this,
                        "Ștergere Absență", "Ștergeți absența selectată?",
                        "Da", "Nu");

                if (confirmareStergere != 0) { // daca am apasat "NO" iesim din metoda
                    return;
                } else { // daca am apasat "YES"
                    try {
                        // obtinem id-ul randului selectat
                        int idRandSelectat = Integer.parseInt(
                                absenteTable.getValueAt(absenteTable.getSelectedRow(), 0).toString());

                        Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
                        Database.stmt.executeUpdate(
                                "delete from Absente where id = " + idRandSelectat + ";"
                        );

                        if (true) {
                            Mesaje.mesajSucces(this, "Absență Ștearsă", 
                                    "<html><b>Succes!</b><br/>Absența a fost ștearsă.</html>");
                        }

                    } catch (SQLException ex) {
                        Mesaje.mesajFrameReconectare(this, "Eroare Ștergere!",
                                "<html><b>Ștergere eșuată!</b><br/>"
                                + "Probabil s-a întrerupt conexiunea.</html>");
                    }
                }
            }
        }
    }//GEN-LAST:event_stergeButtonActionPerformed

    /**
     * Deschide modalul de modificare <code>ModificaNotaDialog</code> sau 
     * <code>ModificaAbsentaDialog</code> in functie de tab-ul 
     * <code>catalogTabbedPane</code> selectat la apasarea butonului 
     * <code>modificaButton</code>.
     * 
     * <p>Afiseaza un mesaj de atentionare daca nu s-a selectat nimic.</p>
     * <p>Daca tab-ul <b>Note</b> este selectat, va deschide modalul de
     * modificare a notei selectate.</p>
     * <p>Daca tab-ul <b>Absente</b> este selectat, va deschide modalul de
     * modificare a absentei selectate.</p>
     * 
     * @param evt indică faptul că a avut loc acțiunea de apăsare a butonului
     * de modificare a notei sau a absentei
     * 
     * @see catalog.ModificaNotaDialog
     * @see catalog.ModificaAbsentaDialog
     */
    private void modificaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificaButtonActionPerformed
        // daca tabul Note este selectat
        if (catalogTabbedPane.getSelectedIndex() == 0) {
            if (noteTable.getSelectedRow() == -1) { // daca nu avem nici o linie selectata
                Mesaje.mesajAtentionare(this, null, "Nu ați selectat nimic!", "warning");
            } else {
                new ModificaNotaDialog(this, true, noteTable).setVisible(true); // deschide fereastra dialog de actualizare
            }
        } // daca tabul Absente este selectat 
        else if (catalogTabbedPane.getSelectedIndex() == 1) {
            if (absenteTable.getSelectedRow() == -1) { // daca nu avem nici o linie selectata
                Mesaje.mesajAtentionare(this, null, "Nu ați selectat nimic!", "warning");
            } else {
                new ModificaAbsentaDialog(this, true, absenteTable).setVisible(true); // deschide fereastra dialog de actualizare
            }
        }
    }//GEN-LAST:event_modificaButtonActionPerformed

    /**
     * Readauga elevii din baza de date in <code>elevComboBox</code> in cazul
     * in care selectia din <code>clasaComboBox</code> a fost modificata si va
     * readauga notele in <code>noteTable</code> si absentele in 
     * <code>absenteTable</code> din baza de date in functie de selectiile din
     * toate combo box-urile. Va actualiza si <code>detaliiLabel</code>.
     * 
     * @param evt indica faptul ca a avut loc actiunea de modificare a starii
     * <code>clasaComboBox</code>
     */
    private void clasaComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_clasaComboBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            adaugareEleviInComboBox();

            // preluare continuturi comboBox
            numeClasaSelectata = clasaComboBox.getSelectedItem().toString();
            getIdElevSelectat();
            numeMaterieSelectata = materieComboBox.getSelectedItem().toString();

            noteTable.setModel(new NoteModelTabel()); // resetare model tabel note dupa reselectare
            absenteTable.setModel(new AbsenteModelTabel()); // resetare model tabel absente dupa reselectare

            detaliiNoteAbsente(); // reactualizare label detalii
        }
    }//GEN-LAST:event_clasaComboBoxItemStateChanged

    /**
     * Readauga notele si absentele din baza de date in <code>noteTable</code> 
     * si <code>absenteTable</code> dupa reselectarea <code>elevComboBox</code>
     * in functie de selectiile din toate combo box-urile. Va actualiza si
     * <code>detaliiLabel</code>.
     * 
     * @param evt indica faptul ca a avut loc actiunea de modificare a starii
     * <code>elevComboBox</code>
     */
    private void elevComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_elevComboBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // preluare continuturi comboBox
            numeClasaSelectata = clasaComboBox.getSelectedItem().toString();
            getIdElevSelectat();
            numeMaterieSelectata = materieComboBox.getSelectedItem().toString();

            noteTable.setModel(new NoteModelTabel()); // resetare model tabel note dupa reselectare
            absenteTable.setModel(new AbsenteModelTabel()); // resetare model tabel absente dupa reselectare

            detaliiNoteAbsente(); // reactualizare label detalii
        }
    }//GEN-LAST:event_elevComboBoxItemStateChanged
    
    /**
     * Readauga notele si absentele din baza de date in <code>noteTable</code> 
     * si <code>absenteTable</code> dupa reselectarea <code>materieComboBox</code>
     * in functie de selectiile din toate combo box-urile. Va actualiza si
     * <code>detaliiLabel</code>.
     * 
     * @param evt indica faptul ca a avut loc actiunea de modificare a starii
     * <code>clasaComboBox</code>
     */
    private void materieComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_materieComboBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // preluare continuturi comboBox
            numeClasaSelectata = clasaComboBox.getSelectedItem().toString();
            getIdElevSelectat();
            numeMaterieSelectata = materieComboBox.getSelectedItem().toString();

            noteTable.setModel(new NoteModelTabel()); // resetare model tabel note dupa reselectare
            absenteTable.setModel(new AbsenteModelTabel()); // resetare model tabel absente dupa reselectare

            detaliiNoteAbsente();
        }
    }//GEN-LAST:event_materieComboBoxItemStateChanged

    /**
     * Metoda de reimprospatare a tabelelor dupa eventuale prelucrari.
     * 
     * @param evt indică faptul că a avut loc acțiunea de obtinere a focus-ului
     * ferestrei curente
     */
    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        noteTable.setModel(new NoteModelTabel());
        absenteTable.setModel(new AbsenteModelTabel());
        // dateEleviJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        detaliiNoteAbsente();
    }//GEN-LAST:event_formWindowGainedFocus

    /**
     * Modifica textul butoanelor <code>adaugaButton</code>, 
     * <code>modificaButton</code> si <code>stergeButton</code>, cat si textul
     * informativ al <code>detaliiLabel</code> in functie de tab-ul selectat.
     * 
     * @param evt indica faptul ca a avut loc actiunea de modificare a starii
     * <code>catalogTabbedPane</code>
     */
    private void catalogTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_catalogTabbedPaneStateChanged
        // activare butoane daca primul tab e selectat
        if (catalogTabbedPane.getSelectedIndex() == 0) {
            adaugaButton.setText("Adaugă Notă");
            modificaButton.setText("Modifică Notă");
            stergeButton.setText("Șterge Notă");
            detaliiNoteAbsente();
        } // dezactivare butoane daca al doilea tab e selectat
        else if (catalogTabbedPane.getSelectedIndex() == 1) {
            adaugaButton.setText("Adaugă Absență");
            modificaButton.setText("Modifică Absență");
            stergeButton.setText("Șterge Absență");
            detaliiNoteAbsente();
        }
    }//GEN-LAST:event_catalogTabbedPaneStateChanged

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
            java.util.logging.Logger.getLogger(CatalogFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CatalogFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CatalogFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CatalogFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable absenteTable;
    private javax.swing.JButton adaugaButton;
    private javax.swing.JTabbedPane catalogTabbedPane;
    private javax.swing.JComboBox<String> clasaComboBox;
    private javax.swing.JLabel detaliiLabel;
    private javax.swing.JComboBox<String> elevComboBox;
    private javax.swing.JButton exportaDateButton;
    private javax.swing.JCheckBox fixeazaFereastraCheckBox;
    private javax.swing.JLabel infoAccesLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> materieComboBox;
    private javax.swing.JButton modificaButton;
    private javax.swing.JTable noteTable;
    private java.awt.Panel panel1;
    private javax.swing.JButton stergeButton;
    // End of variables declaration//GEN-END:variables
}
