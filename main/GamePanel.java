package main;

import java.awt.Color;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import obj.SuperObject;
import tile.TileManager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth=maxScreenCol *tileSize;
    public final int screenHeight=maxScreenRow *tileSize;
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    // public final int worldWidth = maxScreenCol * tileSize;
    // public final int worldHeight = maxScreenRow * tileSize;

    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public Player player = new Player(this, keyH);
    TileManager tileM = new TileManager(this);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];
    public EventHandler eHandler = new EventHandler(this);

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    int fps = 60;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setUpGame() {
        aSetter.setObject();
        aSetter.setNPC();
        // playMusic(0);
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/fps;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null) {
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime<0) {
                    remainingTime=0;
                }
                Thread.sleep( (long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }        
    }
    public void update() {
        if(gameState == playState) {
            player.update();
            for(int i=0;i<npc.length;i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if(gameState == pauseState) {

        }
        if(gameState == dialogueState) {

        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        long drawStart=System.nanoTime();

        if(gameState == titleState) {

            ui.draw(g2);

        }
        else{
            tileM.draw(g2);
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2);
                }
            }
            player.draw(g2);
            ui.draw(g2);

        }

        
        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;
        System.out.println("Draw time : "+passed);
        g2.dispose();
    }
    public void playMusic(int i) {

        music.setFile(i);
        music.play();
        music.loop();

    }
    public void stopMusic() {
        music.stop();
    }
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
