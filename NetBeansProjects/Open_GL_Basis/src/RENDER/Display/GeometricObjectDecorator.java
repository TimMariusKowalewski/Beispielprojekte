/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RENDER.Display;

import geometricobjects.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.lwjgl.opengl.GL11;
/**
 *
 * @author Kowalewski
 */
public class GeometricObjectDecorator {
    public static List<GeometricObject> Objects = new ArrayList();
    
    public static void draw(GeometricObject Go, String Options) {        
        if(Go instanceof Rectangle) {
            Rectangle _r = (Rectangle)Go;
            //_r.dump();
            System.out.println(Options);
            
            GL11.glColor3f(_r.getColor().getXCoord(), _r.getColor().getYCoord(), _r.getColor().getZCoord());
            //GL11.glBegin(GL11.GL_LINE_LOOP);
            
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f( 0f , 0f );
            GL11.glVertex3f(_r.getPointA().getGLXCoord(), _r.getPointA().getGLYCoord(), _r.getPointA().getGLZCoord());
            GL11.glTexCoord2f( 0f , 1f );
            GL11.glVertex3f(_r.getPointB().getGLXCoord(), _r.getPointB().getGLYCoord(), _r.getPointB().getGLZCoord());
            GL11.glTexCoord2f( 1f , 1f );
            GL11.glVertex3f(_r.getPointC().getGLXCoord(), _r.getPointC().getGLYCoord(), _r.getPointC().getGLZCoord());
            GL11.glTexCoord2f( 1f , 0f );
            GL11.glVertex3f(_r.getPointD().getGLXCoord(), _r.getPointD().getGLYCoord(), _r.getPointD().getGLZCoord());
            GL11.glEnd(); 
        } else if(Go instanceof Circle) {
            Circle _c = (Circle)Go;
            _c.dump();
            
            GL11.glColor3f(_c.getColor().getXCoord(), _c.getColor().getYCoord(), _c.getColor().getZCoord());
            GL11.glBegin(GL11.GL_POLYGON);
            
            double _x;
            double _y;
            float _x2;
            float _y2;
            float i = 360;
            while(i >= 0) {
                _x = _c.getGLXCoord() + (Math.cos(i) * 2 * _c.getGLRadius()) / GeometricObject.getGLAspectRatio();
                _y = _c.getGLYCoord() + (Math.sin(i) * 2 * _c.getGLRadius());
                
                _x2 = (float)_x;
                _y2 = (float)_y;
                
                GL11.glVertex2f(_x2, _y2);
                
                i -= 0.5;
            }
        
            GL11.glEnd();
        } else if(Go instanceof Line) {
            Line _l = (Line)Go;
            _l.dump();
            
            GL11.glColor3f(_l.getColor().getXCoord(), _l.getColor().getYCoord(), _l.getColor().getZCoord());
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex3f(_l.getPointA().getGLXCoord(), _l.getPointA().getGLYCoord(), _l.getPointB().getGLZCoord());
            GL11.glVertex3f(_l.getPointB().getGLXCoord(), _l.getPointB().getGLYCoord(), _l.getPointB().getGLZCoord());
            GL11.glEnd();
        } else if(Go instanceof Point) {
            Point _p = (Point)Go;
            _p.dump();
            
            GL11.glColor3f(_p.getColor().getXCoord(), _p.getColor().getYCoord(), _p.getColor().getZCoord());
            GL11.glBegin(GL11.GL_POINTS);
            GL11.glVertex3f(_p.getGLXCoord(), _p.getGLYCoord(), _p.getGLZCoord());
            GL11.glEnd();   
        } else if(Go instanceof Triangle) {
            Triangle _t = (Triangle)Go;
            _t.dump();
            
            GL11.glColor3f(1,0,1);
            GL11.glBegin(GL11.GL_TRIANGLES);
            GL11.glVertex3f(_t.getPointA().getGLXCoord(), _t.getPointA().getGLYCoord(), _t.getPointA().getGLZCoord());
            GL11.glVertex3f(_t.getPointB().getGLXCoord(), _t.getPointB().getGLYCoord(), _t.getPointA().getGLZCoord());
            GL11.glVertex3f(_t.getPointC().getGLXCoord(), _t.getPointC().getGLYCoord(), _t.getPointA().getGLZCoord());        
            GL11.glEnd();
        } else {
            throw new IllegalArgumentException("GeometricObjectDecorator: unbekannter Typ");
        }
    }

    public static void init_demo1() {
        Rectangle _r = new Rectangle(new Point(0,0), new Point(60,0), new Point(60,60), new Point(0,60));
        GeometricObjectDecorator.Objects.set(1, _r);
    }
    
    public static void run_demo1() {
        Rectangle _r = (Rectangle)GeometricObjectDecorator.Objects.get(1);
        GeometricObjectDecorator.draw(_r, "");
    }
}