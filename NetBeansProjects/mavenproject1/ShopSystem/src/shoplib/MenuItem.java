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
public class MenuItem
{
    private String _name;
    private char _key;
    private String _target;
    private boolean _is_submenu;
    private ArrayList<MenuItem> _items;
}
