/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.themorshu.logic;

import java.util.Random;

/**
 *
 * @author ilmar
 */
public class ChemGen implements Gen {
    
    String vastaus;
    String kyssari;
    Random random;

    public ChemGen(Random random) {
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
