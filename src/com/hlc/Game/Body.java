package com.hlc.Game;/*
    User: 黄林春
    Date: 2022/11/20
    Time: 周日
    Project_Name: snakeGame
    */

import java.awt.*;

public class Body {
    Image body;
    int x,y;

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

    public Body(int x, int y, Image body){
        this.x = x;
        this.y = y;
        this.body = body;
    }

    public void drawBody(Graphics g){
        g.drawImage(body,x,y,null);
    }
}
