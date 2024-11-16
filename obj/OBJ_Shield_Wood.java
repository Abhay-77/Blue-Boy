package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity {

    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);

        type = typeShield;
        name = "Wood Shield";
        down1 = setup("/Objects/shield_wood");
        defenseValue = 1;
        description = "[" + name + "]\nMade with wood.";
    }

}
