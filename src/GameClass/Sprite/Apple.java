package GameClass.Sprite;

import GameClass.Commons;

import javax.swing.*;
import java.awt.*;

public class Apple extends Fruit{

    public Apple(int x, int y,int point, int speed, boolean destroyed) {
        super(x,y,point,speed,destroyed);
    }


    @Override
    public void updateImg() {
        var fruitImg = "src/images/Apple.png";
        var ii = new ImageIcon(fruitImg);

        // Kullanıcının belirlediği özel genişlik ve yükseklik değerleri alınır
        int newWidth = Commons.FRUIT_WIDTH;
        int newHeight = Commons.FRUIT_HEIGHT;

        // Resmin boyutları güncellenir
        ii = new ImageIcon(ii.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT));

        setImage(ii.getImage());
    }

}
