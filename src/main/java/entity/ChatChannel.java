package main.java.entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ChatChannel {
    protected Map<String, User> members;
    protected final LocalDateTime creationTime;
    protected List<String> chatHistory = new ArrayList<>();
    protected String channelURL;

    public ChatChannel(Map<String, User> members, LocalDateTime creationTime, String channelURL){
        this.members = members;
        this.creationTime = creationTime;
        this.channelURL = channelURL;
    }

    public List<String> getChatHistory(){
        return this.chatHistory;
    }

    public void addUser(User user){
        this.members.put(user.getUserID(), user);
    }

    public void removeUser(User user){
        this.members.remove(user.getUserID());
    }

    public String getChannelURL(){
        return channelURL;
    }
}
