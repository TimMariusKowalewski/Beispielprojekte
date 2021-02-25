/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wetterstation;

/**
 *
 * @author Kowalewski
 */
public class Wetterstation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] _tage_temperaturen = new int[20];
        int _na_value = 99;
        
        for(int i = 0; i < _tage_temperaturen.length; i++) {
            _tage_temperaturen[i] = _na_value;
        }
        
        _tage_temperaturen[1] = 12;
        _tage_temperaturen[2] = 14;
        _tage_temperaturen[3] = 9;
        _tage_temperaturen[4] = 12;
        _tage_temperaturen[5] = 15;
        _tage_temperaturen[6] = 16;
        _tage_temperaturen[7] = 15;
        _tage_temperaturen[8] = 15;
        _tage_temperaturen[9] = 11;
        _tage_temperaturen[10] = 8;
        _tage_temperaturen[11] = 13;
        _tage_temperaturen[12] = 13;
        _tage_temperaturen[13] = 15;
        _tage_temperaturen[14] = 12;
        
        float _sum = 0;
        float _count = 0;
        int _min = 999;
        int _max = 0;
        int _delta_max = 0;
        int _delta_max_pos1 = 0;
        int _delta_max_pos2 = 0;
        for(int i = 0; i < _tage_temperaturen.length; i++) {
            if(_tage_temperaturen[i] != _na_value) {
                //System.out.println(_tage_temperaturen[i] + "\n");
                _sum += _tage_temperaturen[i];
                _count++;
                int _delta = _tage_temperaturen[i] - _tage_temperaturen[i-1];
                if(_delta > _delta_max) {
                    _delta_max = _delta;
                    _delta_max_pos1 = i;
                    _delta_max_pos2 = i - 1;
                }
                
                if(_tage_temperaturen[i] > _max) {
                    _max = _tage_temperaturen[i];
                } 
                if(_tage_temperaturen[i] < _min) {
                    _min = _tage_temperaturen[i];
                } 
            }
        }
        float _avg = _sum / _count;
        System.out.println("Der Durchschnitt liegt bei: " + String.format("%.2f", _avg));
        System.out.println("Das Minimum liegt bei: " + _min);
        System.out.println("Das Maximum liegt bei: " + _max);
        System.out.println("Max. Temperaturdelta von " + _delta_max + " ergab sich von Tag " + _delta_max_pos2 + " auf " + _delta_max_pos1);
        
        // als Tabelle ausgeben
        String _out = "--------------------\n";
        _out += "| Tag | Temperatur |\n";
        _out += "--------------------\n";
        for(int i = 0; i < _tage_temperaturen.length; i++) {
            if(_tage_temperaturen[i] != _na_value) {
                if(i >= 10) {
                    _out += "|  " + i + " |";
                } else {
                    _out += "|  " + i + "  |";
                }
                if(_tage_temperaturen[i] >= 10) {
                    _out += "     " + _tage_temperaturen[i] + "     |\n";
                } else {
                    _out += "     " + _tage_temperaturen[i] + "      |\n";
                }            
            }
        }
        _out += "--------------------\n";
        System.out.println(_out);
    }
    
}
