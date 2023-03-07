package a9;

/**
 * Special enemy class. When the Boomba (Zombie) has low health, it splits into two zombies and the health is partially restored.
 */
public class BoombaSplitter extends Zombie {
    private boolean zombieKilled = false;

    /**
     * Creates a zombie. For parameter descriptions, see Actor.
     *
     * @param xPosition the x-coordinate for where the Boomba would be placed
     * @param yPosition the y-coordinate for where the Boomba would be placed
     * @param size      the size of the Boomba
     */
    public BoombaSplitter(int xPosition, int yPosition, int size) {
        super(xPosition, yPosition, size, "/Users/nandinigoel/IdeaProjects/A9/src/a9/Mario objects/Mario enemies/boomba enemy.png", 150, 10, -1, 1);
    }

    /**
     * This actOn overridden method excutes the special ability of the Boomba enemy
     *
     * @param other a Zombie object that represents the Boomba enemy object
     */
    @Override
    public void actOn(Zombie other) {
        if (this.getHealth() <= 10 && !zombieKilled) {
            this.setImgPath("/Users/nandinigoel/IdeaProjects/A9/src/a9/Mario objects/Mario enemies/boomba enemy split.png");
            this.setHealth(100);
            this.setAttackDamage(20);
            zombieKilled = true;
        }
    }
}