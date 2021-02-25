/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

import java.sql.Timestamp;

/**
 *
 * @author Kowalewski
 */
public class DBTimestamp
    extends DBValue
{

    private Timestamp value;

    public DBTimestamp(Timestamp Value,
                       String FieldName)
    {
        this.value = Value;
        this.setFieldName(FieldName);
    }

    public Timestamp getValue()
    {
        return this.value;
    }

    public void setValue(Timestamp Value)
    {
        this.value = Value;
    }
    
    public String toString()
    {
        int microseconds = this.value.getNanos() / 1000;
        String value = this.getValue().toString();
        value = value.substring(0, value.length() - 3);
        value += Integer.toString(microseconds);
        System.out.println("asd: " + value);
        return value;
    }
}
