/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logiikka;

import Dao.UsersDao;
import java.util.Random;

/**
 *
 * @author ilmar
 */
public class Generator {
    
    String userName;
    UsersDao userDao;
    String mode;
    String vastaus;
    String kyssari;
    Random random;
    mathGen math;
    physGen phys;
    chemGen chem;

    public Generator(String userName, UsersDao userDao) {
        this.userName = userName;
        this.userDao = userDao;
        this.mode = "";
        this.random = new Random();
        this.math = new mathGen(this.random);
        this.phys = new physGen(this.random);
        this.chem = new chemGen(this.random);
    }

    public void setMode(String mode) {
        this.mode = mode;
    }


    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getQuestion() {
        if (mode.equals("maths")) {
            this.kyssari = math.question();
            this.vastaus = math.answer();
            return this.kyssari;
        }
        if (mode.equals("phys")) {
            this.kyssari = phys.question();
            this.vastaus = phys.answer();
            return this.kyssari;
        }
        if (mode.equals("chem")) {
            this.kyssari = chem.question();
            this.vastaus = chem.answer();
            return this.kyssari;
        }
        if (mode.equals("all")) {
            int n = random.nextInt(3)+1;
            if (n==1) {
                this.kyssari = math.question();
                this.vastaus = math.answer();
                return this.kyssari;
            } if (n==2) {
                this.kyssari = phys.question();
                this.vastaus = phys.answer();
                return this.kyssari;
            } if (n==3) {
                this.kyssari = chem.question();
                this.vastaus = chem.answer();
                return this.kyssari;
            }
        } else {
            System.out.println("else");
            return null;
        }
        return null;
    }
    

    public String getAnswer() {
        return this.vastaus;
    }

    public boolean sendAnswer(String vastaus) {
        if (vastaus.equals(this.vastaus)) {
            return true;
        }
        return false;
    }
    
    
    
}
