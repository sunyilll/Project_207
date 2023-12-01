package use_case.go_to_chat_list;

import entity.ChatChannel;

import java.util.ArrayList;

public class GoToChatListOutputData {
    private final ArrayList<ChatChannel> chatChannels;
    public GoToChatListOutputData(ArrayList<ChatChannel> chatChannels) {
        this.chatChannels = chatChannels;
    }
    public ArrayList<ChatChannel> getChatChannels() {
        return chatChannels;
    }
}
