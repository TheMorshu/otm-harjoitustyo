package fi.themorshu.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Luokan tarkoituksena on toimia työkaluna kysymysgeneraattoreille (tarjoka esimerkiksi vakioarvoja, pyöristää lukuja)
 */
public class BasicValues {

    
    /**
     * Konstruktori eti tarvitse parametrejä
     */
    public BasicValues() {
    }
    
    
    /**
     * Tämän funktion tarkoituksena on pyöristää parametrina annettu luku toisena parametrina annetun luvun desimaalimäärän tarkkuuteen
     * @param value Tämä luku pyröristetään
     * @param places Näin monen desimaalin tarkkuuteen
     * @return Palauttaa pyöristetyn arvon
     */
    public double round(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    public double droppingAccerlation() {
        return 9.81;
    }
    
    
    public double molarGasConstantJMolKelvin() {
        return 8.31451;
    }
    
    
}
