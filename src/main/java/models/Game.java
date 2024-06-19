package models;

import strategies.WinningStrategy;

import java.util.List;

public class Game {

    private Board board;

    private List<Player> players;

    private int nextPlayerMoveIndex;

    private List<Move> moves;

    private GameState gameState;

    private Player winner;

    private List<WinningStrategy> winningStrategies;

}
