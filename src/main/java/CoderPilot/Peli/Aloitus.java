package CoderPilot.Peli;

import CoderPilot.ApuOliot.*;
import CoderPilot.Kentat.Level;
import CoderPilot.Paaohjelma;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import static java.lang.Math.floor;
import static java.lang.Math.random;
import static javafx.scene.layout.BackgroundPosition.CENTER;
import static javafx.scene.layout.BackgroundRepeat.SPACE;
import static javafx.scene.layout.BackgroundSize.DEFAULT;

/**
 * CoderPilot Lentelypelin applikaatioluokka
 * Sisältää pelin toiminnan ja osan käytettävistä resursseista
 * Luokka käynnistyy 'paaohjelma' -luokan käynnistämänä
 * @author Olli Hilke
 */
public class Aloitus extends Application {

    /**
     * playerShip on Ship-luokan olio joka sisältää aluksen tiedot kun suuntima, nopeus, elämät, pisteet
     */
    Ship playerShip = new Ship("Player1", "target/classes/Alukset/alusMusta.png");

    /**
     * Luodaan Settings -luokan olio asetusten asettamiseen
     */
    public Settings settingsObj = new Settings();

    /**
     * Luodaan Language -luokan olio näytettävän kielen sanojen hakua varten
     */
    public Language lang = new Language(Save.loadLanguage());

    /**
     * Level -olio kentän valintaan ja kentän lataamisiin
     */
    public Level levelChoise = new Level();

    /**
     * Scene johon vaihdetaan aloitusvalikosta
     */
    Scene scLevel1;

    /**
     * True mikäli lataus sallittu, muuten false.
     */
    private static boolean bLoadPossible;

    /**
     * bLoadPossiblen set-metodi
     * @param possible true, jos tallennuksen lataus on sallittu, muuten false.
     */
    public static void isLoadPossible(boolean possible){
        bLoadPossible = possible;
    }

    /**
     * Kertoo, mikäli jatketaan tallennettua peliä.
     */
    private boolean bLoaded = false;

    /**
     * True, mikäli peli läpäisty, muuten false
     */
    private boolean bGameOverSuccess = false;

    /**
     * Valikoiden teksteissä käytettävä väri
     */
    private final Color C_VALIKKOTEKSTIT = Color.FIREBRICK;

    /**
     * Kentän pohjan väri. Käytetään törmäyksen tunnistuksessa
     */
    private final Color C_POHJA = new Color(0.168627451, 0.168627451, 0.168627451, 1);

    /**
     * Valikoiden teksteissä käytettävä väri kun hiiri yllä
     */
    private final Color CVALIKKOTEKSTIT_HOV = new Color(0.69803923f, 0.13333334f, 0.13333334f,0.8);

    /**
     * Kentän maalin väri. Kentän toiminnallisen kuvakerroksen pixelreaderia varten
     */
    private final Color C_MAALI = new Color(0,0,1,1);

    /**
     * Taulukko johon tallennetaan kerättyjen pisteiden "keräämätön" -arvo ("first*")
     */
    private Boolean[] saveSpots;

    /**
     * 20 pistettä.
     * PointSpots -olio. Olio toimii kentän toiminnaliseen kuvakerrokseen piirretyn värin tunnistamisessa.
     */
    private final PointSpots PS_20 = new PointSpots(new Text("20"), new Color(1,0,0,1));

    /**
     * Toinen ilmentymä 20 pisteestä.
     * PointSpots -olio. Olio toimii kentän toiminnaliseen kuvakerrokseen piirretyn värin tunnistamisessa.
     */
    private final PointSpots PS20_2 = new PointSpots(new Text("20"), new Color(1,0.3921568627,0,1));

    /**
     * Kolmas ilmentymä 20 pisteestä.
     * PointSpots -olio. Olio toimii kentän toiminnaliseen kuvakerrokseen piirretyn värin tunnistamisessa.
     */
    private final PointSpots PS_20_3 = new PointSpots(new Text("20"), new Color(1,0,0.3921568627,1));

    /**
     * Neljäs ilmentymä 20 pisteestä.
     * PointSpots -olio. Olio toimii kentän toiminnaliseen kuvakerrokseen piirretyn värin tunnistamisessa.
     */
    private final PointSpots PS_20_4 = new PointSpots(new Text("20"), new Color(1,0.3921568627,0.3921568627,1));

    /**
     * Viides ilmentymä 20 pisteestä.
     * PointSpots -olio. Olio toimii kentän toiminnaliseen kuvakerrokseen piirretyn värin tunnistamisessa.
     */
    private final PointSpots PS_20_5 = new PointSpots(new Text("20"), new Color(1,1,0.3921568627,1));

    /**
     * 40 pistettä.
     * PointSpots -olio. Olio toimii kentän toiminnaliseen kuvakerrokseen piirretyn värin tunnistamisessa.
     */
    private final PointSpots PS_40 = new PointSpots(new Text("40"), new Color(0.3921568627,0,0.3921568627,1));

    /**
     * Toinen ilmentymä 40 pisteestä.
     * PointSpots -olio. Olio toimii kentän toiminnaliseen kuvakerrokseen piirretyn värin tunnistamisessa.
     */
    private final PointSpots PS40_2 = new PointSpots(new Text("40"), new Color(0.3921568627,0.3921568627,1,1));

    /**
     * 60 pistettä.
     * PointSpots -olio. Olio toimii kentän toiminnaliseen kuvakerrokseen piirretyn värin tunnistamisessa.
     */
    private final PointSpots PS_60 = new PointSpots(new Text("60"), new Color(0,1,0,1));

    /**
     * 100 pistettä.
     * PointSpots -olio. Olio toimii kentän toiminnaliseen kuvakerrokseen piirretyn värin tunnistamisessa.
     */
    private final PointSpots PS_100 = new PointSpots(new Text("100"), new Color(0,0.3921568627,0.3921568627,1));

    /**
     * Käytetään ajan näyttämiseen.
     */
    protected Text tTime = new Text("--");

    /**
     * Näyttää jäljellä olevat elämät.
     */
    protected Text tLife = new Text("--");

    /**
     * Näyttää kerätyt pisteet.
     */
    protected Text tPoints = new Text("--");

    /**
     * Gameover -kuva. Suomenkielinen.
     */
    private final ImageView IV_BACKGROUND_FI = new ImageView(new Image(new FileInputStream("target/classes/Taustat/gameOverImageFI.png")));

    /**
     * Gameover -kuva. Englanninkielinen
     */
    private final ImageView IV_BACKGROUND_EN = new ImageView(new Image(new FileInputStream("target/classes/Taustat/gameOverImageEN.png")));

    /**
     * Gameover -tausta. Tulee voitettaessa
     */
    private final ImageView IV_VICTORY = new ImageView(new Image(new FileInputStream("target/classes/Taustat/voitto.png")));

    /**
     * Kenttä 1
     */
    private final Pane PANE_KENTTA1 = new Pane(levelChoise.kentta1());

    /**
     * Kenttä 2
     */
    private final Pane PANE_KENTTA2 = new Pane(levelChoise.kentta2());

    /**
     * Kenttä 3
     */
    private final Pane PANE_KENTTA3 = new Pane(levelChoise.kentta3());

    /**
     * Paneeli, mikäli päästään peli läpi
     */
    protected Pane paneEnd = new Pane();

    /**
     * Paneeli, kun peli päättyy
     */
    private StackPane paneGameOver;

    /**
     * heads-up display -paneeli
     */
    private GridPane paneHud;

    /**
     * Käytetään käynnistämään pelin ajanotto.
     */
    private boolean bStartTimer = true;

    /**
     * Alkuaika kellon käynnistyessä.
     */
    private double startTime;

    /**
     * Muuttuja kertoo onko peli vielä ohi.
     */
    private boolean bGameOver = false;

    /**
     * Kertoo onko peli vielä käynnissä
     */
    private boolean bGameRunState = false;

    /**
     * True jos kenttä läpäisty ja voidaan siirtyä seuraavaan kenttään.
     */
    private boolean bVoitto = false;

    /**
     * Kertoo mikäli alus lentää ulos pelialueelta
     */
    private boolean bOutOfBounds = false;

