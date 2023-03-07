package a8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This panel contains components and logic related to displaying and selecting
 * Rock Paper Scissors buttons.
 *
 */
public class UserPanel extends JPanel implements ActionListener {
    private Battle parent;
    private JButton rockButton;
    private JButton paperButton;
    private JButton scissorsButton;

    /** The constructor displays the Rock, Paper, and Scissors buttons and is able to recognize when buttons are clicked
     * with the action listener.
     *
     * @param parent If a button is clicked, the results are sent to the parent.playGame() where the winner is chosen
     *               and the results are updated.
     */
    public UserPanel(Battle parent) {
        super();
        this.parent = parent;

        // Creates the rock, paper, and scissors buttons
        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");
        add(rockButton);
        add(paperButton);
        add(scissorsButton);

        // Adds an action listener that determines when the buttons are pressed.
        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);
    }


    /**
     * Handles events from the JButtons to determine which button is pressed, so it can be sent to the parent.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rockButton) {
            parent.playGame(RPS.ROCK);
        } else if (e.getSource() == paperButton) {
            parent.playGame(RPS.PAPER);
        } else {
            parent.playGame(RPS.SCISSORS);
        }
    }
}

