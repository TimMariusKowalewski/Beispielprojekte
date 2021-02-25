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
public class Katze extends Wesen implements IVerteidigung {
    public static String my_static_katzen_property;
    
    public Katze() {
        //super();
    }
    
    public void Miauen() {
        System.out.println("Miau!");
    }
    
    /*@Override
    public void Angreifen() {
        System.out.println("Kralleneinsatz!");
    }*/
    
    @Override
    public void Verteidigen() {
        System.out.println("Noch mehr Kralleneinsatz!!!!1111");
    }
    
    @Override
    public void Bewegen() {
        System.out.println("Ich bin eine Katze");
    }
}
