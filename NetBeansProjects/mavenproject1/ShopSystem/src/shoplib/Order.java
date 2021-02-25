/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Kowalewski
 */
public class Order
{

    private Customer _customer;
    private LocalDateTime _order_date;
    private ArrayList<OrderItem> _order_items;
    private float sum_taxes = 0f;
    private float sum_total = 0f;
    private float sum_total_with_taxes = 0f;

    public Order(Customer Customer,
                 LocalDateTime OrderDate)
    {
        this._customer = Customer;
        this._order_date = OrderDate;
        this._order_items = new ArrayList<>();
    }

    public void addOrderItem(OrderItem OrderPos)
    {
        this._order_items.add(OrderPos);
    }

    public float calcSumTaxes()
    {
        float value = 0f;

        for (OrderItem O : this._order_items)
        {
            value += O.getSumTotalWithTaxes() - O.getSumTotal();
        }

        return value;
    }

    public float calcSumTotal()
    {
        float value = 0f;

        for (OrderItem O : this._order_items)
        {
            value += O.getSumTotal();
        }

        return value;
    }

    public float getSumTaxes()
    {
        if (this.sum_taxes == 0f)
        {
            this.sum_taxes = this.calcSumTaxes();
        }
        return this.sum_taxes;
    }

    public float getSumTotal()
    {
        if (this.sum_total == 0f)
        {
            this.sum_total = this.calcSumTotal();
        }
        return this.sum_total;
    }

    public float getSumTotalWithTaxes()
    {
        if (this.sum_total_with_taxes == 0f)
        {
            this.sum_total_with_taxes = this.calcSumTaxes() + this.
                calcSumTotal();
        }
        return this.sum_total_with_taxes;
    }

    @Override
    public String toString()
    {
        DecimalFormat f = new DecimalFormat("0.00 €");

        String output = "Übersicht für die Bestellung um " + this._order_date + ":\n";
        output += "Position\tAnzahl\tArtikel\t\tNettopreis\tNettosumme\tBruttopreis\tBruttosumme\n";

        int counter = 0;
        for (OrderItem OI : this._order_items)
        {
            counter++;
            output += counter + "\t\t" + OI.getAmount() + "\t" + OI.getArticle().
                getName();
            output += "\t" + f.format(OI.getSalePrice()) + "\t" + f.format(OI.
                getSumTotal()) + "\t" + f.format(OI.
                    getSalePriceWithTaxes()) + "\t" + f.format(OI.
                    getSumTotalWithTaxes());
            output += "\n";
        }
        output += "\n";
        output += "Nettogesamtpreis: " + f.format(this.getSumTotal()) + "\n";
        output += "MwSt: " + f.format(this.getSumTaxes()) + "\n";
        output += "Bruttogesamtpreis: " + f.format(this.getSumTotalWithTaxes()) + "\n";

        return output;
    }

}
