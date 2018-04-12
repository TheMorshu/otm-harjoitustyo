//OK!

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.themorshu.dao;

import fi.themorshu.logic.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UsersDao implements Dao<User> {
    
    Database database;

    public UsersDao(Database database) {
        this.database = database;
    }
    
    @Override
    public User findOne(String username) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name, password, questions, right FROM Users WHERE name = ?");
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            int questions = resultSet.getInt("questions");
            int right = resultSet.getInt("right");
            statement.close();
            this.database.closeConnection();
            return new User(name, password, questions, right);
        } else {
            statement.close();
            this.database.closeConnection();
            return null;
        }
    }
    
    //extra
    public Boolean checkContainsName(String name) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name FROM Users WHERE name = ?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            statement.close();
            this.database.closeConnection();
            return true;
        }
        statement.close();
        this.database.closeConnection();
        return false;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name, password, questions, right FROM Users;");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            int questions = resultSet.getInt("questions");
            int right = resultSet.getInt("right");
            userList.add(new User(name, password, questions, right));
        }
        statement.close();
        this.database.closeConnection();
        return userList;
    }
    
    
    @Override
    public void setUpTableOnDatabase() {
        PreparedStatement statement;
        try {
            statement = database.getConnection().prepareStatement("CREATE TABLE users (\n" +
                    "id integer PRIMARY KEY,\n" +
                    "name varchar(200),\n" +
                    "password varchar (200),\n" +
                    "questions integer,\n" +
                    "right integer\n" +
                    ");");
            int changes = statement.executeUpdate();
            statement.close();
            this.database.closeConnection();
        } catch (SQLException ex) {
        }
    }
    
    
    @Override
    public void clearDatabase() throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("DROP TABLE Users;");
        int changes = statement.executeUpdate();
        statement = database.getConnection().prepareStatement("CREATE TABLE users (\n" +
        "id integer PRIMARY KEY,\n" +
        "name varchar(200),\n" +
        "password varchar (200),\n" +
        "questions integer,\n" +
        "right integer\n" +
        ");");
        changes = statement.executeUpdate();
        statement.close();
        this.database.closeConnection();
    }
    
    public void printUsers() throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name FROM Users");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            System.out.println(name);
        }
        statement.close();
        this.database.closeConnection();
    }
    
    public User verifyLogin(String username, String password) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name, password, questions, right FROM Users WHERE name = ? AND password = ?;");
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int questions = resultSet.getInt("questions");
            int right = resultSet.getInt("right");
            statement.close();
            this.database.closeConnection();
            return new User(username, password, questions, right);
        }
        statement.close();
        this.database.closeConnection();
        return null;
    }
    
    public void addQuestionsForUser(String name) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("UPDATE Users SET questions = questions + 1 WHERE name = ?;");
        statement.setString(1, name);
        int changes = statement.executeUpdate();
        statement.close();
        this.database.closeConnection();
    }
    
    public void addRightForUser(String name) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("UPDATE Users SET right = right + 1 WHERE name = ?;");
        statement.setString(1, name);
        int changes = statement.executeUpdate();
        statement.close();
        this.database.closeConnection();
    }

    @Override
    public User save(User user) throws SQLException {
        System.out.println("1");
        if (!checkContainsName(user.getUsername())) { //Jos ei sisällä haluttua nimeä, luo tilin
            directInsertToDatabase(user);
            return user;
        } else { //jos nimi on jo varattu ei tee mitään ja palauuttaa null
            return null;
        }
    }

    @Override
    public User update(User user, String usernameOfPreviousVersion) throws SQLException {
        if (checkContainsName(usernameOfPreviousVersion) && !checkContainsName(user.getUsername())) {             
            delete(usernameOfPreviousVersion);
            directInsertToDatabase(user);
            return user;
        } else { //jos ei löydy usernameOfPreviousVersion nimistä käyttäjää databasesta palauttaa null
            return null;
        }
    }

    @Override
    public void delete(String username) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("DELETE FROM Users WHERE name = ?;");
        statement.setString(1, username);
        int changes = statement.executeUpdate();
        statement.close();
        this.database.closeConnection();
    }

    
    @Override
    public void directInsertToDatabase(User user) throws SQLException {
        PreparedStatement statement;
        int changes;
        statement = database.getConnection().prepareStatement("INSERT INTO Users (name, password, questions, right) VALUES (?, ?, ?, ?);");
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setInt(3, user.getQuestions());
        statement.setInt(4, user.getRight());
        changes = statement.executeUpdate();
        statement.close();
        this.database.closeConnection();
    }
    
    
    
}
