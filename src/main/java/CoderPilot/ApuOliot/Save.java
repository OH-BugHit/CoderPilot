package CoderPilot.ApuOliot;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * Luokka toteuttaa tallennukset
 * @author Olli Hilke
 */
public class Save implements Serializable {

    /**
     * Luokan parametriton alustaja.
     * Luokasta ei tule luoda oliota.
     */
    public Save(){
        System.out.println("Luokassa vain staattisia metodeja");
    }

    /**
     * Tallentaa Ship -olion tiedostoon alusSave.dat
     * @param toSave Tallennettava Ship -luokan olio
     */
    public static void saveAlus(Ship toSave) {
        toSave.setSpeed(0);
        toSave.setHeading(0);
        FileOutputStream alusOut = null;
        try {
            alusOut = new FileOutputStream("alusSave.dat");
        } catch (FileNotFoundException EiLoydy){
            System.out.println("Tiedostoa ei löytynyt, jotain meni vikaan.");
        }
        try {
            ObjectOutputStream objOutStream = new ObjectOutputStream(alusOut);
            objOutStream.writeObject(toSave);
            objOutStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lataa Ship -olion tiedostosta alusSave.dat
     * @return Palauttaa Ship -olion tiedostosta alusSave.dat
     * @throws IOException Virhe tiedostonluvussa
     */
    public static Ship loadAlus() throws IOException {
        Ship loaded = new Ship();
        FileInputStream tiedostoIn = null;
        try {
            tiedostoIn = new FileInputStream("alusSave.dat");
            ObjectInput objInStream = new ObjectInputStream(tiedostoIn);
            loaded = (Ship) objInStream.readObject();
        } catch (StreamCorruptedException e) {
            System.out.println("Virhe!\nTiedosto korruptoitunut.");
            e.printStackTrace();
            System.exit(0);
        } catch (ClassNotFoundException e) {
            System.out.println("Väärän luokan olioita?");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            System.out.println("Virhe tiedostoa lukiessa!");
            e.printStackTrace();
            System.exit(0);
        }
        tiedostoIn.close();
        return loaded;
    }

    /**
     * Tallentaa Settings -olion tiedostoon settingsSave.dat
     * @param toSave Tallennettava Settings -luokan olio
     */
    public static void saveSettings(Settings toSave) {
        FileOutputStream settingsOut = null;
        try {
            settingsOut = new FileOutputStream("settingsSave.dat");
        } catch (FileNotFoundException EiLoydy){
            System.out.println("Tiedostoa ei löytynyt, jotain meni vikaan.");
        }
        try {
            ObjectOutputStream objOutStream2 = new ObjectOutputStream(settingsOut);
            objOutStream2.writeObject(toSave);
            objOutStream2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lataa Settings -olion tiedostosta settingsSave.dat
     * @return Palauttaa Settings -olion tiedostosta settingsSave.dat
     * @throws IOException Virhe tiedostonluvussa
     */
    public static Settings loadSettings() throws IOException {
        Settings loadedSettings = new Settings();
        FileInputStream settingsIn = null;
        try {
            settingsIn = new FileInputStream("settingsSave.dat");
            ObjectInput objInStream2 = new ObjectInputStream(settingsIn);
            loadedSettings = (Settings) objInStream2.readObject();
        } catch (StreamCorruptedException e) {
            System.out.println("Virhe!\nTiedosto korruptoitunut.");
            e.printStackTrace();
            System.exit(0);
        } catch (ClassNotFoundException e) {
            System.out.println("Väärän luokan olioita?");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            System.out.println("Virhe tiedostoa lukiessa!");
            e.printStackTrace();
            System.exit(0);
        }
        settingsIn.close();
        return loadedSettings;
    }

    /**
     * Tallentaa PointSpots -olioiden first tiedot Boolean taulukossa tiedostoon spotsSave.dat
     * @param toSave Tallennettava Boolean taulukko. Koko 9
     */
    public static void savePointSpots(Boolean[] toSave) {
        FileOutputStream spotsOut = null;
        try {
            spotsOut = new FileOutputStream("spotsSave.dat");
        } catch (FileNotFoundException EiLoydy){
            System.out.println("Tiedostoa ei löytynyt, jotain meni vikaan");
        }
        try {
            ObjectOutputStream objOutStream3 = new ObjectOutputStream(spotsOut);
            objOutStream3.writeObject(toSave);
            objOutStream3.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lataa PointSpots -olioiden firs tiedot Boolean-taulukkona tiedostosta spotsSave.dat
     * @return Palauttaa Boolean[9] -olion tiedostosta spotsSave.dat
     * @throws IOException Virhe tiedostonluvussa
     */
    public static Boolean[] loadSpots() throws IOException {
        Boolean [] loadedSpots = new Boolean[9];
        FileInputStream spotsIn = null;
        try {
            spotsIn = new FileInputStream("spotsSave.dat");
            ObjectInput objInStream3 = new ObjectInputStream(spotsIn);
            loadedSpots = (Boolean[]) objInStream3.readObject();
        } catch (StreamCorruptedException e) {
            System.out.println("Virhe!\nTiedosto korruptoitunut.");
            e.printStackTrace();
            System.exit(0);
        } catch (ClassNotFoundException e) {
            System.out.println("Väärän luokan olioita?");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Virhe tiedostoa lukiessa!");
            e.printStackTrace();
            System.exit(0);
        }
        spotsIn.close();
        return loadedSpots;
    }

    /**
     * Tallentaa kielivalinnan languageSave.txt tiedostoon
     * @param kielivalinta Valittu kieli. "Finnish" tai "English"
     * @throws FileNotFoundException Voi heittää virheen mikäli tiedostoonpääsyssä ongelmia
     */
    public static void saveLanguage(String kielivalinta) throws FileNotFoundException {
        PrintWriter langOut = new PrintWriter("languageSave.txt");

        langOut.println(kielivalinta);
        langOut.close();
    }

    /**
     * Kielivalinnan lataus. Valinta on tarkastettu txt tiedoston virheiden varalta.
     * @return Palauttaa kielivalinnan. "Finnish" tai "English". Mikäli virheitä DEFAULT arvo on "English"
     * @throws FileNotFoundException Voi heittää virheen mikäli tiedostoonpääsyssä ongelmia
     */
    public static String loadLanguage() throws FileNotFoundException {
        String kieli = "";
        File languageSave = new File("languageSave.txt");
        if (languageSave.exists()) {
            Scanner langF = new Scanner(languageSave);
            if (langF.hasNext()) {
                kieli = langF.nextLine();
            } else {
                kieli = "English";
            }
        } else {kieli = "English";}
        if (Objects.equals(kieli, "Finnish")) {
            return kieli;
        }
        return "English";
    }
}
