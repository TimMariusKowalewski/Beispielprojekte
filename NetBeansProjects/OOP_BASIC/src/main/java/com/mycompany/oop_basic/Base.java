/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oop_basic;

/** @author Kowalewski */
public class Base {

  private int mysecret;
  protected int _x_pos;
  protected int _y_pos;
  protected int _z_pos;

  public Base() {}

  protected final int getXPos() {
    return this._x_pos;
  }

  protected final int getYPos() {
    return this._y_pos;
  }

  protected final int getZPos() {
    return this._z_pos;
  }

  protected final void setXPos(int XPos) {
    if (XPos < 0) {
      System.out.println("XPos < 0 !");
    } else {
      this._x_pos = XPos;
    }
  }

  protected final void setYPos(int YPos) {
    if (YPos < 0) {
      System.out.println("YPos < 0 !");
    } else {
      this._y_pos = YPos;
    }
  }

  protected final void setZPos(int ZPos) {
    if (ZPos < 0) {
      System.out.println("ZPos < 0 !");
    } else {
      this._z_pos = ZPos;
    }
  }
}
