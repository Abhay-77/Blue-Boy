package obj;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Chest extends SuperObject {

    public OBJ_Chest(GamePanel gp){
        this.gp = gp;
        name="Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/chest.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
