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
public class Circle extends GeometricObject {
    // properties
    private double _radius;
    private boolean _filled;
    
    public double getRadius() {
        return this._radius;
    }
    
    public double getGLRadius() {
        return (this._radius / GeometricObject.getWindowWidth());
    }
    
    public void setRadius(double Radius) {
        if(Radius < 0) {
            throw new IllegalArgumentException("Circle: Kein Radius < 0 erlaubt");
        }
        this._radius = Radius;
    }

    public boolean getFilled() {
        return this._filled;
    }
    
    public void setFilled(boolean Fillstatus) {
        this._filled = Fillstatus;       
    }
    
    // constructors
    public Circle(Point Basepoint, double Radius) {
        super(Basepoint);
        this.setRadius(Radius);
        //this.setColor(new Point(0, 0, 1));
    }
    
    // overrides aus GeometricObject
    @Override
    public void dump() {
        super.dump();
        System.out.println("Radius: " + this.getRadius());
        System.out.println("Area: " + this.getArea());
        System.out.println("Perimeter: " + this.getPerimeter());
        System.out.println("");
    }
    
    public double getArea() {
        return Math.pow(this.getRadius(), 2) * Math.PI;
    }
    
    public double getPerimeter() {
        return 2 * this.getRadius() * Math.PI;
    }
    
    @Override
    public void move(float X, float Y, float Z) {
        
    }
    
    @Override
    public void scale(float Factor) {
        this._radius = (int)this._radius * Factor;
    }
    
    // class methods

}