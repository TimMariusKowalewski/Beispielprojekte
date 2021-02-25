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
public class LKW extends Fahrzeug {

	/* Instanzvariablen */
	private float _max_load; // maximale Nutzlast
	private int _axis_count; // Anzahl Achsen

	/* Getter & Setter */
	public float getMaxLoad() {
		return _max_load;
	}

	public void setMaxLoad(float MaxLoad) {
		if (MaxLoad <= 0) {
			throw new Error("LKW._max_load darf nicht kleiner/gleich 0 sein!");
		} else {
			this._max_load = MaxLoad;
		}
	}

	public int getAxisCount() {
		return _axis_count;
	}

	public void setAxisCount(int AxisCount) {
		if (AxisCount < 1) {
			throw new Error("LKW._axis_count muss grösser 1 sein!");
		} else {
			this._axis_count = AxisCount;
		}
	}
	
	/* Konstruktoren */
	public LKW(String Name) {
		super(Name);
	}

	public LKW(String Name, float MaxLoad, int AxisCount) {
		this(Name);
		this.setMaxLoad(MaxLoad);
		this.setAxisCount(AxisCount);
	}

}
