/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zylinder;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 *
 * @author Kowalewski
 */
public class Zylinder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Zylinder
        double _zylinder_hoehe = 10;
        double _zylinder_radius = 1;
        double _zylinder_volumen = Math.PI * Math.pow(_zylinder_radius, 2) * _zylinder_hoehe;
        double _zylinder_oberfläche = 2 * Math.PI * Math.pow(_zylinder_radius, 2) + 2 * Math.PI + _zylinder_radius * _zylinder_hoehe;
        
        String _out = "Ein Zylinder mit der Höhe " + _zylinder_hoehe + " und dem Radius " + _zylinder_radius;
        
        DecimalFormat _df = new DecimalFormat("###.##");
        _df.setRoundingMode(RoundingMode.CEILING);        
        
        _out += " hat ein Volumen von " + _df.format(_zylinder_volumen) + " und eine Oberfläche von " + _df.format(_zylinder_oberfläche);
        System.out.println(_out);
        
        // Kugel
        double _kugel_radius = 4d;
        double _kugel_umfang = 2d * Math.PI + _kugel_radius;
        double _kugel_volumen = 4d/3d * Math.PI * Math.pow(_kugel_radius, 3d);
        double _kugel_oberfläche = 4d * Math.PI * Math.pow(_kugel_radius, 2d);
        _out = "";
        _out += "Ein Kugel mit einem Radius von " + _kugel_radius + " hat einen Umfang von " + _df.format(_kugel_umfang);
        _out += ", ein Volumen von " + _df.format(_kugel_volumen) + " und eine Oberfläche von " + _df.format(_kugel_oberfläche);
        System.out.println(_out);
        
        // Quader
        double _quader_a = 1;
        double _quader_b = 2;
        double _quader_c = 3;
        double _quader_volumen = _quader_a * _quader_b * _quader_c;
        double _quader_oberflaeche = 2 * (_quader_a * _quader_b + _quader_a * _quader_c + _quader_b * _quader_c);
        double _quader_laenge_raumdiagonalen= Math.sqrt(Math.pow(_quader_a, 2) + Math.pow(_quader_b, 2) + Math.pow(_quader_c, 2));
        double _quader_gesamt_kantenlaenge = 4 * (_quader_a + _quader_b + _quader_c);
        
        _out = "Ein Quader mit den Seitenlängen a = " + _quader_a + ", b = " + _quader_b + " und c = " + _quader_c;
        _out += " hat ein Volumen von " + _df.format(_quader_volumen) + ", eine Oberfläche von " + _df.format(_quader_oberflaeche) + ", die Länge der Raumdiagonalen beträgt " + _df.format(_quader_laenge_raumdiagonalen);
        _out += " und die Gesamtkantenlänge beträgt " + _quader_gesamt_kantenlaenge;
        System.out.println(_out);
    }
}