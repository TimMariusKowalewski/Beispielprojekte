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
public class DBString
    extends DBValue
{

    private String value;

    public DBString(String Value,
                    String FieldName)
    {
        this.value = Value;
        this.setFieldName(FieldName);
    }

    public String getValue()
    {
        return this.value;
    }

    public void setValue(String Value)
    {
        this.value = Value;
    }
    
    public String toString() {
        return this.getValue();
    }

}
