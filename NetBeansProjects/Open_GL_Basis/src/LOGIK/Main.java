/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOGIK;



import LOGIK.MODEL.Game;
import java.io.IOException;
//import geometricobjects.*;


/**
 *
 * @author Niklas
 */
public class Main 
{
    
    public static void main(String[] args) throws IOException  
    {
        Game gameInst = new Game();
        gameInst.start();
        gameInst.ende();
    }

}
