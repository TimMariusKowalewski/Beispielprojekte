/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Anleitung: https://de.wikipedia.org/wiki/A*-Algorithmus
package astar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Kowalewski
 */
public class AStar {
    private static Node[][] _map;
    private static Node _start_node;
    private static Node _end_node;
    private static List<Node> _closed_list;
    private static List<Node> _open_list;
    private static int _x_size;
    private static int _y_size;
    private static int _sleep_time = 500;
    
    public static Node[][] getMap() {
        return AStar._map;
    }
    
    public static void setMap(Node[][] Map) {
        AStar._map = Map;
    }
    
    public static Node getStartNode() {
        return AStar._start_node;
    }
    
    public static void setStartNode(Node N) {
        AStar._start_node = N;
    }
    
    public static Node getEndNode() {
        return AStar._end_node;
    }
    
    public static void setEndNode(Node N) {
        AStar._end_node = N;
    }
    
    public static int getXSize() {
        return AStar._x_size;
    }
    
    public static void setXSize(int X) {
        AStar._x_size = X;
    }
    
    public static int getYSize() {
        return AStar._y_size;
    }
    
    public static void setYSize(int Y) {
        AStar._y_size = Y;
    }
    
    public static List getOpenList() {
        return AStar._open_list;
    }
    
    public static void setOpenList(List L) {
        AStar._open_list = L;
    }
    
    public static List getClosedList() {
        return AStar._closed_list;
    }
    
    public static void setClosedList(List L) {
        AStar._closed_list = L;
    }
    
    public static void setMapSize(int X, int Y) {
        AStar.setMap(new Node[X][Y]);
        AStar.setXSize(X);
        AStar.setYSize(Y);
    }
    
    public static int getSleepTime() {
        return AStar._sleep_time;
    }
    
