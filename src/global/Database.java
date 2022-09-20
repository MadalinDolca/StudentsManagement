package global;

import global.Splash;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * Clasa care se conecteaza la bazele de date ce conține metodele 
 * necesare pentru conectarea si deconectarea de la bazele de date
 * si campurile pentru executarea comenzilor asupra tabelelor din bazele de
 * date.
 * 
 * @author Madalin
 */
public class Database {

    /**
     * Stabileste conexiunea necesara cu baza de date care contine tabelele cu
     * datele necesare evidentei elevilor.
     */
    public static Connection connection;
    
    /**
     * Cursorul care arata randul curent de date din tabelele cu datele 
     * necesare evidentei elevilor.
     */
    public static ResultSet rs;
    
    /**
     * Obiect utilizat pentru executarea unei instrucțiuni SQL statice și
     * returnarea rezultatelor pe care le produce din tabelele cu datele 
     * necesare evidentei elevilor.
     */
    public static Statement stmt;

    /**
     * Stabileste conexiunea necesara cu baza de date admin.
     */    
    public static Connection adminConnection;
    
    /**
     * Cursorul care arata randul curent de date din tabelele bazei de date admin.
     */    
    public static ResultSet adminRs;
    
    /**
     * Obiect utilizat pentru executarea unei instrucțiuni SQL statice și
     * returnarea rezultatelor pe care le produce din tabelele bazei de date admin.
     */
    public static Statement adminStmt;

    /**
     * Metoda de conectare la bazele de date cu credentialele necesare.
     * 
     * <p>Arata un mesaj de interogare daca conectarea la bazele de date a 
     * esuat cu optiunile de reincercare si abandonare.</p>
     * 
     * @param splashScreen obiectul care stabileste conexiunea
     */
    public static void conectare(Splash splashScreen) {
        // credentiale conectare la baza de date elevi
        String url = "jdbc:mysql://localhost:3306/evidentaelevi";
        String username = "root";
        String password = "";

        rs = null;
        stmt = null;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Baza de date conectata!");

        } catch (SQLException ex) {
            System.out.println("Nu ma pot conecta la baza de date!" + " " + ex);
            // Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            // System.out.println(ex.getMessage());

            // mesajul dialogului
            JLabel continutDialog = new JLabel(
                    "<html>Nu mă pot conecta la baza de date!<br/>"
                    + "Fie nu ai acces la server,<br/>"
                    + "fie nu ai conexiune la internet.</html>");
            continutDialog.setFont(new Font("Arial", Font.PLAIN, 20));
            String[] optiuni = {"Reîncercare", "Anulare"}; // optiunile dialogului
            UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 18))); // dimensiune font butoane

            // mesajul de reconectare la baza de date
            int optiuneAleasa = JOptionPane.showOptionDialog(splashScreen, continutDialog, "Conectare Eșuată!",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon(Splash.class.getResource("/imagini/connection.png")),
                    optiuni, optiuni[0]);

            // reconectare daca s-a apasat "Reîncercare"
            if (optiuneAleasa == 0) {
                conectare(splashScreen);
                return;

            } // inchidere program daca s-a apasat "Anulare" sau "Inchidere Fereastra" 
            else if (optiuneAleasa == 1 || optiuneAleasa == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            }
        }

        System.out.println("Incarc driver-ul...");
        try {
            connection = DriverManager.getConnection(url, username, password); // conectare la baza de date

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver incarcat!");

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        // credentiale conectare la baza de date admin
        String adminUrl = "jdbc:mysql://localhost:3306/controlpanel";
        String adminUsername = "root";
        String adminPassword = "";

        adminRs = null;
        adminStmt = null;

        try (Connection adminConnection = DriverManager.getConnection(adminUrl, adminUsername, adminPassword)) {
            System.out.println("Baza de date admin conectata!");

        } catch (SQLException ex) {
            System.out.println("Nu ma pot conecta la baza de date admin!" + " " + ex);
            // Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            // System.out.println(ex.getMessage());

            JLabel continutDialog = new JLabel(
                    "<html>Nu mă pot conecta la baza de date admin!<br/>"
                    + "Fie nu ai acces la server,<br/>"
                    + "fie nu ai conexiune la internet.</html>");
            continutDialog.setFont(new Font("Arial", Font.PLAIN, 24));
            String[] optiuni = {"Reîncercare", "Anulare"}; // optiunile dialogului
            UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 18))); // dimensiune font butoane

            // mesajul de reconectare la baza de date
            int optiuneAleasa = JOptionPane.showOptionDialog(splashScreen, continutDialog, "Conectare Eșuată!",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon(Splash.class.getResource("/imagini/connection.png")),
                    optiuni, optiuni[0]);

            // reconectare daca s-a apasat "Reîncercare"
            if (optiuneAleasa == 0) {
                conectare(splashScreen);
                return;

            } // inchidere program daca s-a apasat "Anulare" sau "Inchidere Fereastra" 
            else if (optiuneAleasa == 1 || optiuneAleasa == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            }
        }

        System.out.println("Incarc driver-ul admin...");
        try {
            adminConnection = DriverManager.getConnection(adminUrl, adminUsername, adminPassword); // conectare la baza de date

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver admin incarcat!");

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the admin driver in the classpath!", e);

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda de deconectare de la bazele de date.
     */
    public static void deconectare() {
        try {
            connection.close(); // inchidem conexiunea cu baza de date elevi
            System.out.println("Baza de date deconectata!");

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            adminConnection.close(); // inchidem conexiunea cu baza de date admin
            System.out.println("Baza de date admin deconectata!");

        } catch (SQLException ex) {
            System.out.println("Deconectarea a eșuat!");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
