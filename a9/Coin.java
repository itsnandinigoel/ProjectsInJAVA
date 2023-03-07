package a9;

public class Coin extends Sprite {

    /**
     * A very basic Plant actor. Variants on this class can be created
     * by varying the constructor parameters or by subclassing this.
     */

    private int health;

    public Coin(int xPos, int yPos, int size, String imgPath, int health) {
        super(xPos, yPos, size, imgPath);
        this.health = health;
    }

    /**
     * Return the instance variable, health, of the coin inside a method
     *
     * @return the health of the coin
     */
    public int getHealth() {
        return health;
    }


    /**
     * Modify the health by change value.
     *
     * @param change
     */
    protected void changeHealth(int change) {
        health += change;
    }
}