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
public class physGen implements Gen {
    String vastaus;
    String kyssari;
    Random random;

    public physGen(Random random) {
        this.random = random;
    }

    @Override
    public String question() {
        return "kyssäri";
    }

    @Override
    public String answer() {
        return "vastaus";
    }
    
    
}
