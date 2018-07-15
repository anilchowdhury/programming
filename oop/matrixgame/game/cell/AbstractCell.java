package oop.matrixgame.game.cell;

/**
 * @author Anil Chowdhury
 *         Created on 6/24/2018.
 */
public abstract class AbstractCell {

    protected Location location;
    private String label;

    public AbstractCell(Location location) {
        this(location, "");
    }

    public AbstractCell(Location location, String label) {
        this.location = location;
        this.label = label;
    }

    public int getX() {
        return location.getX();
    }

    public int getY() {
        return location.getY();
    }
}
