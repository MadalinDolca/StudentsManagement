package catalog;

/**
 * Structura de memorare a datelor notelor.
 *
 * @author Madalin
 */
public class Note {

    /**
     * Memoreaza ID-ul notei.
     */
    int id;

    /**
     * Memoreaza valoarea nota.
     */
    float nota;

    /**
     * Memoreaza data la care a fost adaugata nota.
     */
    String data;

    /**
     * Memoreaza tipul notei.
     */
    String tipNota;

    /**
     * Atribuie parametrii campurilor <code>id</code>, <code>nota</code>,
     * <code>data</code> si <code>tipNota</code>.
     * 
     * @param id ID-ul notei din baza de date
     * @param nota valoarea nota din baza de date
     * @param data data la care a fost adaugata nota din baza de date
     * @param tipNota tipul notei din baza de date
     */
    public Note(int id, float nota, String data, String tipNota) {
        this.id = id;
        this.nota = nota;
        this.data = data;
        this.tipNota = tipNota;
    }
}
