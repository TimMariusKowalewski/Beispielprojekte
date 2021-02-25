/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rennschnecke;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kowalewski
 */
public class Wettbuero {
    private String _name;
    private Rennen _ref_rennen;
    private List<Wette> _bets;
    private float _win_factor;

    public String getName() {
        return this._name;
    }
    
    public void setName(String Name) {
        this._name = Name;
    }
    
    public Rennen getRennen() {
        return this._ref_rennen;
    }
    
    public void setRennen(Rennen R) {
        this._ref_rennen = R;
    }
    
    public List getBets() {
        return this._bets;
    }
    
    private void setBets() {
        this._bets = new ArrayList<>();
    }

    public float getWinFactor() {
        return this._win_factor;
    }
    
    private void setWinFactor(float WinFactor) {
        this._win_factor = WinFactor;
    }
    
    public Wettbuero(String Name, Rennen R, float WinFactor) {
        this.setName(Name);
        this.setRennen(R);
        this.setWinFactor(WinFactor);
        this.setBets();
    }
    
    public void wetteAnnehmen(String schneckenName, int wettEinsatz, String spieler) {
        if(wettEinsatz > 0) {
            Wette _w = new Wette(this.getRennen().getSchneckeByName(schneckenName), spieler, wettEinsatz);
            this.getBets().add(_w); 
        } else {
            System.out.println("Kein negativer Wetteinsatz möglich!");
        }
    }
    
    @Override
    public String toString() {
        String _out = "";
        
        _out += "Name: " + this.getName() + "\n";
        _out += this.getBets().size() + " Wette(n):" + "\n";
        if(this.getBets().size() > 0) {
            for(Object _w: this.getBets()) {
                Wette _w2 = (Wette)_w;
                _out += "\n" + _w2.toString();
            }
        }
   
        return _out;
    }
    
    public void rennenDurchfuehren() {
        this.getRennen().durchfuehren();
    }
    
    public String getWinReport() {
        String _out = "";
        
        for(Object _b: this.getBets()) {
            Wette _b2 = (Wette)_b;
            
            if(_b2.getSchnecke().getName().equals(this.getRennen().getWinner().getName())) {
                _out += _b2.getSpieler() + " gewinnt " + (_b2.getWetteinsatz() * this.getWinFactor()) + " Euro!\n";
            }
        }
        
        return _out;
    } 
}
