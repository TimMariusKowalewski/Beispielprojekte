/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

import java.time.LocalDateTime;

/**
 *
 * @author Kowalewski
 */
public class ShoppingCart extends Order
{

    public ShoppingCart(Customer Customer,
                        LocalDateTime OrderDate)
    {
        super(Customer,
              OrderDate);
    }

    

}
