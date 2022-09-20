package date_elevi;

import autentificare.AutentificareFrame;
import global.Database;
import global.Mesaje;
import interfata.InterfataFrame;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Determina modelul tabelului care va contine datele elevilor din baza de date
 * si metodele necesare.
 *
 * @author Madalin
 */
public class DateEleviModelTabel extends AbstractTableModel {

    /**
     * Memoreaza datele elevilor de tip <code>DateElevi</code> din baza de date.
     */
    public ArrayList<DateElevi> dateEleviArray = new ArrayList<DateElevi>();
    
    /**
     * Memoreaza numele coloanelor tabelului care contine datele elevilor.
     */    
    String coloane[] = {"ID", "CNP", "Nume", "IT", "Prenume",
        "Sex", "Data Nașterii", "Adresă", "Număr Telefon"};

    /**
     * Va prelua vectorul returnat de catre <code>copiazaDBinArrayList()</code>
     * in <code>dateEleviArray</code>.
     */    
    public DateEleviModelTabel() {
        dateEleviArray = copiazaDBinArrayList(); // preluam array-ul care contine datele din DB
    }

    /**
     * Returneaza numarul de randuri din tabel.
     *
     * @return numarul de randuri din tabel de tip <code>int</code>
     */    
    @Override
    public int getRowCount() {
        return dateEleviArray.size();
    }

    /**
     * Returneaza numarul de coloane din tabel.
     *
     * @return numarul de coloane din tabel de tip <code>int</code>
     */    
    @Override
    public int getColumnCount() {
        return coloane.length;
    }

    /**
     * Returneaza numele coloanei de la index-ul selectat.
     *
     * @param indexColoana indexul coloanei selectate
     *
     * @return numele coloanei
     */    
    @Override
    public String getColumnName(int indexColoana) {
        return coloane[indexColoana];
    }

    /**
     * Returneaza continutul randului selectat din tabel.
     *
     * @param rowIndex index-ul randului selectat
     * @param columnIndex index-ul coloanei selectat
     *
     * @return continutul randului selectat in functie de coloana selectata
     */    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DateElevi da = dateEleviArray.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return da.id;
            case 1:
                return da.cnp;
            case 2:
                return da.nume;
            case 3:
                return da.initialaTata;
            case 4:
                return da.prenume;
            case 5:
                return da.sex;
            case 6:
                return da.dataNasterii;
            case 7:
                return da.adresa;
            case 8:
                return da.nrTelefon;
        }

        return null;
    }

    /**
     * Sterge randul selectat doar din tabel.
     *
     * @param tab tabelul oferit
     */    
    public void sterge(int[] tab) {
        for (int i = tab.length - 1; i >= 0; i--) {
            dateEleviArray.remove(tab[i]);
        }

        this.fireTableRowsDeleted(tab[0], tab[tab.length - 1]);
    }

    /**
     * Metoda pentru preluarea datelor din tabelul bazei de date si adaugarea
     * acestora in array.
     *
     * <p> Afiseaza <code>mesajFrameReconectare</code> daca preluarea datelor
     * elevilor din baza de date a esuat</p>
     *
     * @return obiectul <code>dateEleviDBArray</code>
     */
    public ArrayList<DateElevi> copiazaDBinArrayList() {
        ArrayList<DateElevi> dateEleviDBArray = new ArrayList<DateElevi>();

        try {
            Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
            Database.rs = Database.stmt.executeQuery("select * from Elevi;");

            while (Database.rs.next()) { // parcurgem fiecare linie din tabelul bazei de date
                // adaugam continutul celulelor din tabelul bazei de date in array
                dateEleviDBArray.add(new DateElevi(
                        Database.rs.getInt("id"),
                        Database.rs.getString("cnp"),
                        Database.rs.getString("nume"),
                        Database.rs.getString("initialaTata"),
                        Database.rs.getString("prenume"),
                        Database.rs.getString("sex"),
                        Database.rs.getString("dataNasterii"),
                        Database.rs.getString("adresa"),
                        Database.rs.getString("nrTelefon")));
            }

        } catch (SQLException ex) {
            try {
                InterfataFrame.dateEleviFrame.dispose();
            } catch (Exception e) {
                System.out.println("Eroare inlaturare dateEleviFrame!");
            }

            Mesaje.mesajFrameReconectare(
                    AutentificareFrame.interfataFrame, "Eroare!",
                    "<html><b>Eroare preluare date elevi!</b><br/>"
                    + "Probabil s-a întrerupt conexiunea!</html>");
        }

        return dateEleviDBArray; // returnam obiectul ArrayList<DateElevi>
    }
}
