package main.java.use_case.refresh_chat_page;

import main.java.entity.ChatChannel;

import java.util.ArrayList;

public interface RefreshChatPageDataAccessInterface {
    ArrayList<String> getMessageList(ChatChannel channel) throws RuntimeException;
}
