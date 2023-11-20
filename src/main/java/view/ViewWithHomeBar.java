package main.java.view;

import javax.swing.*;

public class ViewWithHomeBar extends FrameModel{
    private JPanel mainPanel;
    private JPanel homePanel;
    private JButton searchButton;
    private JButton profileButton;
    private JButton messageButton;

    ViewWithHomeBar(){
        super("ViewNamexxxx");
        super.setContentPane(mainPanel);
        super.setVisible(true);
    }

    public static void main(String[] args) {
        new ViewWithHomeBar();
    }
}
