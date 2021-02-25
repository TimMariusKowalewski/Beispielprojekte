/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rennschnecke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Kowalewski
 */
public class Rennschnecke {
    private String _name;
    private String _race;
    private float _vmax;
    private List<Waypoint> _waypoints;
    
    public String getName() {
        return this._name;
    }
    
    public void setName(String Name) {
        this._name = Name;
    }
    
    public String getRace() {
        return this._race;
    }

    public void setRace(String Race) {
        this._race = Race;
    }
    
    public float getVMax() {
        return this._vmax;
    }

    public void setVMax(float VMax) {
        this._vmax = VMax;
    }

    public List getWaypoints() {
        return this._waypoints;
    }

    private void setWaypoints(List Waypoints) {
        this._waypoints = Waypoints;
    }
    
    public Rennschnecke(String Name, String Race, float VMax) {
        this.setName(Name);
        this.setRace(Race);
        this.setVMax(VMax);
        this.setWaypoints(new ArrayList());
        Waypoint _wp = new Waypoint(0, 0);
        this.getWaypoints().add(_wp);
    }
    
    public void krieche() {
        Waypoint _position;
        //if(this.getWaypoints().size() > 0){
            _position = (Waypoint)this.getWaypoints().get(this.getWaypoints().size() - 1);
        //} else {
            //_position = new Waypoint(0, 0);
        //}
        
        // wir kriechen zufällig in x-Richtung
        Random _rand = new Random();
        float _speed = 0 + _rand.nextFloat() * (this.getVMax() - 0);
        System.out.println(this.getName() + ": krieche mit " + _speed + "...");
        
        Waypoint _target = new Waypoint(_position.getXCoord() + _speed, 0);
        this.getWaypoints().add(_target);
    }
    
    @Override
    public String toString() {
        String _out = "";
        
        _out += "Name: " + this.getName() + "\n";
        _out += "Race: " + this.getRace() + "\n";
        _out += "VMax: " + this.getVMax() + "\n";
        
        //if(this.getWaypoints().size() > 0) {
            _out += "Bisherige Wegpunkte:" + "\n";
            for(Object _w: this.getWaypoints()) {
                Waypoint _w2 = (Waypoint)_w;
                _out += "(" + _w2.getXCoord() + ", " + _w2.getYCoord() + ")" + "\n";
            }
        //} else {
        //    _out += "Bisher keine Bewegung";
        //}
        
        return _out;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Rennschnecke _rs1 = new Rennschnecke("Toni", "Zombie", 2f);
        Rennschnecke _rs2 = new Rennschnecke("Roger", "Zombie", 2f);
        Rennschnecke _rs3 = new Rennschnecke("Bärbel", "Ork", 2f);
        Rennschnecke _rs4 = new Rennschnecke("Heidi", "Ork", 2f);
        Rennschnecke _rs5 = new Rennschnecke("foo", "bar", 3f);
        Rennschnecke _rs6 = new Rennschnecke("Heidi", "Ork", 2f);
        
        Rennen _r = new Rennen("Grand Prix XYZ", new Waypoint(10,0));
        _r.addRennschnecke(_rs1);
        _r.addRennschnecke(_rs2);
        _r.addRennschnecke(_rs3);
        _r.addRennschnecke(_rs4);
        _r.addRennschnecke(_rs6);
        _r.removeRennschnecke("foo");
        System.out.println(_r.toString());
        
        Wettbuero _w = new Wettbuero("foo", _r, 1.8f);
        _w.wetteAnnehmen("Toni", 1, "Spieler1");
        _w.wetteAnnehmen("Toni", 2, "Spieler2");
        _w.wetteAnnehmen("Heidi", 5, "Spieler3");
        _w.wetteAnnehmen("Roger", 8, "Spieler4");
        _w.wetteAnnehmen("Roger", -8, "Spieler4");
        
        System.out.println(_w.toString());
        System.out.println("Das Rennen beginnt:\n");
        _w.rennenDurchfuehren();
        System.out.println(_w.getWinReport());
    }
}