/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

/**
 * @author Kowalewski
 */
public class MeineNeueDatenklasse2
    extends BaseObject
{
    private DBForeignKey MeineNeueDatenklasseObj = new DBForeignKey("my_foreign_key");
    
    private DBString MyValue = new DBString("lala", "my_value");
    
}
