/* Teemu Soini
 * a660929
 * Olio-ohjelmointi
 * Harjoitusty�
 */
package sokkelo;
import apulaiset.*;

public class Robotti extends Oliot implements Suunnallinen {

    private char suunta;

    //Rakentajat.
    public Robotti(int rivi, int sarake, int voima, char suunta) {
        super(rivi, sarake, voima);
        this.suunta = suunta;
    }

    public Robotti() {
        super(0, 0, 0);
        suunta = 'e';
    }

    // Aksessorit.
    public char suunta() {
        return suunta;
    }

    public void suunta(char ilmansuunta) {
        suunta = ilmansuunta;
    }

    // Metodi tulostamaan olion merkkijono esitys.
    public String toString() {
        String apu = "   ";
        return super.toString() + suunta()+ apu + EROTIN;
    }

}