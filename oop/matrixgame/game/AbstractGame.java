package oop.matrixgame.game;

import oop.matrixgame.Player;

import java.util.List;


/**
 * @author Anil Chowdhury
 *         Created on 6/24/2018.
 */
public abstract class AbstractGame implements IGame {

    private String gameName;
    protected GameState state;
    protected List<Player> players;

    public AbstractGame(String gameName, List<Player> players) {
        this.state = GameState.NOT_STARTED;
        this.gameName = gameName;
        this.players = players;
    }

    @Override
    public String name() {
        return gameName;
    }

    @Override
    public GameState getCurrentGameState() {
        return state;
    }

    @Override
    public void updateGameState(GameState state) {
        this.state = state;
    }

    @Override
    public Player playGame() {
        updateGameState(GameState.IN_PROGRESS);
        Player winner = play();
        updateGameState(GameState.FINISHED);
        return winner;
    }

    @Override
    public boolean isGameOver() {
        return state == GameState.FINISHED;
    }

    public Player getWinner() {
        return isGameOver() ? winner() : Player.NOT_FOUND;
    }

    protected abstract Player winner();

    protected abstract Player play();
}
