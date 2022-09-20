package catalog;

import autentificare.AutentificareFrame;
import global.Database;
import global.Mesaje;
import interfata.InterfataFrame;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Determina modelul tabelului care va contine absentele din baza de date si
 * metodele necesare.
 *
 * @author Madalin
 */
public class AbsenteModelTabel extends AbstractTableModel {

    /**
     * Memoreaza absentele de tip <code>Absente</code> din baza de date.
     */
    public ArrayList<Absente> absenteArray = new ArrayList<Absente>();

    /**
     * Memoreaza numele coloanelor tabelului care contine absentele.
     */
    String coloane[] = {"ID", "Dată", "Motivată / Nemotivată"};

    /**
     * Va prelua vectorul returnat de catre <code>copiazaDBinArrayList()</code>
     * in <code>absenteArray</code>.
     */
    public AbsenteModelTabel() {
        absenteArray = copiazaDBinArrayList(); // preluam array-ul care contine datele din DB
    }

    /**
     * Returneaza numarul de randuri din tabel.
     *
     * @return numarul de randuri din tabel de tip <code>int</code>
     */
    @Override
    public int getRowCount() {
        return absenteArray.size();
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
        Absente a = absenteArray.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return a.id;
            case 1:
                return a.data;
            case 2:
                return a.tipAbsenta;
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
            absenteArray.remove(tab[i]);
        }

        this.fireTableRowsDeleted(tab[0], tab[tab.length - 1]);
    }

    /**
     * Metoda pentru preluarea datelor din tabelul bazei de date si adaugarea 
     * acestora in array.
     * 
     * <p>Afiseaza <code>mesajFrameReconectare</code> daca preluarea bursierilor
     * din baza de date a esuat</p>
     * 
     * @return obiectul <code>absenteDBArray</code>
     */
    public ArrayList<Absente> copiazaDBinArrayList() {
        ArrayList<Absente> absenteDBArray = new ArrayList<Absente>();

        try {
            Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
            Database.rs = Database.stmt.executeQuery(
                    "select Absente.id, Absente.data, Absente.tipAbsenta from Absente "
                    + "inner join Elevi on Absente.idElev = Elevi.id "
                    + "inner join Materii on Absente.idMaterie = Materii.id where "
                    + "Elevi.id = '" + CatalogFrame.idElevSelectat + "' and "
                    + "Materii.nume = '" + CatalogFrame.numeMaterieSelectata + "';"
            );

            while (Database.rs.next()) { // parcurgem fiecare linie din tabelul bazei de date
                // adaugam continutul celulelor din tabelul bazei de date in array
                absenteDBArray.add(new Absente(
                        Database.rs.getInt("id"),
                        Database.rs.getString("data"),
                        Database.rs.getString("tipAbsenta")));
            }

        } catch (SQLException ex) {
            try {
                InterfataFrame.catalogFrame.dispose();
            } catch (Exception e) {
                System.out.println("Eroare inlaturare catalogFrame!");
            }

            Mesaje.mesajFrameReconectare(
                    AutentificareFrame.interfataFrame, "Eroare!",
                    "<html><b>Eroare preluare absențe!</b><br/>"
                    + "Probabil s-a întrerupt conexiunea!</html>");
        }

        return absenteDBArray; // returnam obiectul ArrayList<Absente>
    }
}
