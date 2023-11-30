package main.java.view.ChatListView;

import main.java.entity.ChatChannel;
import main.java.entity.User;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListController;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class ChannelPanel extends JPanel {
    ChatChannel channel;
    User currUser;
    User otherUser;
    GoToChatListViewModel viewModel;
    GoToChatListController controller;
    JButton enterChatButton;
    ChannelPanel(ChatChannel channel, User user, GoToChatListViewModel viewModel, GoToChatListController controller){
        this.channel = channel;
        this.currUser = user;
        this.controller = controller;
        this.viewModel = viewModel;

        // Assuming there are always exactly two entries in the map
        User user1 = channel.getMembers().get("user1");

// Get all keys in the map
        Set<String> keys = channel.getMembers().keySet();

// Find the key that doesn't match "user1"
        String otherKey = keys.stream()
                .filter(key -> !key.equals("user1"))
                .findFirst()
                .orElse(null); // handle the case where the key is not found

// Retrieve the other user using the found key
        User otherUser = channel.getMembers().get(otherKey);
        this.otherUser = otherUser;

        this.add(new JLabel(otherUser.getUserID()), BorderLayout.WEST);
        JButton enterChatButton1 = new JButton("Enter Chat");
        this.enterChatButton = enterChatButton1;

//        enterChatButton.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (evt.getSource(),equals(enterChatButton)){
//                            GoToChatState goToChatState = new GoToChatState();
//                            goToChatController.execute();
//                        }
//                    }
//                }
//        );



        this.add(enterChatButton, BorderLayout.EAST);



    }
}
