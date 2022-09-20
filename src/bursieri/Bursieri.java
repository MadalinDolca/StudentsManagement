package bursieri;

/**
 * Structura de memorare a datelor bursierilor.
 *
 * @author Madalin
 */
public class Bursieri {

    /**
     * Memoreaza ID-ul elevului.
     */
    int id;

    /**
     * Memoreaza numele elevului.
     */
    String nume;

    /**
     * Memoreaza clasa din care face parte elevul.
     */
    String clasa;

    /**
     * Atribuie parametrii campurilor <code>id</code>, <code>nume</code> si
     * <code>clasa</code>.
     *
     * @param id ID-ul elevului din baza de date
     * @param nume numele elevului din baza de date
     * @param clasa clasa din care face parte elevul din baza de date
     */
    public Bursieri(int id, String nume, String clasa) {
        this.id = id;
        this.nume = nume;
        this.clasa = clasa;
    }
}
