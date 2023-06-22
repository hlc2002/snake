package com.hlc.Game;/*
    User: 黄林春
    Date: 2022/11/20
    Time: 周日
    Project_Name: snakeGame
    */

import javax.swing.*;

public class GameMain {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        GamePanel gamePanel = new GamePanel();
        jFrame.add(gamePanel);
        jFrame.setSize(600,600);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setTitle("贪吃蛇");
        jFrame.setLocationRelativeTo(null);
        gamePanel.move();
        jFrame.addKeyListener(gamePanel);
    }
}
