package main.java.use_case.go_to_chat_list;

import main.java.entity.ChatChannel;
import main.java.entity.User;

import java.util.ArrayList;

public interface GoToChatListDataAccessInterface {
    ArrayList<ChatChannel> getAllChatChannels(User user) throws RuntimeException;
}
