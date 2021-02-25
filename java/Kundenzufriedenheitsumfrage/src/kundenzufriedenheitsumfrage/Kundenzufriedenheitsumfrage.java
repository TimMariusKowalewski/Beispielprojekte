/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kundenzufriedenheitsumfrage;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Kowalewski
 */
public class Kundenzufriedenheitsumfrage {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String _pfad = "C:\\Users\\Dell\\Documents\\NetBeansProjects\\Kundenzufriedenheitsumfrage\\src\\kundenzufriedenheitsumfrage\\fragenkatalog1";
        Fragenkatalog _f = new Fragenkatalog(_pfad);
        //_f.FragenAnzeigen();
        
        Umfrage _u = new Umfrage("Kundenzufriedenheit - Checkliste IT");
        _u.FragenHinzufuegen(_f.getFragen());
        //_u.FragenAnzeigen();
        _u.FragebogenAusfuellen();
        
        FileWriter fileWriter;
        for(Object _a: _u.getAntworten()) {
            Antwort _a2 = (Antwort)_a;
            System.out.println(_a2.getFrage().getText() + " " + _a2.getBenutzereingabe());
        }
        
        try {
            fileWriter = new FileWriter("ergebnisse.csv");
 
            //fileWriter.append(CSV_HEADER);
            fileWriter.append("\n");
 
            for (Object _a: _u.getAntworten()) {
                Antwort _a2 = (Antwort)_a;
                fileWriter.append(',');
                fileWriter.append(_a2.getBenutzereingabe().toString());
                //fileWriter.append(_a2.getName());
            }
            fileWriter.append("\n");
 
            System.out.println("Write CSV successfully!");
        } catch (Exception e) {
            System.out.println("Writing CSV error!");
            e.printStackTrace();
        } finally {
            //FileWriter fileWriter;
            //try {
                //fileWriter.flush();
                //fileWriter.close();
            //} catch (IOException e) {
            //    System.out.println("Flushing/closing error!");
            //    e.printStackTrace();
            //}
        }
    }
}