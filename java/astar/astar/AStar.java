/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Anleitung: https://de.wikipedia.org/wiki/A*-Algorithmus
package astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Kowalewski
 */
public class AStar {
    static boolean debug = false;
    static Node[][] Map;
    static Node start_node;
    static Node end_node;
    static List<Node> closed_list;
    static List<Node> open_list;
    static int x_size;
    static int y_size;
    public static final int SLEEP_TIMEOUT = 0;

    public static void set_map_size(final int x_size, final int y_size) {
        AStar.Map = new Node[x_size][y_size];
        AStar.x_size = x_size;
        AStar.y_size = y_size;
    }

    public static void log(String LogMsg, boolean Debug) {
        if (AStar.debug == true || Debug == true) {
            System.out.println(LogMsg + "\n");
        }
    }

    public static String color_output(final String Str, final int Color) {
        switch (Color) {
            case 0:
                return "\033[31;1m" + Str + "\033[0m";
            case 1:
                return "\033[32;1;2m" + Str + "\033[0m";
            case 2:
                return "\u001B[35m" + Str + "\033[0m";
            case 3:
                return "\u001B[34m" + Str + "\033[0m";
            default:
                return Str;
        }
    }

    public static void print_map() {
        // oberer Rand
        for (int x = 0; x < AStar.x_size + 2; x++) {
            System.out.print("#");
        }
        System.out.println();

        for (int y = 0; y < AStar.y_size; y++) {
            System.out.print("#");

            for (int x = 0; x < AStar.x_size; x++) {
                System.out.print(AStar.Map[x][y].print_node2map());
            }
            System.out.println("#");
        }

        // unterer Rand
        for (int x = 0; x < AStar.x_size + 2; x++) {
            System.out.print("#");
        }
        System.out.println();
    }

