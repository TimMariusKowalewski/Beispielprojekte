/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolarSystem;

import java.util.Random;

/**
 *
 * @author Kowalewski
 */
public class Star
    extends StellarObj
{

    public Star(int RandMax)
    {
        super();

        Random _rand = new Random();
        this.setX(_rand.nextInt(RandMax));
        this.setY(_rand.nextInt(RandMax));
        this.setRadius(1f);
        this.setSpeed(0);
        if (_rand.nextBoolean() == true)
        {
            this.setX(this.getX() * -1);
        }
        if (_rand.nextBoolean() == true)
        {
            this.setY(this.getY() * -1);
        }
        this.setName("Star " + this.getX() + this.getY());
        this.setColor(SolarSystemColor.WHITE);
    }

}
