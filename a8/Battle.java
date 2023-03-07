package a8;

import javax.swing.*;
import java.awt.*;

/**
 * This is the main class to create a GUI for playing
 * Rock Paper Scissors (RPS).
 * <p>
 * This GUI has 3 main functionalities:
 * 1. A panel for toggling the type of computer opponent. The
 * computer opponent is the person who the user of this GUI
 * will be playing against, and different opponents may use
 * different strategies for playing their moves.
 * 2. A panel for displaying the results of each round of RPS.
 * This panel displays the move that the human made,
 * the move that the computer made, and this round's winner.
 * 3. A panel allowing the human user to choose a RPS move. When
 * this move is chosen, the currently selected computer opponent
 * makes its move, and then the results are updated accordingly.
 */
public class Battle extends JFrame {
    OpponentPanel opponentPanel;
    ResultsPanel resultsPanel;
    UserPanel userPanel;

    /**
     * Adds three subpanels to the main game panel. The opponents panel toggles the type of computer opponent, the second
     * panel displays the results of each round of RPS, and the third panel allows the human to select an RPS move.
     */
    public Battle() {
        super("Battle");

        // Left panel
        opponentPanel = new OpponentPanel();

        // Middle panel
        resultsPanel = new ResultsPanel();

        // Right panel
        userPanel = new UserPanel(this);

        // Adds the opponent panel to the screen
        add(opponentPanel);

        // Adds text stating the player move, the computers move, and the winner
        add(resultsPanel);

        // Creates new layout
        resultsPanel.setLayout(new GridLayout(3, 1, 10, 10));

        // Adds the user panel to the screen.
        add(userPanel);

        // Creates and sets the layout
        userPanel.setLayout(new GridLayout(3, 1, 10, 0));
        setLayout(new GridLayout(1, 3, 10, 10));

        // Ends the program when the application is exited.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();

    }

    /**
     * Returns a string that contains who the winner between the human
     * and computer is based on the moves each player makes, thus returning a tie
     * if both players' moves are the same, or with different moves, which player wins between the human and the computer.
     *
     * @param human    the human's move
     * @param computer the computer's move
     * @return a string containing the winner between human and computer
     */
    public static String pickWinner(RPS human, RPS computer) {
        // Rock computer moves
        if (human == RPS.ROCK && computer == RPS.ROCK) {
            return "winner: tie!";
        }
        if (human == RPS.PAPER && computer == RPS.ROCK) {
            return "winner: human";
        }
        if (human == RPS.SCISSORS && computer == RPS.ROCK) {
            return "winner: computer";
        }

        // Paper computer moves
        if (human == RPS.ROCK && computer == RPS.PAPER) {
            return "winner: computer";
        }
        if (human == RPS.PAPER && computer == RPS.PAPER) {
            return "winner: tie!";
        }
        if (human == RPS.SCISSORS && computer == RPS.PAPER) {
            return "winner: human";
        }

        // Scissors computer moves
        if (human == RPS.ROCK && computer == RPS.SCISSORS) {
            return "winner: human";
        }
        if (human == RPS.PAPER && computer == RPS.SCISSORS) {
            return "winner: computer";
        }
        if (human == RPS.SCISSORS && computer == RPS.SCISSORS) {
            return "winner: tie!";
        }
        return null;
    }

    /**
     * Main method that runs the battle game.
     */
    public static void main(String[] args) {
        Battle b = new Battle();
        b.setVisible(true);
    }

    /**
     * Based on the human choice, a winner is picked and this information is displayed on the results panel.
     *
     * @param humanChoice The RPS button that the human selects.
     */
    public void playGame(RPS humanChoice) {
        // This stores the computer's move based on the human's last move.
        RPS computerMove = opponentPanel.getOpponent().getResponse(humanChoice);

        // Picks a winner for both, the RandomOpponent game, and the CopyPreviousOpponent game.
        String winner = pickWinner(humanChoice, computerMove);

        // Updates the results panel to display the human's move, the computer's move, and the winner
        resultsPanel.updateResults(humanChoice, computerMove, winner);
    }
}