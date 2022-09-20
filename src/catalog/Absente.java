package catalog;

/**
 * Structura de memorare a datelor absentelor.
 *
 * @author Madalin
 */
public class Absente {

    /**
     * Memoreaza ID-ul absentei.
     */
    int id;

    /**
     * Memoreaza data absentei.
     */
    String data;

    /**
     * Memoreaza tipul absentei.
     */
    String tipAbsenta;

    /**
     * Atribuie parametrii campurilor <code>id</code>, <code>data</code> si
     * <code>tipAbsenta</code>.
     * 
     * @param id ID-ul bursei din baza de date
     * @param data data absentei din baza de date
     * @param tipAbsenta tipul absentei din baza de date
     */
    public Absente(int id, String data, String tipAbsenta) {
        this.id = id;
        this.data = data;
        this.tipAbsenta = tipAbsenta;
    }
}
