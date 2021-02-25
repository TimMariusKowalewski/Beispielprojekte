/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kundenzufriedenheitsumfrage_auswertung;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import kundenzufriedenheitsumfrage.Fragenkatalog;
import kundenzufriedenheitsumfrage.Frage;

/**
 *
 * @author Kowalewski
 */
public class Kundenzufriedenheitsumfrage_Auswertung {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        String _csvFile = "ergebnisse.csv";
        BufferedReader _br = null;
        String _line = "";
        String _cvsSplitBy = ",";

        // Daten einlesen
        String[][] _data = new String[100][100];
        try {
            _br = new BufferedReader(new FileReader(_csvFile));
            
            int _row_count = 0;
            int _field_count;
            while ((_line = _br.readLine()) != null) {
                String[] _fields = _line.split(_cvsSplitBy);
                
                // header zeile
                if(_row_count == 0) {
                    _field_count = 0;
                    for(String _s: _fields) {
                        //System.out.println("Header" + _s);
                        _data[_row_count][_field_count] = _s;
                        _field_count++;
                    }
                } else {
                    _field_count = 0;
                    for(String _s: _fields) {
                        _data[_row_count][_field_count] = _s;
                        //System.out.println(_s);
                        _field_count++;
                    }
                }
                
                _row_count++;
            }
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
        
        // Daten auswerten (durchlaufen)
        /*for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                if(_data[i][j] != null) {
                    System.out.print(_data[i][j]);
                }
            }
            System.out.println("");
        }*/
        //long unixTime = System.currentTimeMillis() / 1000L;
        //System.out.println(unixTime);
        
        Map<String, Integer> _column2merkmal = new HashMap<>();
        Map<String, String> _id2name = new HashMap<>();
        Fragenkatalog _fk = new Fragenkatalog("C:\\Users\\Dell\\Documents\\NetBeansProjects\\Kundenzufriedenheitsumfrage\\src\\kundenzufriedenheitsumfrage\\fragenkatalog1");
        _id2name.put("UID", "UmfrageID");
        _id2name.put("TIME", "Zeistempel");
        for(Object _f: _fk.getFragen()) {
            Frage _f2 = (Frage)_f;
            if(_f2 != null) {
                //System.out.println(_f2.getID());
                //System.out.println(_f2.getText());
                _id2name.put(_f2.getID(), _f2.getText());
            }
        }
        
        // einzelne Merkmale betrachten
        for(int _index = 0; _index < 24; _index++) {
            int _sum = 0;
            int _min = 1;
            int _max = 0;
            int _count = 0;
            //int _index = 7;
            String _merkmal = "";
            String _merkmal_type = "";
            boolean _merkmal_check_numeric = false;
            for(int j = 0; j < 100; j++) {
                if(_data[j][_index] != null) {
                    // Merkmalsname
                    if(j == 0) {
                        _merkmal = _data[j][_index];
                        _column2merkmal.put(_merkmal, _index);
                    } else {
                        if(isNumeric(_data[j][_index]) == true) {
                            int _value = Integer.parseInt(_data[j][_index]);
                            _sum += _value;

                            if(_max < _value) {
                                _max = _value;
                            }
                            if(_min > _value) {
                                _min = _value;
                            }
                            _merkmal_check_numeric = true;
                        } else {
                            _merkmal_check_numeric = false;
                        }

                        _count++;
                        //System.out.println(_data[j][_index]);
                    }
                }
            }

            // Namen auflösen
            String _merkmal_orig = _merkmal;
            _merkmal = "\"" + _id2name.get(_merkmal) + "\"";
            
            if(_merkmal_check_numeric == true) {
                _merkmal_type = "Numerisch";
            } else {
                _merkmal_type = "Zeichenkette";
            }

            if(_merkmal_type == "Numerisch") {
                float _avg = _sum / _count;
                System.out.println("Anzahl Datenwerte von Merkmal " + _merkmal + " (numerisch): " + _count);
                System.out.println("Kleinster Datenwert von Merkmal " + _merkmal + " (numerisch): " + _min);
                System.out.println("Durchschnitt von Merkmal " + _merkmal + " (numerisch): " + _avg);
                System.out.println("Größter Datenwert von Merkmal " + _merkmal + " (numerisch): " + _max);
                System.out.println("");
                //System.out.println(_column2merkmal.get(_merkmal_orig));
                
                int[] _local_counts = new int[10];
                _local_counts[1] = 0;
                _local_counts[2] = 0;
                _local_counts[3] = 0;
                _local_counts[4] = 0;
                _local_counts[5] = 0;
                _local_counts[6] = 0;
                _local_counts[7] = 0;
                
                for(int i = 0; i < 100; i++) {
                    if(i == 0) {
                        //header
                    } else {
                        if(_data[i][_column2merkmal.get(_merkmal_orig)] != null) {
                            int _val = Integer.parseInt(_data[i][_column2merkmal.get(_merkmal_orig)]);
                            //System.out.println(_data[i][_column2merkmal.get(_merkmal_orig)]);
                            for(int j = 1; j <= 7; j++) {
                                if(_val == j) {
                                    _local_counts[_val]++;
                                }
                            }           
                        }
                    }
                }
                
                String _hist = "";
                for(int i = 1; i < 8; i++) {
                    Integer _n = new Integer(_local_counts[i]);
                    float _n_rel = (float)_n / (float)_count;
                    //System.out.println(_n);
                    //System.out.println(_count);
                    //System.out.println(_n_rel);
                    System.out.println("Absolute Häufigkeit von Merkmalsausprägung '" + i + "': " + _n.toString());
                    System.out.println("Relative Häufigkeit von Merkmalsausprägung '" + i + "': " + _n_rel);
                }
                
                _hist += "---------------------------------------------------------------------";
                for(int i = 1; i < 8; i++) {
                    //_hist = "---------";
                }
                _hist += "---------------------------------------------------------------------";
                System.out.println(_hist);
            } else {
                System.out.println("Anzahl Datenwerte von Merkmal " + _merkmal + " (Zeichenkette): " + _count);
            }
            System.out.println("");
        }
    }
 
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int _i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        
        return true;
    }
}