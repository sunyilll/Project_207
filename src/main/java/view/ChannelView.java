package main.java.view;

import main.java.interface_adapter.send_message.SendMessageController;
import main.java.interface_adapter.send_message.SendMessageViewModel;
import main.java.interface_adapter.send_message.SendMessageState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChannelView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "channel";
    private final SendMessageViewModel sendMessageViewModel;

    private final SendMessageController sendMessageControllerw;

    private final JButton send;
    private final JTextField messageInputField = new JTextField(15);
    public ChannelView(SendMessageViewModel sendMessageViewModel, SendMessageController sendMessageController, JButton send){
        this.sendMessageViewModel = sendMessageViewModel;
        this.sendMessageControllerw = sendMessageController;
        this.send = send;
        sendMessageViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(sendMessageViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        send.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(send)){
                            SendMessageState currentState = sendMessageViewModel.getState();

                            sendMessageControllerw.execute(
                                    //TODO

                            );
                        }
                    }
                }
        );



        //todo
//        JButton send = new JButton("Send");
        this.add(messageInputField);
        this.add(send);
    }
    public void actionPerformed(ActionEvent evt){}
    public void propertyChange(PropertyChangeEvent evt){  // called when view model firePropertyChange()

    }


}
