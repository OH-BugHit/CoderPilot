package CoderPilot.Rajapinnat;

import javafx.scene.image.Image;

/**
 * Rajapinta PixelReaderin käyttöä varten pelikuvan ja taustalayerin lukemisessa.
 * Toteuttamalla rajapinnan kenttiä käsittelevässä luokassa, voi hakea PixelReadereiden tarvitsemia kuvia.
 * @author Olli Hilke
 */
public interface LevelsForPixelReaders {

    /**
     * Kentän näkyvän kuvatason hakeminen aluksen PixelReadereita varten
     * @param leveli Halutun kentän numero
     * @return Palauttaa kentän kuvan int leveli muuttujan mukaan. Tee switch rakenne.
     */
    Image getIKentta(int leveli);

    /**
     * Metodi kentän toiminnallisten pisteiden sisältävän kuvan hakemiseen pixelReaderia varten
     * @return Palauttaa setLevel metodilla asetetun kentän spots-kuvan
     */
    Image getKentta_Spots();

    /**
     * Luokan "kenttä" setteri levelille
     * @param level Asettaa halutun kentän.
     */
    void setLevel(int level);
}
