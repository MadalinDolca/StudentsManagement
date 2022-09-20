package bursieri;

import autentificare.AutentificareFrame;
import global.Database;
import global.Mesaje;
import interfata.InterfataFrame;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Determina modelul tabelului care va contine bursierii din baza de date si
 * metodele necesare.
 * 
 * @author Madalin
 */
public class BursieriModelTabel extends AbstractTableModel {

    /**
     * Memoreaza bursierii de tip <code>Bursieri</code> din baza de date.
     */
    public ArrayList<Bursieri> bursieriArray = new ArrayList<Bursieri>();
    
    /**
     * Memoreaza numele coloanelor tabelului care contine bursierii.
     */
    String coloane[] = {"ID", "Nume Elev", "Clasa"};

    /**
     * Va prelua vectorul returnat de catre <code>copiazaDBinArrayList()</code>
     * in <code>bursieriArray</code>.
     */
    public BursieriModelTabel() {
        bursieriArray = copiazaDBinArrayList();
    }
    
    /**
     * Returneaza numarul de randuri din tabel.
     * 
     * @return numarul de randuri din tabel de tip <code>int</code>
     */
    @Override
    public int getRowCount() {
        return bursieriArray.size();
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
        Bursieri b = bursieriArray.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return b.id;
            case 1:
                return b.nume;
            case 2:
                return b.clasa;
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
            bursieriArray.remove(tab[i]);
        }

        this.fireTableRowsDeleted(tab[0], tab[tab.length - 1]);
    }

    /**
     * Metoda pentru preluarea datelor din tabelul bazei de date si adaugarea 
     * acestora in array.
     * 
     * <p>Afiseaza <code>mesajFrameReconectare</code> daca preluarea bursierilor
     * din baza de date a esuat.</p>
     * 
     * @return obiectul <code>bursieriDBArray</code>
     */
    public ArrayList<Bursieri> copiazaDBinArrayList() {
        ArrayList<Bursieri> bursieriDBArray = new ArrayList<Bursieri>();

        try {
            Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
            Database.rs = Database.stmt.executeQuery(
                    "select Elevi.id, Elevi.nume, Elevi.initialaTata, Elevi.prenume, Clase.nume from Elevi "
                    + "inner join Clase on Elevi.idClasa = Clase.id "
                    + "inner join Burse on Elevi.idBursa = Burse.id "
                    + "where Burse.tipBursa = '" + BursieriFrame.tipBursaSelectata + "';"
            );

            while (Database.rs.next()) { // parcurgem fiecare linie din tabelul bazei de date
                // adaugam continutul celulelor din tabelul bazei de date in array
                bursieriDBArray.add(new Bursieri(
                        Database.rs.getInt("id"),
                        Database.rs.getString("Elevi.nume")
                        + " " + Database.rs.getString("initialaTata")
                        + " " + Database.rs.getString("prenume"),
                        Database.rs.getString("Clase.nume")));
            }

        } catch (SQLException ex) {
            try {
                InterfataFrame.bursieriFrame.dispose();
            } catch (Exception e) {
                System.out.println("Eroare inlaturare bursieriFrame!");
            }

            Mesaje.mesajFrameReconectare(
                    AutentificareFrame.interfataFrame, "Eroare!",
                    "<html><b>Eroare preluare bursieri!</b><br/>"
                    + "Probabil s-a întrerupt conexiunea!</html>");
        }

        return bursieriDBArray; // returnam obiectul ArrayList<Bursieri>
    }
}
