package main.java.view;

import javax.swing.*;

public class ViewWithHomeBar extends JPanel{
    private JPanel mainPanel;
    private JPanel homePanel;
    private JButton searchButton;
    private JButton profileButton;
    private JButton messageButton;

    ViewWithHomeBar(){}

    public static void main(String[] args) {
        JFrame f = new FrameModel("SearchView");
        f.add(new ViewWithHomeBar());
        f.setVisible(true);
    }
}
