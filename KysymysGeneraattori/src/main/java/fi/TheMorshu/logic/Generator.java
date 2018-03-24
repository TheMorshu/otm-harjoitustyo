/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.TheMorshu.logic;

import fi.TheMorshu.dao.UsersDao;
import java.util.Random;

/**
 *
 * @author ilmar
 */
public class Generator {
    
    String userName;
    UsersDao userDao;
    String mode;
    String answer;
    String question;
    Random random;
    MathGen math;
    PhysGen phys;
    ChemGen chem;

    public Generator(String userName, UsersDao userDao) {
        this.userName = userName;
        this.userDao = userDao;
        this.mode = "";
        this.random = new Random();
        this.math = new MathGen(this.random);
        this.phys = new PhysGen(this.random);
        this.chem = new ChemGen(this.random);
    }

    public void setMode(String mode) {
        this.mode = mode;
    }


    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getQuestion() {
        if (mode.equals("maths")) {
            this.question = math.question();
            this.answer = math.answer();
            return this.question;
        }
        if (mode.equals("phys")) {
            this.question = phys.question();
            this.answer = phys.answer();
            return this.question;
        }
        if (mode.equals("chem")) {
            this.question = chem.question();
            this.answer = chem.answer();
            return this.question;
        }
        if (mode.equals("all")) {
            if (selectRandomQuestionType()) {
                return this.question;
            }         
        } 
        return null;
    }

    public boolean selectRandomQuestionType() {
        int n = random.nextInt(3)+1;
        if (n==1) {
            this.question = math.question();
            this.answer = math.answer();
            return true;
        }
        if (n==2) {
            this.question = phys.question();
            this.answer = phys.answer();
            return true;
        }
        if (n==3) {
            this.question = chem.question();
            this.answer = chem.answer();
            return true;
        }
        return false;
    }
    

    public String getAnswer() {
        return this.answer;
    }

    public boolean sendAnswer(String vastaus) {
        if (vastaus.equals(this.answer)) {
            return true;
        }
        return false;
    }
    
    
    
}
