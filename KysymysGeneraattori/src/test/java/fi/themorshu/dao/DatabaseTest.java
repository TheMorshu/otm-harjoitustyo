package fi.themorshu.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.themorshu.dao.Database;
import java.sql.Connection;
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
public class DatabaseTest {
    
    Database database;
    
    public DatabaseTest() {
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
    
    @After
    public void tearDown() throws SQLException {
        database.closeConnection();
    }
    
    @Test
    public void databaseEiSisallaMitaan() throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT * FROM Users;");
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            statement.close();
            database.closeConnection();
            assertEquals(true, false);
        }
        statement.close();
        database.closeConnection();
        assertEquals(true, true);
    }
    
    @Test
    public void dataBaseenVoiVuorovaikuttaa() throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("INSERT INTO Users (name, password, questions, right) VALUES ('test', 'password', 0, 0);");
        int changes = statement.executeUpdate();
        statement.close();
        database.closeConnection();
        statement = database.getConnection().prepareStatement("SELECT * FROM Users;");
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) {
            statement.close();
            database.closeConnection();
            assertEquals(true, false);
        }
        statement.close();
        database.closeConnection();
        assertEquals(true, true);
    }
    
    
}
