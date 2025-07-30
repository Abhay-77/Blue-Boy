package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity {

    public boolean destructible = false;

    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);

        this.worldX = col;
        this.worldY = row;

        invincibleEffect = false;
    }

    public boolean isCorrectItem(Entity entity) {
        return true;
    }

    public void update() {

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }

    public InteractiveTile getDestroyedForm() {
        return null;
    }

    public void playSE() {
    }
}
