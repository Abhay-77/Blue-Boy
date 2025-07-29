package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class IT_DryTree extends InteractiveTile {

    public IT_DryTree(GamePanel gp, int col, int row) {
        super(gp, col, row);

        down1 = setup("/tiles_interactive/drytree", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;
    }

    public boolean isCorrectItem(Entity entity) {
        return entity.currentWeapon.type == typeAxe;
    }

    public InteractiveTile getDestroyedForm() {
        return new IT_Trunk(gp, this.worldX, this.worldY);
    }

    public void playSE() {
        gp.playSE(11);
    }

}
