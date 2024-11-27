package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Mana extends Entity {

    public OBJ_Mana(GamePanel gp) {
        super(gp);

        name = "Mana Crystal";
        type = typePickupOnly;
        value = 1;
        image = setup("/Objects/manacrystal_full");
        image2 = setup("/Objects/manacrystal_blank");
        down1 = image;
    }
    
    public void use(Entity entity) {
        gp.playSE(2);
        gp.ui.addMessage("Mana +" + value);
        entity.mana += value;
    }
}
