/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometricobjects;

import java.util.Random;

/**
 *
 * @author Kowalewski
 */
public class Rectangle extends GeometricObject {
    // properties
    private Point _a;
    private Point _b;
    private Point _c;
    private Point _d;
    private boolean _filled;
    
    public Point getPointA() {
        return this._a;
    }
    
    public void setPointA(Point P) {
        this._a = P;
        this.setBasepoint(P);
    }
    
    public Point getPointB() {
        return this._b;
    }
    
    public void setPointB(Point P) {
        this._b = P;       
    }
    
    public Point getPointC() {
        return this._c;
    }
    
    public void setPointC(Point P) {
        this._c = P;       
    }
    
    public Point getPointD() {
        return this._d;
    }
    
    public void setPointD(Point P) {
        this._d = P;       
    }
    
    public boolean getFilled() {
        return this._filled;
    }
    
    public void setFilled(boolean Fillstatus) {
        this._filled = Fillstatus;       
    }
    
    // constructors
    // A: oben links, B: unten links, C: unten rechts, D: oben links
    public Rectangle(Point A, Point B, Point C, Point D) {
        super(A);
        this.setPointA(A);
        this.setPointB(B);
        this.setPointC(C);
        this.setPointD(D);
        //this.setColor(new Point(1, 0, 0));
    }
    
    @Override
    public void dump() {
        String _out = super.toString();
        System.out.println("LengthA: " + this.getLengthA());
        System.out.println("LengthB: " + this.getLengthB());
        System.out.println("Area: " + this.getArea());
        System.out.println("Perimeter: " + this.getPerimeter());
        this.getPointA().dump();
        this.getPointB().dump();
        this.getPointC().dump();
        this.getPointD().dump();
        System.out.println("");
    }
    
    public Point getBasepoint() {
        return this.getPointA();
    }
    
    // class methods    
    public double getLengthA() {
        return GeometricObject.getDistance(this.getPointA(), this.getPointD());
    }
    
    public void setLengthA(double Length) {
        double _diff = this.getLengthA() - Length;

        this.getPointB().setXCoord(Math.round(this.getPointB().getXCoord() + _diff), false);
        this.getPointB().setYCoord(Math.round(this.getPointB().getYCoord() + _diff), false);
        this.getPointC().setXCoord(Math.round(this.getPointC().getXCoord() + _diff), false);
        this.getPointC().setYCoord(Math.round(this.getPointC().getYCoord() + _diff), false);
    }
    
    public double getLengthB() {
        return GeometricObject.getDistance(this.getPointA(), this.getPointB());
    }   
    
    public void setLengthB(double Length) {
        double _diff = this.getLengthB() - Length;

        this.getPointC().setXCoord(Math.round(this.getPointC().getXCoord() + _diff), false);
        this.getPointC().setYCoord(Math.round(this.getPointC().getYCoord() + _diff), false);        
        this.getPointD().setXCoord(Math.round(this.getPointD().getXCoord() + _diff), false);
        this.getPointD().setYCoord(Math.round(this.getPointD().getYCoord() + _diff), false);
    }
    
    public double getArea() {
        return this.getLengthA() * this.getLengthB();
    }
    
    public double getPerimeter() {
        return 2 * (this.getLengthA() + this.getLengthB());
    }
    
    @Override
    public void move(float X, float Y, float Z) {
        Random _rand = new Random();
        
        X += _rand.nextInt(10);
        Y += _rand.nextInt(10);    
        this.getPointA().move(X, Y, Z);
        this.getPointB().move(X, Y, Z);
        this.getPointC().move(X, Y, Z);
        this.getPointD().move(X, Y, Z);
        
        if(this.getPointA().getYCoord() > GeometricObject.getWindowHeight() && this.getPointA().getXCoord() > GeometricObject.getWindowWidth()) {            
            this.getPointA().setXCoord(50, false);
            this.getPointA().setYCoord(50, false);
            this.getPointB().setXCoord(50, false);
            this.getPointB().setYCoord(106, false);
            this.getPointC().setXCoord(106, false);
            this.getPointC().setYCoord(106, false);
            this.getPointD().setXCoord(106, false);
            this.getPointD().setYCoord(50, false);
            /*this.getPointB().setYCoord(this.getPointB().getYCoord() - GeometricObject.getWindowHeight());
            this.getPointC().setYCoord(this.getPointC().getYCoord() - GeometricObject.getWindowHeight());
            this.getPointD().setYCoord(this.getPointD().getYCoord() - GeometricObject.getWindowHeight());
            this.getPointA().setXCoord(this.getPointA().getXCoord() - GeometricObject.getWindowWidth());
            this.getPointB().setXCoord(this.getPointB().getXCoord() - GeometricObject.getWindowWidth());
            this.getPointC().setXCoord(this.getPointC().getXCoord() - GeometricObject.getWindowWidth());
            this.getPointD().setXCoord(this.getPointD().getXCoord() - GeometricObject.getWindowWidth());*/
        }
    }
    
    @Override
    public void scale(float Factor) {
        this.setLengthA(this.getLengthA() * Factor);
        this.setLengthB(this.getLengthB() * Factor);
    }  
}