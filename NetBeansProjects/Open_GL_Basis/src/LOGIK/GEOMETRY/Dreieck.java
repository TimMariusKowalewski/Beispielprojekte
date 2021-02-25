/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOGIK.GEOMETRY;

import org.lwjgl.opengl.GL11;

/**
 *
 * @author Niklas
 */
public class Dreieck 
{
    public Punkt a;
    public Punkt b;
    public Punkt c;
    
    public Dreieck()
    {
        this.a = new Punkt( 0, 0 );
        this.b = new Punkt( 0, 0 );
        this.c = new Punkt( 0 ,0 );
    }
    
    public void zeichne()
    {
        GL11.glBegin( GL11.GL_TRIANGLES );

            // koordinaten für eckpunkte
                           //    X    ,    Y      , Z
            GL11.glVertex3f(  this.a.x , this.a.y   , 0 );
            GL11.glVertex3f(  this.b.x , this.b.y   , 0 );              
            GL11.glVertex3f(  this.c.x , this.c.y   , 0 ); 

        GL11.glEnd();        
    }
}
