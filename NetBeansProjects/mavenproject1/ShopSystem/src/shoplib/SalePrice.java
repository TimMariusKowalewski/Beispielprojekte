/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

import java.time.LocalDateTime;

/**
 * @author Kowalewski
 */
public class SalePrice
{

    private int _bulk_min_quantity;
    private float _sales_tax_factor;
    private LocalDateTime _validity_begin;
    private LocalDateTime _validity_end;
    private float _value;

    private SalePrice(Builder Builder)
    {
        this._value = Builder.Value;
        this._sales_tax_factor = Builder.SalesTaxFactor;
        this._validity_begin = Builder.ValidityBegin;
        this._validity_end = Builder.ValidityEnd;
        this._bulk_min_quantity = Builder.BulkMinQuantity;
    }

    public int getBulkMinQuantity()
    {
        return this._bulk_min_quantity;
    }

    public void setBulkMinQuantity(int Quantity)
    {
        this._bulk_min_quantity = Quantity;
    }

    public float getSalesTaxFactor()
    {
        return this._sales_tax_factor;
    }

    public LocalDateTime getValidityBegin()
    {
        return this._validity_begin;
    }

    public LocalDateTime getValidityEnd()
    {
        return this._validity_end;
    }

    public float getValue()
    {
        return this._value;
    }

    public SalePrice setSalesTaxFactor(float SalesTaxFactor)
    {
        this._sales_tax_factor = SalesTaxFactor;
        return this;
    }

    public SalePrice setValidityBegin(LocalDateTime ValidityBegin)
    {
        this._validity_begin = ValidityBegin;
        return this;
    }

    public SalePrice setValidityEnd(LocalDateTime ValidityEnd)
    {
        this._validity_end = ValidityEnd;
        return this;
    }

    public SalePrice setValue(float Value)
    {
        this._value = Value;
        return this;
    }

    public static class Builder
    {

        public float Value;
        public float SalesTaxFactor;
        public LocalDateTime ValidityBegin;
        public LocalDateTime ValidityEnd;
        public int BulkMinQuantity;

        public Builder(float Value,
                       float SalesTaxFactor)
        {
            this.Value = Value;
            this.SalesTaxFactor = SalesTaxFactor;
        }

        public Builder setValidityBegin(LocalDateTime ValidityBegin)
        {
            this.ValidityBegin = ValidityBegin;
            return this;
        }

        public Builder setValidityEnd(LocalDateTime ValidityEnd)
        {
            this.ValidityEnd = ValidityEnd;
            return this;
        }

        public Builder setBulkMinQuantity(int BulkMinQuantity)
        {
            this.BulkMinQuantity = BulkMinQuantity;
            return this;
        }

        public SalePrice build()
        {
            return new SalePrice(this);
        }
    }
}
