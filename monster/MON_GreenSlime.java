package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import obj.OBJ_Coin_Bronze;
import obj.OBJ_Heart;
import obj.OBJ_Mana;
import obj.OBJ_Rock;

public class MON_GreenSlime extends Entity {

    public MON_GreenSlime(GamePanel gp) {
        super(gp);

        type=typeMonster;
        name = "Green Slime";
        speed=1;
        maxLife=4;
        life=maxLife;

        attack = 5;
        defense = 0;
        exp = 2;
        projectile = new OBJ_Rock(gp);

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
        Random random = new Random();

        if (actionLockCounter == 120) {
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
        int i = random.nextInt(100)+1;
        if (i > 99 && projectile.alive == false && shotAvailableCounter == 0) {
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 30;
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
    public void checkDrop() {
        int i = new Random().nextInt(100) + 1;
        if (i < 50) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        else if (i >=50 && i<75) {
            dropItem(new OBJ_Heart(gp));
        }
        else {
            dropItem(new OBJ_Mana(gp));
        }
    }
}
