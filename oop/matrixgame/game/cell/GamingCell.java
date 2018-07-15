package oop.matrixgame.game.cell;


import oop.matrixgame.Player;
import oop.matrixgame.game.IGame;

import java.util.concurrent.ExecutionException;

/**
 * @author Anil Chowdhury
 *         Created on 6/24/2018.
 */
public class GamingCell extends AbstractCell {

    private IGame game;

    public GamingCell(Location location, IGame game) {
        super(location);
        this.game = game;
    }

    public GamingCell(Location location, String label, IGame game) {
        super(location, label);
        this.game = game;
    }

    public Player playGame() throws ExecutionException, InterruptedException {
        return game.playGame();
    }

    public String getGameName() {
        return game.name();
    }

}
