package main.java.view;
import javax.swing.*;

public class FrameModel extends javax.swing.JFrame{
    String viewName;
    public FrameModel(String viewName){
        this.setName(viewName);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(480, 800);
    }
}

