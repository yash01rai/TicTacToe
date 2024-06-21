package strategies;

import models.Board;
import models.Move;
import models.Player;

public class DiagonalWinningStrategy implements WinningStrategy{

    @Override
    public boolean checkWinner(Move move, Board board) {
        return false;
    }
}
