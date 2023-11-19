package main.java.view;
import javax.swing.*;
import java.awt.*;

public class HomeBar extends javax.swing.JPanel{
    HomeBar(){
        this.setSize(720, 100);
        this.setBackground(Color.BLUE);
    }
    public static void main(){
        JFrame f = new JFrame();
        JPanel h = new HomeBar();
        f.setSize(720, 1080);
        f.setContentPane(h);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}
