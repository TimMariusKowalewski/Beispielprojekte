/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lernerfolgskontrolle20210219_Kowalewski;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Kowalewski
 */
public class Aufgabe4_Dictionary
{

    private ArrayList<Aufgabe4_DictionaryEntry> Entries;
    private String Name;

    public Aufgabe4_Dictionary(String Name)
    {
        this.Name = Name;
        this.Entries = new ArrayList<>(10);
    }

    public void addEntry(Aufgabe4_DictionaryEntry Entry)
    {
        this.Entries.add(Entry);
    }

    public void removeEntry(Aufgabe4_DictionaryEntry Entry)
    {
        this.Entries.remove(Entry);
    }
    
    public void print() {
        System.out.println(this.Name + ":");
        
        int i = 0;
        for(Aufgabe4_DictionaryEntry d : this.Entries) {
            i++;
            
            System.out.println("Eintrag " + i + ": " + d.print());
        }
    }
    
    public void sortByLanguage1() {
        for (Aufgabe4_DictionaryEntry d : this.Entries) {
            d.setSortOrder(0);
        }
        Collections.sort(this.Entries);
    }
    
    public void sortByLanguage2() {
        for (Aufgabe4_DictionaryEntry d : this.Entries) {
            d.setSortOrder(1);
        }
        Collections.sort(this.Entries);
    }
    
    public void lookup(String Entry) {
        for(Aufgabe4_DictionaryEntry d : this.Entries) {
            if (d.getLanguage1().equals(Entry)) {
                System.out.println("Englische Übersetzung für '" + Entry + "' gefunden: '" + d.getLanguage2() + "'");
            }
            if (d.getLanguage2().equals(Entry)) {
                System.out.println("Deutsche Übersetzung für '" + Entry + "' gefunden: '" + d.getLanguage1() + "'");
            }
        }
    }

}
