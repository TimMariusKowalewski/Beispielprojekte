/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lernerfolgskontrolle20210219_Kowalewski;

/**
 * Einfaches Wörterbuch
 *
 * @author Kowalewski
 */
public class Aufgabe4
{

    public static void main(String[] args)
    {

        Aufgabe4_Dictionary DeutschEnglisch = new Aufgabe4_Dictionary("Wörterbuch Deutsch-Englisch / Englisch-Deutsch");
        
        Aufgabe4_DictionaryEntry d1 = new Aufgabe4_DictionaryEntry("essen", "to eat");
        Aufgabe4_DictionaryEntry d2 = new Aufgabe4_DictionaryEntry("Kettensäge", "chainsaw");
        Aufgabe4_DictionaryEntry d3 = new Aufgabe4_DictionaryEntry("Vogel", "bird");
        Aufgabe4_DictionaryEntry d4 = new Aufgabe4_DictionaryEntry("Katze", "cat");
        Aufgabe4_DictionaryEntry d5 = new Aufgabe4_DictionaryEntry("Spaß", "fun");
        Aufgabe4_DictionaryEntry d6 = new Aufgabe4_DictionaryEntry("lachen", "to laugh");
        Aufgabe4_DictionaryEntry d7 = new Aufgabe4_DictionaryEntry("Haus", "house");
        Aufgabe4_DictionaryEntry d8 = new Aufgabe4_DictionaryEntry("Zeile", "line");
        Aufgabe4_DictionaryEntry d9 = new Aufgabe4_DictionaryEntry("Wörterbuch", "Dictionary");
        Aufgabe4_DictionaryEntry d10 = new Aufgabe4_DictionaryEntry("Hund", "dog");
        
        DeutschEnglisch.addEntry(d1);
        DeutschEnglisch.addEntry(d2);
        DeutschEnglisch.addEntry(d3);
        DeutschEnglisch.addEntry(d4);
        DeutschEnglisch.addEntry(d5);
        DeutschEnglisch.addEntry(d6);
        DeutschEnglisch.addEntry(d7);
        DeutschEnglisch.addEntry(d8);
        DeutschEnglisch.addEntry(d9);
        DeutschEnglisch.addEntry(d10);
        
        // Wörterbuch ausgeben
        DeutschEnglisch.print();
        System.out.println("");
        System.out.println("Sortiert nach den deutschen Begriffen:");
        DeutschEnglisch.sortByLanguage1();
        DeutschEnglisch.print();
        System.out.println("");
        System.out.println("Sortiert nach den englischen Begriffen:");
        DeutschEnglisch.sortByLanguage2();
        DeutschEnglisch.print();
        System.out.println("");
        
        // Übersetzungen abrufen
        DeutschEnglisch.lookup("lachen");
        
        // Übersetzungen abrufen
        DeutschEnglisch.lookup("dog");
        
    }

}
