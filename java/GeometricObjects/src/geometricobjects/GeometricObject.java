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
import java.util.UUID;

/**
 *
 * @author Kowalewski
 */
public abstract class GeometricObject {
    // properties
    private static float _gl_aspect_ratio;
    private static float _world_width;
    private static float _world_height;
    private static float _world_depth;    
    private static int _window_width;
    private static int _window_height;
    private static int _window_depth;
    private static float _gl_width;
    private static float _gl_height;
    private static float _gl_depth;
    private static List<GeometricObject> _objects;
    private static Point _window_midpoint;
    private UUID _id;
    private boolean _id_set;
    private Point _basepoint;
    private Vector _position_vector_base;
    private Vector _position_vector_window;
    private Vector _position_vector_gl;
    
    public static float getGLAspectRatio() {
        return GeometricObject._gl_aspect_ratio;
    }
    
    public static void setGLAspectRatio() {
        GeometricObject._gl_aspect_ratio = GeometricObject.getGLWidth() / GeometricObject.getGLHeight() ;
    }
    
    public static void setGLAspectRatio(float AspectRatio) {
        GeometricObject._gl_aspect_ratio = AspectRatio;
    }
    
    public static float getWorldWidth() {
        return GeometricObject._world_width;
    }
    
    public static void setWorldWidth(float WorldWidth) {
        GeometricObject._world_width = WorldWidth;
    }
    
    public static float getWorldHeight() {
        return GeometricObject._world_height;
    }
    
    public static void setWorldHeight(float WorldHeight) {
        GeometricObject._world_height = WorldHeight;
    }
    
    public static float getWorldDepth() {
        return GeometricObject._world_depth;
    }
    
