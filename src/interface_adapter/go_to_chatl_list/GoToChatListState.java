package interface_adapter.go_to_chatl_list;

import entity.ChatChannel;
import entity.User;

import java.util.ArrayList;

public class GoToChatListState {
    private ArrayList<ChatChannel> chatChannels = null;
    private String errorMessage = null;
    private boolean success = false;
    private User user = null;
    public GoToChatListState(GoToChatListState copy){
        this.chatChannels = copy.chatChannels;
        this.errorMessage = copy.errorMessage;
        this.success = copy.success;
        this.user = copy.user;
    }
    public GoToChatListState(User user, ArrayList<ChatChannel> chatChannels){
        this.user = user;
        this.chatChannels = chatChannels;
    }
    public GoToChatListState(){}
    public void setChatChannels(ArrayList<ChatChannel> chatChannels){
        this.chatChannels = chatChannels;
    }
    public ArrayList<ChatChannel> getChatChannels(){
        return chatChannels;
    }
    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage(){
        return errorMessage;
    }
    public void setSuccess(boolean success){
        this.success = success;
    }
    public boolean getSuccess(){
        return success;
    }
    public void setUser(User user){
        this.user = user;
    }
    public User getUser(){
        return user;
    }
    public String toString(){
        return "GoToChatListState(chatChannels=" + this.getChatChannels() +
                ", errorMessage=" + this.getErrorMessage() +
                ", success=" + this.getSuccess() +
                ", user=" + this.getUser() + ")";
    }
}
