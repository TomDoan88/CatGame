import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class CatGame extends GameEngine {
    public static void main(String args[]){
        createGame(new CatGame(), 60);
    }


    /*
    READ ME BEFORE YOU START............
    NOTE THE GAME DIMENSION IS 1150 WIDTH AND 650 - I MADE THE CHANGES TO THE GAME ENGINE ITSELF
    You would need to import the game engine from my Github or attempt to change the dimension explicitly.
    */

    //==============================================BACKGROUND PICTURE==============================
    Image backGround;


    //==============================================GRASS ISLAND TILE INITIALIZATION AND FUNCTION ============

    //Note each grass tile takes up 50x50x on screen we are chaining them together to create a long island terrain

    Image terrainSpriteSheet;
    Image terrainImage;

    // Class for Terrain
    public class TerrainElement {
        public int x, y, pixelWidth, pixelHeight;

        public TerrainElement(int x, int y, int pixelWidth, int pixelHeight) {
            this.x = x;
            this.y = y;
            this.pixelWidth = pixelWidth;
            this.pixelHeight = pixelHeight;
        }
    }

    // Adding TerrainElement class into an arrayList
    private List<TerrainElement> terrainElements = new ArrayList<>(19);


    // Initializing x y co ordinates for each tile in the arrayList
    public void initTerrain(){
        terrainSpriteSheet = loadImage("Assets/Terrain/Singular.png");
        terrainImage = subImage(terrainSpriteSheet,0 , 0, 16, 16);

        // These are co-ordinates for terrain islands. There are 5 islands in totals consist of 24 tiles
        // Island 1 -> 5 start from top left down and moving right to up
        // Running this for loop to generate and update x y co-ordinates so engine knows where to draw
        int counter = 0;
        int x = 0;
        int y = 120;
        for(int i = 0; i <= 23; i++){
            int pixelWidth = 50;
            int pixelHeight = 50;
            if(counter <= 5){ // Adding 6 tiles to island 1
                terrainElements.add(new TerrainElement(x, y, pixelWidth, pixelHeight));
                x += 50;
                counter++;
                System.out.println("X = " + x);
                System.out.println("COUNTER = " + counter);
            } else if(counter <= 9){ // Adding 4 tiles to island 2;
                if(counter == 6){
                    x = 0; // Reset x to 0
                }
                y = 350; // Set y to 350
                terrainElements.add(new TerrainElement(x, y, pixelWidth, pixelHeight));
                x += 50; // Increment x by 50
                counter++;
            } else if(counter <= 13){ // Adding 4 tiles to island 3
                if(counter == 10){
                    x = 350;
                    y = 460;
                }
                terrainElements.add(new TerrainElement(x, y, pixelWidth, pixelHeight));
                x+=50;
                counter++;
            } else if(counter <= 17){ // Adding 4 tiles to island 4
                if(counter == 14){
                    x = 700;
                    y = 350;
                }
                terrainElements.add(new TerrainElement(x, y, pixelWidth, pixelHeight));
                x+=50;
                counter++;
            } else if(counter <= 23 ){ // Adding 6 tiles to island 5
                if(counter == 18){
                    x = 850;
                    y = 200;
                }
                terrainElements.add(new TerrainElement(x, y, pixelWidth, pixelHeight));
                x += 50;
                counter++;
            }
        }
    }

    public void drawTerrain(){
        for(TerrainElement element: terrainElements){
            drawImage(terrainImage, element.x, element.y, element.pixelWidth, element.pixelHeight);
        }

    }

    //======================================PORTAL INITIALIZATION AND FUNCTION=====================================

    Image portalSpriteSheet;
    Image[] portalImages = new Image[203];

    public void initPortal(){
        portalSpriteSheet = loadImage("Assets/Portal/instanceportal_385x385");
    }







    /* ################################################################# GAME CONTROL PART ##################################################### */
    /* ################################################################# GAME CONTROL PART ##################################################### */
    public void init(){
    // Background
    backGround = loadImage("Assets/Background/grandcanyon.png");

    // Terrain
        initTerrain();


    }

    public void update(double dt) {

    }
    public void paintComponent(){
        //drawImage(backGround,0, 0);

        changeBackgroundColor(black);
        clearBackground(width(), height());
        drawImage(backGround, 0, 0, width(), height());

        // Draw terrain
        drawTerrain();





    }

    public void keyPressed(KeyEvent e) {

    }
}
