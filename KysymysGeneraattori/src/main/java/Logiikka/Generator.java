/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logiikka;

import Dao.UsersDao;

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

    public Generator(String userName, UsersDao userDao) {
        this.userName = userName;
        this.userDao = userDao;
        this.mode = "";
    }

    public void setMode(String mode) {
        this.mode = mode;
    }


    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getQuestion() {
        //GENEROI KYSSÄRIN, SITTEN VASTAUKSEN
        this.kyssari += "kyssäri";
        this.vastaus = "10";
        return kyssari;
    }
    
    public String getAnswer() {
        return this.vastaus;
    }

    public boolean sendAnswer(String vastaus) {
        return true;
    }
    
    
    
}
