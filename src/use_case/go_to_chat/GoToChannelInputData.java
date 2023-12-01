package use_case.go_to_chat;

import entity.ChatChannel;
import entity.User;

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
