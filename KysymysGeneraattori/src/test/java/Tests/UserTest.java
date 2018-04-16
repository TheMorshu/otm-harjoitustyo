package Tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        assertEquals(user.toString(), user.getUsername()+", "+user.getQuestions()+" tehtävää tehty joista "+user.getRight()+" on vastattu oikein.");
    }

    
}
