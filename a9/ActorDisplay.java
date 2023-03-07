package a9;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Contains all plants and zombies in this game.
 */
public class ActorDisplay extends JPanel {
    // ArrayLists representing the types of Sprites added to arrayLists
    private ArrayList<Actor> actors = new ArrayList<>();
    private ArrayList<Coin> coins = new ArrayList<>();
    private ArrayList<GameOver> gameOverArrayList = new ArrayList<>();
    private ArrayList<BackgroundScreen> backgroundScreenArrayList = new ArrayList<>();

    /**
     * Creates a canvas upon which all actors will live.
     *
     * @param colPixels the number of pixels that this panel is wide
     * @param rowPixels the number of pixels that this panel is high
     */
    public ActorDisplay(int colPixels, int rowPixels) {
        setPreferredSize(new Dimension(colPixels, rowPixels));
    }

    /**
     * Adds an actor to the master list of actors ONLY IF
     * the provided actor is not colliding with any of the existing
     * actors.
     *
     * @param actor the object to add
     * @return false if something prevents the actor from being added, true otherwise
     */
    public boolean addActor(Actor actor) {
        if (actor.isCollidingAny(actors)) {
            return false;
        }
        actors.add(actor);
        return true;
    }

    /**
     * Adds a coin object to the coins ArrayList if the coin isn't colliding with other coin objects.
     *
     * @param coin a Coin object
     * @return true if the coin isn't colliding with other coins
     */
    public boolean addCoins(Coin coin) {
        if (coin.isCollidingAny(coins)) {
            return false;
        }
        coins.add(coin);
        return true;
    }

    /**
     * Adds the game over screen when a zombie makes it to the other side of the screen.
     *
     * @param gameOver a gameOver screen object
     */
    public void addGameOverScreen(GameOver gameOver) {
        gameOverArrayList.add(gameOver);
    }

    /**
     * Adds a backdrop for the game.
     *
     * @param backgroundScreen a background screen object from a class created by students.
     */
    public void addBackgroundScreen(BackgroundScreen backgroundScreen) {
        backgroundScreenArrayList.add(backgroundScreen);
    }

    /**
     * Return a list of all the actors appearing on the screen.
     *
     * @return an ArrayList of actors
     */
    public ArrayList<Actor> getActors() {
        return actors;
    }

    /**
     * This override method draws the details of this particular panel,
     * including all actors that are contained within.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (BackgroundScreen backgroundScreen : backgroundScreenArrayList) {
            backgroundScreen.draw(g);
        }

        for (Actor actor : actors) {
            actor.draw(g);
        }

        for (Coin coin : coins) {
            coin.draw(g);
        }

        for (GameOver gameOver : gameOverArrayList) {
            gameOver.draw(g);
        }
    }

    /**
     * Executes all the actor logic that happens in one turn, including
     * moving actors, checking for collisions, managing attacks, and more.
     */
    public void step() {
        // Increment actor cool downs.
        for (Actor actor : actors) {
            actor.update();
        }

        // Allow all actors to interact with all other actors.
        // This is where attacks, healing, etc happen.
        for (Actor actor : actors) {
            for (Actor other : actors) {
                actor.actOn(other);
            }
        }

        // Remove plants and zombies with no health
        ArrayList<Actor> nextTurnActors = new ArrayList<>();
        for (Actor actor : actors) {
            if (actor.isAlive())
                nextTurnActors.add(actor);
        }
        actors = nextTurnActors;

        // Makes the game over screen appear when the zombies make it to the left side of the screen.
        ArrayList<GameOver> nextTurnGameOver = new ArrayList<>();
        // Move the (alive) actors that are not colliding.
        for (Actor actor : actors) {
            if (!actor.isCollidingAny(actors)) {
                actor.move();
            }
            if (actor.getXPosition() < 0) {
                nextTurnGameOver.addAll(gameOverArrayList);
            }
        }
        gameOverArrayList = nextTurnGameOver;

        // Makes the coins appear on the screen
        ArrayList<Coin> nextTurnCoins = new ArrayList<>();

        for (Coin coin : coins) {
            if (coin.getHealth() > 0) {
                nextTurnCoins.add(coin);
            }
        }
        coins = nextTurnCoins;

        backgroundScreenArrayList = new ArrayList<>(backgroundScreenArrayList);

        // Redraw the scene.
        repaint();

    }

    /**
     * Creates a getter method to get a collected list of coins that appear on the game screen
     *
     * @return the coins ArrayList
     */
    public ArrayList<Coin> getCoins() {
        return coins;
    }
}