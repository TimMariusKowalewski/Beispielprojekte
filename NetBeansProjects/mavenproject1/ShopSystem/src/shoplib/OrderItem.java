/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

/**
 * @author Kowalewski
 */
public class OrderItem
{

    private Article _article;
    private AttributeList _article_attributes;
    private SalePrice _sale_price;
    private int _amount;

    public OrderItem(Article A,
                     AttributeList ArticleAttributes,
                     SalePrice P,
                     int Amount)
    {
        this._article = A;
        this._article_attributes = ArticleAttributes;
        this._sale_price = P;
        this._amount = Amount;
    }

    public Article getArticle()
    {
        return this._article;
    }

    public float getSalePrice()
    {
        return this._sale_price.getValue();
    }
    
    public float getSalePriceWithTaxes()
    {
        float value = this.getSalePrice();
        value += value * (this._sale_price.getSalesTaxFactor() / 100);
        return value;
    }

    public float getSumTotal()
    {
        return this._sale_price.getValue() * this._amount;
    }

    public float getSumTotalWithTaxes()
    {
        float value = this._sale_price.getValue() * this._amount;
        value += value * (this._sale_price.getSalesTaxFactor() / 100);
        return value;
    }

    public int getAmount()
    {
        return this._amount;
    }

    public void setAmount(int Amount)
    {
        this._amount = Amount;
    }

}
