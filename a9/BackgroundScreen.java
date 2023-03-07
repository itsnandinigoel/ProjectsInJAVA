package a9;

/**
 * Creates an object that represents the backdrop of the game
 */
public class BackgroundScreen extends Sprite {

    /**
     * Creates a backdrop with the qualities of a sprite.
     *
     * @param xPos    x position
     * @param yPos    y position
     * @param size    the size as an int value
     * @param imgPath a String that represents what image appears as the backdrop
     */
    public BackgroundScreen(int xPos, int yPos, int size, String imgPath) {
        super(xPos, yPos, size, imgPath);
    }
}