package main.java.view.ChannelView;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {

    String user_id ="";
    String message = "";

    MessagePanel(String user_id, String message) {
        this.user_id = user_id;
        this.message = message;
        this.add(new JLabel(this.user_id), BorderLayout.WEST);
        this.add(new JTextField(this.message), BorderLayout.SOUTH);
    }
}
