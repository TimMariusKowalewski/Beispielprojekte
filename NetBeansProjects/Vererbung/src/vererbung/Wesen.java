/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vererbung;

/**
 *
 * @author Kowalewski
 */
class Wesen {
    private String _id;
    public static String my_static_wesen_property;
    
    public String getID() {
        return this._id;
    }
    
    public void setID(String Str) {
        this._id = Str;
    }
    
//    abstract void Angreifen();
    
    public void Bewegen() {
        System.out.println("Ich bin ein Wesen");
    }
    
    /*public Wesen() {
        
    }*/
}
