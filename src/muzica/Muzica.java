package muzica;

import global.Setari;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Clasa principală din pachetul <code>muzica</code> care conține toate
 * metodele necesare pentru controlarea muzicii.
 * 
 * @author Madalin
 */
public class Muzica {

    /**
     * Variabila ale cărei date audio pot fi încărcate înainte de redare.
     */
    Clip clip;

    /**
     * Reda fisierul muzical din locatia precizata incepand de la primul cadru.
     */
    public void playMusic() {
        // locatia fisierului in proiect cu numele melodiei specificat in Setari
        URL melodieURL = getClass().getResource(global.Setari.melodie + ".wav");
        // URL melodieURL = Muzica.class.getResource("Ranbu no Melody.wav");

        try {
            // setare fisier
            AudioInputStream sound = AudioSystem.getAudioInputStream(melodieURL);
            clip = AudioSystem.getClip();
            clip.open(sound);

            // play
            clip.setFramePosition(0);
            clip.start();

        } catch (Exception ex) {
            System.out.println("Eroare muzică!");
        }
    }

    /**
     * Repeta in continuu melodia curenta.
     */
    public void loopMusic() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Opreste melodia redata in momentul curent.
     */
    public void stopMusic() {
        if (!Setari.melodie.equals("null")) {
            clip.stop();
            clip.close();
        }
    }
}
