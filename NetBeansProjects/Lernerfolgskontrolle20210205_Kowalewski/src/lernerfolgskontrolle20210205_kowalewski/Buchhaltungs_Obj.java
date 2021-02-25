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
public interface Buchhaltungs_Obj {

	public int getBaujahr();

	public String getKraftstoff();

	public void makiereAlsAngeboten(String datum);

	public void makiereAlsVerkauft(String datum, float preis);
}
