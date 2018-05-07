package fi.themorshu.logic;


import fi.themorshu.logic.MathGen;
import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ilmar
 */
public class MathGenTest {
    
    MathGen gen;
    BasicValues values;
    
    public MathGenTest() {
    }
    
    
    @Before
    public void setUp() {
        this.gen = new MathGen(new Random());
        this.values = new BasicValues();
    }
    
    @After
    public void tearDown() {
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
    public void rootsOfFunctionToimii() {
        String kysymys = this.gen.rootsOfFunction();
        if (this.gen.getRoot1() <= this.gen.getRoot2()) {
            assertEquals(this.gen.answer(), "" + this.gen.getRoot1() + " ja " + this.gen.getRoot2());
        } else {
            assertEquals(this.gen.answer(), "" + this.gen.getRoot2() + " ja " + this.gen.getRoot1());
        }
    }
    
    
    @Test
    public void questionPalauttaaMerkkijonon() {
        assertEquals(this.gen.question().length() > 0, true);
    }
    
    @Test
    public void interestOfLoanToimii() {
        String kysymys = this.gen.interestOfLoan();
        assertEquals(this.gen.answer(), "" + String.format("%.2f", (this.values.round(this.gen.getFinalLoan(), 2))) + "e");
    }
    
}
