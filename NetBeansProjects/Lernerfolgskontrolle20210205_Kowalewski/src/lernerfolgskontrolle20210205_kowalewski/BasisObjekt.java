/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lernerfolgskontrolle20210205_kowalewski;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * Basisklasse für alle Objekte unserer Anwendung
 *
 * @author Kowalewski
 */
abstract public class BasisObjekt implements Buchhaltungs_Obj
{

    /* Instanzvariablen */
    private int _id = 0; // ID eines Objekts
    private String _name = ""; // Name eines Objekts
    private LocalDateTime _creation_date = null; // Datum der Erzeugung
    private LocalDateTime _last_modified_date = null; // Datum der letzten Änderung
    private Color _color = null; // Farbe des Objekts
    private float _weight = 0f; // Gewicht des Objekts
    private float _max_speed = 0f; // Höchstgeschwindigkeit
    private Antrieb _drive; // der Antrieb des Objekts
    private LocalDateTime _build_date = null; // Herstellungdatum
    private LocalDateTime _offer_date = null; // Angebotsdatum
    private LocalDateTime _sold_date = null; // Verkaufsdatum
    private float _sold_price = 0f; // Verkaufspreis

    /* Klassenvariablen */
    private static int COUNTER = 0; // Objekt-Zähler, wird für die ID benutzt

    /* Konstruktoren */
    public BasisObjekt(String Name)
    {
        this.setCreationDate();
        this.setID();
        this.setName(Name);
    }

    /* Eigenschaften (Getter & Setter) */
    public int getID()
    {
        return this._id;
    }

    private void setID()
    {
        if (this._id == 0)
        {
            this._id = ++COUNTER; // 1. Objekt kriegt ID 1
        }
    }

    public String getName()
    {
        return this._name;
    }

    // Name darf nicht leer sein
    public void setName(String Name)
    {
        if (Name.equals("") == true)
        {
            throw new Error("BasisObjekt.Name darf nicht leer sein!");
        } else
        {
            this._name = Name;
            this.setLastModifiedDate();
        }
    }

    public LocalDateTime getCreationDate()
    {
        return this._creation_date;
    }

    // kann nur einmal gesetzt werden
    private void setCreationDate()
    {
        if (this._creation_date == null)
        {
            this._creation_date = LocalDateTime.now();
        }
    }

    public LocalDateTime getLastModifiedDate()
    {
        return this._last_modified_date;
    }

    // ist absichtlich privat, soll nur über andere Setter 
    // der Klasse aufgerufen werden können
    private void setLastModifiedDate()
    {
        this._last_modified_date = LocalDateTime.now();
    }

    public Color getColor()
    {
        return this._color;
    }

    public void setColor(Color Color)
    {
        this._color = Color;
    }

    public float getWeight()
    {
        return this._weight;
    }

    public void setWeight(float Weight)
    {
        this._weight = Weight;
    }

    public float getMaxSpeed()
    {
        return this._max_speed;
    }

    public void setMaxSpeed(float MaxSpeed)
    {
        this._max_speed = MaxSpeed;
    }

    public Antrieb getDrive()
    {
        return this._drive;
    }

    public void setDrive(Antrieb Drive)
    {
        this._drive = Drive;
    }

    public LocalDateTime getBuildDate()
    {
        return this._build_date;
    }

    public void setBuildDate(LocalDateTime BuildDate)
    {
        this._build_date = BuildDate;
    }

    public LocalDateTime getOfferDate()
    {
        return this._offer_date;
    }

    public void setOfferDate(LocalDateTime OfferDate)
    {
        this._offer_date = OfferDate;
    }

    public LocalDateTime getSoldDate()
    {
        return this._sold_date;
    }

    public void setSoldDate(LocalDateTime SoldDate)
    {
        this._sold_date = SoldDate;
    }

    public float getSoldPrice()
    {
        return this._sold_price;
    }

    public void setSoldPrice(float SoldPrice)
    {
        this._sold_price = SoldPrice;
    }

    public boolean wurdeAngeboten()
    {
        return this._offer_date != null;
    }

    public boolean wurdeVerkauft()
    {
        return this._sold_date != null;
    }


    /* überschriebene Methoden aus Buchhaltungs_obj() */
    @Override
    public int getBaujahr()
    {
        return this._build_date.getYear();
    }

    @Override
    public String getKraftstoff()
    {
        return this.getDrive().getKraftstoff();
    }

    @Override
    public void makiereAlsAngeboten(String datum)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.setOfferDate(LocalDateTime.parse(datum, formatter));
    }

    @Override
    public void makiereAlsVerkauft(String datum, float preis)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.setSoldDate(LocalDateTime.parse(datum, formatter));
        this.setSoldPrice(preis);
    }

    /* Hilfsmethoden */
    // einfache Ausgabe aller Getter einer Objekts
    public static void dump(BasisObjekt O)
    {
        Method[] _methods = O.getClass().getMethods();

        String[] _replaces = new String[6];
        _replaces[0] = "public2";
        //_replaces[1] = "personalverwaltung_staudinger.";
        //_replaces[2] = "(java.util.List)";
        //_replaces[3] = "(java.lang.Integer)";
        //_replaces[4] = "(java.lang.Float)";
        //_replaces[5] = "(java.lang.Double)";

        String _output = O.toString() + "\n";
        for (Method _method : _methods)
        {
            //if(isGetter(_method) == true && _method.toString().equals("public final native java.lang.Class java.lang.Object.getClass()") == false) {
            if (isGetter(_method) == true)
            {
                //System.out.println(_method);
                try
                {
                    String _clean_method_name = _method.toString();
                    for (int i = 0; i < _replaces.length; i++)
                    {
                        //System.out.println(_replaces[i]);                
                        if (_replaces[i] != null)
                        {
                            _clean_method_name = _clean_method_name.replace(_replaces[i], "");
                        }
                    }
                    if (_method.invoke(O) == null)
                    {
                        _output += _clean_method_name + ": " + "\n";
                    } else
                    {
                        _output += (_clean_method_name + ": " + _method.invoke(O)).trim() + "\n";
                    }
                } catch (IllegalAccessException ex)
                {
                    //Logger.getLogger(sonnensystem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex)
                {
                    //Logger.getLogger(Personalverwaltung_Staudinger.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex)
                {
                    //Logger.getLogger(Personalverwaltung_Staudinger.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        System.out.println(_output);
    }

    // Prüfung, ob eine Methode ein Getter ist
    public static boolean isGetter(Method method)
    {
        if (method.getName().startsWith("get") == false)
        {
            return false;
        }

        if (method.getParameterTypes().length != 0)
        {
            return false;
        }

        /*if(void.class.equals(method.getReturnType()) {
            return false;
        }*/
        return true;
    }

    // Prüfung, ob eine Methode ein Setter ist
    public static boolean isSetter(Method method)
    {
        if (method.getName().startsWith("set") == false)
        {
            return false;
        }

        if (method.getParameterTypes().length != 1)
        {
            return false;
        }

        return true;
    }

}
