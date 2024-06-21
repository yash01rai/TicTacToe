import Controllers.GameController;
import Exceptions.InvalidBotCountException;
import models.*;
import strategies.ColWiningStrategy;
import strategies.DiagonalWinningStrategy;
import strategies.RowWinningStrategy;
import strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws InvalidBotCountException {

        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();

        try {
            int gameSize = 3;
            List<Player> players = new ArrayList<>();

            players.add(new Player(1, "Kabir", PlayerType.HUMAN, new Symbol('$')));
            players.add(new Bot(2, "Jarvis", new Symbol('O'), BotDifficultyLevel.EASY));

            List<WinningStrategy> winningStrategies = List.of(
                    new ColWiningStrategy(),
                    new RowWinningStrategy(),
                    new DiagonalWinningStrategy()
            );

            // starting game
            Game game = gameController.startGame(gameSize, players, winningStrategies);
            gameController.displayBoard(game);

            while(gameController.checkState(game).equals(GameState.IN_PROGRESS)) {
                gameController.makeMove(game);
                gameController.displayBoard(game);
            }

            System.out.println("This Game is Over!");
            GameState gameState = gameController.checkState(game);

            if(gameState.equals(GameState.DRAW)) {
                System.out.println("This one is a Draw!");
            } else if (gameState.equals(GameState.SUCCESS)) {
                System.out.println("The winner is " + gameController.getWinner(game).getName());
            }

        } catch (Exception error) {
            throw error;
        }

//        Initial testing
//        System.out.println("Welcome to TicTacToe!");
//
//        Game game1 = gameController.startGame(3, new ArrayList<>(), new ArrayList<>());
//
//        gameController.displayBoard(game1);
//
//
//        // how to take the input
//        while(gameController.checkState(game1).equals(GameState.IN_PROGRESS)) {
//            gameController.makeMove(game1);
//
//
//            gameController.displayBoard(game1);
//
//        }
//
//        if(gameController.checkState(game1).equals(GameState.DRAW)) {
//            System.out.println("Draw");
//        } else if(gameController.checkState(game1).equals(GameState.SUCCESS)) {
//            System.out.println("Winner is " + gameController.getWinner(game1).getName());
//        }

    }
}
