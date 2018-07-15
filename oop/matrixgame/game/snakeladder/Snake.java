package oop.matrixgame.game.snakeladder;

import oop.calendar.events.Location;

/**
 * @author Anil Chowdhury
 *         Created on 6/25/2018.
 */
public class Snake {

    private Location snakeHead;
    private Location snakeTail;

    public Snake(Location snakeHead, Location snakeTail) {
        this.snakeHead = snakeHead;
        this.snakeTail = snakeTail;
    }
}
