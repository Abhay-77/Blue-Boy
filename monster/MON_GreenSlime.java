package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_GreenSlime extends Entity {

    public MON_GreenSlime(GamePanel gp) {
        super(gp);

        type=2;
        name = "Green Slime";
        speed=1;
        maxLife=4;
        life=maxLife;

        attack = 5;
        defense = 0;
        exp = 2;

        solidArea.x = 4;
        solidArea.y = 19;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y; 

        getImage();
    }

    public void getImage() {
        up1 = setup("/Monster/greenslime_down_1");
        up2 = setup("/Monster/greenslime_down_2");
        down1 = setup("/Monster/greenslime_down_1");
        down2 = setup("/Monster/greenslime_down_2");
        left1 = setup("/Monster/greenslime_down_1");
        left2 = setup("/Monster/greenslime_down_2");
        right1 = setup("/Monster/greenslime_down_1");
        right2 = setup("/Monster/greenslime_down_2");
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else {
                direction = "right";
            }

            actionLockCounter = 0;
        }

    }

    public void damageReaction() {
        actionLockCounter = 0;
        switch(gp.player.direction) {
            case "up":  direction="down";break;
            case "down":  direction="up";break;
            case "left":  direction="right";break;
            case "right":  direction="left";break;
        }
    }
}
