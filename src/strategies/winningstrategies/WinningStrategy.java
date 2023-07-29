package strategies.winningstrategies;

import models.Board;
import models.Player;

public interface WinningStrategy {
    Player checkWinner(Board board);
}
