package date_elevi;

/**
 * Structura de memorare a datelor personale ale elevilor.
 *
 * @author Madalin
 */
public class DateElevi {

    /**
     * Memoreaza ID-ul elevului.
     */
    int id;
    
    /**
     * Memoreaza CNP-ul elevului.
     */
    String cnp;
    
    /**
     * Memoreaza numele elevului.
     */
    String nume;
    
    /**
     * Memoreaza initiala tatalui elevului.
     */
    String initialaTata;
    
    /**
     * Memoreaza prenumele elevului.
     */
    String prenume;
    
    /**
     * Memoreaza sexul elevului.
     */
    String sex;
    
    /**
     * Memoreaza data nasterii elevului.
     */
    String dataNasterii;
    
    /**
     * Memoreaza adresa elevului.
     */
    String adresa;
    
    /**
     * Memoreaza numarul de telefon al elevului.
     */
    String nrTelefon;

    /**
     * Atribuie parametrii campurilor <code>id</code>, <code>cnp</code>,
     * <code>nume</code>, <code>initialaTata</code>, <code>prenume</code>, 
     * <code>sex</code>, <code>dataNasterii</code>, <code>adresa</code> si
     * <code>nrTelefon</code>.
     * 
     * @param id ID-ul elevului din baza de date
     * @param cnp CNP-ul elevului din baza de date
     * @param nume numele elevului din baza de date
     * @param initialaTata initiala tatalui elevului din baza de date
     * @param prenume prenumele elevului din baza de date
     * @param sex sexul elevului din baza de date
     * @param dataNasterii data nasterii elevului din baza de date
     * @param adresa adresa elevului din baza de date
     * @param nrTelefon numarul de telefon al elevului din baza de date
     */
    public DateElevi(int id, String cnp, String nume, String initialaTata, String prenume, String sex, String dataNasterii, String adresa, String nrTelefon) {
        this.id = id;
        this.cnp = cnp;
        this.nume = nume;
        this.initialaTata = initialaTata;
        this.prenume = prenume;
        this.sex = sex;
        this.dataNasterii = dataNasterii;
        this.adresa = adresa;
        this.nrTelefon = nrTelefon;
    }
}
