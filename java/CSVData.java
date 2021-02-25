package csv;

import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;
import javafx.util.Pair;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kowalewski
 */
public class CSVData {
    private int _count_rows;
    private int _count_fields;
    private Map<Pair<Integer, Integer>, String> _string_data; // 2d-array beliebiger Größe mit Strings    

    public int getCountRows() {
        return this._count_rows;
    }
    
    public void setCountRows(int CountRows) {
        this._count_rows = CountRows;
    }
    
    public void setCountRows() {
        this._count_rows++;
    }
    
    public int getCountFields() {
        return this._count_fields;
    }
    
    public void setCountFields(int CountFields) {
        this._count_fields = CountFields;
    }
    
    public void setCountFields() {
        this._count_fields++;
    }
    
    public Map<Pair<Integer, Integer>, String> getData() {
        return this._string_data;
    }    
    
    private void _initData() {
        this._string_data = new Hashtable<>();
    }
    
    public CSVData() {
        this._initData();
    }
    
    public String getCell(int Row, int Column) {
        //System.out.println("Get " + Row +"|" + Column);
        //System.out.println("Get2 " + Row2 +"|" + Column2);
        Pair<Integer, Integer> _pair = new Pair<>(Row, Column);
        return this.getData().get(_pair);
    }
    
    public void setCell(int Row, int Column, String Value) {
        //System.out.println("Set " + Row +"|"+Column);
        Pair<Integer, Integer> _pair = new Pair<>(Row, Column);
        this.getData().put(_pair, Value);
    }

    public String[] getCellArray(String ArrayAxis, int ArrayIndex) {
        String[] _fields = null;
        
        // ich möchte eine Zeile und zwar die ArrayIndex-te
        if(ArrayAxis == "X") {
            _fields = new String[this.getCountFields()];
            for(int i = 0; i < this.getCountFields(); i++) {
                _fields[i] = this.getCell(ArrayIndex, i);
            }
        // ich möchte eine Spalte und zwar die ArrayIndex-te
        } else if(ArrayAxis == "Y") {
            _fields = new String[this.getCountRows()];
            for(int i = 0; i < this.getCountRows(); i++) {
                _fields[i] = this.getCell(i, ArrayIndex);
            }            
        }
        
        return _fields;
    }

    public String[] getRow(int RowID) {
        return this.getCellArray("X", RowID);
    }

    public String[] getColumn(int ColumnID) {
        return this.getCellArray("Y", ColumnID);
    }
    
    public String[] getByID(String ID) {
        for(int j = 0; j < this.getCountRows(); j++) {
            if(this.getCell(j, 0).equals(ID) == true) {
                return this.getCellArray("X", j);
            }
        }
        
        // nicht gefunden, null zurückgeben
        return null;
    }
}