/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Output;

/**
 *
 * @author Kowalewski
 */
public class Console2D {

    /* Instanzvariablen */
    final private int _rows;
    final private int _columns;

    /* Klassenvariablen */
    final static private String DEFAULT_CHAR = " ";

    final private String[][] _map;

    public int getRows() {
        return this._rows;
    }

    public int getColumns() {
        return this._columns;
    }

    public Console2D(int Rows, int Columns) {
        this._rows = Rows;
        this._columns = Columns;
        this._map = new String[Rows][Columns];
        this.init();
    }

    final public void init() {
        for (int _row = 0; _row < this.getRows(); _row++) {
            for (int _column = 0; _column < this.getColumns(); _column++) {
                this._map[_row][_column] = Console2D.DEFAULT_CHAR;
            }
        }
    }

    final public void draw() {
        String _output = "";
        for (int _row = 0; _row < this.getRows(); _row++) {
            for (int _column = 0; _column < this.getColumns(); _column++) {
                _output += this._map[_row][_column];
            }
            _output += "\n";
        }

        System.out.print(_output);
    }

    final public void drawConsoleHeader() {
        System.out.println("Map: " + this.getRows() + "x" + this.getColumns());
    }

    final public void clear() {
        for (int _row = 0; _row < this.getRows(); _row++) {
            for (int _column = 0; _column < this.getColumns(); _column++) {
                this._map[_row][_column] = "";
            }
        }
    }

    // Zeichen an einer bestimmten Position der Matrix setzen - ohne Farbangabe
    final public void setAtPos(int Row, int Column, String Character) {
        this.setAtPos(Row, Column, Character, "DEFAULT");
    }

    // Zeichen an einer bestimmten Position der Matrix setzen - mit Farbangabe
    final public void setAtPos(int Row, int Column, String Character, String Color) {
        //System.out.println("asd" + Row + Column + Character);
        if (Row < this.getRows() && Column < this.getColumns()) {
            Character = String.valueOf(Character.charAt(0));
            this._map[Row][Column] = Character;
            switch (Color) {
                case "PINK":
                    this._map[Row][Column] = "\u001b[35m" + this._map[Row][Column] + "\033[0m";
                    break;
                case "PINK_BACKGROUND":
                    this._map[Row][Column] = "\u001b[45m" + this._map[Row][Column] + "\033[0m";
                    break;
                case "BLUE":
                    this._map[Row][Column] = "\u001b[34m" + this._map[Row][Column] + "\033[0m";
                    break;
                case "BLUE_BACKGROUND":
                    this._map[Row][Column] = "\u001b[44m" + this._map[Row][Column] + "\033[0m";
                    break;
                case "RED":
                    this._map[Row][Column] = "\u001b[31m" + this._map[Row][Column] + "\033[0m";
                    break;
                case "RED_BACKGROUND":
                    this._map[Row][Column] = "\u001b[41m" + this._map[Row][Column] + "\033[0m";
                    break;
                case "GREEN":
                    this._map[Row][Column] = "\u001b[32m" + this._map[Row][Column] + "\033[0m";
                    break;
                case "GREEN_BACKGROUND":
                    this._map[Row][Column] = "\u001b[42m" + this._map[Row][Column] + "\033[0m";
                    break;
                case "YELLOW":
                    this._map[Row][Column] = "\u001b[33m" + this._map[Row][Column] + "\033[0m";
                    break;
                case "YELLOW_BACKGROUND":
                    this._map[Row][Column] = "\u001b[43m" + this._map[Row][Column] + "\033[0m";
                    break;
                default:
                    break;
            }
        } else {
            throw new Error("Illegaler Index: " + "Row - " + Row + " | Column - " + Column);
        }
    }
}
