package GameClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFinal extends JFrame {

    public GameFinal() {
        initUI();
    }

    private void initUI() {

        setLayout(new BorderLayout());

        // Başlangıç ekranı paneli
        JPanel startPanel = new JPanel(new GridLayout(3, 1, 0, 20)); // 3 satır, 1 sütun, yatayda 0 boşluk, dikeyde 20 boşluk
        startPanel.setOpaque(false); // Arkaplanın görünmesi için

        JButton oyunaBaslaButton = createStyledButton("Oyuna Başla", Color.BLUE);
        JButton kurallarButton = createStyledButton("Kurallar", Color.GREEN);
        JButton cikisButonu = createStyledButton("Çıkış Butonu", Color.RED);


        Dimension buttonSize = new Dimension(150, 40); // Düğme boyutları değiştirildi
        oyunaBaslaButton.setPreferredSize(buttonSize);
        kurallarButton.setPreferredSize(buttonSize);
        cikisButonu.setPreferredSize(buttonSize);

        oyunaBaslaButton.setBackground(Color.BLACK);
        kurallarButton.setBackground(Color.BLACK);
        cikisButonu.setBackground(Color.BLACK);

        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        oyunaBaslaButton.setFont(buttonFont);
        kurallarButton.setFont(buttonFont);
        cikisButonu.setFont(buttonFont);

        oyunaBaslaButton.setOpaque(true);
        oyunaBaslaButton.setBackground(new Color(0, 0, 0, 0));
        oyunaBaslaButton.setContentAreaFilled(false);
        oyunaBaslaButton.setBorderPainted(false);

        kurallarButton.setOpaque(true);
        kurallarButton.setBackground(new Color(0, 0, 0, 0));
        kurallarButton.setContentAreaFilled(false);
        kurallarButton.setBorderPainted(false);

        cikisButonu.setOpaque(true);
        cikisButonu.setBackground(new Color(0, 0, 0, 0));
        cikisButonu.setContentAreaFilled(false);
        cikisButonu.setBorderPainted(false);

        oyunaBaslaButton.setFocusPainted(false);
        oyunaBaslaButton.setBorder(BorderFactory.createEmptyBorder());
        oyunaBaslaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                oyunaBaslaButton.setForeground(Color.GREEN);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                oyunaBaslaButton.setForeground(new Color(88, 41, 185));
            }
        });

        kurallarButton.setFocusPainted(false);
        kurallarButton.setBorder(BorderFactory.createEmptyBorder());
        kurallarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                kurallarButton.setForeground(Color.YELLOW);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                kurallarButton.setForeground(new Color(88, 41, 185));
            }
        });

        cikisButonu.setFocusPainted(false);
        cikisButonu.setBorder(BorderFactory.createEmptyBorder());
        cikisButonu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cikisButonu.setForeground(Color.RED);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                cikisButonu.setForeground(new Color(88, 41, 185));
            }
        });

        cikisButonu.addActionListener(e -> System.exit(0));

        kurallarButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Hoş geldiniz! Bambu' nun meyve hesapları oyununa hazır mısınız? Bu eğlenceli oyunumuzda pandamız, yukarıdan düşen meyveleri yakalamaya çalışacak ve aynı zamanda sol üst köşede belirtilen matematik işlemlerini çözmeye çalışacak. İşte oyunun temel kuralları:\n" +
                                "\n" +
                                "1. Meyveleri Yakala: Ekranın üst kısmından süzülen meyveleri yakalamak için panda karakterinizi kontrol edin. Her meyve belirli bir matematik işlemi ifade eder.\n" +
                                "\n" +
                                "2. Matematik İşlemlerini Tamamla: Sol üst köşede göreceğiniz matematik işlemlerini tamamlayarak puan kazanın. Muzlar -1, çilekler -2, elmalar +2, armutlar ise +1 puan değerindedir.\n" +
                                "\n" +
                                "3. Hızlı ve Doğru Ol: Zaman sınırlıdır! Matematik işlemlerini hızlı bir şekilde tamamla ve doğru meyveleri yakala. Unutma, hız ve doğruluk kazanmanın anahtarıdır.\n" +
                                "\n" +
                                "4. Yüksek Skor Hedefi Belirle: Kaç meyve yakalayabileceğini ve kaç matematik işlemi tamamlayabileceğini gör. Hedefini belirle ve yüksek skoruna ulaş!\n" +
                                "\n" +
                                "Hazır olduğun zaman, panda karakterini kontrol et ve matematikle dolu bu eğlenceli meyve avına başla! Başarılar dileriz!\n"
                        ,
                        "Nasil Oynanir", JOptionPane.INFORMATION_MESSAGE));

        cikisButonu.addActionListener(e -> System.exit(0));



        oyunaBaslaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        // Diğer butonlara tıklama olaylarını ekleyebilirsiniz...

        startPanel.add(oyunaBaslaButton);
        startPanel.add(kurallarButton);
        startPanel.add(cikisButonu);

        add(startPanel, BorderLayout.CENTER);

        setTitle("Space Invaders");
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);


        ImageIcon backgroundIcon = new ImageIcon("src/images/gameStartBackground.png"); // Arkaplan fotoğrafı dosya adınıza göre değiştirin
        Image img = backgroundIcon.getImage();
        Image newImg = img.getScaledInstance(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon scaledBackgroundIcon = new ImageIcon(newImg);

        JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.add(startPanel, BorderLayout.CENTER);

        // backgroundLabel'ın boyutunu startPanel'ın boyutuna ayarla
        backgroundLabel.setPreferredSize(startPanel.getPreferredSize());

        add(backgroundLabel, BorderLayout.CENTER);

        setTitle("Space Invaders");
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createStyledButton(String text, Color background) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(150, 40));
        button.setBackground(background);
        button.setForeground(new Color(88, 41, 185));
        button.setFont(new Font("Arial", Font.BOLD, 32));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(new Color(88, 41, 185));
            }
        });
        return button;
    }

    private void startGame() {
        // Oyun ekranını oluştur ve görünür yap
        remove(getContentPane().getComponent(0)); // Başlangıç ekranını kaldır
        add(new Board(), BorderLayout.CENTER); // Oyun ekranını ekle
        revalidate(); // Yeniden düzenleme yap
        repaint(); // Yeniden çiz

        // Oyun ekranını odakla
        getContentPane().getComponent(0).requestFocus();
    }
    private void showRules() {
        JOptionPane.showMessageDialog(this, "Nasil Oynanir? ...", "Nasil Oynanir", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var ex = new GameFinal();
        });
    }
}