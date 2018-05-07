
package fi.themorshu.logic;

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
public class BasicValuesTest {
    
    BasicValues values;
    
    public BasicValuesTest() {
    }
    
    @Before
    public void setUp() {
        this.values = new BasicValues();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void droppingAccerlationPalauttaaOikeanArvon() {
        assertEquals(9.81 == this.values.droppingAccerlation(), true);
    }
    
    @Test
    public void molarGasConstantOikea() {
        assertEquals(8.31451 == this.values.molarGasConstantJMolKelvin(), true);
    }
    
    @Test
    public void roundToimiiOikein() {
        assertEquals(4.333 == this.values.round(4.3333, 3), true);
    }
    
    @Test
    public void roundToimiiOikeinKaksi() {
        assertEquals(4.35 == this.values.round(4.3500, 3), true);
    }
    
    @Test
    public void roundToimiiOikeinKolme() {
        assertEquals(4.36 == this.values.round(4.3555, 2), true);
    }
    
    @Test
    public void roundToimiiOikeinNelja() {
        assertEquals(4.311 == this.values.round(4.3111, 3), true);
    }

}
