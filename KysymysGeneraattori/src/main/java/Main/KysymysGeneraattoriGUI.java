/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Dao.Database;
import Dao.UsersDao;
import Logiikka.User;
import UI.Login;
import UI.LoginForGUI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class KysymysGeneraattoriGUI extends Application{

    
    @Override
    public void start(Stage ikkuna) throws ClassNotFoundException, SQLException {
        Database users = new Database("jdbc:sqlite:users.db");
        //LoginForGUI kirjautuminen = new LoginForGUI(users);
        UsersDao usersDao = new UsersDao(users);
        
        
        //Teksielementit ja kentät
        Label nameText = new Label("Nimi: ");
        TextField nameInput = new TextField();
        Label passText = new Label("Salasana: ");
        TextField passInput = new TextField();
        
        //Napit
        Button newUser = new Button("Uusi käyttäjä");
        Button existingUser = new Button("Vanha käyttäjä");
        Button hiScores = new Button("Tuloslista");
        Button quit = new Button("Lopeta");
        Button backToLogin = new Button("Kirjautumiseen");
        Button clearDatabase = new Button("Clear database");
        
        //Asettelut
        GridPane loginGUI = new GridPane();
        GridPane hiScoreGUI = new GridPane();
        GridPane adminGUI = new GridPane();
        
        //Asettelujen määrittely
        loginGUI.add(nameText, 0, 0);
        loginGUI.add(nameInput, 1, 0);
        loginGUI.add(passText, 0, 1);
        loginGUI.add(passInput, 1, 1);
        loginGUI.add(newUser, 0, 2);
        loginGUI.add(existingUser, 1, 2);
        loginGUI.add(hiScores, 0, 3);
        loginGUI.add(quit, 1, 3);
        
        hiScoreGUI.add(backToLogin, 0, 0);
        
        adminGUI.add(clearDatabase, 0, 0);
        
        //Asettelun hienosäätö
        loginGUI.setHgap(10);
        loginGUI.setVgap(10);
        loginGUI.setPadding(new Insets(10, 10, 10, 10));
        hiScoreGUI.setHgap(10);
        hiScoreGUI.setVgap(10);
        hiScoreGUI.setPadding(new Insets(10, 10, 10, 10));
        adminGUI.setHgap(10);
        adminGUI.setVgap(10);
        adminGUI.setPadding(new Insets(10, 10, 10, 10));
        
        //hiscoresLoad
        ArrayList lista = (ArrayList) usersDao.findAll();
        for (int i=0; i<lista.size(); i++) {
            Label name = new Label((String) lista.get(i));
            hiScoreGUI.add(name, 0, i+1);
        }
        
        //Näkymät
        Scene hiScoreNakyma = new Scene(hiScoreGUI, 640, 640);
        Scene loginNakyma = new Scene(loginGUI, 640, 640);
        Scene adminNakyma = new Scene(adminGUI, 640, 640);
        
        
        quit.setOnAction((event) -> {
            ikkuna.close();
        });
        hiScores.setOnAction((event) -> {
            ikkuna.setTitle("HiScores");
            ikkuna.setScene(hiScoreNakyma);
        });
        newUser.setOnAction((event) -> {
            User user = new User(nameInput.getText(), passInput.getText(), 0, 0);
            try {
                usersDao.saveOrUpdate(user);
            } catch (SQLException ex) {
                Logger.getLogger(KysymysGeneraattoriGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        existingUser.setOnAction((event) -> {
            if (nameInput.getText().equals("admin") && passInput.getText().equals("admin")) {
                ikkuna.setScene(adminNakyma);
            }
        });
        backToLogin.setOnAction((event) -> {
            ikkuna.setScene(loginNakyma);
        });
        clearDatabase.setOnAction((event) -> {
            try {
                usersDao.clearDatabase();
                ikkuna.setScene(loginNakyma);
            } catch (SQLException ex) {
                System.out.println("DATABASE ERROR!");
            }
        });

        
        ikkuna.setTitle("Login");
        ikkuna.setScene(loginNakyma);
        ikkuna.show();
        
    }
    

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        launch(KysymysGeneraattoriGUI.class);
    }
    
}