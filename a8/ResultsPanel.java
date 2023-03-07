package a8;

import javax.swing.*;

/**
 * This class displays the updating content of the chosen moves of the human and
 * the computer in a panel.
 */
public class ResultsPanel extends JPanel {
    private JLabel humanChoiceText;
    private JLabel computerChoiceText;
    private JLabel winnerText;

    /**
     * Creates the constructor that displays the results of both games of RPS
     * in a panel in the middle of the dialogue box,
     * showing the content of what move a human makes and for what move the computer makes,
     * also displaying the winner after comparison.
     */
    public ResultsPanel() {
        // Creates the text

        humanChoiceText = new JLabel("human choice: no selection");
        computerChoiceText = new JLabel("computer choice: no selection");
        winnerText = new JLabel("winner: none");

        // Adds the text to the panel;
        add(humanChoiceText);
        add(computerChoiceText);
        add(winnerText);
    }

    /**
     * Stores and updates the results of the game based on the moves made by the human and the computer.
     * @param human RPS user move
     * @param computer RPS computer move
     * @param winner stores a default winner string value until updated based on comparison between human move and computer move
     */
    public void updateResults(RPS human, RPS computer, String winner) {
        // Updates the JLabels that should store the human's moves
        if (human == RPS.ROCK) {
            humanChoiceText.setText("human choice: rock");
        } else if (human == RPS.PAPER) {
            humanChoiceText.setText("human choice: paper");
        } else {
            humanChoiceText.setText("human choice: scissors");
        }

        // Updates the JLabels that should store the computer's moves
        if (computer == RPS.ROCK) {
            computerChoiceText.setText("computer choice: rock");
        } else if (computer == RPS.PAPER) {
            computerChoiceText.setText("computer choice: paper");
        } else {
            computerChoiceText.setText("computer choice: scissors");
        }
        winnerText.setText(winner);
    }
}