    /**
     * Muuttuja, johon asetetaan painettu näppäin
     */
    private KeyCode kcNappi;

    /**
     * Alustaja aloitus-luokan oliolle
     * @throws FileNotFoundException Luokassa aluksen kuvan lataus, sitä ei löydy.
     */
    public Aloitus() throws FileNotFoundException {
    }

    /**
     * Scenen asettaminen
     * @param scLevel  saa parametrikseen asetettavan Scene -olion
     */
    public void setScLevel1(Scene scLevel) {
        scLevel1 = scLevel;
    }

    /**
     * Soittaa äänet äänenvoimakkuudella 0.
     * Tämä poistaa viiveen soitettaessa ensimmäisellä kerralla itse pelissä
     */
    private void preloadSounds() {
        double valimuuttuja = FxSound.getVolume();
        FxSound.setVolume(0);
        FxSound.thrustPlay();
        FxSound.loopPlay();
        FxSound.stopThrust();
        FxSound.setVolume(valimuuttuja);
    }

    /**
     * Tallennuksen käsittelijä. Tallentaa aluksen tiedot "alusSave.dat" tiedostoon, pisteiden käyntitiedot "spotsSave.dat tiedostoon ja pelin asetukset "settingsSave.dat" tiedostoon
     * @return palauttaa tallennuksen käsittelijän
     */
    private EventHandler<ActionEvent> gameSavexExit() {
        return e->{
            try {
                Save.saveLanguage(lang.getKieliValinta());
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            Save.saveAlus(playerShip);
            Save.saveSettings(settingsObj);
            saveSpots = new Boolean[9];
            saveSpots[0] = PS_20.getFirst();
            saveSpots[1] = PS20_2.getFirst();
            saveSpots[2] = PS_20_3.getFirst();
            saveSpots[3] = PS_20_4.getFirst();
            saveSpots[4] = PS_20_5.getFirst();
            saveSpots[5] = PS_40.getFirst();
            saveSpots[6] = PS40_2.getFirst();
            saveSpots[7] = PS_60.getFirst();
            saveSpots[8] = PS_100.getFirst();
            Save.savePointSpots(saveSpots);
            System.out.println("Kiitos pelaamisesta!");
            System.exit(0);
        };
    }

    /**
     * Asettaa kenttien toiminnallisten kuvien toiminnallisten pisteiden sijainnit arvoina PointSpots -olioiden sijainneille kentittäin.
     */
    private void SpotTextLocations() {
        PS_20.setLvl1X(783);
        PS_20.setLvl1Y(889);
        PS_40.setLvl1X(397);
        PS_40.setLvl1Y(873);
        PS_60.setLvl1X(471);
        PS_60.setLvl1Y(195);
        PS_100.setLvl1X(390);
        PS_100.setLvl1Y(257);

        PS_20.setLvl2X(985);
        PS_20.setLvl2Y(1002);
        PS20_2.setLvl2X(938);
        PS20_2.setLvl2Y(875);
        PS_20_3.setLvl2X(831);
        PS_20_3.setLvl2Y(760);
        PS_20_4.setLvl2X(426);
        PS_20_4.setLvl2Y(412);
        PS_20_5.setLvl2X(1109);
        PS_20_5.setLvl2Y(242);
        PS_40.setLvl2X(455);
        PS_40.setLvl2Y(764);
        PS40_2.setLvl2X(454);
        PS40_2.setLvl2Y(478);
        PS_60.setLvl2X(377);
        PS_60.setLvl2Y(1006);
        PS_100.setLvl2X(463);
        PS_100.setLvl2Y(635);

        PS_20.setLvl3X(528);
        PS_20.setLvl3Y(1004);
        PS20_2.setLvl3X(1176);
        PS20_2.setLvl3Y(966);
        PS_20_3.setLvl3X(1069);
        PS_20_3.setLvl3Y(871);
        PS_20_4.setLvl3X(1007);
        PS_20_4.setLvl3Y(534);
        PS_20_5.setLvl3X(437);
        PS_20_5.setLvl3Y(1010);
        PS_40.setLvl3X(540);
        PS_40.setLvl3Y(851);
        PS40_2.setLvl3X(440);
        PS40_2.setLvl3Y(340);
        PS_60.setLvl3X(472);
        PS_60.setLvl3Y(321);
        PS_100.setLvl3X(437);
        PS_100.setLvl3Y(391);
    }

    /**
     * Asettaa pistekenttien näkyvyyksiä halutuille pistekentille
     * @param vis20 Ensimmäinen 20p Teksti
     * @param vis20_2 Toinen 20p Teksti
     * @param vis20_3 Kolmas 20p Teksti
     * @param vis20_4 Neljäs 20p Teksti
     * @param vis20_5 Viides 20p Teksti
     * @param vis40 Ensimmäinen 40p teksti
     * @param vis40_2 Toinen 40p teksti
     * @param vis60 60p teksti
     * @param vis100 100p teksti
     */
    private void setSpotsVisible(boolean vis20,boolean vis20_2,boolean vis20_3,boolean vis20_4,boolean vis20_5,boolean vis40,boolean vis40_2,boolean vis60,boolean vis100) {
        PS_20.setVisibility(vis20);
        PS20_2.setVisibility(vis20_2);
        PS_20_3.setVisibility(vis20_3);
        PS_20_4.setVisibility(vis20_4);
        PS_20_5.setVisibility(vis20_5);
        PS_40.setVisibility(vis40);
        PS40_2.setVisibility(vis40_2);
        PS_60.setVisibility(vis60);
        PS_100.setVisibility(vis100);
    }

    /**
     * Asettaa kaikkien pistekenttien näkyvyyden
     * @param all true asettaa näkyväksi, false piilottaa
     */
    private void setSpotsVisible(boolean all) {
        PS_20.setVisibility(all);
        PS20_2.setVisibility(all);
        PS_20_3.setVisibility(all);
        PS_20_4.setVisibility(all);
        PS_20_5.setVisibility(all);
        PS_40.setVisibility(all);
        PS40_2.setVisibility(all);
        PS_60.setVisibility(all);
        PS_100.setVisibility(all);
    }

    /**
     * Asettaa pistetekstikenttien ensimmäisestä kosketuksesta kirjaa pitävän booleanin valmiiksi.
     */
    private void setSpotsReady() {
        PS_20.setFirst(true);
        PS20_2.setFirst(true);
        PS_20_3.setFirst(true);
        PS_20_4.setFirst(true);
        PS_20_5.setFirst(true);
        PS_40.setFirst(true);
        PS40_2.setFirst(true);
        PS_60.setFirst(true);
        PS_100.setFirst(true);
    }

    /**
     * Asettaa pointSpots olioiden tekstikenttien värit
     */
    private void setPsTextCol() {
        PS_20.setTextColor(Color.GREEN); //Asetellaan PointSpots-olioden tekstien väri
        PS20_2.setTextColor(Color.GREEN);
        PS_20_3.setTextColor(Color.GREEN);
        PS_20_4.setTextColor(Color.GREEN);
        PS_20_5.setTextColor(Color.GREEN);
        PS_40.setTextColor(Color.ORANGE);
        PS40_2.setTextColor(Color.ORANGE);
        PS_60.setTextColor(Color.RED);
        PS_100.setTextColor(Color.GOLD);
    }

    /**
     * Sijoittelee Aluksen ja PointSpots-oliot kentälle
     * @param ivAlusKuva ImageView, jolle sijainti annetaan. Sisältää aluksen kuvan
     */
    private void prepareLevelObjects(ImageView ivAlusKuva) {
        switch (playerShip.getLevel()){
            case 1 -> {
                prepareLevel1();
                ivAlusKuva.setLayoutX(530);
                ivAlusKuva.setLayoutY(824);

            }
            case 2 -> {
                prepareLevel2();
                ivAlusKuva.setLayoutX(505);
                ivAlusKuva.setLayoutY(973);
            }
            case 3 -> {
                prepareLevel3();
                ivAlusKuva.setLayoutX(521);
                ivAlusKuva.setLayoutY(955);
            }
        } //Kentän valmistelua
    }

    /**
     * Asettelee kentän 1 PointSpots -oliot kuvaan paikoilleen ja näkyville.
     */
    private void prepareLevel1() {
        PS_20.setTextLocation(1);
        PS_40.setTextLocation(1);
        PS_60.setTextLocation(1);
        PS_100.setTextLocation(1);
        if (!bLoaded){setSpotsVisible(true,false,false,false, false, true, false, true, true);}
        PANE_KENTTA1.setVisible(true);
    }

    /**
     * Asettelee kentän 2 PointSpots -oliot kuvaan paikoilleen ja näkyville.
     */
    private void prepareLevel2() {
        PS_20.setTextLocation(2);
        PS20_2.setTextLocation(2);
        PS_20_3.setTextLocation(2);
        PS_20_4.setTextLocation(2);
        PS_20_5.setTextLocation(2);
        PS_40.setTextLocation(2);
        PS40_2.setTextLocation(2);
        PS_60.setTextLocation(2);
        PS_100.setTextLocation(2);
        if (!bLoaded){setSpotsVisible(true);}
        playerShip.setSpeed(0);
        playerShip.setHeading(0);
        PANE_KENTTA2.setVisible(true);
    }

    /**
     * Asettelee kentän 3 PointSpots -oliot kuvaan paikoilleen ja näkyville.
     */
    private void prepareLevel3() {
        PS_20.setTextLocation(3);
        PS20_2.setTextLocation(3);
        PS_20_3.setTextLocation(3);
        PS_20_4.setTextLocation(3);
        PS_20_5.setTextLocation(3);
        PS_40.setTextLocation(3);
        PS40_2.setTextLocation(3);
        PS_60.setTextLocation(3);
        PS_100.setTextLocation(3);
        if (!bLoaded){setSpotsVisible(true);}
        playerShip.setSpeed(0);
        playerShip.setHeading(0);
        PANE_KENTTA3.setVisible(true);
    }

    /**
     * Asettaa PointSpots -olioille näkyvyden ja ensimmäisen kosketuksen kentän kun jatketaan tallennettua peliä
     */
    private void setSpotsIfLoaded() {
        if (bLoaded){
            if (!saveSpots[0]){
                PS_20.setVisibility(false);
                PS_20.setFirst(false);
            }
            if (!saveSpots[1]) {
                PS20_2.setVisibility(false);
                PS20_2.setFirst(false);
            }
            if (!saveSpots[2]) {
                PS_20_3.setVisibility(false);
                PS_20_3.setFirst(false);
            }
            if (!saveSpots[3]) {
                PS_20_4.setVisibility(false);
                PS_20_4.setFirst(false);
            }
            if (!saveSpots[4]) {
                PS_20_5.setVisibility(false);
                PS_20_5.setFirst(false);
            }
            if (!saveSpots[5]) {
                PS_40.setVisibility(false);
                PS_40.setFirst(false);
            }
            if (!saveSpots[6]) {
                PS40_2.setVisibility(false);
                PS40_2.setFirst(false);
            }
            if (!saveSpots[7]) {
                PS_60.setVisibility(false);
                PS_60.setFirst(false);
            }
            if (!saveSpots[8]) {
                PS_100.setVisibility(false);
                PS_100.setFirst(false);
            }
        }
    }

    /**
     * Valikoissa käytettävä fontti
     * @return Palauttaa fontin: "Verdana", FontWeight.Bold, FontPosture.REGULAR, size 40.
     */
    public static Font valikkoFontti(){
        return Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR,40);
    }

