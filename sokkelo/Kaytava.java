/* Teemu Soini
 * a660929
 * Olio-ohjelmointi
 * Harjoitusty�
 */
package sokkelo;
import apulaiset.*;
import fi.uta.csjola.oope.lista.*;

public class Kaytava extends Sokkelo implements Paikallinen {

    // Omalista attribuutti kaytavalle.
    OmaLista lista = new OmaLista();

    // Rakentajat.
    public Kaytava(int rivi, int sarake) {
        super(rivi, sarake);
    }

    public Kaytava() {
        rivi = 0;
        sarake = 0;
    }

    // Metodi, jossa tulostetaan käytävä olio stringinä + listan paikassa olevat oliot.
    public String toString() {
        String sisalto  = super.toString();
        if (!lista.onkoTyhja()) {
            sisalto = sisalto + System.lineSeparator() + lista;
        }
        return sisalto;
    }

    // Apumetodi lisäämään Olioita käytävän listaan.
    public void lisaaOlio(Object Olio) {
        lista.lisaaLoppuun(Olio);
    }

    // Apumetodi poistamaan vanhasta käytävästä haluttu Olio.
    public void poista(Object Olio) {
        lista.poista(Olio);
    }

    // Apumetodi tarkastamaan kaytavanlistan esineita varten.
    public boolean tarkastaE() {
        boolean onkoEsine = false;
        for (int i = 0; i < lista.koko(); i++) {
            if (lista.alkio(i) instanceof Esine) {
                return true;
            } else {
                onkoEsine = false;
            }

        }
        return onkoEsine;
    }

    // Apumetodi tarkastamaan kaytavanlistan robotteja  varten.
    public boolean tarkastaR() {
        boolean onkoRobotti = false;
        for (int i = 0; i < lista.koko(); i++) {
            if (lista.alkio(i) instanceof Robotti) {
                onkoRobotti = true;
            } else {
                onkoRobotti = false;
            }
        }
        return onkoRobotti;
    }

    // Apumetodi, joka palauttaa käytävän listassa olevan esineen viitteen.
    public Object esineHaku() {
        for (int i = 0; i < lista.koko(); i++) {
            if (lista.alkio(i) instanceof Esine) {
                Object esine = lista.alkio(i);
                return esine;
            }
        }
        return null;
    }

    // Apumetodi, joka palauttaa käytävän listassa olevan robotin viitteen.
    public Object robottiHaku() {
        for (int i = 0; i < lista.koko(); i++) {
            if (lista.alkio(i) instanceof Robotti) {
                Object robotti = lista.alkio(i);
                return robotti;
            }
        }
        return null;
    }

    // Apumetodi tarkastamaan kaytavanlistan robotteja  varten.
    public boolean tarkastaM() {
        boolean onkoMonkija = false;
        for (int i = 0; i < lista.koko(); i++) {
            if (lista.alkio(i) instanceof Monkija) {
                onkoMonkija = true;
            }
        }
        return onkoMonkija;
    }

    @Override
    public char getSymboli() {
        for (int i = 0; i < lista.koko(); i++) {
            if (lista.alkio(i) instanceof Monkija) {
                symboli = 'M';
                return symboli;
            } 
            else if (lista.alkio(i) instanceof Robotti) {
                symboli = 'R';
                return symboli;
            } 
            
        }
        for (int i = 0; i< lista.koko(); i++) {
            if (lista.alkio(i) instanceof Esine) {
                symboli = 'E';
                return symboli;
            }
        }
        symboli = ' ';
        return symboli;
    }

    // Tarkistetaan voidaanko paikalle liikkua tai asettaa sisältöä.
    public boolean sallittu() {
        return true;
    }
}