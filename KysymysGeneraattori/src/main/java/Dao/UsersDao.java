/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsersDao implements Dao {
    
    Database database;

    public UsersDao(Database database) {
        this.database = database;
    }
    
    @Override
    public Object findOne(Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findAll() throws SQLException { //palauta Usereina!
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name FROM Users");
        ResultSet resultSet = statement.executeQuery();
        ArrayList lista = new ArrayList<>();
        while (resultSet.next()){
            String nimi = resultSet.getString("name");
            lista.add(nimi);
        }
        return lista;
    }

    @Override
    public Object saveOrUpdate(Object object) throws SQLException { //kesken
        PreparedStatement statement = database.getConnection().prepareStatement("INSERT INTO Users (name, passowrd, questions, right) VALUES ('?', '?', 0, 0);");
        statement.setString(1, "asd");
        statement.setString(2, "asd");
        int changes = statement.executeUpdate();
        return null;
    }

    @Override
    public void delete(Object key) throws SQLException { //kesken
        PreparedStatement statement = database.getConnection().prepareStatement("INSERT INTO users....;");
        int changes = statement.executeUpdate();
    }
    
    public void clearDatabase() throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("DROP TABLE users;");
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
    }
    
    public void printUsers() throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name FROM Users");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            String nimi = resultSet.getString("name");
            System.out.println(nimi);
        }
    }
    
    
    
}
