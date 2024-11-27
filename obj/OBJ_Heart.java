package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp) {
        super(gp);

        name = "Heart";
        value = 2;
        type = typePickupOnly;
        image = setup("/Objects/heart_full");
        image2 = setup("/Objects/heart_half");
        image3 = setup("/Objects/heart_blank");
        down1 = image;

    }

    public void use(Entity entity) {
        gp.playSE(2);
        gp.ui.addMessage("Life +" + value);
        entity.life += value;
    }

}
