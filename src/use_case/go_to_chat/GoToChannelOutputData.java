package use_case.go_to_chat;

import entity.ChatChannel;
import entity.User;

public class GoToChannelOutputData {
    private final User currentUser;
    private final ChatChannel currentChannel;
    public GoToChannelOutputData(User currentUser, ChatChannel currentChannel){
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
