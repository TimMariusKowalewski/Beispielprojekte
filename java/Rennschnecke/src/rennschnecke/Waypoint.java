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
public class Waypoint {
    private float _xcoord;
    private float _ycoord;
    
    public float getXCoord() {
        return this._xcoord;
    }
    
    public void setXCoord(float XCoord) {
        this._xcoord = XCoord;
    }

    public float getYCoord() {
        return this._ycoord;
    }
    
    public void setYCoord(float YCoord) {
        this._ycoord = YCoord;
    }
    
    public Waypoint(float XCoord, float YCoord) {
        this.setXCoord(XCoord);
        this.setYCoord(YCoord);
    }
}
