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
public class Sun extends StellarObj
{

    // Sterntyp
    // assoziierte Planeten
    private ArrayList<Planet> _my_planets;

    // Konstruktoren
    public Sun(String Name, float Radius, SolarSystemColor Color)
    {
        super();
        this.setName(Name);
        this.setRadius(Radius);
        this.setColor(Color);
        this._my_planets = new ArrayList<>();
    }

    public void addPlanet(Planet Planet)
    {
        Planet.setDistance2CenterObject(Planet.getDistance2CenterObject());
        Planet.setCenterObject(this);
        this._my_planets.add(Planet);
    }

    public void update()
    {
        this._my_planets.forEach(planet ->
        {
            planet.update();
        });
    }

    @Override
    public void drawOnConsole2D(Console2D Console)
    {
        //super.drawOnConsole2D(Console);
        int _new_x = (int) this.transformXCoord2TopLeftOrigin(this.getX(), Console.getRows());
        int _new_y = (int) this.transformYCoord2TopLeftOrigin(this.getY(), Console.getColumns());
        Console.setAtPos((int) _new_x, (int) _new_y, this.getName(), Obj.SolarSystemColor2ConsoleColor.get(this.getColor()).getColor());

        // Radius des Objekts malen
        for (int i = 1; i < this.getRadius(); i++)
        {
            Console.setAtPos(_new_x, _new_y, this.getName(), Obj.SolarSystemColor2ConsoleColor.get(this.getColor()).getColor());
            Console.setAtPos(_new_x + i, _new_y, " ", this.getColor() + "_BACKGROUND");
            Console.setAtPos(_new_x, _new_y + i, " ", this.getColor() + "_BACKGROUND");
            Console.setAtPos(_new_x + i, _new_y + i, " ", this.getColor() + "_BACKGROUND");
            Console.setAtPos(_new_x - i, _new_y, " ", this.getColor() + "_BACKGROUND");
            Console.setAtPos(_new_x, _new_y - i, " ", this.getColor() + "_BACKGROUND");
            Console.setAtPos(_new_x - i, _new_y - i, " ", this.getColor() + "_BACKGROUND");
            Console.setAtPos(_new_x - i, _new_y + i, " ", this.getColor() + "_BACKGROUND");
            Console.setAtPos(_new_x + i, _new_y - i, " ", this.getColor() + "_BACKGROUND");
        }
        this._my_planets.forEach(P ->
        {
            P.drawOnConsole2D(Console);
        });
    }

    public void drawOnJFrame2D(Window Window)
    {
        super.drawOnJFrame2D(Window);

        this._my_planets.forEach(P ->
        {
            P.drawOnJFrame2D(Window);
        });
    }
}
