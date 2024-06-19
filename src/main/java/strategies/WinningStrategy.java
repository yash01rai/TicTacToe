package strategies;

import models.Board;
import models.Player;

public interface WinningStrategy {
    public boolean checkWinner(Player player, Board board);
}
