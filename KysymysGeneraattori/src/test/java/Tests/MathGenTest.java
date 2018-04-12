package Tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    
    public MathGenTest() {
    }
    
    
    @Before
    public void setUp() {
        this.gen = new MathGen(new Random());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void answerOnNullJosEiKysymystä() {
        assertEquals(this.gen.answer(), null);
    }
    
    @Test
    public void rootsOfFunctionToimii() { //jokin vialla välillä
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
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
