package oop.matrixgame.game.snakeladder;

import oop.matrixgame.game.cell.Location;

/**
 * @author Anil Chowdhury
 *         Created on 6/25/2018.
 */
public class Stairs {

    private Location stairBase;
    private Location stairTop;

    public Stairs(Location stairBase, Location stairTop) {
        this.stairBase = stairBase;
        this.stairTop = stairTop;
    }
}
