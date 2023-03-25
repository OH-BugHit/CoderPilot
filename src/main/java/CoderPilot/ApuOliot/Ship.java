package CoderPilot.ApuOliot;

import java.io.Serializable;
import java.util.Objects;

/**
 * CoderPilot -pelin alus
 * @author Olli Hilke
 */
public class Ship implements Serializable {

    /**
     * Jarrutuksen kertoimen maksimiarvo.
     */
    private static final double MAX_BRAKE_MULTIPLIER = 2.5;

    /**
     * Nopeudenmuutoksen kertoimen maksimiarvo.
     */
    private static final double MAX_SPEED_MULTIPLIER = 1;

    /**
     * Kerätyt pisteet
     */
    private int points = 0;

    /**
     * Kenttä joka on saavutettu
     */
    private int level = 1;

    /**
     * Vaikeusaste
     */
    private String difficulty = "Normal";

    /**
     * Aluksen nopeus
     */
    private double speed;

    /**
     * Jarrutuksen kerroin pehmeään jarrutukseen. Asetetaan Aloitus -luokassa
     */
    private double brakeKerroin = 0;

    /**
     * Nopeuden muutoksen kerroin kiihdytykseen. Asetetaan Aloitus -luokassa
     */
    private double speedMultiplier = 0;

    /**
     * Aluksen maksiminopeus
     */
    private double maxSpeed;

    /**
     * Aluksen rotaatio yhdellä käskyllä. "Kääntymisnopeus"
     */
    private double turnSpeed = 8;

    /**
     * Aluksen suuntima
     */
    private double heading;

    /**
     * Aluksen nimi, esim Player1
     */
    private String name;

    /**
     * Aluksen elämät, eli monta törmäystä kestää vielä
     */
    private int shipLife;

    /**
     * Aluksen kuvan tiedostopolku
     */
    private String shipImage;

    /**
     * Aluksen kulunut peliaika. Käytetään tallentamaan välillä käytetty aika kun peli on pausella tai muuten keskeytetty.
     */
    private double timeElapsed = 0;

    /**
     * Aluksen kuluneen peliajan getteri
     * @return Palauttaa aluksen kuluneen peliajan
     */
    public double getTimeElapsed() {
        return timeElapsed;
    }

