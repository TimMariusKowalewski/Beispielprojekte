/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

import java.util.UUID;

/**
 *
 * @author Kowalewski
 */
public class DBUUID
    extends DBValue
{
    
    private UUID value;
    
    public DBUUID(UUID Value,
                  String FieldName)
    {
        this.value = Value;
        this.setFieldName(FieldName);
    }
    
    public UUID getValue()
    {
        return this.value;
    }
    
    public void setValue(UUID Value)
    {
        this.value = Value;
    }
    
    @Override
        public String toString()
    {
        return this.getValue().toString();
    }
    
}
