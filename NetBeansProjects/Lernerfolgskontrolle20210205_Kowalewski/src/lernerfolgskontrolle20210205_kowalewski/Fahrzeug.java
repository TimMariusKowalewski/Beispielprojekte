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
public abstract class Fahrzeug extends BasisObjekt
{

	/* Instanzvariablen */
	protected int _wheel_count; // Anzahl Räder


	/* Getter & Setter */
	public int getWheelCount()
	{
		return _wheel_count;
	}

	public void setWheelCount(int WheelCount)
	{
		this._wheel_count = WheelCount;
	}

	public Fahrzeug(String Name)
	{
		super(Name);
	}

//	public Fahrzeug(int Test)
//	{
//		super(Test);
//	}

	public Fahrzeug(String Name, int WheelCount, Color Color)
	{
		this(Name);
		this.setWheelCount(WheelCount);
		this.setColor(Color);
	}

}
