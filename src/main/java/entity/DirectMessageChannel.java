package main.java.entity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public class DirectMessageChannel extends ChatChannel{

    public DirectMessageChannel(Map<String, User> members, LocalDateTime creationTime, String[] chatHistory, String channelURL){
        super(members, creationTime, channelURL);
    }

}
