/* Teemu Soini
 * a660929
 * Olio-ohjelmointi
 * Harjoitusty�
 */
package sokkelo;
import apulaiset.*;
import fi.uta.csjola.oope.lista.*;

public class Esine extends Oliot {


    // Parametrillinen rakentaja.
    public Esine(int rivi, int sarake, int voima) {
        super(rivi, sarake, voima);
    }

    // Parametritön rakentaja.
    public Esine() {
        super(0, 0, 0);
    }

    // Aksessorit.

    public void rivi(int rivi) {
        this.rivi = rivi;
    }

    public void sarake(int sarake) {
        this.sarake = sarake;
    }

    public int rivi() {
        return rivi;
    }

    public int sarake() {
        return sarake;
    }

}