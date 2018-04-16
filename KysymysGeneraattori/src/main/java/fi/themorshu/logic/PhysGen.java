/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.themorshu.logic;

import static java.lang.Math.sqrt;
import java.util.Random;

/**
 *
 * @author ilmar
 */
public class PhysGen implements Gen {
    String answer;
    String question;
    Random random;
    BasicValues values;

    public PhysGen(Random random) {
        this.random = random;
        this.values = new BasicValues();
    }

    @Override
    public String question() {
        return this.massOfVehicle();
    }

    @Override
    public String answer() {
        return this.answer;
    }
    
    
    public String massOfVehicle() {
        int kitka = this.random.nextInt(100) + 1;
        int jarrutus = this.random.nextInt(910) + 100;
        double kitkakerroin = 1.0 * kitka / 100;
        double jarrutusmatka = 1.0 * jarrutus / 10;
        double nopeus = sqrt(2 * kitkakerroin * jarrutusmatka * this.values.droppingAccerlation());
        this.answer = "" + String.format("%.2f", (this.values.round(nopeus, 2)));
        System.out.println("Vastaus: " + this.answer);
        return "Laske auton alkunopeus ennen jarrutusta, kun kitkakerroin on " + kitkakerroin + " ja auton jarrutusmatka " + jarrutusmatka + " m. Anna vastaus m/s 2 desimaalin tarkkuudella (esim kirjoita 32.22)";
    }
    

    
}
