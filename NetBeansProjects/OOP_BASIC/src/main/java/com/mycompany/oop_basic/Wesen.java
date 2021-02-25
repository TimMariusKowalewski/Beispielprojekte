/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oop_basic;

/**
 *
 * @author Kowalewski
 */
public class Wesen extends Base {

    private int alter;

    public Wesen(int XPos, int YPos) {
        this.setXPos(XPos);
        this.setYPos(YPos);

        System.out.println("XPos: " + this.getXPos());
        System.out.println("YPos: " + this.getYPos());
    }

    public Wesen(int Alter) {
        this.setAlter(Alter);
    }
    
    public Wesen(float Alter) {
        //this.setAlter(Alter);
    }

    public final int getAlter() {
        return this.alter;
    }

    public final void setAlter(int Alter) {
        this.alter = Alter;
    }

}
