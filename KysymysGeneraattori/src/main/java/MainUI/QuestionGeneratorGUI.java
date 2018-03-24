/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainUI;

import Dao.Database;
import Dao.UsersDao;
import Logic.Generator;
import Logic.User;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class QuestionGeneratorGUI extends Application{

    String userNameLogged;
    
    @Override
    public void start(Stage ikkuna) throws ClassNotFoundException, SQLException {
        Database users = new Database("jdbc:sqlite:users.db");
        UsersDao usersDao = new UsersDao(users);
        userNameLogged = "";
        Generator gene = new Generator("", usersDao);
        
        //Teksielementit ja kentät
        Label nameText = new Label("Nimi: ");
        TextField nameInput = new TextField();
        Label passText = new Label("Salasana: ");
        TextField passInput = new TextField();
        Label message = new Label("");
        Label question = new Label("Kysymys: ");
        TextField answer = new TextField();
        
        //Napit
        Button newUser = new Button("Uusi käyttäjä");
        Button existingUser = new Button("Vanha käyttäjä");
        Button hiScores = new Button("Tuloslista");
        Button quit = new Button("Lopeta");
        Button backToLogin = new Button("Kirjautumiseen");
        Button backToLogin2 = new Button("Kirjautumiseen");
        Button tallennaJaLopeta = new Button("Tallenna ja lopeta");
        Button clearDatabase = new Button("Clear database");
        Button maths = new Button("Tee matikan tehtäviä!");
        Button phys = new Button("Tee fysiikan tehtäviä!");
        Button chem = new Button("Tee kemian tehtäviä!");
        Button all = new Button("Tee kaikkia tehtäviä sekaisin!");
        Button sendAnswer = new Button("Lähetä vastaus!");
        Button lisaaSUPER = new Button("LISÄÄ SUPER!");
        Button korvaaAsdwSUPER = new Button("Korvaa asd SUPER:illa!");
        
        //Asettelut
        GridPane loginGUI = new GridPane();
        GridPane hiScoreGUI = new GridPane();
        GridPane adminGUI = new GridPane();
        GridPane questionGUI = new GridPane();
        
        //Asettelujen määrittely
        loginGUI.add(nameText, 0, 0);
        loginGUI.add(nameInput, 1, 0);
        loginGUI.add(passText, 0, 1);
        loginGUI.add(passInput, 1, 1);
        loginGUI.add(newUser, 0, 2);
        loginGUI.add(existingUser, 1, 2);
        loginGUI.add(hiScores, 0, 3);
        loginGUI.add(quit, 1, 3);
        loginGUI.add(message, 0, 4);
        
        
        
        hiScoreGUI.add(backToLogin, 0, 0);
        
        adminGUI.add(clearDatabase, 0, 0);
        adminGUI.add(lisaaSUPER, 0, 1);
        adminGUI.add(backToLogin2, 0, 2);
        adminGUI.add(korvaaAsdwSUPER, 0, 3);
        
        questionGUI.add(question, 0, 0);
        questionGUI.add(answer, 0, 1);
        questionGUI.add(sendAnswer, 0, 2);
        questionGUI.add(tallennaJaLopeta, 0, 3);
        
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
        questionGUI.setHgap(10);
        questionGUI.setVgap(10);
        questionGUI.setPadding(new Insets(10, 10, 10, 10));
        
        
        //Näkymät
        Scene hiScoreNakyma = new Scene(hiScoreGUI, 640, 640);
        Scene loginNakyma = new Scene(loginGUI, 640, 640);
        Scene adminNakyma = new Scene(adminGUI, 640, 640);
        Scene questionNakyma = new Scene(questionGUI, 640, 640);
        
        
        quit.setOnAction((event) -> {
            ikkuna.close();
        });
        
        
        korvaaAsdwSUPER.setOnAction((event) -> {
            User SUPER = new User("SUPER", "SUPER", 1000, 1000);
            try {
                usersDao.update(SUPER, "asd");
            } catch (SQLException ex) {
                Logger.getLogger(QuestionGeneratorGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        lisaaSUPER.setOnAction((event) -> {
            User SUPER = new User("SUPER", "SUPER", 1000, 1000);
            try {
                usersDao.save(SUPER);
            } catch (SQLException ex) {
                Logger.getLogger(QuestionGeneratorGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        hiScores.setOnAction((event) -> {
            try {
                usersDao.addRightForUser("testi");
            } catch (SQLException ex) {
                Logger.getLogger(QuestionGeneratorGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            message.setText("");
            hiScoreGUI.getChildren().clear();
            hiScoreGUI.add(backToLogin, 0, 0);
            hiScoreGUI.setHgap(10);
            hiScoreGUI.setVgap(10);
            hiScoreGUI.setPadding(new Insets(10, 10, 10, 10));
            try {
                ArrayList lista = (ArrayList) usersDao.findAll();
                for (int i=0; i<lista.size(); i++) {
                    User kayttaja = (User) lista.get(i);
                    Label name = new Label(kayttaja.toString());
                    hiScoreGUI.add(name, 0, i+1);
                }
            } catch (SQLException ex) {
                System.out.println("ERROR!");
            }
            ikkuna.setScene(hiScoreNakyma);
        });
        
        newUser.setOnAction((event) -> {
            message.setText("");
            if (!nameInput.getText().equals("") && !passInput.getText().equals("")) {
                User user = new User(nameInput.getText(), passInput.getText(), 0, 0);
                try {
                    nameInput.clear();
                    passInput.clear();
                    if (usersDao.checkContainsName(user.getUsername())) {
                        message.setText("Käyttäjänimi on jo käytössä!");
                    } else {
                        usersDao.save(user);
                        message.setText("Käyttäjä lisätty! Voit nyt kirjautua sisään tiedoilla.");
                    }
                } catch (SQLException ex) {
                    System.out.println("ERROR!!");
                    Logger.getLogger(QuestionGeneratorGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                message.setText("Kirjoita kunnolliset tiedot!");
            }    
        });
        
        
        existingUser.setOnAction((event) -> {
            message.setText("");
            if (nameInput.getText().equals("admin") && passInput.getText().equals("admin")) {
                ikkuna.setScene(adminNakyma);
            } else {
                User loggedUser = null;
                try {
                    loggedUser = usersDao.verifyLogin(nameInput.getText(), passInput.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(QuestionGeneratorGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (loggedUser == null) {
                    message.setText("Käyttäjänimi ja salasana eivät täsmää!");
                } else {
                    System.out.println("Sisäänkirjaus onnistui!");
                    userNameLogged = loggedUser.getUsername();
                    loginGUI.add(maths, 0, 5);
                    loginGUI.add(phys, 1, 5);
                    loginGUI.add(chem, 2, 5);
                    loginGUI.add(all, 3, 5);
                    ikkuna.setTitle("Kirjautuneena sisään: " + userNameLogged);
                    
                }
            }
        });
        
        backToLogin.setOnAction((event) -> {
            message.setText("");
            ikkuna.setScene(loginNakyma);
        });
        backToLogin2.setOnAction((event) -> {
            message.setText("");
            ikkuna.setScene(loginNakyma);
        });
        tallennaJaLopeta.setOnAction((event) -> {
            ikkuna.close();
        });
        
        clearDatabase.setOnAction((event) -> {
            message.setText("");
            try {
                usersDao.clearDatabase();
                ikkuna.setScene(loginNakyma);
            } catch (SQLException ex) {
                System.out.println("DATABASE ERROR!");
                ikkuna.setScene(loginNakyma);
            }
        });
        maths.setOnAction((event) -> {
            gene.setUserName(userNameLogged);
            gene.setMode("maths");
            question.setText(gene.getQuestion());
            ikkuna.setScene(questionNakyma);
        });
        phys.setOnAction((event) -> {
            gene.setUserName(userNameLogged);
            gene.setMode("chem");
            question.setText(gene.getQuestion());
            ikkuna.setScene(questionNakyma);
        });
        chem.setOnAction((event) -> {
            gene.setUserName(userNameLogged);
            gene.setMode("phys");
            question.setText(gene.getQuestion());
            ikkuna.setScene(questionNakyma);
        });
        all.setOnAction((event) -> {
            gene.setUserName(userNameLogged);
            gene.setMode("all");
            question.setText(gene.getQuestion());
            ikkuna.setScene(questionNakyma);
        });
        sendAnswer.setOnAction((event) -> {
            if (gene.sendAnswer(answer.getText())) {
                try {
                    usersDao.addRightForUser(userNameLogged);
                    System.out.println("Oikein!");
                } catch (SQLException ex) {
                    Logger.getLogger(QuestionGeneratorGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("väärin! Oikea vastaus: "+gene.getAnswer());
            }
            try {
                usersDao.addQuestionsForUser(userNameLogged);
            } catch (SQLException ex) {
                Logger.getLogger(QuestionGeneratorGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            question.setText(gene.getQuestion());
        });
        
        
        
        

        
        ikkuna.setTitle("Login");
        ikkuna.setScene(loginNakyma);
        ikkuna.show();
        
    }
    

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        launch(QuestionGeneratorGUI.class);
    }
    
}