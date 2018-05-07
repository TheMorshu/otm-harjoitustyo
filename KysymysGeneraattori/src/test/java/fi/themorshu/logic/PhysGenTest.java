
package fi.themorshu.logic;

import fi.themorshu.logic.PhysGen;
import java.util.Random;
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
public class PhysGenTest {
    
    PhysGen gen;
    BasicValues values;
    
    public PhysGenTest() {
    }

    
    @Before
    public void setUp() {
        this.gen = new PhysGen(new Random());
        this.values = new BasicValues();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void questionPalauttaaMerkkijonon() {
        assertEquals(this.gen.question().length() > 0, true); //KESKEN!
    }
    
    @Test
    public void answerOnNullJosEiKysymystä() {
        assertEquals(this.gen.answer(), null);
    }
    
    @Test
    public void answerPalauttaaMerkkijononJosOnKysymys() {
        this.gen.question();
        assertEquals(this.gen.answer().length() > 0, true);
    }
    
    @Test
    public void speedOfVehicleToimii() {
        String kysymys = this.gen.speedOfVehicle();
        assertEquals(this.gen.answer(), "" + String.format("%.2f", (this.values.round(this.gen.getOriginalSpeed(), 2))));
    }
    

}
