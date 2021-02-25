/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kundenzufriedenheitsumfrage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Kowalewski
 */
public class Frage {
    // properties
    private String _id;
    private Frage _parent;
    private List<Frage> _children;
    private String _text;
    private String _filename;
    private int _frage_typ;
    private String _antwort;
    private String _antwort_type;
    private String _antwort_min;
    private String _antwort_max;
    private String _sub_antworten;
 
    public String getID() {
        return this._id;
    }
    
    public void setID(String ID) {
        this._id = ID;
    }
    
    public Frage getParent() {
        return this._parent;
    }
    
    public void setParent(Frage F) {
        this._parent = F;
    }
    
    public List getChildren() {
        return this._children;
    }
    
    private void setChildren() {
        this._children = new ArrayList();
    }
    
    public String getText() {
        return this._text;
    }
    
    public void setText(String Text) {
        this._text = Text;
    }
    
    public String getFileName() {
        return this._filename;
    }
    
    private void setFilename(String FileName) {
        this._filename = FileName;
    }
    
    public int getFrageTyp() {
        return this._frage_typ;
    }
    
    public void setFrageTyp(int FrageTyp) {
        this._frage_typ = FrageTyp;
    }
    
    public String getAntwort() {
        return this._antwort;
    }
    
    public void setAntwort(String Antwort) {
        this._antwort = Antwort;
    }
    
    public String getAntwortType() {
        return this._antwort_type;
    }
    
    public void setAntwortType(String AntwortType) {
        this._antwort_type = AntwortType;
    }
    
    public String getAntwortMin() {
        return this._antwort_min;
    }
    
    public void setAntwortMin(String Min) {
        this._antwort_min = Min;
    }
    
    public String getAntwortMax() {
        return this._antwort_max;
    }
    
    public void setAntwortMax(String Max) {
        this._antwort_max = Max;
    }
    
    public String getSubAntworten() {
        return this._sub_antworten;
    }
    
    public void setSubAntworten(String SubAntworten) {
        this._sub_antworten = SubAntworten;
    }
    
    
    // constructors
        public Frage(String Datei, boolean DoParse) {
        this.setFilename(Datei);
        
        if(DoParse == true) {
            try {
                this.parseFile();
                this.setChildren();
            } catch (Exception e) {
            
            }
        }
    }
    
    public Frage() {
        
    }

    
    // overrides
    @Override
    public String toString() {
        String _out = "";
        
        _out += this.getID() + "\n";
        _out += this.getFileName() + "\n";
        _out += this.getText() + "\n";
        _out += this.getFrageTyp() + "\n";
        _out += this.getAntwort() + "\n";
        _out += this.getAntwortType() + "\n";
        _out += this.getAntwortMin() + "\n";
        _out += this.getAntwortMax() + "\n";
        _out += this.getSubAntworten() + "\n";
        _out += "\n";
        
        return _out;
    }
    
    
    // class methods
    private void parseFile() throws FileNotFoundException, IOException {
        Scanner _sc = new Scanner(new File(this.getFileName()));
        String _expr;
        String _line;
        while(_sc.hasNextLine()){
            _line = _sc.nextLine();
            _line = _line.trim();
            //System.out.println(_line);
            
            _expr = "ID:";
            if(_line.startsWith(_expr) == true) {
                String _str_value = _line.replace(_expr, "");
                this.setID(_str_value);
                //int _int_value = Integer.parseInt(_str_value);
                //this.setID(_int_value);
            }
            
            _expr = "FRAGE_TYP:";
            if(_line.startsWith(_expr) == true) {
                String _str_value = _line.replace(_expr, "");
                int _int_value = Integer.parseInt(_str_value);
                this.setFrageTyp(_int_value);
            }
            
             _expr = "FRAGE:";
            if(_line.startsWith(_expr) == true) {
                String _str_value = _line.replace(_expr, "");
                this.setText(_str_value);
            }
            
            _expr = "ANTWORT_SKALA:";
            if(_line.startsWith(_expr) == true) {
                String _str_value = _line.replace(_expr, "");
                this.setAntwort(_str_value);
                
                String[] _splits = _str_value.split("-");
                this.setAntwortMin(_splits[0]);
                this.setAntwortMax(_splits[1]);
            }
            
            _expr = "ANTWORT_TYPE:";
            if(_line.startsWith(_expr) == true) {
                String _str_value = _line.replace(_expr, "");
                this.setAntwortType(_str_value);
            }
            
            _expr = "SUB_ANTWORTEN:";
            if(_line.startsWith(_expr) == true) {
                String _str_value = _line.replace(_expr, "");
                
                this.setSubAntworten(_str_value);
            
                //String[] _splits = _str_value.split(",");
                // handle parents
                //for(String _s: _splits) {
                //    System.out.println("asd" + _s);
                //}
            }
            
        }
    }
        
    public String format() {
        String _out = "";
        
        _out += this.getText() + "\n";
        _out = _out.replace("<i>", "");
        _out = _out.replace("</i>", "");
        _out = _out.replace("<b>", "");
        _out = _out.replace("</b>", "");
        
        //_out += this.getFrageTyp() + "\n";
        //_out += this.getAntwortMin() + "\n";
        //_out += this.getAntwortMax() + "\n";        

        if(this.getFrageTyp() == 1) {
            //_out += this.getSubAntworten() + "\n";
            _out += "\n" + "Antwortmöglichkeiten:" + "\n";
            _out += this.getAntwortMin() + " stimme nicht zu" + "\n";
            _out += this.getAntwortMax() + " stimme voll zu" + "\n";
        } else if(this.getFrageTyp() == 2) {
            _out += this.getSubAntworten() + "\n";
        } else if(this.getFrageTyp() == 3) {
            //_out += this.getSubAntworten() + "\n";
        }
        
        return _out;
    }
}