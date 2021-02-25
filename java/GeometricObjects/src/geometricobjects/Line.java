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
public class Line extends GeometricObject {
    // properties
    private Point _point_a;
    private Point _point_b;
    private Point _point_c;
    
    public Point getPointA() {
        return this._point_a;
    }
    
    public void setPointA(Point PointA) {
        this._point_a = PointA;
    }

    public Point getPointB() {
        return this._point_b;
    }
    
    public void setPointB(Point PointB) {
        this._point_b = PointB;
    }
    
    public Point getPointC() {
        return this._point_c;
    }
    
    public void setPointC(Point PointC) {
        this._point_c = PointC;
    }
    
    // constructors
    public Line(Point PointA, Point PointB) {
        super(new Point(0, 0));
        this.setPointA(PointA);
        this.setPointB(PointB);
        //this.setColor(new Point (0, 0, 1));
    }
    
    public double getLength() {
        return GeometricObject.getDistance(this.getPointA(), this.getPointB());
    }
    
    // overrides aus GeometricObject
    @Override
    public void dump() {
        super.dump();
        System.out.println("Length: " + this.getLength());
        this.getPointA().dump();
        this.getPointB().dump();
        System.out.println("");
    }
    
    // class methods
    public void scale(float Factor) {
        this.getPointB().setXCoord(Math.round(this.getPointB().getXCoord() * Factor), false);
        this.getPointB().setYCoord(Math.round(this.getPointB().getYCoord() * Factor), false);
    }
    
    @Override
    public void move(float DeltaX, float DeltaY, float DeltaZ) {
        this.getPointA().setXCoord(this.getPointA().getXCoord() + DeltaX, false);
        this.getPointA().setYCoord(this.getPointA().getYCoord() + DeltaY, false);
        this.getPointB().setXCoord(this.getPointB().getXCoord() + DeltaX, false);
        this.getPointB().setYCoord(this.getPointB().getYCoord() + DeltaY, false);
    }
}