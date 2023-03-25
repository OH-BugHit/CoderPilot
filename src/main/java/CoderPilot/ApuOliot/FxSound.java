package CoderPilot.ApuOliot;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Pelin tehosteäänet käsittelevä luokka
 */
public class FxSound {

    /**
     * Äänenvoimakkuus, Oletus 0.5
     */
    private static double volume = 0.5;

    /**
     * Vaihe. Tämä muuttuja kertoo mikä ääni soitetaan kun näppäin pysyy painettuna tai painetaan uudestaan
     */
    private static int phase = 0;

    /**
     * Getteri äänenvoimakkuudelle
     * @return Palauttaa asetetun äänenvoimakkuuden
     */
    public static double getVolume() {
        return volume;
    }

    /**
     * Getteri äänen vuorojärjestysmuuttujalle
     * @return Palauttaa vuorossa olevan numeron
     */
    public static int getPhase() {
        return phase;
    }

    /**
     * Mediaplayer äänelle
     */
    private static MediaPlayer mpSound;

    /**
     * Äänenvoimakkuuden asettaminen
     * @param Vol Äänenvoimakkuus. Arvo välille 0-1.0
     */
    public static void setVolume(double Vol) {
        volume = Vol;
    }

    /**
     * Metodi soittaa kiihdytyksen ensimmäisen vaiheen äänen
     */
    public static void thrustPlay() {
        mpSound = new MediaPlayer(new Media(new File("target/classes/Sounds/accStart.mp3").toURI().toString()));
        mpSound.setVolume(volume);
        mpSound.play();
        phase = 1;
    }

    /**
     * Metodi soittaa kiihdytyksen toisen vaiheen äänen
     */
    public static void loopPlay() {
        mpSound = new MediaPlayer(new Media(new File("target/classes/Sounds/accPohjassa.mp3").toURI().toString()));
        mpSound.setVolume(volume);
        mpSound.play();
        phase = 2;
    }

    /**
     * Metodi soittaa hidastuksen äänen
     */
    public static void stopThrust() {
        mpSound.stop();
        mpSound = new MediaPlayer(new Media(new File("target/classes/Sounds/accEnd.mp3").toURI().toString()));
        mpSound.setVolume(volume);
        mpSound.setAutoPlay(false);
        if (phase == 2) {
            mpSound.play();
            phase = 0;
        }
    }

    /**
     * Metodi asettaa soitettavan äänen vaiheen
     * @param setPhase Haluttu vaihe. Arvo alkaen 0
     */
    public static void setPhase(int setPhase) {
        phase = setPhase;
    }
}
