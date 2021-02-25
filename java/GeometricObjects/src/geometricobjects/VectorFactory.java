/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometricobjects;

import static java.lang.Float.isNaN;
import static java.lang.Math.acos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 *
 * @author Kowalewski
 */
public abstract class VectorFactory {
    public static Vector create(float... Coordinates) {
        return new Vector(Coordinates);
    }
    
    public static Vector add(Vector V1, Vector V2) {
        if(V1.getDimensions() != V2.getDimensions()) {
            throw new IllegalArgumentException("VectorFactory.add(Vector V1, Vector V2): dimensions mismatch");
        }
        
        Vector _ret = new Vector(V1.getCoordinates());
        for(int i = 1; i <= V1.getDimensions(); i++) {
            _ret.setCoord(i, _ret.getCoord(i) + V2.getCoord(i));
        }
        
        return _ret;
    }
    
    public static Vector substract(Vector V1, Vector V2) {
        if(V1.getDimensions() != V2.getDimensions()) {
            throw new IllegalArgumentException("VectorFactory.add(Vector V1, Vector V2): dimensions mismatch");
        }
        
        Vector _ret = new Vector(V1.getCoordinates());
        for(int i = 1; i <= V1.getDimensions(); i++) {
            _ret.setCoord(i, _ret.getCoord(i) - V2.getCoord(i));
        }
        
        return _ret;
    }
    
    // Multiplikation mit einem Skalar
    public static Vector multiply(Vector V, float Scalar) {  
        Vector _ret = new Vector(V.getCoordinates());
        for(int i = 1; i <= V.getDimensions(); i++) {
            _ret.setCoord(i, Scalar * _ret.getCoord(i));
        }
        
        return _ret;
    }
    
    public static float scalarProduct(Vector V1, Vector V2) {
        if(V1.getDimensions() != V2.getDimensions()) {
            throw new IllegalArgumentException("VectorFactory.add(Vector V1, Vector V2): dimensions mismatch");
        }
        
        float _ret = 0f;
        for(int i = 1; i <= V1.getDimensions(); i++) {
            _ret += V1.getCoord(i) * V2.getCoord(i);
        }
        
        return _ret;        
    }
    
    public static float getLength(Vector V) {
        
        float _ret = 0f;
        for(int i = 1; i <= V.getDimensions(); i++) {
            _ret += pow(V.getCoord(i), 2);
        }
        
        return (float)sqrt(_ret);         
        
    }
    
    public static float getAngle(Vector V1, Vector V2) {
        float _ret = (float)acos(VectorFactory.scalarProduct(V1, V2) / (VectorFactory.getLength(V1) * VectorFactory.getLength(V2)));       
        if(isNaN(_ret) == true) {
            _ret = 0f;
        }
   
        return _ret;       
    }
    
    // returns a vector with the same length and a certain angle
    public static Vector setAngle(Vector V1, float Angle) {
        //return (float)acos(VectorFactory.scalarProduct(V1, V2) / (VectorFactory.getLength(V1) * VectorFactory.getLength(V2)));       
        return new Vector();
    }
    
    // norms a vector to a certain length
    public static Vector setLength(Vector V1, float Length) {
        //return (float)acos(VectorFactory.scalarProduct(V1, V2) / (VectorFactory.getLength(V1) * VectorFactory.getLength(V2)));       
        return new Vector();
    }
    
    public static Vector transform(Vector V, float... Scalar) {  
        Vector _ret = new Vector(V.getCoordinates());
        for(int i = 1; i <= V.getDimensions(); i++) {
            _ret.setCoord(i, Scalar[i-1] * _ret.getCoord(i));
        }
        
        return _ret;
    }
}