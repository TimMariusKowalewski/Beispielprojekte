/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolarSystem;

import Output.Console2D;
import Output.IConsole2DDrawable;
import Output.IJFrameDrawable2D;
import Output.Window;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

/** @author Kowalewski */
public abstract class StellarObj extends Obj implements IConsole2DDrawable, IJFrameDrawable2D {

  /* Instanzvariablen */
  private float _radius; // der Radius des Objekts
  private boolean _rotate_clockwise; // Drehrichtung des Objekts
  private StellarObj _center_obj; // das Referenz-Objekt unseres Objekts
  private float _distance2center_obj; // Entfernung zum Referenz-Objekt
  private float _angle2center_obj; // Winkel zur Längsachse des Referenz-Objects
  private float _calculated_x; // transformierte X-Koordinate
  private float _calculated_y; // transformierte X-Koordinate
	private int mysecret;

  /* Konstruktoren */
  public StellarObj() {
    super();
    this._radius = 0f;
    this._rotate_clockwise = true;
    this._center_obj = null;
    this._distance2center_obj = 0f;
    this._angle2center_obj = 0f;
  }

  public StellarObj(
      float _radius,
      boolean _rotate_clockwise,
      StellarObj _center_obj,
      float _distance2center_obj,
      float _angle2center_obj,
      float _calculated_x,
      float _calculated_y) {
    this._radius = _radius;
    this._rotate_clockwise = _rotate_clockwise;
    this._center_obj = _center_obj;
    this._distance2center_obj = _distance2center_obj;
    this._angle2center_obj = _angle2center_obj;
    this._calculated_x = _calculated_x;
    this._calculated_y = _calculated_y;
  }

  /* Eigenschaften */
  public final float getRadius() {
    return this._radius;
  }

  public final void setRadius(float Radius) {
    if (Radius < 0) {
      throw new ValueException("Obj._radius darf nicht < 0 sein!");
    } else {
      this._radius = Radius;
    }
  }

  public boolean isRotateClockwise() {
    return _rotate_clockwise;
  }

  public void setRotateClockwise(boolean RotateClockwise) {
    this._rotate_clockwise = RotateClockwise;
  }

  public final float getAngle2CenterObject() {
    return _angle2center_obj;
  }

  public final void setAngle2CenterObject(float Degree2CenterObject) {
    this._angle2center_obj = Degree2CenterObject;

    if (this._angle2center_obj < 0) {
      this._angle2center_obj += 360;
    } else if (this._angle2center_obj > 0) {
      this._angle2center_obj -= 360;
    }
  }

  public final StellarObj getCenterObject() {
    return this._center_obj;
  }

  public final void setCenterObject(StellarObj Object) {
    if (Object == null) {
      throw new ValueException("Obj._center_obj darf nicht null sein!");
    } else {
      this._center_obj = Object;
    }
  }

  public final float getDistance2CenterObject() {
    return _distance2center_obj;
  }

  public final void setDistance2CenterObject(float Distance2CenterObject) {
    if (Distance2CenterObject > this.getRadius()) {
      this._distance2center_obj = Distance2CenterObject;
    } else {
      throw new Error("StellarObj: Distanz zum Referenz-Objekt unsinnig!");
    }
  }

  public float getCalculatedX() {
    return _calculated_x;
  }

  public void setCalculatedX(float CalculatedX) {
    this._calculated_x = CalculatedX;
  }

  public float getCalculatedY() {
    return _calculated_y;
  }

  public void setCalculatedY(float CalculatedY) {
    this._calculated_y = CalculatedY;
  }

  /* implementierte Instanz-Methoden */
  // Zeichnen-Funktion für die Konsole
  /** @param Console */
  @Override
  public void drawOnConsole2D(Console2D Console) {
    // für die Console passende Koordinaten berechnen
    int _new_x = (int) this.transformXCoord2TopLeftOrigin(this.getX(), Console.getRows());
    int _new_y = (int) this.transformYCoord2TopLeftOrigin(this.getY(), Console.getColumns());

    // Punkte im Radius des Objekts malen
    int _loop_x_start = _new_x - (int) (this.getRadius());
    int _loop_x_end = _new_x + (int) (this.getRadius());
    int _loop_y_start = _new_y - (int) (this.getRadius());
    int _loop_y_end = _new_y + (int) (this.getRadius());

    float _a;
    float _b;
    float _c;
    for (int i = _loop_x_start; i <= _loop_x_end; i++) {
      for (int j = _loop_y_start; j <= _loop_y_end; j++) {
        _a = _new_x + i;
        _b = _new_y + j;
        Console.setAtPos(i, j, " ", this.getColor() + "_BACKGROUND");
      }
    }

    // Objekt selber malen
    Console.setAtPos(
        (int) _new_x,
        (int) _new_y,
        this.getName(),
        Obj.SolarSystemColor2ConsoleColor.get(this.getColor()).getColor());
  }

