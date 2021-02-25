/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolarSystem;

import Output.Console2D;
import Output.Window;
import Tools.Vector;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/** @author Kowalewski */
public class SolarSystem {

  /** @param args the command line arguments */
  public static void main(String[] args) {
    // Sonnensystem.testSolarSystem();
    SolarSystem.testSolarSystem3();
  }

  public static void testSolarSystem3() {
    List<String> myList = Arrays.asList("element1", "element2", "element3");
		
		for (String element : myList) {
      System.out.println(element);
    }
		
		myList.forEach(element -> System.out.println(element));
		
		// Alternative mit Lambdas
  }

  public static void testSolarSystem2() {
    // Sun
    Sun _sonne = new Sun("Sonne", 30f, SolarSystemColor.YELLOW); // Radius ist eigentlich 696.340 km

    // Merkur
    Planet _merkur = new Planet("Merkur", 5f, SolarSystemColor.PINK, _sonne, 55f, 90f);
    _merkur.setSpeed(20);
    _sonne.addPlanet(_merkur);

    // Venus
    Planet _venus = new Planet("Venus", 5f, SolarSystemColor.YELLOW, _sonne, 65f, 45f);
    _venus.setSpeed(20);
    _sonne.addPlanet(_venus);

    // Erde
    Planet _erde = new Planet("Erde", 10f, SolarSystemColor.BLUE, _sonne, 100f, 0f);
    _erde.setSpeed(5);
    _sonne.addPlanet(_erde);

    // Erd-Mond
    Moon _erd_mond = new Moon("Mond (Erde)", 5f, SolarSystemColor.GRAY, _erde, 20f, 0f);
    _erd_mond.setSpeed(10);
    _erde.addMoon(_erd_mond);

    // Mars
    Planet _mars = new Planet("Mars", 10f, SolarSystemColor.RED, _sonne, 150f, 45f);
    _erde.setSpeed(5);
    _sonne.addPlanet(_mars);

    // Jupiter
    Planet _jupiter = new Planet("Jupiter", 20f, SolarSystemColor.WHITE, _sonne, 250f, 180f);
    _erde.setSpeed(4);
    _sonne.addPlanet(_jupiter);

    // Jupiter-Monde
    Moon _jupiter_mond1 =
        new Moon("Mond 1 (Jupiter)", 5f, SolarSystemColor.GREEN, _jupiter, 35f, 0f);
    _jupiter_mond1.setSpeed(10);
    _jupiter_mond1.setRotateClockwise(false);
    _jupiter.addMoon(_jupiter_mond1);
    Moon _jupiter_mond2 =
        new Moon("Mond 2 (Jupiter)", 5f, SolarSystemColor.GREEN, _jupiter, 35f, 93f);
    _jupiter_mond2.setSpeed(10);
    _jupiter.addMoon(_jupiter_mond2);

    Window _window = new Window(1000, 1000, "Mein Sonnensystem");

    Star _star; // Platzhalter für später generierte Sterne
    while (true) {
      _sonne.update(); // aktualisiert alle Planeten und Monde
      _window.getCanvas().clearShapes(); // aktuellen Canvas löschen
      _sonne.drawOnJFrame2D(_window); // zeichnet alle Planeten und Mond

      // Sterne generieren
      for (int i = 0; i < 50; i++) {
        _star = new Star(1000);
        _star.drawOnJFrame2D(_window);
      }

      // Canvas neu malen
      _window.getCanvas().repaint();

      // Warten
      try {
        Thread.sleep(100);
      } catch (InterruptedException ex) {
        Logger.getLogger(SolarSystem.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public static void testSolarSystem() {
    // unserer Sun im Zentrum (0, 0, 0)
    Sun _sonne = new Sun("Sonne", 3f, SolarSystemColor.YELLOW); // Radius ist eigentlich 696.340 km
    // Obj.dump(_sonne);

    // Erde wird in Relation zur Sun erzeugt
    Planet _erde = new Planet("Erde", 2f, SolarSystemColor.BLUE, _sonne, 15f, 0f);
    _sonne.addPlanet(_erde);
    // Obj.dump(_erde);

    // Mond wird in Relation zur Erde erzeugt
    Moon _erd_mond = new Moon("Mond (Erde)", 1f, SolarSystemColor.GRAY, _erde, 5f, 0f);
    _erde.addMoon(_erd_mond);
    // Obj.dump(_erd_mond);

    // Merkur
    Planet _merkur = new Planet("Merkur", 1f, SolarSystemColor.PINK, _sonne, 7f, 90f);
    _sonne.addPlanet(_merkur);

    // Venus
    Planet _venus = new Planet("Venus", 2f, SolarSystemColor.GREEN, _sonne, 11f, 45f);
    _sonne.addPlanet(_venus);

    // Mars
    Planet _mars = new Planet("Mars", 2f, SolarSystemColor.RED, _sonne, 20f, 120f);
    _sonne.addPlanet(_mars);
    Moon _mars_mond = new Moon("Mond (Mars)", 1f, SolarSystemColor.GRAY, _mars, 5f, 0f);
    _mars.addMoon(_mars_mond);
    // Obj.dump(_erd_mond);

    // Ausgabe in der Konsole
    // wie eine Matrix: m-Zeilen x n-Spalten
    Console2D _console = new Console2D(60, 200);
    _console.drawConsoleHeader();

    // Zeichnen der Sun löst das Zeichen von Planeten und deren Monden aus
    _sonne.drawOnConsole2D(_console);
    _console.draw();

    int i = 0;
    int _max = 500;
    while (i < _max) {
      // alle Berechnungen durchführen
      _sonne.update();

      // Console leeren und neu darstellen
      System.out.print("\033[H\033[2J");
      System.out.flush();
      _console.init();
      _sonne.drawOnConsole2D(_console);
      _console.draw();

      // warten
      try {
        Thread.sleep(200);
      } catch (InterruptedException ex) {
        Logger.getLogger(SolarSystem.class.getName()).log(Level.SEVERE, null, ex);
      }

      i++;
    }
  }

  public static void testVectors() {
    float[] _v1_data = {1f, 2f, 3f};
    Vector _v1 = new Vector(3, _v1_data);
    float[] _v2_data = {4f, 5f, 6f};
    Vector _v2 = new Vector(3, _v2_data);

    Vector _v3 = Vector.add(_v1, _v2);
    System.out.println(_v3.getData()[2]);
    // Obj.dump(_v3);

  }
}
