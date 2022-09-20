package date_elevi;

import autentificare.AutentificareFrame;
import global.Database;
import global.Mesaje;
import interfata.InterfataFrame;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Determina modelul tabelului care va contine istoricul modificarilor datelor
 * elevilor din baza de date si metodele necesare.
 *
 * @author Madalin
 */
public class IstoricModificariModelTabel extends AbstractTableModel {

    /**
     * Memoreaza datele elevilor de tip <code>IstoricModificari</code> din baza 
     * de date.
     */
    public ArrayList<IstoricModificari> istoricModificariArray = new ArrayList<IstoricModificari>();
    
    /**
     * Memoreaza numele coloanelor tabelului care contine istoricul 
     * modificarilor datelor elevilor.
     */  
    String coloane[] = {"ID", "CNP", "Nume", "IT", "Prenume", "Adăugat", "Actualizat"};

    /**
     * Va prelua vectorul returnat de catre <code>copiazaDBinArrayList()</code>
     * in <code>istoricModificariArray</code>.
     */      
    public IstoricModificariModelTabel() {
        istoricModificariArray = copiazaDBinArrayList(); // preluam array-ul care contine datele din DB
    }

    /**
     * Returneaza numarul de randuri din tabel.
     *
     * @return numarul de randuri din tabel de tip <code>int</code>
     */      
    @Override
    public int getRowCount() {
        return istoricModificariArray.size();
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
        IstoricModificari da = istoricModificariArray.get(rowIndex);

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
                return da.adaugat;
            case 6:
                return da.actualizat;
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
            istoricModificariArray.remove(tab[i]);
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
     * @return obiectul <code>istoricModificariDBArray</code>
     */
    public ArrayList<IstoricModificari> copiazaDBinArrayList() {
        ArrayList<IstoricModificari> istoricModificariDBArray = new ArrayList<IstoricModificari>();

        try {
            Database.stmt = Database.connection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
            Database.rs = Database.stmt.executeQuery("select id, cnp, nume, initialaTata, prenume, creat, actualizat from Elevi;");

            while (Database.rs.next()) { // parcurgem fiecare linie din tabelul bazei de date
                // adaugam continutul celulelor din tabelul bazei de date in array
                istoricModificariDBArray.add(new IstoricModificari(
                        Database.rs.getInt("id"),
                        Database.rs.getString("cnp"),
                        Database.rs.getString("nume"),
                        Database.rs.getString("initialaTata"),
                        Database.rs.getString("prenume"),
                        Database.rs.getString("creat"),
                        Database.rs.getString("actualizat")));
            }

        } catch (SQLException ex) {
            try {
                InterfataFrame.dateEleviFrame.dispose();
            } catch (Exception e) {
                System.out.println("Eroare inlaturare dateEleviFrame!");
            }

            Mesaje.mesajFrameReconectare(
                    AutentificareFrame.interfataFrame, "Eroare!",
                    "<html><b>Eroare preluare istoric modificări!</b><br/>"
                    + "Probabil s-a întrerupt conexiunea!</html>");
        }

        return istoricModificariDBArray; // returnam obiectul ArrayList<IstoricModificari>
    }
}
