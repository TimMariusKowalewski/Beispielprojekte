package javaapplication1;

import java.io.IOException;

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Kowalewski
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        int _count_generations = 100;
        GOL gol = new GOL(30, 100, _count_generations); // 20x20 Karte, 100 Generationen
        System.out.println("Startkarte:");
        gol.init_map();
        gol.print_map();
        System.out.println("");
        
        //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        gol.run(_count_generations);
    }
    
}