    /**
     * Asettaa kuluneen peliajan alukselle
     * @param timeElapsed Kulunut peliaika sekuntteina
     */
    public void setTimeElapsed(double timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    /**
     * Aluksen parametriton alustaja
     */
    public Ship(){}

    /**
     * Metodi asettaa elämät, kääntönopeuden, maksiminopeuden ja vaikeusasteen
     * @param vaikeusAste Haluttu vaikeusaste. "Easy", "Normal", "Hard", muuten "Test"
     */
    public void setDifficultySettings(String vaikeusAste){
        if (Objects.equals(vaikeusAste, "Easy")){
            this.difficulty = "Easy";
            this.shipLife = 15;
            this.turnSpeed = 8;
            this.maxSpeed = 6;

        } else if (Objects.equals(vaikeusAste, "Normal")) {
            this.difficulty = "Normal";
            this.shipLife = 10;
            this.turnSpeed = 8;
            this.maxSpeed = 10;
        } else if (Objects.equals(vaikeusAste,"Hard")) {
            this.difficulty = "Hard";
            this.shipLife = 5;
            this.turnSpeed = 8;
            this.maxSpeed = 12;
        } else {
            this.difficulty = "Test";
            this.shipLife = 99;
            this.turnSpeed = 12;
            this.maxSpeed = 15;
        }
    }

    /**
     * Luokan Ship alustaja
     * @param name Haluttu nimi.
     * @param shipImage Aluksen kuvan tiedostopolku
     */
    public Ship(String name, String shipImage) {
        this.name = name;
        this.shipImage = shipImage;
        this.heading = 0;
        this.speed = 0;
        switch (difficulty) {
            case "Easy" -> {
                this.shipLife = 15;
                this.maxSpeed = 6;
            }
            case "Normal" -> {
                this.shipLife = 10;
                this.maxSpeed = 10;
            }
            case "Hard" -> {
                this.shipLife = 5;
                this.maxSpeed = 12;
            }
            default -> {
                this.shipLife = 99;
                this.maxSpeed = 15;
            }
        }
    }

    /**
     * Getteri aluksen vaikeusasteelle
     * @return Palauttaa vaikeusasteen Stringinä
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Metodi pisteiden muuttamiseen
     * @param pisteet Asetettavat pisteet. Huom! Ei plussaa eikä vähennä vaan asettaa pisteet.
     *                Erillinen metodi pisteiden lisäämiseen on addPoints().
     */
    public void setPoints(int pisteet){
        this.points = pisteet;
    }

    /**
     * Metodi pisteiden hakemiseen alukselta
     * @return Palauttaa aluksen pisteet
     */
    public int getPoints() {return points;}

    /**
     * Lisää pisteitä alukselle
     * @param pisteet Lisättävät pisteet. Vähentää pisteitä jos annetaan negatiivinen arvo.
     */
    public void addPoints(int pisteet){
        this.points += pisteet;
    }

    /**
     * Aluksen nimen palauttava metodi
     * @return Palauttaa aluksen nimen
     */
    public String getName() {
        return name;
    }

    /**
     * Aluksen nimen asettava metodi
     * @param name Haluttu nimi
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Aluksen jäljelläolevien elämien getteri
     * @return Palauttaa aluksen jäljellä olevat elämät
     */
    public int getShipLife() {
        return shipLife;
    }

    /**
     * Aluksen elämien setteri
     * @param shipLife Asetettava elämien määrä
     */
    public void setShipLife(int shipLife) {
        this.shipLife = shipLife;
    }

    /**
     * Aluksen saavuttaman kentän getteri
     * @return Palauttaa aluksen saavuttaman kentän numeron
     */
    public int getLevel() {
        return level;
    }

    /**
     * Aluksen saavuttaman kentän asetus
     * @param level Kentän numero
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Aluksen käyttämän kuvan getteri
     * @return Palauttaa tiedostopolun aluksen kuvalle
     */
    public String getShipImage() {
        return shipImage;
    }

    /**
     * Metodi aluksen kuvan tiedostopolun asettamiseen
     * @param shipImage Tiedostopolku aluksen kuvaan. Suositettu koko 16x15px
     */
    public void setShipImage(String shipImage) {
        this.shipImage = shipImage;
    }

    /**
     * Aluksen nopeuden palauttava metodi
     * @return Aluksen nopeus
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Aluksen nopeuden asettava metodi
     * @param speed ALuksen uusi nopeus
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Aluksen suuntiman palauttava metodi
     * @return Palauttaa aluksen suuntiman
     */
    public double getHeading() {
        return heading;
    }

    /**
     * ALuksen suuntiman asettava metodi
     * @param heading Asettaa aluksen uuden suuntiman
     */
    public void setHeading(double heading) {
        this.heading = heading;
    }

    /**
     * Aluksen kiihdysarvon palauttava metodi
     * @return Aluksen kiihdytyksen kerroin
     */
    public double getSpeedMultiplier() {
        return speedMultiplier;
    }

    /**
     * Aluksen kiihdytyksen kertoimen asettava metodi
     * @param speedMultiplier Asetettava kiihtyvyyden kerroin
     */
    public void setSpeedMultiplier(double speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }

    /**
     * Aluksen maksiminopeuden kertova metodi
     * @return Palauttaa aluksen maksiminopeuden
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Aluksen kääntönopeuden palauttava metodi
     * @return Aluksen kääntönopeus (rotaatio yhdellä käskyllä)
     */
    public double getTurnSpeed() {
        return turnSpeed;
    }

    /**
     * Aluksen hidastamisen kertoimen palauttava metodi
     * @return Palauttaa aluksen hidastuskertoimen
     */
    public double getBrakeKerroin() {
        return brakeKerroin;
    }

    /**
     * Aluksen hidastuksen kertoimen asettava metodi
     * @param brakeKerroin Asetettava aluksen hidastuskerroin
     */
    public void setBrakeKerroin(double brakeKerroin) {
        this.brakeKerroin = brakeKerroin;
    }

    /**
     * Aluksen maksimi hidastuskertoimen palauttava metodi
     * @return Palauttaa suurimman sallitun hidastuskertoimen
     */
    public static double getMaxBrakeMultiplier() {
        return MAX_BRAKE_MULTIPLIER;
    }

    /**
     * Aluksen suurimman nopeuskertoimen palauttava metodi
     * @return Palauttaa suurimman sallitun nopeuskertoimen
     */
    public static double getMaxSpeedMultiplier() {
        return MAX_SPEED_MULTIPLIER;
    }
}