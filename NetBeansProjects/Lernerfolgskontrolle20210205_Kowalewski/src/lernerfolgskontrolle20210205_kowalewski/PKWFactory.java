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
public class PKWFactory extends FahrzeugFactory {
	@Override
	public Fahrzeug bauen() {
		PKW _pkw = new PKW("neu", 0, Color.YELLOW);
		Antrieb _diesel = new Diesel();
		_pkw.setDrive(_diesel);
		
		return _pkw;
	}
}
