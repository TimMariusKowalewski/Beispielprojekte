/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Output;

import java.awt.Color;

/**
 *
 * @author Kowalewski
 *
 * JFrame-Farbcodes
 */
public enum JFrameColor {
    //RESET("\033[0m"),
    DEFAULT(Color.BLACK),
    BLACK(Color.BLACK),
    PINK(Color.PINK),
    PINK_BACKGROUND(Color.PINK),
    BLUE(Color.BLUE),
    BLUE_BACKGROUND(Color.BLUE),
    RED(Color.RED),
    RED_BACKGROUND(Color.RED),
    GREEN(Color.GREEN),
    GREEN_BACKGROUND(Color.GREEN),
    GRAY(Color.GRAY),
    GRAY_BACKGROUND(Color.GRAY),
    WHITE(Color.WHITE),
    WHITE_BACKGROUND(Color.WHITE),
    YELLOW(Color.YELLOW),
    YELLOW_BACKGROUND(Color.YELLOW);

    private final Color _color;

    JFrameColor(Color Color) {
        this._color = Color;
    }

    public Color getColor() {
        return this._color;
    }
}
