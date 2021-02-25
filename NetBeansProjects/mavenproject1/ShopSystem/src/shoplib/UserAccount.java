/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

import java.util.ArrayList;

/**
 * @author Kowalewski
 */
public class UserAccount
{

    private String _password; // Benutzername
    private String _user_name; // Passwort
    private ArrayList<Order> orders = new ArrayList<>(); // die Bestellungen des Users
    // Adresse und so

    public UserAccount()
    {
        this.orders = null;
    }

    public void addOrder(Order Order)
    {
        this.orders.add(Order);
    }

    public String getPassword()
    {
        return this._password;
    }

    public void setPassword(String Password)
    {
        this._password = Password;
    }

    public String getUserName()
    {
        return this._user_name;
    }

    public void setUserName(String UserName)
    {
        this._user_name = UserName;
    }

    public void printOrders()
    {
        this.orders.forEach(
            o ->
        {
            System.out.println(o);
        });
    }
}
