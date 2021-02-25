/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

/**
 *
 * @author Kowalewski
 */
public class Vector {

    // Instanzvariablen
    final private int _dimensions;
    private float _data[];

    // Konstruktoren
    public Vector(int Dimensions) {
        if (Dimensions > 0) {
            this._dimensions = Dimensions;
            this._data = new float[Dimensions];
        } else {
            this._dimensions = 1;
            this._data = new float[1];
        }
    }

    public Vector(int Dimensions, float[] Data) {
        this(Dimensions);
        this.setData(Data);
    }

    // Eigenschaften
    final public int getDimensions() {
        return this._dimensions;
    }

    final public float[] getData() {
        return this._data;
    }

    final public void setData(float[] Data) {

    }

    // Klassenmethoden
    public static Vector add(Vector Vector1, Vector Vector2) {
        float[] _vector1_data = Vector1.getData();
        float[] _vector2_data = Vector2.getData();
        float[] _vector3_data;

        if (Vector1.getDimensions() == Vector2.getDimensions()) {
            _vector3_data = new float[Vector1.getDimensions()];
            for (int i = 0; i < Vector1.getDimensions(); i++) {
                _vector3_data[i] = _vector1_data[i] + _vector2_data[i];
            }
            return new Vector(Vector1.getDimensions(), _vector3_data);
        } else {
            throw new Error("Vector: ungleiche Dimensionen!");
        }
    }

}
