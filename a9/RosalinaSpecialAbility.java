package a9;

public class RosalinaSpecialAbility extends Plant {
    private final Game game;
    private boolean coinVisible = false;

    /**
     * Creates a plant. For parameter descriptions, see Actor.
     *
     * @param xPosition    the x position of location to place Rosalina at
     * @param yPosition    the y position of location to place Rosalina at
     * @param size         the size of Rosalina
     * @param imgPath      sets the image reference to get Rosalina to actually show up
     * @param health       the int form of Rosalina's health
     * @param coolDown     the speed at which the attacks happen in number form
     * @param attackDamage how much damage is done to the opponent in number form
     */
    public RosalinaSpecialAbility(int xPosition, int yPosition, int size, String imgPath, int health, int coolDown, int attackDamage, Game game) {
        super(xPosition, yPosition, size, imgPath, health, coolDown, attackDamage);
        this.game = game;
    }

    /**
     * Executes the special ability of Rosalina, which just turns her into a star when her health is low.
     * When she is turned into a star, a coin bonus is provided to the user.
     *
     * @param other a Plant / a Rosalina plant for comparison
     */
    @Override
    public void actOn(Plant other) {
        if (this.getHealth() <= 20 && !coinVisible) {
            this.setImgPath("/Users/nandinigoel/IdeaProjects/CS1410Assignments/src/a9/Mario objects/mario star.png");
            this.setHealth(20);
            this.setAttackDamage(0);
            coinVisible = true;
        }
        if (coinVisible) {
            game.setCoinsCollected(20);
            game.setCoinLabelText(game.getCoinsCollected());
        }
    }
}