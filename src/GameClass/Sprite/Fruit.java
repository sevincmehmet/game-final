package GameClass.Sprite;

import GameClass.Commons;

import javax.swing.*;
import java.util.Random;


public class Fruit extends Sprite{

    private int point;
    private int speed;
    private boolean destroyed;

    public Fruit(int x, int y,int point, int speed, boolean destroyed) {

        this.point = point;
        this.speed = speed;
        this.destroyed = destroyed;
        initFruit(x,y);
    }


    private void initFruit(int x, int y) {

        Random random = new Random();
        int randomFruitX = random.nextInt(Commons.BOARD_HEIGHT);

        setDestroyed(true);

        this.x = x;
        this.y = y;

        updateImg();
    }

    public void updateImg(){
        var fruitImg = "";
        var ii = new ImageIcon(fruitImg);
        setImage(ii.getImage());
    }

    public void setDestroyed(boolean destroyed) {

        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {

        return destroyed;
    }

    public int getPoint(){
        return point;
    }

    public int getSpeed() {
        return speed;
    }


}

