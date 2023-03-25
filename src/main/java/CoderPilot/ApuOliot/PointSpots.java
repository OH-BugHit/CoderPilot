package CoderPilot.ApuOliot;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.Serializable;

/**
 * Luokan oliot toimivat pelissä kentän toiminnallisen kuvan toiminnallisten pisteiden käytössä.
 * @author Olli Hilke
 */
public class PointSpots implements Serializable {

    /**
     * Alustajalla luodaan PointSpots -olio
     * @param teksti Teksti, joka halutaan näyttää pelikuvan kerroksella (esim pistemäärä "20p")
     * @param cSpotsColor Asetetaan väri jolla kentän toiminnallisen spots -kuvan kohta on väritetty.
     */
    public PointSpots(Text teksti, Color cSpotsColor) {
        this.teksti = teksti;
        this.first = true;
        this.cSpotsColor = cSpotsColor;
    }

    /**
     * Kentän pelikuvan kerroksella näytettävä teksti
     */
    private final Text teksti;

    /**
     * Kertoo onko PointSpots -olion väri löydetty pixelReaderilla Aloitus.collisionShip() -metodissa
     */
    private Boolean first;

    /**
     * PointSpots -olion väri. Tätä väriä etsitään pixelReaderilla ja tällä värillä on toiminnallisuus väritetty kentän toiminnallisessa kuvassa (kentta*_spots.png)
     */
    private final Color cSpotsColor;

    /**
     * Olion sijainti X-akselilla kentässä 1
     */
    private int lvl1X;

    /**
     * Olion sijainti Y-akselilla kentässä 1
     */
    private int lvl1Y;

    /**
     * Olion sijainti X-akselilla kentässä 2
     */
    private int lvl2X;

    /**
     * Olion sijainti Y-akselilla kentässä 2
     */
    private int lvl2Y;

    /**
     * Olion sijainti X-akselilla kentässä 3
     */
    private int lvl3X;

    /**
     * Olion sijainti Y-akselilla kentässä 3
     */
    private int lvl3Y;

    /**
     * Kentän 1 X-akselin sijainnin asetus
     * @param lvl1X sijainti X-koordinaatisossa
     */
    public void setLvl1X(int lvl1X) {
        this.lvl1X = lvl1X;
    }

    /**
     * Kentän 1 Y-akselin sijainnin asetus
     * @param lvl1Y sijainti Y-koordinaatisossa
     */
    public void setLvl1Y(int lvl1Y) {
        this.lvl1Y = lvl1Y;
    }

    /**
     * Kentän 2 X-akselin sijainnin asetus
     * @param lvl2X sijainti X-koordinaatisossa
     */
    public void setLvl2X(int lvl2X) {
        this.lvl2X = lvl2X;
    }

    /**
     * Kentän 2 Y-akselin sijainnin asetus
     * @param lvl2Y sijainti Y-koordinaatisossa
     */
    public void setLvl2Y(int lvl2Y) {
        this.lvl2Y = lvl2Y;
    }

    /**
     * Kentän 3 X-akselin sijainnin asetus
     * @param lvl3X sijainti X-koordinaatisossa
     */
    public void setLvl3X(int lvl3X) {
        this.lvl3X = lvl3X;
    }

    /**
     * Kentän 3 Y-akselin sijainnin asetus
     * @param lvl3Y sijainti Y-koordinaatisossa
     */
    public void setLvl3Y(int lvl3Y) {
        this.lvl3Y = lvl3Y;
    }

    /**
     * Metodi asettaa tekstin sijainnin pelikuvakerroksella
     * @param lvl Kenttä jonka sijainnit halutaan. (Ladattava kenttä)
     */
    public void setTextLocation(int lvl) {
        switch (lvl){
            case 1 -> {
                this.teksti.setX(lvl1X);
                this.teksti.setY(lvl1Y);
            }
            case 2 -> {
                this.teksti.setX(lvl2X);
                this.teksti.setY(lvl2Y);
            }
            case 3 -> {
                this.teksti.setX(lvl3X);
                this.teksti.setY(lvl3Y);
            }
        }
    }

    /**
     * Asettaa olion tekstin värin
     * @param fillColor Haluttu olion tekstin väri.
     */
    public void setTextColor (Color fillColor){
        this.teksti.setFill(fillColor);
    }

    /**
     * Asettaa tekstin näkyvyyden
     * @param arvo true -jos halutaan tekstin näkyvän, muuten false
     */
    public void setVisibility(Boolean arvo){
        this.teksti.setVisible(arvo);
    }

    /**
     * Palauttaa olion tekstin
     * @return Olion tekstikentän arvo
     */
    public Text getTeksti() {
        return teksti;
    }

    /**
     * Palauttaa olion First -arvon, eli onko käyty olion pisteessä
     * @return true jos ei käyty, false jos käyty
     */
    public Boolean getFirst() {
        return first;
    }

    /**
     * Asettaa olion First -kentän arvon
     * @param first Asetetaan true kentän alussa ja false kun PointSpots -olion sijainnissa käydään (Väri löytyy pixelReaderilla)
     */
    public void setFirst(Boolean first) {
        this.first = first;
    }

    /**
     * Olion spots kuvassa käytetty värin hakeminen
     * @return Palauttaa olion "kentta*_spots.png" käytetyn värin (Väri asetettu ohjeman Aloitus luokan alussa)
     */
    public Color getcSpotsColor() {
        return cSpotsColor;
    }
}
