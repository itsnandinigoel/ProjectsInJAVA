package a8;

import java.util.Random;

/**
 * Class that chooses a random RPS value.
 */
public class RandomOpponent implements Opponent {
    /**
     * @param humanMove the move that the human is making this game round.
     *                  it is considered cheating to use this move to
     *                  determine ones move for this round, but this information
     *                  may be used for determining moves in future rounds.
     * @return a random RPS move.
     */
    @Override
    public RPS getResponse(RPS humanMove) {
        Random computerMove = new Random();
        int randomMove = computerMove.nextInt(3);
        RPS randomRPS = null;
        if (randomMove == 0) {
            randomRPS = RPS.ROCK;
        }
        if (randomMove == 1) {
            randomRPS = RPS.PAPER;
        }
        if (randomMove == 2) {
            randomRPS = RPS.SCISSORS;
        }
        return randomRPS;
    }
}
