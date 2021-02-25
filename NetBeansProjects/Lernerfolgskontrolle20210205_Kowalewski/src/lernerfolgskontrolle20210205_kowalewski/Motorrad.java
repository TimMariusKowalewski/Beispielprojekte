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
public class Motorrad extends Fahrzeug {

	public Motorrad(String Name) {
		super(Name);
	}

	/* überschriebene Methoden */
	// alle Motorräder haben nur 2 Räder
	@Override
	public void setWheelCount(int WheelCount) {
		this._wheel_count = 2;
	}

}
