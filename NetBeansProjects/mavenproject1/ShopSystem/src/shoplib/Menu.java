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
public class Menu
{

    private ArrayList<MenuItem> _items;
    private String _name;

    public ArrayList<MenuItem> getItems()
    {
        return this._items;
    }

    public void setItems(ArrayList<MenuItem> Items)
    {
        this._items = Items;
    }

    public String getName()
    {
        return this._name;
    }

    public void setName(String Name)
    {
        this._name = Name;
    }

}
