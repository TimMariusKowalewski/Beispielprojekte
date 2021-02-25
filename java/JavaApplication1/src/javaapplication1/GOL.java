package javaapplication1;

import java.io.IOException;
import java.util.Random;


/**
 *
 * @author Kowalewski
 */
public class GOL {
    private int x_size;	
    private int y_size;	
    private int x_size_adv;	
    private int y_size_adv;	
    private int z_size;
    private int map[][][];
    private int current_generation = 0;

    public GOL(int x_size, int y_size, int z_size) {
        this.x_size = x_size;
        this.y_size = y_size;
        this.x_size_adv = x_size + 2;
        this.y_size_adv = y_size + 2;
        this.z_size = z_size;
        this.map = new int[this.x_size][this.y_size][this.z_size];
    }
    
    public void print_map() {
        // oberer Rand
        for(int x = 0; x < this.y_size + 2; x++) {
            System.out.print("#");
        }
        System.out.println();
	
        
        for(int x = 0; x < this.x_size; x++) {
            System.out.print("#");
            
            for(int y = 0; y < this.y_size; y++) {
                System.out.print(this.map[x][y][this.current_generation]);
            }
            System.out.println("#");
	}
        
        // unterer Rand
        for(int x = 0; x < this.y_size + 2; x++) {
           System.out.print("#");
        }
        System.out.println();
    }
    
    public void clearScreen() throws IOException, InterruptedException{  
        //System.out.println("\b");
        //System.out.println("asd");
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        //Runtime.getRuntime().exec("cls");
    }
    
    public void init_map() {
        Random random = new Random();
        int randomInteger;
        
        for(int x = 0; x < this.x_size; x++) {
            for(int y = 0; y < this.y_size; y++) {
                randomInteger = random.nextInt(2); // liefert Zahlen 0 o. 1
                //System.out.println(randomInteger);
                this.map[x][y][this.current_generation] = randomInteger;
            }
	}
    }
    
    public void next_generation() {
        for(int x = 0; x < this.x_size_adv; x++) {
            for(int y = 0; y < this.y_size_adv; y++) {
                
                // Randfelder
                if(x > 0 && y > 0 && x <= this.x_size - 2 && y <= this.y_size - 2) {
                
                    int _anzahl_nachbarn = 0;
                    int[] _nachbar_felder = {
                        this.map[x - 1][y][this.current_generation],
                        this.map[x + 1][y][this.current_generation],
                        this.map[x][y - 1][this.current_generation],
                        this.map[x][y + 1][this.current_generation],
                        this.map[x - 1][y - 1][this.current_generation],
                        this.map[x + 1][y + 1][this.current_generation],
                        this.map[x - 1][y + 1][this.current_generation],
                        this.map[x + 1][y - 1][this.current_generation]
                    };

                    for (int i = 0; i < _nachbar_felder.length; i++) {
                        if (_nachbar_felder[i] == 1) {
                            _anzahl_nachbarn++;
                        }
                    }
                    if (this.map[x][y][this.current_generation] == 1) {
                        if (_anzahl_nachbarn < 2) {
                            this.map[x][y][this.current_generation + 1] = 0;
                        }
                        else if (_anzahl_nachbarn == 2 || _anzahl_nachbarn == 3) {
                            this.map[x][y][this.current_generation + 1] = 1;
                        }
                        else if (_anzahl_nachbarn > 3) {
                            this.map[x][y][this.current_generation + 1] = 0;
                        }
                    } else if(this.map[x][y][this.current_generation] == 0) {
                        if (_anzahl_nachbarn == 3) {
                            this.map[x][y][this.current_generation + 1] = 1;
                        }
                    }
                }    
            }    
        }
        this.current_generation++;
    }
    
    public void run(int number_of_generations) {
        for(int x = 0; x < number_of_generations - 1; x++) {
            System.out.println("Generation " + x + ":");
            this.next_generation();
            this.print_map();
            System.out.println("");
            
            try {
                this.clearScreen();
            } catch(Exception e) { 
                
            }
        }
    }   
}