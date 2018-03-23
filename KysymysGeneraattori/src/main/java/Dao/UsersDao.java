/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Logiikka.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsersDao implements Dao<User, Integer> {
    
    Database database;

    public UsersDao(Database database) {
        this.database = database;
    }
    
    @Override
    public User findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //extra
    public Boolean checkContainsName(String nimi) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name FROM Users WHERE name = ?");
        statement.setString(1, nimi);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            statement.close();
            return true;
        }
        statement.close();
        return false;
    }

    @Override
    public List<User> findAll() throws SQLException { //palauta Usereina!
        List<User> userLista = new ArrayList<>();
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name, password, questions, right FROM Users;");
        ResultSet resultSet = statement.executeQuery();
        //ArrayList lista = new ArrayList<>();
        while (resultSet.next()){
            String nimi = resultSet.getString("name");
            String salasana = resultSet.getString("password");
            int kysymykset = resultSet.getInt("questions");
            int oikein = resultSet.getInt("right");
            userLista.add(new User(nimi, salasana, kysymykset, oikein));
        }
        statement.close();
        return userLista;
    }

    @Override
    public User saveOrUpdate(User user) throws SQLException { //kesken
        if (checkContainsName(user.getNimi())) {
            return null;
        }
        int id = 0;
        String nimi = user.getNimi();
        String salasana = user.getSalasana();
        PreparedStatement statement = database.getConnection().prepareStatement("INSERT INTO Users (name, password, questions, right) VALUES (?, ?, 0, 0);");
        statement.setString(1, nimi);
        statement.setString(2, salasana);
        int changes = statement.executeUpdate();
        statement.close();
        return new User(nimi, salasana, 0, 0);
    }

    @Override
    public void delete(Integer key) throws SQLException { //kesken
        PreparedStatement statement = database.getConnection().prepareStatement("INSERT INTO users....;");
        int changes = statement.executeUpdate();
        statement.close();
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
    }
    
    public void printUsers() throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name FROM Users");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            String nimi = resultSet.getString("name");
            System.out.println(nimi);
        }
        statement.close();
    }
    
    public User verifyLogin(String username, String password) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name, password, questions, right FROM Users WHERE name = ? AND password = ?;");
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            String nimi = resultSet.getString("name");
            String salasana = resultSet.getString("password");
            int kysymykset = resultSet.getInt("questions");
            int oikein = resultSet.getInt("right");
            statement.close();
            return new User(nimi, salasana, kysymykset, oikein);
        }
        statement.close();
        return null;
    }
    
    
    public void addQuestionsForUser(String nimi) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("UPDATE Users SET questions = questions + 1 WHERE name = ?;");
        statement.setString(1, nimi);
        int changes = statement.executeUpdate();
    }
    
    public void addRightForUser(String nimi) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("UPDATE Users SET right = right + 1 WHERE name = ?;");
        statement.setString(1, nimi);
        int changes = statement.executeUpdate();
    }
    
    
    
}
