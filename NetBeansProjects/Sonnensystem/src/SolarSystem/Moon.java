/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolarSystem;

import Output.Console2D;
import Output.Window;

/**
 *
 * @author Kowalewski
 */
public class Moon extends StellarObj {

    /* Konstruktoren */
    // default Konstruktor
    public Moon(String Name, float Radius, SolarSystemColor Color) {
        super();
        this.setName(Name);
        this.setRadius(Radius);
        this.setColor(Color);
    }

    // erzeugt einen Mond in Relation zu einem Referenz-Objekt
    public Moon(
            String Name,
            float Radius,
            SolarSystemColor Color,
            StellarObj CenterObject,
            float Distance2CenterObject,
            float Angle2CenterObject
    ) {
        this(Name, Radius, Color);
        //this.setSpeed(10);
        this.setCenterObject(CenterObject);
        this.setDistance2CenterObject(Distance2CenterObject);
        this.setAngle2CenterObject(Angle2CenterObject);
        this.updateRelativePosition();
    }

    // erzeugt einen Mond in absoluter Position
    public Moon(
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

    public void update() {
        if (this.isRotateClockwise() == true) {
            this.rotateClockwise();
        } else {
            this.rotateCounterClockwise();
        }
        this.updateRelativePosition();
    }

}
