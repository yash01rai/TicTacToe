package strategies;

import models.Board;
import models.Move;

public interface WinningStrategy {
    public boolean checkWinner(Move move, Board board);
}
