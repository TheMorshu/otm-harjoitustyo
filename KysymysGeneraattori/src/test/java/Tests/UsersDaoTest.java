package Tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.themorshu.dao.Database;
import java.sql.PreparedStatement;
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
    
    public UsersDaoTest() {
    }
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        database = new Database("jdbc:sqlite:test.db");
        PreparedStatement statement;
        try {
            statement = database.getConnection().prepareStatement("DROP TABLE Henkilo;");
            int changes = statement.executeUpdate();
            statement.close();
            database.closeConnection();
            statement = database.getConnection().prepareStatement("CREATE TABLE Henkilo (\n" +
            "    syntymavuosi integer,\n" +
            "    nimi varchar(200)\n" +
            ")");
            changes = statement.executeUpdate();
            statement.close();
            database.closeConnection();
        } catch (SQLException ex) {
        statement = database.getConnection().prepareStatement("CREATE TABLE Henkilo (\n" +
        "    syntymavuosi integer,\n" +
        "    nimi varchar(200)\n" +
        ")");
        int changes = statement.executeUpdate();
        statement.close();
        database.closeConnection();
        }
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
