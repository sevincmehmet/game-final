package GameClass;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import GameClass.Sprite.Fruit;
import GameClass.Sprite.Player;


public class Board extends JPanel {

    private Dimension d;
    private Player player;
    private Fruit fruit;

    private int direction = -1;
    private int deaths = 0;

    private boolean inGame = true;
    private String explImg = "src/images/explosion.png";
    private String message = "Game Over";

    private Timer timer;


    public Board() {

        initBoard();
        gameInit();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();

        gameInit();
    }


    private void gameInit() {


        // oyun başlangıcı
        player = new Player();
        fruit = new Fruit();
    }

    // oyuncuyu çizer
    private void drawPlayer(Graphics g) {

        if (player.isVisible()) {

            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {

            player.die();
            inGame = false;
        }
    }

    // meyveleri çizer
    private void drawFruit(Graphics g) {


            //if (!fruit.isDestroyed()) {

             g.drawImage(fruit.getImage(), fruit.getX(), fruit.getY(), this);
            //}


    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (inGame) {

            g.drawLine(0, Commons.GROUND,
                    Commons.BOARD_WIDTH, Commons.GROUND);

            drawPlayer(g);
            drawFruit(g);

        } else {

            if (timer.isRunning()) {
                timer.stop();
            }

            gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);

        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2,
                Commons.BOARD_WIDTH / 2);
    }

    private void update() {

        // player
        player.act();

        // Fruit
        var random = new Random();


            int randomFruitX = random.nextInt(15);
            if (randomFruitX == Commons.CHANCE && fruit.isDestroyed()) {

                fruit.setDestroyed(false);
                fruit.setX(fruit.getX());
                fruit.setY(fruit.getY());
            }

            int fruitX = fruit.getX();
            int fruitY = fruit.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !fruit.isDestroyed()) {

                if (fruitX >= (playerX)
                        && fruitX <= (playerX + Commons.PLAYER_WIDTH)
                        && fruitY >= (playerY)
                        && fruitY <= (playerY + Commons.PLAYER_HEIGHT)) {

                    var ii = new ImageIcon(explImg);
                    player.setImage(ii.getImage());
                    fruit.setDestroyed(true);

                    System.out.println("Oyuncuyla player çarpıştı");
                }
            }

            if (!fruit.isDestroyed()) {

                fruit.setY(fruit.getY() + 1);

                if (fruit.getY() >= Commons.GROUND ) {

                    fruit.setDestroyed(true);
                }
            }

    }

    private void doGameCycle() {

        update();
        repaint();
    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            player.keyReleased(e);
        }

        // Tuş basılma kontrolü
        @Override
        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_UP) {
                System.out.println("yukarı basıldı");
            }

            if(key == KeyEvent.VK_DOWN) {
                System.out.println("aşağı basıldı");
            }
        }
    }
}