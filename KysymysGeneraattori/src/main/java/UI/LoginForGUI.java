/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Dao.Database;
import Dao.UsersDao;
import Main.KysymysGeneraattoriGUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import static javafx.application.Application.launch;

/**
 *
 * @author ilmar
 */
public class LoginForGUI {
    
    Database database;
    Scanner lukija;
    UsersDao usersDao;
    
    

    public LoginForGUI(Database users) {
        this.database = users;
        this.lukija = new Scanner(System.in);
        this.usersDao = new UsersDao(this.database);
    }

    
    public void tulostaUsers() throws SQLException {
        this.usersDao.printUsers();
    }
    
    public List getUserNames() throws SQLException {
        return this.usersDao.findAll();
    }
    
    
    public void clearDatabase() throws SQLException { //valmis
        this.usersDao.clearDatabase();
    }
    

    
}