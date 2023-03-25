package CoderPilot.ApuOliot;

import javafx.scene.input.KeyCode;

import java.io.Serializable;
import java.util.Objects;

/**
 * Luokka yleisten asetusten hallintaan
 * @author Olli Hilke
 */
public class Settings implements Serializable {

    /**
     * Luokan "settings" -alustaja
     */
    public Settings() {}

    /**
     * Valittu näppäimistöasettelu. Oletus "arrows"
     */
    private String keyLayout = "arrows";

    /**
     * Äänitehosteiden voimakkuus. Oletus 50
     */
    private double fxSound = 50;

    /**
     * Getteri äänenvoimakkuudelle
     * @return Palauttaa äänenvoimakkuuden tason. 0-1.0
     */
    public double getFxSound() {
        return this.fxSound;
    }

    /**
     * Asettaa äänenvoimakkuuden asetuksiin
     * @param fxSound 0-100
     */
    public void setFxSound(double fxSound) {
        this.fxSound = fxSound;
    }

    /**
     * Valitun näppäimistöasettelun getteri
     * @return Palauttaa asetetun näppäimistöasettelun
     */
    public String getKeyLayout() {
        return this.keyLayout;
    }

    /**
     * Asettaa näppäimistöasettelun asetuksiin
     * @param keyLayout "arrows" tai “wasd”
     */
    public void setKeyLayout(String keyLayout) {
        this.keyLayout = keyLayout;
    }

    /**
     * Palauttaa "ylös" -näppäimen valitun näppäimistöasettelun mukaan
     * @return Keycode.Up jos "arrows" Keycode.W jos "wasd"
     */
    public KeyCode getUpKey() {
        if (Objects.equals(this.keyLayout, "wasd")) {
            return KeyCode.W;
        } else return KeyCode.UP;
    }

    /**
     * Palauttaa "alas" -näppäimen valitun näppäimistöasettelun mukaan
     * @return Keycode.Down jos "arrows" Keycode.S jos "wasd"
     */
    public KeyCode getDownKey() {
        if (Objects.equals(this.keyLayout, "wasd")) {
            return KeyCode.S;
        } else return KeyCode.DOWN;
    }

    /**
     * Palauttaa "Vasen" -näppäimen valitun näppäimistöasettelun mukaan
     * @return Keycode.Left jos "arrows" Keycode.A jos "wasd"
     */
    public KeyCode getLeftKey() {
        if (Objects.equals(this.keyLayout, "wasd")) {
            return KeyCode.A;
        } else return KeyCode.LEFT;
    }

    /**
     * Palauttaa "Oikea" -näppäimen valitun näppäimistöasettelun mukaan
     * @return Keycode.Right jos "arrows" Keycode.D jos "wasd"
     */
    public KeyCode getRightKey() {
        if (Objects.equals(this.keyLayout, "wasd")) {
            return KeyCode.D;
        } else return KeyCode.RIGHT;
    }
}
