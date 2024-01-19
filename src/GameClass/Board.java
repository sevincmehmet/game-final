package GameClass;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Delayed;

import GameClass.Sprite.*;


public class Board extends JPanel {

    private int timerCount = 0;
    private int second = 0;
    private Dimension d;
    private Player player;
    private List<Fruit> fruits;

    private SoundManager soundManager;

    private int direction = -1;
    private int deaths = 0;
    private int fruitScore = 0;//burası meyve carpısma durumunda score toplamı;

    private boolean inGame = true;
    private String explImg = "";
    private String message = "Game Over";

    private Timer timer;

    private int[] operation = {4, 6, 8, 2};
    private String[] operationString = {"2x2", "4+2", "10-2", "6/3"};
    private int index = 0;

    public Board() {

        initBoard();
        gameInit();
    }

    private void initBoard() {

        this.soundManager = new SoundManager();

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();

        gameInit();
        soundManager.playBackgroundMusic();
    }


    private void gameInit() {


        // oyun başlangıcı
        player = new Player();
        fruits = new ArrayList<>();
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

        for (Fruit fruit : fruits) {
            if (fruit.isVisible()) {

                g.drawImage(fruit.getImage(), fruit.getX(), fruit.getY(), this);
            }

//            if (fruit.isDying() && !fruit.isImmortal()) {
//
//                fruit.die();
//            }
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        String playerImg = "src/images/background.png";

        var ii = new ImageIcon(playerImg);
        int newWidth = Commons.BOARD_WIDTH;
        int newHeight = Commons.BOARD_HEIGHT;

        ii = new ImageIcon(ii.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT));

        g.drawImage(ii.getImage(), 0, 0, null);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 35));
        g.drawString(operationString[index], 20, 40);

        g.setColor(Color.BLUE);
        String score = String.valueOf(fruitScore);
        g.drawString(score,750,40);

        g.setColor(Color.BLUE);
        String time = String.valueOf(second);
        g.drawString(time,750,550);

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

//        g.setColor(Color.black);
//        g.fillRect(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
//
//        g.setColor(new Color(0, 32, 48));
//        g.fillRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
//        g.setColor(Color.white);
//        g.drawRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
//
//        var small = new Font("Helvetica", Font.BOLD, 14);
//        var fontMetrics = this.getFontMetrics(small);
//
//        g.setColor(Color.white);
//        g.setFont(small);
//        g.drawString(message, (Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2,
//        Commons.BOARD_WIDTH / 2);

        var ii = new ImageIcon("src/images/gameWin.png");
        int newWidth = Commons.BOARD_WIDTH;
        int newHeight = Commons.BOARD_HEIGHT;

        ii = new ImageIcon(ii.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT));

        g.drawImage(ii.getImage(), 0, 0, null);
    }

    private void createRandomFruit() {
        Random random = new Random();
        int fruit = random.nextInt(4);
        int x = random.nextInt(Commons.BOARD_WIDTH);
        int y = 15;
        int speed = random.nextInt(5) + 1;
        int randomValue = random.nextInt(50);

        if (randomValue == Commons.CHANCE) {

            switch (fruit) {
                case 0:
                    fruits.add(new Apple(x, y, 2, speed, false));
                    break;
                case 1:
                    fruits.add(new Pear(x, y, 1, speed, false));
                    break;
                case 2:
                    fruits.add(new Banana(x, y, -1, speed, false));
                    break;
                case 3:
                    fruits.add(new Strawberry(x, y, -2, speed, false));
                    break;
            }
        }
    }

    //çarpısmada puanın eklenme durumu;
    private void sumFruitPoint(int point) {
        int sum = 0;
        fruitScore += point;
        int result = operation[index];
        System.out.println(fruitScore);

        if (fruitScore == result) {

            fruitScore = 0;

            if (inGame && index == operation.length - 1) {
                System.out.println("Kazandın!");
                inGame = false;
            }

            if (index == operation.length-1){
                index = operation.length-1;
            }
            else {
                index++;
            }
        }
        //burda istenen işlem sayısı fruitScoreye eşit olunca meyve skorunu sıfırla.
    }



    private void updateFruits() {
        for (Fruit fruit : fruits) {
            var random = new Random();


//            int randomFruitX = random.nextInt(10);
//            if (randomFruitX == Commons.CHANCE && fruit.isDestroyed()) {
//
//                fruit.setDestroyed(false);
//                fruit.setX(fruit.getX());
//                fruit.setY(fruit.getY());
//            }

            fruit.setDestroyed(false);
            fruit.setX(fruit.getX());
            fruit.setY(fruit.getY());

            int fruitX = fruit.getX();
            int fruitY = fruit.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !fruit.isDestroyed()) {

                if (fruitX >= (playerX)
                        && fruitX <= (playerX + Commons.PLAYER_WIDTH)
                        && fruitY >= (playerY)
                        && fruitY <= (playerY + Commons.PLAYER_HEIGHT)) {

                    fruit.setDestroyed(true);
                    fruit.die();


                    //toplama durumu
                    sumFruitPoint(fruit.getPoint());
                    /*int sum=0;
                    int scoreValue=;
                    sum+=scoreValue;*/


                    //System.out.println("Oyuncuyla player çarpıştı");
                }
            }

            if (!fruit.isDestroyed()) {

                fruit.setY(fruit.getY() + fruit.getSpeed());

                if (fruit.getY() >= Commons.GROUND) {

                    fruit.setDestroyed(true);
                }
            }
        }

        fruits.removeIf(fruit -> !fruit.isVisible() || fruit.getY() >= 550);
    }

    private void update() {

        timerCount++;

        if(timerCount % (1000/ Commons.DELAY) == 0 ){
            System.out.println(second);
            second++;
        }

        // player
        player.act();

        // Rastgele meyve oluştur ve listeye at
        createRandomFruit();

        // Fruit
        updateFruits();

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


                if (player.getSpeed() >= 4) return;

                player.setSpeed(player.getSpeed() + 1);

            }

            if (key == KeyEvent.VK_DOWN) {
                if (player.getSpeed() <= -1) return;

                player.setSpeed(player.getSpeed() - 1);
            }
        }
    }
}