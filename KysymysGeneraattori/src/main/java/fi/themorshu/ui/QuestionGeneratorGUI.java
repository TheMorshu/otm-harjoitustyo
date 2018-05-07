package fi.themorshu.ui;
import fi.themorshu.dao.Database;
import fi.themorshu.dao.UsersDao;
import fi.themorshu.logic.GeneratorCore;
import fi.themorshu.dao.User;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class QuestionGeneratorGUI extends Application {
    String userNameLogged;
    Boolean neverLogged;
    UsersDao usersDao;
    GeneratorCore gene;
    GridPane loginGUI;
    
    @Override
    public void init() {
        try {
            usersDao = new UsersDao(new Database("jdbc:sqlite:users.db"));
        } catch (SQLException ex) {}
        userNameLogged = "";
        gene = new GeneratorCore("", usersDao);
        usersDao.setUpTableOnDatabase(); //Luo Users tablen databaseen, jos sitä ei jo ole (välttämätön, jso ohjelam käynnistetään 1. kertaa!)
    }
    
    public void resetBackOriginalLogin() {
        userNameLogged = "";
        loginGUI.getChildren().remove(9, 15);
        neverLogged = true;
    }
    
    @Override
    public void start(Stage window) {
        //Teksielementit ja kentät
        Label nameText = new Label("Nimi: ");
        TextField nameInput = new TextField();
        Label passText = new Label("Salasana: ");
        PasswordField passInput = new PasswordField();
        Label message = new Label("");
        Label question = new Label("Kysymys: ");
        TextField answer = new TextField();
        Label newPassText = new Label("Uusi salasana: ");
        PasswordField newPassInput = new PasswordField();
        Label userToBeRemovedText = new Label("Poistettava käyttäjä: ");
        TextField userToBeRemovedLabel = new TextField();
        Label feedbackText = new Label("");
        TextArea usersAndPasswords = new TextArea();
        TextArea hiScoresArea = new TextArea();
        
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
        Button listUsernamesAndPasswords = new Button("Listaa käyttäjänimet ja salasanat");
        Button nextQuestion = new Button("Seuraava kysymys");
        Button returnToQuestionSelection = new Button("Palaa tehtävävalintaan");
        Button logout = new Button("Kirjaudu ulos!");
        
        //Asettelut
        loginGUI = new GridPane();
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
        hiScoreGUI.add(hiScoresArea, 0, 1);
        adminGUI.add(clearDatabase, 0, 0);
        adminGUI.add(backToLogin2, 0, 1);
        adminGUI.add(removeUser, 0, 2);
        adminGUI.add(userToBeRemovedText, 0, 3);
        adminGUI.add(userToBeRemovedLabel, 1, 3);
        adminGUI.add(listUsernamesAndPasswords, 0, 4);
        adminGUI.add(usersAndPasswords, 0, 5);
        questionGUI.add(question, 0, 0);
        questionGUI.add(answer, 0, 1);
        questionGUI.add(sendAnswer, 0, 3);
        questionGUI.add(nextQuestion, 0, 4);
        questionGUI.add(returnToQuestionSelection, 0, 5);
        questionGUI.add(saveAndQuit, 0, 6);
        questionGUI.add(feedbackText, 0, 2);
        userSettingsGUI.add(deleteAccount, 0, 0);
        userSettingsGUI.add(resetScore, 1, 0);
        userSettingsGUI.add(changePassword, 2, 0);
        userSettingsGUI.add(newPassText, 2, 1);
        userSettingsGUI.add(newPassInput, 2, 2);
        userSettingsGUI.add(backToLogin3, 0, 3);
        
        //Näkymät
        Scene hiScoreScene = new Scene(hiScoreGUI, 800, 400);
        Scene loginScene = new Scene(loginGUI, 800, 400);
        Scene adminScene = new Scene(adminGUI, 800, 400);
        Scene questionScene = new Scene(questionGUI, 800, 400);
        Scene userSettingsScene = new Scene(userSettingsGUI, 800, 400);
        
        quit.setOnAction((event) -> {
            window.close();
        });
        hiScores.setOnAction((event) -> {
            message.setText("");
            String hiScoresText = "";
            try {
                hiScoresArea.setText(usersDao.printAllUsersScores());
            } catch (SQLException ex) {}
            window.setScene(hiScoreScene);
        });
        newUser.setOnAction((event) -> {
            message.setText("");
            User user = new User(nameInput.getText(), passInput.getText(), 0, 0);
            try {
                usersDao.save(user);
            } catch (SQLException ex) {}
            nameInput.clear();
            passInput.clear();
            message.setText(usersDao.getSaveFeedBack());  
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
                        loginGUI.add(logout, 3, 1);
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
            feedbackText.setText(gene.sendAnswer(answer.getText())); //lähettää vastauksen GeneratorCorelle, tekee muutokset tietokantaan ja antaa palautetta totuusarvon perusteella käyttäjälle
        });
        nextQuestion.setOnAction((event) -> {
            feedbackText.setText("");
            question.setText(gene.getQuestion());
            answer.clear();
            gene.setAnswered(false);
        });
        returnToQuestionSelection.setOnAction((event) -> {
            feedbackText.setText("");
            question.setText(gene.getQuestion());
            answer.clear();
            gene.setAnswered(false);
            message.setText("");
            window.setScene(loginScene);
        });
        logout.setOnAction((event) -> {
            window.setTitle("Login");
            resetBackOriginalLogin();
        });
        
        //USER SETTINGS BUTTONS
        userSettings.setOnAction((event) -> {
            window.setScene(userSettingsScene);
        });
        deleteAccount.setOnAction((event) -> {
            try {
                usersDao.delete(userNameLogged);
                message.setText(userNameLogged + "poistettu!");
            } catch (SQLException ex) {}
                window.setTitle("Login");
                window.setScene(loginScene);
                resetBackOriginalLogin();
        });
        resetScore.setOnAction((event) -> {
            try {
                usersDao.resetScore(userNameLogged);
                window.setScene(loginScene);
                message.setText("Käyttäjän tulokset nollattu!");
            } catch (SQLException ex) {}
        });
        changePassword.setOnAction((event) -> {
            if (!newPassInput.getText().equals("")) {
                try {
                    usersDao.changePassword(userNameLogged, newPassInput.getText());
                    resetBackOriginalLogin();
                    window.setTitle("Login");
                    window.setScene(loginScene);
                    message.setText("Salasana vaihdettu! Ole hyvä ja uudelleenkirjaudu!");
                } catch (SQLException ex) {}   
            }
        });
        
        //TÄSTÄ ALASPÄIN NAPPEJA ADMIN KÄYTTÄJÄLLE! (DATABASEN CLEARAAMISEEN, DEBUGGAAMISEEN YMS)
        clearDatabase.setOnAction((event) -> {
            message.setText("");
            try {
                usersDao.clearDatabase();
                window.setTitle("Login");
                window.setScene(loginScene);
                resetBackOriginalLogin();
            } catch (SQLException ex) {
                window.setScene(loginScene);
            }
        });
        removeUser.setOnAction((event) -> {
            try {
                usersDao.delete(userToBeRemovedLabel.getText());
                message.setText(userToBeRemovedLabel.getText() + " poistettu!");
            } catch (SQLException ex) {}
                window.setTitle("Login");
                window.setScene(loginScene);
                resetBackOriginalLogin();
        });
        listUsernamesAndPasswords.setOnAction((event) -> {
            usersAndPasswords.clear();
            String teksti = "";
            try {
                ArrayList dataList = (ArrayList) usersDao.findAll();
                for (int i=0; i<dataList.size(); i++) {
                    User kayttaja = (User) dataList.get(i);
                    teksti += "username: " + kayttaja.getUsername() + " password: " + kayttaja.getPassword() + "\n";
                    usersAndPasswords.setText(teksti);
                }
            } catch (SQLException ex) {}
            usersAndPasswords.setText(teksti);
        });
        window.setTitle("Login");
        window.setScene(loginScene);
        window.show();
    }
    
    public static void main(String[] args) {
        launch(QuestionGeneratorGUI.class);
    }
}