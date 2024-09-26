package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    int standCounter=0;

    public Player(GamePanel gp , KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2-(gp.tileSize/2);
        screenY = gp.screenHeight/2-(gp.tileSize/2);
        solidArea=new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();

    }
    public void setDefaultValues() {
        worldX=gp.tileSize*23;
        worldY=gp.tileSize*21;
        speed=4;
        direction = "down";
    }
    public void getPlayerImage() {
        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");

    }
    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;
        try {
            scaledImage=ImageIO.read(getClass().getResourceAsStream("/res/player/Walking sprites/"+imageName+".png")); 
            scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
                e.printStackTrace();
        }
        return scaledImage;

    }
    public void update() {
        if(keyH.downPressed == true ||keyH.upPressed == true ||keyH.leftPressed == true ||keyH.rightPressed == true){

            standCounter=0;
            if(keyH.upPressed == true) {
                direction = "up";
            }
            if(keyH.downPressed == true) {
                direction = "down";
            }
            if(keyH.leftPressed == true) {
                direction = "left";
            }
            if(keyH.rightPressed == true) {
                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            if(collisionOn == false){
                switch(direction) {
                    case "up": worldY-=speed;break;
                    case "down": worldY+=speed;break;
                    case "left": worldX-=speed;break;
                    case "right": worldX+=speed;break;
                }
            // if(keyH.upPressed == true) {
            //     worldY-=speed;
            // }
            // if(keyH.downPressed == true) {
            //     worldY+=speed;
            // }
            // if(keyH.leftPressed == true) {
            //     worldX-=speed;
            // }
            // if(keyH.rightPressed == true) {
            //     worldX+=speed;
            // }
            

            }

            spriteCounter++;
            if(spriteCounter>12){
                if(spriteNum==1){
                    spriteNum=2;
                }
                else if(spriteNum==2){
                    spriteNum=1;
                }
                spriteCounter=0;
        }
        }
        else {
            standCounter++;
            if(standCounter == 20) {
                spriteNum=1;
                standCounter=0;
            }
        }

    }
    public void pickUpObject(int i) {

        if(i != 999) {
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    gp.playSE(1);
                    gp.ui.showMessage("You obtained key");
                    break;
                case "Door":
                    if(hasKey > 0) {
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door");
                    }
                    else {
                        gp.ui.showMessage("You need a key");
                    }
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed+=4;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speed up");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }
        }

    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(spriteNum==1)
                    image=up1;
                if(spriteNum==2)
                    image=up2;
                break;
            case "down":
                if(spriteNum==1)
                    image=down1;
                if(spriteNum==2)
                    image=down2;
                break;
            case "left":
                if(spriteNum==1)
                    image=left1;
                if(spriteNum==2)
                    image=left2;
                break;
            case "right":
                if(spriteNum==1)
                    image=right1;
                if(spriteNum==2)
                    image=right2;
                break;
        }
        g2.drawImage(image, screenX,screenY,null);
    }
}
