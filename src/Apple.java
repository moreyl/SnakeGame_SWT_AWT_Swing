package moreyl.com.Objects;
import moreyl.com.SnakeGameMain;

import static moreyl.com.SnakeGameMain.*;

public class Apple {

    //переменный позиуия яблока

    public int posY;
    public int posX;

    //конструктор

    public Apple(int x, int y) {
        posX = x;
        posY = y;
    }

    // метод отвечающий за рандомное появление

    public void setRundomPosition() {
        posX = Math.abs((int) (Math.random() * SnakeGameMain.WIDTH - 1));
        posY = Math.abs((int) (Math.random() * SnakeGameMain.HEIGHT - 1));
    }



}
