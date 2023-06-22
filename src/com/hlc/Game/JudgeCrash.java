package com.hlc.Game;/*
    User: 黄林春
    Date: 2022/11/20
    Time: 周日
    Project_Name: snakeGame
    */

public class JudgeCrash {
    public boolean judgeCrash(Food food, int x, int y) {
        return food.x < x + 12 && food.x + 25 > x + 12 && food.y < y + 12 && food.y + 25 > y + 12;
    }

    public boolean judgeCrashBody(Body body, int x, int y) {
        return body.x < x + 12 && body.x + 25 > x + 12 && body.y < y + 12 && body.y + 25 > y + 12;
    }
}
