package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener 
{

    //Settings
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
    char direction = 'right';
    boolean running = false;
    Timer timer;
    Random random;
    boolean gameOver = false;


    //UI
    public GamePanel() 
    {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color(119, 221, 119)); 
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        random = new Random();
        startGame();
    }


    //Setup
    public void startGame() 
    {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    
    public void newApple() 
    {
        appleX = random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        appleY = random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }

    
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        if (running) 
        {
            
            // Paint apple
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // Paint snake
            for (int i = 0; i < bodyParts; i++) 
            {
                g.setColor(Color.blue);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            // Score color
            g.setColor(Color.black);
            g.setFont(new Font("SansSerif", Font.BOLD, 30));
            g.drawString("SCORE: " + applesEaten, 10, 30);
        } 
        
        else 
        {
            gameOver(g);
        }
    }

    
    public void move() 
    {

        //Shift body parts
        for (int i = bodyParts; i > 0; i--) 
        {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        //Move the head of the snake
        switch (direction) 
        {
            case 'up': y[0] -= UNIT_SIZE; break;
            case 'down': y[0] += UNIT_SIZE; break;
            case 'left': x[0] -= UNIT_SIZE; break;
            case 'right': x[0] += UNIT_SIZE; break;
        }
    }

    
    public void checkApple() 
    {
        if (x[0] == appleX && y[0] == appleY) 
        {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    
    public void checkCollisions() 
    {
        // Body
        for (int i = bodyParts; i > 0; i--) 
        {
            if (x[0] == x[i] && y[0] == y[i]) 
            {
                running = false;
                break;
            }
        }

        // Walls
        if (x[0] < 0 || x[0] >= WIDTH || y[0] < 0 || y[0] >= HEIGHT) 
        {
            running = false;
        }

        if (!running) 
        {
            timer.stop();
            gameOver = true;
        }
    }

    
    public void gameOver(Graphics g) 
    {
        g.setColor(Color.black);
        g.setFont(new Font("SansSerif", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (WIDTH - metrics.stringWidth("Game Over")) / 2, HEIGHT / 2);

        //Replay
        g.setFont(new Font("SansSerif", Font.PLAIN, 30));
        String msg = "Press ENTER to play again";
        g.drawString(msg, (WIDTH - g.getFontMetrics().stringWidth(msg)) / 2, HEIGHT / 2 + 50);
    }

    
    public void restartGame() 
    {
        bodyParts = 6;
        applesEaten = 0;
        direction = 'R';
        running = true;
        gameOver = false;

        // Reset positions
        for (int i = 0; i < x.length; i++) 
        {
            x[i] = 0;
            y[i] = 0;
        }

        newApple();
        timer.restart();
    }

    
    //Keyboard inputs for WASD
    public class MyKeyAdapter extends KeyAdapter 
    {
        @Override
        public void keyPressed(KeyEvent e) 
        {
            switch (e.getKeyCode()) 
            {
                case KeyEvent.VK_A:
                    if (direction != 'right') direction = 'left';
                    break;

                case KeyEvent.VK_D:
                    if (direction != 'left') direction = 'right';
                    break;

                case KeyEvent.VK_W:
                    if (direction != 'down') direction = 'up';
                    break;

                case KeyEvent.VK_S:
                    if (direction != 'up') direction = 'down';
                    break;
            }
        }
    }
}
