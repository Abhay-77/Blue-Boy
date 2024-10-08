package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {


    public OBJ_Heart(GamePanel gp) {
        super(gp);
        name = "Heart";

        image = setup("/Objects/heart_full");
        image2 = setup("/Objects/heart_half");
        image3 = setup("/Objects/heart_blank");
        
    }

}
