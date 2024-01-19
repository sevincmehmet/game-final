package GameClass.Sprite;

import GameClass.Commons;

import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Sprite {

    private int width;
    private int height;
    private int speed = 0;
    private static int count = 0;

    public Player() {

        initPlayer();
    }

    private void initPlayer() {

        updateImg("src/images/sağ125.png");
        int START_X = 400;
        setX(START_X);

        int START_Y = 500;
        setY(START_Y);
    }

    private void updateImg(String path) {
        String playerImg = path;
        var ii = new ImageIcon(playerImg);

        // Kullanıcının belirlediği özel genişlik ve yükseklik değerleri alınır
        int newWidth = Commons.PLAYER_WIDTH;
        int newHeight = Commons.PLAYER_HEIGHT;

        // Resmin boyutları güncellenir
        ii = new ImageIcon(ii.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT));

        width = newWidth;
        height = newHeight;

        setImage(ii.getImage());
    }


    public void act() {

        x += dx;
        if (dx == 2) {
            x += dx + speed;
        } else if (dx == -2) {
            x += dx - speed;
        }

        if (x <= 2) {

            x = 2;
        }

        if (x >= Commons.BOARD_WIDTH - 90) {

            x = Commons.BOARD_WIDTH - 90;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            count++;

            if(count % 5 == 0) {
                updateImg("src/images/sol125.png");
            } else {
                updateImg("src/images/sola_yatmış_125.png");
            }

            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            count++;

            if(count % 5 == 0) {
                updateImg("src/images/sağ125.png");
            } else {
                updateImg("src/images/sağa-yatmış-125.png");
            }

            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

