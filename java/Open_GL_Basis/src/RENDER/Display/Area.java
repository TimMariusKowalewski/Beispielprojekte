/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RENDER.Display;

import geometricobjects.Rectangle;
import geometricobjects.Point;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Kowalewski
 */
public class Area extends Rectangle implements IAlive, IDrawable, IMouseOver, IClick {
    private Point _color;
    private Texture _texture;
    
    public Area(Point A, Point B, Point C, Point D) {
        super(A, B, C, D);
    }
    
    // IAlive
    @Override
    public void onIsAlive() {
        
    }
    
    // IDrawable
    @Override
    public void onDraw() {
        
    }
    
    @Override
    public Point getColor() {
        return this._color;
    }
    
    @Override
    public void setColor(Point Color) {
        this._color = Color;
    }
    
    @Override
    public Texture getTexture() {
        return this._texture;
    }
    
    @Override
    public void setTexture(Texture T) {
        this._texture = T;
    }
    
    // IMouseOver
    @Override
    public void onMouseOver(int X, int Y) {
        
    }
    
    // IClick
    @Override
    public void onClick(int X, int Y) {
        
    }
}
