package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
    public GamePanel gp;
    public String name;
    public int worldX, worldY;
    public int speed;
    public String direction = "down";

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1,
        attackRight2;
    public BufferedImage image, image2, image3;

    public Rectangle solidArea = new Rectangle(0, 16, 48, 40);
    public Rectangle attackArea = new Rectangle(0,0,0,0 );
    public int solidAreaDefaultX, solidAreaDefaultY;

    public int spriteNum = 1;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int dialogueIndex = 0;
    String dialogue[] = new String[20];
    
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean attacking = false;
    public boolean collison = false;
    boolean hpBarOn = false;
    
    public int actionLockCounter;
    int hpBarCounter = 0;
    public int spriteCounter = 0;

    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;

    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int value = 5;

    public int type;
    public final int typePlayer = 0;
    public final int typeNpc = 1;
    public final int typeMonster = 2;
    public final int typeSword = 3;
    public final int typeAxe = 4;
    public final int typeShield = 5;
    public final int typeConsumable = 6;
    public final int typePickupOnly = 7;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void speak() {

        if (dialogue[dialogueIndex] == null)
            dialogueIndex = 0;
        gp.ui.currentDialogue = dialogue[dialogueIndex++];

        switch (gp.player.direction) {
            case "up":
                this.direction = "down";
                break;
            case "down":
                this.direction = "up";
                break;
            case "left":
                this.direction = "right";
                break;
            case "right":
                this.direction = "left";
                break;

            default:
                break;
        }

    }

    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;
        try {
            scaledImage = ImageIO.read(getClass().getResourceAsStream("/res" + imagePath + ".png"));
            scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImage;

    }
    public BufferedImage setup(String imagePath , int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;
        try {
            scaledImage = ImageIO.read(getClass().getResourceAsStream("/res" + imagePath + ".png"));
            scaledImage = uTool.scaleImage(scaledImage, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImage;

    }

    public void setAction() {
    }
    public void damageReaction() {}
    public void checkDrop() {}
    public void dropItem(Entity droppedItem) {
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] == null) {
                gp.obj[i] = droppedItem;
                droppedItem.worldX = worldX;
                droppedItem.worldY = worldY;
                break;
            }
        }
    }
    public void update() {

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == typeMonster && contactPlayer == true) {
            damagePlayer(attack);
        }

        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }

        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (shotAvailableCounter > 0) {
            shotAvailableCounter--;
        }
    }
    void damagePlayer(int attack) {
        if (gp.player.invincible == false && dying == false) {
            gp.playSE(6);
            int damage = attack - gp.player.defense;
            if (damage < 0) {
                damage = 0;
            }
            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        // g2.fillRect(screenX+solidArea.x,screenY+ solidArea.y, solidArea.width, solidArea.height);

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "up":
                    if (spriteNum == 1)
                        image = up1;
                    if (spriteNum == 2)
                        image = up2;
                    break;
                case "down":
                    if (spriteNum == 1)
                        image = down1;
                    if (spriteNum == 2)
                        image = down2;
                    break;
                case "left":
                    if (spriteNum == 1)
                        image = left1;
                    if (spriteNum == 2)
                        image = left2;
                    break;
                case "right":
                    if (spriteNum == 1)
                        image = right1;
                    if (spriteNum == 2)
                        image = right2;
                    break;
            }

            if (type ==2 && hpBarOn == true) {
                hpBarCounter++;
                double oneScale = (double)gp.tileSize/maxLife;
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX - 1, screenY - 15, gp.tileSize + 2, 12);

                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);
                if (hpBarCounter > 200) {
                    hpBarCounter = 0;
                    hpBarOn=false;
                }
            }

            if (invincible == true) {
                hpBarOn=true;
                hpBarCounter = 0;
                changeAlpha(g2, .5f);
            }
            if (dying == true) {
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, null);
            changeAlpha(g2, 1f);
        }
    }

    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        int  i = 5;

        if (dyingCounter <= i) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter> i && dyingCounter <= i*2) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter> i * 2 && dyingCounter <= i * 3) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter> i * 3 && dyingCounter <= i * 4) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter> i * 4 && dyingCounter <= i * 5) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter> i * 5 && dyingCounter <= i * 6) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter> i * 6 && dyingCounter <= i * 7) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter> i * 7 && dyingCounter <= i * 8) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter> i * 8) {
            alive = false;
        }
        
    }
    public void changeAlpha(Graphics2D g2 , float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public void use(Entity entity) {}
}
