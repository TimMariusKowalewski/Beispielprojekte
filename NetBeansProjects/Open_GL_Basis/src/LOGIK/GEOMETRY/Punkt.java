/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOGIK.GEOMETRY;

/**
 *
 * @author Niklas
 */
public class Punkt 
{
    public float x;
    public float y;
    public float z;
    
    public Punkt( float in_x , float in_y )
    {
        this.x = in_x;
        this.y = in_y;
        this.z = 0;
    }
    
    public Punkt( float in_x , float in_y , float in_z )
    {
        this.x = in_x;
        this.y = in_y;
        this.z = in_z;
    }    
}
