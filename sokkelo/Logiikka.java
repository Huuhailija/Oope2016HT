/* Teemu Soini
 * a660929
 * Olio-ohjelmointi
 * Harjoitustyö
 */
package sokkelo;
import apulaiset.Suunnallinen;
import apulaiset.*;
import java.io.*;


// Operaatiot, jotka vastaavat ohjelman toimintoja.
public class Logiikka {


    private int siemenluku;
    private int rivlkm;
    private int sarlkm;
    private int esineLkm = 0;
    private Sokkelo[][] kentta;
    private Monkija monkija = new Monkija();
    private Kaytava kaytava = new Kaytava();

    // Omalista lista roboteille, joita käytetään liikuttamiseen.
    OmaLista listarobotti = new OmaLista();

    // Oletusrakentaja käynnistyksen yhteydessä.
    public Logiikka() {
        ladataan();

    }


    // Metodi, joka lukee sokkelo.txt tiedoston ja lataa kentän valmiiksi.
    public void ladataan() {
        
        listarobotti = new OmaLista();
        final String TNIMI = "sokkelo.txt";
        int x;
        int y;
        int voima;
        char suunta;
        boolean jatketaan = true;


        try {
            FileInputStream syotevirta = new FileInputStream(TNIMI);
            InputStreamReader lukija = new InputStreamReader(syotevirta);
            BufferedReader puskuroitulukija = new BufferedReader(lukija);


            while (puskuroitulukija.ready()) {
                String rivi = puskuroitulukija.readLine();
                String[] osat = rivi.split("[|]");
                for (int i = 0; i < osat.length; i++) {
                    osat[i] = osat[i].trim();
                }
                if (jatketaan) {
                    siemenluku = Integer.parseInt(osat[0]);
                    rivlkm = Integer.parseInt(osat[1]);
                    sarlkm = Integer.parseInt(osat[2]);
                    jatketaan = false;
                    kentta = new Sokkelo[rivlkm][sarlkm];
                    Automaatti.alusta(siemenluku);
                }
                if (osat[0].equals("Seina")) {
                    x = Integer.parseInt(osat[1]);
                    y = Integer.parseInt(osat[2]);
                    kentta[x][y] = new Seina(x, y);

                }
                if (osat[0].equals("Kaytava")) {
                    x = Integer.parseInt(osat[1]);
                    y = Integer.parseInt(osat[2]);
                    Kaytava uusiKaytava = new Kaytava(x, y);
                    kentta[x][y] = uusiKaytava;
                    kaytava = uusiKaytava;
                }
                if (osat[0].equals("Monkija")) {
                    x = Integer.parseInt(osat[1]);
                    y = Integer.parseInt(osat[2]);
                    voima = Integer.parseInt(osat[3]);
                    suunta = osat[4].charAt(0);
                    Monkija seikkailija = new Monkija(x, y, voima, suunta);
                    kaytava.lisaaOlio(seikkailija);
                    monkija = seikkailija;
                }
                if (osat[0].equals("Robotti")) {
                    x = Integer.parseInt(osat[1]);
                    y = Integer.parseInt(osat[2]);
                    voima = Integer.parseInt(osat[3]);
                    suunta = osat[4].charAt(0);
                    Robotti robotti1 = new Robotti(x, y, voima, suunta);
                    kaytava.lisaaOlio(robotti1);
                    listarobotti.lisaaLoppuun(robotti1);
                }
                if (osat[0].equals("Esine")) {
                    x = Integer.parseInt(osat[1]);
                    y = Integer.parseInt(osat[2]);
                    voima = Integer.parseInt(osat[3]);
                    Esine esine = new Esine(x, y, voima);
                    kaytava.lisaaOlio(esine);
                    esineLkm++;
                }
            }

            puskuroitulukija.close();
            // Tarkastetaan, onko esineitä suoraan seikkailijan syntymä kohdan alla.
            int rivi = monkija.rivi();
            int sarake = monkija.sarake();
            Kaytava paikka = (Kaytava)kentta[rivi][sarake];
            if (paikka.tarkastaE()) {
                do {
                    Object esine = paikka.esineHaku();
                    monkija.lisaa(esine);
                    paikka.poista(esine);
                    esineLkm--;
                    if (esineLkm == 0) {
                        System.out.println("Ohjelma lopetettu");
                        System.exit(0);
                    }
                }
                while (paikka.tarkastaE());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Tiedosto hukassa.");
        }
        catch (IOException e) {
            System.out.println("Lukuvirhe");
        }
        catch (NumberFormatException e) {
            System.out.println("Vituiks män");
        }
    }

    // Näyttää seikkailijan tiedot + esinevaraston.
    public void inventoidaan() {
        System.out.println(monkija);
        Object[] apulista = monkija.esineVarasto.taulukkoon();
        if (monkija.esineVarasto != null) {
            for (int i = 0; i < monkija.esineVarasto.koko(); i++) {
                System.out.println(apulista[i]);
            }
        }
    }

    // Piirtää kartan seikkailijalle.
    public void kartoitetaan() {
        for (int r = 0; r < kentta.length; r++) {
            for (int s = 0; s < kentta[0].length; s++) {
                System.out.print(kentta[r][s].getSymboli());
            }
            System.out.println();
        }
    }

    // Metodi, jolla katsotaan seikkailijan ympäristöä.
    public void katso(char suunta) {

        int rivi = monkija.rivi();
        int sarake = monkija.sarake();

        if (suunta == Suunnallinen.ETELA) {
            rivi++;
        }
        if (suunta == Suunnallinen.ITA) {
            sarake++;
        }
        if (suunta == Suunnallinen.LANSI) {
            sarake--;
        }
        if (suunta == Suunnallinen.POHJOINEN) {
            rivi--;
        }
        System.out.println(kentta[rivi][sarake]);
    }

    // Metodi, jolla liikutetaan seikkailijaa.
    public boolean liiku(char suunta) {
        int rivi = monkija.rivi();
        int sarake = monkija.sarake();
        boolean jatketaan = true;

        
        if (suunta == Suunnallinen.ETELA) {
            rivi++;
            monkija.suunta('e');
        }
        if (suunta == Suunnallinen.ITA) {
            sarake++;
            monkija.suunta('i');
        }
        if (suunta == Suunnallinen.LANSI) {
            sarake--;
            monkija.suunta('l');
        }
        if (suunta == Suunnallinen.POHJOINEN) {
            rivi--;
            monkija.suunta('p');
        }
        if (kentta[rivi][sarake] instanceof Seina) {
            System.out.println("Kops!");
        }
        else if (kentta[rivi][sarake] instanceof Kaytava) {
            Kaytava vanhaKaytava = (Kaytava) kentta[monkija.rivi()][monkija.sarake()];
            Kaytava uusiKaytava = (Kaytava) kentta[rivi][sarake];
            Object esine;
            if (uusiKaytava.tarkastaE()) {
                do {
                    esine = uusiKaytava.esineHaku();
                    monkija.lisaa(esine);
                    uusiKaytava.poista(esine);
                    esineLkm--;
                    if (esineLkm == 0) {
                        vanhaKaytava.poista(monkija);
                        uusiKaytava.lisaaOlio(monkija);
                        return false;
                    }
                }
                while (uusiKaytava.tarkastaE());
            }
            if (uusiKaytava.tarkastaR() || vanhaKaytava.tarkastaR()) {
                boolean tulos = taistelu(uusiKaytava);
                if (!tulos) {
                    uusiKaytava.poista(monkija);
                    vanhaKaytava.poista(monkija);
                    return false;
                }
            }
            vanhaKaytava.poista(monkija);
            uusiKaytava.lisaaOlio(monkija);
            monkija.rivi(rivi);
            monkija.sarake(sarake);
        }
        monkija.paivita(rivi,sarake);
        if (jatketaan && listarobotti != null) {
            jatketaan = liikutaRobotti();
            if (!jatketaan) {
                return false;
            }
        }
        kartoitetaan();
        return jatketaan;
    }

    // Metodi, jossa seikkailija haluaa odottaa ja vain robotit liikkuvat.
    public boolean odotetaan() {
        boolean havittiinko = liikutaRobotti();
        kartoitetaan();
        return havittiinko;
    }

    // Operaatio muuntamaan esineitä voimaksi.
    public boolean muunnetaan(int parametri) {
        int koko = monkija.esineVarasto.koko();
        if (parametri <= koko) {
            for (int i = 0; i < parametri; i++) {
                Esine esine = (Esine)monkija.esineVarasto.alkio(0);
                int voima = esine.voima();
                monkija.voima = voima + monkija.voima();
                monkija.esineVarasto.poista(esine);
            }
            return true;
        }
        else {
            return false;
        }
    }

    // Metodi, jossa kamppaillaan robottia vastaan.
    public boolean taistelu(Kaytava uusiKaytava) {
        Object robotti = uusiKaytava.robottiHaku();
        int tulos = monkija.compareTo((Oliot) robotti);
        if (tulos == 1 || tulos == 0) {
            System.out.println("Voitto!");
            int voima = monkija.voima() - ((Oliot) robotti).voima();
            monkija.voima(voima);
            uusiKaytava.poista(robotti);
            listarobotti.poista(robotti);
        }
        else if (tulos == -1) {
            System.out.println("Tappio!");
            return false;
        }
        return true;
    }

    // Metodi, joka liikuttaa robotteja.
    public boolean liikutaRobotti() {
        boolean tulos = true;
        Kaytava kaytava = new Kaytava();

        // Poistetaan vanhat robotit paikoistaan.
        for (int r = 0; r < kentta.length; r++) {
            for (int s = 0; s < kentta[0].length; s++) {
                if (kentta[r][s] instanceof Kaytava) {
                    Kaytava vanhaKaytava = (Kaytava) kentta[r][s];
                    for (int i = 0; i < vanhaKaytava.lista.koko(); i++) {
                        if (vanhaKaytava.lista.alkio(i) instanceof Robotti) {
                            Robotti vanhaRobotti = (Robotti) vanhaKaytava.lista.alkio(i);
                            vanhaKaytava.lista.poista(vanhaRobotti);
                            i--;
                        }
                    }
                }
            }
        }
        Automaatti.paivitaPaikat(listarobotti,kentta);

        // Lisätään robottit uuteen paikaan robotti listasta.
        for(int i = 0; i < listarobotti.koko(); i++) {
            Robotti uusiRobotti = (Robotti) listarobotti.alkio(i);
            int Rrivi = uusiRobotti.rivi();
            int Rsarake = uusiRobotti.sarake();
            Kaytava kaytavaR = (Kaytava) kentta[Rrivi][Rsarake];
            kaytavaR.lisaaOlio(uusiRobotti); 
            kaytava = kaytavaR;
        }
        if (kaytava.tarkastaM() == true) {
            tulos = taistelu(kaytava);
        }
        return tulos;
    }

    // Tallentaa pelin sen hetkisen tilanteen sokkelo.txt tiedostoon.
    public void tallenna() {
        try {
            PrintWriter tiedosto = new PrintWriter("sokkelo.txt");
            String apu = "";
            String apu1 = "";
            String apu2 = "";
            Object[] apulista = monkija.esineVarasto.taulukkoon();

            if (siemenluku < 10) {
                apu = "   ";
            }
            if (siemenluku > 9 && siemenluku < 100) {
                apu = "  ";
            }
            if (siemenluku < 0) {
                apu = "  ";
            }
            if (siemenluku < -9 && siemenluku > -100) {
                apu = " ";
            }
            if (rivlkm < 10) {
                apu1 = "   ";
            }
            if (rivlkm > 9 && sarlkm < 100 ) {
                apu1 = "  ";
            }
            if (sarlkm <10) {
                apu2 = "   ";
            }
            if (sarlkm > 9 && sarlkm < 100 ) {
                apu2 = "  ";
            }

            tiedosto.println(siemenluku + apu + "|" + rivlkm + apu1 + "|" + sarlkm + apu2 + "|");
            boolean onkoMonkija;
            for (int r = 0; r < kentta.length; r++) {
                for (int s = 0; s < kentta[0].length; s++) {
                    tiedosto.println(kentta[r][s]);
                    if (kentta[r][s] instanceof Kaytava) {
                        Kaytava apuKaytava = (Kaytava)kentta[r][s];
                        onkoMonkija = apuKaytava.tarkastaM();
                        if (onkoMonkija) {
                            for (int i = 0; i< monkija.esineVarasto.koko();i++) {
                                tiedosto.println(apulista[i]);
                            }
                        }
                    }
                    onkoMonkija = false;
                }
            }
            tiedosto.close();

        } catch (FileNotFoundException e) {
            System.out.println("Tiedosto hukassa");
        }
    }

    // Lopettaa pelin pelauttamalla false ja tulostaa lopetus tekstin.
    public boolean lopetetaan() {
        return false;
    }

}