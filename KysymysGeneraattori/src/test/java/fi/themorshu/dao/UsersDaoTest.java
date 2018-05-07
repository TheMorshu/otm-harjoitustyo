package fi.themorshu.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.themorshu.dao.Database;
import fi.themorshu.dao.UsersDao;
import fi.themorshu.dao.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ilmar
 */
public class UsersDaoTest {
    
    Database database;
    UsersDao dao;
    
    public UsersDaoTest() {
    }
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        database = new Database("jdbc:sqlite:test.db");
        PreparedStatement statement;
        try {
            statement = database.getConnection().prepareStatement("DROP TABLE Users;");
            int changes = statement.executeUpdate();
            statement.close();
            database.closeConnection();
            statement = database.getConnection().prepareStatement("CREATE TABLE users (\n" +
                    "id integer PRIMARY KEY,\n" +
                    "name varchar(200),\n" +
                    "password varchar (200),\n" +
                    "questions integer,\n" +
                    "right integer\n" +
                    ");");
            changes = statement.executeUpdate();
            statement.close();
            database.closeConnection();
            statement = database.getConnection().prepareStatement("INSERT INTO Users (name, password, questions, right) VALUES ('test', 'password', 100, 50);");
            changes = statement.executeUpdate();
            statement.close();
            database.closeConnection();
        } catch (SQLException ex) {
        statement = database.getConnection().prepareStatement("CREATE TABLE users (\n" +
                    "id integer PRIMARY KEY,\n" +
                    "name varchar(200),\n" +
                    "password varchar (200),\n" +
                    "questions integer,\n" +
                    "right integer\n" +
                    ");");
        int changes = statement.executeUpdate();
        statement.close();
        database.closeConnection();
        }
        this.dao = new UsersDao(database);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void saveToimiiLuokassa() throws SQLException {
        User user = new User("testi2", "password", 0, 0);
        User returningUser = this.dao.save(user);
        database.closeConnection();
        assertEquals(user, returningUser);
    }
    
    @Test
    public void saveEiTeeMitaanJosNimiOlemassa() throws SQLException {
        User user = new User("test", "password", 0, 0);
        User returningUser = this.dao.save(user);
        database.closeConnection();
        assertEquals(returningUser, null);
    }
    
    @Test
    public void saveEiTeeMitaanJosTyhjatTiedot() throws SQLException {
        User user = new User("", "", 0, 0);
        User returningUser = this.dao.save(user);
        database.closeConnection();
        assertEquals(returningUser, null);
    }
    
    
    @Test
    public void saveFeedbackPalauttaaOikeanKommentinKunUusiKayttaja() throws SQLException {
        User user = new User("nakkiAri", "salasana", 0, 0);
        this.dao.save(user);
        database.closeConnection();
        assertEquals("Käyttäjä lisätty! Voit nyt kirjautua sisään tiedoilla.", dao.getSaveFeedBack());
    }
    
    @Test
    public void salasananVaihtoToimii() throws SQLException {
        User user = new User("testUser", "passOriginal", 0, 0);
        this.dao.save(user);
        database.closeConnection();
        this.dao.changePassword("testUser", "passNew");
        assertEquals(this.dao.findOne("testUser").getPassword(),"passNew");
    }
    
    @Test
    public void resetScoreNollaaKysymykset() throws SQLException {
        User user = new User("testUser", "pass", 10, 10);
        this.dao.save(user);
        database.closeConnection();
        this.dao.resetScore("testUser");
        assertEquals(this.dao.findOne("testUser").getQuestions(), 0);
    }
    
    @Test
    public void resetScoreNollaaPisteet() throws SQLException {
        User user = new User("testUser", "pass", 10, 10);
        this.dao.save(user);
        database.closeConnection();
        this.dao.resetScore("testUser");
        assertEquals(this.dao.findOne("testUser").getRight(), 0);
    }
    
    @Test
    public void updateMetodiKykeneeMuuttamaanKaikkiaTietoja() throws SQLException {
        User user = new User("testUser", "pass", 10, 10);
        this.dao.save(user);
        database.closeConnection();
        User newUser = new User("testUser123", "pass123", 100, 100);
        this.dao.update(newUser, "testUser");
        assertEquals(this.dao.findOne("testUser123"), newUser);
    }
    
    @Test
    public void updateMetodiPalauttaaNullJosUusiNimiJoOlemassa() throws SQLException {
        User user = new User("testUser", "pass", 10, 10);
        this.dao.save(user);
        database.closeConnection();
        User newUser = new User("testUser123", "pass123", 100, 100);
        this.dao.save(newUser);
        database.closeConnection();
        this.dao.update(newUser, "testUser");
        assertEquals(null, this.dao.update(newUser, "testUser"));
    }
    
    @Test
    public void updateMetodiPalauttaaNullJosMuokattavaaOliotaEiOlemassa() throws SQLException {
        User newUser = new User("testUser123", "pass123", 100, 100);
        assertEquals(null, this.dao.update(newUser, "testUser"));
    }
    
    
    @Test
    public void loytaaDatabasestaHalutunHenkilon() throws SQLException {
        assertEquals(true, this.dao.checkContainsName("test"));
    }
    
