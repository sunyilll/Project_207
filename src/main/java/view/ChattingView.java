package main.java.view;

import main.java.interface_adapter.send_message.SendMessageController;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class ChattingView extends JPanel implements ActionListener, PropertyChangeListener {
    private final SendMessageController sendMessageController;
    private final JButton send;
    private final JTextField messageInputField = new JTextField(15);
    public ChattingView(SendMessageController sendMessageController){
        this.sendMessageController = sendMessageController;
        //todo
        JButton send = new JButton("Send");
        this.add(send);
    }
    public void actionPerformed(ActionEvent evt){}
    public void propertyChange(PropertyChangeEvent evt){  // called when view model firePropertyChange()

    }
}
