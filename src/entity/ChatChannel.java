package entity;
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

    public String getChannelURL(){
        return channelURL;
    }
    public Map<String, User> getMembers(){
        return members;
    }
}
