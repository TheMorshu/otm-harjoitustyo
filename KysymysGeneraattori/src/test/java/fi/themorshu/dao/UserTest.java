package fi.themorshu.dao;


import fi.themorshu.dao.User;
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
public class UserTest {
    
    User user;
    
    public UserTest() {
    }
    
    @Before
    public void setUp() {
        this.user = new User("nimi", "salasana", 0, 0);
    }
    
    @Test
    public void nimiSama() {
        assertEquals("nimi", user.getUsername());
    }
    
    @Test
    public void salasanaSama() {
        assertEquals("salasana", user.getPassword());
    }
    
    @Test
    public void salasananSetterToimii() {
        user.setPassword("testi");
        assertEquals("testi", user.getPassword());
    }
    
    @Test
    public void nimenSetterToimii() {
        user.setUsername("nimi2");
        assertEquals("nimi2", user.getUsername());
    }
    
    @Test
    public void tehtavatAsettuuOikein() {
        assertEquals(0, user.getQuestions());
    }
    
    
    @Test
    public void oikeinAsettuuOikein() {
        assertEquals(0, user.getRight());
    }
    
    @Test
    public void tehtavatSetterToimii() {
        user.setQuestions(10);
        assertEquals(10, user.getQuestions());
    }
    
    @Test
    public void oikeinSetterToimii() {
        user.setRight(10);
        assertEquals(10, user.getRight());
    }
    
    @Test
    public void toStringToimiiOikeinNykyisillaTiedoilla() {
        double percentWithOneDecimal = 1.0 * Math.round(1000.0 * user.getRight() / user.getQuestions()) / 10;
        assertEquals(user.toString(), user.getUsername()+", "+user.getQuestions()+" tehtävää tehty joista "+user.getRight()+" on vastattu oikein. (" + percentWithOneDecimal + " % tarkkuus)");
    }
    
    @Test
    public void compareToToimiiOikeinPain() {
        User userOne = new User("asd", "asd", 100, 50);
        User userTwo = new User("asd", "asd", 100, 51);
        assertEquals(true, userOne.compareTo(userTwo) >= 0);
    }
    
    public void equalsToimiiSamoillaOlioilla() {
        User userOne = new User("asd", "asd", 100, 50);
        User userTwo = new User("asd", "asd", 100, 50);
        assertEquals(true, userOne.equals(userTwo));
    }
    
    public void equalsToimiiSamannimisillaOlioilla() {
        User userOne = new User("asd", "asd", 100, 50);
        User userTwo = new User("asd", "asdasdasd", 0, 0);
        assertEquals(true, userOne.equals(userTwo));
    }
    
    public void equalsToimiiEriOlioilla() {
        User userOne = new User("asd", "asd", 100, 50);
        User userTwo = new User("asdy", "asd", 100, 50);
        assertEquals(false, userOne.equals(userTwo));
    }
    
    public void equalsToimiiEriObjekteilla() {
        User userOne = new User("asd", "asd", 100, 50);
        String userTwo = "eiUser";
        assertEquals(false, userOne.equals(userTwo));
    }
    
    public void equalsToimiiNullilla() {
        User userOne = new User("asd", "asd", 100, 50);
        String userTwo = null;
        assertEquals(false, userOne.equals(userTwo));
    }

    
}
