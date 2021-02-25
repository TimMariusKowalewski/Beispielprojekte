/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lernerfolgskontrolle20210219_Kowalewski;

/**
 *
 * @author Kowalewski
 */
public class Aufgabe4_DictionaryEntry
    implements Comparable<Aufgabe4_DictionaryEntry>
{

    private String Language1;
    private String Language2;
    private int sort_order;

    public Aufgabe4_DictionaryEntry(String Language1,
                           String Language2)
    {
        this.Language1 = Language1;
        this.Language2 = Language2;
        this.sort_order = 0;
    }

    public String getLanguage1()
    {
        return this.Language1;
    }

    public String getLanguage2()
    {
        return this.Language2;
    }

    public String print()
    {
        return this.Language1 + " <-> " + this.Language2;
    }
    
    public void setSortOrder(int Order) {
        this.sort_order = Order;
    }

    @Override
    public int compareTo(Aufgabe4_DictionaryEntry Entry)
    {
        if (this.sort_order == 0)
        {
            return this.getLanguage1().
                compareTo(Entry.getLanguage1());
        }
        else
        {
            return this.getLanguage2().
                compareTo(Entry.getLanguage2());
        }
    }

}
