package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void testaaAlkusummanOikeellisuus() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikeinPosLisays() {
        kortti.lataaRahaa(500);
        assertEquals("saldo: 5.10", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikeinNegLisays() {
        kortti.lataaRahaa(-1000);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikeinTyhjaLisays() {
        kortti.lataaRahaa(0);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    
    @Test
    public void otaRahaaToimii() {
        if (kortti.otaRahaa(1)) {
            assertEquals("saldo: 0.09", kortti.toString());
        } else if (!kortti.otaRahaa(100)) {
            assertEquals("saldo: 0.10", kortti.toString());
        }
    }
    
    
    
    
}
