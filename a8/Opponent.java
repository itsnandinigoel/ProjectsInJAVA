package a8;

/**
 * A computer player for the game Rock Paper Scissors.
 * <p>
 * This player must implement the getResponse method, which responds
 * to some Rock Paper Scissors move with a move of its own.
 */
public interface Opponent {
    /**
     * Returns a Rock Paper Scissors move for the given input move.
     *
     * @param humanMove the move that the human is making this game round.
     *                  it is considered cheating to use this move to
     *                  determine ones move for this round, but this information
     *                  may be used for determining moves in future rounds.
     * @return the Rock Paper Scissors move of this player for the current round.
     */
    RPS getResponse(RPS humanMove);
}
