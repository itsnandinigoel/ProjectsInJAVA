package a9;

/**
 * Creates an object that represents a game over screen.
 */

public class GameOver extends Sprite {

    /**
     * Creates a new sprite.
     *
     * @param xPos    the starting x pixel (0 is the left)
     * @param yPos    the starting y pixel (0 is the top)
     * @param size    the initial dimensions of this actor (width and height).
     *                this is used both for the display size of this sprite
     *                as well as its size for collision detection, unless
     *                isColliding is overridden.
     * @param imgPath a path to the image file for this actor's picture
     */
    public GameOver(int xPos, int yPos, int size, String imgPath) {
        super(xPos, yPos, size, imgPath);
    }
}