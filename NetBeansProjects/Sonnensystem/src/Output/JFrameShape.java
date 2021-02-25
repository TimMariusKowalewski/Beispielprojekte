/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Output;

import java.awt.Color;

/**
 *
 * @author Kowalewski
 */
public class JFrameShape {

    private int _x_coord;
    private int _y_coord;
    private float _radius;
    private Color _color;
    private String _name;
    private ShapeType _shape_type;
    
    public enum ShapeType {
        CIRCLE,
        FILLED_CIRCLE
    }

    public JFrameShape(int XCoord, int YCoord, int Radius, String Name, Color Color, ShapeType ShapeType) {
        this._x_coord = XCoord;
        this._y_coord = YCoord;
        this._radius = Radius;
        this._color = Color;
        this._name = Name;
        this._shape_type = ShapeType;
    }

    public int getXCoord() {
        return _x_coord;
    }

    public void setXCoord(int XCoord) {
        this._x_coord = XCoord;
    }

    public int getYCoord() {
        return _y_coord;
    }

    public void setYCoord(int YCoord) {
        this._y_coord = YCoord;
    }

    public float getRadius() {
        return _radius;
    }

    public void setRadius(int Radius) {
        this._radius = Radius;
    }

    public Color getColor() {
        return _color;
    }

    public void setColor(Color Color) {
        this._color = Color;
    }

    public String getName() {
        return _name;
    }

    public void setName(String Name) {
        this._name = Name;
    }

    public ShapeType getShapeType() {
        return _shape_type;
    }

    public void setShapeType(ShapeType ShapeType) {
        this._shape_type = ShapeType;
    }

}
