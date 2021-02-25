/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

/**
 *
 * @author Kowalewski
 */
public class DBFloat
    extends DBValue
{

    private float value;

    public DBFloat(float Value,
                   String FieldName)
    {
        this.value = Value;
        this.setFieldName(FieldName);
    }

    public float getValue()
    {
        return this.value;
    }

    public void setValue(float Value)
    {
        this.value = Value;
    }

    public String toString()
    {
        return Float.toString(this.getValue());
    }
}
