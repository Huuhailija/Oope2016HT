/* Teemu Soini
 * a660929
 * Olio-ohjelmointi
 * Harjoitustyö
 */
package sokkelo;
import apulaiset.*;

public class Kayttoliittyma {

    // Final komennot.
    private final String LATAA = "lataa";
    private final String INVENTOI = "inventoi";
    private final String KARTTA = "kartta";

    private final String KATSO_E = "katso e";
    private final String KATSO_L = "katso l";
    private final String KATSO_P = "katso p";
    private final String KATSO_I = "katso i";

    private final String LIIKU_E = "liiku e";
    private final String LIIKU_L = "liiku l";
    private final String LIIKU_P = "liiku p";
    private final String LIIKU_I = "liiku i";

    private final String ODOTA = "odota";
    private final String TALLENNA = "tallenna";
    private final String LOPETA = "lopeta";

    private final String ERROR = "Virhe!";

    String komento2 = "";

    // Silmukka metodi, jossa ohjelma pyörii.
    public void silmukka() {
        boolean jatketaan = true;

        // Alustetaan kentän lataus kutsumalla logiikka rakentajaa.
        Logiikka logiikka = new Logiikka();

        while (jatketaan) {
            System.out.println("Kirjoita komento:");
            String komento = In.readString();

            // Metodi lataaman kartta.
            if (komento.equals(LATAA)) {
                logiikka.ladataan();
            }
            // Metodi tulostamaan inventaarion.
            else if (komento.equals(INVENTOI)) {
                logiikka.inventoidaan();
            }
            // Metodi piirtämään seikkailijalle kartan.
            else if (komento.equals(KARTTA)) {
                logiikka.kartoitetaan();
            }
            // Katso seikkailijasta etelään. Palauttaa oliot katsotussa paikassa.
            else if (komento.equals(KATSO_E)) {
                logiikka.katso('e');
            }
            // Katso seikkailijasta länteen. Palauttaa oliot katsotussa paikassa.
            else if (komento.equals(KATSO_L)) {
                logiikka.katso('l');
            }
            // Katso seikkailijasta pohjoiseen. Palauttaa oliot katsotussa paikassa.
            else if (komento.equals(KATSO_P)) {
                logiikka.katso('p');
            }
            // Katso seikkailijasta itään. Palauttaa oliot katsotussa paikassa.
            else if (komento.equals(KATSO_I)) {
                logiikka.katso('i');
            }
            // Liikkuu itään. Kerää käytäväpaikassa olevat esineet ja taistelee robotteja vastaan.
            else if (komento.equals(LIIKU_I)) {
                jatketaan = logiikka.liiku('i');
            }
            // Liikkuu länteen. Kerää käytäväpaikassa olevat esineet ja taistelee robotteja vastaan.
            else if (komento.equals(LIIKU_L)) {
                jatketaan = logiikka.liiku('l');
            }
            // Liikkuu pohjoiseen. Kerää käytäväpaikassa olevat esineet ja taistelee robotteja vastaan.
            else if (komento.equals(LIIKU_P)) {
                jatketaan = logiikka.liiku('p');
            }
            // Liikkuu etelään. Kerää käytäväpaikassa olevat esineet ja taistelee robotteja vastaan.
            else if (komento.equals(LIIKU_E)) {
                jatketaan = logiikka.liiku('e');
            }
            // Seikkailija odottaa yhden vuoron.
            else if (komento.equals(ODOTA)) {
                jatketaan = logiikka.odotetaan();
                if (!jatketaan) {
                    logiikka.lopetetaan();
                }
            }
            // Tallentaa pelin.
            else if (komento.equals(TALLENNA)) {
                logiikka.tallenna();
            }
            // Lopettaa pelin.
            else if (komento.equals(LOPETA)) {
                jatketaan = false;
                logiikka.lopetetaan();
            }
            // Kokeillaan muunna komentoa. Napataan virheet ja muunnetaan luvusta 1-9 määrä mahdollisia esineitä.
            else {
                try {
                    boolean onkoLuku = false;
                    int parametri = 0;
                    for (int i = 0; i < komento.length(); i++){
                        char kirjain = komento.charAt(i);
                        if (kirjain == ' ') {
                            i = i + 1;
                            char kirjain2 = komento.charAt(i);
                            onkoLuku = Character.isDigit(kirjain2);
                            i = komento.length();
                            parametri = Character.getNumericValue(kirjain2);
                        }
                        else {
                            komento2 = komento2 + kirjain;
                        }
                    }
                    if (komento2.equals("muunna") && onkoLuku && parametri > 0) {
                        boolean onnistu = logiikka.muunnetaan(parametri);
                        if (!onnistu) {
                            System.out.println(ERROR);
                        }
                    }
                    else {
                        System.out.println(ERROR);
                    }
                    komento2 = "";
                }
                catch (StringIndexOutOfBoundsException s) {
                    System.out.println(ERROR);
                }
            }
        }
        logiikka.kartoitetaan();
        System.out.println("Ohjelma lopetettu.");
    }
}