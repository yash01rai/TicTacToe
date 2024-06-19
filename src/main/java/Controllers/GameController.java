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
//        game.getBoard().printBoard();
    }
    public void makeMove(Game game){

    }

    public GameState checkState(Game game){
        return null;
    }

    public Player getWinner(Game game){
        return null;
    }

    public void undo(Game game){

    }
}
