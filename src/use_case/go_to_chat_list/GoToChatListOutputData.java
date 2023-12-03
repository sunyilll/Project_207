package use_case.go_to_chat_list;

import entity.ChatChannel;
import entity.User;

import java.util.ArrayList;

public class GoToChatListOutputData {
    private final ArrayList<ChatChannel> chatChannels;
    private final User user;
    public GoToChatListOutputData(ArrayList<ChatChannel> chatChannels, User user) {
        this.chatChannels = chatChannels;
        this.user = user;
    }
    public ArrayList<ChatChannel> getChatChannels() {
        return chatChannels;
    }
    public User getUser() {
        return user;
    }
}
