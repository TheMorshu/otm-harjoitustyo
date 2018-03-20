/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

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
public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kroisosKortti;
    Maksukortti tyhjaKortti;
    
    
//    public KassapaateTest() {
//    }
    
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();  
        kroisosKortti = new Maksukortti(100000);
        tyhjaKortti = new Maksukortti(100);
    }
    @Test
    public void luotuKassaOlemassa() {
        assertTrue(kassa!=null);      
    }
    @Test
    public void alussaOikeaMaaraRahaa() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void alussaOikeaMaaraMyytyjaLounaita() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty()+kassa.edullisiaLounaitaMyyty());
    }
    @Test
    public void kateisOstoToimiiKunRahaRiittaaSaldoOikeaMaukas() {
        kassa.syoMaukkaasti(500);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    @Test
    public void kateisOstoToimiiKunRahaRiittaaSaldoOikeaEdullinen() {
        kassa.syoEdullisesti(500);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    @Test
    public void kateisOstoToimiiKunRahaEiRiitaSaldoOikea() {
        kassa.syoEdullisesti(0);
        kassa.syoMaukkaasti(0);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void vaihtoRahaOikeaMaukas() {
        int vaihto = kassa.syoMaukkaasti(500);
        assertEquals(vaihto, 100);
    }
    @Test
    public void vaihtoRahaOikeaEdullinen() {
        int vaihto = kassa.syoEdullisesti(500);
        assertEquals(vaihto, 260);
    }
    @Test
    public void vaihtoRahaOikeaMaukasKunMaksuLiianVahan() {
        int vaihto = kassa.syoMaukkaasti(100);
        assertEquals(vaihto, 100);
    }
    @Test
    public void vaihtoRahaOikeaEdullinenKunMaksuLiianVahan() {
        int vaihto = kassa.syoEdullisesti(100);
        assertEquals(vaihto, 100);
    }
    @Test
    public void kateisOstoToimiiEdullinenMyydytOikea() {
        kassa.syoEdullisesti(500);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    @Test
    public void kateisOstoToimiiMaukasMyydytOikea() {
        kassa.syoMaukkaasti(500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    @Test
    public void kateisOstoToimiiEdullinenMyydytOikeaKunMaksuLiianVahan() {
        kassa.syoEdullisesti(50);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }    
    @Test
    public void kateisOstoToimiiMaukasMyydytOikeaKunMaksuLiianVahan() {
        kassa.syoMaukkaasti(50);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    @Test
    public void josKortillaTarpeeksiRahaaVeloitusOikeaJaTrueEdullinen() {
        if (kassa.syoEdullisesti(kroisosKortti)) {
            assertEquals(100000-240, kroisosKortti.saldo());
        }
    }
    @Test
    public void josKortillaTarpeeksiRahaaVeloitusOikeaJaTrueMaukas() {
        if (kassa.syoMaukkaasti(kroisosKortti)) {
            assertEquals(100000-400, kroisosKortti.saldo());
        }
    }
    @Test
    public void josKortillaTarpeeksiRahaaMyydytKasvaaEdullinen() {
        kassa.syoEdullisesti(kroisosKortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    @Test
    public void josKortillaTarpeeksiRahaaMyydytKasvaaMaukas() {
        kassa.syoMaukkaasti(kroisosKortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    @Test
    public void josKortillaEiTarpeeksiRahaaVeloitusOikeaJaFalseEdullinen() {
        if (!kassa.syoEdullisesti(tyhjaKortti)) {
            assertEquals(100, tyhjaKortti.saldo());
        }
    }
    @Test
    public void josKortillaEiTarpeeksiRahaaVeloitusOikeaJaFalseMaukas() {
        if (!kassa.syoMaukkaasti(tyhjaKortti)) {
            assertEquals(100, tyhjaKortti.saldo());
        }
    }
    @Test
    public void josKortillaEiTarpeeksiRahaaMyydytEiKasvaEdullinen() {
        kassa.syoEdullisesti(tyhjaKortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    @Test
    public void josKortillaEiTarpeeksiRahaaMyydytEiKasvaMaukas() {
        kassa.syoMaukkaasti(tyhjaKortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    @Test
    public void kassanRahamaaraEiMuutuKorttiostoksessa() {
        kassa.syoMaukkaasti(kroisosKortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void korttiaLadatessaKortinSaldoOikea() {
        kassa.lataaRahaaKortille(tyhjaKortti, 100000);
        assertEquals(100000+100, tyhjaKortti.saldo());
    }
    @Test
    public void korttiaLadatessaKassanRahaOikea() {
        kassa.lataaRahaaKortille(tyhjaKortti, 100000);
        assertEquals(100000+100000, kassa.kassassaRahaa());
    }
    @Test
    public void korttiaLadatessaMitaanEiTapahduNegSummallaKortinSaldo() {
        kassa.lataaRahaaKortille(tyhjaKortti, -100);
        assertEquals(100, tyhjaKortti.saldo());
    }
    @Test
    public void korttiaLadatessaMitaanEiTapahduNegSummallaKassanRaha() {
        kassa.lataaRahaaKortille(tyhjaKortti, -100);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    
}
