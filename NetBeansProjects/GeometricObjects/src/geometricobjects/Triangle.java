/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometricobjects;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static java.lang.Math.sqrt;

/**
 *
 * @author Kowalewski
 */
public class Triangle extends GeometricObject {
    // properties
    private Point _point_a;
    private Point _point_b;
    private Point _point_c;
    private boolean _filled;
    
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
    
    public boolean getFilled() {
        return this._filled;
    }
    
    public void setFilled(boolean Fillstatus) {
        this._filled = Fillstatus;       
    }
    
    // constructors
    public Triangle(Point PointA, Point PointB, Point PointC) {
        super(PointA);
        this.setPointA(PointA);
        this.setPointB(PointB);
        this.setPointC(PointC);
        this.setColor(new Point(0, 0, 1));
    }
    
    // overrides aus GeometricObjects
    @Override
    public void dump() {
        super.dump();
        System.out.println("Area: " + this.getArea());
        System.out.println("Perimeter: " + this.getPerimeter());
        this.getPointA().dump();
        this.getPointB().dump();
        this.getPointC().dump();
        System.out.println("");
    }
    
    // class methods
    public double getArea() {
        // nach Heron
        double _a = GeometricObject.getDistance(this.getPointB(), this.getPointC());
        double _b = GeometricObject.getDistance(this.getPointA(), this.getPointC());
        double _c = GeometricObject.getDistance(this.getPointA(), this.getPointB());
        double _half_perimeter = this.getPerimeter() / 2;
        
        return sqrt(_half_perimeter * (_half_perimeter - _a) * (_half_perimeter - _b) * (_half_perimeter - _c));
    }
    
    public double getPerimeter() {
        double _a = GeometricObject.getDistance(this.getPointB(), this.getPointC());
        double _b = GeometricObject.getDistance(this.getPointA(), this.getPointC());
        double _c = GeometricObject.getDistance(this.getPointA(), this.getPointB());
        
        return _a + _b + _c;
    }
    
    // FIXME: scale by what in case of a triangle???
    public void scale(double Factor) {
        this.getPointB().setXCoord((int)Math.round(this.getPointB().getXCoord() * Factor));
        this.getPointB().setYCoord((int)Math.round(this.getPointB().getYCoord() * Factor));
        this.getPointC().setXCoord((int)Math.round(this.getPointC().getXCoord() * Factor));
        this.getPointC().setYCoord((int)Math.round(this.getPointC().getYCoord() * Factor));
    }
}