package main.java.view;

import javax.swing.*;

public class FrameModel extends javax.swing.JFrame{
    String viewName;
    JPanel viewPanel;
    FrameModel(){
        this.setName(viewName);
        this.setSize(720, 1280);
        this.setContentPane(viewPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}

