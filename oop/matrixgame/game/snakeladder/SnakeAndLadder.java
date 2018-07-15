package oop.matrixgame.game.snakeladder;

import oop.matrixgame.Player;
import oop.matrixgame.game.AbstractGame;

import java.util.List;

/**
 * @author Anil Chowdhury
 *         Created on 6/24/2018.
 */
public class SnakeAndLadder extends AbstractGame {

    public SnakeAndLadder(String gameName, List<Player> players) {
        super(gameName, players);
    }

    @Override
    public Player play() {
        return Player.NOT_FOUND;
    }

    @Override
    public Player winner() {
        return Player.NOT_FOUND;
    }
}
