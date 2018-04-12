/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.themorshu.logic;

/**
 *
 * @author ilmar
 */
public class User {
    
    private String username;
    private String password;
    private int questions;
    private int right;

    public User(String name, String password, int questions, int right) {
        this.username = name;
        this.password = password;
        this.questions = questions;
        this.right = right;
    }

    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return this.username + ", " + this.questions + " tehtävää tehty joista " + this.right + " on vastattu oikein.";
    }

    
    
    
    
    
}
