package obj;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Heart extends SuperObject {

    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {
        this.gp = gp;
        name = "heart";

        image = setup_img("heart_full");
        image2 = setup_img("heart_half");
        image3 = setup_img("heart_blank");
        
    }
    BufferedImage setup_img(String imageName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/res/Objects/"+imageName+".png"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return uTool.scaleImage(img, gp.tileSize, gp.tileSize);
    }

}
