package oop.matrixgame;

/**
 * @author Anil Chowdhury
 *         Created on 6/24/2018.
 */
public class Player {
    private String name;
    private int id;
    public static final Player NOT_FOUND = new Player("", -1);

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
