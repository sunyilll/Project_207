package use_case.go_to_chat_list;

import entity.ChatChannel;
import entity.User;

import java.util.ArrayList;

public interface GoToChatListDataAccessInterface {
    ArrayList<ChatChannel> getAllChatChannels() throws RuntimeException;
    User getCurrentUser();
}
