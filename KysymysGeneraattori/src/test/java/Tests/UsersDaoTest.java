package Tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.themorshu.dao.Database;
import fi.themorshu.dao.UsersDao;
import fi.themorshu.logic.User;
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
        statement = database.getConnection().prepareStatement("CREATE TABLE Users (\n" +
        "    syntymavuosi integer,\n" +
        "    nimi varchar(200)\n" +
        ")");
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
        assertEquals(user, returningUser);
    }
    
    @Test
    public void asd() throws SQLException {
        User user = new User("testi2", "password", 0, 0);
        User returningUser = this.dao.save(user);
        assertEquals(user, returningUser);
    }
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
