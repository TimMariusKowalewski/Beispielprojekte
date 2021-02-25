/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Output;

/**
 *
 * @author Kowalewski
 *
 * VT100 Farbcodes
 */
public enum ConsoleColor {
    RESET("\033[0m"),
    DEFAULT("\u001b[35m"),
    BLACK("\u001b[35m"),
    PINK("\u001b[35m"),
    PINK_BACKGROUND("\u001b[45m"),
    BLUE("\u001b[34m"),
    BLUE_BACKGROUND("\u001b[44m"),
    RED("\u001b[31m"),
    RED_BACKGROUND("\u001b[41m"),
    GREEN("\u001b[32m"),
    GREEN_BACKGROUND("\u001b[42m"),
    GRAY("\u001b[32m"),
    GRAY_BACKGROUND("\u001b[42m"),
    WHITE("\u001b[32m"),
    WHITE_BACKGROUND("\u001b[42m"),
    YELLOW("\u001b[33m"),
    YELLOW_BACKGROUND("\u001b[43m");

    private final String _color;

    ConsoleColor(String Color) {
        this._color = Color;
    }

    public String getColor() {
        return this._color;
    }
}
