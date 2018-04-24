/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.themorshu.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author ilmar
 */
public class BasicValues {

    public BasicValues() {
    }
    
    
    
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
