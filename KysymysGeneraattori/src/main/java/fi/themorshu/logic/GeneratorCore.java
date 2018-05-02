/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.themorshu.logic;

import fi.themorshu.dao.UsersDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ilmar
 */
public class GeneratorCore {
    
    String userName;
    UsersDao userDao;
    String mode;
    String answer;
    String question;
    Random random;
    MathGen math;
    PhysGen phys;
    ChemGen chem;
    ArrayList<Gen> genLista;

    public GeneratorCore(String userName, UsersDao userDao) {
        this.userName = userName;
        this.userDao = userDao;
        this.mode = "";
        this.random = new Random();
        this.math = new MathGen(this.random);
        this.phys = new PhysGen(this.random);
        this.chem = new ChemGen(this.random);
        this.genLista = new ArrayList<>();
        this.genLista.add(this.math);
        this.genLista.add(this.phys);
        this.genLista.add(this.chem);
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
    
    
    
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserName() {
        return this.userName;
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
        int n = random.nextInt(3);
        this.question = this.genLista.get(n).question();
        this.answer = this.genLista.get(n).answer();
        return true;
    }
    

    public String getAnswer() {
        return this.answer;
    }

    public boolean sendAnswer(String vastaus) throws SQLException {
        if (vastaus.equals(this.answer)) {
            this.userDao.addRightForUser(this.userName);
            this.userDao.addQuestionsForUser(this.userName);
            return true;
        }
        this.userDao.addQuestionsForUser(this.userName);
        return false;
    }
    
    
    
}
