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

    public static void main(String[] args) {


    }

    @Override
    public void actionPerformed(ActionEvent e) {

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