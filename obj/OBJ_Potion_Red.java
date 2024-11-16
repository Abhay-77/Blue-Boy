package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {
    int value = 5;
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);

        type = typeConsumable;
        name = "Red Potion";
        down1 = setup("/Objects/potion_red");
        description = "[" + name + "]\nHeals your life by " + value + ".";
    }
    public void use(Entity entity) {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You have drunk the " + name + "!\nYour life has been recovered by " 
        + value + ".";
        entity.life += value;
        if (gp.player.life > gp.player.maxLife) {
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(2);
    }
}
