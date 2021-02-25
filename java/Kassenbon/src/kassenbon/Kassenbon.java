/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kassenbon;

/**
 *
 * @author Kowalewski
 */
public class Kassenbon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Kassenbon.run_normal();
        Kassenbon.run_auto();
    }
    
    public static void run_normal() {
        String _ware1 = "Bananen";
        String _ware2 = "Äpfel";
        String _ware3 = "Birnen";
        String _ware4 = "Orangen";
        
        float _ware1_preis = 1.3f;
        float _ware2_preis = 2.9f;
        float _ware3_preis = 0.3f;
        float _ware4_preis = 0.01f;
        
        int _ware1_anzahl = 1;
        int _ware2_anzahl = 2;
        int _ware3_anzahl = 3;
        int _ware4_anzahl = 2;
        
        float _inhalt_brieftasche = 9.5f;
        
        float _gesamtpreis = 0;
        _gesamtpreis += _ware1_preis * _ware1_anzahl;
        _gesamtpreis += _ware2_preis * _ware2_anzahl;
        _gesamtpreis += _ware3_preis * _ware3_anzahl;
        _gesamtpreis += _ware4_preis * _ware4_anzahl;
        
        if(_gesamtpreis < _inhalt_brieftasche) {
            String _out = "Dein Kassenbon:\n";
            
            _out += _ware1 + "  " + _ware1_anzahl + " x " + String.format("%.2f", _ware1_preis) + " €\n";
            _out += "               " + String.format("%.2f", _ware1_anzahl * _ware1_preis) + " €\n";
            _out += _ware2 + "  " + _ware2_anzahl + " x " + String.format("%.2f", _ware2_preis) + " €\n"; 
            _out += "               " + String.format("%.2f", _ware2_anzahl * _ware2_preis) + " €\n";
            _out += _ware3 + "  " + _ware3_anzahl + " x " + String.format("%.2f", _ware3_preis) + " €\n"; 
            _out += "               " + String.format("%.2f", _ware3_anzahl * _ware3_preis) + " €\n";
            _out += _ware4 + "  " + _ware4_anzahl + " x " + String.format("%.2f", _ware4_preis) + " €\n"; 
            _out += "               " + String.format("%.2f", _ware4_anzahl * _ware4_preis) + " €\n";
            _out += "\n-----------------------\n";
            /*float _sum = _ware1_anzahl * _ware1_preis;
            _sum += _ware2_anzahl * _ware2_preis;
            _sum += _ware3_anzahl * _ware3_preis;
            _sum += _ware4_anzahl * _ware4_preis;*/
            _out += "Gesamt: " + String.format("%.2f", _gesamtpreis) + " €\n";
            _out += "Gegegeben: " + String.format("%.2f", _inhalt_brieftasche) + " €\n";
            _out += "\n";
            _out += "Zurück: " + String.format("%.2f", _gesamtpreis - _inhalt_brieftasche) + " €\n";
            
            System.out.print(_out);
        } else {
            String _out = "Gesamtpreis von " + String.format("%.2f", _gesamtpreis) + " € ist größer als deine Brieftasche mit " + String.format("%.2f", _inhalt_brieftasche)  + " €!\n";
            System.out.print(_out);
        }
        
    }
    
    public static void run_auto() {
        String[] _waren = new String[20];
        _waren[0] = "Bananen";
        _waren[1] = "Äpfel";
        _waren[2] = "Birnen";
        _waren[3] = "Orangen";
        
        float[] _waren_preise = new float[20];
        _waren_preise[0] = 1.3f;
        _waren_preise[1] = 2.9f;
        _waren_preise[2] = 0.3f;
        _waren_preise[3] = 0.01f;
        
        int[] _waren_anzahlen = new int[20];
        _waren_anzahlen[0] = 1;
        _waren_anzahlen[1] = 10;
        _waren_anzahlen[2] = 20;
        _waren_anzahlen[3] = 100;
        
        float _inhalt_brieftasche = 9.5f;
        
        int[] _waren_einkauf = new int[20];
        
        // hier wird geprüft, welche der gewünschten Produkte man sich leisten kann, alle anderen werden ignoriert
        float _gesamtpreis = 0;
        for(int i = 0; i < _waren.length; i++) {
            float _preis = _waren_preise[i] * _waren_anzahlen[i];
            
            float _zwischensumme = _gesamtpreis + _preis;
            if(_zwischensumme <= _inhalt_brieftasche) {
                _waren_einkauf[i] = i;
                _gesamtpreis = _zwischensumme;
            } else {
                for(int k = i; k < _waren_einkauf.length; k++) {
                    _waren_einkauf[k] = 21;
                }
                break;
            }
        }
        

        // Ausgabe Kassenbon
        String _out = "Dein Kassenbon:\n";
        float _sum = 0;
        for(int j = 0; j < _waren_einkauf.length; j++) {
            if(_waren_einkauf[j] < 21) {
                //System.out.println(_waren[j] + "\n");
           
                _out += _waren[j] + "  " + _waren_anzahlen[j] + " x " + String.format("%.2f", _waren_preise[j]) + " €\n";
                _out += "               " + String.format("%.2f", _waren_anzahlen[j] * _waren_preise[j]) + " €\n";
                
                _sum += _waren_anzahlen[j] * _waren_preise[j];
            }
        }
        
        _out += "\n-----------------------\n";
        _out += "Gesamt: " + String.format("%.2f", _sum) + " €\n";
        _out += "Gegegeben: " + String.format("%.2f", _inhalt_brieftasche) + " €\n";
        _out += "\n";
        _out += "Zurück: " + String.format("%.2f", _sum - _inhalt_brieftasche) + " €\n";
        System.out.print(_out);
    }
}
