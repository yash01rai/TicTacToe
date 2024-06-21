package Controllers;

import Exceptions.InvalidBotCountException;
import models.Game;
import models.GameState;
import models.Player;
import strategies.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int dimensions, List<Player> players, List<WinningStrategy> winningStrategy) throws InvalidBotCountException {

        return Game.getBuilder()
                .setDimensions(dimensions)
                .setPlayers(players)
                .setWinningStrategy(winningStrategy)
                .build();

    }

    public void displayBoard(Game game){
        game.printBoard();
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public GameState checkState(Game game){
        return game.getGameState();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public void undo(Game game){

    }
}
