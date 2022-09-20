package clase_elevi;

/**
 * Structura de memorare a elevilor din clase.
 *
 * @author Madalin
 */
public class ClaseElevi {

    /**
     * Memoreaza ID-ul elevului din clasa.
     */
    int id;

    /**
     * Memoreaza numele elevului din clasa.
     */
    String nume;

    /**
     * Memoreaza initiala tatalui elevului din clasa.
     */
    String initialaTata;

    /**
     * Memoreaza prenumele elevului din clasa.
     */
    String prenume;

    /**
     * Atribuie parametrii campurilor <code>id</code>, <code>nume</code>,
     * <code>initialaTata</code> si <code>prenume</code>.
     * 
     * @param id ID-ul elevului din clasa
     * @param nume numele elevului din clasa
     * @param initialaTata initiala tatalui elevului din clasa
     * @param prenume prenumele elevului din clasa
     */
    public ClaseElevi(int id, String nume, String initialaTata, String prenume) {
        this.id = id;
        this.nume = nume;
        this.initialaTata = initialaTata;
        this.prenume = prenume;
    }
}
