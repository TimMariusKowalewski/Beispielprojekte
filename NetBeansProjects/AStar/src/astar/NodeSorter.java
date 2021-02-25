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
        
        if(n1.getFCost() > n2.getFCost()) {
            _ret = 1;
        } else if(n1.getFCost() == n2.getFCost()) {
            if(n1.getHCost() > n2.getHCost()){
                _ret = 1;
            } else if(n1.getHCost() == n2.getHCost()) {
                _ret = 0;
            } else if(n1.getHCost() < n2.getHCost()) {
                _ret = -1;
            }
        } else if(n1.getFCost() < n2.getFCost()) {
            _ret = -1;
        }
        
        return _ret;
    }
}