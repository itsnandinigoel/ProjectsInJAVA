package a9;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * A top-level panel for playing a game similar to Plants Vs Zombies.
 * <p>
 * This panel is primarily responsible for coordinating the various
 * aspects of the game, including:
 * - Running the game step-by-step using a timer
 * - Creating and displaying other components that make up the game
 * - Creating new plants and/or zombies, when necessary
 *
 * @author Travis Martin and David Johnson
 * @author Edited by Nandini Goel and Ethan Schrauf
 */

/**
 * Theme: Mario Brothers Tower Defense - a game with different characters from the Mario Brothers Franchise
 * Each character has unique abilities to defend from Bowser's Kingdom.
 */
public class Game extends JPanel implements ActionListener, MouseListener {
    // The final variables that represent the JPanel's rows, columns, buffer pixels, cell size, and step time
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 7;
    private static final int GRID_BUFFER_PIXELS = 20;
    private static final int CELL_SIZE = 75;
    private static final int STEP_TIME = 20;

    // Timer that starts the game
    Timer timer = new Timer(STEP_TIME, this);

    // A default of gift of 10 coins at the start of the game
    private int coinsCollected = 10;

    // A Random generator is created so that enemies(zombies) and coins can randomly appear on the screen when called.
    private final Random generator = new Random();

    // JLabels that appear on the game window that keep the user informed of how to navigate the game and that based on how many coins they have and can buy

    private final JLabel coinsCollectedLabel = new JLabel("Coins Collected: 10");
    private final JLabel instructionLabel = new JLabel("Click button to add plant | Deselect to collect coins!");

    // Buttons to select and switch between characters (plants) to place on the screen when the game begins
    private final JRadioButton marioJRadioButton = new JRadioButton("Mario Cost: 2 Coins");
    private final JRadioButton rosalinaJRadioButton = new JRadioButton("Rosalina Cost: 5 Coins");
    private final ButtonGroup jRadioButtonGroup = new ButtonGroup();

    // Buttons that start the game and unselect plants so the user is able to collect coins
    private final JButton unselectedButton = new JButton("Deselect Plants");
    private final JButton startButton = new JButton("Start");

    // Boolean values representing if various buttons have been clicked to manipulate the game
    private boolean marioButtonIsClicked = false;
    private boolean rosalinaButtonIsClicked = false;
    private boolean startButtonPressed = false;

    /**
     * This panel is responsible for displaying plants
     * and zombies, and for managing their interactions.
     */
    private final ActorDisplay actorDisplay = new ActorDisplay(
            NUM_COLS * CELL_SIZE + GRID_BUFFER_PIXELS * 2,
            NUM_ROWS * CELL_SIZE + GRID_BUFFER_PIXELS * 2);

    /**
     * Game is executed through Game constructor
     */
    private Game() {
        // Adds the background screen at the coordinate (0,0)
        addBackgroundScreen(0, 0);

        // Adds actor display to the JPanel
        add(actorDisplay);

        // Adds the buttons to the display
        add(marioJRadioButton);
        add(rosalinaJRadioButton);
        add(unselectedButton);
        add(startButton);
        jRadioButtonGroup.add(marioJRadioButton);
        jRadioButtonGroup.add(rosalinaJRadioButton);

        // Adds the action/mouse listeners
        startButton.addActionListener(this);
        unselectedButton.addActionListener(this);
        marioJRadioButton.addActionListener(this);
        rosalinaJRadioButton.addActionListener(this);
        actorDisplay.addMouseListener(this);

        // Adds JLabels
        add(instructionLabel);
        add(coinsCollectedLabel);

        // Sets the layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Starts the timer which starts the game
        timer.start();
    }

    /**
     * Create, start, and run the game.
     */
    public static void main(String[] args) {
        JFrame app = new JFrame("Mario Vs. Bowser");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.add(new Game());
        app.pack();
        app.setVisible(true);
    }

    /**
     * Getter to return the value of the number of coins collected
     *
     * @return the number of coinsCollected
     */
    public int getCoinsCollected() {
        return coinsCollected;
    }

    /**
     * Sets the number of coinsCollected
     *
     * @param coinsCollected a new number of coinsCollected to assign or reassign to a default coinsCollected value
     */
    public void setCoinsCollected(int coinsCollected) {
        this.coinsCollected = coinsCollected;
    }

    /**
     * Adds a background screen to the official game grid & display panel.
     *
     * @param col column position in pixels
     * @param row row position in pixels
     */
    private void addBackgroundScreen(int col, int row) {
        actorDisplay.addBackgroundScreen(new BackgroundScreen(0, 0, 620, "/Users/nandinigoel/IdeaProjects/A9/src/a9/Mario objects/background screen.png"));
    }

    /**
     * Adds a Mario (plant) to the official game grid & display panel.
     *
     * @param col column position in pixels
     * @param row row position in pixels
     */
    private boolean addMarioAlly(int col, int row) {
        return actorDisplay.addActor(new Plant(
                gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5,
                "/Users/nandinigoel/IdeaProjects/CS1410Assignments/src/a9/Mario objects/Mario allies/mario ally.png",
                150, 1, 2));
    }

    /**
     * Adds a Rosalina (plant) to the official game grid & display panel.
     *
     * @param col column position in pixels
     * @param row row position in pixels
     */
    private boolean addRosalinaAlly(int col, int row) {
        return actorDisplay.addActor(new RosalinaSpecialAbility(
                gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5,
                "/Users/nandinigoel/IdeaProjects/CS1410Assignments/src/a9/Mario objects/Mario allies/rosalina ally.png",
                150, 5, 10, this));
    }

    /**
     * Adds a Boomba Splitter (zombie) to the official game grid & display panel.
     *
     * @param col column position in pixels
     * @param row row position in pixels
     */
    private void addBoombaSplitterEnemy(int col, int row) {
        actorDisplay.addActor(new BoombaSplitter(
                gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5));
    }

