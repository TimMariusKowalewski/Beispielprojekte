/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lwjgl_test;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;


import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
/**
 *
 * @author Kowalewski
 */
public class SphereDemo {
   public static void main(String[] args) {
     SphereDemo test = new SphereDemo();
     test.run();
}

private void run() {
     try {
     init();
     render(); // render graphics
     Display.sync(70); // sync to fps
     Display.update(); // update the view/screen
     } catch (Exception e) {
          e.printStackTrace();
          System.exit(1);
     }
}

private void render() {
     glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
     glLoadIdentity();
     glTranslatef(0.0f, 0.0f, zTranslation);
     renderSphere(-2f, -0.5f, -1f);
     renderSphere(-1f, -0.5f, -2f);
     renderSphere(-0f, -0.5f, -3f);
     renderSphere(1f, -0.5f, -4f);
     renderSphere(2f, -0.5f, -5f);
}

private void renderSphere(float x, float y, float z) {
     glPushMatrix();
     glTranslatef(x, y, z);
     Sphere s = new Sphere();
     s.draw(0.4f, 16, 16);
     glPopMatrix();
}

private void init() {
     createWindow();
     initGL();
}

private void createWindow() {
    try {
         Display.setDisplayMode(DISPLAY_MODE);
         Display.setTitle(WINDOW_TITLE);
         Display.create();
    } catch (LWJGLException e) {
         e.printStackTrace();
    }
}

private void initGL() {
     glClearDepth(1.0f); // clear depth buffer
     glEnable(GL_DEPTH_TEST); // Enables depth testing
     glDepthFunc(GL_LEQUAL); // sets the type of test to use for depth
     // testing
     glMatrixMode(GL_PROJECTION); // sets the matrix mode to project
     float fovy = 45.0f;
     float aspect = DISPLAY_MODE.getWidth() / (float) DISPLAY_MODE.getHeight();
     float zNear = 0.1f;
     float zFar = 100.0f;
     GLU.gluPerspective(fovy, aspect, zNear, zFar);
     glMatrixMode(GL_MODELVIEW);
     glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
} 
}
