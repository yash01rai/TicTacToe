package models;

import Exceptions.InvalidBotCountException;
import strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private Board board;

    private List<Player> players;

    private int nextPlayerMoveIndex;

    private List<Move> moves;

    private GameState gameState;

    private Player winner;

    private List<WinningStrategy> winningStrategies;


    // constructor
    private Game(int dimensions, List<Player> players, List<WinningStrategy> winningStrategies) {

        this.board = new Board(dimensions);
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerMoveIndex = 0;
        this.winner = null;
        this.moves = new ArrayList<>();

    }

    // builder pattern
    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private int dimensions;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public Builder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategy(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder addPlayer(Player player) {
            this.players.add(player);
            return this;
        }

        public Builder addWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategies.add(winningStrategy);
            return this;
        }

        public void validate() throws InvalidBotCountException {
            // validate bot count
            int botCount = 0;
            for(Player player : players) {
                if(player.getPlayerType().equals(PlayerType.BOT)) {
                    botCount++;
                }
            }

            if(botCount > 1) {
                throw new InvalidBotCountException();
            }

            if(players.size() != dimensions - 1) {
                throw new RuntimeException();
            }

            Map<Character, Integer> symbolCounts = new HashMap<>();
            for(Player player : players) {
                if(!symbolCounts.containsKey(player.getSymbol().getSymbol())){
                    symbolCounts.put(player.getSymbol().getSymbol(), 1);
                } else {
                    throw new RuntimeException();
                }
            }
        }

        public Game build() throws InvalidBotCountException {

            // validation
            validate();
            return new Game(this.dimensions, this.players, this.winningStrategies);
        }
    }

    // getter and setter
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public void printBoard() {
        board.printBoard();
    }

    public boolean validMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row >= board.getSize()) {
            return false;
        }

        if(col >= board.getSize()) {
            return false;
        }

        return board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    public boolean checkWinner(Board board, Move move) {
        for(WinningStrategy winningStrategy : winningStrategies) {
            if(winningStrategy.checkWinner(move, board)){
                return true;
            }
        }
        return false;
    }

    public void makeMove() {
        Player currentMovePlayer = players.get(nextPlayerMoveIndex);
        System.out.println("It is " + currentMovePlayer.getName() + "'s turn. Please make your move");
        Move move = currentMovePlayer.makeMove(board);

        if(!validMove(move)) {
            System.out.println("It is not a valid move! Please try again.");
            return;
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cellToBeUpdated = board.getBoard().get(row).get(col);
        cellToBeUpdated.setCellState(CellState.FILLED);
        cellToBeUpdated.setPlayer(currentMovePlayer);

        Move finalMoveObject = new Move(cellToBeUpdated, currentMovePlayer);
        moves.add(finalMoveObject);

        nextPlayerMoveIndex++;
        nextPlayerMoveIndex %= players.size();

        if(checkWinner(board, finalMoveObject)){
            this.gameState = GameState.SUCCESS;
            this.winner = currentMovePlayer;

        } else if(moves.size() == board.getSize() * board.getSize()) {
            this.gameState = GameState.DRAW;
        }

        System.out.println("Player " + currentMovePlayer.getName() + "moved at " + move.getCell().getRow() + ", " + move.getCell().getCol());
    }

}
