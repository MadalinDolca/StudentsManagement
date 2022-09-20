package global;

import static autentificare.AutentificareFrame.interfataFrame;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * Contine metodele care se ocupa de afisarea a diferitor mesaje.
 * 
 * @author Madalin
 */
public class Mesaje {

    /**
     * Afiseaza un mesaj de eroare si reconectare la baza de date pentru 
     * ecranul de autentificare care extinde <code>JFrame</code>.
     * 
     * @param <Clasa> orice clasa care extinde clasa <code>JFrame</code>
     * @param obiect componenta parinte in care se va afisa mesajul
     * @param titlu titlul ferestrei care contine mesajul
     * @param continut continutul mesajului de afisat
     */
    public static <Clasa extends javax.swing.JFrame>
            void mesajAutentificareReconectare(Clasa obiect, String titlu, String continut) {

        // continut implicit in cazul in care nu sunt oferite informatii
        if (titlu == null || titlu.isEmpty()) {
            titlu = "Eroare!";
        }

        if (continut == null || continut.isEmpty()) {
            continut = "Probabil s-a întrerupt conexiunea!";
        }

        JLabel continutDialog = new JLabel(continut);
        continutDialog.setFont(new Font("Arial", Font.PLAIN, 24));
        String[] optiuniEsuare = {"Reîncercare", "Închidere aplicație"}; // optiunile dialogului
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 18))); // dimensiune font butoane

        int optiuneAleasa = JOptionPane.showOptionDialog(
                (Component) obiect, continutDialog, titlu,
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                new ImageIcon(obiect.getClass().getResource("/imagini/error.png")),
                optiuniEsuare, optiuniEsuare[0]);

        // reconectare daca s-a apasat "Reîncercare"
        if (optiuneAleasa == 0) {
            obiect.setVisible(false);
            Splash.splashFrame.setVisible(true);

            Database.conectare(Splash.splashFrame);

            obiect.setVisible(true);
            Splash.splashFrame.dispose();
        } // inchidere program daca s-a apasat "Închidere aplicație" sau "Inchidere Fereastra" 
        else if (optiuneAleasa == 1 || optiuneAleasa == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Afiseaza un mesaj de eroare si reconectare la baza de date pentru orice 
     * clasa care extinde <code>JFrame</code>.
     * 
     * @param <Clasa> orice clasa care extinde clasa <code>JFrame</code>
     * @param obiect componenta parinte in care se va afisa mesajul
     * @param titlu titlul ferestrei care contine mesajul
     * @param continut continutul mesajului de afisat
     */
    public static <Clasa extends javax.swing.JFrame>
            void mesajFrameReconectare(Clasa obiect, String titlu, String continut) {

        // continut implicit in cazul in care nu sunt oferite informatii
        if (titlu == null || titlu.isEmpty()) {
            titlu = "Eroare!";
        }

        if (continut == null || continut.isEmpty()) {
            continut = "Probabil s-a întrerupt conexiunea!";
        }

        JLabel continutDialog = new JLabel(continut);
        continutDialog.setFont(new Font("Arial", Font.PLAIN, 24));
        String[] optiuniEsuare = {"Reîncercare", "Închidere aplicație"}; // optiunile dialogului
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 18))); // dimensiune font butoane

        int optiuneAleasa = JOptionPane.showOptionDialog(
                (Component) obiect, continutDialog, titlu,
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                new ImageIcon(obiect.getClass().getResource("/imagini/error.png")),
                optiuniEsuare, optiuniEsuare[0]);

        // reconectare daca s-a apasat "Reîncercare"
        if (optiuneAleasa == 0) {
            interfataFrame.setVisible(false);
            obiect.setVisible(false);
            Splash.splashFrame.setVisible(true);

            Database.conectare(Splash.splashFrame);

            interfataFrame.setVisible(true);
            obiect.setVisible(true);
            Splash.splashFrame.dispose();
        } // inchidere program daca s-a apasat "Închidere aplicație" sau "Inchidere Fereastra" 
        else if (optiuneAleasa == 1 || optiuneAleasa == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Afiseaza un mesaj de eroare si reconectare la baza de  date pentru orice
     * clasa care extinde <code>JDialog</code>.
     * 
     * @param <Clasa> orice clasa care extinde clasa <code>JDialog</code>
     * @param obiect componenta parinte in care se va afisa mesajul
     * @param titlu titlul ferestrei care contine mesajul
     * @param continut continutul mesajului de afisat
     */        
    public static <Clasa extends javax.swing.JDialog>
            void mesajDialogReconectare(Clasa obiect, String titlu, String continut) {

        // continut implicit in cazul in care nu sunt oferite informatii
        if (titlu == null || titlu.isEmpty()) {
            titlu = "Eroare!";
        }

        if (continut == null || continut.isEmpty()) {
            continut = "Probabil s-a întrerupt conexiunea!";
        }

        JLabel continutDialog = new JLabel(continut);
        continutDialog.setFont(new Font("Arial", Font.PLAIN, 24));
        String[] optiuniEsuare = {"Reîncercare", "Închidere aplicație"}; // optiunile dialogului
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 18))); // dimensiune font butoane

        int optiuneAleasa = JOptionPane.showOptionDialog(
                (Component) obiect, continutDialog, titlu,
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                new ImageIcon(obiect.getClass().getResource("/imagini/error.png")),
                optiuniEsuare, optiuniEsuare[0]);

        // reconectare daca s-a apasat "Reîncercare"
        if (optiuneAleasa == 0) {
            interfataFrame.setVisible(false);
            obiect.setVisible(false);
            Splash.splashFrame.setVisible(true);

            Database.conectare(Splash.splashFrame);

            interfataFrame.setVisible(true);
            obiect.setVisible(true);
            Splash.splashFrame.dispose();
        } // inchidere program daca s-a apasat "Închidere aplicație" sau "Inchidere Fereastra" 
        else if (optiuneAleasa == 1 || optiuneAleasa == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Afiseaza un mesaj dialog de atentionare.
     * 
     * @param <Clasa> orice clasa
     * @param obiect componenta parinte in care se va afisa mesajul
     * @param titlu titlul ferestrei care contine mesajul
     * @param continut continutul mesajului de afisat
     * @param tipMesaj tipul de mesaj care va determina iconita din continutul
     * mesajului
     */
    public static <Clasa> void mesajAtentionare(Clasa obiect, String titlu, String continut, String tipMesaj) {
        // continut implicit in cazul in care nu sunt oferite informatii
        if (titlu == null || titlu.isEmpty()) {
            titlu = "Atenție!";
        }

        if (continut == null || continut.isEmpty()) {
            continut = "Ceva nu e bine!";
        }

        JLabel continutDialog = new JLabel(continut);
        continutDialog.setFont(new Font("Arial", Font.PLAIN, 24));
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 18))); // dimensiune font butoane

        JOptionPane.showMessageDialog((Component) obiect, continutDialog, titlu,
                JOptionPane.WARNING_MESSAGE,
                new ImageIcon(obiect.getClass().getResource("/imagini/" + tipMesaj + ".png")));
    }

    /**
     * Afiseaza un mesaj dialog daca exportarea datelor din tabel in fisier 
     * a esuat.
     * 
     * @param <Clasa> orice clasa
     * @param obiect componenta parinte in care se va afisa mesajul
     */
    public static <Clasa> void mesajExportareEsuata(Clasa obiect) {
        JLabel continutDialog = new JLabel("Exportare eșuată!");
        continutDialog.setFont(new Font("Arial", Font.PLAIN, 24));
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 18))); // dimensiune font butoane

        JOptionPane.showMessageDialog((Component) obiect, continutDialog, "Eroare!",
                JOptionPane.WARNING_MESSAGE,
                new ImageIcon(obiect.getClass().getResource("/imagini/error.png")));
    }

    /**
     * Afiseaza un mesaj dialog daca actiunea s-a incheiat cu succes.
     * 
     * @param <Clasa> orice clasa
     * @param obiect componenta parinte in care se va afisa mesajul
     * @param titlu titlul ferestrei care contine mesajul
     * @param continut continutul mesajului de afisat
     */
    public static <Clasa> void mesajSucces(Clasa obiect, String titlu, String continut) {
        // continut implicit in cazul in care nu sunt oferite informatii
        if (titlu == null || titlu.isEmpty()) {
            titlu = "Succes!";
        }

        if (continut == null || continut.isEmpty()) {
            continut = "Acțiunea s-a încheiat cu succes!";
        }

        JLabel continutDialog = new JLabel(continut);
        continutDialog.setFont(new Font("Arial", Font.PLAIN, 24));
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 18))); // dimensiune font butoane

        JOptionPane.showMessageDialog((Component) obiect, continutDialog, titlu,
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(obiect.getClass().getResource("/imagini/success.png")));
    }

    /**
     * Afiseaza un mesaj dialog cu doua optiuni pentru interogarea unei actiuni.
     * 
     * @param <Clasa> orice clasa
     * @param obiect componenta parinte in care se va afisa mesajul
     * @param titlu titlul ferestrei care contine mesajul
     * @param continut continutul mesajului de afisat
     * @param optiune1 numele primei optiuni
     * @param optiune2 numele celei de a doua optiune
     * 
     * @return valoarea de tip <code>int</code> a optiunii alese (inchiderea
     * ferestrei are valoarea -1, prima optiune are valoarea 0, cea de a doua
     * optiune are valoarea 1)
     */
    public static <Clasa> int mesajInterogareOptiune(Clasa obiect, String titlu, String continut, String optiune1, String optiune2) {
        // continut implicit in cazul in care nu sunt oferite informatii
        if (titlu == null || titlu.isEmpty()) {
            titlu = "Interogare";
        }

        if (continut == null || continut.isEmpty()) {
            continut = "Ștergeți câmpul selectat?";
        }

        JLabel continutDialog = new JLabel(continut);
        continutDialog.setFont(new Font("Arial", Font.PLAIN, 24));
        String[] optiuni = {optiune1, optiune2}; // optiunile dialogului
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 18))); // dimensiune font butoane

        // returnare optiune aleasa
        return JOptionPane.showOptionDialog((Component) obiect, continutDialog, titlu,
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                new ImageIcon(obiect.getClass().getResource("/imagini/question.png")),
                optiuni, optiuni[0]);
    }
}
