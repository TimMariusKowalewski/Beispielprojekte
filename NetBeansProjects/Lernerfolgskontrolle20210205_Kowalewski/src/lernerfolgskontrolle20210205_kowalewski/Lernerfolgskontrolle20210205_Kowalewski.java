/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lernerfolgskontrolle20210205_kowalewski;

/**
 *
 * @author Kowalewski
 */
public class Lernerfolgskontrolle20210205_Kowalewski {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		PKWFactory _pkwf = new PKWFactory();
		PKW _pkw1 = (PKW)_pkwf.bauen();
		BasisObjekt.dump(_pkw1);
		PKW _pkw2 = (PKW)_pkwf.bauen();
		BasisObjekt.dump(_pkw2);
	}
	
}
