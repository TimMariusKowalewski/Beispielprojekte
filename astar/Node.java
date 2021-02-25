/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
//import java.util.List;

/**
 *
 * @author Kowalewski
 */
public class Node {
    public int x_coord;
    public int y_coord;
    public int node_type;
    public Node ref_predecessor;
    
    public static final int OBSTACLE = 3;
    public static final int START = 1;
    public static final int GOAL = 2;
    public static final int EXPANDED = 4;
    public static final int PATHNODE = 23;
    public static final int PATHNODE2 = 24;
    
    public Node(final int x_coord, final int y_coord, final int node_type) {
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.node_type = node_type;
    }

    public void expand() {
        double _tentative_g;

        final double _min_hcost;
        for (final Node _n : this.get_neighbors()) {
            if (AStar.closed_list.contains(_n) == false && _n.node_type != Node.OBSTACLE) {
                if (_n.x_coord != AStar.end_node.x_coord || _n.y_coord != AStar.end_node.y_coord) {
                    _n.node_type = Node.EXPANDED;

                    _tentative_g = _n.get_gcost() + this.get_distance(_n);
                    if (AStar.open_list.contains(_n) == true && _tentative_g > _n.get_gcost()) {
                        continue;
                    }
                }

                AStar.open_list.add(_n);
            }
        }
    }

    public String print_node2map() {
        switch (this.node_type) {
            case Node.START:
                return AStar.color_output("S", 2);
            case Node.GOAL:
                return AStar.color_output("G", 2);
            case Node.OBSTACLE:
                return AStar.color_output("O", 0);
            case Node.EXPANDED:
                return "E";
            case Node.PATHNODE:
                return AStar.color_output("P", 1);
            case Node.PATHNODE2:
                return AStar.color_output("P", 3);
            default:
                return "_";
        }
    }

    public void print_node() {
        final String _out = this.node_type + ": (" + this.x_coord + ", " + this.y_coord + ") fcost: "
                + this.get_fcost();
        System.out.println(_out);
    }

    public double get_fcost() {
        return this.get_gcost() + this.get_hcost();
    }

    public double get_gcost() {
        return this.get_distance(AStar.start_node);
    }

    public double get_hcost() {
        return this.get_distance(AStar.end_node);
    }

    public double get_distance(final Node n) {
        final double _a = abs(this.x_coord - n.x_coord);
        final double _b = abs(this.y_coord - n.y_coord);

        return sqrt(_a * _a + _b * _b);
    }

    public Node[] get_neighbors() {
        // der Zugriff auf die Nachbarfelder erfolgt über dynamische Offsets
        int _x_minus_one_offset = this.x_coord - 1;
        int _x_plus_one_offset = this.x_coord + 1;
        int _y_minus_one_offset = this.y_coord - 1;
        int _y_plus_one_offset = this.y_coord + 1;

        // nachträgliche Manipulation der Offsets für Randfelder
        if (this.x_coord == 0) {
            _x_minus_one_offset = 0;
        }
        if (this.x_coord == AStar.x_size - 1) {
            _x_plus_one_offset = AStar.x_size;
        }
        if (this.y_coord == 0) {
            _y_minus_one_offset = 0;
        }
        if (this.y_coord == AStar.y_size - 1) {
            _y_plus_one_offset = AStar.y_size;
        }

        // alle 8 Nachbarfelder einer Zelle
        final Node[] _neighbors = { AStar.Map[_x_minus_one_offset][this.y_coord],
                AStar.Map[_x_plus_one_offset][this.y_coord], AStar.Map[this.x_coord][_y_minus_one_offset],
                AStar.Map[this.x_coord][_y_plus_one_offset], AStar.Map[_x_minus_one_offset][_y_minus_one_offset],
                AStar.Map[_x_plus_one_offset][_y_plus_one_offset], AStar.Map[_x_minus_one_offset][_y_plus_one_offset],
                AStar.Map[_x_plus_one_offset][_y_minus_one_offset] };

        return _neighbors;
    }

    public void display_neighbors() {
        final Node[] _neighbors = this.get_neighbors();
        for (final Node n : _neighbors) {
            System.out.println("(" + n.x_coord + "," + n.y_coord + ")");
        }       
    }
}
