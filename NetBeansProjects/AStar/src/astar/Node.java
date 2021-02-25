/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import java.util.List;

/**
 *
 * @author Kowalewski
 */
public class Node {
    // properties
    public static final int OBSTACLE = 3;
    public static final int START = 1;
    public static final int GOAL = 2;
    public static final int EXPANDED = 4;
    public static final int PATHNODE = 23;
    public static final int PATHNODE2 = 24;
    
    private int _x_coord;
    private int _y_coord;
    private int _node_type;
    private Node _ref_predecessor;
    
    public int getXCoord() {
        return this._x_coord;
    }
    
    public void setXCoord(int X) {
        this._x_coord = X;
    }
    
    public int getYCoord() {
        return this._y_coord;
    }
    
    public void setYCoord(int Y) {
        this._y_coord = Y;
    }
    
    public int getNodeType() {
        return this._node_type;
    }
    
    public void setNodeType(int T) {
        this._node_type = T;
    }

    public Node getPredecessor() {
        return this._ref_predecessor;
    }
    
    public void setPredecessor(Node N) {
        this._ref_predecessor = N;
    }
    
    // constructors
    public Node(int X, int Y, int NodeType) {
        this.setXCoord(X);
        this.setYCoord(Y);
        this.setNodeType(NodeType);
    }
    
    // class methods
    public void expand() {
        double _tentative_g;

        double _min_hcost;
        for(Node _n: this.getNeighbors()) {
            if(AStar.getClosedList().contains(_n) == false && _n.getNodeType() != Node.OBSTACLE) {
                if(_n.getXCoord() != AStar.getEndNode().getXCoord() || _n.getYCoord() != AStar.getEndNode().getYCoord()) {
                    _n.setNodeType(Node.EXPANDED);
                    
                    _tentative_g = _n.getGCost() + this.getDistance(_n);
                    if(AStar.getOpenList().contains(_n) == true && _tentative_g > _n.getGCost()) {
                        continue;
                    }                    
                } 
                
                AStar.getOpenList().add(_n);
            }
        }
    }
    
    public String printNode2Map() {
        switch(this.getNodeType()) {
            case Node.START:
                return AStar.getColorOutput("S", 2);
            case Node.GOAL:
                return AStar.getColorOutput("G", 2);
            case Node.OBSTACLE:
                return AStar.getColorOutput("O", 0);
            case Node.EXPANDED:
                return "E";
            case Node.PATHNODE:
                return AStar.getColorOutput("P", 1);
            case Node.PATHNODE2:
                return AStar.getColorOutput("P", 3);
            default:
                return "_";
        }
    }
    
    public void printNode() {
        String _out = this.getNodeType() + ": (" + this.getXCoord() + ", " + this.getYCoord() + ") fcost: " + this.getFCost();
        System.out.println(_out);
    }
    
    public double getFCost() {
        return this.getGCost() + this.getHCost();
    }
    
    public double getGCost() {
        return this.getDistance(AStar.getStartNode());
    }
    
    public double getHCost() {
        return this.getDistance(AStar.getEndNode());
    }
    
    public double getDistance(Node n) {
        double _a = abs(this.getXCoord() - n.getXCoord());
        double _b = abs(this.getYCoord() - n.getYCoord());
        
        return sqrt(_a * _a + _b * _b);
    }
    
    public Node[] getNeighbors() {
        // der Zugriff auf die Nachbarfelder erfolgt über dynamische Offsets
        int _x_minus_one_offset = this.getXCoord() - 1;
        int _x_plus_one_offset = this.getXCoord() + 1;
        int _y_minus_one_offset = this.getYCoord() - 1;
        int _y_plus_one_offset = this.getYCoord() + 1;
                
        // nachträgliche Manipulation der Offsets für Randfelder
        if(this.getXCoord() == 0) {
            _x_minus_one_offset = 0;
        }
        
        if(this.getXCoord() == AStar.getXSize() - 1) {
            _x_plus_one_offset = AStar.getXSize();
        }
        
        if(this.getYCoord() == 0) {
            _y_minus_one_offset = 0;
        } 
        
        if(this.getYCoord() == AStar.getYSize() - 1) {
            _y_plus_one_offset = AStar.getYSize();
        }
        
        // alle 8 Nachbarfelder einer Zelle
        Node[] _neighbors = {
            AStar.getMap()[_x_minus_one_offset][this.getYCoord()],
            AStar.getMap()[_x_plus_one_offset][this.getYCoord()],
            AStar.getMap()[this.getXCoord()][_y_minus_one_offset],
            AStar.getMap()[this.getXCoord()][_y_plus_one_offset],
            AStar.getMap()[_x_minus_one_offset][_y_minus_one_offset],
            AStar.getMap()[_x_plus_one_offset][_y_plus_one_offset],
            AStar.getMap()[_x_minus_one_offset][_y_plus_one_offset],
            AStar.getMap()[_x_plus_one_offset][_y_minus_one_offset]        
        };
        
        return _neighbors;
    }
    
    public void displayNeighbors() {
        Node[] _neighbors = this.getNeighbors();         
        for(Node n: _neighbors) {
            System.out.println("(" + n.getXCoord() + "," + n.getYCoord() + ")");
        }       
    }
}
