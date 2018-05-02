/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.themorshu.logic;

import fi.themorshu.logic.ChemGen;
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
public class ChemGenTest {
    
    ChemGen gen;
    BasicValues values;
    
    public ChemGenTest() {
    }
    

    
    @Before
    public void setUp() {
        this.gen = new ChemGen(new Random());
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
    public void molarAmountOfIdealGasToimii() {
        String kysymys = this.gen.molarAmountOfIdealGas();
        assertEquals(this.gen.answer(), "" + String.format("%.2f", this.values.round(this.gen.getMolarAmountOfGas(), 2)) + " mol");
    }

}
