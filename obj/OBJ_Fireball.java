package obj;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile {
    public OBJ_Fireball(GamePanel gp) {
        super(gp);

        name = "Fireball";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    void getImage() {
        up1 = setup("/Projectile/fireball_up_1");
        up2 = setup("/Projectile/fireball_up_2");
        down1 = setup("/Projectile/fireball_down_1");
        down2 = setup("/Projectile/fireball_down_2");
        left1 = setup("/Projectile/fireball_left_1");
        left2 = setup("/Projectile/fireball_left_2");
        right1 = setup("/Projectile/fireball_right_1");
        right2 = setup("/Projectile/fireball_right_2");
    }
    public boolean haveResource(Entity user) {
        return user.mana >= useCost;
    }
    public void subtractResource(Entity user) {
        user.mana -= useCost;
    }
}
