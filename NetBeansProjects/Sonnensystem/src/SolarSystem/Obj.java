/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolarSystem;

import Output.ConsoleColor;
import Output.JFrameColor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumMap;
import java.util.Map;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

/**
 * @author Kowalewski
 *     <p>Basisklasse für alle Objekte der Anwendung
 */
public abstract class Obj {

  // Mapping der Farben des Objekts auf andere Farbsysteme
  public static Map<SolarSystemColor, ConsoleColor> SolarSystemColor2ConsoleColor;
  public static Map<SolarSystemColor, JFrameColor> SolarSystemColor2JFrameColor;
  // Klassenvariablen
  private static int COUNTER = 1; // ID-Counter für alle Objekte
  protected int mysecret;

  // static initializer
  static {
    Obj.SolarSystemColor2ConsoleColor = new EnumMap<>(SolarSystemColor.class);
    Obj.SolarSystemColor2JFrameColor = new EnumMap<>(SolarSystemColor.class);

    Obj.SolarSystemColor2ConsoleColor.put(SolarSystemColor.DEFAULT, ConsoleColor.BLACK);
    Obj.SolarSystemColor2JFrameColor.put(SolarSystemColor.DEFAULT, JFrameColor.BLACK);
    Obj.SolarSystemColor2ConsoleColor.put(SolarSystemColor.BLACK, ConsoleColor.BLACK);
    Obj.SolarSystemColor2JFrameColor.put(SolarSystemColor.BLACK, JFrameColor.BLACK);
    Obj.SolarSystemColor2ConsoleColor.put(SolarSystemColor.BLUE, ConsoleColor.BLUE);
    Obj.SolarSystemColor2JFrameColor.put(SolarSystemColor.BLUE, JFrameColor.BLUE);
    Obj.SolarSystemColor2ConsoleColor.put(SolarSystemColor.RED, ConsoleColor.RED);
    Obj.SolarSystemColor2JFrameColor.put(SolarSystemColor.RED, JFrameColor.RED);
    Obj.SolarSystemColor2ConsoleColor.put(SolarSystemColor.YELLOW, ConsoleColor.YELLOW);
    Obj.SolarSystemColor2JFrameColor.put(SolarSystemColor.YELLOW, JFrameColor.YELLOW);
    Obj.SolarSystemColor2ConsoleColor.put(SolarSystemColor.PINK, ConsoleColor.PINK);
    Obj.SolarSystemColor2JFrameColor.put(SolarSystemColor.PINK, JFrameColor.PINK);
    Obj.SolarSystemColor2ConsoleColor.put(SolarSystemColor.GREEN, ConsoleColor.GREEN);
    Obj.SolarSystemColor2JFrameColor.put(SolarSystemColor.GREEN, JFrameColor.GREEN);
    Obj.SolarSystemColor2ConsoleColor.put(SolarSystemColor.GRAY, ConsoleColor.GRAY);
    Obj.SolarSystemColor2JFrameColor.put(SolarSystemColor.GRAY, JFrameColor.GRAY);
    Obj.SolarSystemColor2ConsoleColor.put(SolarSystemColor.WHITE, ConsoleColor.WHITE);
    Obj.SolarSystemColor2JFrameColor.put(SolarSystemColor.WHITE, JFrameColor.WHITE);
  }

  // Klassenmethoden
  // Ausgabe aller Eigenschaften eines Objekts
  public static void dump(Obj O) {
    Method[] _methods = O.getClass().getMethods();

    String[] _replaces = new String[6];
    _replaces[0] = "public2";
    // _replaces[1] = "personalverwaltung_staudinger.";
    // _replaces[2] = "(java.util.List)";
    // _replaces[3] = "(java.lang.Integer)";
    // _replaces[4] = "(java.lang.Float)";
    // _replaces[5] = "(java.lang.Double)";

    String _output = O.toString() + "\n";
    for (Method _method : _methods) {
      // if(isGetter(_method) == true && _method.toString().equals("public final native
      // java.lang.Class java.lang.Object.getClass()") == false) {
      if (isGetter(_method) == true) {
        // System.out.println(_method);
        try {
          String _clean_method_name = _method.toString();
          for (String _replace : _replaces) {
            // System.out.println(_replaces[i]);
            if (_replace != null) {
              _clean_method_name = _clean_method_name.replace(_replace, "");
            }
          }
          if (_method.invoke(O) == null) {
            _output += _clean_method_name + ": " + "\n";
          } else {
            _output += (_clean_method_name + ": " + _method.invoke(O)).trim() + "\n";
          }
        } catch (IllegalAccessException ex) {
          // Logger.getLogger(sonnensystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
          // Logger.getLogger(Personalverwaltung_Staudinger.class.getName()).log(Level.SEVERE, null,
          // ex);
        } catch (InvocationTargetException ex) {
          // Logger.getLogger(Personalverwaltung_Staudinger.class.getName()).log(Level.SEVERE, null,
          // ex);
        }
      }
    }

    System.out.println(_output);
  }

  // Prüfung, ob eine Methode ein Getter ist
  public static boolean isGetter(Method method) {
    if (method.getName().startsWith("get") == false) {
      return false;
    }

    if (method.getParameterTypes().length != 0) {
      return false;
    }

    /*if(void.class.equals(method.getReturnType()) {
    return false;
    }*/
    return true;
  }

  // Prüfung, ob eine Methode ein Setter ist
  public static boolean isSetter(Method method) {
    if (method.getName().startsWith("set") == false) {
      return false;
    }

    if (method.getParameterTypes().length != 1) {
      return false;
    }

    return true;
  }

  /* Instanzvariablen */
  private SolarSystemColor _color; // Farbe des Objektes
  private final int _id; // ID - wird über Obj.COUNTER gesetzt
  private String _name; // der Name des Objekts
  private float _speed; // Geschwindigkeit des Objekts
  // Mittelpunkt (0, 0, 0) unseres Koordinatensystems ist die Bildschirm-Mitte
  private float _x; // Position auf der X-Achse
  private float _y; // Position auf der Y-Achse
  private float _z; // Position auf der Z-Achse

  // Konstruktoren
  public Obj() {
    Obj.COUNTER++;
    this._id = Obj.COUNTER;
    this._x = 0f;
    this._y = 0f;
    this._z = 0f;
    this._color = SolarSystemColor.DEFAULT;
    this._speed = 1f;
    this._name = "";
  }

  public final SolarSystemColor getColor() {
    return this._color;
  }

  public final void setColor(SolarSystemColor Color) {
    this._color = Color;
  }

  public final int getID() {
    return this._id;
  }

  // Wert soll nicht von außen manipuliert werden können
  /*public void setID(int ID) {
  this._id = ID;
  }*/
  // Name des Objekts
  public final String getName() {
    return this._name;
  }

  public final void setName(String Name) {
    if (Name.isEmpty()) {
      throw new ValueException("Unzulässiger Wert \"" + Name + "\" für Obj._name");
    } else {
      this._name = Name;
    }
  }

  public final float getSpeed() {
    return this._speed;
  }

  public final void setSpeed(float Speed) {
    this._speed = Speed;
  }

  // Eigenschaften
  public float getX() {
    return this._x;
  }

  public final void setX(float X) {
    this._x = X;
  }

  public final float getY() {
    return this._y;
  }

  public final void setY(float Y) {
    this._y = Y;
  }

  public final float getZ() {
    return this._z;
  }

  public final void setZ(float Z) {
    this._z = Z;
  }
}
