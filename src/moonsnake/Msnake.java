package moonsnake;

import javax.swing.*;
import java.awt.*;

/**
 * @Author:xiang
 * @Date:2020/3/6 22:30
 * 窗口
 */
public class Msnake {
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setBounds(10,10,900,720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MPanel());

        frame.setVisible(true);
    }
}
