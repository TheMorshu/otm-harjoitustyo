package fi.themorshu.mainUI;

import fi.themorshu.dao.Database;
import fi.themorshu.dao.UsersDao;
import fi.themorshu.logic.GeneratorCore;
import fi.themorshu.dao.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class QuestionGeneratorGUI extends Application {
    String userNameLogged;
    Boolean neverLogged;
    UsersDao usersDao;
    GeneratorCore gene;

    @Override
    public void init() throws ClassNotFoundException, SQLException {
        usersDao = new UsersDao(new Database("jdbc:sqlite:users.db"));
        userNameLogged = "";
        gene = new GeneratorCore("", usersDao);
        usersDao.setUpTableOnDatabase(); //Luo Users tablen databaseen, jos sitä ei jo ole (välttämätön, jso ohjelam käynnistetään 1. kertaa!)
    }

    @Override
    public void start(Stage window) throws ClassNotFoundException, SQLException {
        //Teksielementit ja kentät
        Label nameText = new Label("Nimi: ");
        TextField nameInput = new TextField();
        Label passText = new Label("Salasana: ");
        PasswordField passInput = new PasswordField();
        Label message = new Label("");
        Label question = new Label("Kysymys: ");
        TextField answer = new TextField();
        Label newPassText = new Label("Uusi salasana: ");
        TextField newPassInput = new TextField();
        Label userToBeRemovedText = new Label("Poistettava käyttäjä: ");
        TextField userToBeRemovedLabel = new TextField();
        Label feedbackText = new Label("");
        
        //Napit
        Button newUser = new Button("Uusi käyttäjä");
        Button existingUser = new Button("Vanha käyttäjä");
        Button hiScores = new Button("Tuloslista");
        Button quit = new Button("Lopeta");
        Button backToLogin = new Button("Kirjautumiseen");
        Button backToLogin2 = new Button("Kirjautumiseen");
        Button backToLogin3 = new Button("Kirjautumiseen");
        Button saveAndQuit = new Button("Tallenna ja lopeta");
        Button clearDatabase = new Button("Clear database");
        Button maths = new Button("Tee matikan tehtäviä!");
        Button phys = new Button("Tee fysiikan tehtäviä!");
        Button chem = new Button("Tee kemian tehtäviä!");
        Button all = new Button("Tee kaikkia tehtäviä sekaisin!");
        Button sendAnswer = new Button("Lähetä vastaus!");
        Button userSettings = new Button("Käyttäjän asetukset");
        Button deleteAccount = new Button("Poista tili");
        Button resetScore = new Button("Nollaa tulokset");
        Button changePassword = new Button("Vaihda salasana");
        Button removeUser = new Button("Poista käyttäjä!");
        
        //Asettelut
        GridPane loginGUI = new GridPane();
        GridPane hiScoreGUI = new GridPane();
        GridPane adminGUI = new GridPane();
        GridPane questionGUI = new GridPane();
        GridPane userSettingsGUI = new GridPane();
        
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
        adminGUI.add(backToLogin2, 0, 1);
        adminGUI.add(removeUser, 0, 2);
        adminGUI.add(userToBeRemovedText, 1, 2);
        adminGUI.add(userToBeRemovedLabel, 2, 2);
        questionGUI.add(question, 0, 0);
        questionGUI.add(answer, 0, 1);
        questionGUI.add(sendAnswer, 0, 2);
        questionGUI.add(saveAndQuit, 0, 3);
        questionGUI.add(feedbackText, 0, 4);
        userSettingsGUI.add(deleteAccount, 0, 0);
        userSettingsGUI.add(resetScore, 1, 0);
        userSettingsGUI.add(changePassword, 2, 0);
        userSettingsGUI.add(newPassText, 2, 1);
        userSettingsGUI.add(newPassInput, 2, 2);
        userSettingsGUI.add(backToLogin3, 0, 3);

        //Näkymät
        Scene hiScoreScene = new Scene(hiScoreGUI, 640, 320);
        Scene loginScene = new Scene(loginGUI, 640, 320);
        Scene adminScene = new Scene(adminGUI, 640, 320);
        Scene questionScene = new Scene(questionGUI, 900, 320);
        Scene userSettingsScene = new Scene(userSettingsGUI, 640, 320);
        
        quit.setOnAction((event) -> {
            window.close();
        });
        hiScores.setOnAction((event) -> {
            message.setText("");
            hiScoreGUI.getChildren().clear();
            hiScoreGUI.add(backToLogin, 0, 0);
            try {
                ArrayList lista = (ArrayList) usersDao.findAll();
                for (int i=0; i<lista.size(); i++) {
                    User kayttaja = (User) lista.get(i);
                    Label name = new Label(kayttaja.toString());
                    hiScoreGUI.add(name, 0, i+1);
                }
            } catch (SQLException ex) {}
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
                } catch (SQLException ex) {}
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
                } catch (SQLException ex) {}
                if (loggedUser == null) {
                    message.setText("Käyttäjänimi ja salasana eivät täsmää!");
                } else {
                    userNameLogged = loggedUser.getUsername();
                    if (neverLogged == null || neverLogged == true) {
                        loginGUI.add(maths, 0, 5);
                        loginGUI.add(phys, 1, 5);
                        loginGUI.add(chem, 2, 5);
                        loginGUI.add(all, 3, 5);
                        loginGUI.add(userSettings, 3, 0);
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
        backToLogin3.setOnAction((event) -> {
            message.setText("");
            window.setScene(loginScene);
        });
        saveAndQuit.setOnAction((event) -> {
            window.close();
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
                if (gene.sendAnswer(answer.getText())) {
                    feedbackText.setText("Vastaus oikein! Sait pisteen!");
                } else {
                    feedbackText.setText("Väärin! Oikea vastaus oli: " + gene.getAnswer());
                }
            } catch (SQLException ex) {}
            question.setText(gene.getQuestion());
            answer.clear();
        });
        
        //USER SETTINGS BUTTONS
        userSettings.setOnAction((event) -> {
            window.setScene(userSettingsScene);
        });
        deleteAccount.setOnAction((event) -> {
            try {
                usersDao.delete(userNameLogged);
            } catch (SQLException ex) {}
                userNameLogged = "";
                window.setTitle("Login");
                window.setScene(loginScene);
                loginGUI.getChildren().remove(9, 14);
        });
        resetScore.setOnAction((event) -> {
            try {
                usersDao.resetScore(userNameLogged);
            } catch (SQLException ex) {}
                userNameLogged = "";
                window.setTitle("Login");
                window.setScene(loginScene);
                loginGUI.getChildren().remove(9, 14);
        });
        changePassword.setOnAction((event) -> {
            if (!newPassInput.getText().equals("")) {
                try {
                    usersDao.changePassword(userNameLogged, newPassInput.getText());
                    userNameLogged = "";
                    window.setTitle("Login");
                    window.setScene(loginScene);
                    loginGUI.getChildren().remove(9, 14);
                } catch (SQLException ex) {}   
            }
        });
        
        //TÄSTÄ ALASPÄIN NAPPEJA ADMIN KÄYTTÄJÄLLE! (DATABASEN CLEARAAMISEEN, DEBUGGAAMISEEN YMS)

        clearDatabase.setOnAction((event) -> {
            message.setText("");
            try {
                usersDao.clearDatabase();
                window.setScene(loginScene);
            } catch (SQLException ex) {
                window.setScene(loginScene);
            }
        });
        removeUser.setOnAction((event) -> {
            try {
                usersDao.delete(userToBeRemovedLabel.getText());
            } catch (SQLException ex) {}
                userNameLogged = "";
                window.setTitle("Login");
                window.setScene(loginScene);
        });

        window.setTitle("Login");
        window.setScene(loginScene);
        window.show();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        launch(QuestionGeneratorGUI.class);
    }
}