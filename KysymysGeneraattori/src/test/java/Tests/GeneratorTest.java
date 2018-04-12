package Tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.themorshu.dao.Database;
import fi.themorshu.dao.UsersDao;
import fi.themorshu.logic.Generator;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class GeneratorTest {
    
    Generator gen;
    UsersDao userDao;
    Database database;
    
    public GeneratorTest() {
    }
    
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        prepareDatabase();
        this.userDao = new UsersDao(this.database);
        this.gen = new Generator("testi", this.userDao);
    }

    public void prepareDatabase() throws SQLException, ClassNotFoundException {
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
    }
    
    
    @Test
    public void vastausOnNullAlussa() throws SQLException {
        assertEquals(this.gen.getAnswer(), null);
    }
    
    
    @Test
    public void setModeJaGetModeToimii() {
        String mode = "testi";
        gen.setMode(mode);
        assertEquals(mode, gen.getMode());
    }
    
    @Test
    public void setUserNameToimii() {
        gen.setUserName("uusiNimi");
        assertEquals("uusiNimi", gen.getUserName());
    }
    
    
    @Test
    public void sendAnswerToimiiOikeallaVastauksellaMatikka() throws SQLException {
        gen.setMode("maths");
        String kysymys = gen.getQuestion();
        String vastaus = gen.getAnswer();
        assertEquals(true, gen.sendAnswer(vastaus));
    }
    
    
    @After
    public void tearDown() throws SQLException {
        database.closeConnection();
    }
    
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
