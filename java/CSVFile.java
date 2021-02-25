/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kowalewski
 */
public class CSVFile {
    private String _filename;
    private String _field_separator;
    private CSVData _csv_data;
    
    public String getFileName() {
        return this._filename;
    }
    
    public void setFileName(String FileName) {
        this._filename = FileName;
    }
    
    public final String getFieldSeparator() {
        return this._field_separator;
    }
    
    public final void setFieldSeparator(String FieldSeparator) {
        this._field_separator = FieldSeparator;
    }
    
    public CSVData getCSVData() {
        return this._csv_data;
    }
    
    private void setCSVData(CSVData CSVData) {
        this._csv_data = CSVData;
    }
    
    public int getCountRows() {
        return this.getCSVData().getCountRows();
    }
    
    // setze auf Wert CountRows
    public void setCountRows(int CountRows) {
        this.getCSVData().setCountRows(CountRows);
    }
    
    // +1
    public void setCountRows() {
        this.getCSVData().setCountRows();
    }
    
    public int getCountFields() {
        return this.getCSVData().getCountFields();
    }
    
    public void setCountFields(int CountFields) {
        this.getCSVData().setCountFields(CountFields);
    }
    
    public void setCountFields() {
        this.getCSVData().setCountFields();
    }
    
    // asd
    public String getCell(int Row, int Column) {
        return this.getCSVData().getCell(Row, Column);
    }
    
    public void setCell(int Row, int Column, String Value) {
        this.getCSVData().setCell(Row, Column, Value);
    }
    
    // zum Lesen
    public CSVFile(String FileName, String FieldSeparator) {
        this.setFileName(FileName);
        this.setFieldSeparator(FieldSeparator);
        this.setCSVData(new CSVData());
        this.setCountRows(0);
        this.setCountFields(0);
        this.readFile();  
    }
    
    // zum Schreiben
    public CSVFile(CSVData CSVData, int CountRows, int CountFields, String FileName, String FieldSeparator) {
        this.setFileName(FileName);
        this.setFieldSeparator(FieldSeparator);
        this.setCSVData(CSVData);
        this.setCountRows(CountRows);
        this.setCountFields(CountFields);
        this.writeFile();
    }
    
    public void readFile() {
        BufferedReader _br = null;
        String _line = "";
        
        try {
            _br = new BufferedReader(new FileReader(this.getFileName()));
            
            int _count_rows = 0;
            int _count_fields = 0;
            while ((_line = _br.readLine()) != null) {
                String[] _fields = _line.split(this.getFieldSeparator());
                
                // header zeile
                if(_count_rows == 0) {
                    _count_fields = 0;
                    for(String _s: _fields) {
                        //System.out.println(_s + " index: (" + this.getCountRows() + "," + this.getCountFields() + ")");
                        this.setCell(_count_rows, _count_fields, _s);
                        _count_fields++;
                    }
                    this.setCountFields(_count_fields);
                // datenzeile
                } else {
                    _count_fields = 0;
                    for(String _s: _fields) {
                        //System.out.println(_s + " index: (" + this.getCountRows() + "," + this.getCountFields() + ")");
                        this.setCell(_count_rows, _count_fields, _s);
                        _count_fields++;
                    }
                }
                
                _count_rows++;
            }
            this.setCountRows(_count_rows);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (_br != null) {
                try {
                    _br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void writeFile() {
        FileWriter _csvWriter = null;
        
        try {
            _csvWriter = new FileWriter(this.getFileName());
        } catch (IOException ex) {
            Logger.getLogger(CSVFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String[] _parts = new String[this.getCSVData().getCountFields()];
        for(int i = 0; i < this.getCSVData().getCountRows(); i++) {
            for(int j = 0; j < this.getCSVData().getCountFields(); j++) {
                _parts[j] = this.getCSVData().getCell(i, j);
                //System.out.println(_parts[j]);
            }
            
            try {
                _csvWriter.append(explode(_parts) + "\n");
            } catch (IOException ex) {
                Logger.getLogger(CSVFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            _parts = new String[this.getCSVData().getCountFields()];
        }
        
        try {
            _csvWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(CSVFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            _csvWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(CSVFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
	public String toString() {
        String _out;
        
        _out = this.getFileName() + " (" + this.getCountRows() + " x " + this.getCountFields() + ")\n";
        for(int _row = 0; _row < this.getCountRows(); _row++) {
            
            String[] _output_parts = new String[this.getCountFields()];
            for(int _column = 0; _column < this.getCountFields(); _column++) {
                _output_parts[_column] = this.getCell(_row, _column);
            }
            _out += this.explode(_output_parts) + "\n";
        }
        
        return _out;
    }
    
    public String explode(String[] Parts) {
        int _part_count = 0;
        String _ret = "";
     
        for(String _s: Parts) {
            if(_part_count == 0) {
                _ret += _s;
            } else {
                _ret += this.getFieldSeparator() + _s;
            }
            
            _part_count++;
        }
        
        return _ret;
    }
}