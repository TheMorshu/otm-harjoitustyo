/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Dao.Database;
import UI.Login;
import UI.LoginForGUI;
import java.sql.SQLException;
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

/**
 *
 * @author ilmar
 */
public class KysymysGeneraattoriGUI extends Application{

    
    @Override
    public void start(Stage ikkuna) throws ClassNotFoundException {
        Database users = new Database("jdbc:sqlite:users.db");
        LoginForGUI kirjautuminen = new LoginForGUI(users);
        
        Label nameText = new Label("Nimi: ");
        TextField nameInput = new TextField();
        Label passText = new Label("Salasana: ");
        TextField passInput = new TextField();
        
        Button newUser = new Button("Uusi käyttäjä");
        Button existingUser = new Button("Vanha käyttäjä");
        Button hiScores = new Button("Tuloslista");
        Button quit = new Button("Lopeta");
        Button backToLogin = new Button("Kirjautumiseen");
        
        GridPane loginGUI = new GridPane();
        GridPane hiScoreGUI = new GridPane();
        
        loginGUI.add(nameText, 0, 0);
        loginGUI.add(nameInput, 1, 0);
        loginGUI.add(passText, 0, 1);
        loginGUI.add(passInput, 1, 1);
        loginGUI.add(newUser, 0, 2);
        loginGUI.add(existingUser, 1, 2);
        loginGUI.add(hiScores, 0, 3);
        loginGUI.add(quit, 1, 3);
        hiScoreGUI.add(backToLogin, 0, 0);
        
        loginGUI.setHgap(10);
        loginGUI.setVgap(10);
        loginGUI.setPadding(new Insets(10, 10, 10, 10));
        
        
        Scene hiScoreNakyma = new Scene(hiScoreGUI);
        Scene loginNakyma = new Scene(loginGUI);
        
        
        
        quit.setOnAction((event) -> {
            ikkuna.close();
        });
        hiScores.setOnAction((event) -> {
            ikkuna.setTitle("HiScores");
            ikkuna.setScene(hiScoreNakyma);
        });
        newUser.setOnAction((event) -> {
            
        });
        existingUser.setOnAction((event) -> {
            
        });
        backToLogin.setOnAction((event) -> {
            ikkuna.setScene(loginNakyma);
        });
        

        
        ikkuna.setTitle("Login");
        ikkuna.setScene(loginNakyma);
        ikkuna.show();
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        launch(KysymysGeneraattoriGUI.class);
    }
    
}