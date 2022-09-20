package date_elevi;

public class IstoricModificari {

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
     * Memoreaza data la care a fost adaugat elevul.
     */
    String adaugat;
    
    /**
     * Memoreaza data la care a fost actualizat elevul.
     */
    String actualizat;

    /**
     * Atribuie parametrii campurilor <code>id</code>, <code>cnp</code>,
     * <code>nume</code>, <code>initialaTata</code>, <code>prenume</code>, 
     * <code>adaugat</code> si <code>actualizat</code>.
     * 
     * @param id ID-ul elevului din baza de date
     * @param cnp CNP-ul elevului din baza de date
     * @param nume numele elevului din baza de date
     * @param initialaTata initiala tatalui elevului din baza de date
     * @param prenume prenumele elevului din baza de date
     * @param adaugat data la care a fost adaugat elevul in baza de date
     * @param actualizat data la care a fost actualizat elevul in baza de date
     */
    public IstoricModificari(int id, String cnp, String nume, String initialaTata, String prenume, String adaugat, String actualizat) {
        this.id = id;
        this.cnp = cnp;
        this.nume = nume;
        this.initialaTata = initialaTata;
        this.prenume = prenume;
        this.adaugat = adaugat;
        this.actualizat = actualizat;
    }
}
