/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logiikka;

/**
 *
 * @author ilmar
 */
public class User {
    
    private String nimi;
    private String salasana;
    private int tehtavat;
    private int oikein;

    public User(String nimi, String salasana, int tehtavat, int oikein) {
        this.nimi = nimi;
        this.salasana = salasana;
        this.tehtavat = tehtavat;
        this.oikein = oikein;
    }

    
    

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    public int getTehtavat() {
        return tehtavat;
    }

    public void setTehtavat(int tehtavat) {
        this.tehtavat = tehtavat;
    }

    public int getOikein() {
        return oikein;
    }

    public void setOikein(int oikein) {
        this.oikein = oikein;
    }

    @Override
    public String toString() {
        return this.nimi+", "+this.tehtavat+" tehtävää tehty joista "+this.oikein+" on vastattu oikein.";
    }
    
    
    
    
}
