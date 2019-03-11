/* Teemu Soini
 * a660929
 * Olio-ohjelmointi
 * Harjoitusty�
 */
package sokkelo;
import apulaiset.*;

public abstract class Sokkelo implements Paikallinen {

    // Attribuutit.
    int rivi;
    int sarake;
    char symboli;

    protected final String EROTIN = "|";

    // Aksessorit.
    public int rivi() {
        return rivi;
    }

    public int sarake() {
        return sarake;
    }

    // Rakentajat.
    public void rivi(int rivi) {
        this.rivi = rivi;
    }

    public void sarake(int sarake) {
        this.sarake = sarake;
    }

    // Parametrillinen rakentaja.
    public Sokkelo(int rivi, int sarake) {
        this.rivi = rivi;
        this.sarake = sarake;
    }

    // Parametriton rakentaja.
    public Sokkelo() {
        rivi = 0;
        sarake = 0;
    }

    // Aksessori symbolille.

    public char getSymboli() {
        return symboli;
    }


    public boolean sallittu() {
        return false;
    }

    // Metodi tulostamaan olion merkkijono esitys.
    public String toString() {
        String nimi = getClass().getSimpleName();
        String apu = "";
        String apu2 = "";
        if (nimi.equals("Seina")) {
            nimi = "Seina    ";
            
        }
        if (nimi.equals("Kaytava")) {
            nimi = "Kaytava  ";
        }
        if (nimi.equals("Monkija")) {
            nimi = "Monkija  ";
        }
        if (nimi.equals("Robotti")) {
            nimi = "Robotti  ";
        }
        if (nimi.equals("Esine")) {
            nimi = "Esine    ";
        }
        if (rivi > 9 && rivi < 100) {
            apu = "  ";
        }
        if (rivi <10) {
            apu = "   ";
        }
        if (sarake > 9 && sarake < 100) {
            apu2 = "  ";
        }
        if (sarake < 10) {
            apu2 = "   ";
        }
        
        return nimi + EROTIN + rivi + apu + EROTIN + sarake + apu2 + EROTIN;
        
    }
}