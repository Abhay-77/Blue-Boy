package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class Particle extends Entity {

    Entity generator;
    Color color;
    int size;
    int xd;
    int yd;
    int offset;

    public Particle(GamePanel gp, Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
        super(gp);

        this.generator = generator;
        this.color = color;
        this.speed = speed;
        this.maxLife = maxLife;
        this.xd = xd;
        this.yd = yd;
        this.size = size;

        offset = gp.tileSize / 2 - size / 2;

        life = maxLife;
        worldX = generator.worldX;
        worldY = generator.worldY;

    }

    public void update() {
        life--;

        if (life < maxLife / 3) {
            yd += 1;
        }

        worldX += xd * speed;
        worldY += yd * speed;

        if (life == 0) {
            alive = false;
        }
    }

    public void draw(Graphics2D g2) {
        int screenY = worldY - gp.player.worldY + gp.player.screenY + offset;
        int screenX = worldX - gp.player.worldX + gp.player.screenX + offset;

        g2.setColor(color);
        g2.fillRect(screenX, screenY, size, size);
    }

}
