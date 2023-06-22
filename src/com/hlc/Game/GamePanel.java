package com.hlc.Game;/*
    User: 黄林春
    Date: 2022/11/20
    Time: 周日
    Project_Name: snakeGame
    */


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GamePanel extends Panel implements KeyListener {
    //初始化状态常量
    static final int ToUp = 0;
    static final int ToDown = 1;
    static final int ToLeft = 2;
    static final int ToRight = 3;
    static Random random = new Random();

    Image up, down, left, right, food, body;//图片资源

    List<Body> bodys = new LinkedList<>();//身体容器
    List<Food> foods = new LinkedList<>();//食物容器

    int x, y = 0;//初始蛇头坐标
    int state = ToRight;//控制蛇的运动方向（开局向右运动）
    boolean life = true;//生命状态
    int timer = 0;//充当一个计时器，当它每次增加到n（最好是奇数）的倍数时就运行某个方法，实现与线程内的循环体执行的时差

    public GamePanel() {
        right = new ImageIcon("Image/right.png").getImage();
        up = new ImageIcon("Image/up.png").getImage();
        down = new ImageIcon("Image/down.png").getImage();
        left = new ImageIcon("Image/left.png").getImage();
        food = new ImageIcon("Image/food.png").getImage();
        body = new ImageIcon("Image/body.png").getImage();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (life) {
            if (state == ToRight) g.drawImage(right, x, y, null);
            if (state == ToLeft) g.drawImage(left, x, y, null);
            if (state == ToDown) g.drawImage(down, x, y, null);
            if (state == ToUp) g.drawImage(up, x, y, null);

            for (Food f : foods) {
                f.drawFood(g);
            }

            for (Body b : bodys) {
                b.drawBody(g);
            }

        } else {
            g.setFont(new Font("微软雅黑", Font.BOLD, 50));
            g.drawString("GameOver", 180, 275);
        }
    }

    public void move() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (life) {
                    timer++;
                    if (foods.size() < 10 && bodys.size() < 10) {
                        if (timer % 5 == 0)
                            foods.add(new Food(food, random));
                    }
                    if (bodys.size() >= 20) {
                        if (timer % 20 == 0)
                            foods.add(new Food(food, random));
                    }

                    repaint();
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (life) {
                    if (state == ToRight) {
                        x = x + 25;
                        if (bodys.size() != 0) {
                            bodys.remove(bodys.size() - 1);
                            bodys.add(0, new Body(x - 25, y, body));
                        }
                    }

                    if (state == ToDown) {
                        y = y + 25;
                        if (bodys.size() != 0) {
                            bodys.remove(bodys.size() - 1);
                            bodys.add(0, new Body(x, y - 25, body));
                        }
                    }

                    if (state == ToLeft) {
                        x = x - 25;
                        if (bodys.size() != 0) {
                            bodys.remove(bodys.size() - 1);
                            bodys.add(0, new Body(x + 25, y, body));
                        }
                    }

                    if (state == ToUp) {
                        y = y - 25;
                        if (bodys.size() != 0) {
                            bodys.remove(bodys.size() - 1);
                            bodys.add(0, new Body(x, y + 25, body));
                        }
                    }

                    JudgeCrash judgeCrash = new JudgeCrash();
                    for (int i = 0; i < bodys.size(); i++) {
                        boolean crashBody = judgeCrash.judgeCrashBody(bodys.get(i), x, y);
                        if (crashBody) {
                            life = false;
                            timer = 0;
                        }
                    }
                    for (int i = 0; i < foods.size(); i++) {
                        boolean crash = judgeCrash.judgeCrash(foods.get(i), x, y);
                        if (crash) {
                            bodys.add(new Body(foods.get(i).getX(), foods.get(i).getY(), body));
                            foods.remove(foods.get(i));
                        }
                    }



                    if (x > 600 || x < 0 || y < 0 || y > 600) {
                        timer = 0;
                        life = false;
                    }


                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    repaint();
                }

            }
        }.start();


    }

    @Override//键入
    public void keyTyped(KeyEvent e) {

    }

    @Override//按下
    public void keyPressed(KeyEvent e) {

    }

    @Override//释放
    public void keyReleased(KeyEvent e) {

        int keyCode = e.getKeyCode();//获取当前释放的键盘键位的键码

        if (keyCode == KeyEvent.VK_DOWN && state != ToUp) {
            if (state == ToDown) {
                y = y + 25;
            }
            state = ToDown;
        }
        if (keyCode == KeyEvent.VK_UP && state != ToDown) {
            if (state == ToUp) {
                y = y - 25;
            }
            state = ToUp;
        }
        if (keyCode == KeyEvent.VK_RIGHT && state != ToLeft) {
            if (state == ToRight) {
                x = x + 25;
            }
            state = ToRight;
        }
        if (keyCode == KeyEvent.VK_LEFT && state != ToRight) {
            if (state == ToLeft) {
                x = x - 25;
            }
            state = ToLeft;
        }

        if (!life && keyCode == KeyEvent.VK_ENTER) {
            life = true;
            state = ToRight;
            x = 0;
            y = 0;
            bodys.clear();//清空容器
            foods.clear();
            move();
        }
    }
}
