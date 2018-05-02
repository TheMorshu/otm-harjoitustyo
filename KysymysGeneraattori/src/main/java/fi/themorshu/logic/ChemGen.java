/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.themorshu.logic;

import java.util.Random;

/**
 * Tämä luokka generoi automaattisesti kemia aiheisia kysymyksiä ja generoi myös niihin oikeat vastaukset.
 * Tätä luokkaa hyödynnetään GeneratorCore generaattorinhallitna luokassa.
 */
public class ChemGen implements Gen {
    
    String answer;
    String question;
    Random random;
    BasicValues values;

    /**
     * Konstruktorissa otetaan vastaan Random olio, sekä luodaan käyttään BasicValues olio, josta voidaan
     * saada muunmuassa luonnonvakioita
     * @param random Javan Random annetaan parametrina konstruktorille, hyödynnetään generoinnissa
     */
    public ChemGen(Random random) {
        this.random = random;
        this.values = new BasicValues();
    }

    /**
     * "Arpoo" satunnaisen kysymystyypin generaattorin sisältä. Tässä tapauksessa kysymystyyppejä kuitenkin vaik yksi
     * ,joten ei arvo mitään, vaan ottaa suoraan sen.
     * @return Kysymys merkkijonona
     */
    @Override
    public String question() {
        return molarAmountOfIdealGas();
    }

    @Override
    public String answer() {
        return this.answer;
    }
    
    /**
     * Luo kemia-aiheisen kysymyksen (laske ideaalikaasun ainemäärä, kun tiedetään asiat X ja Y..) ja luo sille myös
     * oikean vastauksen. Oikea vastaus tallennetaan olion sisään. GeneratorCore:ssa käyttäjän antamaa vastausta verrataan tähän oikeaan vastaukseen.
     * @return Kysymys merkkijonona
     */
    public String molarAmountOfIdealGas() {
        double temperatureKelvin = 1.0 * (this.random.nextInt(10001) + 26000) / 100; // 260-360 Kelvin
        int pressurePascal = this.random.nextInt(1000001) + 10000; // 10000-1010000 Pascalia
        double volumeInCubicCentiMeters = 1.0 * (this.random.nextInt(100001) + 100); // 100-1000100 kuutiosenttiö = 0,1-100,1 litraa
        double volumeInCubicMeters = volumeInCubicCentiMeters / 1000000;
        double volumeInLitres = volumeInCubicCentiMeters / 1000;
        double molarAmountOfGas = (1.0 * (pressurePascal) * volumeInCubicMeters) / (temperatureKelvin * this.values.molarGasConstantJMolKelvin());
        this.answer = "" + String.format("%.2f", this.values.round(molarAmountOfGas, 2)) + " mol";
        return "Laske ideaalikaasun ainemäärä kahden desimaalin tarkkuudella. Vastaus muotoa 10,22 mol. "
                + "\nKaasun lämpötila: " + temperatureKelvin + " K, paine: " + pressurePascal + " Pa, tilavuus: " + volumeInLitres + " litraa.";
    }
    
}
