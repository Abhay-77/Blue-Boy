package obj;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Rock extends Projectile {
    public OBJ_Rock(GamePanel gp) {
        super(gp);

        name = "Rock";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 0;
        alive = false;
        getImage();
    }
    void getImage() {
        up1 = setup("/Projectile/rock_down_1");
        up2 = setup("/Projectile/rock_down_1");
        down1 = setup("/Projectile/rock_down_1");
        down2 = setup("/Projectile/rock_down_1");
        left1 = setup("/Projectile/rock_down_1");
        left2 = setup("/Projectile/rock_down_1");
        right1 = setup("/Projectile/rock_down_1");
        right2 = setup("/Projectile/rock_down_1");
    }
    public boolean haveResource(Entity user) {
        return user.ammo >= useCost;
    }
    public void subtractResource(Entity user) {
        user.ammo -= useCost;
    }
}
