/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rennschnecke;

/**
 *
 * @author Kowalewski
 */
public class Wette {
    private Rennschnecke _ref_schnecke;
    private String _spieler;
    private int _wetteinsatz;
    
    public Rennschnecke getSchnecke() {
        return this._ref_schnecke;
    }
    
    public void setSchnecke(Rennschnecke S) {
        this._ref_schnecke = S;
    }

    public String getSpieler() {
        return this._spieler;
    }
    
    public void setSpieler(String Spieler) {
        this._spieler = Spieler;
    }
    
    public float getWetteinsatz() {
        return this._wetteinsatz;
    }
    
    public void setWetteinsatz(int Wetteinsatz) {
        if(Wetteinsatz < 0) {
            System.out.println("Kein negativer Wetteinsatz möglich!");
        } else {
            this._wetteinsatz = Wetteinsatz;
        }
    }
    
    public Wette(Rennschnecke R, String Spieler, int Wetteinsatz) {
        if(Wetteinsatz > 0) {
            this.setSchnecke(R);
            this.setSpieler(Spieler);
            this.setWetteinsatz(Wetteinsatz);
        } else {
            System.out.println("Kein negativer Wetteinsatz möglich!");
        }
    }
    
    @Override
    public String toString() {
        String _out = "Wette für " + this.getSchnecke().getName() + " von ";
        
        _out += this.getSpieler() + ": ";
        _out += this.getWetteinsatz() + " Euro";
        
        return _out;
    }
}
