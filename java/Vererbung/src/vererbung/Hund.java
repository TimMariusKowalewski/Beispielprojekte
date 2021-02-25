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
public class Hund extends Wesen implements IVerteidigung {
    
   //public Hund() {
        //super();
    //}
    
    public void Bellen() {
        System.out.println("Wuff!");
    }
    
    /*@Override
    public void Angreifen() {
        System.out.println("Feste Zubeißen!");        
    }*/
    
    @Override
    public void Verteidigen() {
        System.out.println("Zubeißen!");    
    }
    
}