    /**
     * Käsittelee pelin päättymisen. Voittoon taikka häviöön.
     */
    private void gameOver() {
        if (Objects.equals(lang.getKieliValinta(), "Finnish")) {
            paneGameOver.getChildren().add(IV_BACKGROUND_FI);
        } else if (Objects.equals(lang.getKieliValinta(), "English")) {
            paneGameOver.getChildren().add(IV_BACKGROUND_EN);
        }
        Text tFinalTime = new Text(String.format("%.0f", playerShip.getTimeElapsed() + floor(System.currentTimeMillis() * 0.001) - startTime) + "s"); //Loppuaika sekunteina
        tFinalTime.setFont(valikkoFontti());
        tFinalTime.setFill(Color.GOLD);
        Label lbFinalTime = new Label(lang.getStrEndTime(),tFinalTime);
        lbFinalTime.setContentDisplay(ContentDisplay.BOTTOM);
        lbFinalTime.setFont(valikkoFontti());
        lbFinalTime.setTextFill(Color.FIREBRICK);

        Text tFinalScore = new Text(String.valueOf(playerShip.getPoints())); //Loppupisteet
        tFinalScore.setFont(valikkoFontti());
        tFinalScore.setFill(Color.GOLD);
        Label lbFinalScore = new Label(lang.getStrPoints(),tFinalScore);
        lbFinalScore.setContentDisplay(ContentDisplay.BOTTOM);
        lbFinalScore.setFont(valikkoFontti());
        lbFinalScore.setTextFill(Color.FIREBRICK);
        Text tSuccess = new Text(lang.getStrSuccess());
        tSuccess.setFont(valikkoFontti());
        tSuccess.setFill(Color.GOLD);
        tSuccess.setTextAlignment(TextAlignment.CENTER);
        Button bquit = new Button(lang.getStrQuit());
        bquit.setStyle("-fx-color: #323232;");
        bquit.setOnAction(e-> System.exit(0));
        GridPane paneEndStats = new GridPane();
        paneEndStats.setHgap(30);
        paneEndStats.setVgap(60);

        Text tCongrats = new Text(lang.getStrCongrats() + playerShip.getName() + "!"); // Asetetaan teksti onnitteluille, jos peli menee läpi
        tCongrats.setFont(valikkoFontti());
        tCongrats.setFill(Color.SILVER);
        tCongrats.setVisible(false); // Piilotetaan luotu tekstikenttä, tämä saa näkyä vain jos peli päättyy voittoon.
        if (bGameOverSuccess){ //Näytetään pelaajalle onnittelu nimen kera, mikäli pääsee loppuun
            paneEnd.getChildren().add(IV_VICTORY);
            paneEndStats.add(tSuccess,1,0);
            tCongrats.setVisible(true);
        }

        paneEndStats.add(lbFinalScore,0,1);
        paneEndStats.add(lbFinalTime,2,1);
        VBox vbEndStats = new VBox(20,paneEndStats,tCongrats,bquit);
        vbEndStats.setAlignment(Pos.BOTTOM_CENTER);
        paneGameOver.getChildren().add(vbEndStats);
        paneEndStats.setAlignment(Pos.BOTTOM_CENTER);
        paneHud.setVisible(false);
        paneGameOver.setVisible(true);
        paneGameOver.setLayoutX(Paaohjelma.getScreenwidth()*0.5- IV_BACKGROUND_EN.getLayoutBounds().getWidth()/2);
        paneGameOver.setLayoutY(Paaohjelma.getScreenheight()*0.5- IV_BACKGROUND_EN.getLayoutBounds().getHeight()/2);
        bGameRunState = false;
        playerShip.setPoints(0);
        playerShip.setDifficultySettings(playerShip.getDifficulty());
    }

    /**
     * Keycodeolion getteri. Käytetään näppäimistösyötteen käsittelyssä
     * @return Palauttaa napinpainalluksen KeyCoden
     */
    public KeyCode getKcNappi() {
        return kcNappi;
    }

    /**
     * Keycodeolion setteri
     * @param kcNappi Painettu näppäin
     */
    public void setKcNappi(KeyCode kcNappi) {
        this.kcNappi = kcNappi;
    }

    /**
     * Liikuttaa kuvaa kuvan päällä
     * Muuttaa kuvan LayoutX ja LayoutY tietoja
     * @param speed Nopeus, jolla liikutaan (monta pixeliä)
     * @param heading Suunta, johon liikutaan (asteina)
     * @param ivObjImage liikutettava kuva
     */
    public void moveObjInImage(double speed, double heading, ImageView ivObjImage){
        ivObjImage.setLayoutX(ivObjImage.getLayoutX() + speed * Math.sin((heading) * (2 * Math.PI / 360)));
        ivObjImage.setLayoutY(ivObjImage.getLayoutY() - speed * Math.cos((heading) * (2 * Math.PI / 360)));
    };

