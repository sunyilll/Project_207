package interface_adapter.refresh_chat_page;

import kotlin.Pair;
import entity.ChatChannel;
import entity.User;

import java.util.ArrayList;

public class RefreshChatPageState {
    private ChatChannel channel = null;
    private ArrayList<Pair<String, String>> messageList = null;
    private String user_id = "";
    private boolean refreshSuccessful = false;
    private String errorMessage = null;

    public RefreshChatPageState(RefreshChatPageState copy){
        this.channel = copy.channel;
        this.messageList = copy.messageList;
        this.user_id = copy.user_id;
        this.refreshSuccessful = copy.refreshSuccessful;
        this.errorMessage = copy.errorMessage;
    }

    public RefreshChatPageState(User user, ChatChannel channel) {
        this.user_id = user.getUserID();
        this.channel = channel;
    }
    public RefreshChatPageState(){}

    public void setChannel(ChatChannel channel) {
        this.channel = channel;
    }
    public ChatChannel getChannel(){
        return channel;
    }
    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
    public String getUser_id(){
        return user_id;
    }
    public void setMessageList(ArrayList<Pair<String, String>> messageList){
        this.messageList = messageList;
    }
    public ArrayList<Pair<String, String>> getMessageList(){
        return messageList;
    }
    public void setRefreshSuccessful(boolean refreshSuccessful){
        this.refreshSuccessful = refreshSuccessful;
    }
    public boolean getRefreshSuccessful(){
        return refreshSuccessful;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public String toString() {
        return "RefreshChatPageState{" +
                "refreshSuccessful=" + refreshSuccessful +
                ", errorMessage='" + errorMessage +"\"";
    }
}
