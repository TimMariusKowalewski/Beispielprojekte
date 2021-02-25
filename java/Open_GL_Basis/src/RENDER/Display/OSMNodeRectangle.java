/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RENDER.Display;

import geometricobjects.GeometricObject;
import geometricobjects.Rectangle;
import geometricobjects.Point;
import geometricobjects.OSMNode;

/**
 *
 * @author Kowalewski
 */
public class OSMNodeRectangle extends Rectangle {
    private OSMNode _osmnode;
    
    public OSMNode getOSMNode() {
        return this._osmnode;
    }
    
    public void setOSMNode(OSMNode Node) {
        this._osmnode = Node;
    }
    
    public OSMNodeRectangle(Point PointA, Point PointB, Point PointC, Point PointD) {
        super(PointA, PointB, PointC, PointD);
    }
    
}