    // FIXME: die Felder sollten nicht doppelt ausgewählt werden
    public static void init_map() {
        final Random _random = new Random();

        // alle Felder einmal durchrödeln und Node-Objekt anlegen
        for (int y = 0; y < AStar.y_size; y++) {
            for (int x = 0; x < AStar.x_size; x++) {
                AStar.Map[x][y] = new Node(x, y, 0);
            }
        }

        // Hindernisse generieren
        int _random_int;
        int _random_int2;
        int _count_obstacles = 120;
        for (int i = 0; i < _count_obstacles; i++) {
            _random_int = _random.nextInt(AStar.x_size - 1);
            _random_int2 = _random.nextInt(AStar.y_size - 1);
            AStar.Map[_random_int][_random_int2] = new Node(_random_int, _random_int2, 3);
        }
        /*AStar.Map[60][20] = new Node(60, 20, 3);
        AStar.Map[61][20] = new Node(61, 20, 3);
        AStar.Map[59][20] = new Node(59, 20, 3);
        AStar.Map[58][20] = new Node(58, 20, 3);
        AStar.Map[57][20] = new Node(57, 20, 3);
        AStar.Map[56][20] = new Node(56, 20, 3);
        AStar.Map[55][20] = new Node(55, 20, 3);
        AStar.Map[54][20] = new Node(54, 20, 3);
        AStar.Map[53][20] = new Node(53, 20, 3);
        AStar.Map[52][20] = new Node(52, 20, 3);
        AStar.Map[62][20] = new Node(62, 20, 3);
        AStar.Map[63][20] = new Node(63, 20, 3);
        AStar.Map[63][19] = new Node(63, 19, 3);
        AStar.Map[63][18] = new Node(63, 18, 3);
        AStar.Map[63][17] = new Node(63, 17, 3);
        AStar.Map[63][16] = new Node(63, 16, 3);*/

        // Start- und Endpunkt generieren
        _random_int = _random.nextInt(AStar.x_size - 1);
        _random_int2 = _random.nextInt(AStar.y_size - 1);
        // _random_int = 60;
        // _random_int2 = 10;
        AStar.Map[_random_int][_random_int2] = new Node(_random_int, _random_int2, 1);
        AStar.start_node = AStar.Map[_random_int][_random_int2];

        _random_int = _random.nextInt(AStar.x_size - 1);
        _random_int2 = _random.nextInt(AStar.y_size - 1);
        // _random_int = 60;
        // _random_int2 = 28;
        AStar.Map[_random_int][_random_int2] = new Node(_random_int, _random_int2, 2);
        AStar.end_node = AStar.Map[_random_int][_random_int2];
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        // Karte initialisieren
        AStar.set_map_size(120, 30);
        AStar.init_map();

        // Open- und Closed-List erzeugen
        AStar.open_list = new ArrayList<Node>();
        AStar.closed_list = new ArrayList<Node>();

        // Startknoten in die Open-List
        AStar.open_list.add(AStar.start_node);

        // Info-Ausgabe vor Start
        System.out.println("Karte: " + AStar.x_size + "x" + AStar.y_size);
        System.out.print("Start - ");
        AStar.start_node.print_node(true);
        System.out.print("Ziel - ");
        AStar.end_node.print_node(true);
        AStar.print_map();

        // Start des Algorithmus
        int _count = 0;
        Node _last_node = null;
        while (AStar.open_list.size() > 0) {
            AStar.log("Aktuelle Größe der OpenList: " + AStar.open_list.size(), false);
            AStar.log("Aktuelle Knoten in der OpenList:", false);
            for (final Node n : AStar.open_list) {
                AStar.log("(" + n.x_coord + ", " + n.y_coord + ")", false);
            }
            // OpenList sortieren, so dass das Element mit den geringsten F-Kosten in
            // Position 0 ist
            Collections.sort(AStar.open_list, new NodeSorter());
            Node _current_node = AStar.open_list.remove(0);

            // Prüfung, ob wir bereits am Ziel sind
            if (_current_node.x_coord == AStar.end_node.x_coord && _current_node.y_coord == AStar.end_node.y_coord) {
                AStar.log("Wir sind am Ziel!", false);
                _last_node = _current_node;
                _current_node.print_node(true);
                break;
            }

            AStar.log("Füge folgenden Punkt der ClosedList hinzu: (" + _current_node.x_coord + ", "
                    + _current_node.y_coord + ")", false);
            AStar.closed_list.add(_current_node);
            AStar.log("Aktuelle Größe der ClosedList: " + AStar.closed_list.size(), false);
            AStar.log("Aktuelle Knoten in der ClosedList:", false);
            for (final Node n : AStar.closed_list) {
                n.print_node(true);
            }

            // diese Methode prüft die Nachbarknoten
            _current_node.expand();

            // Schrittweises Neumalen der Karte
            if (AStar.SLEEP_TIMEOUT != 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(AStar.SLEEP_TIMEOUT);
                    AStar.print_map();
                } catch (final Exception e) {
                    // AStar.print_map();
                }
            }

            AStar.open_list.remove(_current_node);
            AStar.log("2 - Aktuelle Größe der OpenList: " + AStar.open_list.size(), false);
            AStar.log("Aktuelle Knoten in der OpenList:", false);
            for (final Node n : AStar.open_list) {
                n.print_node(true);
            }

            _count++;
        }

        AStar.log(_count + " Iterationen bis zum Ziel", false);
        AStar.print_map();

        // den kürzesten Weg zum Ziel ausgeben
        AStar.log("Der Pfad:", false);
        List<Node> _path_list = new ArrayList<Node>();
        while (_last_node.predecessor != null) {
            _last_node = _last_node.predecessor;
            _path_list.add(_last_node);
            if (_last_node != AStar.start_node) {
                _last_node.node_type = Node.PATHNODE;
            }
        }

        // Reihenfolge der Pfadliste umkehren
        Collections.reverse(_path_list);

        // Endpunkt dem Pfad hinzufügen
        _path_list.add(AStar.end_node);

        // Pfad ausgeben
        int _path_count = 0;
        String _log_msg = "";
        for (final Node n : _path_list) {
            _log_msg = "Schritt " + _path_count++ + " - " + n.print_node(false);
            AStar.log(_log_msg, false);
        }

        AStar.print_map();
    }

}