    @Test
    public void deletePoistaaUserinDatabasesta() throws SQLException {
        User user = new User("poistettava", "password", 0, 0);
        User returningUser = this.dao.save(user);
        this.dao.delete("poistettava");
        database.closeConnection();
        assertEquals(false, this.dao.checkContainsName("poistettava"));
    }
    
    @Test
    public void findOnePalauttaaVastaavanOlion() throws SQLException { //KESKEN 
        User foundOne = this.dao.findOne("test");
        database.closeConnection();
        if (foundOne.getUsername().equals("test") && foundOne.getPassword().equals("password") && foundOne.getQuestions() == 100 && foundOne.getRight() == 50) {
            assertEquals(true, true);
        } else {
            assertEquals(true, false);
        }
    }
    
    @Test
    public void findOnePalauttaaNullJosEiOlemassa() throws SQLException { //KESKEN 
        assertEquals(null, this.dao.findOne("doesntExist"));
    }
    
    @Test
    public void verifyLoginPalauttaaNullVaarillaTiedoilla() throws SQLException { //KESKEN 
        assertEquals(null, this.dao.verifyLogin("eiOle", "eiOle"));
    }
    
    @Test
    public void verifyLoginPalauttaaNullVaarallaSalasanalla() throws SQLException { //KESKEN 
        assertEquals(null, this.dao.verifyLogin("test", "wrongPass"));
    }
    
    @Test
    public void verifyLoginPalauttaaOikeanOlionOikeillaTiedoilla() throws SQLException { //KESKEN 
        assertEquals(new User("test", "password", 100, 50), this.dao.verifyLogin("test", "password"));
    }
    
    @Test
    public void saveEiKelpuutaTyhjiatietoja() throws SQLException { //KESKEN 
        assertEquals(null, this.dao.save(new User("","",0,0)));
    }
    
    @Test
    public void saveEiKelpuutaTyhjiatietojaJaPalauttaaOikeanFeedbackin() throws SQLException { //KESKEN 
        this.dao.save(new User("","",0,0));
        assertEquals("Kirjoita kunnolliset tiedot!", this.dao.getSaveFeedBack());
    }
    
    @Test
    public void clearDatabaseTyhjentaaDatabasen() throws SQLException { //KESKEN 
        this.dao.clearDatabase();
        assertEquals(this.dao.findAll().isEmpty(), true);
    }
    
    @Test
    public void findAllPalauttaaAlustuksessaLisatynOlion() throws SQLException { //KESKEN 
        ArrayList list = (ArrayList) this.dao.findAll();
        for (int i = 0; i<list.size(); i++) {
            if (i >= 1) {
                assertEquals(true, false);
            }
        }
        assertEquals(this.dao.findAll().get(0).equals(new User("test", "password", 100, 50)), true);
    }
    
    @Test
    public void findAllPalauttaaUseammanOlion() throws SQLException { //KESKEN 
        this.dao.save(new User("asd", "asd", 0, 0));
        this.dao.save(new User("asda", "asd", 0, 0));
        this.dao.save(new User("asdaa", "asd", 0, 0));
        ArrayList list = (ArrayList) this.dao.findAll();
        assertEquals(this.dao.findAll().size() == 4, true);
    }
    
    
    @Test
    public void printAllUsersScoresToimiiYhdellaKayttajalla() throws SQLException { //KESKEN 
        ArrayList list = (ArrayList) this.dao.findAll();
        String string = "#1: " + list.get(0).toString() + "\n";
        assertEquals(string, this.dao.printAllUsersScores());
    }
    
    @Test
    public void printAllUsersScoresToimiiUseammallaKayttajalla() throws SQLException { //KESKEN 
        this.dao.save(new User("asd", "asd", 10, 3));
        this.dao.save(new User("asda", "asd", 10, 4));
        this.dao.save(new User("asdaa", "asd", 10, 5));
        ArrayList list = (ArrayList) this.dao.findAll();
        Collections.sort(list);
        String string = "";
        for (int i = 0; i < list.size(); i++) {
            User user = (User) list.get(i);
            string += "#" + (i + 1) + ": " + user.toString() + "\n";
        }
        assertEquals(string, this.dao.printAllUsersScores());
    }
    
    @Test
    public void printusersToimiiYhdellaKayttajalla() throws SQLException { //KESKEN 
        assertEquals("test" + "\n", this.dao.printUsers());
    }
    
    @Test
    public void printusersToimiUseallaKayttajalla() throws SQLException { //KESKEN 
        this.dao.save(new User("asd", "asd", 10, 3));
        this.dao.save(new User("asda", "asd", 10, 4));
        this.dao.save(new User("asdaa", "asd", 10, 5));
        assertEquals("test" + "\n" + "asd" + "\n" + "asda" + "\n" + "asdaa" + "\n", this.dao.printUsers());
    }
    
    
}
