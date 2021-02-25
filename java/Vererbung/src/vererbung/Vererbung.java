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
public class Vererbung {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Basisklasse Wesen
        // Subklassen Hunde, Katzen
        // paar Objekte bauen
        
        Hund h1 = new Hund();
        Katze k1 = new Katze();
        
        h1.setID("Hundi");
        System.out.println(h1.getID());
        h1.Bellen();
        
        k1.setID("Mimii");
        System.out.println(k1.getID());
        k1.Miauen();
        
        Katze.my_static_wesen_property = "asd";
        Katze.my_static_katzen_property = "foo";
        System.out.println(Katze.my_static_katzen_property);
        //System.out.println(Hund.my_static_katzen_property); <-- Exception, weil nur in Katze
        System.out.println(Hund.my_static_wesen_property);
        System.out.println(Wesen.my_static_wesen_property);
        
        int z1 = 5;
        //k1.Angreifen();
        //h1.Angreifen();
        k1.Verteidigen();
        h1.Verteidigen();
        System.out.println(Katze.VERTEIDIGUNGS_FAKTOR);
        System.out.println(h1.VERTEIDIGUNGS_FAKTOR);
        //h1.VERTEIDIGUNGS_FAKTOR = 30F;
        
        Wesen w1 = new Katze();
        // Katze k2 = new Wesen(); <-- funktioniert nicht
        //w1.Angreifen();
        //w1.Verteidigen() <-- fehlt weil nur in den Subklassen definiert
        
        Wesen w2 = new Wesen();
        w2.Bewegen();
        w1.Bewegen();
        k1.Bewegen();
        
        
        
    }
    
}
