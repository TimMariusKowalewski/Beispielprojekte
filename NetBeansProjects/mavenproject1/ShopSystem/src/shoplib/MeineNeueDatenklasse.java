/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

import java.util.UUID;

/**
 * @author Kowalewski
 */
public class MeineNeueDatenklasse
    extends BaseObject
{

    public final static String DBTableName = "tabellen_von_meine_neue_datenklasse";
    private DBString TestString1 = new DBString("",
                                                "test_string1");
    private DBFloat TestFloat1 = new DBFloat(0.00f,
                                             "test_float1");
    private DBInt TestInt1 = new DBInt(123,
                                       "test_int1");
    private DBBool TestBool1 = new DBBool(true,
                                          "test_bool1");
    
    
    
    
    //private DBArrayList<MeineNeueDatenklasse2> RelatedRecordMeineNeueDatenklasse = new DBArrayList(DBRelationshipType.OneToMany);

    // Konstruktor für neues Objekt
    public MeineNeueDatenklasse(String TestString1,
                                float TestFloat1,
                                int TestInt1,
                                boolean TestBool1)
    {
        super();
        this.TestString1.setValue(TestString1);
        this.TestFloat1.setValue(TestFloat1);
        this.TestInt1.setValue(TestInt1);
        this.TestBool1.setValue(TestBool1);
    }

    // Konstruktor für vorhandenes Objekt
    public MeineNeueDatenklasse(UUID ID)
    {
        super();
        this.setID(ID);
    }

    public String getTestString1()
    {
        return this.TestString1.getValue();
    }

    public void setTestString1(String TestString1)
    {
        this.TestString1.setValue(TestString1);
    }

    public DBFloat getTestFloat1()
    {
        return this.TestFloat1;
    }

    public void setTestFloat1(float TestFloat1)
    {
        this.TestFloat1.setValue(TestFloat1);
    }

    public DBInt getTestInt1()
    {
        return this.TestInt1;
    }

    public void setTestInt1(int TestInt1)
    {
        this.TestInt1.setValue(TestInt1);
    }

    public DBBool isTestBool1()
    {
        return this.TestBool1;
    }

    public void setTestBool1(boolean TestBool1)
    {
        this.TestBool1.setValue(TestBool1);
    }

}
