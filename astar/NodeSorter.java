/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.Comparator;
 
public class NodeSorter implements Comparator<Node> 
{
    @Override
    public int compare(Node n1, Node n2) {
        int _ret = 0;
        
        if(n1.get_fcost() > n2.get_fcost()) {
            _ret = 1;
        } else if(n1.get_fcost() == n2.get_fcost()) {
            if(n1.get_hcost() > n2.get_hcost()){
                _ret = 1;
            } else if(n1.get_hcost() == n2.get_hcost()) {
                _ret = 0;
            } else if(n1.get_hcost() < n2.get_hcost()) {
                _ret = -1;
            }
        } else if(n1.get_fcost() < n2.get_fcost()) {
            _ret = -1;
        }
        
        return _ret;
    }
}