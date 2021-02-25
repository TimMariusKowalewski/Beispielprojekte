/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Kowalewski
 */
public class Contact
{

    private ArrayList<String> _first_names;
    private String _last_name;
    private ArrayList<Address> _addresses;
    private ArrayList<String> _salutations;
    private LocalDate _date_of_birth;
    private ArrayList<String> _phone_numbers;

    public ArrayList<String> getFirstNames()
    {
        return this._first_names;
    }

    public void setFirstNames(ArrayList<String> FirstNames)
    {
        this._first_names = FirstNames;
    }

    public String getLastName()
    {
        return this._last_name;
    }

    public void setLast_name(String LastName)
    {
        this._last_name = LastName;
    }

    public ArrayList<Address> getAddresses()
    {
        return this._addresses;
    }

    public void setAddresses(ArrayList<Address> Addresses)
    {
        this._addresses = _addresses;
    }

    public ArrayList<String> getSalutations()
    {
        return this._salutations;
    }

    public void setSalutations(ArrayList<String> _salutations)
    {
        this._salutations = _salutations;
    }

    public LocalDate getDate_of_birth()
    {
        return this._date_of_birth;
    }

    public void setDate_of_birth(LocalDate _date_of_birth)
    {
        this._date_of_birth = _date_of_birth;
    }

    public ArrayList<String> getPhone_numbers()
    {
        return this._phone_numbers;
    }

    public void setPhone_numbers(ArrayList<String> _phone_numbers)
    {
        this._phone_numbers = _phone_numbers;
    }

}
