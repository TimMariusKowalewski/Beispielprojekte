/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kundenzufriedenheitsumfrage;

/**
 *
 * @author Kowalewski
 */
public class Antwort<T> {
    private Frage _frage;
    private T _benutzereingabe;
    
    public Frage getFrage() {
        return this._frage;
    }
    
    public final void setFrage(Frage F) {
        this._frage = F;
    }
    
    public T getBenutzereingabe() {
        return this._benutzereingabe;
    }
    
    public final void setBenutzereingabe(T Benutzereingabe) {
        this._benutzereingabe = Benutzereingabe;
    }
    
    public Antwort(Frage F, T Benutzereingabe) {
        this.setFrage(F);
        this.setBenutzereingabe(Benutzereingabe);
    }
}