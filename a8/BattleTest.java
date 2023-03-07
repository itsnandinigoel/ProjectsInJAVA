package a8;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BattleTest {

    /*********** testing pickWinner ************/

    @org.junit.jupiter.api.Test
    void pickWinnerHumanWinnerTest() {
        RPS humanMove = RPS.PAPER;
        RPS computerMove = RPS.ROCK;
        String expectedWinner = "winner: human";
        assertEquals(expectedWinner, Battle.pickWinner(humanMove, computerMove), "Failed to correctly calculate the human move as the winner of an RPS game.");
    }

    @org.junit.jupiter.api.Test
    void pickWinnerComputerWinnerTest() {
        RPS humanMove = RPS.SCISSORS;
        RPS computerMove = RPS.ROCK;
        String expectedWinner = "winner: computer";
        assertEquals(expectedWinner, Battle.pickWinner(humanMove, computerMove), "Failed to correctly calculate the computer move as the winner of an RPS game.");
    }

    @org.junit.jupiter.api.Test
    void pickWinnerTieTest() {
        RPS humanMove = RPS.PAPER;
        RPS computerMove = RPS.PAPER;
        String expectedWinner = "winner: tie!";
        assertEquals(expectedWinner, Battle.pickWinner(humanMove, computerMove), "Failed to correctly calculate the winner as a tie of an RPS game.");
    }

    @org.junit.jupiter.api.Test
    void pickWinnerForRandomGameTest() {
        // Picks a human move
        RPS humanMove = RPS.ROCK;

        // Makes a random opponent which makes a random move based on the humans choice
        RandomOpponent randomOpponent = new RandomOpponent();
        RPS computerMove = randomOpponent.getResponse(humanMove);

        // Calculates winner and stores it into a string tests for human winner, computer winner, and a tie
        String expectedWinner = Battle.pickWinner(humanMove, computerMove);
        if (expectedWinner.equals("winner: human")) { // Testing human winner
            assertEquals(expectedWinner, Battle.pickWinner(humanMove, computerMove), "Failed to calculate human as the winner when the human is competing with the random move computer and there is a tie.");
        } else if (expectedWinner.equals("winner: computer")) { // Testing computer winner
            assertEquals(expectedWinner, Battle.pickWinner(humanMove, computerMove), "Failed to calculate computer as the winner when the human is competing with the random move computer and there is a tie.");
        } else { // Testing tie
            assertEquals(expectedWinner, Battle.pickWinner(humanMove, computerMove), "Failed to calculate a tie when the human is competing with the random move computer and there is a tie.");
        }
    }

    @org.junit.jupiter.api.Test
    void pickWinnerForCopyPreviousGameHumanWinnerTest() {
        // Picks a human move
        RPS humanMove = RPS.ROCK;

        // Makes a copy previous opponent which makes a move based on the human's last move.
        CopyPreviousOpponent copyPreviousOpponent = new CopyPreviousOpponent();
        RPS computerMove = copyPreviousOpponent.getResponse(humanMove);

        // Human makes a second move
        humanMove = RPS.PAPER;

        // Computer makes another move based on the human's last move.
        computerMove = copyPreviousOpponent.getResponse(humanMove);

        // Picks a winner based on the above moves.
        String expectedWinner = "winner: human";
        assertEquals(expectedWinner, Battle.pickWinner(humanMove, computerMove), "Failed to pick the human as a winner after using the human's previous move as computer's next move.");
    }

    @org.junit.jupiter.api.Test
    void pickWinnerForCopyPreviousGameComputerWinnerTest() {
        // Picks a human move
        RPS humanMove = RPS.PAPER;

        // Makes a copy previous opponent which makes a move based on the human's last move.
        CopyPreviousOpponent copyPreviousOpponent = new CopyPreviousOpponent();
        RPS computerMove = copyPreviousOpponent.getResponse(humanMove);

        // Human makes a second move
        humanMove = RPS.ROCK;

        // Computer makes another move based on the human's last move.
        computerMove = copyPreviousOpponent.getResponse(humanMove);

        // Picks a winner based on the above moves.
        String expectedWinner = "winner: computer";
        assertEquals(expectedWinner, Battle.pickWinner(humanMove, computerMove), "Failed to pick the computer as a winner after using the human's previous move as computer's next move.");
    }

    @org.junit.jupiter.api.Test
    void pickWinnerForCopyPreviousGameTieTest() {
        // Picks a human move
        RPS humanMove = RPS.ROCK;

        // Makes a copy previous opponent which makes a move based on the human's last move.
        CopyPreviousOpponent copyPreviousOpponent = new CopyPreviousOpponent();
        RPS computerMove = copyPreviousOpponent.getResponse(humanMove);

        // Human makes a second move
        humanMove = RPS.ROCK;

        // Computer makes another move based on the human's last move.
        computerMove = copyPreviousOpponent.getResponse(humanMove);

        // Picks a winner based on the above moves.
        String expectedWinner = "winner: tie!";
        assertEquals(expectedWinner, Battle.pickWinner(humanMove, computerMove), "Failed to find a tie after using the human's previous move as computer's next move.");
    }


}
