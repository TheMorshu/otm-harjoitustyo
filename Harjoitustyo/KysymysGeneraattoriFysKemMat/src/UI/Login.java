/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Dao.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author ilmar
 */
public class Login {
    
    Database database;
    Scanner lukija;
    
    

    public Login(Database users) {
        this.database = users;
        this.lukija = new Scanner(System.in);
    }

    
    public void tulostaUsers() throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name FROM Users");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            String nimi = resultSet.getString("name");
            System.out.println(nimi);
        }
    }
    
    public void aloita() throws SQLException {
        System.out.println("Login: ");
        System.out.println("1. Vanha käyttäjä");
        System.out.println("2. Uusi käyttäjä");
        System.out.println("3. Tulosta pisteet");
        System.out.println("4. Lopeta");
        int valinta = Integer.parseInt(lukija.nextLine());
        if (valinta == 3) {
            tulostaUsers();
        }
        
    }

    
}
