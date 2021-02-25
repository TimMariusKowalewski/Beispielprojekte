/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Kowalewski
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            new ProcessBuilder("C:\\tools\\neovim\\Neovim\\bin\\nvim.exe").inheritIO().start().waitFor();
        } catch (Exception e) {
            
        }

    }
    
}
