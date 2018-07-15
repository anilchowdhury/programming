package oop.matrixgame.game.chess;

import oop.matrixgame.Player;
import oop.matrixgame.game.AbstractGame;

import java.util.List;

/**
 * @author Anil Chowdhury
 *         Created on 6/25/2018.
 */
public class Chess extends AbstractGame {

    public Chess(String gameName, List<Player> players) {
        super(gameName, players);
    }

    @Override
    protected Player play() {
        return Player.NOT_FOUND;
    }

    @Override
    public Player winner() {
        return Player.NOT_FOUND;
    }
}
