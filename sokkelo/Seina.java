/* Teemu Soini
 * a660929
 * Olio-ohjelmointi
 * Harjoitusty�
 */

package sokkelo;
import apulaiset.*;

public class Seina extends Sokkelo implements Paikallinen {

    // Attribuutit.

    // Rakentajat.
    public Seina(int rivi, int sarake) {
        super(rivi, sarake);
    }

    public Seina() {
        rivi = 0;
        sarake = 0;
    }

    @Override
    public char getSymboli() {
        symboli = '.';
        return symboli;
    }

    // Seinään ei voi asettaa asioita, joten palautetaan false.
    public boolean sallittu() {
        return false;
    }
}