/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Kowalewski
 */
public class Article //extends BaseObject
{

    private DBString Name = new DBString("", "asd");
    private DBFloat BuyPrice = new DBFloat(0.00f, "bla");
    private ArrayList<SalePrice> _available_sale_prices;
    private String manufacturer;
    private Article predecessor;
    private ArrayList<Attribute> attributes;
    private ArrayList<Image> images = new ArrayList<>();
    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<String> searchterms = new ArrayList<>();

    public Article(String Name)
    {
        //super();
        this.Name.setValue(Name);
        this._available_sale_prices = new ArrayList<>();
    }

    public String getName()
    {
        return this.Name.getValue();
    }

    public void setName(String Name)
    {
        this.Name.setValue(Name);
    }
    
    public float getBuyPrice()
    {
        return this.BuyPrice.getValue();
    }

    public void setBuyPrice(float BuyPrice)
    {
        this.BuyPrice.setValue(BuyPrice);
    }

    public void addPriceToAvailablePrices(SalePrice P)
    {
        if (P != null)
        {
            this._available_sale_prices.add(P);
        }
    }

    public SalePrice getCurrentSalePrice(float Amount)
    {
        /*Collections.sort(this._available_sale_prices,
                         new SalePriceComparator(Amount));
        return this._available_sale_prices.get(0);*/

        SalePrice current_price = null;
        LocalDateTime now = LocalDateTime.now();
        for (SalePrice P : this._available_sale_prices)
        {
            if (now.isAfter(P.getValidityBegin()) && now.isBefore(P.
                getValidityEnd()))
            {
                if (P.getBulkMinQuantity() <= Amount)
                {
                    if (current_price != null)
                    {
                        if (current_price.getBulkMinQuantity() < P.
                            getBulkMinQuantity())
                        {
                            current_price = P;
                        }
                        /*if (current_price.getBulkMinQuantity() < P.
                            getBulkMinQuantity())
                        {
                            // timespan for current_price
                            long timespan_validity_current_price = current_price.
                                getValidityEnd().
                                getNano() - current_price.getValidityBegin().
                                    getNano();

                            // timespan for current element
                            long timespan_validity_current_element = P.
                                getValidityEnd().
                                getNano() - P.getValidityBegin().
                                    getNano();

                            if (timespan_validity_current_price > timespan_validity_current_element)
                            {
                                current_price = P;
                            }
                        }*/
                    }
                    else
                    {
                        current_price = P;
                    }
                }
            }
        }

        // im Zweifel einen Default-Preis zurückgeben
        if (current_price == null)
        {
            current_price = this._available_sale_prices.get(0);
        }

        return current_price;
    }
}
