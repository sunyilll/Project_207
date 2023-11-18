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
    public final String viewName = "channel";
    private final SendMessageViewModel sendMessageViewModelw;

    private final SendMessageController sendMessageControllerw;

    private final JButton send;
    private final JTextField messageInputField = new JTextField(15);

    private JTextArea chatArea;

    public ChannelView(SendMessageViewModel sendMessageViewModel, SendMessageController sendMessageController){
        this.sendMessageViewModelw = sendMessageViewModel;
        this.sendMessageControllerw = sendMessageController;
        this.send = new JButton("send");
        sendMessageViewModelw.addPropertyChangeListener(this);
        sendMessageViewModelw.firePropertyChanged();

        JLabel title = new JLabel(sendMessageViewModel.TITLE_LABEL);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        chatArea = new JTextArea();
        chatArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

//        messageInputField = new JTextField(15);
        inputPanel.add(messageInputField, BorderLayout.CENTER);

        send.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(send)){

                            SendMessageState currentState = sendMessageViewModelw.getState();
                            System.out.println(currentState);

                            sendMessageControllerw.execute(
                                    currentState.getMessage(),
                                    currentState.getUser_id(),
                                    currentState.getChannel()
                            );
                        }
                    }
                }
        );

        messageInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SendMessageState currentState = sendMessageViewModelw.getState();
                        currentState.setMessgae(messageInputField.getText() + e.getKeyChar());
                        sendMessageViewModelw.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );


        inputPanel.add(send, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);




        //todo
//        JButton send = new JButton("Send");
//        this.add(messageInputField);
//        this.add(send);
    }
    public void actionPerformed(ActionEvent evt){}
    public void propertyChange(PropertyChangeEvent evt){  // called when view model firePropertyChange()
        SendMessageState sendMessageState = (SendMessageState) evt.getNewValue();
        if(sendMessageState.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(this, sendMessageState.getErrorMessage());
        }
    }


}
