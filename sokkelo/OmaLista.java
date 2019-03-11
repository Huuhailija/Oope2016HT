// Otetaan käyttöön lista-pakkauksen luokat.
package sokkelo;
import fi.uta.csjola.oope.lista.*;

/* Käytetään Teemu Soinin harjoitustyössä.
 *  a660929
 *  Muutettu sopivammaksi omiin tarpeisiin.
 */

/**
 * Viikkoharjoitus 7, tehtävät 2 ja 3.
 * <p>
 * Yhteen suuntaan linkitetystä Oope-listasta peritty luokka.
 * <p>
 * Olio-ohjelmoinnin perusteet, kevät 2016.
 * <p>
 *
 * @author Jorma Laurikkala (jorma.laurikkala@uta.fi),
 *         Informaatiotieteiden yksikkö, Tampereen yliopisto.
 */

public class OmaLista extends LinkitettyLista {

    /**
     * Vaihtaa listan pään ja hännän tietoalkiot keskenään. Operaatio voidaan
     * toteuttaa usealla eri tavalla. Tehokkainta olisi vaihtaa suoraan
     * paa- ja hanta-viitteisiin liittyvien solmuolioiden tietoalkio-oliot
     * keskenään apuviitteen avulla.
     *
     * @return true, jos alkiot voitiin vaihtaa ja false, jos lista on tyhjä.
     */
    public boolean vaihdaEkaJaVika() {
        // Null-arvoinen viite tai lista on tyhjä.
        if (koko == 0)
            return false;

            // Tehdään vaihto, jos alkioita on vähintään kaksi.
        else if (koko > 1) {
            // Poistetaan listan pää ja asetetaan viite pään alkioon,
            // jotta tieto ei katoa.
            Object paanAlkio = poistaAlusta();

            // Poistetaan listan häntä ja asetetaan viite hännän alkioon,
            // jotta tieto ei katoa.
            Object hannanAlkio = poistaLopusta();

            // Lisätään vanhan hännän alkio listan alkuun uudeksi pääksi.
            lisaaAlkuun(hannanAlkio);

            // Lisätään vanhan pään alkio listan loppuun uudeksi hännäksi.
            lisaaLoppuun(paanAlkio);
        }

        // Viestitään onnistumisesta myös, kun listalla on vain yksi alkio,
        // jolloin listalle ei tehdä mitään.
        return true;
    }

    /**
     * Luo taulukon ja asettaa sen viitteet listan tietoalkioihin siten,
     * että i. alkio viittaa i. tietoalkioon. Operaatio on hyvin tehoton,
     * koska alkio-operaatio käy listaa läpi aina lista alusta alkaen.
     * Tehokkaampaa olisi käydä lista läpi yhteen kertaa paa-viitteestä
     * alkaen ja seuraava-viitteitä seuraten, kunnes ollaan hännässä.
     *
     * @return null, jos lista on tyhjä. Paluuarvo on viite viitteet
     * sisältävään taulukkoon, jos listalla on alkioita.
     */
    public Object[] taulukkoon() {
        // Listalla yksi tai useampi tietoalkio.
        if (koko > 0) {
            // Luodaan Object-tyyppisten viitteiden taulukko,
            // jossa on yhtä monta alkiota kuin listalla on tietoalkioita.
            Object[] viitteet = new Object[koko];

            // Käydään lista läpi alusta loppuun silmukan avulla. Joka kierroksella
            // saadaan käyttöön laskurin ja alkio-operaation avulla solmun
            // tietoalkion viite, jonka avulla vastaavassa paikassa olevasta
            // taulukon alkiosta voidaan asettaa viite tietoalkioon.
            for (int i = 0; i < koko; i++)
                // Liitetään nykyisestä taulukon alkiosta viite nykyiseen listan
                // tietoalkioon.
                viitteet[i] = alkio(i);

            // Palautetaan viite listan alkioihin liittyvät viitteet sisältävään
            // taulukkoon.
            return viitteet;
        }
        // Lista on tyhjä.
        else
            return null;
    }

   /*
    * Object-luokan metodin korvaus.
    *
    */

