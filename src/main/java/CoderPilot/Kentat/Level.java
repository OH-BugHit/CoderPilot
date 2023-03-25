package CoderPilot.Kentat;

import CoderPilot.Rajapinnat.LevelsForPixelReaders;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Luokka kentän asettamiseen ja kentän kuvien käsittelemiseen
 * @author Olli Hilke
 */
public class Level implements LevelsForPixelReaders {

    /**
     * Kenttä jota käsitellään
     */
    private int level;

    /**
     * Viimeinen kenttä
     */
    public final int MAXLEVEL = 3;

    /**
     * Kenttä1 kuva
     */
    private final Image I_KENTTA1 = new Image(new FileInputStream("target/classes/Kentat/kentta1.png"));

    /**
     * Kentän1 "Spots" -kuva. Kuva sisältää kentän toiminnalliset pisteet valituin värein joita käytetään PointSpots -luokassa
     */
    private final Image I_KENTTA_1_SPOTS = new Image(new FileInputStream("target/classes/Kentat/kentta1_spots.png"));

    /**
     * Kenttä2 kuva
     */
    private final Image I_KENTTA_2 = new Image(new FileInputStream("target/classes/Kentat/kentta2.png"));

    /**
     * Kentän2 "Spots" -kuva. Kuva sisältää kentän toiminnalliset pisteet valituin värein joita käytetään PointSpots -luokassa
     */
    private final Image I_KENTTA_2_SPOTS = new Image(new FileInputStream("target/classes/Kentat/kentta2_spots.png"));

    /**
     * Kenttä3 kuva
     */
    private final Image I_KENTTA3 = new Image(new FileInputStream("target/classes/Kentat/kentta3.png"));

    /**
     * Kentän3 "Spots" -kuva. Kuva sisältää kentän toiminnalliset pisteet valituin värein joita käytetään PointSpots -luokassa
     */
    private final Image I_KENTTA_3_SPOTS = new Image(new FileInputStream("target/classes/Kentat/kentta3_spots.png"));


    /**
     * Luokan "kenttä" -alustaja
     * @throws FileNotFoundException Kuvatiedostoa ei löydy
     */
    public Level() throws FileNotFoundException {
    }

    /**
     * Kentän kuvan hakeminen aluksen PixelReadereita varten
     * @param leveli Halutun kentän numero
     * @return Palauttaa kentän kuvan
     */
    public Image getIKentta(int leveli) {
        if (leveli == 1){
            return I_KENTTA1;
        } else if (leveli == 2) {
            return I_KENTTA_2;
        } else if (leveli == 3) {
            return I_KENTTA3;
        }else {return null;}
    }

    /**
     * Luokan "kenttä" setteri levelille
     * @param level käytössä oleva kenttä. Tämän mukaan kenttäluokka palauttaa asioita
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Metodi kentän toiminnallisten pisteiden sisältävän kuvan palauttamiseen pixelReaderia varten
     * @return Palauttaa valitun kentän spots-kuvan
     */
    public Image getKentta_Spots() {
        if (this.level == 1){
            return I_KENTTA_1_SPOTS;
        } else if (this.level == 2){
            return I_KENTTA_2_SPOTS;
        } else if (this.level == 3) {
            return I_KENTTA_3_SPOTS;
        } else return null;
    }

    /**
     * Kentän 1 ImageView getteri
     * @return Palauttaa kentän 1 ImageViewin
     */
    public ImageView kentta1() {
        return new ImageView(I_KENTTA1);
    }

    /**
     * Kentän 2 ImageView getteri
     * @return Palauttaa kentän 2 ImageViewin
     */
    public ImageView kentta2(){
        return new ImageView(I_KENTTA_2);
    }

    /**
     * Kentän 3 ImageView getteri
     * @return Palauttaa kentän 3 ImageViewin
     */
    public ImageView kentta3(){
        return new ImageView(I_KENTTA3);
    }
}