/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.themorshu.logic;

import static java.lang.Math.sqrt;
import java.util.Random;

/**
 * Tämä luokka generoi automaattisesti fysiikka aiheisia kysymyksiä ja generoi myös niihin oikeat vastaukset.
 * Tätä luokkaa hyödynnetään GeneratorCore generaattorinhallitna luokassa.
 */
public class PhysGen implements Gen {
    String answer;
    String question;
    Random random;
    BasicValues values;
    double originalSpeed;

    /**
     * Konstruktorissa otetaan vastaan Random olio, sekä luodaan käyttään BasicValues olio, josta voidaan
     * saada muunmuassa luonnonvakioita
     * @param random Javan Random annetaan parametrina konstruktorille, hyödynnetään generoinnissa
     */
    public PhysGen(Random random) {
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
        return this.speedOfVehicle();
    }

    @Override
    public String answer() {
        return this.answer;
    }
    
    /**
     * Luo fysiikka-aiheisen kysymyksen (laske auton alkunopeus, kun tiedossa asiat X ja Y..) ja luo sille myös
     * oikean vastauksen. Oikea vastaus tallennetaan olion sisään. GeneratorCore:ssa käyttäjän antamaa vastausta verrataan tähän oikeaan vastaukseen.
     * @return Kysymys merkkijonona
     */
    public String speedOfVehicle() {
        int kitka = this.random.nextInt(100) + 1;
        int jarrutus = this.random.nextInt(910) + 100;
        double kitkakerroin = 1.0 * kitka / 100;
        double jarrutusmatka = 1.0 * jarrutus / 10;
        originalSpeed = sqrt(2 * kitkakerroin * jarrutusmatka * this.values.droppingAccerlation());
        this.answer = "" + String.format("%.2f", (this.values.round(originalSpeed, 2)));
        return "Laske auton alkunopeus ennen jarrutusta, kun kitkakerroin on " + kitkakerroin + " ja auton jarrutusmatka " + jarrutusmatka + " m. "
                + "\nAnna vastaus m/s 2 desimaalin tarkkuudella (esim kirjoita 32.22)";
    }
    
    /**
     * Tämä metodi on vain testejä varten (speedOfvehicle testaus)
     * @return Palauttaa alkuperäisen nopeuden pyöristämättömänä
     */
    public double getOriginalSpeed() {
        return this.originalSpeed;
    }

    
}
