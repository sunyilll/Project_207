package interface_adapter.go_to_chat;

import entity.ChatChannel;
import entity.User;

public class GoToChannelState {
    private User currentUser = null;
    private ChatChannel currentChannel = null;
    private String errorMessage = "";
    private boolean success = false;
    public GoToChannelState(GoToChannelState copy){
        this.currentUser = copy.currentUser;
        this.currentChannel = copy.currentChannel;
        this.errorMessage = copy.errorMessage;
        this.success = copy.success;
    }
    public GoToChannelState(User currentUser, ChatChannel currentChannel){
        this.currentUser = currentUser;
        this.currentChannel = currentChannel;
    }
    public GoToChannelState(){}
    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }
    public User getCurrentUser(){
        return currentUser;
    }
    public void setCurrentChannel(ChatChannel currentChannel){
        this.currentChannel = currentChannel;
    }
    public ChatChannel getCurrentChannel(){
        return currentChannel;
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
    public String toString(){
        return "GoToChannelState(currentUser=" + this.getCurrentUser() +
                ", currentChannel=" + this.getCurrentChannel() +
                ", errorMessage=" + this.getErrorMessage() +
                ", success=" + this.getSuccess() + ")";
    }

}
