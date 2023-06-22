package com.hlc.Game;/*
    User: 黄林春
    Date: 2022/11/20
    Time: 周日
    Project_Name: snakeGame
    */

import java.awt.*;
import java.util.Random;

public class Food {
    int x,y;
    Image food;
    public Food(Image food, Random random){
        this.x = (random.nextInt(0,10))*60;
        this.y = (random.nextInt(0,10))*60;
        this.food = food;
    }

    public void drawFood(Graphics g){
        g.drawImage(food,x,y,null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
