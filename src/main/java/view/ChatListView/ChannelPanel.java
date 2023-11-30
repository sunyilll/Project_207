package main.java.view.ChatListView;

import main.java.entity.ChatChannel;
import main.java.entity.User;
import main.java.interface_adapter.go_to_chat.GoToChannelController;
import main.java.interface_adapter.go_to_chat.GoToChannelState;
import main.java.interface_adapter.go_to_chat.GoToChannelViewModel;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListController;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;

public class ChannelPanel extends JPanel implements ActionListener, PropertyChangeListener {
    ChatChannel channel;
    User currUser;
    User otherUser;
    GoToChannelViewModel viewModel;
    GoToChannelController controller;
    JButton enterChatButton;
    ChannelPanel(ChatChannel channel1, User user, GoToChannelViewModel viewModel1, GoToChannelController controller1){
        this.channel = channel1;
        this.currUser = user;
        this.controller = controller1;
        this.viewModel = viewModel1;

        // Assuming there are always exactly two entries in the map
        User user1 = channel.getMembers().get(user.getUserID());

// Get all keys in the map
        Set<String> keys = channel.getMembers().keySet();

// Find the key that doesn't match "user1"
        String otherKey = keys.stream()
                .filter(key -> !key.equals(user.getUserID()))
                .findFirst()
                .orElse(null); // handle the case where the key is not found

// Retrieve the other user using the found key
        System.out.println(otherKey);
        User otherUser = channel.getMembers().get(otherKey);
        this.otherUser = otherUser;

        this.add(new JLabel(otherKey), BorderLayout.WEST);
        JButton enterChatButton1 = new JButton("Enter Chat");
        this.enterChatButton = enterChatButton1;

        enterChatButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(enterChatButton)){
                            GoToChannelState goToChannelState = viewModel.getState();
//                            controller.execute(currUser, channel);
//                            System.out.println(otherKey);
                            controller.execute(goToChannelState.getCurrentUser(),goToChannelState.getCurrentChannel());
                        }
                    }
                }
        );



        this.add(enterChatButton, BorderLayout.EAST);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
