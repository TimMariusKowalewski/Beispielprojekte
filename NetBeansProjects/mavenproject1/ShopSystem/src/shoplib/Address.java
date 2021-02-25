/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

/**
 *
 * @author Kowalewski
 */
public class Address
{

    private String _address_type;
    private String _city;
    private String _country;
    private String _id;
    private String _postalcode;
    private String _state;
    private String _street;
    private String _street_number;
    private String _street_number_suffix;

    public String getAdressType()
    {
        return this._address_type;
    }

    public void setAdress_type(String AddressType)
    {
        this._address_type = AddressType;
    }

    public String getCity()
    {
        return this._city;
    }

    public void setCity(String City)
    {
        this._city = City;
    }

    public String getCountry()
    {
        return this._country;
    }

    public void setCountry(String Country)
    {
        this._country = Country;
    }

    public String getID()
    {
        return this._id;
    }

    public void setID(String ID)
    {
        this._id = ID;
    }

    public String getPostalcode()
    {
        return this._postalcode;
    }

    public void setPostalcode(String PostalCode)
    {
        this._postalcode = PostalCode;
    }

    public String getState()
    {
        return _state;
    }

    public void setState(String _state)
    {
        this._state = _state;
    }

    public String getStreet()
    {
        return this._street;
    }

    public void setStreet(String _street)
    {
        this._street = _street;
    }

    public String getStreetNumber()
    {
        return this._street_number;
    }

    public void setStreetNumber(String StreetNumber)
    {
        this._street_number = StreetNumber;
    }

    public String getStreetNumberSuffix()
    {
        return this._street_number_suffix;
    }

    public void setStreetNumberSuffix(String StreetNumberSuffix)
    {
        this._street_number_suffix = StreetNumberSuffix;
    }

}
