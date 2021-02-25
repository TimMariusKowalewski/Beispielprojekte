/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kundenzufriedenheitsumfrage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Kowalewski
 */
public class Fragenkatalog {
    private List<Frage> _fragen;
 
    public List getFragen() {
        return this._fragen;
    }
    
    private void initFragen() {
        this._fragen = new ArrayList<>();
    }
    
    private void FragenEinlesen(String Pfad) {
        File _folder = new File(Pfad);
        File[] _listOfFiles = _folder.listFiles();
        Arrays.sort(_listOfFiles);

        for (File _file : _listOfFiles) {
            
            if (_file.isFile()) {
                Frage _f = new Frage(Pfad + "\\" + _file.getName(), true);
                this.getFragen().add(_f);
            }
        }

        Frage[] _frage_test = new Frage[100]; 
        int i = 0;
        for(Object _f: this.getFragen()) {
            Frage _f2 = (Frage)_f;
            
            if(_f2.getSubAntworten() != null) {
                //System.out.println("asd" + _f2.getText());
                String[] _splits = _f2.getSubAntworten().split(",");
                
                for(String _s: _splits) {
                    Frage _foo = new Frage(_f2.getFileName(), false);
                    _foo.setParent(_f2);
                    _foo.setID(_f2.getID() + i);
                    _foo.setText(_f2.getText() + " " + _s);
                    _foo.setFrageTyp(_f2.getFrageTyp());
                    _foo.setAntwortMin(_f2.getAntwortMin());
                    _foo.setAntwortMax(_f2.getAntwortMax());
                    _foo.setAntwort(_f2.getAntwort());
                    _foo.setAntwortType(_f2.getAntwortType());
                    _frage_test[i] = _foo;
                    _f2.getChildren().add(_foo);
                    
                    i++;
                }
            }
            
        }
        
        for(Frage _fn: _frage_test) {
            this.getFragen().add(_fn);
        }
    }
    
    public void FragenAnzeigen() {
        for(Object _frage: this.getFragen()) {
            if(_frage != null) {
                Frage _frage2 = (Frage)_frage;
                System.out.println("---");
                System.out.println(_frage2.toString());
            }
        }
    }
    
    public Fragenkatalog(String Pfad) {
        this.initFragen();
        this.FragenEinlesen(Pfad);
    }
}
