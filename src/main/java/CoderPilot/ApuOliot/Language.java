package CoderPilot.ApuOliot;

import java.io.Serializable;
import java.util.Objects;

/**
 * Kielen valinta. Sisältää sanat ja lauseet
 * @author Olli Hilke
 */
public class Language implements Serializable {

    /**
     * Valittu kieli. Oletuksena "English".
     */
    private String kieliValinta = "English";

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "quit"
     */
    private String strQuit;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "settings"
     */
    private String strSettings;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "New Game"
     */
    private String strNewGame;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Load Game"
     */
    private String strLoadGame;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Credits"
     */
    private String strCredits;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Return"
     */
    private String strReturn;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Easy"
     */
    private String strEasy;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Normal"
     */
    private String strNormal;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Hard"
     */
    private String strHard;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Test"
     */
    private String strTesti;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "FX volume"
     */
    private String strTehosAani;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Difficulty"
     */
    private String strDifficulty;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Choose language"
     */
    private String strChooseLang;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Points"
     */
    private String strPoints;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Life"
     */
    private String strLife;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Save and exit"
     */
    private String strSaveXexit;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Loaded"
     */
    private String strLoaded;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Continue"
     */
    private String strContinue;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Quit game"
     */
    private String strExit;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Level"
     */
    private String strLevel;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "End time"
     */
    private String strEndTime;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Success"
     */
    private String strSuccess;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Choose ship image"
     */
    private String strChooseShipImage;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Select keyboard layout"
     */
    private String strSelectKeyLay;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Name your ship"
     */
    private String strNameShip;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Congratulations"
     */
    private String strCongrats;

    /**
     * Sisältää valitun kielen mukaisen termin sanalle tilanteessa "Tip for pausebutton"
     */
    private String strPauseTip;

    /**
     * Valitun kielen kertova metodi
     * @return Palauttaa käytettävän kielen. String
     */
    public String getKieliValinta() {
        return kieliValinta;
    }

    /**
     * Kentän ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrLevel() {
        return strLevel;
    }

    /**
     * Continue ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrContinue() {return strContinue;}

    /**
     * Loaded ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrLoaded() {
        return strLoaded;
    }

    /**
     * Save and exit ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrSaveXexit() {return strSaveXexit;}

    /**
     * Points ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrPoints() {
        return strPoints;
    }

    /**
     * Life ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrLife() {
        return strLife;
    }

    /**
     * Choose language ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrChooseLang() {
        return strChooseLang;
    }

    /**
     * Difficulty ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrDifficulty() {
        return strDifficulty;
    }

    /**
     * FX-volume ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrTehosAani() {
        return strTehosAani;
    }

    /**
     * Easy ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrEasy() {
        return strEasy;
    }

    /**
     * Normal ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrNormal() {
        return strNormal;
    }

    /**
     * Hard ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrHard() {
        return strHard;
    }

    /**
     * Test ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrTesti() {
        return strTesti;
    }

    /**
     * Settings ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrSettings() {
        return strSettings;
    }

    /**
     * New Game ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrNewGame() {
        return strNewGame;
    }

    /**
     * Load Game ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrLoadGame() {return strLoadGame;}

    /**
     * Credits ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrCredits() {return strCredits;}

    /**
     * Return ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrReturn() {return strReturn;}

    /**
     * Test Exit oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrExit() {return strExit;}

    /**
     * End time ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrEndTime() {return strEndTime;}

    /**
     * Quit ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrQuit() {return strQuit;}

    /**
     * Success ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrSuccess() {return strSuccess;}

    /**
     * Choose ship image ilmaisu oikealla kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrChooseShipImage() {return strChooseShipImage;}

    /**
     * Select keyboard layout ilmaisu oikella kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrSelectKeyLay() { return strSelectKeyLay;
    }

    /**
     * Name your ship ilmaisu oikella kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getstrNameShip(){
        return strNameShip;
    }

    /**
     * Congratulations ilmaisu oikella kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrCongrats() {return strCongrats;
    }

    /**
     * Pausebutton tip ilmaisu oikella kielellä
     * @return ilmaisu oikealla kielellä. String
     */
    public String getStrPauseTip() {return strPauseTip;
    }

    /**
     * Olioluokan "kieli" -alustaja. Alustaa valitun kielen mukaan haettavat tekstit
     * @param kieli Käytettävä kieli
     */
    public Language(String kieli) {
        this.kieliValinta = kieli;
        if (Objects.equals(this.kieliValinta, "Finnish")) {
            this.strSettings = "Asetukset";
            this.strNewGame = "Uusi peli";
            this.strLoadGame = "Lataa peli";
            this.strCredits = "Tekijät";
            this.strReturn = "Paluu";
            this.strEasy = "Helppo";
            this.strNormal = "Keskitaso";
            this.strHard = "Vaikea";
            this.strTesti = "Testi";
            this.strTehosAani = "Tehosteiden äänenvoimakkuus";
            this.strDifficulty = "Vaikeusaste";
            this.strChooseLang = "Valitse kieli";
            this.strPoints = "Pisteet";
            this.strLife = "Elämät";
            this.strSaveXexit = "Tallenna ja lopeta peli";
            this.strLoaded = "Peli ladattu";
            this.strContinue = "Jatka peliä";
            this.strExit = "Lopeta";
            this.strLevel = "Taso";
            this.strEndTime = "Aika";
            this.strQuit = "Lopeta";
            this.strSuccess = "voittoon!";
            this.strChooseShipImage = "Valitse alus";
            this.strSelectKeyLay = "Valitse näppäimet";
            this.strNameShip = "Aluksesi nimi";
            this.strCongrats = "Onnittelut ";
            this.strPauseTip = "'P' = pause";
        } else if (Objects.equals(this.kieliValinta, "English")) {
            this.strSettings = "Settings";
            this.strNewGame = "New Game";
            this.strLoadGame = "Load Game";
            this.strCredits = "Credits";
            this.strReturn = "Return";
            this.strEasy = "Easy";
            this.strNormal = "Normal";
            this.strHard = "Hard";
            this.strTesti = "Test";
            this.strTehosAani = "FX Volume";
            this.strDifficulty = "Difficulty";
            this.strChooseLang = "Choose language";
            this.strPoints = "Points";
            this.strLife = "Lives";
            this.strSaveXexit = "Save and quit game";
            this.strLoaded = "Game loaded";
            this.strContinue = "Continue";
            this.strExit = "Quit game";
            this.strLevel = "Level";
            this.strEndTime = "Time";
            this.strQuit = "Quit";
            this.strSuccess = "with success!";
            this.strChooseShipImage = "Choose ship";
            this.strSelectKeyLay = "Select keys";
            this.strNameShip = "Name Your ship";
            this.strCongrats = "Congratulations ";
            this.strPauseTip = "'P' = pause";
        }
    }
}