  // Zeichnen-Funktion für JFrame
  /** @param Window */
  @Override
  public void drawOnJFrame2D(Window Window) {
    // Koordinaten passend zum Window berechnen - der Canvas ist kleiner als das Window!
    int _new_x = (int) this.transformXCoord2TopLeftOrigin(this.getX(), Window.getCanvasXSize());
    int _new_y = (int) this.transformYCoord2TopLeftOrigin(this.getY(), Window.getCanvasYSize());
    this.setCalculatedX(_new_x);
    this.setCalculatedY(_new_y);

    // extra-Wurst-Anpassung für Canvas
    _new_x -= (int) (this.getRadius());
    _new_y -= (int) (this.getRadius());

    // Objekt darstellen
    Window.addShape(
        _new_x,
        _new_y,
        this.getRadius(),
        this.getName(),
        Obj.SolarSystemColor2JFrameColor.get(this.getColor()).getColor(),
        "FILLED_CIRCLE");

    // Umlaufbahn des Objekts darstellen
    // Canvas-Koordinaten des Mittelpunkts
    if (this.getCenterObject() != null) {
      int _center_x = (int) (this.getCenterObject().getCalculatedX());
      int _center_y = (int) (this.getCenterObject().getCalculatedY());
      _center_x -= (int) (this.getDistance2CenterObject());
      _center_y -= (int) (this.getDistance2CenterObject());

      Window.addShape(
          _center_x,
          _center_y,
          this.getDistance2CenterObject(),
          this.getName(),
          Obj.SolarSystemColor2JFrameColor.get(SolarSystemColor.GREEN).getColor(),
          "CIRCLE");
    }
  }

  /* Instanzmethoden */
  // berechnet die X-Position in Relation zum Referenz-Objekt
  public void calculateXPosition() {
    float _sin = (float) (Math.sin(Math.toRadians(this.getAngle2CenterObject())));
    this.setX(_sin * this.getDistance2CenterObject() + this.getCenterObject().getX());
  }

  // berechnet die Y-Position in Relation zum Referenz-Objekt
  public void calculateYPosition() {
    float _cos = (float) (Math.cos(Math.toRadians(this.getAngle2CenterObject())));
    this.setY(_cos * this.getDistance2CenterObject() + this.getCenterObject().getY());
  }

  // berechnet die Z-Position in Relation zum Referenz-Objekt
  public void calculateZPosition() {}

  // aktualisiert die Position in allen Achsen
  public final void updateRelativePosition() {
    this.calculateXPosition();
    this.calculateYPosition();
    this.calculateZPosition();
  }

  // im Uhrzeigersinn rotieren
  public void rotateClockwise() {
    this.setAngle2CenterObject(this.getAngle2CenterObject() + this.getSpeed());
  }

  // gegen den Uhrzeigersinn rotieren
  public void rotateCounterClockwise() {
    this.setAngle2CenterObject(this.getAngle2CenterObject() - this.getSpeed());
  }

  // übersetzt die X-Koordinate in ein Koordinatensystem
  // mit dem Ursprung oben links
  public final float transformXCoord2TopLeftOrigin(float X, int XSize) {
    float _half_rows = XSize >> 1;

    if (X == 0) {
      return _half_rows;
    } else {
      return (X * -1) + _half_rows;
    }
  }

  // übersetzt die Y-Koordinate in ein Koordinatensystem
  // mit dem Ursprung oben links
  public final float transformYCoord2TopLeftOrigin(float Y, int YSize) {
    float _half_columns = YSize >> 1;

    if (Y == 0) {
      return _half_columns;
    } else {
      return (Y * -1) + _half_columns + 2 * Y;
    }
  }
}
