package CoderPilot;

import CoderPilot.Peli.Aloitus;

import java.io.File;

/**
 * CoderPilot pelin pääohjelma. Ohjelma käynnistetään tästä.
 * @author Olli Hilke
 */
public class Paaohjelma extends StartSetup {

    /**
     * Luokan parametriton alustaja.
     * Alustaja asettaa StartSetup luokan bLoadPossible kenttään boolean arvon true jos tallennustiedostot löytyvät
     */
    public Paaohjelma() {
        this.bLoadPossible = checkFiles();
    }

    /**
     * Getteri sovelluksessa käytettyyn ikkunan leveyteen
     * @return Palauttaa sovelluksessa käytetyn ikkunan leveyden
     */
    public static int getScreenwidth() {
        return StartSetup.getScreenWidthFullHd();
    }

    /**
     * Getteri sovelluksessa käytettyyn ikkunan korkeuteen
     * @return Palauttaa sovelluksessa käytetyn ikkunan korkeuden
     */
    public static int getScreenheight() {
        return StartSetup.getScreenHeightFullHd();
    }

    /**
     * Tiedostojen olemassaolon tarkastus
     */
    protected boolean checkFiles(){
        File fS1 = new File("settingsSave.dat");
        File fS2 = new File("alusSave.dat");
        File fS3 = new File("spotsSave.dat");

        File fSo1 = new File("target/Classes/Sounds/accEnd.mp3");
        if (!fSo1.exists()) {
            System.out.println("accEnd.mp3 puuttuu Sounds kansiosta");
            System.exit(0);
        }
        File fSo2 = new File("target/classes/Sounds/accPohjassa.mp3");
        if (!fSo2.exists()) {
            System.out.println("accPohjassa.mp3 puuttuu Sounds kansiosta");
            System.exit(0);
        }
        File fSo3 = new File("target/classes/Sounds/accStart.mp3");
        if (!fSo3.exists()) {
            System.out.println("accStart.mp3 puuttuu Sounds kansiosta");
            System.exit(0);
        }
        File fkl1 = new File("target/classes/wasd.png");
        if (!fkl1.exists()) {
            System.out.println("wasd.png puuttuu resources kansiosta");
            System.exit(0);
        }
        File fkl2 = new File("target/classes/arrows.png");
        if (!fkl2.exists()) {
            System.out.println("arrows.png puuttuu resources kansiosta");
            System.exit(0);
        }
        File ft1 = new File("target/classes/Taustat/aloitusTausta.png");
        if (!ft1.exists()) {
            System.out.println("aloitusTausta.png puuttuu Taustat kansiosta");
            System.exit(0);
        }
        File ft2 = new File("target/classes/Taustat/settingsTausta.png");
        if (!ft2.exists()) {
            System.out.println("settingsTausta.png puuttuu Taustat kansiosta");
            System.exit(0);
        }
        File ft3 = new File("target/classes/Taustat/gameOverImageEN.png");
        if (!ft3.exists()) {
            System.out.println("gameOverImageEN.png puuttuu Taustat kansiosta");
            System.exit(0);
        }
        File ft4 = new File("target/classes/Taustat/gameOverImageFI.png");
        if (!ft4.exists()) {
            System.out.println("gameOverImageFI.png puuttuu Taustat kansiosta");
            System.exit(0);
        }
        File ft5 = new File("target/classes/Taustat/voitto.png");
        if (!ft5.exists()) {
            System.out.println("voitto.png puuttuu Taustat kansiosta");
            System.exit(0);
        }
        File fk3 = new File("target/classes/Kentat/kentta1.png");
        if (!fk3.exists()) {
            System.out.println("kentta1.png puuttuu  resouces/Kentat kansiosta\"");
            System.exit(0);
        }
        File fk4 = new File("target/classes/Kentat/kentta1_spots.png");
        if (!fk4.exists()) {
            System.out.println("kentta1_spots.png puuttuu Kentat kansiosta");
            System.exit(0);
        }
        File fk5 = new File("target/classes/Kentat/kentta2.png");
        if (!fk5.exists()) {
            System.out.println("kentta2.png puuttuu  resouces/Kentat kansiosta\"");
            System.exit(0);
        }
        File fk6 = new File("target/classes/Kentat/kentta2_spots.png");
        if (!fk6.exists()) {
            System.out.println("kentta2_spots.png puuttuu Kentat kansiosta");
            System.exit(0);
        }
        File fk7 = new File("target/classes/Kentat/kentta3.png");
        if (!fk7.exists()) {
            System.out.println("kentta3.png puuttuu  resouces/Kentat kansiosta\"");
            System.exit(0);
        }
        File fk8 = new File("target/classes/Kentat/kentta3_spots.png");
        if (!fk8.exists()) {
            System.out.println("kentta3_spots.png puuttuu Kentat kansiosta");
            System.exit(0);
        }
        File fs1 = new File("target/classes/Alukset/alusMusta.png");
        if (!fs1.exists()) {
            System.out.println("alusMusta.png puuttuu Kentat kansiosta");
            System.exit(0);
        }
        File fs2 = new File("target/classes/Alukset/alusOH.png");
        if (!fs2.exists()) {
            System.out.println("alusOH.png puuttuu Kentat kansiosta");
            System.exit(0);
        }
        File fs3 = new File("target/classes/Alukset/alusPinkki.png");
        if (!fs3.exists()) {
            System.out.println("alusPinkki.png puuttuu Kentat kansiosta");
            System.exit(0);
        }
        return fS1.exists() & fS2.exists() & fS3.exists(); //Palauttaa true, jos kaikki tallennukseen liittyvät tiedostot ovat olemassa
    }

    /**
     * CoderPilot -pelin pääohjelman main. Peli käynnistetään tästä
     * Sisältää myös tiedostojen olemassaolon varmistusmetodin kutsun
     * @param args parametriä ei käytetä
     */
    public static void main(String[] args) {
        Aloitus.isLoadPossible(new Paaohjelma().bLoadPossible);
        Aloitus.main(args);
    }
}
