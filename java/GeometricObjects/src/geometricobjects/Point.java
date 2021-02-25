/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometricobjects;

public class Point extends GeometricObject {
    // properties
    private float _x_coord;
    private int _window_x_coord;
    private float _gl_x_coord;
    private float _y_coord;
    private int _window_y_coord;
    private float _gl_y_coord;
    private float _z_coord;
    private int _window_z_coord;
    private float _gl_z_coord;
    
    @Override
    public float getXCoord() {
        return this._x_coord;
    }
    
    @Override
    public void setXCoord(float XCoord, boolean FullUpdate) {      
        this._x_coord = XCoord;
        
        if(FullUpdate == true) {
            this.setGLXCoord(GeometricObject.translateXCoord2GLXCoord(XCoord), false);
            this.setWindowXCoord(GeometricObject.translateXCoord2WindowXCoord(XCoord), false);
        }
    }
    
    @Override
    public int getWindowXCoord() {
        return this._window_x_coord;
    }
    
    @Override
    public void setWindowXCoord(int WindowXCoord, boolean FullUpdate) {
        this._window_x_coord = WindowXCoord;
        
        if(FullUpdate == true) {
            this.setXCoord(GeometricObject.translateWindowXCoord2XCoord(WindowXCoord), false);
            this.setGLXCoord(GeometricObject.translateXCoord2GLXCoord(WindowXCoord), false);
        }
    }
    
    @Override
    public float getGLXCoord() {
        return this._gl_x_coord;
    }
    
    @Override
    public void setGLXCoord(float GLXCoord, boolean FullUpdate) {
        this._gl_x_coord = GLXCoord;
        
        if(FullUpdate == true) {
            this.setXCoord(GeometricObject.translateGLXCoord2XCoord(GLXCoord), false);
            this.setWindowXCoord(GeometricObject.translateGLXCoord2WindowXCoord(GLXCoord), false);
        }
        
    }
    
    @Override
    public float getYCoord() {
        return this._y_coord;
    }
    
    @Override
    public void setYCoord(float YCoord, boolean FullUpdate) {        
        this._y_coord = YCoord;
        
        if(FullUpdate == true) {
            this.setGLYCoord(GeometricObject.translateYCoord2GLYCoord(YCoord), false);
            this.setWindowYCoord(GeometricObject.translateYCoord2WindowYCoord(YCoord), false);
        }
    }
    
    @Override
    public int getWindowYCoord() {
        return this._window_y_coord;
    }
    
    @Override
    public void setWindowYCoord(int WindowYCoord, boolean FullUpdate) {
        this._window_y_coord = WindowYCoord;
        
        if(FullUpdate == true) {
            this.setYCoord(GeometricObject.translateWindowYCoord2YCoord(WindowYCoord), false);
            this.setGLYCoord(GeometricObject.translateYCoord2GLYCoord(WindowYCoord), false);
        }
    }
    
    @Override
    public float getGLYCoord() {
        return this._gl_y_coord;
    }
    
    @Override
    public void setGLYCoord(float GLYCoord, boolean FullUpdate) {        
        this._gl_y_coord = GLYCoord;
        
        if(FullUpdate == true) {
            this.setYCoord(GeometricObject.translateGLYCoord2YCoord(GLYCoord), false);
            this.setWindowYCoord(GeometricObject.translateGLYCoord2WindowYCoord(GLYCoord), false);
        }
    }
    
    @Override
    public float getZCoord() {
        return this._z_coord;
    }
    
    @Override
    public void setZCoord(float ZCoord, boolean FullUpdate) {        
        this._z_coord = ZCoord;
        
        if(FullUpdate == true) {
            this.setGLZCoord(GeometricObject.translateZCoord2GLZCoord(ZCoord), false);
            this.setWindowZCoord(GeometricObject.translateZCoord2WindowZCoord(ZCoord), false);
        }
    }
    
    @Override
    public int getWindowZCoord() {
        return this._window_z_coord;
    }
    
    @Override
    public void setWindowZCoord(int WindowZCoord, boolean FullUpdate) {
        this._window_z_coord = WindowZCoord;
        
        if(FullUpdate == true) {
            this.setZCoord(GeometricObject.translateWindowZCoord2ZCoord(WindowZCoord), false);
            this.setGLZCoord(GeometricObject.translateZCoord2GLZCoord(WindowZCoord), false);
        }
    }
    
    @Override
    public float getGLZCoord() {
        return this._gl_z_coord;
    }
    
    @Override
    public void setGLZCoord(float GLZCoord, boolean FullUpdate) {        
        this._gl_z_coord = GLZCoord;
        
        if(FullUpdate == true) {
            this.setZCoord(GeometricObject.translateGLZCoord2ZCoord(GLZCoord), false);
            this.setWindowYCoord(GeometricObject.translateGLZCoord2WindowZCoord(GLZCoord), false);
        }   
    }
    
    // constructors
    public Point(float XCoord, float YCoord) {
        super();
        this.setXCoord(XCoord, true);
        this.setYCoord(YCoord, true);
        this.setZCoord(0, true);
    }
    
    public Point(float XCoord, float YCoord, float ZCoord) {
        super();
        this.setXCoord(XCoord, true);
        this.setYCoord(YCoord, true);
        this.setZCoord(ZCoord, true);
    }
    
    @Override
    public void move(float DeltaX, float DeltaY, float DeltaZ) {
        
        
    }
    
    @Override
    public void scale(float Factor) {
        
    }
}