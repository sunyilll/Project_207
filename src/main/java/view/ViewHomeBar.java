package main.java.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewHomeBar extends JPanel{
    private JPanel mainPanel;
    private JPanel homePanel;
    private JButton searchButton;
    private JButton profileButton;
    private JButton messageButton;

    ViewHomeBar(){this.add(homePanel);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo: add GoToSearch Controller
                System.out.println("Click go to search");
            }
        });
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo: add GoToProfile Controller
                System.out.println("click go to profile");
            }
        });
        messageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo: add GoToMessage Controller
                System.out.println("click go to message page");
            }
        });
    }

    public static void main(String[] args) {
        JFrame f = new FrameModel("SearchView");
        f.add(new ViewHomeBar());
        f.setVisible(true);
    }
}
