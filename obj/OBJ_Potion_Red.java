package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);

        type = typeConsumable;
        name = "Red Potion";
        down1 = setup("/Objects/potion_red");
        value = 5;
        description = "[" + name + "]\nHeals your life by " + value + ".";
    }
    public void use(Entity entity) {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You have drunk the " + name + "!\nYour life has been recovered by " 
        + value + ".";
        entity.life += value;
        gp.playSE(2);
    }
}
