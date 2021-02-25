/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOGIK.MODEL;

import org.lwjgl.input.Keyboard;

/**
 *
 * @author Niklas
 */
public class Inputs 
{
    public static boolean[] keyDown;
    public static final int keyCount = 256;

    public static void initInputs() 
    {
        Inputs.keyDown = new boolean[Inputs.keyCount];
        
        for( int x = 0 ; x < Inputs.keyCount ; x++ )
        {
            Inputs.keyDown[x] = false;
        }
    }
    
    public static boolean onKeyDown( int key )
    {
        if( Inputs.keyDown[key] == false && Keyboard.isKeyDown( key ) == true )
        {
            Inputs.keyDown[key] = true;
            return true;
        }
        
        return false;
    }
    
    public static boolean onKeyUp( int key )
    {
        if( Inputs.keyDown[key] == true && Keyboard.isKeyDown( key ) == false )
        {
            Inputs.keyDown[key] = false;
            return true;
        }
        
        return false;
    } 
    
    public static void checkKeys() 
    {        
        for( int x = 0 ; x < Inputs.keyCount ; x++ )
        {
           if( Inputs.keyDown[x] )
           {
               Inputs.keyDown[x] = Keyboard.isKeyDown( x );
           }
        }
    }    
    
    
    
}
