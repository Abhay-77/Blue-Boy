package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        type = typeSword;
        name = "Normal Sword";
        down1 = setup("/Objects/sword_normal");
        attackValue = 1;
        description = "[" + name + "]\nAn old sword.";
        attackArea.width = 36;
        attackArea.height = 36;

    }

}
