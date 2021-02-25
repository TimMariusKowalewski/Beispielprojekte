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
public class PKW extends Fahrzeug {

	/* Instanzvariablen */
	private int _max_passengers; // Anzahl Insassen

	/* Getter & Setter */
	public int getMaxPassengers() {
		return _max_passengers;
	}

	public void setMaxPassengers(int MaxPassengers) {
		this._max_passengers = MaxPassengers;
	}

	/* Konstruktoren */
	public PKW(String Name) {
		super(Name);
	}

	PKW(String Name, int WheelCount, Color Color) {
		super(Name, WheelCount, Color);
	}

	/* überschriebene Methoden */
	// alle PKW haben 4 Räder
	@Override
	public void setWheelCount(int WheelCount) {
		this._wheel_count = 4;
	}

}
