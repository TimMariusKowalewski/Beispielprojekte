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
public class Rennen {
    private String _name;
    private Waypoint _goal;
    private Rennschnecke _winner;
    private ArrayList<Rennschnecke> _participants;
    
    public String getName() {
        return this._name;
    }
    
    public void setName(String Name) {
        this._name = Name;
    }
    
    public Waypoint getGoal() {
        return this._goal;
    }
    
    public void setGoal(Waypoint Goal) {
        this._goal = Goal;
    }
    
    public ArrayList getParticipants() {
        return this._participants;
    }
    
    private void setParticipants(ArrayList Participants) {
        this._participants = Participants;
    }
    
    public Rennschnecke getWinner() {
        return this._winner;
    }
    
    private void setWinner(Rennschnecke Winner) {
        this._winner = Winner;
    }
    
    public void addRennschnecke(Rennschnecke R) {
        Rennschnecke _test = this.getSchneckeByName(R.getName());
        
        if(_test == null) {
            this.getParticipants().add(R);
        } else {
            System.out.println("Schnecke mit Namen \"" + R.getName() + "\" bereits vorhanden!");
        }
    }
    
    public void removeRennschnecke(String Name) {
        this.getParticipants().remove(this.getSchneckeByName(Name));
    }
    
    public int getCountParticipants() {
        return this.getParticipants().size();
    }
    
    @Override
    public String toString() {
        String _out = "";
        
        _out += "Name: " + this.getName() + "\n";
        _out += this.getCountParticipants() + " Teilnehmer:" + "\n";
        for(Object _r: this.getParticipants()) {
            Rennschnecke _r2 = (Rennschnecke)_r;
            _out += "\n" + _r2.toString();
        }
    
        return _out;
    }
    
    public Rennen(String Name, Waypoint Goal) {
        this.setName(Name);
        this.setGoal(Goal);
        this.setParticipants(new ArrayList());
    }
    
    public Rennschnecke ermittleGewinner() {
        Rennschnecke _ret = null;
        
        for(Object _r: this.getParticipants()) {
            Rennschnecke _r2 = (Rennschnecke)_r;
            Object _position = _r2.getWaypoints().get(_r2.getWaypoints().size() - 1);
            Waypoint _position2 = (Waypoint)_position;
            
            //int _count_gt_distance = 0;
            if(_position2.getXCoord() >= this.getGoal().getXCoord()) {
                //_count_gt_distance++;
                _ret = _r2;
            }           
        }
        
        return _ret;
    }
    
    public void lasseSchneckenKriechen() {
        // Die Teilnehmerliste wird sortiert!
        // Kopie der Teilnehmerliste erzeugen, damit die ursprüngliche Sortierung erhalten bleibt
        ArrayList<Rennschnecke> _shuffled_list = (ArrayList)this.getParticipants().clone();
        
        Random _rand = new Random();
        
        // wir fangen am Ende der Liste an
        for(int i = _shuffled_list.size() - 1; i >= 1; i--) {
            // wir suchen uns eine zufällige Ganzzahl im Bereich 0 <= i
            int j = _rand.nextInt(i + 1);
            
            // wir tauschen das Element mit Index i gegen das Element mit Index j
            Rennschnecke _r = _shuffled_list.get(i);
            _shuffled_list.set(i, _shuffled_list.get(j));
            _shuffled_list.set(j, _r);
        }
        
        for(Object _r: _shuffled_list) {
            Rennschnecke _r2 = (Rennschnecke)_r;
            _r2.krieche();
            
            // abbrechen, falls eine Schnecke bereits am Ziel ist
            if(this.ermittleGewinner() != null) {
                System.out.println("\n" + "Gewinner / Gewinnerin ist: ");
                System.out.println(this.ermittleGewinner().toString());
                break;
            }
        }    
    }
    
    
    public void durchfuehren() {
        boolean _no_winner = true;
        
        while(_no_winner == true) {
            this.lasseSchneckenKriechen();
            
            if(this.ermittleGewinner() != null) {
                _no_winner = false;
                this.setWinner(this.ermittleGewinner());
            }
        }
        
    }
    
    public Rennschnecke getSchneckeByName(String Name) {
        Rennschnecke _ret = null;
        
        for(Object _r: this.getParticipants()) {
            Rennschnecke _r2 = (Rennschnecke)_r;
            if(_r2.getName().equals(Name)) {
                _ret = _r2;
                break;
            }
            
        }
        
        return _ret;
    }    
}