
package LOGIK.MODEL;



import RENDER.Display.OSMObjectFactory;
import LOGIK.GEOMETRY.Dreieck;
import LOGIK.GEOMETRY.Rechteck;
import geometricobjects.*;
import RENDER.Display.DisplayManager;
import RENDER.Display.GeometricObjectDecorator;
import RENDER.Display.Button;
import RENDER.Display.OSMNodeRectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import astar.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


/**
 *
 * @author Niklas
 */
public class Game 
{
    public static Texture texture = null;
       
    public Game() 
    {
        DisplayManager.createDisplay();   

        Inputs.initInputs();
    }
    
    public void start() throws IOException
    {      
        //initialize ...
        GL11.glClearColor( 0.0f , 0.5333f, 1.00f, 0f);
        
        try {
            // das bild einmalig in die texture ( RAM der GraKa )
                Game.texture = TextureLoader.getTexture( "PNG" , new FileInputStream( "res/a.png" ) );
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_DITHER);
        GL11.glDisable(GL11.GL_LIGHTING);   

        // main game loop starten
        this.run();      
    }
    
    public void ende()
    {        
        // game beenden und aufräumen
        this.closeGame();          
    }
         
    
    private void run()
    {                          
        // Fenster initialisieren
        //GL11.glClearColor(0, 0, 0, 1);
        GeometricObject.setGLAspectRatio(RENDER.Display.DisplayManager.ASPECT_RATIO);
        GeometricObject.setWindowWidth(RENDER.Display.DisplayManager.WIDTH);
        GeometricObject.setWindowHeight(RENDER.Display.DisplayManager.HEIGHT);
        GeometricObject.setWindowDepth(1);
        GeometricObject.setWindowMidpoint();
        
        // unsere Objektliste
        GeometricObject.initObjectList();
        List<GeometricObject> _object_list = GeometricObject.getObjectList();
        
        /*int _map_x = 120;
        int _map_y = 30;
        AStar.setMapSize(_map_x, _map_y);
        AStar.initMap();
        AStar.run(false, false);
        Node[][] _map = AStar.getMap();
        AStar.getStartNode().printNode();
        AStar.getEndNode().printNode();

        for(int i = 0; i < AStar.getXSize(); i++) {
            for(int j = 0; j < AStar.getYSize(); j++) {
                //Node _n = AStar.getMap
            }
        }*/
        // Objekte anlegen
        for(int i = 0; i < 10; i++) {
            Random _rand = new Random();
            int _x1 = 50 + 50*i;
            int _x2 = 50 + 50*i;
            int _x3 = 106 + 50*i;
            int _x4 = 106 + 50*i;
            int _y1 = 50 + 50*i;
            int _y2 = 106 + 50*i;
            int _y3 = 106 + 50*i;
            int _y4 = 50 + 50*i;
            Rectangle _r = new Rectangle(new Point(_x1,_y1), new Point(_x2,_y2), new Point(_x3,_y3), new Point(_x4,_y4));
            _r.setColor(new Point(0,1,1));
            _object_list.add(_r);
        }
        
        //Rectangle _r = new Rectangle(new Point(0,500), new Point(0,600), new Point(800,600), new Point(800,500));
            //_r.setColor(new Point(1,0,0));
            //_object_list.add(_r);
        
        //Line _l1 = new Line(new Point(0,0), new Point(10,10));
        //_object_list.add(_l1);
        
        /*Point _p1 = new Point(15, 5);
        _p1.setColor(new Point(0, 0, 1));
        _object_list.add(_p1);
        Point _p2 = new Point(25, 5);
        _p2.setColor(new Point(0, 0, 1));
        _object_list.add(_p2);
        Point _p3 = new Point(35, 5);
        _p3.setColor(new Point(0, 0, 1));
        _object_list.add(_p3);
        Point _p4 = new Point(45, 5);
        _p4.setColor(new Point(0, 0, 1));
        _object_list.add(_p4);
        
        Circle _c = new Circle(new Point(
                GeometricObject.getWindowMidpoint().getXCoord(),
                GeometricObject.getWindowMidpoint().getYCoord(),
                GeometricObject.getWindowMidpoint().getZCoord()
        ), 300d);
        _object_list.add(_c);
        
        Triangle _t = new Triangle(new Point(30,30), new Point(100,60), new Point(100,100));
        _object_list.add(_t);
        
        Button _b1 = new Button(new Point(0,0), new Point(0, 0), new Point(0, 0), new Point(0, 0));
        _b1.setColor(new Point(1,0,0));
        _b1.set_Pos_Top_Left_in_proz(0, 0, 20, 50);
        _object_list.add(_b1);*/
        
        //_object_list.clear();
        //OSMObjectFactory.parseOSMXML("C:\\Users\\Dell\\Downloads\\test2.osm");
        //List _nodes = OSMObjectFactory.getNodeRectangles();
        //boolean _addAll = _object_list.addAll(_nodes);
        
        //List _nodes = OSMObjectFactory.getNodes();
        //boolean _addAll = _object_list.addAll(_nodes);
        
        
        // ab hier die grosse endlos game schleife 
        while (!Display.isCloseRequested()) 
        {   
            if( Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) )
                break;
            
            // zeichnen hier 
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            boolean _fire_events = false;
            for(Object _geo: _object_list) {
                GeometricObject _geo2 = (GeometricObject)_geo; 
                GeometricObjectDecorator.draw(_geo2, "");
                
                
                if((_geo2 instanceof Rectangle) == true && (_geo2 instanceof Button) == false) {
                    _geo2 = (Rectangle)_geo2;
                    _fire_events = true;
                } else if(_geo2 instanceof Button) {
                    _geo2 = (Button)_geo2;
                    _fire_events = true;
                } else if(_geo2 instanceof Circle) {
                    _geo2 = (Circle)_geo2;
                    _fire_events = true;
                } else if(_geo2 instanceof OSMNodeRectangle) {
                    _geo2 = (OSMNodeRectangle)_geo2;
                    _fire_events = false;
                } else if(_geo2 instanceof Triangle) {
                    _geo2 = (Triangle)_geo2;
                    _fire_events = true;
                } else if(_geo2 instanceof Line) {
                    _geo2 = (Line)_geo2;
                    _fire_events = true;
                }
                
                if(_fire_events == true) {
                    _geo2.onMouseOver(Mouse.getX(), Mouse.getY());
                    _geo2.onClick(Mouse.getX(), Mouse.getY());
                    _geo2.onBeingAlive();
                    _fire_events = false;
                }
            }
            
            GL11.glBindTexture( GL11.GL_TEXTURE_2D , Game.texture.getTextureID() );
            
            DisplayManager.updateDispaly();            
        }        
    }
    
    private void closeGame()
    {         
        DisplayManager.closeDisplay();        
    }    
    
}

