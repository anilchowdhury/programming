package oop.matrixgame.game.chess;

import oop.matrixgame.game.cell.Location;

/**
 * @author Anil Chowdhury
 *         Created on 6/25/2018.
 */
public interface IMoveable {
    void move(Location newLocation);
}
