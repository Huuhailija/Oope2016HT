/* Teemu Soini
 * a660929
 * Olio-ohjelmointi
 * Harjoitusty�
 */
package sokkelo;
import apulaiset.*;

public class Monkija extends Oliot implements Suunnallinen {


    // Omalista instance, johonka tehdään esinevarasto.
    OmaLista esineVarasto = new OmaLista();

    // Attribuutit.
    private char suunta;

    //Parametrillinen rakentaja.
    public Monkija(int rivi, int sarake, int voima, char suunta) {
        super(rivi, sarake, voima);
        this.suunta = suunta;
    }

    // Parametritön rakentaja.
    public Monkija() {
        super(0, 0, 0);
        suunta = 'p';
    }

    // Aksessorit.
    public char suunta() {
        return suunta;
    }

    public void suunta(char ilmansuunta) {
        suunta = ilmansuunta;
    }

    public String toString() {
        String apu = "   ";
        return super.toString() + suunta()+ apu + EROTIN;
    }

    // Apumetodi varastoimaan esineitä.
    public void lisaa(Object uusiOsa) {
        esineVarasto.lisaa(uusiOsa);
    }

    // Apumetodi päivittämään esineiden koordinaatteja seikkailijan mukana.
       public void paivita(int rivi, int sarake) {
           for (int i = 0; i < esineVarasto.koko(); i++) {
               if (esineVarasto.alkio(i) instanceof Esine) {
                    Esine esine = (Esine)esineVarasto.alkio(i);
                    esine.rivi(rivi);
                    esine.sarake(sarake);
               }
           }
        }
}
