/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Output;

import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author Kowalewski
 */
public class MyCanvas extends Canvas {

    private ArrayList<JFrameShape> _shapes; // Liste für die zu zeichnenden Objekte

    /* Konstruktoren */
    public MyCanvas() {
        this._shapes = new ArrayList<>();
    }

    // wird durch repaint() aufgerufen
    @Override
    public void paint(Graphics g) {
        // Anti-Aliasing aktivieren
        //Graphics2D g2d = (Graphics2D) g;
        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // alle Shapes in unserer Liste zeichnen
        for (JFrameShape Shape : this._shapes) {
            if (Shape.getShapeType() == JFrameShape.ShapeType.FILLED_CIRCLE) {
                g.setColor(Shape.getColor());
                g.fillOval(Shape.getXCoord(), Shape.getYCoord(), (int) (2 * Shape.getRadius()), (int) (2 * Shape.getRadius()));
            } else if (Shape.getShapeType() == JFrameShape.ShapeType.CIRCLE) {
                g.setColor(Shape.getColor());
                g.drawOval(Shape.getXCoord(), Shape.getYCoord(), (int) (2 * Shape.getRadius()), (int) (2 * Shape.getRadius()));
            }
        }
    }

    // ein Shape unserer Liste hinzufügen
    public void addShape(JFrameShape Shape) {
        this._shapes.add(Shape);
    }

    // Liste mit Shapes löschen
    public void clearShapes() {
        this._shapes.clear();
    }
}
