package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Bronze extends Entity {

    public OBJ_Coin_Bronze(GamePanel gp) {
        super(gp);

        type = typePickupOnly;
        name = "Bronze Coin";
        down1 = setup("/Objects/coin_bronze");
        value = 1;
    }
    public void use(Entity entity) {
        gp.playSE(1);
        gp.ui.addMessage("Coin +"+value);
        gp.player.coin += value;
    }
}