    /**
     * Aluksen ohjausmetodi kiihdytystä, hidastusta ja kääntöä varten. Asettaa aluksen heading arvon.
     * @param keycode Syötteenä näppäimistön painallus
     * @param ship    Ohjattava alus
     */
    public void controlShip(KeyCode keycode, Ship ship) {
        if (bGameRunState) {
            double oldSpeed = ship.getSpeed();
            double newSpeed = oldSpeed;

            //Kiihdytys
            if (keycode == settingsObj.getUpKey()) {
                switch (FxSound.getPhase()) {
                    case 0 -> FxSound.thrustPlay();
                    case 1 -> FxSound.loopPlay();
                }
                if (Ship.getMaxSpeedMultiplier() < ship.getSpeedMultiplier())
                    ship.setSpeedMultiplier(ship.getSpeedMultiplier() + 0.004);
                if (ship.getSpeed() < ship.getMaxSpeed()) {
                    newSpeed = oldSpeed + 0.1 + (1.6 * ship.getSpeedMultiplier());
                    ship.setSpeed(newSpeed);
                }
                ship.setBrakeKerroin(0);
            }

            //Jarrutus ja peruutus
            if (keycode == settingsObj.getDownKey()) {
                FxSound.stopThrust();
                if (ship.getBrakeKerroin() < Ship.getMaxBrakeMultiplier()) {
                    ship.setBrakeKerroin(ship.getBrakeKerroin() + 0.03);
                }
                if (oldSpeed > 4) {
                    newSpeed -= 0.6 + (2 * ship.getBrakeKerroin());
                } else if (oldSpeed > 2) {
                    ship.setBrakeKerroin(0);
                    newSpeed -= 0.5;
                } else if (oldSpeed > -0.5) {
                    newSpeed -= 0.1;
                } else if (oldSpeed > -3) {
                    ship.setBrakeKerroin(0);
                    newSpeed = -0.5;
                }
                ship.setSpeed(newSpeed);
                ship.setSpeedMultiplier(0);
            }

            //Jarrutus
            if (keycode == KeyCode.SPACE) {
                FxSound.stopThrust();
                if (oldSpeed > 5 | oldSpeed < -5) {
                    ship.setSpeed(ship.getSpeed() * 0.7 - 0.3);
                } else if (oldSpeed > 1 | oldSpeed < -1) {
                    ship.setSpeed(ship.getSpeed() * 0.7);
                } else if (oldSpeed > 0.5 | oldSpeed < -0.5) {
                    ship.setSpeed(oldSpeed - 0.1);
                } else {
                    ship.setSpeed(0);
                }
                ship.setSpeedMultiplier(0);
            }

            // Seuraavassa asetetaan uusi suuntima näppäiminen vasen ja oikea perusteella.
            double newHeading = ship.getHeading();
            if (keycode == settingsObj.getLeftKey()) {
                newHeading -= ship.getTurnSpeed();
            }
            if (keycode == settingsObj.getRightKey()) {
                newHeading += ship.getTurnSpeed();
            }
            ship.setHeading(newHeading);
        }
    }

    /**
     * Aluksen vuorovaikutus kentän kanssa. Sisältää pikselivertailua aluksen takanaolevan kentän, sekä kentän "Spots" kuvan kanssa
     * Mikäli kentän pohjaväriltä poistutaan eli "törmätään" esim. tekstiin, alus palaa lähtöpisteeseen tai elämien loppuessa peli päättyy.
     * Spots-vertailuissa kerätään pisteitä ja myös kentän maali on toteutettu samoin.
     * @param ivAlus Saa parametrinä käytettävän aluksen ImageViewin
     */
    public void collisionShip(ImageView ivAlus) {
        if (ivAlus.getLayoutX() > Paaohjelma.getScreenwidth() | ivAlus.getLayoutX() < 0 | ivAlus.getLayoutY() < 0 | ivAlus.getLayoutY() > Paaohjelma.getScreenheight()) {
            bOutOfBounds = true;
            playerShip.setSpeed(0);
        }
        PixelReader pr1 = levelChoise.getKentta_Spots().getPixelReader();
        Color spots = null;
        if (!bOutOfBounds) {
        spots = pr1.getColor((int) ivAlus.getLayoutX(), (int) ivAlus.getLayoutY());}
        if (Objects.equals(spots, C_MAALI)) {
            FxSound.setPhase(0);
            bVoitto = true;
            playerShip.setSpeed(0);
            playerShip.setHeading(0);
        } else if (Objects.equals(spots, PS_20.getcSpotsColor()) & PS_20.getFirst()) {
            playerShip.addPoints(20);
            PS_20.setVisibility(false);
            PS_20.setFirst(false);
        } else if (Objects.equals(spots, PS20_2.getcSpotsColor()) & PS20_2.getFirst()) {
            playerShip.addPoints(20);
            PS20_2.setVisibility(false);
            PS20_2.setFirst(false);
        } else if (Objects.equals(spots, PS_20_3.getcSpotsColor()) & PS_20_3.getFirst()) {
            playerShip.addPoints(20);
            PS_20_3.setVisibility(false);
            PS_20_3.setFirst(false);
        } else if (Objects.equals(spots, PS_20_4.getcSpotsColor()) & PS_20_4.getFirst()) {
            playerShip.addPoints(20);
            PS_20_4.setVisibility(false);
            PS_20_4.setFirst(false);
        } else if (Objects.equals(spots, PS_20_5.getcSpotsColor()) & PS_20_5.getFirst()) {
            playerShip.addPoints(20);
            PS_20_5.setVisibility(false);
            PS_20_5.setFirst(false);
        } else if (Objects.equals(spots, PS_40.getcSpotsColor()) & PS_40.getFirst()) {
            playerShip.addPoints(40);
            PS_40.setVisibility(false);
            PS_40.setFirst(false);
        } else if (Objects.equals(spots, PS40_2.getcSpotsColor()) & PS40_2.getFirst()) {
            playerShip.addPoints(40);
            PS40_2.setVisibility(false);
            PS40_2.setFirst(false);
        } else if (Objects.equals(spots, PS_60.getcSpotsColor()) & PS_60.getFirst()) {
            playerShip.addPoints(60);
            PS_60.setVisibility(false);
            PS_60.setFirst(false);
        } else if (Objects.equals(spots, PS_100.getcSpotsColor()) & PS_100.getFirst()) {
            playerShip.addPoints(100);
            PS_100.setVisibility(false);
            PS_100.setFirst(false);
        }
        if (!bVoitto) {
            PixelReader pr2 = levelChoise.getIKentta(playerShip.getLevel()).getPixelReader();
            Color argb = null;
            Color argb2 = null;
            Color argb3 = null;
            if (!bOutOfBounds) {
                argb = pr2.getColor((int) ivAlus.getLayoutX(), (int) ivAlus.getLayoutY());
                argb2 = pr2.getColor(((int) ivAlus.getLayoutX() + 1), ((int) ivAlus.getLayoutY() + 1));
                argb3 = pr2.getColor(((int) ivAlus.getLayoutX() - 1), ((int) ivAlus.getLayoutY() + 1));
            }
            // Jos alus lentää kentältä ulos, menetellään kuten aluksen törmäyksessä
            if (bOutOfBounds | !(Objects.equals(argb, C_POHJA)) | !(Objects.equals(argb2, C_POHJA)) | !(Objects.equals(argb3, C_POHJA))) {
                playerShip.addPoints(-10);
                if (playerShip.getShipLife() > 0) {
                    playerShip.setShipLife(playerShip.getShipLife() - 1);
                    if (playerShip.getLevel() == 1) {
                        ivAlus.setLayoutX(530);
                        ivAlus.setLayoutY(824);
                    } else if (playerShip.getLevel() == 2) {
                        ivAlus.setLayoutX(505);
                        ivAlus.setLayoutY(973);
                    } else if (playerShip.getLevel() == 3) {
                        ivAlus.setLayoutX(521);
                        ivAlus.setLayoutY(955);
                    }
                    playerShip.setSpeed(0);
                    playerShip.setHeading(0);
                    bOutOfBounds = false;
                } else { //Jos elämiä ei enää jäljellä niin peli päättyy.
                    bGameOver = true;
                    bOutOfBounds = false;
                    playerShip.setSpeed(0);
                    gameOver();
                }
            }
        }
    }

