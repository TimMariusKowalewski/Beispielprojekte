package lernerfolgskontrolle20210205_kowalewski;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kowalewski
 */
abstract public class Antrieb {
	abstract public void Vorwaerts();
	abstract public void Rueckwaerts();
	abstract public void Tanken();
	abstract public String getKraftstoff();
}