    public static void setWorldDepth(float WorldDepth) {
        GeometricObject._world_depth = WorldDepth;
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

    public static float getGLWidth() {
        return GeometricObject._gl_width;
    }
    
    public static void setGLWidth(float GLWidth) {
        GeometricObject._gl_width = GLWidth;
    }
    
    public static float getGLHeight() {
        return GeometricObject._gl_height;
    }
    
    public static void setGLHeight(float GLHeight) {
        GeometricObject._gl_height = GLHeight;
    }
    
    public static float getGLDepth() {
        return GeometricObject._gl_depth;
    }
    
    public static void setGLDepth(float GLDepth) {
        GeometricObject._gl_depth = GLDepth;
    }
    
    public UUID getID() {
        return this._id;
    }
    
    public final void setID() {
        if(this.isIDSet() == false) {
            this._id = java.util.UUID.randomUUID();
        }
    }
    
    public boolean isIDSet() {
        return this._id_set;
    }
    
    public final void setIDSet() {
        this._id_set = true;
    }
    
    public float getCoord(String CoordName, String CoordinateSystem) {
        Vector _vec;
        
        switch(CoordinateSystem) {
            case "base":
                _vec = this._position_vector_base;
                break;
            case "window":
                _vec = this._position_vector_window;
                break;
            case "gl":
                _vec = this._position_vector_gl;
                break;
            default:
                throw new IllegalArgumentException("GeometricObject.getCoord(String CoordName, String CoordinateSystem): unknown CoordinateSystem");
        }
        
        switch(CoordName) {
            case "X":
                return _vec.getCoord(0);
            case "Y":
                return _vec.getCoord(1);
            case "Z":
                return _vec.getCoord(2);
            default:
                throw new IllegalArgumentException("GeometricObject.getCoord(String CoordName, String CoordinateSystem): unknown CoordName");
        }
    }
    
    public void setCoord() {
        
    }
    
    public float getXCoord() {
        return this._basepoint.getXCoord();
    }
    
    public void setXCoord(float XCoord, boolean FullUpdate) {        
        this._basepoint.setXCoord(XCoord, false);
        
        if(FullUpdate == true) {
            this._basepoint.setGLXCoord(GeometricObject.translateXCoord2GLXCoord(XCoord), false);
            this._basepoint.setWindowXCoord(GeometricObject.translateXCoord2WindowXCoord(XCoord), false);
        }
    }
    
    public int getWindowXCoord() {
        return this._basepoint.getWindowXCoord();
    }
    
    public void setWindowXCoord(int WindowXCoord, boolean FullUpdate) {
        this._basepoint.setWindowXCoord(WindowXCoord, false);
        
        if(FullUpdate == true) {
            this._basepoint.setXCoord(GeometricObject.translateWindowXCoord2XCoord(WindowXCoord), false);
            this._basepoint.setGLXCoord(GeometricObject.translateXCoord2GLXCoord(WindowXCoord), false);
        }
    }
    
    public float getGLXCoord() {
        return this._basepoint.getGLXCoord();
    }
    
    public void setGLXCoord(float GLXCoord, boolean FullUpdate) {
        this._basepoint.setGLXCoord(GLXCoord, false);
        
        if(FullUpdate == true) {
            this._basepoint.setXCoord(GeometricObject.translateGLXCoord2XCoord(GLXCoord), false);
            this._basepoint.setWindowXCoord(GeometricObject.translateGLXCoord2WindowXCoord(GLXCoord), false);
        }
    }
    
    public float getYCoord() {
        return this._basepoint.getYCoord();
    }
    
    public void setYCoord(float YCoord, boolean FullUpdate) {        
        this._basepoint.setYCoord(YCoord, false);
        
        if(FullUpdate == true) {
            this._basepoint.setGLYCoord(GeometricObject.translateYCoord2GLYCoord(YCoord), false);
            this._basepoint.setWindowYCoord(GeometricObject.translateYCoord2WindowYCoord(YCoord), false);
        }
    }
    
    public int getWindowYCoord() {
        return this._basepoint.getWindowYCoord();
    }
    
    public void setWindowYCoord(int WindowYCoord, boolean FullUpdate) {
        this._basepoint.setWindowYCoord(WindowYCoord, false);
        
        if(FullUpdate == true) {
            this._basepoint.setYCoord(GeometricObject.translateWindowYCoord2YCoord(WindowYCoord), false);
            this._basepoint.setGLYCoord(GeometricObject.translateYCoord2GLYCoord(WindowYCoord), false);
        }
    }
    
    public float getGLYCoord() {
        return this._basepoint.getGLYCoord();
    }
    
    public void setGLYCoord(float GLYCoord, boolean FullUpdate) {        
        this._basepoint.setGLYCoord(GLYCoord, false);
        
        if(FullUpdate == true) {
            this._basepoint.setYCoord(GeometricObject.translateGLYCoord2YCoord(GLYCoord), false);
            this._basepoint.setWindowYCoord(GeometricObject.translateGLYCoord2WindowYCoord(GLYCoord), false);
        }
    }
    
    public float getZCoord() {
        return this._basepoint.getZCoord();
    }
    
    public void setZCoord(float ZCoord, boolean FullUpdate) {        
        this._basepoint.setZCoord(ZCoord, false);
        
        if(FullUpdate == true) {
            this._basepoint.setGLZCoord(GeometricObject.translateZCoord2GLZCoord(ZCoord), false);
            this._basepoint.setWindowZCoord(GeometricObject.translateZCoord2WindowZCoord(ZCoord), false);
        }
    }
    
    public int getWindowZCoord() {
        return this._basepoint.getWindowZCoord();
    }
    
    public void setWindowZCoord(int WindowZCoord, boolean FullUpdate) {
        this._basepoint.setWindowZCoord(WindowZCoord, false);
        
        if(FullUpdate == true) {
            this._basepoint.setZCoord(GeometricObject.translateWindowZCoord2ZCoord(WindowZCoord), false);
            this._basepoint.setGLZCoord(GeometricObject.translateZCoord2GLZCoord(WindowZCoord), false);
        }
    }
    
    public float getGLZCoord() {
        return this._basepoint.getGLZCoord();
    }
    
    public void setGLZCoord(float GLZCoord, boolean FullUpdate) {        
        this._basepoint.setGLZCoord(GLZCoord, false);
        
        if(FullUpdate == true) {
            this._basepoint.setZCoord(GeometricObject.translateGLZCoord2ZCoord(GLZCoord), false);
            this._basepoint.setWindowYCoord(GeometricObject.translateGLZCoord2WindowZCoord(GLZCoord), false);
        }
    }

    public Point getBasepoint() {
        return this._basepoint;
    }
    
    protected final void setBasepoint(Point Basepoint) {
        this._basepoint = Basepoint;
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
    abstract void move(float DeltaX, float DeltaY, float DeltaZ);
    abstract void scale(float Factor);

    
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
    
    public static float translateXCoord2GLXCoord(float XCoord) {
        // Wir haben eine X-Weltkoordinate, multiplizieren diese mit 2 und subtrahieren davon die ---breite
        // => _coord liegt im Bereich -Fensterbreite bis +Fensterbreite
        float _coord = (XCoord * 2f) - GeometricObject.getWindowWidth();
        
        // jetzt dividieren wir _coord durch die Fensterbreite
        // => _coord liegt im Bereich -1 bis +1
        return _coord / GeometricObject.getWindowWidth();
    }
    
    public static float translateGLXCoord2XCoord(float GLXCoord) {
        // Umkehrung von GeometricObject.translateXCoord2GLXCoord()
        float _coord = GLXCoord * GeometricObject.getWindowWidth();
        
        return (_coord + GeometricObject.getWindowWidth()) * 0.5f;
    }
    
    public static int translateXCoord2WindowXCoord(float XCoord) {
        return 0;
    }
    
    public static float translateWindowXCoord2XCoord(int WindowXCoord) {
        return 0f;
    }
    
    public static float translateWindowXCoord2GLXCoord() {
        return 0f;
    }
    
    public static int translateGLXCoord2WindowXCoord(float GLXCoord) {
        return 0;
    }
    
    public static float translateYCoord2GLYCoord(float YCoord) {
        // Wir haben eine positive Y-Weltkoordinate, multiplizieren diese mit 2 und subtrahieren davon die --breite
        // => _coord liegt im Bereich -Fensterbreite bis +Fensterbreite
        float _coord = (YCoord * 2f) - GeometricObject.getWindowHeight();
        
        // jetzt dividieren wir _coord durch die Fensterbreite
        // => _coord liegt im Bereich -1 bis +1
        return _coord / GeometricObject.getWindowHeight();
    }
    
    public static float translateGLYCoord2YCoord(float GLYCoord) {
        // Umkehrung von GeometricObject.translateYCoord2GLYCoord()
        float _coord = GLYCoord * GeometricObject.getWindowHeight();
        
        return (_coord + GeometricObject.getWindowHeight()) * 0.5f;
    }
    
    public static int translateYCoord2WindowYCoord(float YCoord) {
        return 0;
    }
    
    public static float translateWindowYCoord2YCoord(int WindowYCoord) {
        return 0f;
    }
    
    public static float translateWindowYCoord2GLYCoord() {
        return 0f;
    }
    
    public static int translateGLYCoord2WindowYCoord(float GLYCoord) {
        return 0;
    }

    public static float translateZCoord2GLZCoord(float ZCoord) {
        // Wir haben eine Z-Koordinate, multiplizieren diese mit 2 und subtrahieren davon die Fensterbreite
        // => _coord liegt im Bereich -Fensterbreite bis +Fensterbreite
        float _coord = (ZCoord * 2) - GeometricObject.getWindowDepth();
        
        // jetzt dividieren wir _coord durch die Fensterbreite
        // => _coord liegt im Bereich -1 bis +1
        return _coord / GeometricObject.getWindowDepth();
    }
    
    public static float translateGLZCoord2ZCoord(float GLZCoord) {
        // Umkehrung von GeometricObject.translateZCoord2GLZCoord()
        float _coord = GLZCoord * GeometricObject.getWindowDepth();
        
        return (_coord + GeometricObject.getWindowDepth()) * 0.5f;
    }
    
    public static int translateZCoord2WindowZCoord(float ZCoord) {
        return 0;
    }
    
    public static float translateWindowZCoord2ZCoord(int WindowZCoord) {
        return 0f;
    }
    
    public static float translateWindowZCoord2GLZCoord() {
        return 0f;
    }
    
    public static int translateGLZCoord2WindowZCoord(float GLZCoord) {
        return 0;
    }
    
    
    // class methods
    @Override
    public String toString() {
        String _out = "window: " + GeometricObject.getWindowWidth() + "x" + GeometricObject.getWindowHeight() + "x" + GeometricObject.getWindowDepth() + " " + GeometricObject.getGLAspectRatio() + "\n";
        _out += this.getClass().toString() + " (" + this.getXCoord() + ", " + this.getYCoord() + ", " + this.getZCoord() + ") | " + this.getGLXCoord() + ", " + this.getGLYCoord() + ", " + this.getGLZCoord() + ") " + this.getID();                
        
        return _out;
    }
    
    public void dump() {
        System.out.println(this.toString());
    }
    
    public static void main(String[] args) {
        Vector _v1 = VectorFactory.create(1,2);
        Vector _v2 = VectorFactory.create(2,2);
        
        System.out.println(_v1.toString());
        System.out.println(_v2.toString());
        
        float _v3 = VectorFactory.getAngle(_v1, _v2);
        System.out.println(_v3);
        
    }
}