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
public class Vector {
    // properties
    float[] _coordinates;
    int _dimensions;

    public int getDimensions() {
        return this._dimensions;
    }

    private void setDimensions(int Dimensions) {
        this._dimensions = Dimensions; 
    }
    
    public float[] getCoordinates() {
        return this._coordinates;
    }

    private void setCoordinates(float... Coordinates) {
        this._coordinates = Coordinates; 
    }

    
    // constructors
    public Vector(float... Coordinates) {
        this.setCoordinates(Coordinates);
        this.setDimensions(Coordinates.length);
    }
    
    
    // class methods
    public float getCoord(int Dimension) {
        if(Dimension < 1) {
            throw new IllegalArgumentException("Vector.getCoord(int Dimension): Dimension < 1");
        } else if(Dimension > this.getDimensions()) {
            throw new IllegalArgumentException("Vector.getCoord(int Dimension): Dimension > this.getDimensions()");
        }
        
        return this._coordinates[Dimension - 1];
    }
    
    public void setCoord(int Dimension, float Coord) {
        if(Dimension < 1) {
            throw new IllegalArgumentException("Vector.setCoord(int Dimension, float Coord): Dimension < 1");
        } else if(Dimension > this.getDimensions()) {
            throw new IllegalArgumentException("Vector.setCoord(int Dimension, float Coord): Dimension > this.getDimensions()");
        }
        
        this._coordinates[Dimension - 1] = Coord;
    }
    
    public float getCoord(String CoordName) {
        switch(CoordName) {
            case "X":
                return this._coordinates[0];
            case "Y":
                return this._coordinates[1];
            case "Z":
                return this._coordinates[2];
            default:
                throw new IllegalArgumentException("Vector.getCoord(String CoordName): unknown CoordName");
        }
    }

    public void setCoord(String CoordName, float Coord) {
        switch(CoordName) {
            case "X":
                this._coordinates[0] = Coord;
            case "Y":
                this._coordinates[1] = Coord;
            case "Z":
                this._coordinates[2] = Coord;
            default:
                throw new IllegalArgumentException("Vector.setCoord(String CoordName, float Coord): unknown CoordName");
        }
    }
    
    @Override
    public String toString() {
        String _out = "(";

        for(int i = 1; i <= this.getDimensions(); i++) {
            if(i != 1) {
                _out += ", ";
            }
            _out += this.getCoord(i);
        }
        
        return _out + ")";
    }
}