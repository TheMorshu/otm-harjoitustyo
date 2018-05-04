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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tätä luokkaa käytetään kysymysgeneraattorin hallintaan. Generaattorin avulla valitaan sopivat kysymyskategoriat.
 * Ja noudetaan generoituja kysymyksiä vastauksineen nihiin sopivista generaattoreista. Generaattorin avulla myls varmistetaan käytäjien
 * antamien vastausten oikeellisuus ja annetaan tarvittaessa UsersDao:lle käsky asntaa pisteitä
 * 
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
    Boolean answered;

    /**
     * Konstruktorissa alustetaan generaattorinhallinta käyttävalmiuteen
     * @param userName Tämä parametri määrittelee käyttäjän nimen, kuka sillä hetkellä käyttää sovellusta. Voidaan vaihtaa myöhemmin
     * @param userDao Toisena parametrina annetaan UsersDao olio, jolla tarvittaessa tehdään muutoksia tietokantaan
     */
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

    public Boolean getAnswered() {
        return answered;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    /**
     * Tällä metodilla generoidaan automaattisesti sekä kysymys, että kysymykseen oikea vastaus ja molemmat taltioidaan.
     * Ennen generointia tarkastaa menetelmä "moden" eli tilan, minkä mukaan kysymyskategoria valitaan
     * Samalla kun GeneratorCore noutaa uuden kysymyksen, asettaa se kysymykseen liittyvät "answered" totuusarvon falseksi.
     * @return Palauttaa generoidun kysymyksen merkkijonona
     */
    public String getQuestion() {
        if (mode.equals("maths")) {
            this.question = math.question();
            this.answer = math.answer();
        }
        if (mode.equals("phys")) {
            this.question = phys.question();
            this.answer = phys.answer();
        }
        if (mode.equals("chem")) {
            this.question = chem.question();
            this.answer = chem.answer();
        }
        if (mode.equals("all")) {
            selectRandomQuestionType();
        }
        this.answered = false;
        return this.question;
    }

    /**
     * Tämä metodi suoritetaan, jos käyttäjä on valinnut kysymystyypiksi satunnaisen. Metodin sisällä hoidetaan kysymyksen ja vastauksen generointi ja taltiointi.
     */
    public void selectRandomQuestionType() {
        int n = random.nextInt(3);
        this.question = this.genLista.get(n).question();
        this.answer = this.genLista.get(n).answer();
    }
    

    public String getAnswer() {
        return this.answer;
    }

    /**
     * Metodin avulla tarkistetaan, onko käyttäjän esittämä vastaus oikea. Hyödynnetään käyttöliittymäkoodissa. Mikäli vastaus on oikea
     * lisätään kyseiselle käyttäjälle oikea vastaus tietokantaan. Riippumatta siitä, oliko vastaus oikea vai ei, lisätään tietokantaan myös tieto siitä, että yhteen kysymykseen on vastattu jotain (kysymysten kokonaismäärä)
     * @param vastaus Käyttäjän antama vastaus merkkijonona, jota verrataan oikeaan generoituun vastaukseen
     * @return totuusarvo siitä, vastattiinko kysymykseen oikein
     * @throws SQLException Heittää virheen, jos SWL yhteydessä on vikaa
     */
    public boolean checkAnswer(String vastaus) throws SQLException {
        if (vastaus.equals(this.answer)) {
            this.userDao.addRightForUser(this.userName);
            this.userDao.addQuestionsForUser(this.userName);
            return true;
        }
        this.userDao.addQuestionsForUser(this.userName);
        return false;
    }
    
    /**
     * Hyödynnetään käyttöliittymäkoodissa. Metodi ottaa vastaan vastauksen, tarkistaa sen oikeellosuuden checkAnswer() metodilla,
     * joka antaa mahdolliset pisteet käyttäjälle (vain jos ei ole vielä vastattu). Tämä metodi palauttaa käyttäjälle näytettävän palautteen Strigninä.
     * @param ans Käyttäjän antama vastaus
     * @return Käyttäjälle annettava palaute Stringinä
     */
    public String sendAnswer(String ans) {
        Boolean correctAnswer = false;
        try {
            if (!answered) {
                correctAnswer = checkAnswer(ans);
            }
        } catch (SQLException ex) { }
        if (correctAnswer) {
            setAnswered(true);
            return "Vastaus oikein! Sait pisteen!";
        }
        setAnswered(true);
        return "Väärin! Oikea vastaus oli: " + getAnswer();
    } 
}
