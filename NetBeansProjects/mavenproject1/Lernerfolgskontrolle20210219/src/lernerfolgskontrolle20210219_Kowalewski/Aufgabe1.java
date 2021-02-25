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
public class Aufgabe1
{

    public static void main(String[] args)
    {
        int h = 50;

        // wir müssen h-Zeilen + eine für den Stamm malen
        int rows = h + 1;

        // wir müssen 2 * (h-1) Spalten malen 
        int columns = 2 * h - 1;

        // unsere Matrix
        int[][] matrix = new int[rows][columns];

        String output = "";
        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < columns; col++)
            {

                // Spitze
                if (row == 0 && col == h - 1)
                {
                    output += "*";
                }
                // Stamm
                else if (row == rows - 1 && col == h - 1)
                {
                    output += "#";
                }
                // Mitte
                else if (row > 0 && row < rows - 1 && col == h - 1)
                {
                    output += "*";
                }
                // Äste aus der Mitte
                else if (row > 0 && row < rows - 1 && (col >= h - 1 - row && col <= h - 1 + row))
                {
                    output += "*";
                }
                else
                {
                    output += " ";
                }
            }
            output += "\n";
        }
        System.out.println(output);

    }

}
