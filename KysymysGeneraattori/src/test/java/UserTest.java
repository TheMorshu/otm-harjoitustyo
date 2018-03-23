/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Logiikka.User;
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
        assertEquals("nimi", user.getNimi());
    }
    
    @Test
    public void salasanaSama() {
        assertEquals("salasana", user.getSalasana());
    }
    
    @Test
    public void salasananSetterToimii() {
        user.setSalasana("testi");
        assertEquals("testi", user.getSalasana());
    }
    
    @Test
    public void nimenSetterToimii() {
        user.setNimi("nimi2");
        assertEquals("nimi2", user.getNimi());
    }
    
    @Test
    public void tehtavatAsettuuOikein() {
        assertEquals(0, user.getTehtavat());
    }
    
    
    @Test
    public void oikeinAsettuuOikein() {
        assertEquals(0, user.getOikein());
    }
    
    @Test
    public void tehtavatSetterToimii() {
        user.setTehtavat(10);
        assertEquals(10, user.getTehtavat());
    }
    
    @Test
    public void oikeinSetterToimii() {
        user.setOikein(10);
        assertEquals(10, user.getOikein());
    }
    
    @Test
    public void toStringToimiiOikeinNykyisillaTiedoilla() {
        assertEquals(user.toString(), user.getNimi()+", "+user.getTehtavat()+" tehtävää tehty joista "+user.getOikein()+" on vastattu oikein.");
    }

    
}
