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
public class OSMNode extends OSMObject {

    public OSMNode(Point Basepoint) {
        super(Basepoint);
    }
    
        @Override
    public int getXCoord() {
        float _f = this.getLongitude();
        
        return 0;
    }
    
    @Override
    public int getYCoord() {
        float _f = this.getLatitude();
        return 0;
    }
}