package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

    public OBJ_Axe(GamePanel gp) {
        super(gp);

        type = typeAxe;
        name = "Axe";
        down1 = setup("/Objects/axe");
        attackValue = 2;
        description = "[Woodcutter's Axe]\nCan cut trees.";
    }

}
