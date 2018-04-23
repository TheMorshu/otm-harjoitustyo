/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public PhysGenTest() {
    }

    
    @Before
    public void setUp() {
        this.gen = new PhysGen(new Random());
    }
    
    @After
    public void tearDown() {
    }
    


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
