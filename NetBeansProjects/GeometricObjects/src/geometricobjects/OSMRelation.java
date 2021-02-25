/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometricobjects;

/**
 *
 * @author Kowalewski
 */
public class OSMRelation extends OSMObject {
    public OSMRelation(Point Basepoint) {
        super(Basepoint);
    }
    @Override
    public void setLongitude(String Longitude) {
        this._longitude = 0f;
    }
    
    @Override
    public void setLatitude(String Latitude) {
        this._latitude = 0f;
    }     
}
