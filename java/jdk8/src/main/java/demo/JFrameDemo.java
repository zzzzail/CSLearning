package demo;

import javax.swing.*;

public class JFrameDemo {
    
    public static void main(String[] args) {
        // 窗体类
        JFrame jFrame = new JFrame();
        // 头
        jFrame.setTitle("奇怪的窗体");
        // 窗口大小
        jFrame.setSize(500, 500);
        // 设置关闭即退出
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置居中显示
        jFrame.setLocationRelativeTo(null);
    
        // 设置为可见
        jFrame.setVisible(true);
    }
}
