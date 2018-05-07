package fi.themorshu.logic;


import fi.themorshu.dao.Database;
import fi.themorshu.dao.UsersDao;
import fi.themorshu.logic.GeneratorCore;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ilmar
 */
public class GeneratorCoreTest {
    
    GeneratorCore gen;
    UsersDao userDao;
    Database database;
    
    public GeneratorCoreTest() {
    }
    
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        prepareDatabase();
        this.userDao = new UsersDao(this.database);
        this.gen = new GeneratorCore("testi", this.userDao);
    }

    public void prepareDatabase() throws SQLException, ClassNotFoundException {
        database = new Database("jdbc:sqlite:test.db");
        PreparedStatement statement;
        try {
            statement = database.getConnection().prepareStatement("DROP TABLE Users;");
            int changes = statement.executeUpdate();
            statement.close();
            database.closeConnection();
            statement = database.getConnection().prepareStatement("CREATE TABLE users (\n" +
                    "id integer PRIMARY KEY,\n" +
                    "name varchar(200),\n" +
                    "password varchar (200),\n" +
                    "questions integer,\n" +
                    "right integer\n" +
                    ");");
            changes = statement.executeUpdate();
            statement.close();
            database.closeConnection();
        } catch (SQLException ex) {
            statement = database.getConnection().prepareStatement("CREATE TABLE users (\n" +
                    "id integer PRIMARY KEY,\n" +
                    "name varchar(200),\n" +
                    "password varchar (200),\n" +
                    "questions integer,\n" +
                    "right integer\n" +
                    ");");
            int changes = statement.executeUpdate();
            statement.close();
            database.closeConnection();
        }
    }
    
    
    @Test
    public void vastausOnNullAlussa() throws SQLException {
        assertEquals(this.gen.getAnswer(), null);
    }
    
    
    @Test
    public void setModeJaGetModeToimii() {
        String mode = "testi";
        gen.setMode(mode);
        assertEquals(mode, gen.getMode());
    }
    
    @Test
    public void setUserNameToimii() {
        gen.setUserName("uusiNimi");
        assertEquals("uusiNimi", gen.getUserName());
    }
    
    
    @Test
    public void checkAnswerToimiiOikeallaVastauksellaMatikka() throws SQLException {
        gen.setMode("maths");
        String kysymys = gen.getQuestion();
        String vastaus = gen.getAnswer();
        assertEquals(true, gen.checkAnswer(vastaus));
    }
    
    @Test
    public void checkAnswerToimiiVaarallaVastauksellaMatikka() throws SQLException {
        gen.setMode("maths");
        String kysymys = gen.getQuestion();
        String vastaus = gen.getAnswer()+"nakki";
        assertEquals(false, gen.checkAnswer(vastaus));
    }
    
    @Test
    public void checkAnswerToimiiOikeallaVastauksellaKemia() throws SQLException {
        gen.setMode("chem");
        String kysymys = gen.getQuestion();
        String vastaus = gen.getAnswer();
        assertEquals(true, gen.checkAnswer(vastaus));
    }
    
    @Test
    public void checkAnswerToimiiVaarallaVastauksellaKemia() throws SQLException {
        gen.setMode("chem");
        String kysymys = gen.getQuestion();
        String vastaus = gen.getAnswer()+"nakki";
        assertEquals(false, gen.checkAnswer(vastaus));
    }
    
    @Test
    public void checkAnswerToimiiOikeallaVastauksellaFysiikka() throws SQLException {
        gen.setMode("phys");
        String kysymys = gen.getQuestion();
        String vastaus = gen.getAnswer();
        assertEquals(true, gen.checkAnswer(vastaus));
    }
    
    @Test
    public void checkAnswerToimiiVaarallaVastauksellaFysiikka() throws SQLException {
        gen.setMode("phys");
        String kysymys = gen.getQuestion();
        String vastaus = gen.getAnswer()+"nakki";
        assertEquals(false, gen.checkAnswer(vastaus));
    }
    
    @Test
    public void checkAnswerToimiiOikeallaVastauksellaAll() throws SQLException {
        gen.setMode("all");
        String kysymys = gen.getQuestion();
        String vastaus = gen.getAnswer();
        assertEquals(true, gen.checkAnswer(vastaus));
    }
    
    @Test
    public void checkAnswerToimiiVaarallaVastauksellaAll() throws SQLException {
        gen.setMode("all");
        String kysymys = gen.getQuestion();
        String vastaus = gen.getAnswer()+"nakki";
        assertEquals(false, gen.checkAnswer(vastaus));
    }
    
    @Test
    public void getQuestionPalauttaaNullJosModeEpakelpo() throws SQLException {
        gen.setMode("nonValid");
        assertEquals(null, this.gen.getQuestion());
    }
    
    @Test
    public void sendAnswerToimiiOikeallaVastauksellaAll() throws SQLException {
        gen.setMode("all");
        String kysymys = gen.getQuestion();
        String vastaus = gen.getAnswer();
        assertEquals("Vastaus oikein! Sait pisteen!", gen.sendAnswer(vastaus));
    }
    
    @Test
    public void sendAnswerToimiiVaarallaVastauksellaAll() throws SQLException {
        gen.setMode("all");
        String kysymys = gen.getQuestion();
        String vastaus = gen.getAnswer()+"nakki";
        assertEquals("Väärin! Oikea vastaus oli: " + gen.getAnswer(), gen.sendAnswer(vastaus));
    }
    
    @Test
    public void getAnsweredToimiiKunUusiKysymysLuotu() throws SQLException {
        gen.setMode("all");
        gen.getQuestion();
        assertEquals(false, gen.getAnswered());
    }
    
    @Test
    public void getAnsweredToimiiKunKysymykseenVastattu() throws SQLException {
        gen.setMode("all");
        gen.getQuestion();
        gen.sendAnswer("answer");
        assertEquals(true, gen.getAnswered());
    }
    
    @Test
    public void setAnsweredToimii() throws SQLException {
        gen.setMode("all");
        gen.getQuestion();
        gen.setAnswered(true);
        assertEquals(true, gen.getAnswered());
    }
    

    @After
    public void tearDown() throws SQLException {
        database.closeConnection();
    }
}