    /**
     * Poistaa annettuun viitteeseen liittyvän alkion listalta.
     *
     * @param alkio viite poistettavaan tietoalkioon. Paluuarvo on null,
     *              jos parametri on null-arvoinen tai poistettavaa alkiota ei löytynyt.
     */
    public Object poista(Object alkio) {
        // Apuviite, joka alustetaan aluksi virhekoodilla.
        Object poistettava = null;

        // Kääntyy todeksi, jos löydetään poistettava alkio.
        boolean loydetty = false;

        // Käydään listaa läpi alusta loppuun niin pitkään kuin alkioita on
        // saatavilla tai poistettavaa ei ole löydetty.
        int i = 0;
        while (i < koko && !loydetty) {
            // Löydettiin tietoalkio, johon liittyy parametri ja listan solmu.
            if (alkio == alkio(i)) {
                // Asetetaan poistettavaan alkioon apuviite, jotta alkiota ei hukata.
                poistettava = poista(i);

                // Löydettiin mitä haettiin.
                loydetty = true;
            }

            // Siirrytään seuraavaan paikkaan.
            else
                i++;
        }

        // Palalutetaan viite mahdollisesti poistettuun alkioon.
        return poistettava;
    }

    /**
     * Poistaa annetun nimisen luokan tietoalkiot listalta ja palauttaa
     * viitteet niihin listalla.
     *
     * @param luokanNimi listalta poistettavien alkioiden luokan nimi.
     * @return lista, jossa viitteet poistettuihin alkoihin. Lista on tyhjä,
     * jos listalla ei ollut luokan olioita.
     */
    public OmaLista poista(String luokanNimi) {
        // Tehdään palautettava lista.
        OmaLista poistetut = new OmaLista();

        // Yritetään poistaa, jos alkioita on vähintään yksi.
        if (koko > 0) {
            // Silmukoidaan lista läpi alusta loppuun.
            int i = 0;
            while (i < koko) {
                // Selvitetään metaolion avulla nykyisen tietoalkion luokan nimi.
                String alkionLuokanNimi = alkio(i).getClass().getSimpleName();

                // Poistetaan alkio, jos luokan nimi on parametrina annettu
                // ja lisätään viite tuloslistan loppuun.
                if (alkionLuokanNimi.equals(luokanNimi))
                    poistetut.lisaaLoppuun(poista(i));
                    // Kasvatetaan laskuria vain, kun ei poisteta, jotta alkioita
                    // ei jäisi väliin.
                else
                    i++;
            }
        }

        // Palautetaan viite tuloslistaan.
        return poistetut;
    }

   /*
    * Object-luokan metodin korvaus.
    *
    */

    public String toString() {
        // Listan alkioittainen merkkijonoesitys tänne.
        String alkiot = "";

        // Tarkistetaan, että listalla on alkioita.
        if (!onkoTyhja()) {
            // Aloitetaan parametrina saadun listan päästä.
            Solmu paikassa = paa();

            // Edetään solmu kerrallaan, kunnes löydetään alkio tai lista loppuu.
            while (paikassa != null) {
                // Liitetään apuviite paikassa-viitteeseen liittyvän solmun alkioon.
                Object paikanAlkio = paikassa.alkio();

                // Siirrytään seuraavaan solmuun. Seuraava-aksessori palauttaa
                // viitteen paikassa-viitteeseen liittyvää solmua _seuraavaan_
                // solmuun. Sijoituksen jälkeen paikassa-viite liittyy tähän solmuun.
                paikassa = paikassa.seuraava();

                // Lisätään alkio ja erotin merkkijonoon.
                alkiot += paikanAlkio;
                if (paikassa != null)
                    alkiot += System.lineSeparator();
            }

        }

        // Palautetaan oma lista merkkijonona.
        return alkiot;
    }

    public void lisaa(Object alkio) {
        boolean apu = true;

        for (int i = 0; i < koko(); i++) {
            Object nykyinen = alkio(i);
            Comparable vertailtava = (Comparable) nykyinen;

            if (vertailtava.compareTo(alkio) > 0) {
                lisaa(i, alkio);
                i = koko;
                apu = false;
            }
        }
        if (apu) {
            lisaaLoppuun(alkio);
        }
    }
}