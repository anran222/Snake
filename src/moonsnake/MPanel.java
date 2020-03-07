package moonsnake;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * @Author:xiang
 * @Date:2020/3/6 22:36
 * 画布
 */
public class MPanel extends JPanel implements KeyListener, ActionListener {
    ImageIcon title=new ImageIcon("title.jpg");
    ImageIcon body=new ImageIcon("body.png");
    ImageIcon up=new ImageIcon("up.png");
    ImageIcon down=new ImageIcon("down.png");
    ImageIcon left=new ImageIcon("left.png");
    ImageIcon right=new ImageIcon("right.png");
    ImageIcon food=new ImageIcon("food.png");

    int len=3;

    int score=0;

    int[] snakex=new int[750];
    int[] snakey=new int[750];

    String fx="R";

    boolean isStart=false;

    boolean isFail=false;

    Timer timer=new Timer(100,this);

    int foodx;
    int foody;
    Random rand=new Random();

    public MPanel(){
        initSnake();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        title.paintIcon(this, g, 25, 11);
        g.fillRect(25, 75, 850, 600);
        g.setColor(Color.YELLOW);
        g.drawString("长度："+len,750,35);
        g.drawString("成绩："+score,750,50);


        if (fx == "R") {
            right.paintIcon(this, g, snakex[0], snakey[0]);
        } else if (fx == "L") {
            left.paintIcon(this, g, snakex[0], snakey[0]);
        } else if (fx == "D") {
            down.paintIcon(this, g, snakex[0], snakey[0]);
        } else if (fx == "U") {
            up.paintIcon(this, g, snakex[0], snakey[0]);
        }
        for (int i = 1; i < len; i++) {
            body.paintIcon(this, g, snakex[i], snakey[i]);
        }
        food.paintIcon(this,g,foodx,foody);
        if (isStart == false) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按空格键开始游戏！", 300, 300);
        }
        if (isFail) {
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("你已经死亡，请重新开始！", 300, 300);
        }
    }

    public void initSnake(){
        len=3;
        snakex[0]=100;
        snakey[0]=100;
        snakex[1]=75;
        snakey[1]=100;
        snakex[2]=50;
        snakey[2]=100;
        foodx=25+25*rand.nextInt(34);
        foody=75+25*rand.nextInt(24);
        fx="R";
        score=0;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode=e.getKeyCode();
        if (keyCode==KeyEvent.VK_SPACE){
            if (isFail){
                isFail=false;
                initSnake();
            }else {
                isStart = !isStart;
            }
            repaint();
        }else if (keyCode==KeyEvent.VK_LEFT){
            fx="L";
        }else if (keyCode==KeyEvent.VK_RIGHT){
            fx="R";
        }
        else if (keyCode==KeyEvent.VK_UP){
            fx="U";
        }
        else if (keyCode==KeyEvent.VK_DOWN){
            fx="D";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart&&!isFail) {
            for (int i = len - 1; i > 0; i--) {
                snakex[i] = snakex[i - 1];
                snakey[i] = snakey[i - 1];
            }
            if (fx=="R"){
            snakex[0] = snakex[0] + 25;
            if (snakex[0] > 850) {
                snakex[0] = 25;
            }
            }else if (fx=="L"){
                snakex[0] = snakex[0] - 25;
                if (snakex[0] <25) {
                    snakex[0] = 850;
                }
            }
            else if (fx=="U"){
                snakey[0] = snakey[0] - 25;
                if (snakey[0] <75) {
                    snakey[0] = 650;
                }
            }
            else if (fx=="D"){
                snakey[0] = snakey[0] +25;
                if (snakey[0] >650) {
                    snakey[0] = 75;
                }
            }
            if (snakex[0]==foodx&&snakey[0]==foody){
                len++;
                score+=10;
                foodx=25+25*rand.nextInt(34);
                foody=75+25*rand.nextInt(24);
            }
            for (int i = 1; i <len ; i++) {
                if (snakex[i]==snakex[0]&&snakey[i]==snakey[0]){
                    isFail=true;
                }
            }
            repaint();
        }
            timer.start();
        }
}
