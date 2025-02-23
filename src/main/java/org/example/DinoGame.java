package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class DinoGame extends JPanel implements ActionListener, KeyListener {
    private int dinoX = 50, dinoY = 250, dinoWidth = 50, dinoHeight = 50;
    private int groundY = 300;
    private boolean jumping = false, falling = false;
    private int jumpSpeed = 10, gravity = 2;
    private int score = 0;
    private Timer timer;
    private ArrayList<Rectangle> obstacles;
    private Random rand = new Random();

    public DinoGame() {
        setPreferredSize(new Dimension(800, 400));
        setBackground(Color.WHITE);
        addKeyListener(this);
        setFocusable(true);

        obstacles = new ArrayList<>();
        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setColor(Color.BLACK);
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
    }

    public static void main(String[] args) {


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
            if (falling) {
                dinoY += gravity * 2;
                if (dinoY >= 250) {
                    dinoY = 250;
                    falling = false;
                    jumpSpeed = 10;
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
                    JOptionPane.showMessageDialog(this, "Game Over! Score: " + score);
                    System.exit(0);
                }
            }
            if (rand.nextInt(100) < 2) {
                obstacles.add(new Rectangle(800, 275, 30, 30));
            }

            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}