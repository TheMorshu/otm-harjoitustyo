/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.themorshu.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ilmar
 */
public class MathGen implements Gen {
    
    String answer;
    String question;
    Random random;
    int root1;
    int root2;
    int multipier;
    BasicValues values;

    public MathGen(Random random) {
        this.random = random;
        this.values = new BasicValues();
    }

    @Override
    public String question() {
        int choice = this.random.nextInt(2);
        if (choice == 0) {
            return rootsOfFunction();
        } else if (choice == 1) {
            return interestOfLoan();
        } else {
            return "ERROR!";
        }
    }

    
    public String rootsOfFunction() {
        root1 = this.random.nextInt(21) - 10; //luku väliltä -10 - 10
        root2 = this.random.nextInt(21) - 10;
        multipier = this.random.nextInt(5) - 2;
        while (multipier == 0) {
            multipier = this.random.nextInt(5) - 2;
        }
        int xAmount = multipier * (-root2 - root1);
        int x2Amount = multipier;
        int cTerm = multipier * root1 * root2;
        if (root1 <= root2) {
            this.answer = root1 + " ja " + root2;
        } else {
            this.answer = root2 + " ja " + root1;
        }
        return trimRootsQuestion(x2Amount, xAmount, cTerm);
    }
    public String trimRootsQuestion(int x2Amount, int xAmount, int cTerm) {
        String string = "Laske nollakohdat: ";
        string += x2Amount + "X^2";
        if (xAmount > 0) {
            string += "+" + xAmount + "X";
        } 
        if (xAmount < 0) {
            string += xAmount + "X";
        } 
        if (cTerm > 0) {
            string += "+" + cTerm;
        } 
        if (cTerm < 0) {
            string += cTerm;
        }
        string += ". Ilmoita vastaus muodossa: " + "n ja n, siten, että pienempi luku aluksi";
        return string;
    }
    public int getRoot1() {
        return root1;
    }
    public int getRoot2() {
        return root2;
    }
    
    public String interestOfLoan() {
        double originalLoan = 1.0*(this.random.nextInt(901) + 100); //100-1000e laina
        double interestPercent = 1.0*(this.random.nextInt(10) + 1); //1-10% korko
        double amountOfWeeks = 1.0*(this.random.nextInt(8) + 3); //3-10 viikkoa
        double interestMultipier = 1.0 + interestPercent/100;
        double finalLoan = 1.0 * originalLoan * Math.pow(1.0*interestMultipier, 1.0*amountOfWeeks);
        this.answer = "" + String.format("%.2f", (this.values.round(finalLoan, 2))) + "e";
        return "Kalle lainaa penalta " + originalLoan + " euroa ja he sopivat viikkokoroksi " + interestPercent + " %. Kuinka paljon Kalle on Penalle velkaa " + amountOfWeeks + " viikon päästä? Anna vastaus sentin tarkkuudella esim 265,44e";
    }
    
    

    @Override
    public String answer() {
        return this.answer;
    }
    

    
}
