/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RENDER.Display;

import LOGIK.Config;
import LOGIK.Main;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

/**
 *
 * @author Niklas
 */
public class DisplayManager 
{
    public static int WIDTH = Config.window_width;    
    public static int HEIGHT = Config.window_height;    
    public static float ASPECT_RATIO = 1.0f;    
    public static final int FPS_CAP = Config.window_fps;
    
    private static int frameCounter = 0;
    private static int current_fps = 0;
    
    private static long lastFrameTime;
    private static float delta;

    
    public static void createDisplay()
    {
        ContextAttribs attribs = new ContextAttribs(3,0);
        
        try {
            
            if( Config.fullscreen )
                Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());
            else
                Display.setDisplayMode( new DisplayMode( WIDTH, HEIGHT ) );
            
            Display.create( new PixelFormat().withSamples(Config.window_multisampling) , attribs );
            //Display.create( new PixelFormat() , attribs );

            Display.setTitle( Config.window_name );
        } 
        catch (LWJGLException ex) 
        {
            Logger.getLogger(DisplayManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GL11.glViewport( 0, 0, Display.getWidth(), Display.getHeight());
        lastFrameTime = getCurrentTime();
        
        WIDTH  = Display.getWidth();
        HEIGHT = Display.getHeight();
        ASPECT_RATIO = (float)WIDTH / (float)HEIGHT;
    }
    
    public static void updateDispaly()
    {
        Display.sync(FPS_CAP);
        Display.update();
        long currentFrameTime = getCurrentTime();
        delta = (currentFrameTime - lastFrameTime) / 1000.0f;
        
        current_fps = (int)(1.0f / delta);
        
        frameCounter++;
        
        lastFrameTime = currentFrameTime;
        if( frameCounter % 30 == 0 )
            Display.setTitle( " Sec per Frame: "+ Float.toString( delta ) +" - FPS: "+ current_fps +" - Frame:"+ frameCounter );
    }
    
    public static float getFrameTimeSeconds()
    {
        return delta;              
    }
    
    public static void closeDisplay()
    {
        Display.destroy();
    }
    
    private static long getCurrentTime()
    {
      return Sys.getTime()*1000/Sys.getTimerResolution();
    }

    public static int getFrameCounter() {
        return frameCounter;
    }

    public static void setFrameCounter(int frameCounter) {
        DisplayManager.frameCounter = frameCounter;
    }

}
