package catalog;

import autentificare.AutentificareFrame;
import global.Database;
import global.Mesaje;
import interfata.InterfataFrame;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Determina modelul tabelului care va contine notele din baza de date si
 * metodele necesare.
 *
 * @author Madalin
 */
public class NoteModelTabel extends AbstractTableModel {

    /**
     * Memoreaza notele de tip <code>Note</code> din baza de date.
     */
    public ArrayList<Note> noteArray = new ArrayList<Note>();

    /**
     * Memoreaza numele coloanelor tabelului care contine notele.
     */
    String coloane[] = {"ID", "Notă", "Dată", "Tip Notă"};

    /**
     * Va prelua vectorul returnat de catre <code>copiazaDBinArrayList()</code>
     * in <code>noteArray</code>.
     */
    public NoteModelTabel() {
        noteArray = copiazaDBinArrayList(); // preluam array-ul care contine datele din DB
    }

    /**
     * Returneaza numarul de randuri din tabel.
     *
     * @return numarul de randuri din tabel de tip <code>int</code>
     */
    @Override
    public int getRowCount() {
        return noteArray.size();
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
        Note n = noteArray.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return n.id;
            case 1:
                return n.nota;
            case 2:
                return n.data;
            case 3:
                return n.tipNota;
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
            noteArray.remove(tab[i]);
        }

        this.fireTableRowsDeleted(tab[0], tab[tab.length - 1]);
    }

    /**
     * Metoda pentru preluarea datelor din tabelul bazei de date si adaugarea
     * acestora in array.
     *
     * <p> Afiseaza <code>mesajFrameReconectare</code> daca preluarea bursierilor
     * din baza de date a esuat</p>
     *
     * @return obiectul <code>noteDBArray</code>
     */
    public ArrayList<Note> copiazaDBinArrayList() {
        ArrayList<Note> noteDBArray = new ArrayList<Note>();

        try {
            Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
            Database.rs = Database.stmt.executeQuery(
                    "select Note.id, Note.nota, Note.data, Note.tipNota from Note "
                    + "inner join Elevi on Note.idElev = Elevi.id "
                    + "inner join Materii on Note.idMaterie = Materii.id where "
                    + "Elevi.id = '" + CatalogFrame.idElevSelectat + "' and "
                    + "Materii.nume = '" + CatalogFrame.numeMaterieSelectata + "';"
            );

            while (Database.rs.next()) { // parcurgem fiecare linie din tabelul bazei de date
                // adaugam continutul celulelor din tabelul bazei de date in array
                noteDBArray.add(new Note(
                        Database.rs.getInt("id"),
                        Database.rs.getFloat("nota"),
                        Database.rs.getString("data"),
                        Database.rs.getString("tipNota")));
            }

        } catch (SQLException ex) {
            try {
                InterfataFrame.catalogFrame.dispose();
            } catch (Exception e) {
                System.out.println("Eroare inlaturare catalogFrame!");
            }

            Mesaje.mesajFrameReconectare(
                    AutentificareFrame.interfataFrame, "Eroare!",
                    "<html><b>Eroare preluare note!</b><br/>"
                    + "Probabil s-a întrerupt conexiunea!</html>");
        }

        return noteDBArray; // returnam obiectul ArrayList<Note>
    }
}
