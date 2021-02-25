/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lernerfolgskontrolle20210219_Kowalewski;

/**
 * Minimum / Maximum bestimmen
 *
 * @author Kowalewski
 */
public class Aufgabe3
{

    public static void main(String[] args)
    {
        int[] Werte =
        {
            4, 2, 10, 3, -5, 0, 17
        };

        int minimum = minimum(Werte);
        System.out.println("Kleinster Wert: " + minimum + " bei Index " + minIndex(Werte));
        int maximum = maximum(Werte);
        System.out.println("Größter Wert: " + maximum + " bei Index " + maxIndex(Werte));
    }

    public static int minimum(int[] Werte)
    {
        int kleinster_wert = Werte[0];
        for (int i = 0; i < Werte.length; i++)
        {
            if (Werte[i] < kleinster_wert)
            {
                kleinster_wert = Werte[i];
            }
        }

        return kleinster_wert;

    }

    public static int maximum(int[] Werte)
    {
        int groesster_wert = Werte[0];
        for (int i = 0; i < Werte.length; i++)
        {
            if (Werte[i] > groesster_wert)
            {
                groesster_wert = Werte[i];
            }
        }

        return groesster_wert;

    }

    public static int maxIndex(int[] Werte)
    {
        int groesster_wert = Werte[0];
        int groesster_wert_idx = 0;
        for (int i = 0; i < Werte.length; i++)
        {
            if (Werte[i] > groesster_wert)
            {
                groesster_wert = Werte[i];
                groesster_wert_idx = i;
            }
        }

        return groesster_wert_idx;
    }
    
    public static int minIndex(int[] Werte)
    {
        int kleinster_wert = Werte[0];
        int kleinster_wert_idx = 0;
        for (int i = 0; i < Werte.length; i++)
        {
            if (Werte[i] < kleinster_wert)
            {
                kleinster_wert = Werte[i];
                kleinster_wert_idx = i;
            }
        }

        return kleinster_wert_idx;
    }
}
