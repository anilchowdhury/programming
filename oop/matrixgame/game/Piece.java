package oop.matrixgame.game;

import oop.matrixgame.game.cell.Location;
import oop.matrixgame.game.chess.IMoveable;

/**
 * @author Anil Chowdhury
 *         Created on 6/25/2018.
 */
public abstract class Piece implements IMoveable {

    private Location currentLocation;

    public Piece(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public void move(Location newLocation) {
        currentLocation.setX(newLocation.getX());
        currentLocation.setY(newLocation.getY());
    }
}
