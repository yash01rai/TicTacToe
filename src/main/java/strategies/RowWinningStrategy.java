package strategies;

import models.Board;
import models.Player;

public class RowWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWinner(Player player, Board board) {
        return false;
    }
}
