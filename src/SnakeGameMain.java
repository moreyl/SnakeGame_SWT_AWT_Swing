package moreyl.com;

import moreyl.com.Objects.Apple;
import moreyl.com.Objects.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// This line is added by Eclipse

public class SnakeGameMain extends JPanel implements ActionListener {


    public static JFrame jFrame;
    // Опишем переменную которая будет связана с клеточками
    public static final int SCALE = 32; // размер квадратной клеточки
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static int speed = 10;
    private enum STATE {
        MENU,
        GAME
    };
    private STATE State = STATE.MENU;


    //создадим объект класса Snake
    Snake s = new Snake(5,6,5,5);
    Apple apple = new Apple(Math.abs((int) (Math.random() * SnakeGameMain.WIDTH - 1)), Math.abs((int) (Math.random() * SnakeGameMain.HEIGHT - 1)));
    Timer timer = new Timer(1000/speed, this);// создадим таймер который будет обновлять наш экран


    //Пишем конструктор

    public SnakeGameMain() {
        // запускаем таймер
        timer.start();
        // вызываем метод клавиатуры
             addKeyListener(new KeyBoard());
            setFocusable(true);
    }

    //Графический художник

    public void paint(Graphics g ) {
      g.setColor(Color.black);  // Зададим задний фон
      g.fillRect(0,0, WIDTH*SCALE, HEIGHT*SCALE);  // Делаем заливку по координатам (0,0 (левый верхний угол) и координата SCALE*WIDTH, SCALE*HEIGHT (правый нижний угол)
      for (int x = 0; x < WIDTH*SCALE; x+=SCALE)
      {//отресуем наши клеточки с помощью цикла for (x увеличивается на ширину клетки)
         g.setColor(Color.black); //залатим цвет
         g.drawLine(x,0,x, HEIGHT*SCALE); //отрисуем линии
      }
      //сделаем тоже самое только по y
      for (int y = 0; y < HEIGHT*SCALE; y+=SCALE)
      {//отресуем наши клеточки с помощью цикла for (н увеличивается на ширину клетки)
          g.setColor(Color.black); //залатим цвет
          g.drawLine(0,y,WIDTH*SCALE, y ); //отрисуем линии
      }

        //отрисуем яблоко
        g.setColor(Color.red);
        g.fillOval(apple.posX*SCALE + 4, apple.posY*SCALE + 4, SCALE - 8, SCALE -8);


        for (int l = 1; l < s.length; ++l)
      {
          g.setColor(Color.green); //отрисуем квадратики
          g.fillRect(s.sX[l]*SCALE+3, s.sY[l]*SCALE+3, SCALE-6, SCALE-6);
          g.setColor(Color.orange);
          g.fillRect(s.sX[0]*SCALE+3, s.sY[0]*SCALE+3, SCALE-6, SCALE-6);// умножаем на размер нашей клетки
      }


    }





    public static void main (String[] args) {

        jFrame = new JFrame("Title");
        jFrame.setSize(SCALE*WIDTH+16, SCALE*HEIGHT+4); // Задаём размеры окна // Размер окна будет кол-во клеток на размер
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Делаем так что бы по закрытию программа завершалась
        jFrame.setResizable(false); // убираем растягивание окна
        jFrame.setLocationRelativeTo(null); // Размещаем его по середине
        jFrame.add(new SnakeGameMain()); // добавим отрисовщик (с помощью конструктора)
        jFrame.setVisible(true); // делаем окно видимым


    }



    @Override
    public void actionPerformed(ActionEvent e) { // все события игры


        s.move();//зададим нашей змейке движение
        if ((s.sX[0] == apple.posX) && (s.sY[0] == apple.posY)) // взаимодействие яблока и змейки
        {
            apple.setRundomPosition();
            s.length++;

        }
        for (int l = 1; l < s.length; ++l)
        {
            if ((s.sX[l] == apple.posX) && (s.sY[l] == apple.posY)) // если любой элемент хвоста
                 {
                     apple.setRundomPosition();

            }
            if ((s.sX[0] == s.sX[l]) && (s.sY[0] == s.sY[l])){
                timer.stop(); // останавливаем счетчик
                JOptionPane.showMessageDialog(null,"Ты проиграл, ублюдок! Хочешь попробовать ещё?");
                jFrame.setVisible(false);
                        s.length = 2;
                        s.direction = 0;
                        apple.setRundomPosition();
                jFrame.setVisible(true);
                timer.start();

            }
        }
        repaint(); // каждый раз змейка должна перересоваться
       // apple.setRundomPosition();

    }

    // настраиваем нажатия клавиш

    public class KeyBoard extends KeyAdapter {
        //метод который будет принимать в себя событие с клавиатуры
        public void keyPressed(KeyEvent event) {
            int key = event.getKeyCode(); // тут будет хранится код клавиш которые мы будем нажимать и он будет обнавлятся каждый раз когда у нас сработает таймер
            if ((key == KeyEvent.VK_UP) && (s.direction != 2)) s.direction = 0;
            if ((key == KeyEvent.VK_DOWN) && (s.direction != 0)) s.direction = 2;
            if ((key == KeyEvent.VK_LEFT) && (s.direction != 1) ) s.direction = 3;
            if ((key == KeyEvent.VK_RIGHT) && (s.direction != 3) ) s.direction = 1;
        }
    }



}
