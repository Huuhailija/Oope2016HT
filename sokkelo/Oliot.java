/* Teemu Soini
 * a660929
 * Olio-ohjelmointi
 * Harjoitusty�
 */
package sokkelo;
public abstract class Oliot extends Sokkelo implements Comparable<Oliot> {

    // Attribuutti.
    int voima;

    // Aksessorit.
    public int voima() {
        return voima;
    }

    public void voima(int voima) {
        this.voima = voima;
    }

    // Paremetrillinen rakentaja.
    public Oliot(int rivi, int sarake, int voima) {
        super(rivi, sarake);
        this.voima = voima;
    }

    // Parametritön rakentaja.
    public Oliot() {
        super(0, 0);
        voima = 0;
    }

    // Comparable- rajapinta.
    public int compareTo(Oliot o) {
        if (o != null) {
            // Jos olion voima on pienempi kuin saatu parametri.
            if (voima < o.voima()) {
               return -1;
             }
              // Jos voimat ovat samat.
             else if (voima == o.voima()) {
                return 0;
            }
               }
             // Muuten olio on voimakkaampi.
             else {
               return 1;
        }
        return 1;
    }

    // Metodi tulostamaan olion merkkijono esitys.
    public String toString() {
        String apu = "";
        if (voima < 10) {
            apu = "   ";
        }
        if (voima < 100  && voima > 9) {
            apu = "  ";
        }
        if (voima < 1000 && voima > 99) {
            apu = " ";
        }
        if (voima > 999) {
            apu = "";
        }
        return super.toString() + voima()+ apu + EROTIN;
    }
}