    public static String getColorOutput(String Str, int Color) {
        switch(Color) {
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
    
    public static void printMap() {
        // oberer Rand
        for(int x = 0; x < AStar.getXSize() + 2; x++) {
            System.out.print("#");
        }
        System.out.println();
	
        
        for(int y = 0; y < AStar.getYSize(); y++) {
            System.out.print("#");
            
            for(int x = 0; x < AStar.getXSize(); x++) {
                System.out.print(AStar.getMap()[x][y].printNode2Map());
            }
            System.out.println("#");
	}
        
        // unterer Rand
        for(int x = 0; x < AStar.getXSize() + 2; x++) {
           System.out.print("#");
        }
        System.out.println();
    }
    
    // FIXME: die Felder sollten nicht doppelt ausgewählt werden
    public static void initMap() {
        Random _random = new Random();   
        
        // alle Felder einmal durchrödeln und Node-Objekt anlegen
        for(int y = 0; y < AStar.getYSize(); y++) {
            for(int x = 0; x < AStar.getXSize(); x++) {
                AStar.getMap()[x][y] = new Node(x, y, 0);
            }
	}
    
        // Hindernisse generieren
        int _random_int;
        int _random_int2;
        /*int _count_obstacles = 1200;
        for(int i = 0; i < _count_obstacles; i++) {
            _random_int = _random.nextInt(AStar.x_size - 1);
            _random_int2 = _random.nextInt(AStar.y_size - 1);
            AStar.Map[_random_int][_random_int2] = new Node(_random_int, _random_int2, 3);
        }*/
        AStar.getMap()[60][20] = new Node(60, 20, 3);
        AStar.getMap()[61][20] = new Node(61, 20, 3);
        AStar.getMap()[59][20] = new Node(59, 20, 3);
        AStar.getMap()[58][20] = new Node(58, 20, 3);
        AStar.getMap()[57][20] = new Node(57, 20, 3);
        AStar.getMap()[56][20] = new Node(56, 20, 3);
        AStar.getMap()[55][20] = new Node(55, 20, 3);
        AStar.getMap()[54][20] = new Node(54, 20, 3);
        AStar.getMap()[53][20] = new Node(53, 20, 3);
        AStar.getMap()[52][20] = new Node(52, 20, 3);
        AStar.getMap()[62][20] = new Node(62, 20, 3);
        AStar.getMap()[63][20] = new Node(63, 20, 3);
        AStar.getMap()[63][19] = new Node(63, 19, 3);
        AStar.getMap()[63][18] = new Node(63, 18, 3);
        AStar.getMap()[63][17] = new Node(63, 17, 3);
        AStar.getMap()[63][16] = new Node(63, 16, 3);
                
        // Start- und Endpunkt generieren
        //_random_int = _random.nextInt(AStar.x_size - 1);
        //_random_int2 = _random.nextInt(AStar.y_size - 1);
        _random_int = 60;
        _random_int2 = 10;
        AStar.getMap()[_random_int][_random_int2] = new Node(_random_int, _random_int2, 1);
        AStar.setStartNode(AStar.getMap()[_random_int][_random_int2]);
        
        //_random_int = _random.nextInt(AStar.x_size - 1);
        //_random_int2 = _random.nextInt(AStar.y_size - 1);
        _random_int = 60;
        _random_int2 = 28;
        AStar.getMap()[_random_int][_random_int2] = new Node(_random_int, _random_int2, 2);
        AStar.setEndNode(AStar.getMap()[_random_int][_random_int2]);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Karte initialisieren
        AStar.setMapSize(120, 30);
        AStar.initMap();
        
        AStar.run(true, false);
    }
    
    public static void run(boolean OutputVisible, boolean RunInSteps) {
        // Open- und Closed-List erzeugen
        AStar.setOpenList(new ArrayList<>());
        AStar.setClosedList(new ArrayList<>());
        
        // Startknoten ab in die Open-List 
        AStar.getOpenList().add(AStar.getStartNode());
        
        // Info-Ausgabe vor Start
        if(OutputVisible == true) {
            System.out.println("Karte: " + AStar.getXSize() + "x" + AStar.getYSize());
            AStar.getStartNode().printNode();
            AStar.getEndNode().printNode();
            AStar.printMap();
        }
        
        // Start des Algorithmus
        int _count = 0; // Zähler für die Schleifendurchläufe
        while(AStar.getOpenList().size() > 0) {
            // OpenList sortieren            
            Collections.sort(AStar.getOpenList(), new NodeSorter());
            // => das Element mit den geringsten F-Kosten ist in Position 0
            Node _current_node = (Node)(AStar.getOpenList()).remove(0);
                    
            // Prüfung, ob wir bereits am Ziel sind
            if( _current_node.getXCoord() == AStar.getEndNode().getXCoord()
                && _current_node.getYCoord() == AStar.getEndNode().getYCoord()
            ) {
                if(OutputVisible == true) {
                    System.out.println("Wir sind am Ziel!");
                }
                break;
            }
            
            // die Bearbeitung des aktuellen Knotens ist abgeschlossen...
            AStar.getClosedList().add(_current_node);
            
            // ...aber wir interessieren uns für seine Nachbarn
            _current_node.expand();
            
            // zrD 
            if(
                _current_node.getXCoord() != AStar.getStartNode().getXCoord()
                || _current_node.getYCoord() != AStar.getStartNode().getYCoord()
            ) {
                _current_node.setNodeType(Node.PATHNODE2);
            }
            
            // Schrittweises Neumalen
            if(RunInSteps == true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(AStar.getSleepTime());
                } catch (Exception e) {
                
                }
                
                AStar.printMap();
            }
            
            // zrD
            if(
                _current_node.getXCoord() != AStar.getStartNode().getXCoord()
                || _current_node.getYCoord() != AStar.getStartNode().getYCoord()
            ) {
                _current_node.setNodeType(Node.PATHNODE);
            }
            
            _count++; // Schleifendurchläufe++
        }
        
        if(OutputVisible == true) {
            System.out.println(_count + " Iterationen bis zum Ziel");
            AStar.printMap();
        }
    }
    
}
