package moreyl.com.Objects;
import moreyl.com.SnakeGameMain;

import static moreyl.com.SnakeGameMain.*;

public class Snake {
    public int length = 2; // зададим переменную которая будет описывать длинну змейку
    public int direction = 2; // зададим переменную описывающую движение
    /* змейка представяляет из себя
    массив по x и по y
    Создадим два массива X и Y
     */
    public int sX[] = new int[300]; // максимальная длина умещающаеся в клеточках нашего экрана
    public int sY[] = new int[300];

    /* При создании объекта класса Snake
    нам нужно указать изначальные координаты
    для этого напишем конструктор
     */

    public Snake(int x1, int y1, int x2, int y2) {    // укажем в конструкторе четыре начальные координаты
        sX[0] = x1;
        sX[1] = x2;
        sY[0] = y1;
        sY[1] = y2;
    }

    //создадим движение

    /* каждая клеточка змейки должна занимать
    предыдущую позицию
     */


    public void move() {
        for (int l = length; l > 0; l--) {
            sX[l] = sX[l-1];  // нашу координату sX[l] мы будем присваивать предыдущую координату
            sY[l] = sY[l-1]; // тоже самое для sY

        }


 // создадим четыре направления
        if (direction == 0) sY[0]--; ; //up  // и уменьшаем sY координату
        if (direction == 2) sY[0]++;  //down // увеличиваем
        if (direction == 1) sX[0]++; // right
        if (direction == 3) sX[0]--; // left

        //добавим переходы
        if (sX[0] > SnakeGameMain.WIDTH - 1) sX[0] = 0; // нижний переход
        if (sX[0] < 0) sX[0] = SnakeGameMain.WIDTH - 1; // верхний переход
        if (sY[0] > SnakeGameMain.HEIGHT - 1) sY[0] = 0; // нижний переход
        if (sY[0] < 0) sY[0] = SnakeGameMain.HEIGHT - 1; // верхний переход



    }



}
