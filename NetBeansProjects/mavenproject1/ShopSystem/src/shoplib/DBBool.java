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
public class DBBool
    extends DBValue
{

    private boolean value;

    public DBBool(boolean Value,
                  String FieldName)
    {
        this.value = Value;
        this.setFieldName(FieldName);
    }

    public boolean getValue()
    {
        return this.value;
    }

    public void setValue(boolean Value)
    {
        this.value = Value;
    }

    public String toString()
    {
        return Boolean.toString(this.getValue());
    }
}
