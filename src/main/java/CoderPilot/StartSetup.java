package CoderPilot;

/**
 * Abstrakti luokka pääohjelmalle
 * @author Olli Hilke
 */
abstract class StartSetup {

    /**
     * Muuttuja sisältää tiedon onko lataaminen hyväksytty.
     */
    protected boolean bLoadPossible;

    /**
     * Metodin tulee tarkistaa ohjelmassa käytettävien tiedostojen olemassaolo.
     * Metodin tulee sisältää virheenkäsittely, mikäli tiedosto puuttuu.
     * @return Palauttaa true, mikäli ohjelman saa ladata tallennuksen, muuten false. Arvo tulee asettaa bLoadPossible muuttujaan
     * bLoadPossible voidaan asettaa esimerkiksi pääohjelman alustajassa: public Paaohjelma() {this.bLoadPossible = checkFiles();}
     */
    protected abstract boolean checkFiles();

    /**
     * Getteri sovelluksessa käytettyyn ikkunan korkeuteen
     * @return Palauttaa sovelluksessa käytetyn ikkunan korkeuden
     */
    protected static int getScreenHeightFullHd() {
        return 1080;
    }

    /**
     * Getteri sovelluksessa käytettyyn ikkunan leveyteen
     * @return Palauttaa sovelluksessa käytetyn ikkunan leveyden
     */
    protected static int getScreenWidthFullHd() {
        return 1920;
    }
}
