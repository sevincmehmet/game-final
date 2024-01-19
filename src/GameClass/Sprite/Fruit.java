package GameClass.Sprite;

import GameClass.Commons;

import javax.swing.*;
import java.util.Random;


public class Fruit extends Sprite{

    public Fruit() {
        Random random = new Random();
        int randomFruitX = random.nextInt(Commons.BOARD_HEIGHT);
        initFruit(randomFruitX, y);
    }

    private boolean destroyed;


    private void initFruit(int x, int y) {

        setDestroyed(true);

        this.x = x;
        this.y = y;

        var bombImg = "src/images/bomb.png";
        var ii = new ImageIcon(bombImg);
        setImage(ii.getImage());
    }

    public void setDestroyed(boolean destroyed) {

        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {

        return destroyed;
    }
}

