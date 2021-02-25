/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

import java.util.Comparator;

/**
 *
 * @author Kowalewski
 */
public class SalePriceComparator implements Comparator<SalePrice>
{
    private float _amount;
    
    public SalePriceComparator(float Amount) {
        super();
        this._amount = Amount;
    }
    
    
    @Override
    public int compare(SalePrice p1, SalePrice p2) {
        if(p1.getBulkMinQuantity() > p2.getBulkMinQuantity()) {
            return 1;
        } else if(p1.getBulkMinQuantity() == p2.getBulkMinQuantity()) {
            return 0;
        } else {
            return -1;
        }
    }
}
