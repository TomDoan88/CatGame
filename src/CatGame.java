import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import javax.swing.*;
import java.awt.geom.*;

public class CatGame extends GameEngine {
    public static void main(String args[]){
        createGame(new CatGame(), 60);
    }


    /*
    READ ME BEFORE YOU START............
    NOTE THE GAME DIMENSION IS 1150 WIDTH AND 650 - I MADE THE CHANGES TO THE GAME ENGINE ITSELF
    You would need to import the game engine from my Github or attempt to change the dimension explicitly.


    */

    // Common variables to be used for all assets
    double animateTime;

    //==============================================BACKGROUND PICTURE========================================
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

    //======================================PORTAL INITIALIZATION AND FUNCTIONS =====================================

    Image portalSpriteSheet;
    Image[] portalImages;
    int currentFrame;



    public void initPortal(){
        portalImages = new Image[210];
        portalSpriteSheet = loadImage("Assets/Portal/instanceportal_385x385.png");
        for(int iy = 0; iy < 14; iy++){
            for(int ix = 0; ix < 15; ix++){
                    portalImages[iy * 15 + ix] = subImage(portalSpriteSheet, ix * 385, iy * 385, 385, 385);
            }
        }
    }

//    public getPortalFrame(double d, int num_frames ){
//        return(int) Math.floor(((animateTime % d) / d) * num_frames);
//    }

    public void updatePortal(){
        currentFrame = (int) ((animateTime / 0.001) % 210);
    }

    public void drawPortal(){

        drawImage(portalImages[currentFrame], 0, 10, 105, 110);
        drawImage(portalImages[currentFrame], 1045, 545, 105, 105);
    }

    //=========================================== CAT INITIALIZATION AND FUNCTIONS  ============================



    Image catSpriteSheet;
    Image[] catImages;
    int catCurrentFrame;
    boolean isCatMoving = false;
    boolean turnLeft, turnRight;

    double catPositionX, getCatPositionY;
    double catVelocityX = 1, catVelocityY = 480;



    // render cat idle if cat moving = false;
    public void initCat(){
        if(!isCatMoving){
            catImages = new Image[4];
            catSpriteSheet = loadImage("Assets/Cat/Idle.png");
            for(int iy = 0; iy < 1; iy++){
                for(int ix = 0; ix < 4; ix++){
                    catImages[iy * 4 + ix] = subImage(catSpriteSheet, ix * 48, iy * 48, 48, 48);
                }
            }
        } else if (isCatMoving){
            catImages = new Image[6];
            catSpriteSheet = loadImage("Assets/Cat/Walk.png");
            for(int iy = 0; iy < 1; iy++){
                for(int ix = 0; ix < 6; ix++){
                    catImages[iy * 6 + ix] = subImage(catSpriteSheet, ix * 48, iy * 48, 48, 48);
                }
            }
        }
    }

    public void updateCat(double dt){
        if(turnRight){
            catPositionX += catVelocityX * dt * 350;
            System.out.println("CAT POSIITIONX" + catPositionX);
        }

        catCurrentFrame = (int) ((animateTime / 0.1) % 4);
    }

    public void drawCat(){
        //saveCurrentTransform();
        //translate(catPositionX, catVelocityY);
        drawImage(catImages[catCurrentFrame], catPositionX, 480, 170, 170 );

        //restoreLastTransform();

    }

    //=========================================== BIRD  INITIALIZATION AND FUNCTIONS  ============================

    /*
    Bird flying randomly  inside the box of x1=350 x2=795 --- y1=0 y2=150
    In the sky and
     */
    Image  birdSpriteSheet;
    Image[] birdImages;
    int birdCurrentFrame;
    double birdPositionX = 650, birdPositionY = 150;
    double birdVelocityX = 1, birdVelocityY = 1;
    boolean birdFacingRightDirection; // If flyLimit = true we render the reverse the image for bird to turn around

    public void initBird(){
        birdImages = new Image[6];
        birdSpriteSheet = loadImage("Assets/Bird/Walk.png");
        for(int iy = 0; iy < 1; iy++){
            for(int ix = 0; ix < 6; ix++){
                birdImages[iy * 6 + ix] = subImage(birdSpriteSheet, ix * 32, iy * 32, 32, 32);
            }
        }
    }

    public void updateBird(double dt){


        birdPositionX += birdVelocityX * dt * 100;
        birdPositionY += birdVelocityY * dt * 100;

        // Check boundaries and reverse direction if needed
        if (birdPositionX < 350) {
            birdPositionX = 350;
            birdVelocityX = Math.abs(birdVelocityX); // Reverse to positive direction
            birdFacingRightDirection = true;
        } else if (birdPositionX > 700) {
            birdPositionX = 700;
            birdVelocityX = -Math.abs(birdVelocityX); // Reverse to negative direction
            birdFacingRightDirection = false;
        }

        if (birdPositionY < -55) {
            birdPositionY = -55;
            birdVelocityY = Math.abs(birdVelocityY); // Reverse to positive direction
        } else if (birdPositionY > 150) {
            birdPositionY = 150;
            birdVelocityY = -Math.abs(birdVelocityY); // Reverse to negative direction
        }

        birdCurrentFrame = (int) ((animateTime / 0.1) % 6);
    }


    public void drawBird(){
        if(birdFacingRightDirection){
            drawImage(birdImages[birdCurrentFrame], birdPositionX, birdPositionY, 100,  100 );
        } else {
            drawImage(birdImages[birdCurrentFrame], birdPositionX, birdPositionY, -100,  100 );
        }
    }

    //=========================================== MICE  INITIALIZATION AND FUNCTIONS  ============================



    /* ################################################################# GAME CONTROL PART ##################################################### */
    /* ################################################################# GAME CONTROL PART ##################################################### */
    public void init(){
    // Background
    backGround = loadImage("Assets/Background/grandcanyon.png");

    // Terrain
        initTerrain();

    // Portal
        initPortal();

    // Cat
        initCat();

   // Bird
       initBird();
    }

    public void update(double dt) {
        animateTime += dt;

        // Portal update
        updatePortal();

        // Cat update
        updateCat(dt);;

        // Bird update
       updateBird(dt);
    }
    public void paintComponent(){
        //drawImage(backGround,0, 0);

        changeBackgroundColor(black);
        clearBackground(width(), height());
        drawImage(backGround, 0, 0, width(), height());


        // This is to draw a circle to fit inside the portal uptop since it's transfer parent
        changeColor(black);
        drawSolidCircle(55, 65, 48);

        // Draw terrain
        drawTerrain();


        //Draw Portal
        drawPortal();

        // Draw Cat
        drawCat();

        // Draw Bird
        drawBird();

    }

    public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    isCatMoving = true;
                    turnRight = true;
                    initCat();
                }
    }

    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            isCatMoving = false;
            turnRight = false;
            initCat();
        }
    }
}