    /**
     * loadLevel asettaa kenttä-olion levelin alus-olion leveliksi, jotta PixelReaderit saavat oikean kuvan.
     * Samalla asetetaan Spots muuttujat alkutilaan odottamaan "kosketusta"
     */
    private void loadLevel() {
        if (playerShip.getLevel() != 1) {
            if (playerShip.getLevel() < levelChoise.MAXLEVEL + 1) {
                levelChoise.setLevel(playerShip.getLevel());
                setSpotsReady();
            } else {
                bGameOverSuccess = true;
                gameOver();

            }
        } else {
            levelChoise.setLevel(playerShip.getLevel());
            setSpotsReady();
        }
    }

    /**
     * Scenen asettamiseen metodi
     * @return Palauttaa scenen
     */
    public Scene getScLevel1() {
        return scLevel1;
    }


    /**
     * paneHud -paneelin asettelu ja rakentaminen
     * Paneeli sisältää pelaajalle näytettävät tiedot ikkunan oikeassa alanurkassa
     */
    private void paneHudCreate() {
        paneHud.setHgap(10);
        paneHud.setVgap(10);
        Text tPauseTip = new Text(lang.getStrPauseTip());
        tPauseTip.setFont(Font.font("Verdana",FontPosture.ITALIC,18));
        tPauseTip.setFill(Color.SILVER);

        Text tLifeHead = new Text(lang.getStrLife());
        tLifeHead.setFont(Font.font("Verdana",24));
        tLifeHead.setFill(Color.FIREBRICK);
        Text tPointsHead = new Text(lang.getStrPoints());
        tPointsHead.setFont(Font.font("Verdana",24));
        tPointsHead.setFill(Color.FIREBRICK);
        Text tLevelHead = new Text(lang.getStrLevel());
        tLevelHead.setFont(Font.font("Verdana",24));
        tLevelHead.setFill(Color.FIREBRICK);
        Text tLevel = new Text(String.valueOf(playerShip.getLevel()));
        tLevel.setFill(Color.GREEN);
        tLevel.setFont(Font.font("Verdana",FontWeight.BOLD,24));

        tLife.setFill(Color.GREEN);
        tLife.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,24));
        tPoints.setFill(Color.GOLD);
        tPoints.setFont(Font.font("Verdana",FontWeight.BOLD,FontPosture.ITALIC,24));
        tTime.setFill(Color.WHITE);
        tTime.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,24));

        paneHud.addRow(0,tPauseTip);
        paneHud.addRow(1,tTime);
        paneHud.addRow(2,tPointsHead,tLifeHead, tLevelHead);
        paneHud.addRow(3,tPoints,tLife,tLevel);
        paneHud.setAlignment(Pos.CENTER);
        paneHud.setPadding(new Insets(10));
        paneHud.setLayoutX(Paaohjelma.getScreenwidth()-300);
        paneHud.setLayoutY(Paaohjelma.getScreenheight()-240);
    }

    /**
     * pause -paneelin asettelu ja rakentaminen
     * @param panePause Paneeli, jolle rakennetaan
     * @param tfPause Tekstikenttä paneelin yläreunaan
     * @param btSaveXexit Nappula, jolla tallennetaan ja suljetaan peli
     * @param btExit Nappula, jolla suljetaan peli
     */
    private void pausePanelCreate(VBox panePause, TextField tfPause, Button btSaveXexit, Button btExit) {
        panePause.setVisible(false);
        panePause.setAlignment(Pos.CENTER);
        tfPause.setMouseTransparent(true);
        tfPause.setEditable(false);
        tfPause.setStyle("-fx-text-fill: black; -fx-font-size: 18px;-fx-background-color: DARKCYAN");
        tfPause.setAlignment(Pos.CENTER);

        btSaveXexit.setMinSize(200,30);
        btSaveXexit.setStyle("-fx-text-fill: green; -fx-font-size: 18px;-fx-background-color: black");
        btSaveXexit.setOnAction(gameSavexExit());

        btExit.setMinSize(200,30);
        btExit.setOnAction(e3 -> System.exit(0));
        btExit.setStyle("-fx-text-fill: green; -fx-font-size: 18px;-fx-background-color: black");
        panePause.setLayoutX(Paaohjelma.getScreenwidth()*0.5);
        panePause.setLayoutY(Paaohjelma.getScreenheight()*0.5);
    }

    /**
     * Lataa tallennetun pelin
     * @param tNewGame tälle tekstikentälle asetetaan jatkamisen ilmaisu
     * @param tLoadGame tämä tekstikenttä asetetaan näyttämään ilmaisu "ladattu"
     */
    private void loadPreviousGame(Text tNewGame, Text tLoadGame) {
        bLoaded = true;
        try {
            playerShip = Save.loadAlus();
            settingsObj = Save.loadSettings();
            saveSpots = Save.loadSpots();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        FxSound.setVolume(settingsObj.getFxSound());
        levelChoise.setLevel(playerShip.getLevel());
        tLoadGame.setDisable(true);
        tLoadGame.setText(lang.getStrLoaded());
        tLoadGame.setFill(CVALIKKOTEKSTIT_HOV);
        tNewGame.setText(lang.getStrContinue());
        PANE_KENTTA1.setVisible(false);
        PANE_KENTTA2.setVisible(false);
        PANE_KENTTA3.setVisible(false);
    }

    /**
     * Peli
     * @param coderPilot the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws Exception Virhe voi ilmetä applikaatiossa
     */
    @Override
    public void start(Stage coderPilot) throws Exception {
        preloadSounds(); //Soitetaan aluksi äänet voimakkuudella 0, jotta ne eivät lataile enää sitten itse pelissä.
        SpotTextLocations();
        paneGameOver = new StackPane(); //Paneeli, joka näytetään pelin päättyessä
        paneGameOver.setVisible(false);
        paneGameOver.setAlignment(Pos.CENTER);
        PANE_KENTTA2.setVisible(false);
        PANE_KENTTA3.setVisible(false);
        StackPane paneAloitus = new StackPane(); //Aloitusscenen pohjapaneeli jonka päällä muut paneelit ovat.
        paneAloitus.setStyle("-fx-background-color: black;");
        paneAloitus.setAlignment(Pos.CENTER);
        VBox vbValikko = new VBox(100); //Alkuvalikon paneeli.
        vbValikko.setPadding(new Insets(320,0,0,0));
        vbValikko.setAlignment(Pos.TOP_CENTER);

        Image IaloitusKuva = new Image(new FileInputStream("target/classes/Taustat/aloitusTausta.png"));
        ImageView ivAloitusTausta = new ImageView(IaloitusKuva);
        paneAloitus.getChildren().add(ivAloitusTausta);

        Text tOtsikko = new Text("CoderPilot");
        tOtsikko.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, FontPosture.ITALIC,50));
        tOtsikko.setFill(Color.SILVER);

        Text tNewGame = new Text(lang.getStrNewGame());
        tNewGame.setFont(valikkoFontti());
        tNewGame.setFill(C_VALIKKOTEKSTIT);

        Text tLoadGame = new Text(lang.getStrLoadGame());
        tLoadGame.setFont(valikkoFontti());
        tLoadGame.setFill(C_VALIKKOTEKSTIT);
        //Mikäli kaikkia lataustiedostoja ei löydy, lataaminen on estetty.
        if (!bLoadPossible){
            tLoadGame.setDisable(true);
            tLoadGame.setFill(Color.DARKGRAY);
        }

        Text tSettings = new Text(lang.getStrSettings());
        tSettings.setFont(valikkoFontti());
        tSettings.setFill(C_VALIKKOTEKSTIT);

        Text tCredits = new Text(lang.getStrCredits());
        tCredits.setFont(valikkoFontti());
        tCredits.setFill(C_VALIKKOTEKSTIT);

        //Alla rakennetaan paneStartMenu, joka sisältää alkuvalikon ja lisätään se aloitusskenen pohjapaneeliin
        vbValikko.getChildren().addAll(tNewGame,tLoadGame,tSettings,tCredits);
        StackPane paneStartMenu = new StackPane(tOtsikko,vbValikko);
        paneStartMenu.setAlignment(Pos.TOP_CENTER);
        paneAloitus.getChildren().add(paneStartMenu);
        paneStartMenu.setVisible(true);

        //Valikko. Alla asetellaan alkuvalikon teksteille toiminnot painettaessa

        // Peli alkaa (tai jatkuu)
        tNewGame.setOnMouseEntered(e-> tNewGame.setFill(CVALIKKOTEKSTIT_HOV)); //New Game (tai Continue)
        tNewGame.setOnMouseExited(e-> tNewGame.setFill(C_VALIKKOTEKSTIT));
        tNewGame.setOnMouseClicked(e -> {
            loadLevel(); //Asettaa oikean tason olioille

            //Varsinaisen pelin pohjapaneeli.
            Pane paneGame = new Pane();

            //Pause-paneeli
            VBox panePause = new VBox(2);
            panePause.requestFocus();
            TextField tfPause = new TextField("Pause");
            Button btSaveXexit = new Button(lang.getStrSaveXexit());
            Button btExit = new Button(lang.getStrExit());
            pausePanelCreate(panePause, tfPause, btSaveXexit, btExit); // Pause -paneelin tekeminen
            panePause.getChildren().addAll(tfPause,btSaveXexit,btExit);

            //Aluksen kuvan lataaminen
            File f2 = new File(playerShip.getShipImage());
            ImageView ivAlusKuva;
            if (f2.exists()) {
                Image iAlusKuva;
                try {
                    iAlusKuva = new Image(new FileInputStream(playerShip.getShipImage()));
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                ivAlusKuva = new ImageView(iAlusKuva);
            } else {
                ivAlusKuva = null;
                System.out.println("Aluksen kuva puuttuu Alukset kansiosta");
                System.exit(0);
            }

            setPsTextCol(); //Asetetaan PointSpots olioiden tekstikenttien värit
            prepareLevelObjects(ivAlusKuva); //Valmistellaan aluksen paikka kentän alussa, sekä PointSpots paikat ja näkyvyydet
            setSpotsIfLoaded(); //Asettaa PointSpots -olioden näkyvyydet ja "ensikosketuksen", kun jatketaan tallennettua peliä
            bLoaded = false;

            //Kentän Timelinen "pää"-keyframen käsittelijä
            EventHandler<ActionEvent> gameControl = e1 ->{
                if (bStartTimer){
                    bStartTimer = false;
                    startTime = Math.floor(System.currentTimeMillis() *0.001); //sekunnit
                }
                controlShip(getKcNappi(), playerShip);
                ivAlusKuva.setRotate(playerShip.getHeading());
                moveObjInImage(playerShip.getSpeed(),playerShip.getHeading(), ivAlusKuva);
                collisionShip(ivAlusKuva);
                tPoints.setText(String.valueOf(playerShip.getPoints()));
                tLife.setText(String.valueOf(playerShip.getShipLife()));
                tTime.setText(String.format("%.0f", playerShip.getTimeElapsed() + floor(System.currentTimeMillis() * 0.001) - startTime) + "s");
                if (bVoitto & !bGameOverSuccess){ //Sisältää kentän vaihtamisoperaatiot kun viimeistä kenttää ei ole läpäisty
                    playerShip.setLevel(playerShip.getLevel() + 1);
                    PANE_KENTTA1.setVisible(false);
                    PANE_KENTTA2.setVisible(false);
                    PANE_KENTTA3.setVisible(false);
                    if (playerShip.getLevel()== 2){
                        prepareLevel2();
                        ivAlusKuva.setLayoutX(505);
                        ivAlusKuva.setLayoutY(973);

                    } else if (playerShip.getLevel()==3) {
                        prepareLevel3();
                        ivAlusKuva.setLayoutX(521);
                        ivAlusKuva.setLayoutY(955);
                    }
                    loadLevel();
                    bVoitto = false;
                }
            };
            Timeline tlGame = new Timeline(new KeyFrame((Duration.millis(6)),gameControl));
            KeyFrame kfNappain = new KeyFrame(Duration.ZERO,e2 -> setKcNappi(KeyCode.B)); //Toinen keyframe katkomaan näppäinpainallusta. Tämä mahdollistaa näppäimen pohjassapitämisen.
            tlGame.getKeyFrames().add(kfNappain);
            tlGame.setCycleCount(Animation.INDEFINITE);

            if (bGameOverSuccess){
                tlGame.stop();
            }

            paneHud = new GridPane(); //Pelaajalle tietoja näyttävä paneeli
            paneHudCreate(); //Sisältää paneHudin tekemisen

            //Lisätään varsinaisen pelin pohjapaneeliin kaikki pelissä näkyvät paneelit
            paneGame.getChildren().addAll(PANE_KENTTA1, PANE_KENTTA2, PANE_KENTTA3,ivAlusKuva,panePause, PS_20.getTeksti(), PS20_2.getTeksti(), PS_20_3.getTeksti(), PS_20_4.getTeksti(), PS_20_5.getTeksti(), PS_40.getTeksti(), PS40_2.getTeksti(), PS_60.getTeksti(), PS_100.getTeksti(), paneHud, paneEnd, paneGameOver);

            GridPane paneFull = new GridPane(); // Käytetty vielä pohjapaneelin pohjapaneelina gridpanea, jolla on helppo asettaa peli keskelle kuvaa.
            paneFull.setStyle("-fx-background-color: black;");
            paneFull.setAlignment(Pos.CENTER);
            paneFull.getChildren().add(paneGame);

            setScLevel1(new Scene(paneFull, Paaohjelma.getScreenwidth(), Paaohjelma.getScreenheight())); //Annetaan Scenen vaihtajalle uusi, varsinaisen pelaamisen sisältävä scene

            // Pelin näppäinpainallusten käsittely. Käynnistää pelin Timelinen. Käsittelijä sisältää "Pause" -toiminnon. Muuten set -metodi muualla ohjelmassa tarvittaville näppäimistösyötteille.
            EventHandler<KeyEvent> nappain = e2 -> {
                if (!bGameOver & !bGameOverSuccess){
                    if (bGameRunState & e2.getCode() == KeyCode.P) {
                        bGameRunState = false;
                        panePause.setVisible(true);
                        playerShip.setTimeElapsed(playerShip.getTimeElapsed()+(floor(System.currentTimeMillis()*0.001)-startTime));
                        tlGame.pause();
                        bStartTimer = true;
                    } else if (!(bGameRunState) | ((panePause.isVisible() & e2.getCode() == KeyCode.P))) {
                        bGameRunState = true;
                        panePause.setVisible(false);
                        tlGame.play();
                    } else {
                        setKcNappi(e2.getCode());
                    }
                } else {tlGame.stop();}
            };

            scLevel1.setOnKeyPressed(nappain); //Pelin näppäinten painallukset

            paneGame.requestFocus();
            coderPilot.setScene(getScLevel1());
            coderPilot.setScene(scLevel1); // Vaihtaa aloitus-scenen pelin sceneen
            coderPilot.setFullScreen(true);
        });


        // Tallennetun pelin lataus
        tLoadGame.setOnMouseEntered(e-> tLoadGame.setFill(CVALIKKOTEKSTIT_HOV)); //Load Game
        tLoadGame.setOnMouseExited(e-> tLoadGame.setFill(C_VALIKKOTEKSTIT));
        tLoadGame.setOnMouseClicked(e -> loadPreviousGame(tNewGame, tLoadGame));

        // Asetusten ja aluksen ominaisuuksien asettaminen
        tSettings.setOnMouseEntered(e-> tSettings.setFill(CVALIKKOTEKSTIT_HOV)); //Settings
        tSettings.setOnMouseExited(e-> tSettings.setFill(C_VALIKKOTEKSTIT));
        tSettings.setOnMouseClicked(e -> {
            StackPane paneSettings = new StackPane(); //Asetusten pohjapaneeli

            Image IsettingsKuva; //Asetusten taustakuva
            try {
                IsettingsKuva = new Image(new FileInputStream("target/classes/Taustat/settingsTausta.png"));
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            ImageView IVsettingsTausta = new ImageView(IsettingsKuva);
            paneSettings.getChildren().add(IVsettingsTausta);

            paneStartMenu.setVisible(false);
            paneSettings.setVisible(true);

            //Paluu
            Text tPaluu = new Text(lang.getStrReturn());
            tPaluu.setFont(valikkoFontti());
            tPaluu.setFill(C_VALIKKOTEKSTIT);
            tPaluu.setOnMouseEntered(r-> tPaluu.setFill(CVALIKKOTEKSTIT_HOV));
            tPaluu.setOnMouseExited(r-> tPaluu.setFill(C_VALIKKOTEKSTIT));
            tPaluu.setOnMouseClicked(r ->{
                paneSettings.setVisible(false);
                paneStartMenu.setVisible(true);
            });

            // Kielen valinta
            RadioButton rbFinnish = new RadioButton("Suomi");
            rbFinnish.setStyle("-fx-color: #323232; ");
            rbFinnish.setTextFill(C_VALIKKOTEKSTIT);
            rbFinnish.setFont(Font.font(20));
            RadioButton rbEnglish = new RadioButton("English");
            rbEnglish.setStyle("-fx-color: #323232; ");
            rbEnglish.setTextFill(C_VALIKKOTEKSTIT);
            rbEnglish.setFont(Font.font(20));
            ToggleGroup tgLanguage = new ToggleGroup(); //Kielen valinnan ToggleGroup
            rbFinnish.setToggleGroup(tgLanguage);
            rbEnglish.setToggleGroup(tgLanguage);
            RadioButton LangValinta = null;
            if (Objects.equals(lang.getKieliValinta(), "Finnish")){
                LangValinta = rbFinnish;
            } else if (Objects.equals(lang.getKieliValinta(), "English")) {
                LangValinta = rbEnglish;
            }
            tgLanguage.selectToggle(LangValinta);
            VBox vbLanguageSetting = new VBox(20,rbFinnish,rbEnglish);
            vbLanguageSetting.setAlignment(Pos.CENTER);
            vbLanguageSetting.setPadding(new Insets(10,0,0,0));
            Label lbLangSet = new Label(lang.getStrChooseLang(),vbLanguageSetting);
            lbLangSet.setFont(Font.font(22));
            lbLangSet.setTextFill(C_VALIKKOTEKSTIT);
            lbLangSet.setContentDisplay(ContentDisplay.BOTTOM);
            lbLangSet.setPadding(new Insets(60,0,0,0));

            // Aluksen nimen valinta
            TextField tfShipName = new TextField(playerShip.getName());
            tfShipName.setStyle("-fx-text-fill: white; -fx-fonr-size: 18px;");
            try {
                tfShipName.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream("target/classes/Taustat/shipName.png")),SPACE,SPACE,CENTER,DEFAULT)));
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            tfShipName.setOnKeyTyped(e2 -> playerShip.setName(tfShipName.getText()));
            Label lbShipName = new Label(lang.getstrNameShip() + ":",tfShipName);
            lbShipName.setContentDisplay(ContentDisplay.RIGHT);
            lbShipName.setFont(Font.font(22));
            lbShipName.setTextFill(C_VALIKKOTEKSTIT);

            // Vaikeustaso
            Text tDifficulty = new Text(lang.getStrDifficulty());
            tDifficulty.setFont(Font.font(22));
            tDifficulty.setFill(C_VALIKKOTEKSTIT);
            RadioButton rbEasy = new RadioButton(lang.getStrEasy());
            rbEasy.setTextFill(C_VALIKKOTEKSTIT);
            rbEasy.setFont(Font.font(20));
            RadioButton rbNormal = new RadioButton(lang.getStrNormal());
            rbNormal.setTextFill(C_VALIKKOTEKSTIT);
            rbNormal.setFont(Font.font(20));
            RadioButton rbHard = new RadioButton(lang.getStrHard());
            rbHard.setTextFill(C_VALIKKOTEKSTIT);
            rbHard.setFont(Font.font(20));
            RadioButton rbTest = new RadioButton(lang.getStrTesti());
            rbTest.setTextFill(C_VALIKKOTEKSTIT);
            rbTest.setFont(Font.font(20));
            rbEasy.setStyle("-fx-color: #323232; ");
            rbNormal.setStyle("-fx-color: #323232; ");
            rbHard.setStyle("-fx-color: #323232; ");
            rbTest.setStyle("-fx-color: #323232; ");
            RadioButton DiffValinta = null;
            if (Objects.equals(playerShip.getDifficulty(), "Easy")){
                DiffValinta = rbEasy;
            } else if (Objects.equals(playerShip.getDifficulty(), "Normal")) {
                DiffValinta = rbNormal;
            } else if (Objects.equals(playerShip.getDifficulty(), "Hard")) {
                DiffValinta = rbHard;
            } else if (Objects.equals(playerShip.getDifficulty(), "Test")) {
                DiffValinta = rbTest;
            }
            ToggleGroup tgDifficulty = new ToggleGroup(); //Vaikeustason valinnan ToggleGroup
            rbEasy.setToggleGroup(tgDifficulty);
            rbNormal.setToggleGroup(tgDifficulty);
            rbHard.setToggleGroup(tgDifficulty);
            rbTest.setToggleGroup(tgDifficulty);
            tgDifficulty.selectToggle(DiffValinta);
            tgDifficulty.selectedToggleProperty().addListener(r-> {
                if (rbEasy.isSelected()) {
                    playerShip.setDifficultySettings("Easy");
                } else if (rbNormal.isSelected()) {
                    playerShip.setDifficultySettings("Normal");
                } else if (rbHard.isSelected()) {
                    playerShip.setDifficultySettings("Hard");
                } else if (rbTest.isSelected()) {
                    playerShip.setDifficultySettings("Test");
                }
            });
            if (bLoaded) {
                rbEasy.setDisable(true);
                rbNormal.setDisable(true);
                rbHard.setDisable(true);
                rbTest.setDisable(true);
            }

            //Aluksen valinta
            Text tShipChoise = new Text(lang.getStrChooseShipImage());
            tShipChoise.setFont(Font.font(22));
            tShipChoise.setFill(C_VALIKKOTEKSTIT);
            RadioButton rbAlusMusta = new RadioButton();
            rbAlusMusta.setStyle("-fx-color: #323232; ");
            rbAlusMusta.setFont(Font.font(20));
            RadioButton rbAlusPinkki = new RadioButton();
            rbAlusPinkki.setStyle("-fx-color: #323232; ");
            rbAlusPinkki.setFont(Font.font(20));
            RadioButton rbAlusOH = new RadioButton();
            rbAlusOH.setStyle("-fx-color: #323232; ");
            rbAlusOH.setFont(Font.font(20));

            ToggleGroup tgShipChoise = new ToggleGroup(); //Aluksen valinnan ToggleGroup
            rbAlusMusta.setToggleGroup(tgShipChoise);
            rbAlusPinkki.setToggleGroup(tgShipChoise);
            rbAlusOH.setToggleGroup(tgShipChoise);

            RadioButton ShipValinta = null;
            if (Objects.equals(playerShip.getShipImage(), "target/classes/Alukset/alusMusta.png")){
                ShipValinta = rbAlusMusta;
            } else if (Objects.equals(playerShip.getShipImage(), "target/classes/Alukset/alusPinkki.png")) {
                ShipValinta = rbAlusPinkki;
            } else if (Objects.equals(playerShip.getShipImage(), "target/classes/Alukset/alusOH.png")) {
                ShipValinta = rbAlusOH;
            }
            tgShipChoise.selectToggle(ShipValinta);

            tgShipChoise.selectedToggleProperty().addListener(r-> {
                if (rbAlusMusta.isSelected()) {
                    playerShip.setShipImage("target/classes/Alukset/alusMusta.png");
                } else if (rbAlusPinkki.isSelected()) {
                    playerShip.setShipImage("target/classes/Alukset/alusPinkki.png");
                } else if (rbAlusOH.isSelected()) {
                    playerShip.setShipImage("target/classes/Alukset/alusOH.png");
                }
            });

            HBox hbAlusMusta;
            HBox hbAlusPinkki;
            HBox hbAlusOH;
            try {
                hbAlusMusta = new HBox(10,rbAlusMusta,new ImageView(new Image(new FileInputStream("target/classes/Alukset/alusMusta.png"))));
                hbAlusMusta.setAlignment(Pos.CENTER);
                hbAlusPinkki = new HBox(10,rbAlusPinkki,new ImageView(new Image(new FileInputStream("target/classes/Alukset/alusPinkki.png"))));
                hbAlusPinkki.setAlignment(Pos.CENTER);
                hbAlusOH = new HBox(10,rbAlusOH,new ImageView(new Image(new FileInputStream("target/classes/Alukset/alusOH.png"))));
                hbAlusOH.setAlignment(Pos.CENTER);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            VBox vbShipChoise = new VBox(20,tShipChoise, hbAlusMusta,hbAlusPinkki,hbAlusOH);
            vbShipChoise.setAlignment(Pos.CENTER);

            // Näppäimistöasettelun valinta
            Text tSelKeyLay = new Text(lang.getStrSelectKeyLay());
            tSelKeyLay.setFont(Font.font(22));
            tSelKeyLay.setFill(C_VALIKKOTEKSTIT);
            RadioButton rbWasd = new RadioButton();
            rbWasd.setStyle("-fx-color: #323232; ");
            rbWasd.setFont(Font.font(20));
            RadioButton rbArrows = new RadioButton();
            rbArrows.setStyle("-fx-color: #323232; ");
            rbArrows.setFont(Font.font(20));

            ToggleGroup tgKeyLay = new ToggleGroup();
            rbWasd.setToggleGroup(tgKeyLay);
            rbArrows.setToggleGroup(tgKeyLay);
            RadioButton KeyLayValinta = null;
            if (Objects.equals(settingsObj.getKeyLayout(), "wasd"))
                KeyLayValinta = rbWasd;
            else if (Objects.equals(settingsObj.getKeyLayout(), "arrows")) {
                KeyLayValinta = rbArrows;
            }
            tgKeyLay.selectToggle(KeyLayValinta);
            tgKeyLay.selectedToggleProperty().addListener(e2->{
                if (rbWasd.isSelected()) {
                    settingsObj.setKeyLayout("wasd");
                } else if (rbArrows.isSelected()) {
                    settingsObj.setKeyLayout("arrows");
                }
            });

            HBox hbWasd;
            HBox hbArrows;
            try {
                ImageView ivWasd = new ImageView(new Image(new FileInputStream("target/classes/wasd.png")));
                ivWasd.setFitHeight(150);
                ivWasd.setFitWidth(150);
                hbWasd = new HBox(10,rbWasd,ivWasd);
                hbWasd.setAlignment(Pos.CENTER);
                ImageView ivArrows = new ImageView(new Image(new FileInputStream("target/classes/arrows.png")));
                ivArrows.setFitHeight(150);
                ivArrows.setFitWidth(150);
                hbArrows = new HBox(10,rbArrows,ivArrows);
                hbArrows.setAlignment(Pos.CENTER);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            VBox vbKeyLay = new VBox(20, tSelKeyLay, hbWasd,hbArrows);
            vbKeyLay.setAlignment(Pos.CENTER);

            // Äänenvoimakkuuden säätö
            Slider sldFxAani = new Slider();
            sldFxAani.setStyle("-fx-color: #323232;");
            sldFxAani.setMin(0);
            sldFxAani.setMax(1);
            sldFxAani.setMaxWidth(200);
            sldFxAani.setValue(settingsObj.getFxSound());
            sldFxAani.valueProperty().addListener(t -> {
                settingsObj.setFxSound(sldFxAani.getValue());
                FxSound.setVolume(sldFxAani.getValue());
            });
            sldFxAani.setPadding(new Insets(10,0,0,0));
            Label lbFxAani = new Label(lang.getStrTehosAani(),sldFxAani);
            lbFxAani.setContentDisplay(ContentDisplay.BOTTOM);
            lbFxAani.setFont(Font.font(22));
            lbFxAani.setTextFill(C_VALIKKOTEKSTIT);

            // Kielen vaihdon kuuntelija
            tgLanguage.selectedToggleProperty().addListener(r-> {
                if (rbEnglish.isSelected()) {
                    lang = new Language("English");
                } else if (rbFinnish.isSelected()) {
                    lang = new Language("Finnish");
                }
                //Tässä asetetaan kaikki tekstit, jotka on nähtävissä kun kielenvalinta on vielä tehtävissä (Eli ennen peliä).
                // Näin tekstit päivittyy.
                tCredits.setText(lang.getStrCredits());
                tSettings.setText(lang.getStrSettings());
                if(bLoaded) {
                    tNewGame.setText(lang.getStrContinue());
                    tLoadGame.setText(lang.getStrLoaded());
                } else {
                    tNewGame.setText(lang.getStrNewGame());
                    tLoadGame.setText(lang.getStrLoadGame());
                }
                tPaluu.setText(lang.getStrReturn());
                tDifficulty.setText(lang.getStrDifficulty());
                rbEasy.setText(lang.getStrEasy());
                rbNormal.setText(lang.getStrNormal());
                rbHard.setText(lang.getStrHard());
                rbTest.setText(lang.getStrTesti());
                lbFxAani.setText(lang.getStrTehosAani());
                lbLangSet.setText(lang.getStrChooseLang());
                tShipChoise.setText(lang.getStrChooseShipImage());
                tSelKeyLay.setText(lang.getStrSelectKeyLay());
                lbShipName.setText(lang.getstrNameShip() + ":");
            });

            //Lopullinen asetusten asettelu seuraavissa paneeleissa.
            VBox vbDifficulty = new VBox(20,tDifficulty,rbTest,rbEasy,rbNormal,rbHard);
            vbDifficulty.setAlignment(Pos.CENTER);

            HBox hbDifnKey = new HBox(150,vbDifficulty,vbShipChoise,vbKeyLay);
            hbDifnKey.setAlignment(Pos.CENTER);

            VBox vbSettingsMain = new VBox(80,lbLangSet,lbShipName,hbDifnKey,lbFxAani,tPaluu);
            vbSettingsMain.setAlignment(Pos.CENTER);
            paneSettings.getChildren().add(vbSettingsMain);

            paneAloitus.getChildren().add(paneSettings);
        });

        // Tekijän esittely
        tCredits.setOnMouseEntered(e-> {
            if (tCredits.getOpacity() == 0.0 | tCredits.getOpacity() == 1.0) {
                tCredits.setFill(CVALIKKOTEKSTIT_HOV);
            }
        }); //Credits
        tCredits.setOnMouseExited(e-> {
            if (tCredits.getOpacity() == 0.0 | tCredits.getOpacity() == 1.0) {
                tCredits.setFill(C_VALIKKOTEKSTIT);
                tCredits.setText(lang.getStrCredits());
                tCredits.setOpacity(1.0);
            }
        });
        tCredits.setOnMouseClicked(e -> {
                        tCredits.setText("Olli Hilke");
                        tCredits.setFill(new Color(random(),random(),random(),1));
                        double opacity = tCredits.getOpacity();

                        FadeTransition fade = new FadeTransition(Duration.millis(1500),tCredits);
                        Animation.Status animationStatus = fade.getStatus();
                        fade.setFromValue(1.0);
                        fade.setToValue(0.0);

                        if ((int) opacity == 0) {
                            return;
                        }
                        if (animationStatus == Animation.Status.RUNNING) {
                            return;
                        }
                        if (animationStatus == Animation.Status.STOPPED) {
                            fade.play();
                        }
        });

        //aloitus scenen asettaminen.
        Scene aloitus = new Scene(paneAloitus, Paaohjelma.getScreenwidth(), Paaohjelma.getScreenheight());
        coderPilot.setTitle("CoderPilot");
        coderPilot.setScene(aloitus);
        coderPilot.setFullScreen(true);
        coderPilot.show();
    }

    /**
     * Luokan pääohjelma. CoderPilot -peliä ei käynnistetä tästä.
     * @param args parametriä ei käytetä
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}