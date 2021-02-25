/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometricobjects;

public class Point extends GeometricObject {
    // properties
    private int _x_coord;
    private int _y_coord;
    private int _z_coord;
    
    @Override
    // hiermit wird gerechnet
    public int getXCoord() {
        return this._x_coord;
    }
    
    @Override
    // hiermit wird gemalt
    public float getGLXCoord() {
        // Wir haben eine positive Pixel-X-Koordinate(<= Fensterbreite), multiplizieren diese mit 2 und subtrahieren davon die Fensterbreite
        // => _coord liegt im Bereich -Fensterbreite bis +Fensterbreite
        float _coord = (this.getXCoord() * 2) - GeometricObject.getWindowWidth();
        
        // jetzt dividieren wir _coord durch die Fensterbreite
        // => _coord liegt im Bereich -1 bis +1
        return _coord / GeometricObject.getWindowWidth();
    }
    
    @Override
    public void setXCoord(int XCoord) {
        /*if(XCoord < 0) {
            throw new IllegalArgumentException("Point: XCoord < 0");
        } else if(XCoord > GeometricObject.getWindowWidth()) {
            throw new IllegalArgumentException("Point: XCoord > " + GeometricObject.getWindowWidth());
        }*/
        
        this._x_coord = XCoord;
    }
    
    @Override
    // hiermit wird gerechnet
    public int getYCoord() {
        return this._y_coord;
    }
    
    @Override
    // hiermit wird gemalt
    public float getGLYCoord() {
        // Wir haben eine positive Pixel-Y-Koordinate(<= Fensterhöhe), multiplizieren diese mit 2 und subtrahieren davon die Fensterhöhe
        float _coord = (this.getYCoord() * 2) - GeometricObject.getWindowHeight();
        // => _coord liegt jetzt im Bereich -Fensterhöhe bis +Fensterhöhe

        // jetzt dividieren wir _coord durch die Fensterbreite und invertieren das Ergebnis, da die y-Achse "nach oben" wächst
        return -(_coord / GeometricObject.getWindowHeight());
        // => _coord liegt jetzt im Bereich -1 bis +1
    }
    
    @Override
    public void setYCoord(int YCoord) {
        /*if(YCoord < 0) {
            throw new IllegalArgumentException("Point: YCoord < 0");
        } else if(YCoord > GeometricObject.getWindowHeight()) {
            throw new IllegalArgumentException("Point: YCoord > " + GeometricObject.getWindowHeight());
        }*/
        
        this._y_coord = YCoord;
    }
    
    @Override
    // hiermit wird gerechnet
    public int getZCoord() {
        return this._z_coord;
    }
    
    @Override
    // hiermit wird gemalt
    public float getGLZCoord() {
        return this._z_coord; // / GeometricObject.getWindowDepth();
    }
    
    @Override
    public void setZCoord(int ZCoord) {
        this._z_coord = ZCoord;
    }
    
    // constructors
    public Point(int XCoord, int YCoord) {
        super();
        this.setXCoord(XCoord);
        this.setYCoord(YCoord);
        this.setZCoord(0);
    }
    
    public Point(int XCoord, int YCoord, int ZCoord) {
        super();
        this.setXCoord(XCoord);
        this.setYCoord(YCoord);
        this.setZCoord(ZCoord);
    }
}