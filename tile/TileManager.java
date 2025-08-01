package tile;

import java.awt.Graphics2D;
// import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile=new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/res/My map/worldV2.txt");
    }

    public void getTileImage() {

        setup(0,"grass00",false);          
        setup(1,"grass00",false);          
        setup(2,"grass00",false);          
        setup(3,"grass00",false);          
        setup(4,"grass00",false);          
        setup(5,"grass00",false);          
        setup(6,"grass00",false);          
        setup(7,"grass00",false);          
        setup(8,"grass00",false);          
        setup(9,"grass00",false);
        setup(10,"grass00",false);
        setup(11,"grass01",false);
        setup(12,"water00",true);
        setup(13,"water01",true);
        setup(14,"water02",true);
        setup(15,"water03",true);
        setup(16,"water04",true);
        setup(17,"water05",true);
        setup(18,"water06",true);
        setup(19,"water07",true);
        setup(20,"water08",true);
        setup(21,"water09",true);
        setup(22,"water10",true);
        setup(23,"water11",true);
        setup(24,"water12",true);
        setup(25,"water13",true);
        setup(26,"road00",false);
        setup(27,"road01",false);
        setup(28,"road02",false);
        setup(29,"road03",false);
        setup(30,"road04",false);
        setup(31,"road05",false);
        setup(32,"road06",false);
        setup(33,"road07",false);
        setup(34,"road08",false);
        setup(35,"road09",false);
        setup(36,"road10",false);
        setup(37,"road11",false);
        setup(38,"road12",false);
        setup(39,"earth",false);
        setup(40,"wall",true);
        setup(41,"tree",true);
        

    }
    public void setup(int i , String imagePath , boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[i] = new Tile();
            tile[i].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/New version/"+imagePath+".png"));
            tile[i].image = uTool.scaleImage(tile[i].image, gp.tileSize, gp.tileSize);
            tile[i].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String location){
        try {
            InputStream is = getClass().getResourceAsStream(location);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0,row=0;
            while(col<gp.maxWorldCol && row<gp.maxWorldRow) {
                String line = br.readLine();
                String numbers[] = line.split(" ");
                while(col<gp.maxWorldCol) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++; 
                }
                if (col == gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        int worldRow=0,worldCol=0,tileNum,worldX,worldY,screenX,screenY;
        while(worldRow<gp.maxWorldRow && worldCol<gp.maxWorldCol) {
            tileNum =mapTileNum[worldCol][worldRow];
            worldX = worldCol * gp.tileSize;
            worldY = worldRow * gp.tileSize;
            screenX = worldX - gp.player.worldX +gp.player.screenX;
            screenY = worldY - gp.player.worldY +gp.player.screenY;

            if(worldX +gp.tileSize > gp.player.worldX-gp.player.screenX && worldX - gp.tileSize < gp.player.worldX+gp.player.screenX
                && worldY +gp.tileSize > gp.player.worldY-gp.player.screenY && worldY - gp.tileSize < gp.player.worldY+gp.player.screenY){
                g2.drawImage(tile[tileNum].image,screenX,screenY,null);
            }
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol=0;
                worldRow++;
            }
        }
    }

}
