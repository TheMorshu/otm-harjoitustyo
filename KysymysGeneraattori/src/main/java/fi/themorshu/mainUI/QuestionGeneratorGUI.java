package fi.themorshu.mainUI;

import fi.themorshu.dao.Database;
import fi.themorshu.dao.UsersDao;
import fi.themorshu.logic.Generator;
import fi.themorshu.dao.User;
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
    Boolean neverLogged;
    
    @Override
    public void start(Stage window) throws ClassNotFoundException, SQLException {
        //Database users = new Database("jdbc:sqlite:users.db");
        UsersDao usersDao = new UsersDao(new Database("jdbc:sqlite:users.db"));
        userNameLogged = "";
        Generator gene = new Generator("", usersDao);
        
        //Luo Users tablen databaseen, jos sitä ei jo ole (välttämätön, jso ohjelam käynnistetään 1. kertaa!)
        usersDao.setUpTableOnDatabase();
         
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
        Button saveAndQuit = new Button("Tallenna ja lopeta");
        Button clearDatabase = new Button("Clear database");
        Button maths = new Button("Tee matikan tehtäviä!");
        Button phys = new Button("Tee fysiikan tehtäviä!");
        Button chem = new Button("Tee kemian tehtäviä!");
        Button all = new Button("Tee kaikkia tehtäviä sekaisin!");
        Button sendAnswer = new Button("Lähetä vastaus!");
        Button addSUPER = new Button("LISÄÄ SUPER!");
        Button replaceAsdWithSUPER = new Button("Korvaa asd SUPER:illa!");
        
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
        adminGUI.add(addSUPER, 0, 1);
        adminGUI.add(backToLogin2, 0, 2);
        adminGUI.add(replaceAsdWithSUPER, 0, 3);
        
        questionGUI.add(question, 0, 0);
        questionGUI.add(answer, 0, 1);
        questionGUI.add(sendAnswer, 0, 2);
        questionGUI.add(saveAndQuit, 0, 3);
        
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
        Scene hiScoreScene = new Scene(hiScoreGUI, 640, 640);
        Scene loginScene = new Scene(loginGUI, 640, 640);
        Scene adminScene = new Scene(adminGUI, 640, 640);
        Scene questionScene = new Scene(questionGUI, 640, 640);
        
        quit.setOnAction((event) -> {
            window.close();
        });
         
        replaceAsdWithSUPER.setOnAction((event) -> {
            User SUPER = new User("SUPER", "SUPER", 1000, 1000);
            try {
                usersDao.update(SUPER, "asd");
            } catch (SQLException ex) {
                Logger.getLogger(QuestionGeneratorGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        addSUPER.setOnAction((event) -> {
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
            window.setScene(hiScoreScene);
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
                    Logger.getLogger(QuestionGeneratorGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                message.setText("Kirjoita kunnolliset tiedot!");
            }    
        });
        
        
        existingUser.setOnAction((event) -> {
            message.setText("");
            if (nameInput.getText().equals("admin") && passInput.getText().equals("admin")) {
                window.setScene(adminScene);
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
                    userNameLogged = loggedUser.getUsername();
                    if (neverLogged == null || neverLogged == true) {
                        loginGUI.add(maths, 0, 5);
                        loginGUI.add(phys, 1, 5);
                        loginGUI.add(chem, 2, 5);
                        loginGUI.add(all, 3, 5);
                        neverLogged = false;
                    }
                    window.setTitle("Kirjautuneena sisään: " + userNameLogged);
                }
            }
        });
        
        backToLogin.setOnAction((event) -> {
            message.setText("");
            window.setScene(loginScene);
        });
        backToLogin2.setOnAction((event) -> {
            message.setText("");
            window.setScene(loginScene);
        });
        saveAndQuit.setOnAction((event) -> {
            window.close();
        });
        
        clearDatabase.setOnAction((event) -> {
            message.setText("");
            try {
                usersDao.clearDatabase();
                window.setScene(loginScene);
            } catch (SQLException ex) {
                window.setScene(loginScene);
            }
        });
        maths.setOnAction((event) -> {
            gene.setUserName(userNameLogged);
            gene.setMode("maths");
            question.setText(gene.getQuestion());
            window.setScene(questionScene);
        });
        phys.setOnAction((event) -> {
            gene.setUserName(userNameLogged);
            gene.setMode("phys");
            question.setText(gene.getQuestion());
            window.setScene(questionScene);
        });
        chem.setOnAction((event) -> {
            gene.setUserName(userNameLogged);
            gene.setMode("chem");
            question.setText(gene.getQuestion());
            window.setScene(questionScene);
        });
        all.setOnAction((event) -> {
            gene.setUserName(userNameLogged);
            gene.setMode("all");
            question.setText(gene.getQuestion());
            window.setScene(questionScene);
        });
        sendAnswer.setOnAction((event) -> {
            try {
                gene.sendAnswer(answer.getText());
            } catch (SQLException ex) {
                Logger.getLogger(QuestionGeneratorGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            question.setText(gene.getQuestion());
            answer.clear();
        });
        window.setTitle("Login");
        window.setScene(loginScene);
        window.show();
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        launch(QuestionGeneratorGUI.class);
    }
    
}