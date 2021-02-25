/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RENDER.Display;

import geometricobjects.GeometricObject;
import geometricobjects.Rectangle;
import geometricobjects.Point;

/**
 *
 * @author Kowalewski
 */
public class Button extends Area {
    private boolean _mouse_was_over;
    
    public boolean getMouseWasOver() {
        return _mouse_was_over;
    }
    
    public void setMouseWasOver(boolean MouseOver) {
        this._mouse_was_over = MouseOver;
    }
    
    public Button(Point PointA, Point PointB, Point PointC, Point PointD) {
        super(PointA, PointB, PointC, PointD);
    }
    
    // erlaubt die Positionierung des Button in prozentualen Angaben der Top-Left-Koordinate, Breite und Höhe
    public void set_Pos_Top_Left_in_proz(float top_proz, float left_proz, float width_proz, float height_proz) {
        // Übersetzen der Prozentangaben in Pixel-Werte
        int pixel_left_proz = Math.round(GeometricObject.getWindowHeight() * 0.01f * left_proz);
        int pixel_top_proz = Math.round(GeometricObject.getWindowWidth() * 0.01f * top_proz);
        int pixel_width_proz = Math.round(GeometricObject.getWindowHeight() * 0.01f * width_proz);
        int pixel_height_proz = Math.round(GeometricObject.getWindowWidth() * 0.01f * height_proz);
        
        // Pixel-Koordinaten der neuen vier Eckpunkte des Buttons setzen         
        this.getPointA().setXCoord(pixel_left_proz, false);
        this.getPointA().setYCoord(pixel_top_proz, false);
        this.getPointB().setXCoord(pixel_left_proz, false);
        this.getPointB().setYCoord(pixel_top_proz + pixel_height_proz, false);
        this.getPointC().setXCoord(pixel_left_proz + pixel_width_proz, false);
        this.getPointC().setYCoord(pixel_top_proz + pixel_height_proz, false);
        this.getPointD().setXCoord(pixel_left_proz + pixel_width_proz, false);
        this.getPointD().setYCoord(pixel_top_proz, false);
        
        this.setColor(new Point(0,0,1));
    }
    
    @Override
    public void onMouseOver(int X, int Y) {
        System.out.println("(" + X + ", "+ Y + ")");
    }
    
        /*Y = Math.abs(Y - DisplayManager.HEIGHT);
        //System.out.println("(" + X + ", " + Y + ")");
        
        if(
                X > this.pixel_von_links && Y > this.pixel_von_oben
                && X < this.pixel_von_links + this.breite_in_pixeln
                && Y < this.pixel_von_oben + this.hoehe_in_pixeln
        ) {
            // color set
            this.setColor(new Point(0,1,0));
            System.out.println("Drüber " + "(" + X + ", " + Y+ ")");
            if(this.mouse_was_over == false) {
                this.mouse_was_over = true;
            }
        } else {
            // anderer color
            if(this.mouse_was_over == true) {
                this.setColor(new Point(1,0,0));
                //System.out.println("NICHT Drüber");
            }
        }    
    }*/
    
    @Override
    public void onClick(int X, int Y) {
        System.out.println("(" + X + ", "+ Y + ")");
        /*try {
            Process process = new ProcessBuilder("C:\\Windows\\System32\\cmd.exe").start();
        } catch (Exception e) {
            
        }*/
    }
    
    @Override
    public void dump() {
        super.dump();
        System.out.println("LengthA: " + this.getLengthA());
        System.out.println("LengthB: " + this.getLengthB());
        System.out.println("Area: " + this.getArea());
        System.out.println("Perimeter: " + this.getPerimeter());
        this.getPointA().dump();
        this.getPointB().dump();
        this.getPointC().dump();
        this.getPointD().dump();
        System.out.println("");
    }
}