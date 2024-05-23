import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class CatGame extends GameEngine {
    public static void main(String args[]){
        createGame(new CatGame(), 60);
    }

    //==============================================BACKGROUND PICTURE==============================
    Image backGround;
    private int width = 1150;
    private int height = 650;









    /* ################################################################# GAME CONTROL PART ##################################################### */
    /* ################################################################# GAME CONTROL PART ##################################################### */
    public void init(){
    //backGround = loadImage("grandcanyon.png");
    }

    public void update(double dt) {

    }
    public void paintComponent(){
        //drawImage(backGround,0, 0);

        changeBackgroundColor(black);
        clearBackground(width(), height());



    }

    public void keyPressed(KeyEvent e) {

    }
}
