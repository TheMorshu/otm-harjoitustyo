/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logiikka;

import java.util.Random;

/**
 *
 * @author ilmar
 */
public class mathGen implements Gen {
    
    String vastaus;
    String kyssari;
    Random random;

    public mathGen(Random random) {
        this.random = random;
    }

    @Override
    public String question() {
        return nollaKohta(); //yksi monista, jatkossa randomilla
    }
    
    public String nollaKohta() {
        int nolla1 = this.random.nextInt(21)-10; //luku väliltä -10 - 10
        int nolla2 = this.random.nextInt(21)-10;
        int xAmount = -nolla2-nolla1;
        int x2Amount = 1;
        int vakio = nolla1*nolla2;
        if (nolla1 <= nolla2) {
            this.vastaus = nolla1+" ja "+nolla2;
        } else {
            this.vastaus = nolla2+" ja "+nolla1;
        }
        return "Laske nollakohdat: "+"X^2+"+xAmount+"X+"+vakio+". Ilmoita vastaus muodossa: "+"n ja n, siten, että pienempi luku aluksi";
    }
    

    @Override
    public String answer() {
        return this.vastaus;
    }
    

    
}
