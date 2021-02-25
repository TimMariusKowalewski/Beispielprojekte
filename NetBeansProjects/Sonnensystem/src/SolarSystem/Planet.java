/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolarSystem;

import Output.Console2D;
import Output.Window;
import java.util.ArrayList;

/**
 *
 * @author Kowalewski
 */
public class Planet extends StellarObj {

    /* zusätzliche Instanzvariablen */
    private ArrayList<Moon> _my_moons;

    /* Konstruktoren */
    // default Konstruktor
    public Planet(
            String Name,
            float Radius,
            SolarSystemColor Color
    ) {
        super();
        this.setName(Name);
        this.setRadius(Radius);
        this.setColor(Color);
        this._my_moons = new ArrayList<>();
    }

    // erzeugt einen Planeten in Relation zu einem Referenz-Objekt
    public Planet(
            String Name,
            float Radius,
            SolarSystemColor Color,
            StellarObj CenterObject,
            float Distance2CenterObject,
            float Angle2CenterObject
    ) {
        this(Name, Radius, Color);
        this.setCenterObject(CenterObject);
        this.setDistance2CenterObject(Distance2CenterObject);
        this.setAngle2CenterObject(Angle2CenterObject);
        this.updateRelativePosition();
    }

    // erzeugt einen Planeten an absoluter Position ohne Referenz-Objekt
    public Planet(
            String Name,
            float Radius,
            SolarSystemColor Color,
            float XPos,
            float YPos
    ) {
        this(Name, Radius, Color);
        this.setX(XPos);
        this.setY(YPos);
    }

    /* Instanzmethoden */
    // Trabanten hinzufügen
    public void addMoon(Moon Object) {
        Object.setDistance2CenterObject(Object.getDistance2CenterObject());
        Object.setCenterObject(this);
        this._my_moons.add(Object);
    }

    // eigene Position aktualisieren und für alle Monde update() aufrufen
    public void update() {
        this.rotateClockwise();
        this.updateRelativePosition();

        this._my_moons.forEach(M -> {
            M.update();
        });
    }

    @Override
    public void drawOnConsole2D(Console2D Console) {
        super.drawOnConsole2D(Console);

        this._my_moons.forEach(M -> {
            M.drawOnConsole2D(Console);
        });
    }

    /**
     *
     * @param Window
     */
    @Override
    public void drawOnJFrame2D(Window Window) {
        super.drawOnJFrame2D(Window);
        
        this._my_moons.forEach(M -> {
            M.drawOnJFrame2D(Window);
        });
    }
}
