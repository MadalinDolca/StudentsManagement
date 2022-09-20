package global;

import java.sql.SQLException;

/**
 * Contine valorile predefinite sau cele preluate din baza de date folosite in
 * diverse locuri in aplicatie.
 * 
 * @author Madalin
 */
public class Setari {

    // setari predefinite
    public static String icon = "/imagini/icon.png";
    public static String numarVersiune = "v1.0";
    public static String numarVersiuneCurenta = "v1.0";
    public static String mesajRestrictionare = "Nu ai dreptul de a folosi această aplicație!";
    public static String temaLookAndFeel = "Windows"; // Metal, Nimbus, CDE/Motif, Windows, Windows Classic, Darcula
    public static String melodie = "null";
    public static String credit = "Gloriously developed by Ca Băieții INC.";

    /**
     * Modificarea setarilor in functie de cele din baza de date.
     */
    public static void setariGlobale() {
        try {
            Database.adminStmt = Database.adminConnection.createStatement(); // obiect de trimitere a statement-urilor spre baza de date
            Database.adminRs = Database.adminStmt.executeQuery("select * from Setari;");

            while (Database.adminRs.next()) {
                numarVersiune = Database.adminRs.getString("numarVersiune");
                numarVersiuneCurenta = Database.adminRs.getString("numarVersiuneCurenta");
                mesajRestrictionare = Database.adminRs.getString("mesajRestrictionare");
                temaLookAndFeel = Database.adminRs.getString("temaLookAndFeel");
                melodie = Database.adminRs.getString("melodie");
                credit = Database.adminRs.getString("credit");
            }

        } catch (SQLException ex) {
            System.out.println("Eroare executare setări globale!");
        }
    }
}
