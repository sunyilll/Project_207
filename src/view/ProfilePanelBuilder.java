package view;

import javax.swing.*;
import java.awt.*;

public class ProfilePanelBuilder {
    JPanel mainPanel;
    public ProfilePanelBuilder() {
        this.create();
    }
    private JPanel horizontalPanel(JLabel titleLabel, JLabel contentLabel) {
        JPanel panel = new JPanel();
        panel.add(titleLabel);
        panel.add(contentLabel);
        panel.setLayout(new GridLayout(1, 2));
        return panel;
    }
    private JPanel verticalPanel(JLabel titleLabel, JLabel contentLabel) {
        JPanel panel = new JPanel();
        panel.add(titleLabel);
        panel.add(contentLabel);
        panel.setLayout(new GridLayout(2, 1));
        return panel;
    }
    public JLabel add(String title, String content, String layout) {
        if (content == null) {
            content = "";
        }
        JLabel titleLabel = new JLabel(title);
        JLabel contentLabel = new JLabel(content);

        if (layout.equals("horizontal")) {
            mainPanel.add(horizontalPanel(titleLabel, contentLabel));
        } else if (layout.equals("vertical")) {
            mainPanel.add(verticalPanel(titleLabel, contentLabel));
        } else {
            System.out.println("Invalid layout");
        }
        return contentLabel;
    }
    public void add(String title) {
        JPanel panel = new JPanel();
        JLabel titleLabel = new JLabel(title);
        panel.add(titleLabel);
        panel.setLayout(new GridLayout(1, 1));
        mainPanel.add(panel);
    }
    public JPanel build() {
        return mainPanel;
    }
    public void create() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    }
}
