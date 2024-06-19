import Controllers.GameController;
import Exceptions.InvalidBotCountException;
import models.Game;
import models.GameState;

import java.util.ArrayList;

public class Client {
    public static void main(String[] args) throws InvalidBotCountException {

        GameController gameController = new GameController();

        Game game1 = gameController.startGame(3, new ArrayList<>(), new ArrayList<>());

        gameController.displayBoard(game1);

        while(gameController.checkState(game1).equals(GameState.IN_PROGRESS)) {
            gameController.makeMove(game1);
            gameController.displayBoard(game1);
        }

        if(gameController.checkState(game1).equals(GameState.DRAW)) {
            System.out.println("Draw");
        } else if(gameController.checkState(game1).equals(GameState.SUCCESS)) {
            System.out.println("Winner is " + gameController.getWinner(game1).getName());
        }

    }
}
