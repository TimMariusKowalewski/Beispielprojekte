/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Kowalewski
 */
public class JavaApplication3 {    
    public static void print_map(List Map) {
        int _count = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.print(Map.get(_count) + " ");
                _count++;
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static boolean check_winner(List Map) {
        boolean _return = false;
        
        // Zeilen passend gefüllt
        if(
                Map.get(0).equals(Map.get(1)) == true
                && Map.get(0).equals(Map.get(2)) == true
                && Map.get(0).equals("_") == false
                && Map.get(1).equals("_") == false
                && Map.get(2).equals("_") == false
        ) {
            _return = true;
        }
        if(
                Map.get(3).equals(Map.get(4)) == true
                && Map.get(3).equals(Map.get(5)) == true
                && Map.get(3).equals("_") == false
                && Map.get(4).equals("_") == false
                && Map.get(5).equals("_") == false
        ) {
            _return = true;
        }
        if(
                Map.get(6).equals(Map.get(7)) == true
                && Map.get(6).equals(Map.get(8)) == true
                && Map.get(6).equals("_") == false
                && Map.get(7).equals("_") == false
                && Map.get(8).equals("_") == false
        ) {
            _return = true;
        }
        
        // Spalten passend gefüllt
        if(
                Map.get(0).equals(Map.get(3)) == true
                && Map.get(0).equals(Map.get(6)) == true
                && Map.get(0).equals("_") == false
                && Map.get(3).equals("_") == false
                && Map.get(6).equals("_") == false
        ) {
            _return = true;
        }
        if(
                Map.get(1).equals(Map.get(4)) == true
                && Map.get(1).equals(Map.get(7)) == true
                && Map.get(1).equals("_") == false
                && Map.get(4).equals("_") == false
                && Map.get(7).equals("_") == false
        ) {
            _return = true;
        }
        if(
                Map.get(2).equals(Map.get(5)) == true
                && Map.get(2).equals(Map.get(8)) == true
                &&  Map.get(2).equals("_") == false
                && Map.get(5).equals("_") == false
                && Map.get(8).equals("_") == false
        ) {
            _return = true;
        }
        
        // Diagonalen passend gefüllt
        if(
                Map.get(0).equals(Map.get(4)) == true
                && Map.get(0).equals(Map.get(8)) == true
                &&  Map.get(0).equals("_") == false
                &&  Map.get(4).equals("_") == false
                &&  Map.get(8).equals("_") == false
        ) {
            _return = true;
        }
        if(
                Map.get(2).equals(Map.get(4)) == true
                && Map.get(2).equals(Map.get(6)) == true
                &&  Map.get(2).equals("_") == false
                &&  Map.get(4).equals("_") == false
                &&  Map.get(6).equals("_") == false
        ) {
            _return = true;
        }
        
        return _return;
    }
    
    public static boolean check_deadlock(List Map) {
        boolean _return = true;
        
        // alle Felder gefüllt?
        for(int i = 0; i < 9; i++) {
            if(Map.get(i).equals("_") == true) {
                _return = false;
                break;
            }
        }

        return _return;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Spielernamen abfragen
        Scanner _scan = new Scanner(System.in);
        System.out.println("Bitte den Namen von Spieler 1 eingeben:");
        String _player1 = _scan.next();
        String _player1_token;
        System.out.println("");
        System.out.println("Bitte den Namen von Spieler 2 eingeben:");
        String _player2 = _scan.next();
        String _player2_token;
        
        // Auslosen, wer X und wer O ist
        Random _random = new Random();
        int _random_int = _random.nextInt(1);
        if(_random_int == 0) {
            _player1_token = "X";
            _player2_token = "O";
        } else {
            _player1_token = "O";
            _player2_token = "X";
        }
        _player1 = _player1 + " (" + _player1_token + ")";
        _player2 = _player2 + " (" + _player2_token + ")";
        
        System.out.println("");
        System.out.println("Es spielt " + _player1 + " gegen " + _player2);
        System.out.println("");
        
        // Spielfeld anlegen
        List<Integer> _used_fields = new ArrayList<Integer>();
        List<String> _map = new ArrayList<String>();
        for(int i = 1; i <= 9; i++) {
            _map.add("_");
        }
        
        // Startspielfeld
        JavaApplication3.print_map(_map);
        
        boolean _game_has_ended = false;
        while(_game_has_ended == false) {
            
            // Zug Spieler 1
            boolean _player1_valid_input = false;
            while(_player1_valid_input == false) {
                System.out.println(_player1 + " ist am Zug: Welches Feld? (1-9)");
                int _field_choice = _scan.nextInt();
                
                if(_field_choice >= 1 && _field_choice <= 9 && _used_fields.contains(_field_choice) == false) {
                    _map.set(_field_choice - 1, _player1_token);
                    _used_fields.add(_field_choice);
                    _player1_valid_input = true;
                    
                }
            }
            
            // Hat Spieler 1 bereits gewonnen?
            JavaApplication3.print_map(_map);
            _game_has_ended = JavaApplication3.check_winner(_map);
            if(_game_has_ended == true) {
                System.out.println(_player1 + " hat gewonnen!");
                break;
            }
            
            // Ergibt sich durch den Zug von Spieler 1 ein Deadlock?
            _game_has_ended = JavaApplication3.check_deadlock(_map);
            if(_game_has_ended == true) {
                System.out.println("Spiel vorbei!");
                break;
            }
            
            // Zug Spieler 2
            boolean _player2_valid_input = false;
            while(_player2_valid_input == false) {
                System.out.println(_player2 + " ist am Zug: Welches Feld? (1-9)");
                int _field_choice = _scan.nextInt();

                if(_field_choice >= 1 && _field_choice <= 9 && _used_fields.contains(_field_choice) == false) {
                    _map.set(_field_choice - 1, _player2_token);
                    _used_fields.add(_field_choice);
                    _player2_valid_input = true;
                }
            }
            
            // Hat Spieler 2 bereits gewonnen?
            JavaApplication3.print_map(_map);
            _game_has_ended = JavaApplication3.check_winner(_map);
            if(_game_has_ended == true) {
                System.out.println(_player2 + " hat gewonnen!");
                break;
            }

            // Ergibt sich durch den Zug von Spieler 2 ein Deadlock?
            _game_has_ended = JavaApplication3.check_deadlock(_map);
            if(_game_has_ended == true) {
                System.out.println("Spiel vorbei!");
                break;
            }
        }
    }
}