/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometricobjects;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author Kowalewski
 */
public abstract class GeometricObject {
    // properties
    private static float _gl_aspect_ratio;
    private static int _window_width;
    private static int _window_height;
    private static int _window_depth;
    private static List<GeometricObject> _objects;
    private static Point _window_midpoint;
    private UUID _id;
    private boolean _id_set;
    private Point _basepoint;
    private Point _color;
    private Object _texture;
    
    public UUID getID() {
        return this._id;
    }
    
    public void setID() {
        if(this.isIDSet() == false) {
            this._id = java.util.UUID.randomUUID();
        }
    }
    
    public boolean isIDSet() {
        return this._id_set;
    }
    
    public void setIDSet() {
        this._id_set = true;
    }
    
    public int getXCoord() {
        return this._basepoint.getXCoord();
    }
    
    public float getGLXCoord() {        
        // Wir haben eine positive Pixel-X-Koordinate(<= Fensterbreite), multiplizieren diese mit 2 und subtrahieren davon die Fensterbreite
        float _coord = (this._basepoint.getXCoord() * 2) - GeometricObject.getWindowWidth();
        // => _coord liegt im Bereich -Fensterbreite bis +Fensterbreite
        
        // jetzt dividieren wir _coord durch die Fensterbreite
        return _coord / GeometricObject.getWindowWidth();
        // => _coord liegt im Bereich -1 bis +1
    }
        
    public void setXCoord(int XCoord) {
        /*if(XCoord < 0) {
            throw new IllegalArgumentException("GeometricObject: XCoord < 0");
        } else if(XCoord > GeometricObject.getWindowWidth()) {
            throw new IllegalArgumentException("GeometricObject: XCoord > " + GeometricObject.getWindowWidth());
        }*/
        
        this._basepoint.setXCoord(XCoord);
    }
    
    public int getYCoord() {
        return this._basepoint.getYCoord();
    }

    public float getGLYCoord() {
        // Wir haben eine positive Pixel-Y-Koordinate(<= Fensterhöhe), multiplizieren diese mit 2 und subtrahieren davon die Fensterhöhe
        float _coord = (this._basepoint.getYCoord() * 2) - GeometricObject.getWindowHeight();
        // => _coord liegt im Bereich -Fensterhöhe bis +Fensterhöhe

        // jetzt dividieren wir _coord durch die Fensterbreite und invertieren das Ergebnis, da die y-Achse nach oben wächst
        return -(_coord / GeometricObject.getWindowHeight());
        // => _coord liegt im Bereich -1 bis +1
    }
    
    public void setYCoord(int YCoord) {
        /*if(YCoord < 0) {
            throw new IllegalArgumentException("GeometricObject: YCoord < 0");
        } else if(YCoord > GeometricObject.getWindowHeight()) {
            throw new IllegalArgumentException("GeometricObject: YCoord > " + GeometricObject.getWindowHeight());
        }*/
        
        this._basepoint.setYCoord(YCoord);
    }
    
    public int getZCoord() {
        return this._basepoint.getZCoord();
    }

    public float getGLZCoord() {
        return this.getZCoord(); // / GeometricObject.getWindowDepth();
    }
    
    public void setZCoord(int ZCoord) {
        this._basepoint.setZCoord(ZCoord);
    }

    public Point getBasepoint() {
        return this._basepoint;
    }
    
    /**
     *
     * @param Basepoint
     */
    protected void setBasepoint(Point Basepoint) {
        this._basepoint = Basepoint;
    }
    
    public Point getColor() {
        return this._color;
    }
    
    public void setColor(Point Color) {
        this._color = Color;
    }
    
    public static float getGLAspectRatio() {
        return GeometricObject._gl_aspect_ratio;
    }
    
    public static void setGLAspectRatio(float AspectRatio) {
        GeometricObject._gl_aspect_ratio = AspectRatio;
    }
    
    public static int getWindowWidth() {
        return GeometricObject._window_width;
    }
    
    public static void setWindowWidth(int WindowWidth) {
        GeometricObject._window_width = WindowWidth;
    }
    
    public static int getWindowHeight() {
        return GeometricObject._window_height;
    }
    
    public static void setWindowHeight(int WindowHeight) {
        GeometricObject._window_height = WindowHeight;
    }
    
    public static int getWindowDepth() {
        return GeometricObject._window_depth;
    }
    
    public static void setWindowDepth(int WindowDepth) {
        GeometricObject._window_depth = WindowDepth;
    }
    
    public static Point getWindowMidpoint() {        
        return GeometricObject._window_midpoint;
    }
    
    public static void setWindowMidpoint() {
        int _x = (int)Math.floor(GeometricObject.getWindowWidth() / 2);
        int _y = (int)Math.floor(GeometricObject.getWindowHeight() / 2);
        
        GeometricObject._window_midpoint = new Point(_x, _y, 0);
    }

    public static List getObjectList() {
        return GeometricObject._objects;
    }
    
    public static void initObjectList() {
        GeometricObject._objects = new ArrayList();
    }
    
    public Object getTexture() {
        return this._texture;
    }
    
    public void setTexture(Object T) {
        this._texture = T;
    }
    
    // constructors   
    public GeometricObject(Point Basepoint) {
        this.setBasepoint(Basepoint);
        this.setID();
        this.setIDSet();
    }
    
    public GeometricObject() {
        this.setID();
        this.setIDSet();
    }

    // abstract methods
    
    // static methods
    public static double getDistance(Point PointA, Point PointB) {
        double _a = abs(PointA.getXCoord() - PointB.getXCoord());
        double _b = abs(PointA.getYCoord() - PointB.getYCoord());
        
        return sqrt(pow(_a, 2) + pow(_b, 2));
    }
    
    //FIXME: getDistance3D
    
    /*public static double getGLDistance(Point PointA, Point PointB) {
        double _a = abs(PointA.getGLXCoord() - PointB.getGLXCoord());
        double _b = abs(PointA.getGLYCoord() - PointB.getGLYCoord());
        
        return sqrt(pow(_a, 2) + pow(_b, 2));
    }*/
    
    // class methods
    public void dump() {
        String _out = "window: " + GeometricObject.getWindowWidth() + "x" + GeometricObject.getWindowHeight() + "x" + GeometricObject.getWindowDepth() + " " + GeometricObject.getGLAspectRatio() + "\n";
        _out += this.getClass().toString() + " (" + this.getXCoord() + ", " + this.getYCoord() + ", " + this.getZCoord() + ") | " + this.getGLXCoord() + ", " + this.getGLYCoord() + ", " + this.getGLZCoord() + ") " + this.getID();        
        System.out.println(_out);
    }
    
    public void move(int DeltaX, int DeltaY) {
        this.setXCoord(this.getXCoord() + DeltaX);
        this.setYCoord(this.getYCoord() + DeltaY);
    }
    
    /*public void setRandomCoords() {
        Random _random = new Random();
        int _random_int = _random.nextInt(GeometricObject.getWindowWidth());
    }*/
    
    public void onClick(int X, int Y) {
        //System.out.println("GeometricObject:onClick(): implement me!");
    }
    
    public void onMouseOver(int X, int Y) {
        //System.out.println("GeometricObject:onMouseOver(): implement me!");
    }
    
    public void onBeingAlive() {
        //System.out.println("GeometricObject:onMouseOver(): implement me!");
    }
}