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
public abstract class Flugzeug extends BasisObjekt {

	/* Instanzvariablen */
	protected int _turbine_count; // Anzahl Turbinen

	public Flugzeug(String Name) {
		super(Name);
	}

}
