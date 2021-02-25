/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Kowalewski
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     * @return int
     */
    public static int get_input() {
        System.out.println("Bitte geben Sie eine weitere Tippzahl ein:");
        Scanner _scan = new Scanner(System.in);
        
        return _scan.nextInt();
    }
    
    public static void main(String[] args) {
        boolean _again = true;
        
        while(_again == true) {
            System.out.println("Willkommen beim Lotto!");

            // Tippzahlen eingeben
            int _count_numbers = 6;
            List<Integer> _my_numbers = new ArrayList();
            //int[] _my_numbers = new int[_count_numbers];
            int _count_entered_numbers = 0;
            
            //System.out.println(_my_numbers.length);
            boolean _is_valid = false;
            while(_count_entered_numbers < _count_numbers) {
                int _value_int = JavaApplication2.get_input();

                if(_value_int >= 1 && _value_int <= 49) {
                    _is_valid = true;
                    _my_numbers.add(_value_int);
                    _count_entered_numbers++;
                    //System.out.println(_value_int);
                } else {
                    _is_valid = false;
                    System.out.println("Ungültiger Wert: " + String.valueOf(_value_int) + "! Nur Zahlen zwischen 1 und 49");
                }
            }

            System.out.println("Ihr Tippzahlen:");
            
            int _count_output = 0;
            for(Integer _number: _my_numbers) {
                if(_count_output != 0) {
                    System.out.print(", ");
                }

                System.out.print(_number);
                _count_output++;
            }
            System.out.println();

            // Auslosung
            List<Integer> _random_numbers = new ArrayList();
            //int[] _random_numbers = new int[_count_numbers];
            int _count_random_numbers = 0;

            Random _random = new Random();
            while(_count_random_numbers < 6) {
                int _random_int = _random.nextInt(50);

                if(_random_int != 0 && _random_numbers.contains(_random_int) == false) {
                    _count_random_numbers++;
                    _random_numbers.add(_random_int);
                    //System.out.println(String.valueOf(_random_int));
                }
            }

            System.out.println("");
            System.out.println("Für die nächste Ziehung wurden folgende Tippzahlen ausgelost:");
            _count_random_numbers = 0;
            for(Integer _random_int: _random_numbers) {
                if(_count_random_numbers != 0) {
                    System.out.print(", ");    
                }

                System.out.print(_random_int);
                _count_random_numbers++;
            }
            System.out.println();

            // Vergleich Tippzahlen und ausgeloste Zahlen
            int _count_matches = 0;
            //int[] _matches = new int[_count_numbers];
            List<Integer> _matches = new ArrayList();
            for(Integer _my_number: _my_numbers) {
                if(_random_numbers.contains(_my_number) == true) {
                    //System.out.println("Match: " + String.valueOf(_my_numbers[i]));
                    _matches.add(_my_number);
                    _count_matches++;
                }
            }

            // Ergebnis
            System.out.println("");
            if(_count_matches == 0) {
                System.out.println("Leider keine Treffer :(");
            } else {
                System.out.println("Anzahl Treffer: " + _count_matches);

                System.out.print("Ihre Gewinnzahlen: ");
                _count_output = 0;
                for(Integer _match: _matches) {
                    if(_count_output != 0) {
                        System.out.print(", ");
                    }
                    System.out.print(String.valueOf(_match));
                    _count_output++;
                }
                System.out.println("");
            }

            // Darstellung als Tippschein
            System.out.println("Ihr vollständiger Tippschein:");
            int _number = 0;
            for(int i = 0; i < 7; i++) {
                for(int j = 0; j < 7; j++) {
                    _number++;
                    if(_matches.contains(_number) == true && _my_numbers.contains(_number) == true) {
                        System.out.print("!|" + _number + "|!" + " ");
                    } else if(_matches.contains(_number) == false && _my_numbers.contains(_number) == true) {
                        System.out.print("|" + _number + "|" + " ");
                    } else {
                        System.out.print(_number + " ");
                    }
                }
                System.out.println("");
            }
            
            // nocheinmal spielen?
            System.out.println("");
            System.out.println("Möchten Sie noch einmal spielen? J für Ja drücken");
            Scanner _scan = new Scanner(System.in);
            String _value = _scan.next();
            String _test = "J";
            if(_test.equals(_value) == false) {
                _again = false;
            }
        }
    }
}
