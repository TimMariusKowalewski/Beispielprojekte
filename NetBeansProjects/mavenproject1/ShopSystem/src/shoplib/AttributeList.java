/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

import java.util.ArrayList;

/**
 *
 * @author Kowalewski
 */
public class AttributeList
{

    private ArrayList<Attribute> _attributes;

    public AttributeList()
    {
        this._attributes = new ArrayList<>();
    }

    public void addAttribute(Attribute A)
    {
        this._attributes.add(A);
    }
}
