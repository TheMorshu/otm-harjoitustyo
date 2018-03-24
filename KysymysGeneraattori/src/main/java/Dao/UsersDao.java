//OK!

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Logic.User;
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
            statement.close();
            this.database.closeConnection();
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            int questions = resultSet.getInt("questions");
            int right = resultSet.getInt("right");
            return new User(name, password, questions, right);
        } else {
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
        while (resultSet.next()){
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
    public User saveOrUpdate(User user, String oldUserName) throws SQLException { //HIOTTAVAAA!
        if (checkContainsName(oldUserName)) { //päivittää vanhan päälle
            System.out.println("1");
            String name = user.getUsername();
            String password = user.getPassword();
            int questions = user.getQuestions();
            int right = user.getRight();
            System.out.println(name+password+questions+right);
            PreparedStatement statement = database.getConnection().prepareStatement("DELETE FROM Users WHERE name = ?;");
            statement.setString(1, oldUserName);
            int changes = statement.executeUpdate();
            statement.close();
            this.database.closeConnection();
            return saveOrUpdate(user, "");
        } //jos ei ole olemassa suoritta vain tämän
        String name = user.getUsername();
        String password = user.getPassword();
        int questions = user.getQuestions();
        int right = user.getRight();
        PreparedStatement statement = database.getConnection().prepareStatement("INSERT INTO Users (name, password, questions, right) VALUES (?, ?, ?, ?);");
        statement.setString(1, name);
        statement.setString(2, password);
        statement.setInt(3, questions);
        statement.setInt(4, right);
        int changes = statement.executeUpdate();
        statement.close();
        this.database.closeConnection();
        return new User(name, password, 0, 0);
    }

    @Override
    public void delete(String username) throws SQLException { //kesken
        PreparedStatement statement = database.getConnection().prepareStatement("DELETE ......;");
        int changes = statement.executeUpdate();
        statement.close();
        this.database.closeConnection();
    }
    
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
        statement = database.getConnection().prepareStatement("INSERT INTO users (name, password, questions, right) VALUES ('admin', 'admin', 0, 0);");
        changes = statement.executeUpdate();
        statement.close();
        this.database.closeConnection();
    }
    
    public void printUsers() throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name FROM Users");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
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
        while (resultSet.next()){
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
    
    
    
}
