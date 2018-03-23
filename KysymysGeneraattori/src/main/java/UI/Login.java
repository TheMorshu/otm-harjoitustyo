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
import java.util.Scanner;
import static javafx.application.Application.launch;

/**
 *
 * @author ilmar
 */
public class Login {
    
//    Database database;
//    Scanner lukija;
//    UsersDao usersDao;
//    
//    
//
//    public Login(Database users) {
//        this.database = users;
//        this.lukija = new Scanner(System.in);
//        this.usersDao = new UsersDao(this.database);
//    }
//
//    
//    public void tulostaUsers() throws SQLException {
//        this.usersDao.printUsers();
//        this.aloita();
//    }
//    
//    public void aloita() throws SQLException {
//        System.out.println("Login: ");
//        System.out.println("1. Vanha käyttäjä");
//        System.out.println("2. Uusi käyttäjä");
//        System.out.println("3. Tulosta pisteet");
//        System.out.println("4. Lopeta");
//        System.out.println("5. GUI");
//        System.out.println("");
//        System.out.print("Valinta: ");
//        int valinta = Integer.parseInt(lukija.nextLine());
//        if (valinta == 1) {
//            login();
//        }
//        if (valinta == 3) {
//            tulostaUsers();
//        }
//        if (valinta == 5) {
//            launch(KysymysGeneraattoriGUI.class);
//        } else {
//            System.out.println("Kesken tai virhe!");
//        }
//    }
//    
//    public void login() throws SQLException { //logiikka sisäänkirjautumiseen, tarkistaa databasesta tiedot
//        System.out.print("Käyttäjänimi: ");
//        String username = lukija.nextLine();
//        System.out.print("Salasana: ");
//        String password = lukija.nextLine();
//        if (username.equals("admin") && password.equals("admin")) { //HUOM! Tämä if looppi vain kehitysvaiheessa! Poistetaan lopullsesta
//            System.out.println("ADMIN COMMANDS: ");
//            System.out.println("1. Clear database");
//            System.out.println("2. Back to login");
//            int valinta = Integer.parseInt(lukija.nextLine());
//            if (valinta == 2) {
//                aloita();
//            } if (valinta == 1) {
//                clearDatabase();
//            }
//        } else {
//            System.out.println("KESKEN"); //Tähän varsinainen sisäänkirjautuminen
//        }   
//    }
//    
//    
//    public void clearDatabase() throws SQLException {
//        this.usersDao.clearDatabase();
//        aloita();
//    }
//    

    
}