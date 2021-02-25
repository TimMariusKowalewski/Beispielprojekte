/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RENDER.Display;

import geometricobjects.Point;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Kowalewski
 */
public interface IDrawable {
    public void onDraw();
    
    public Point getColor();
    
    public void setColor(Point Color);
    
    public Texture getTexture();
    
    public void setTexture(Texture T);
}