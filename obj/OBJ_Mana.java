package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Mana extends Entity {

    public OBJ_Mana(GamePanel gp) {
        super(gp);

        name = "Mana Crystal";
        image = setup("/Objects/manacrystal_full");
        image2 = setup("/Objects/manacrystal_blank");
    }
}
