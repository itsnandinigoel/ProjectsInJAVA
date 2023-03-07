package a8;

/**
 * This class stores the last move that the player made to use as the computer's next move.
 */
public class CopyPreviousOpponent implements Opponent {

    private RPS lastMove = RPS.ROCK;

    /**
     * @param humanMove the move that the human is making this game round.
     *                  it is considered cheating to use this move to
     *                  determine ones move for this round, but this information
     *                  may be used for determining moves in future rounds.
     * @return the previousMove stores the humanMove for the computer's next move
     */
    @Override
    public RPS getResponse(RPS humanMove) {
        RPS previousMove = lastMove;
        lastMove = humanMove;
        return previousMove;
    }
}
