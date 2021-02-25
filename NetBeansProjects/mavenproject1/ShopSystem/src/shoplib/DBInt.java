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
public class DBInt
    extends DBValue
{

    private int value;

    public DBInt(int Value,
                 String FieldName)
    {
        this.value = Value;
        this.setFieldName(FieldName);
    }

    public int getValue()
    {
        return this.value;
    }

    public void setValue(int Value)
    {
        this.value = Value;
    }

    public String toString()
    {
        return Integer.toString(this.getValue());
    }
}
