/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Output;

import java.awt.*;
import javax.swing.*;
import SolarSystem.StellarObj;

/**
 *
 * @author Kowalewski
 */
public class Window extends JFrame {

    private int _x_size;
    private int _y_size;
    private MyCanvas _canvas;

    public int getXSize() {
        return _x_size;
    }

    public void setXSize(int XSize) {
        this._x_size = XSize;
    }

    public int getYSize() {
        return _y_size;
    }

    public void setYSize(int YSize) {
        this._y_size = YSize;
    }

    public int getCanvasXSize() {
        return this.getCanvas().getWidth();
    }

    public int getCanvasYSize() {
        return this.getCanvas().getHeight();
    }

    final public MyCanvas getCanvas() {
        return this._canvas;
    }

    public Window(int XSize, int YSize, String Title) {
        this._canvas = new MyCanvas();
        this._x_size = XSize;
        this._y_size = YSize;

        this.setLayout(new BorderLayout());
        this.setSize(XSize, YSize);
        this.setTitle(Title);
        this.add("Center", this.getCanvas());
        this.getCanvas().setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void addShape(
            int XCoord,
            int YCoord,
            float Radius,
            String Name,
            Color Color,
            String ShapeType
    ) {
        JFrameShape.ShapeType _shape_type = null;
        if (ShapeType.equals("FILLED_CIRCLE") == true) {
            _shape_type = JFrameShape.ShapeType.FILLED_CIRCLE;
        } else if (ShapeType.equals("CIRCLE") == true) {
            _shape_type = JFrameShape.ShapeType.CIRCLE;
        }
        this.getCanvas().addShape(new JFrameShape(XCoord, YCoord, (int) Radius, Name, Color, _shape_type));
    }
}
