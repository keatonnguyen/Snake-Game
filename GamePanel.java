package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener 
{

    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    static final int DELAY = 100;

    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    public GamePanel() 
    {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color(119, 221, 119)); 
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        random = new Random();
        startGame();
    }

    public class MyKeyAdapter extends KeyAdapter 
    {
        @Override
        public void keyPressed(KeyEvent e) 
        {
            switch (e.getKeyCode()) 
            {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') direction = 'L';
                    break;

                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') direction = 'R';
                    break;

                case KeyEvent.VK_UP:
                    if (direction != 'D') direction = 'U';
                    break;

                case KeyEvent.VK_DOWN:
                    if (direction != 'U') direction = 'D';
                    break;
            }
        }
    }
}