    /**
     * Adds a Wendy (zombie) to the official game grid & display panel.
     *
     * @param col column position in pixels
     * @param row row position in pixels
     */
    private void addWendyEnemy(int col, int row) {
        actorDisplay.addActor(new Zombie(
                gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5,
                "/Users/nandinigoel/IdeaProjects/CS1410Assignments/src/a9/Mario objects/Mario enemies/wendy enemy.png",
                75, 50, -3, 10));
    }

    /**
     * Adds a coin to the screen randomly
     *
     * @param col column position in pixels
     * @param row row position in pixels
     */
    private void addCoin(int col, int row) {
        actorDisplay.addCoins(new Coin(
                gridToPixel(col), gridToPixel(row), (CELL_SIZE * 4 / 5),
                "/Users/nandinigoel/IdeaProjects/CS1410Assignments/src/a9/Mario objects/mario coin.png", 1));
    }

    /**
     * Converts a row or column to its exact pixel location in the grid.
     *
     * @param rowOrCol represents a row or colum position
     * @return the specific pixel position
     */
    private int gridToPixel(int rowOrCol) {
        return rowOrCol * CELL_SIZE + GRID_BUFFER_PIXELS;
    }

    /**
     * The inverse of gridToPixel
     *
     * @param xOrY the x or y position in pixels
     * @return the specific grid position
     */
    private int pixelToGrid(int xOrY) {
        return (xOrY - GRID_BUFFER_PIXELS) / CELL_SIZE;
    }

    /**
     * Adds a game over screen to the actor display
     *
     * @param col column pixel position
     * @param row row pixel position
     */
    private void gameOverScreen(int col, int row) {
        actorDisplay.addGameOverScreen(new GameOver(0, 0, (700),
                "/Users/nandinigoel/IdeaProjects/CS1410Assignments/src/a9/Mario objects/game over screen.png"));
    }

    /**
     * Updates the coins collected label to reflect the amount of coins collected.
     *
     * @param coinsCollected the amount of coins collected.
     */
    public void setCoinLabelText(int coinsCollected) {
        coinsCollectedLabel.setText("Coins collected: " + coinsCollected);
    }

    /**
     * Executes game logic every time the timer ticks.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // Determines if the start button is clicked. If the button is clicked it is removed from the JPanel
        if (e.getSource() == startButton) {
            startButtonPressed = true;
            startButton.setVisible(false);
        }

        // If zombies make it to the other side of the screen the game over screen is displayed and the timer is stopped
        for (Actor actor : actorDisplay.getActors()) {
            if (actor.getXPosition() < 1) {
                timer.stop();
                gameOverScreen(0, 0);
            }
        }

        // If the start button is pressed, the zombies and coins will begin to appear in random locations
        if (startButtonPressed) {
            // Spawns Wendy enemies
            if (generator.nextDouble(100) > 99.6) {
                int randomRow = generator.nextInt(NUM_ROWS);
                addWendyEnemy(7, randomRow);
            }

            // Spawns Boomba enemies
            if (generator.nextDouble(100) > 99.6) {
                int randomRow = generator.nextInt(NUM_ROWS);
                addBoombaSplitterEnemy(7, randomRow);
            }

            // Spawn coins
            if (generator.nextDouble(100) > 96) {
                int randomRow = generator.nextInt(NUM_ROWS);
                int randomColumn = generator.nextInt(2, 7);
                addCoin(randomColumn, randomRow);
            }
        }

        // If the JRadio buttons representing a zombie selection are clicked, the booleans that represent the buttons being clicked change.
        if (e.getSource() == rosalinaJRadioButton) {
            marioButtonIsClicked = false;
            rosalinaButtonIsClicked = true;
        }

        if (e.getSource() == marioJRadioButton) {
            marioButtonIsClicked = true;
            rosalinaButtonIsClicked = false;
        }

        if (e.getSource() == unselectedButton) {
            marioButtonIsClicked = false;
            rosalinaButtonIsClicked = false;
            jRadioButtonGroup.clearSelection();
        }

        // Executes the game actions in actor display
        actorDisplay.step();
    }

    /**
     * Uses mouse clicked position to control various aspects of the game such as buttons, placing enemies, collecting coins, etc.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // Gets the x and y positions from a mouse click
        int x = e.getX();
        int y = e.getY();

        // If the coin is clicked, it is removed from the game and the coins collected instance variable and label changes to reflect this action
        for (Coin coin : actorDisplay.getCoins()) {
            if (coin.isCollidingPoint(x, y)) {
                coin.changeHealth(-1);
                coinsCollected++;
                coinsCollectedLabel.setText("Coins Collected: " + coinsCollected);
            }
        }

        // Snaps where the user clicked to a certain column and row
        int col = pixelToGrid(x);
        int row = pixelToGrid(y);

        // Based on where the user clicks, the mario ally (plant) is placed in this location and money is subtracted accordingly
        if (marioButtonIsClicked) {
            if (coinsCollected >= 2) {
                if (addMarioAlly(col, row)) {
                    coinsCollected -= 2;
                    coinsCollectedLabel.setText("Coins collected: " + coinsCollected);
                }
            }
        }

        // Based on where the user clicks, the rosalina ally (plant) is placed in this location and money is subtracted accordingly
        if (rosalinaButtonIsClicked) {
            if (coinsCollected >= 5) {
                if (addRosalinaAlly(col, row)) {
                    coinsCollected -= 5;
                    coinsCollectedLabel.setText("Coins collected: " + coinsCollected);
                }
            }
        }
    }

    /**
     * Does nothing
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Does nothing
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Does nothing
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Does nothing
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }
}