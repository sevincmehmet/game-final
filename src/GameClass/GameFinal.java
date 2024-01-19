package GameClass;

import javax.swing.*;
import java.awt.*;

public class GameFinal extends JFrame {
    public GameFinal() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setTitle("Space Invaders");
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new GameFinal();
            ex.setVisible(true);
        });
    }
}
