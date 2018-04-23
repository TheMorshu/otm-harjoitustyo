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
    
    public ChemGenTest() {
    }
    

    
    @Before
    public void setUp() {
        this.gen = new ChemGen(new Random());
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void tyhjaKyssari() {
        assertEquals(this.gen.question(), "kyssäri KEMIA");
    }
    
    
    @Test
    public void tyhjaVastaus() {
        assertEquals(this.gen.answer(), "vastaus");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
