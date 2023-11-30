package main.java.use_case.go_to_chat;

import main.java.entity.ChatChannel;
import main.java.entity.User;

public class GoToChannelInputData {
    final private User currentUser;
    final private ChatChannel currentChannel;
    public GoToChannelInputData(User currentUser, ChatChannel currentChannel){
        this.currentUser = currentUser;
        this.currentChannel = currentChannel;
    }
    public User getCurrentUser(){
        return currentUser;
    }
    public ChatChannel getCurrentChannel(){
        return currentChannel;
    }
}
