package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class DinoGame extends JPanel implements ActionListener {
    private int dinoX = 50, dinoY = 250, dinoWidth = 50, dinoHeight = 50;
    private int groundY = 300;
    private boolean jumping = false, falling = false;
    private int jumpSpeed = 15, gravity = 1;
    private int score = 0;
    private Timer timer;
    private ArrayList<Rectangle> obstacles;
    private Random rand = new Random();
    private boolean gameOver = false;

    public DinoGame() {
        setPreferredSize(new Dimension(800, 400));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && !jumping && !falling) {
                    jumping = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
                    resetGame();
                }
            }
        });

        obstacles = new ArrayList<>();
        timer = new Timer(20, this);
        timer.start();
    }

    private void resetGame() {
        dinoY = 250;
        score = 0;
        obstacles.clear();
        jumping = false;
        falling = false;
        gameOver = false;
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        
        g.setColor(new Color(100, 100, 100));
        g.fillRect(0, groundY, getWidth(), 5);


        g.setColor(Color.BLUE);
        g.fillRect(dinoX, dinoY, dinoWidth, dinoHeight);


        g.setColor(Color.RED);
        for (Rectangle obs : obstacles) {
            g.fillRect(obs.x, obs.y, obs.width, obs.height);
        }


        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 650, 50);


        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("GAME OVER!", 300, 200);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Press 'R' to Restart", 320, 250);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jumping) {
            dinoY -= jumpSpeed;
            jumpSpeed -= gravity;
            if (jumpSpeed <= 0) {
                jumping = false;
                falling = true;
            }
        }

        if (falling) {
            dinoY += gravity * 2;
            if (dinoY >= 250) {
                dinoY = 250;
                falling = false;
                jumpSpeed = 15;
            }
        }

        for (int i = 0; i < obstacles.size(); i++) {
            Rectangle obs = obstacles.get(i);
            obs.x -= 5;
            if (obs.x + obs.width < 0) {
                obstacles.remove(i);
                score++;
            }
            if (new Rectangle(dinoX, dinoY, dinoWidth, dinoHeight).intersects(obs)) {
                timer.stop();
                gameOver = true;
            }
        }

        if (rand.nextInt(100) < 2 && !gameOver) {
            obstacles.add(new Rectangle(800, 275, 30, 30));
        }

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dino Chrome Game");
        DinoGame game = new DinoGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}

