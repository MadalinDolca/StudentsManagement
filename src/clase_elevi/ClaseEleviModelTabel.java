package clase_elevi;

import autentificare.AutentificareFrame;
import global.Database;
import global.Mesaje;
import interfata.InterfataFrame;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Determina modelul tabelului care va contine elevi care apartin clasei din 
 * baza de date si metodele necesare.
 *
 * @author Madalin
 */
public class ClaseEleviModelTabel extends AbstractTableModel {

    /**
     * Memoreaza elevii din clasa de tip <code>ClaseElevi</code> din baza de date.
     */    
    public ArrayList<ClaseElevi> claseEleviArray = new ArrayList<ClaseElevi>();
    
    /**
     * Memoreaza numele coloanelor tabelului care contine elevii din clasa.
     */    
    String coloane[] = {"ID", "Nume", "Inițială Tată", "Prenume"};

    /**
     * Va prelua vectorul returnat de catre <code>copiazaDBinArrayList()</code>
     * in <code>claseEleviArray</code>.
     */
    public ClaseEleviModelTabel() {
        claseEleviArray = copiazaDBinArrayList(); // preluam array-ul care contine datele din DB
    }

    /**
     * Returneaza numarul de randuri din tabel.
     *
     * @return numarul de randuri din tabel de tip <code>int</code>
     */
    @Override
    public int getRowCount() {
        return claseEleviArray.size();
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
        ClaseElevi ce = claseEleviArray.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return ce.id;
            case 1:
                return ce.nume;
            case 2:
                return ce.initialaTata;
            case 3:
                return ce.prenume;
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
            claseEleviArray.remove(tab[i]);
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
     * @return obiectul <code>claseEleviDBArray</code>
     */
    public ArrayList<ClaseElevi> copiazaDBinArrayList() {
        ArrayList<ClaseElevi> claseEleviDBArray = new ArrayList<ClaseElevi>();

        try {
            Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
            Database.rs = Database.stmt.executeQuery(
                    "select Elevi.id, Elevi.nume, Elevi.initialaTata, Elevi.prenume from Elevi "
                    + "inner join Clase on Elevi.idClasa = Clase.id where Clase.nume = '"
                    + ClaseEleviFrame.numeClasaSelectata + "';");

            while (Database.rs.next()) { // parcurgem fiecare linie din tabelul bazei de date
                // adaugam continutul celulelor din tabelul bazei de date in array
                claseEleviDBArray.add(new ClaseElevi(
                        Database.rs.getInt("id"),
                        Database.rs.getString("nume"),
                        Database.rs.getString("initialaTata"),
                        Database.rs.getString("prenume")));
            }

        } catch (SQLException ex) {
            try {
                InterfataFrame.claseEleviFrame.dispose();
            } catch (Exception e) {
                System.out.println("Eroare inlaturare claseEleviFrame!");
            }

            Mesaje.mesajFrameReconectare(
                    AutentificareFrame.interfataFrame, "Eroare!",
                    "<html><b>Eroare preluare clase elevi!</b><br/>"
                    + "Probabil s-a întrerupt conexiunea!</html>");
        }

        return claseEleviDBArray; // returnam obiectul ArrayList<ClaseElevi>
    }
}
