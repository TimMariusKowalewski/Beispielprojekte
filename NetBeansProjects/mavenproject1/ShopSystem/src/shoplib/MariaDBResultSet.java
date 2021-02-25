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
public class MariaDBResultSet
{

    private ArrayList<DBField<String, Object, Object, String>> current_record;
    private ArrayList<ArrayList<DBField<String, Object, Object, String>>> data;

    public MariaDBResultSet()
    {
        this.data = new ArrayList<>(10);
    }

    public void addDBTriplet(DBField<String, Object, Object, String> Field)
    {

    }

    public void finishRecord()
    {
        this.data.add(current_record);
        this.current_record = new ArrayList<>(10);
    }

    public ArrayList<ArrayList<DBField<String, Object, Object, String>>> getData()
    {
        return this.data;
    }
}
