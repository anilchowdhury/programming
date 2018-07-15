package oop.matrixgame.game;

import oop.matrixgame.Player;

/**
 * @author Anil Chowdhury
 *         Created on 6/24/2018.
 */
public interface IGame {
    String name();
    Player playGame();
    GameState getCurrentGameState();
    void updateGameState(GameState state);
    boolean isGameOver();
    Player getWinner();
}
