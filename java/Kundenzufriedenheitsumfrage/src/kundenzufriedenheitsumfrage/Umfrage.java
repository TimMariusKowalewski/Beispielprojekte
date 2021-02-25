/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kundenzufriedenheitsumfrage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Kowalewski
 */
public class Umfrage {
    private String _name;
    private List<Frage> _fragen;
    private List<Antwort> _antworten;
    
    private String getName() {
        return this._name;
    }
    
    private void setName(String Name) {
        this._name = Name;
    }
    
    public List getFragen() {
        return this._fragen;
    }
    
    private void setFragen() {
        this._fragen = new ArrayList<>();
    }
    
    public List getAntworten() {
        return this._antworten;
    }
    
    private void setAntworten() {
        this._antworten = new ArrayList<>();
    }
    
    public Umfrage(String Name) {
        this.setName(Name);
        this.setFragen();
        this.setAntworten();
    }
    
    public void FrageHinzufuegen(Frage F) {
        this.getFragen().add(F); 
    }
    
    public void FragenHinzufuegen(List Fragen) {
        for(Object _f: Fragen) {
            Frage _f2 = (Frage)_f;
            this.getFragen().add(_f2); 
        }
    }
    
    public void FragenAnzeigen() {
        for(Object _frage: this.getFragen()) {
            Frage _frage2 = (Frage)_frage;
            System.out.println(_frage2.toString());
        }
    }
    
    public void FragebogenAusfuellen() {
        System.out.println(this.getName());
        System.out.println("");
        
        Scanner _user_input = new Scanner(System.in);
        String _input = "";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        
        int i = 1;
        for(Object _frage: this.getFragen()) {
            //System.out.println("foo");
            Frage _frage2 = (Frage)_frage;
            if(_frage2.getParent() != null) {
                continue;
            }
            
            System.out.println("Frage " + i + ": " + _frage2.getText());
            
            if(_frage2.getFrageTyp() == 1) {
                /*_out = "\n" + "Antwortmöglichkeiten:" + "\n";
                _out += _frage2.getAntwortMin() + " stimme nicht zu" + "\n";
                _out += _frage2.getAntwortMax() + " stimme voll zu" + "\n";
                System.out.println(_out);*/
                
                _user_input = new Scanner(System.in);
                System.out.print("Ihre Antwort:");

                _input = _user_input.nextLine();
                System.out.println(_input + "\n");
                this.getAntworten().add(new Antwort<String>(_frage2, _input));
            } else if(_frage2.getFrageTyp() == 2) {                
                String _sub_antworten = _frage2.getSubAntworten();
                String[] _sub_antworten_split = _sub_antworten.split(",");
                
                /*for(int j = 0; j < _sub_antworten_split.length; j++) {
                    System.out.println(_sub_antworten_split[j] + "\n");
                    _out = "\n" + "Antwortmöglichkeiten:" + "\n";
                    _out += _frage2.getAntwortMin() + " stimme nicht zu" + "\n";
                    _out += _frage2.getAntwortMax() + " stimme voll zu" + "\n";
                    System.out.println(_out);

                    _user_input = new Scanner(System.in);
                    System.out.print("Ihre Antwort:");

                    _input = _user_input.nextLine();
                    System.out.println(_input + "\n");
                    this.getAntworten().add(new Antwort(_frage2, _input));
                }*/
                
            } else if(_frage2.getFrageTyp() == 3) {
                //_out = "\n" + "Antwortmöglichkeiten:" + "\n";
                //_out += _frage2.getAntwortMin() + " stimme nicht zu" + "\n";
                //_out += _frage2.getAntwortMax() + " stimme voll zu" + "\n";
                //System.out.println(_out);
                
                _user_input = new Scanner(System.in);
                System.out.print("Ihre Antwort (Freitext):");

                _input = _user_input.nextLine();
                System.out.println(_input + "\n");
                this.getAntworten().add(new Antwort<String>(_frage2, _input));
                
            } else if(_frage2.getFrageTyp() == 4) {                                
                _user_input = new Scanner(System.in);
                System.out.print("Ihre Antwort:");

                _input = _user_input.nextLine();
                System.out.println(_input + "\n");
                this.getAntworten().add(new Antwort<String>(_frage2, _input));
            }
            
            i++;
        }
    }
}