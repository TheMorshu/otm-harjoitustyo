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
public class MathGen implements Gen {
    
    String answer;
    String question;
    Random random;
    int root1;
    int root2;
    int multipier;

    public MathGen(Random random) {
        this.random = random;
    }

    @Override
    public String question() {
        return rootsOfFunction(); //yksi monista, jatkossa randomilla
    }

    
    public String rootsOfFunction() {
        root1 = this.random.nextInt(21) - 10; //luku väliltä -10 - 10
        root2 = this.random.nextInt(21) - 10;
        multipier = this.random.nextInt(5) - 2;
        while (multipier == 0) {
            multipier = this.random.nextInt(5) - 2;
        }
        System.out.println(multipier);
        int xAmount = multipier * (-root2 - root1);
        int x2Amount = multipier;
        int cTerm = multipier * root1 * root2;
        if (root1 <= root2) {
            this.answer = root1 + " ja " + root2;
        } else {
            this.answer = root2 + " ja " + root1;
        }
        return trimRootsQuestion(x2Amount, xAmount, cTerm);
//        return "Laske nollakohdat: " + x2Amount + "X^2+" + xAmount + "X+" + cTerm + ". Ilmoita vastaus muodossa: " + "n ja n, siten, että pienempi luku aluksi";
    }

    public String trimRootsQuestion(int x2Amount, int xAmount, int cTerm) {
        String string = "Laske nollakohdat: ";
        if (x2Amount != 1 && x2Amount != -1) {
            string += x2Amount + "X^2";
        } else {
            if (x2Amount == 1) {
                string += "X^2";
            } else {
                string += "-X^2";
            }
        }
        if (xAmount > 0) {
            string += "+" + xAmount + "X";
        } if (xAmount < 0) {
            string += xAmount + "X";
        } if (cTerm > 0) {
            string += "+" + cTerm;
        } if (cTerm < 0) {
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
    
    
    

    @Override
    public String answer() {
        return this.answer;
    }
    

    
}
