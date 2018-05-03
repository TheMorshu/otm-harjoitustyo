/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.themorshu.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Tämä luokka generoi automaattisesti kemia aiheisia kysymyksiä ja generoi myös niihin oikeat vastaukset.
 * Tätä luokkaa hyödynnetään GeneratorCore generaattorinhallitna luokassa.
 */
public class ChemGen implements Gen {
    
    private String answer;
    private String question;
    private Random random;
    private BasicValues values;
    private double molarAmountOfGas;
    private double molarity;
    private double liters;
    private double molarMass;

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
        int choice = this.random.nextInt(2);
        if (choice == 0) {
            return molarAmountOfIdealGas();
        } else if (choice == 1) {
            return liquidPreparation();
        } else {
            return "ERROR!";
        }
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
        this.molarAmountOfGas = (1.0 * (pressurePascal) * volumeInCubicMeters) / (temperatureKelvin * this.values.molarGasConstantJMolKelvin());
        this.answer = "" + String.format("%.2f", this.values.round(molarAmountOfGas, 2)) + " mol";
        return "Laske ideaalikaasun ainemäärä kahden desimaalin tarkkuudella. Vastaus muotoa 10,22 mol. "
                + "\nKaasun lämpötila: " + temperatureKelvin + " K, paine: " + pressurePascal + " Pa, tilavuus: " + volumeInLitres + " litraa.";
    }
    
    /**
     * Tätä metodia käytetään testejä varten
     * @return palauttaa lyhentämättömän versio molarAmountOfIdealGas() metodin oikeasta vastauksesta
     */
    public double getMolarAmountOfGas() {
        return this.molarAmountOfGas;
    }
    
    
    /**
     * Tämä metodi palauttaa perus liuoskemiaan liittyvän laskutehtävän String oliona, ja generoi samalla automaattisesti oikean mallivastauksen ja tallentaa sen olion sisäiseksi muuttujaksi this.answer
     * @return Kysymys String oliona
     */
    public String liquidPreparation() {
        int molarMassTimesOneHundred = this.random.nextInt(10100) + 2000; //arpoo moolimasan väliltä 20-120 g/mol
        this.molarMass = (1.0 * molarMassTimesOneHundred) / 100;
        int deciLiters = this.random.nextInt(41) + 10; //arpoo tilavuuden desilitroina 10-50 (eli 1.0-5.0 Litraa)
        this.liters = 1.0 * deciLiters / 10;
        this.molarity = 1.0 * (this.random.nextInt(20) + 1) / 10; //arpoo molaarisuuden väliltä 0.1-2M
        String question = "Sinun tulee valmistaa " + liters + " litraa " + molarity + " M liuosta vesiliukoisesta yhdisteestä, jonka moolimassa on " + molarMass + " g/mol. Kuinka monta \n"
                + "grammaa sinun tulee punnita kyseistä yhdistettä, jotta liuoksesta tulee oikeav vahvuista? Vastaa desimaalin tarkkuudella (esim. 103.1 g)";
        double answerInDouble = this.molarity * this.liters * this.molarMass; //=grammoina
        this.answer = String.format("%.1f", this.values.round(answerInDouble, 1)) + " g";
        return question;
    }
    
    /**
     * Tämä metodi on olemassa vain liquidPreparation() metodin testausta varten
     * @return liquidPreparationissa arvotut arvot listana, joiden pohjalta oikea vastaus voidaan laskea
     */
    public ArrayList<Double> getLiquidParameters() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(this.molarity);
        list.add(this.liters);
        list.add(this.molarMass);
        return list;
    }
    
}
