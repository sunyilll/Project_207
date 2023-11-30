package main.java.view.ChannelView;

import kotlin.Pair;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListController;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListState;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import main.java.interface_adapter.refresh_chat_page.RefreshChatPageController;
import main.java.interface_adapter.refresh_chat_page.RefreshChatPageState;
import main.java.interface_adapter.refresh_chat_page.RefreshChatPageViewModel;
import main.java.interface_adapter.send_message.SendMessageController;
import main.java.interface_adapter.send_message.SendMessageViewModel;
import main.java.interface_adapter.send_message.SendMessageState;
import main.java.view.FrameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class ChannelView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "channel";
    private final SendMessageViewModel sendMessageViewModelw;

    private final SendMessageController sendMessageControllerw;
    private final RefreshChatPageViewModel refreshChatPageViewModel1;
    private final RefreshChatPageController refreshChatPageController1;
    private final GoToChatListViewModel goToChatListViewModel1;
    private final GoToChatListController goToChatListController1;

    private final JButton send;
    private final JButton back;
    private final JTextField messageInputField = new JTextField(15);
    private final JButton refresh;

    private JPanel chatArea;

    public ChannelView(SendMessageViewModel sendMessageViewModel, SendMessageController sendMessageController, RefreshChatPageViewModel refreshChatPageViewModel, RefreshChatPageController refreshChatPageController, GoToChatListViewModel goToChatListViewModel, GoToChatListController goToChatListController){
        this.sendMessageViewModelw = sendMessageViewModel;
        this.sendMessageControllerw = sendMessageController;
        this.refreshChatPageController1 = refreshChatPageController;
        this.refreshChatPageViewModel1 = refreshChatPageViewModel;
        this.goToChatListController1 = goToChatListController;
        this.goToChatListViewModel1 = goToChatListViewModel;
        this.send = new JButton("send");
        sendMessageViewModelw.addPropertyChangeListener(this);
        sendMessageViewModelw.firePropertyChanged();

        JLabel title = new JLabel(sendMessageViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

//        chatArea = new JTextArea();
//        chatArea.setEditable(false);

//        JScrollPane scrollPane = new JScrollPane(chatArea);
//        add(scrollPane, BorderLayout.CENTER);
        JPanel functionPanel = new JPanel();
        functionPanel.setLayout(new BoxLayout(functionPanel, BoxLayout.X_AXIS));
        JButton backButton = new JButton("Back");
        this.back = backButton;
        JButton refreshButton = new JButton("refresh");
        this.refresh = refreshButton;


        functionPanel.add(backButton, BorderLayout.WEST);
        functionPanel.add(refreshButton, BorderLayout.EAST);
        this.add(functionPanel, BorderLayout.NORTH);



        JPanel chatpanel = new JPanel();
        chatpanel.setLayout(new BoxLayout(chatpanel, BoxLayout.Y_AXIS));

        // Add some JPanels to the container panel
        for (int i = 0; i < 100; i++) {
            MessagePanel innerPanel = new MessagePanel("User1", "hehe");
            innerPanel.setPreferredSize(new Dimension(350, 50));
            innerPanel.setBackground(Color.LIGHT_GRAY);
            innerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            chatpanel.add(innerPanel);
        }

        JScrollPane scrollPane = new JScrollPane(chatpanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create a wrapper panel with a fixed size
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setPreferredSize(new Dimension(400, 600));
        wrapperPanel.add(scrollPane);
        this.chatArea = wrapperPanel;

        this.add(chatArea, BorderLayout.NORTH);

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)){
                            System.out.println("I am at step 1");
                            GoToChatListState currState = goToChatListViewModel1.getState();
                            goToChatListController1.execute(currState.getUser());

                            System.out.println("I am at step 2");
                        }
                    }
                }
        );


        refresh.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(refresh)){
                            RefreshChatPageState currState = refreshChatPageViewModel1.getState();
                            System.out.println(currState);

                            refreshChatPageController1.execute(currState.getChannel());

                            RefreshChatPageState newState = refreshChatPageViewModel1.getState();
                            setChatArea(newState.getMessageList());


                        }
                    }
                }
        );


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
                            System.out.println("I am trying to send message");

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
        this.add(inputPanel, BorderLayout.SOUTH);


    }
    public void setChatArea(ArrayList<Pair<String, String>> messageAndUserPairList) {
        chatArea.removeAll();

        JPanel chatpanelNew = new JPanel();
        chatpanelNew.setLayout(new BoxLayout(chatpanelNew, BoxLayout.Y_AXIS));

        for (Pair<String, String> messageAndUserIDPair : messageAndUserPairList) {
            MessagePanel innerPanel = new MessagePanel(messageAndUserIDPair.getFirst(), messageAndUserIDPair.getSecond());
            innerPanel.setPreferredSize(new Dimension(350, 50));
            innerPanel.setBackground(Color.LIGHT_GRAY);
            innerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            chatpanelNew.add(innerPanel);
        }
        JScrollPane scrollPane = new JScrollPane(chatpanelNew);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.chatArea.add(scrollPane);
        chatArea.revalidate();
        chatArea.repaint();
    }

    public void actionPerformed(ActionEvent evt){}
    public void propertyChange(PropertyChangeEvent evt){  // called when view model firePropertyChange()
        SendMessageState sendMessageState = (SendMessageState) evt.getNewValue();
        if(sendMessageState.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(this, sendMessageState.getErrorMessage());
        }
    }


}
