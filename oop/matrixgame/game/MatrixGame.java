package oop.matrixgame.game;

import com.sun.istack.internal.NotNull;
import oop.matrixgame.Player;
import oop.matrixgame.game.cell.GamingCell;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Anil Chowdhury
 *         Created on 6/24/2018.
 */
public class MatrixGame extends AbstractGame implements MultiGame {

    private GamingCell[][] board;
    private int boardRow, boardColumn, nextGameCellX, nextGameCellY;
    private int[][] scores;

    public MatrixGame(String gameName, @NotNull List<Player> players, @NotNull GamingCell[][] board) {
        super(gameName, players);
        if (players.size() != 2) {
            //also validate the same set of players are present in each of the GamingCell
            throw new RuntimeException("Only two players can play the game");
        }
        boardRow = board.length;
        boardColumn = board[0].length;
        this.board = board;
        scores = new int[boardRow][boardColumn];
        Arrays.fill(scores, Player.NOT_FOUND.getId());
        nextGameCellX = 0;
        nextGameCellY = -1;
    }

    @Override
    public void playNextGame() {
        if (getCurrentGameState() == GameState.NOT_STARTED) {
            updateGameState(GameState.IN_PROGRESS);
        }
        if (getCurrentGameState() == GameState.FINISHED) {
            System.out.println("Game is already finished");
            System.out.println(String.format("Winner is %s = ", winner().getName()));
        }
        nextGameCellY = nextGameCellY + 1;
        if (nextGameCellY == boardColumn) {
            nextGameCellY = 0;
            nextGameCellX = (nextGameCellX + 1) % boardRow;
        }
        play();
    }

    @Override
    public Player play() {
        String gameName = board[nextGameCellX][nextGameCellY].getGameName();
        System.out.println(String.format("Starting next Game --> %s", gameName));
        Player winner;
        try {
            winner = board[nextGameCellX][nextGameCellY].playGame();
            updateScore(board[nextGameCellX][nextGameCellY], winner);
            System.out.println(String.format("Winner of game %s is %s", gameName, winner.getName()));
            return winner;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return Player.NOT_FOUND;
    }

    private void updateScore(GamingCell cell, Player player) {
        scores[cell.getX()][cell.getY()] = player.getId();
        if (getWinner() != Player.NOT_FOUND) {
            updateGameState(GameState.FINISHED);
        }
    }

    @Override
    public Player winner() {
        Player winner = searchInRow();
        if (winner == Player.NOT_FOUND) {
            winner = searchInColumn();
            if (winner == Player.NOT_FOUND) {
                winner = searchDiagonally();
            }
        }
        return winner;
    }

    private Player searchInRow() {
        boolean winnerFound = true;
        int winnerID = -1;

        for (int[] score : scores) {
            int id = score[0];
            for (int j = 0; j < scores[0].length; j++) {
                if (id != score[j]) {
                    winnerFound = false;
                    break;
                }
            }
            if (winnerFound) {
                winnerID = id;
                break;
            }
        }
        return getWinner(winnerID);
    }

    private Player searchInColumn() {
        boolean winnerFound = true;
        int winnerID = -1;

        for (int j = 0; j < scores[0].length; j++) {
            int id = scores[0][j];
            for (int[] score : scores) {
                if (id != score[j]) {
                    winnerFound = false;
                    break;
                }
            }
            if (winnerFound) {
                winnerID = id;
                break;
            }
        }
        return getWinner(winnerID);
    }

    private Player searchDiagonally() {
        int winnerID = scores[0][0];
        for (int i = 0; i < scores.length; i++) {
            if (winnerID != scores[i][i]) {
                winnerID = -1;
                break;
            }
        }
        if (winnerID != -1) {
            return getWinner(winnerID);
        }
        winnerID = scores[0][scores.length - 1];
        for (int i = scores.length - 1; i >= 0; i--) {
            if (winnerID != scores[i][i]) {
                winnerID = -1;
                break;
            }
        }
        return getWinner(winnerID);
    }

    private Player getWinner(int id) {
        Player winner = Player.NOT_FOUND;
        for (Player player : players) {
            if (player.getId() == id) {
                winner = player;
                break;
            }
        }
        return winner;
    }
}
