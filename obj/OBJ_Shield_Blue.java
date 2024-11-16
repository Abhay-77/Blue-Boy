package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Blue extends Entity {

    public OBJ_Shield_Blue(GamePanel gp) {
        super(gp);

        type = typeShield;
        name = "Blue Shield";
        down1 = setup("/Objects/shield_blue");
        defenseValue = 2;
        description = "[" + name + "]\nA shiny blue shield.";
    }

}
