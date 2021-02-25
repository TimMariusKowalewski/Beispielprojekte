/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;
import shoplib.BaseObject;
import shoplib.MeineNeueDatenklasse;

/**
 * @author Kowalewski
 */
public class ShopApp
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // Neues Objekt anlegen
        MeineNeueDatenklasse test_obj1 = new MeineNeueDatenklasse("foo1", 3456f, 456, true);

        // Objekt speichern
        test_obj1.create();
        
        
        // Objekt aktualisieren
        test_obj1.setTestString1("foo2");
        test_obj1.update();
        
        // Objekt-Daten erneut abrufen
        UUID ID = test_obj1.getID();
        MeineNeueDatenklasse test_obj2 = (MeineNeueDatenklasse) BaseObject.read(
            ID,
            MeineNeueDatenklasse.class);
        
        // Eigenschaften des abgerufenen Objekts ausgeben
        System.out.println("0: " + test_obj2.getID());
        System.out.println("1: " + test_obj2.getTestString1());
        System.out.println("2: " + test_obj2.getTestFloat1());
        System.out.println("3: " + test_obj2.getTestInt1());
        System.out.println("4: " + test_obj2.isTestBool1());
    }

    // Ausgabe aller Eigenschaften eines Objekts
    public static void dump(Object O)
    {
        Method[] _methods = O.getClass().
            getMethods();

        String[] _replaces = new String[6];
        _replaces[0] = "public2";
        // _replaces[1] = "personalverwaltung_staudinger.";
        // _replaces[2] = "(java.util.List)";
        // _replaces[3] = "(java.lang.Integer)";
        // _replaces[4] = "(java.lang.Float)";
        // _replaces[5] = "(java.lang.Double)";

        String _output = O.toString() + "\n";
        for (Method _method : _methods)
        {
            // if(isGetter(_method) == true && _method.toString().equals("public final native
            // java.lang.Class java.lang.Object.getClass()") == false) {
            if (isGetter(_method) == true)
            {
                System.out.println(_method);
                try
                {
                    String _clean_method_name = _method.toString();
                    for (String _replace : _replaces)
                    {
                        // System.out.println(_replaces[i]);
                        if (_replace != null)
                        {
                            _clean_method_name = _clean_method_name.replace(
                                _replace,
                                "");
                        }
                    }
                    if (_method.invoke(O) == null)
                    {
                        _output += _clean_method_name + ": " + "\n";
                    }
                    else
                    {
                        _output += (_clean_method_name + ": " + _method.
                            invoke(O)).trim() + "\n";
                    }
                }
                catch (IllegalAccessException ex)
                {
                    // Logger.getLogger(sonnensystem.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (IllegalArgumentException ex)
                {
                    // Logger.getLogger(Personalverwaltung_Staudinger.class.getName()).log(Level.SEVERE, null,
                    // ex);
                }
                catch (InvocationTargetException ex)
                {
                    // Logger.getLogger(Personalverwaltung_Staudinger.class.getName()).log(Level.SEVERE, null,
                    // ex);
                }
            }
        }

        System.out.println(_output);
    }

    // Prüfung, ob eine Methode ein Getter ist
    public static boolean isGetter(Method method)
    {
        if (method.getName().
            startsWith("get") == false)
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
        if (method.getName().
            startsWith("set") == false)
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
